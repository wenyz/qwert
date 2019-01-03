//
// Created by wenyz on 2018/12/28.
//
//图像二值化操作
//
//        两种方法，全局固定阈值二值化和局部自适应阈值二值化
//
//        全局固定阈值很容易理解，就是对整幅图像都是用一个统一的阈值来进行二值化；
//
//局部自适应阈值则是根据像素的邻域块的像素值分布来确定该像素位置上的二值化阈值。
#include "op002.h"

#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

int main(){

    Mat image = imread("D:\\clangage\\jnitest\\opcvl\\1.jpg", CV_LOAD_IMAGE_GRAYSCALE); //注意了，必须是载入灰度图
    if (image.empty())
    {
        cout << "read image failure" << endl;
        return -1;
    }

    // 全局二值化
    int th = 100;
    Mat global;
    threshold(image, global, th, 255, CV_THRESH_BINARY_INV);

    // 局部二值化
    int blockSize = 25;
    int constValue = 10;
    Mat local;
    adaptiveThreshold(image, local, 255, CV_ADAPTIVE_THRESH_MEAN_C, CV_THRESH_BINARY_INV, blockSize, constValue);

    imshow("全局二值化", global);
    imshow("局部二值化", local);

    cout << "全局二值化" << endl;

    waitKey(0);
    return 0;


}