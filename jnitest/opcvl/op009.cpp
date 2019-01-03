//
// Created by wenyz on 2019/1/2.
//

#include "op009.h"
//#include <opencv2/core.hpp>
#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
//#include <iostream>
//#include <stdio.h>
//#include <opencv2/core/core.hpp>
using namespace std;
using namespace cv;



Mat g_srcImage, g_tempalteImage, g_resultImage;
int g_nMatchMethod;
int g_nMaxTrackbarNum = 5;


void on_matching(int, void*)
{
    Mat srcImage;
    g_srcImage.copyTo(srcImage);
    int resultImage_cols = g_srcImage.cols - g_tempalteImage.cols + 1;
    int resultImage_rows = g_srcImage.rows - g_tempalteImage.rows + 1;
    g_resultImage.create(resultImage_cols, resultImage_rows, CV_32FC1);

    matchTemplate(g_srcImage, g_tempalteImage, g_resultImage, g_nMatchMethod);
    normalize(g_resultImage, g_resultImage, 0, 2, NORM_MINMAX, -1, Mat());
    double minValue, maxValue;
    Point minLocation, maxLocation, matchLocation;
    minMaxLoc(g_resultImage, &minValue, &maxValue, &minLocation, &maxLocation);

    if (g_nMatchMethod == TM_SQDIFF || g_nMatchMethod == CV_TM_SQDIFF_NORMED)
    {
        matchLocation = minLocation;
    }
    else
    {
        matchLocation = maxLocation;
    }

    rectangle(srcImage, matchLocation, Point(matchLocation.x + g_tempalteImage.cols, matchLocation.y + g_tempalteImage.rows), Scalar(0, 0, 255), 2, 8, 0);
    rectangle(g_resultImage, matchLocation, Point(matchLocation.x + g_tempalteImage.cols, matchLocation.y + g_tempalteImage.rows), Scalar(0, 0, 255), 2, 8, 0);

    cout << "111";
    imshow("ԭʼͼ", srcImage);
    imshow("Ч��ͼ", g_resultImage);

}


int main(){
    Mat img, templ, result;
    img = imread("D:\\clangage\\jnitest\\opcvl\\nba.jpg");
    templ = imread("D:\\clangage\\jnitest\\opcvl\\yao.png");

    int result_cols = img.cols - templ.cols + 1;
    int result_rows = img.rows - templ.rows + 1;
    result.create(result_cols, result_rows, CV_32FC1);

    matchTemplate(img, templ, result, CV_TM_SQDIFF_NORMED);//��������ʹ�õ�ƥ���㷨�Ǳ�׼ƽ����ƥ�� method=CV_TM_SQDIFF_NORMED����ֵԽСƥ���Խ��
    normalize(result, result, 0, 1, NORM_MINMAX, -1, Mat());

    double minVal = -1;
    double maxVal;
    Point minLoc;
    Point maxLoc;
    Point matchLoc;
    cout << "ƥ��ȣ�" << minVal << endl;
    minMaxLoc(result, &minVal, &maxVal, &minLoc, &maxLoc, Mat());


    cout << "ƥ��ȣ�" << minVal << endl;

    matchLoc = minLoc;

    rectangle(img, matchLoc, Point(matchLoc.x + templ.cols, matchLoc.y + templ.rows), Scalar(0, 255, 0), 2, 8, 0);

    imshow("img", img);

//=================================================================
    g_srcImage = imread("D:\\clangage\\jnitest\\opcvl\\nba.jpg");
    if (!g_srcImage.data)
    {
        cout << "ԭʼͼ��ȡʧ��" << endl;
        return -1;
    }
    g_tempalteImage = imread("D:\\clangage\\jnitest\\opcvl\\yao.PNG");
    if (!g_tempalteImage.data)
    {
        cout << "ģ��ͼ��ȡʧ��" << endl;
        return -1;
    }

    namedWindow("ԭʼͼ", CV_WINDOW_AUTOSIZE);
    namedWindow("Ч��ͼ", CV_WINDOW_AUTOSIZE);
    createTrackbar("����", "ԭʼͼ", &g_nMatchMethod, g_nMaxTrackbarNum, on_matching);

    on_matching(0, NULL);


    waitKey(0);

    return 0;
}