//
// Created by wenyz on 2018/12/29.
//

//最后简单总结一下图像处理中概念
//
//        离散傅里叶变换
//
//图像高频部分代表了图像的细节、纹理信息；低频代表了图像的轮廓信息。
//
//低通-》模糊
//
//        高通-》锐化
//
//        腐蚀和膨胀是针对白色部分（高亮部分）而言的。膨胀就是对图像高亮部分进行“领域扩张”，效果图拥有比原图更大的高亮区域；腐蚀是原图中的高亮区域被蚕食，效果图拥有比原图更小的高亮区域。
//
//开运算：先腐蚀再膨胀，用来消除小物体
//
//        闭运算：先膨胀再腐蚀，用于排除小型黑洞
//
//        形态学梯度：就是膨胀图与俯视图之差，用于保留物体的边缘轮廓。
//
//顶帽：原图像与开运算图之差，用于分离比邻近点亮一些的斑块。
//
//黑帽：闭运算与原图像之差，用于分离比邻近点暗一些的斑块。

#include "op005.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

//常见数据结构使用方法总结
int main()
{
    //Mat的用法
    Mat m1(2, 2, CV_8UC3, Scalar(0, 0, 255)); //其中的宏的解释：CV_[位数][带符号与否][类型前缀]C[通道数]
    cout << m1 << endl;

    //或者,利用IplImage指针来初始化,将IplImage*转化为Mat
    IplImage* image = cvLoadImage("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat mat = cvarrToMat(image);

    //Mat转IplImage:
    IplImage img = IplImage(mat);

    //或者
    Mat m2;
    m2.create(4, 5, CV_8UC(2));


    //点的表示:Point
    Point p;
    p.x = 1; //x坐标
    p.y = 1; //y坐标

    //或者
    Point p2(1, 1);

    //颜色的表示：Scalar(b,g,r);注意不是rgb，注意对应关系
    Scalar(1, 1, 1);

    //尺寸的表示:Size
    Size(5, 5);// 宽度和高度都是5

    //矩形的表示：Rect，成员变量有x,y,width,height
    Rect r1(0, 0, 100, 60);
    Rect r2(10, 10, 100, 60);
    Rect r3 = r1 | r2; //两个矩形求交集
    Rect r4 = r1 & r2; //两个矩形求并集

    //访问图片中像素的方式
    Mat img2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");

    for (int i = 0; i < img2.rows; i++)
    {
        uchar* data = img2.ptr<uchar>(i);  //获取第i行地址
        for (int j = 0; j < img2.cols; j++)
        {
            //printf("%d\n",data[j]);
        }
    }

    //直方图均衡化
    Mat img3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img3);
    cout<<"aaa";
    Mat dst;
    cvtColor(img3, img3, CV_RGB2GRAY);
    imshow("灰度图", img3);
    equalizeHist(img3, dst);

    imshow("直方图均衡化", dst);

    waitKey(0);

}
