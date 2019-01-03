//
// Created by wenyz on 2018/12/29.
//

#include "op007.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

//��Ե����һ�㲽�裺
//
//�˲�������������
//        ��ǿ����ʹ�߽�������������
//        ��⡪��ѡ����Ե��
int main(){


//    Canny�㷨
//    Canny��Ե����㷨���ܶ����Ƴ�Ϊ����������ı�Ե����㷨���������ǵ�һ���ͽ�������
//    opencv���ṩ��Canny������
    Mat img = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img);
    Mat DstPic, edge, grayImage;

    //������srcͬ���ͺ�ͬ��С�ľ���
   // DstPic.create(img.size(), img.type());
    //��ԭʼͼת��Ϊ�Ҷ�ͼ
    cvtColor(img, grayImage, COLOR_BGR2GRAY);
    //��ʹ��3*3�ں�������
    blur(grayImage, edge, Size(3, 3));
    //����canny����
    Canny(edge, edge, 3, 9, 3);
    imshow("��Ե��ȡЧ��", edge);


//Sobel�㷨
    //ͨ����ͼ���Կ�����sobel��������ȡ������ûcnany����ôϸ�£�ֻ�ǰ�һЩ���������ı�Ե��ȡ�����ˣ�������������һ�㡣

    Mat img2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat grad_x, grad_y;
    Mat abs_grad_x, abs_grad_y, dst;
    //��x�����ݶ�
    Sobel(img2, grad_x, CV_16S, 1, 0, 3, 1, 1,BORDER_DEFAULT);
    convertScaleAbs(grad_x, abs_grad_x);
    imshow("x����soble", abs_grad_x);
    //��y�����ݶ�
    Sobel(img2, grad_y,CV_16S,0, 1,3, 1, 1, BORDER_DEFAULT);
    convertScaleAbs(grad_y,abs_grad_y);
    imshow("y��soble", abs_grad_y);
    //�ϲ��ݶ�
    addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, dst);
    imshow("���巽��soble", dst);


    // Laplacian�㷨
    Mat img3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img3);
    Mat gray, dst2,abs_dst;
    //��˹�˲���������
    GaussianBlur(img3, img3, Size(3, 3), 0, 0, BORDER_DEFAULT);
    //ת��Ϊ�Ҷ�ͼ
    cvtColor(img3, gray, COLOR_RGB2GRAY);
    //ʹ��Laplace����
    //������������Ŀ��ͼ����ȣ����ĸ��������˲����׾��ߴ磻������������������ӣ���������������ʾ�������Ŀ��ͼ
    Laplacian(gray, dst2, CV_16S, 3, 1, 0, BORDER_DEFAULT);
    //�������ֵ���������תΪ8λ
    convertScaleAbs(dst2, abs_dst);

    imshow("laplaceЧ��ͼ", abs_dst);

    waitKey(0);

}