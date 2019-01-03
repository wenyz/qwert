//
// Created by wenyz on 2018/12/29.
//利用trakbar打开多个图片。

#include "op004.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace cv;
using namespace std;

int pic_num = 0;
int PIC_MAX_NUM = 10;

void on_track(int,void*)
{
    char file[100];
    sprintf(file, "D:\\clangage\\jnitest\\cut_img\\%d.jpg", pic_num);
    Mat img = imread(file);
    if (!img.data)
    {
        cout << "read fail" << endl;
        return;
    }
    imshow("show picture", img);
}

int main()
{

    namedWindow("是不是乱码");
    createTrackbar("no", "multi pictures", &pic_num, PIC_MAX_NUM, on_track);
    on_track(pic_num, NULL);
    while(1){
        if (waitKey(0) == 27) break;
    }


}