//
// Created by wenyz on 2018/12/29.
//

//�����ܽ�һ��ͼ�����и���
//
//        ��ɢ����Ҷ�任
//
//ͼ���Ƶ���ִ�����ͼ���ϸ�ڡ�������Ϣ����Ƶ������ͼ���������Ϣ��
//
//��ͨ-��ģ��
//
//        ��ͨ-����
//
//        ��ʴ����������԰�ɫ���֣��������֣����Եġ����;��Ƕ�ͼ��������ֽ��С��������š���Ч��ͼӵ�б�ԭͼ����ĸ������򣻸�ʴ��ԭͼ�еĸ������򱻲�ʳ��Ч��ͼӵ�б�ԭͼ��С�ĸ�������
//
//�����㣺�ȸ�ʴ�����ͣ���������С����
//
//        �����㣺�������ٸ�ʴ�������ų�С�ͺڶ�
//
//        ��̬ѧ�ݶȣ���������ͼ�븩��ͼ֮����ڱ�������ı�Ե������
//
//��ñ��ԭͼ���뿪����ͼ֮����ڷ�����ڽ�����һЩ�İ߿顣
//
//��ñ����������ԭͼ��֮����ڷ�����ڽ��㰵һЩ�İ߿顣

#include "op005.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

//�������ݽṹʹ�÷����ܽ�
int main()
{
    //Mat���÷�
    Mat m1(2, 2, CV_8UC3, Scalar(0, 0, 255)); //���еĺ�Ľ��ͣ�CV_[λ��][���������][����ǰ׺]C[ͨ����]
    cout << m1 << endl;

    //����,����IplImageָ������ʼ��,��IplImage*ת��ΪMat
    IplImage* image = cvLoadImage("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat mat = cvarrToMat(image);

    //MatתIplImage:
    IplImage img = IplImage(mat);

    //����
    Mat m2;
    m2.create(4, 5, CV_8UC(2));


    //��ı�ʾ:Point
    Point p;
    p.x = 1; //x����
    p.y = 1; //y����

    //����
    Point p2(1, 1);

    //��ɫ�ı�ʾ��Scalar(b,g,r);ע�ⲻ��rgb��ע���Ӧ��ϵ
    Scalar(1, 1, 1);

    //�ߴ�ı�ʾ:Size
    Size(5, 5);// ��Ⱥ͸߶ȶ���5

    //���εı�ʾ��Rect����Ա������x,y,width,height
    Rect r1(0, 0, 100, 60);
    Rect r2(10, 10, 100, 60);
    Rect r3 = r1 | r2; //���������󽻼�
    Rect r4 = r1 & r2; //���������󲢼�

    //����ͼƬ�����صķ�ʽ
    Mat img2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");

    for (int i = 0; i < img2.rows; i++)
    {
        uchar* data = img2.ptr<uchar>(i);  //��ȡ��i�е�ַ
        for (int j = 0; j < img2.cols; j++)
        {
            //printf("%d\n",data[j]);
        }
    }

    //ֱ��ͼ���⻯
    Mat img3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img3);
    cout<<"aaa";
    Mat dst;
    cvtColor(img3, img3, CV_RGB2GRAY);
    imshow("�Ҷ�ͼ", img3);
    equalizeHist(img3, dst);

    imshow("ֱ��ͼ���⻯", dst);

    waitKey(0);

}
