//
// Created by wenyz on 2018/12/29.
//

#include "op007.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

//边缘检测的一般步骤：
//
//滤波——消除噪声
//        增强——使边界轮廓更加明显
//        检测——选出边缘点
int main(){


//    Canny算法
//    Canny边缘检测算法被很多人推崇为当今最优秀的边缘检测算法，所以我们第一个就介绍他。
//    opencv中提供了Canny函数。
    Mat img = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img);
    Mat DstPic, edge, grayImage;

    //创建与src同类型和同大小的矩阵
   // DstPic.create(img.size(), img.type());
    //将原始图转化为灰度图
    cvtColor(img, grayImage, COLOR_BGR2GRAY);
    //先使用3*3内核来降噪
    blur(grayImage, edge, Size(3, 3));
    //运行canny算子
    Canny(edge, edge, 3, 9, 3);
    imshow("边缘提取效果", edge);


//Sobel算法
    //通过下图可以看出，sobel的轮廓提取明显有没cnany的那么细致，只是把一些明显轮廓的边缘提取出来了，看起来会更舒服一点。

    Mat img2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat grad_x, grad_y;
    Mat abs_grad_x, abs_grad_y, dst;
    //求x方向梯度
    Sobel(img2, grad_x, CV_16S, 1, 0, 3, 1, 1,BORDER_DEFAULT);
    convertScaleAbs(grad_x, abs_grad_x);
    imshow("x方向soble", abs_grad_x);
    //求y方向梯度
    Sobel(img2, grad_y,CV_16S,0, 1,3, 1, 1, BORDER_DEFAULT);
    convertScaleAbs(grad_y,abs_grad_y);
    imshow("y向soble", abs_grad_y);
    //合并梯度
    addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, dst);
    imshow("整体方向soble", dst);


    // Laplacian算法
    Mat img3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("原始图", img3);
    Mat gray, dst2,abs_dst;
    //高斯滤波消除噪声
    GaussianBlur(img3, img3, Size(3, 3), 0, 0, BORDER_DEFAULT);
    //转换为灰度图
    cvtColor(img3, gray, COLOR_RGB2GRAY);
    //使用Laplace函数
    //第三个参数：目标图像深度；第四个参数：滤波器孔径尺寸；第五个参数：比例因子；第六个参数：表示结果存入目标图
    Laplacian(gray, dst2, CV_16S, 3, 1, 0, BORDER_DEFAULT);
    //计算绝对值，并将结果转为8位
    convertScaleAbs(dst2, abs_dst);

    imshow("laplace效果图", abs_dst);

    waitKey(0);

}