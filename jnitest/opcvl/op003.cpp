//
// Created by wenyz on 2018/12/28.
//

#include "op003.h"

#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

int main(){

//    腐蚀操作

    Mat SrcPic = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("Src Pic", SrcPic);
    Mat element = getStructuringElement(MORPH_RECT, Size(15, 15)); //getStructuringElement函数返回的是指定形状和尺寸的结构元素
    Mat DstPic;
    erode(SrcPic, DstPic, element); //腐蚀操作
    imshow("腐蚀效果图", DstPic);

    //使用 均值滤波实现图像模糊
    Mat SrcPic2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
//    imshow("Src Pic", SrcPic2);
    Mat DstPic2;
    blur(SrcPic2, DstPic2, Size(7, 7));
    imshow("均值模糊效果图", DstPic2);


//    canny边缘检测
//
//    思路：将原始图像转化为灰度图，用blur函数进行图像模糊以降噪，然后用canny函数进行边缘检测。
    Mat SrcPic3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
//    imshow("Src Pic", SrcPic3);
    Mat DstPic3, edge, grayImage;

    //创建与src同类型和同大小的矩阵
    DstPic.create(SrcPic3.size(), SrcPic3.type());

    //将原始图转化为灰度图
    cvtColor(SrcPic3, grayImage, COLOR_BGR2GRAY);

    //先使用3*3内核来降噪
    blur(grayImage, edge, Size(3, 3));

    //运行canny算子
    Canny(edge, edge, 3, 9, 3);

    imshow("边缘提取效果", edge);

    waitKey(0);
    return 0;

}
