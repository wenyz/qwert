# 生产环境调优经验总结
### 内存调优
#### 充分使用内存
1. 设置swap和RAM一样大；设置swapiness=0;
2. 设置overcommit_memory=2,overcommit_ratio=100,swapiness=0;
   这样设置可以使物理内存加swap内存，即翻倍，然后可以使系统
   优先使用RAM内存，不够再使用swap内存
