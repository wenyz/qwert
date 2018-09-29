# 激励函数
# 解决不能单纯用线性方程来概括问题

# 学习如何添加一层 hide 层
# 有3层 输入层 输出层 隐藏层
import tensorflow as tf
import numpy as np
import matplotlib.pyplot as plt


# 定义层函数 输入数据 进入数据神经元个数 出来数据神经元个数 激励函数
def add_layer(inputs, in_size, out_size, activation_function=None):
    # 权重 偏差 函数值 3个层内要有的东西
    Weights = tf.Variable(tf.random_normal([in_size, out_size]))
    biases = tf.Variable(tf.zeros([1, out_size]) + 0.1)  # biases 推荐不为零 所以加上0.1
    Wx_plus_b = tf.matmul(inputs, Weights) + biases
    if activation_function is None:
        outputs = Wx_plus_b
    else:
        outputs = activation_function(Wx_plus_b)
    return outputs


# 模拟数据 x_data y_data
x_data = np.linspace(-1, 1, 300)[:, np.newaxis]
noise = np.random.normal(0, 0.05, x_data.shape)
y_data = np.square(x_data) - 0.5 + noise
# 声明placeholder 来hold住要测试的data
xs = tf.placeholder(tf.float32, [None, 1])
ys = tf.placeholder(tf.float32, [None, 1])
# 追加一层 x进去 1个 出来 10个 激励函数是 relu
l1 = add_layer(xs, 1, 10, activation_function=tf.nn.relu)
# 预测层 输入是上一层 10个进去 1个出来 无激励函数
prediction = add_layer(l1, 10, 1, activation_function=None)
# 计算损失函数 由此判断预测的正确性
loss = tf.reduce_mean(tf.reduce_sum(tf.square(ys - prediction), reduction_indices=[1]))
# 指定训练的目的 损失函数要梯度下降，效率为0.1 loss 要变小
train_step = tf.train.GradientDescentOptimizer(0.1).minimize(loss)

# init = tf.initialize_all_variables()
sess = tf.Session()
sess.run(tf.global_variables_initializer())

fig = plt.figure()
ax = fig.add_subplot(1, 1, 1)
ax.scatter(x_data, y_data)
# plot 输出会暂停 要加以下这个函数 py3.5的功能
plt.ion()
# plt.show()

# 训练次数
for i in range(1000):
    sess.run(train_step, feed_dict={xs: x_data, ys: y_data})
    if i % 50 == 0:
        # print(sess.run(loss,feed_dict={xs:x_data,ys:y_data}))
        try:
            ax.lines.remove(lines[0])
        except Exception as e:
            print(e)
            pass
        prediction_value = sess.run(prediction, feed_dict={xs: x_data})
        lines = ax.plot(x_data, prediction_value, 'r-', lw=5)
        # plt.pause(.1)
        # 这个函数不知道为什么有问题，pause了以后不继续绘图
        # 很大概率是不兼容anaconda 的jupyter

# 随着每次执行 loss 逐渐变小 说明该网络在不断优化
