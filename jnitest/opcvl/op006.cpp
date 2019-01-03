//
// Created by wenyz on 2018/12/29.
// 滤波分为两种 一种为线性滤波 一种为非线性滤波

#include "op006.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

int main(){
    // 线性滤波：方框滤波、均值滤波、高斯滤波

    // 方框滤波
    Mat img = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img);
    Mat out;
    boxFilter(img, out, -1, Size(5, 5));//-1指原图深度
    imshow("方框滤波", out);

    // 均值滤波 均值滤波的缺点就是不能很好地保护细节，在图像去燥的同时也破坏了图像的而细节部分，从而使图像变得模糊，不能很好的去除噪点。
    Mat img2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out2;
    blur(img2, out2,Size(5, 5));//-1指原图深度
    imshow("均值滤波", out2);

    // 高斯滤波，可以消除高斯噪声，广泛应用于图像处理的减噪过程。
    Mat img3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out3;
    GaussianBlur(img3, out3, Size(3, 3), 0, 0);
    imshow("高斯滤波", out3);


    // 非线性滤波：中值滤波、双边滤波
//    中值滤波
//    基本思想就是用像素点的领域灰度的中值来代替该像素点的灰度值，该方法在去除脉冲噪声、椒盐噪声的同时又能保留图像的细节（不会出现边缘模糊的情况）。
//    中值滤波跟均值滤波的思想看起来很相似，只是一个取平均值，一个取中位数而已
//    现在说说中值滤波与均值滤波的比较：均值滤波中噪声成分会被加入到平均计算，所以输出是受到噪声的影响的。但是中值滤波中，由于噪声成分很难选上，所以基本不影响输出。当然好的性能也需要付出一点代价的，中值滤波花费的时间是均值滤波的5倍以上。
//    中值滤波一般采用奇数的卷积核。
//    中值滤波对一些细节多（特别是细、尖顶的）的图像不太适合。
    Mat img4 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out4;
    medianBlur(img4, out4, 7);//第三个参数表示孔径的线性尺寸，它的值必须是大于1的奇数
    imshow("中值滤波", out4);

//    双边滤波
//            双边滤波的最大特点就是做边缘保存
    // 效果图看来，双边滤波是所有滤波中最清晰的。
    Mat img5 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out5;
    bilateralFilter(img5, out5, 25, 25 * 2, 25 / 2);
    imshow("双边滤波", out5);


//    腐蚀和膨胀是最基本的形态学运算。
//    腐蚀和膨胀是针对白色部分（高亮部分）而言的。
//    膨胀就是对图像高亮部分进行“领域扩张”，效果图拥有比原图更大的高亮区域；腐蚀是原图中的高亮区域被蚕食，效果图拥有比原图更小的高亮区域。
//    膨胀
//    膨胀就是求局部最大值的操作，从图像直观看来，就是将图像光亮部分放大，黑暗部分缩小

    Mat img6 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    namedWindow("原始图", WINDOW_NORMAL);
    imshow("原始图", img6);
    Mat out6;
    //获取自定义核
    Mat element = getStructuringElement(MORPH_RECT, Size(15, 15)); //第一个参数MORPH_RECT表示矩形的卷积核，当然还可以选择椭圆形的、交叉型的
    //膨胀操作
    dilate(img6, out6, element);
    namedWindow("膨胀操作", WINDOW_NORMAL);
    imshow("膨胀操作", out6);

    // 可以看到，图像原来光亮的部分被放大了，黑暗的部分被缩小了
    Mat img7 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    namedWindow("原始图", WINDOW_NORMAL);
    imshow("原始图", img7);
    Mat out7;
    //获取自定义核
    Mat element2 = getStructuringElement(MORPH_RECT, Size(15, 15)); //第一个参数MORPH_RECT表示矩形的卷积核，当然还可以选择椭圆形的、交叉型的
    //腐蚀操作
    erode(img7, out7, element2);
    namedWindow("腐蚀操作", WINDOW_NORMAL);
    imshow("腐蚀操作", out7);


//    开运算：先腐蚀再膨胀，用来消除小物体
//    闭运算：先膨胀再腐蚀，用于排除小型黑洞
//    形态学梯：就是膨胀图与俯视图之差，用于保留物体的边缘轮廓。
//    顶帽：原图像与开运算图之差，用于分离比邻近点亮一些的斑块。
//    黑帽：闭运算与原图像之差，用于分离比邻近点暗一些的斑块。
//    opencv里有一个很好的函数getStructuringElement，我们只要往这个函数传相应的处理参数，就可以进行相应的操作了，使用起来非常方便。

    Mat img8 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    namedWindow("原始图", WINDOW_NORMAL);
    imshow("原始图", img8);
    Mat out8;
    //获取自定义核
    Mat element3 = getStructuringElement(MORPH_RECT, Size(15, 15)); //第一个参数MORPH_RECT表示矩形的卷积核，当然还可以选择椭圆形的、交叉型的

    //高级形态学处理，调用这个函数就可以了，具体要选择哪种操作，就修改第三个参数就可以了。这里演示的是形态学梯度处理
    morphologyEx(img8, out8, MORPH_GRADIENT, element3);
    namedWindow("形态学处理操作", WINDOW_NORMAL);
    imshow("形态学处理操作", out8);


    // 对图像进行缩放的最简单方法当然是调用resize函数啦！
    Mat img9 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img9);
    Mat dst = Mat::zeros(512, 512, CV_8UC3); //我要转化为512*512大小的
    resize(img9, dst, dst.size());
    imshow("尺寸调整之后", dst);

    Mat img10 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img9);
    Mat dst2;
    resize(img10, dst2, Size(),0.5,0.5);//我长宽都变为原来的0.5倍
    imshow("尺寸调整之后", dst2);

    //图像金字塔
//    两种类型的金字塔：
//    高斯金字塔：用于下采样，主要的图像金字塔；
//    拉普拉斯金字塔：用于重建图像，也就是预测残差（我的理解是，因为小图像放大，必须插入一些像素值，那这些像素值是什么才合适呢，那就得进行根据周围像素进行预测），对图像进行最大程度的还原。比如一幅小图像重建为一幅大图像，
//    图像金字塔有两个高频出现的名词：上采样和下采样。现在说说他们俩。
//    上采样：就是图片放大（所谓上嘛，就是变大），使用PryUp函数
//    下采样：就是图片缩小（所谓下嘛，就是变小），使用PryDown函数
//    下采样将步骤：
//    对图像进行高斯内核卷积
//            将所有偶数行和列去除
//    下采样就是图像压缩，会丢失图像信息。
//    上采样步骤：
//    将图像在每个方向放大为原来的两倍，新增的行和列用0填充；
//    使用先前同样的内核（乘以4）与放大后的图像卷积，获得新增像素的近似值。
//    上、下采样都存在一个严重的问题，那就是图像变模糊了，因为缩放的过程中发生了信息丢失的问题。要解决这个问题，就得看拉普拉斯金字塔了。

    Mat img11 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img11);

    Mat dst3,dst4;
    pyrUp(img11, dst3, Size(img11.cols*2, img11.rows*2)); //放大一倍
    pyrDown(img11, dst4, Size(img11.cols * 0.5, img11.rows * 0.5)); //缩小为原来的一半
    imshow("尺寸放大之后", dst3);
    imshow("尺寸缩小之后", dst4);

    waitKey(0);
}