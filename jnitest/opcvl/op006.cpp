//
// Created by wenyz on 2018/12/29.
// �˲���Ϊ���� һ��Ϊ�����˲� һ��Ϊ�������˲�

#include "op006.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui.hpp>

using namespace cv;
using namespace std;

int main(){
    // �����˲��������˲�����ֵ�˲�����˹�˲�

    // �����˲�
    Mat img = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img);
    Mat out;
    boxFilter(img, out, -1, Size(5, 5));//-1ָԭͼ���
    imshow("�����˲�", out);

    // ��ֵ�˲� ��ֵ�˲���ȱ����ǲ��ܺܺõر���ϸ�ڣ���ͼ��ȥ���ͬʱҲ�ƻ���ͼ��Ķ�ϸ�ڲ��֣��Ӷ�ʹͼ����ģ�������ܺܺõ�ȥ����㡣
    Mat img2 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out2;
    blur(img2, out2,Size(5, 5));//-1ָԭͼ���
    imshow("��ֵ�˲�", out2);

    // ��˹�˲�������������˹�������㷺Ӧ����ͼ����ļ�����̡�
    Mat img3 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out3;
    GaussianBlur(img3, out3, Size(3, 3), 0, 0);
    imshow("��˹�˲�", out3);


    // �������˲�����ֵ�˲���˫���˲�
//    ��ֵ�˲�
//    ����˼����������ص������Ҷȵ���ֵ����������ص�ĻҶ�ֵ���÷�����ȥ����������������������ͬʱ���ܱ���ͼ���ϸ�ڣ�������ֱ�Եģ�����������
//    ��ֵ�˲�����ֵ�˲���˼�뿴���������ƣ�ֻ��һ��ȡƽ��ֵ��һ��ȡ��λ������
//    ����˵˵��ֵ�˲����ֵ�˲��ıȽϣ���ֵ�˲��������ɷֻᱻ���뵽ƽ�����㣬����������ܵ�������Ӱ��ġ�������ֵ�˲��У����������ɷֺ���ѡ�ϣ����Ի�����Ӱ���������Ȼ�õ�����Ҳ��Ҫ����һ����۵ģ���ֵ�˲����ѵ�ʱ���Ǿ�ֵ�˲���5�����ϡ�
//    ��ֵ�˲�һ����������ľ���ˡ�
//    ��ֵ�˲���һЩϸ�ڶࣨ�ر���ϸ���ⶥ�ģ���ͼ��̫�ʺϡ�
    Mat img4 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out4;
    medianBlur(img4, out4, 7);//������������ʾ�׾������Գߴ磬����ֵ�����Ǵ���1������
    imshow("��ֵ�˲�", out4);

//    ˫���˲�
//            ˫���˲�������ص��������Ե����
    // Ч��ͼ������˫���˲��������˲����������ġ�
    Mat img5 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    Mat out5;
    bilateralFilter(img5, out5, 25, 25 * 2, 25 / 2);
    imshow("˫���˲�", out5);


//    ��ʴ�����������������̬ѧ���㡣
//    ��ʴ����������԰�ɫ���֣��������֣����Եġ�
//    ���;��Ƕ�ͼ��������ֽ��С��������š���Ч��ͼӵ�б�ԭͼ����ĸ������򣻸�ʴ��ԭͼ�еĸ������򱻲�ʳ��Ч��ͼӵ�б�ԭͼ��С�ĸ�������
//    ����
//    ���;�����ֲ����ֵ�Ĳ�������ͼ��ֱ�ۿ��������ǽ�ͼ��������ַŴ󣬺ڰ�������С

    Mat img6 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    namedWindow("ԭʼͼ", WINDOW_NORMAL);
    imshow("ԭʼͼ", img6);
    Mat out6;
    //��ȡ�Զ����
    Mat element = getStructuringElement(MORPH_RECT, Size(15, 15)); //��һ������MORPH_RECT��ʾ���εľ���ˣ���Ȼ������ѡ����Բ�εġ������͵�
    //���Ͳ���
    dilate(img6, out6, element);
    namedWindow("���Ͳ���", WINDOW_NORMAL);
    imshow("���Ͳ���", out6);

    // ���Կ�����ͼ��ԭ�������Ĳ��ֱ��Ŵ��ˣ��ڰ��Ĳ��ֱ���С��
    Mat img7 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    namedWindow("ԭʼͼ", WINDOW_NORMAL);
    imshow("ԭʼͼ", img7);
    Mat out7;
    //��ȡ�Զ����
    Mat element2 = getStructuringElement(MORPH_RECT, Size(15, 15)); //��һ������MORPH_RECT��ʾ���εľ���ˣ���Ȼ������ѡ����Բ�εġ������͵�
    //��ʴ����
    erode(img7, out7, element2);
    namedWindow("��ʴ����", WINDOW_NORMAL);
    imshow("��ʴ����", out7);


//    �����㣺�ȸ�ʴ�����ͣ���������С����
//    �����㣺�������ٸ�ʴ�������ų�С�ͺڶ�
//    ��̬ѧ�ݣ���������ͼ�븩��ͼ֮����ڱ�������ı�Ե������
//    ��ñ��ԭͼ���뿪����ͼ֮����ڷ�����ڽ�����һЩ�İ߿顣
//    ��ñ����������ԭͼ��֮����ڷ�����ڽ��㰵һЩ�İ߿顣
//    opencv����һ���ܺõĺ���getStructuringElement������ֻҪ�������������Ӧ�Ĵ���������Ϳ��Խ�����Ӧ�Ĳ����ˣ�ʹ�������ǳ����㡣

    Mat img8 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    namedWindow("ԭʼͼ", WINDOW_NORMAL);
    imshow("ԭʼͼ", img8);
    Mat out8;
    //��ȡ�Զ����
    Mat element3 = getStructuringElement(MORPH_RECT, Size(15, 15)); //��һ������MORPH_RECT��ʾ���εľ���ˣ���Ȼ������ѡ����Բ�εġ������͵�

    //�߼���̬ѧ����������������Ϳ����ˣ�����Ҫѡ�����ֲ��������޸ĵ����������Ϳ����ˡ�������ʾ������̬ѧ�ݶȴ���
    morphologyEx(img8, out8, MORPH_GRADIENT, element3);
    namedWindow("��̬ѧ�������", WINDOW_NORMAL);
    imshow("��̬ѧ�������", out8);


    // ��ͼ��������ŵ���򵥷�����Ȼ�ǵ���resize��������
    Mat img9 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img9);
    Mat dst = Mat::zeros(512, 512, CV_8UC3); //��Ҫת��Ϊ512*512��С��
    resize(img9, dst, dst.size());
    imshow("�ߴ����֮��", dst);

    Mat img10 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img9);
    Mat dst2;
    resize(img10, dst2, Size(),0.5,0.5);//�ҳ�����Ϊԭ����0.5��
    imshow("�ߴ����֮��", dst2);

    //ͼ�������
//    �������͵Ľ�������
//    ��˹�������������²�������Ҫ��ͼ���������
//    ������˹�������������ؽ�ͼ��Ҳ����Ԥ��в�ҵ�����ǣ���ΪСͼ��Ŵ󣬱������һЩ����ֵ������Щ����ֵ��ʲô�ź����أ��Ǿ͵ý��и�����Χ���ؽ���Ԥ�⣩����ͼ��������̶ȵĻ�ԭ������һ��Сͼ���ؽ�Ϊһ����ͼ��
//    ͼ���������������Ƶ���ֵ����ʣ��ϲ������²���������˵˵��������
//    �ϲ���������ͼƬ�Ŵ���ν������Ǳ�󣩣�ʹ��PryUp����
//    �²���������ͼƬ��С����ν������Ǳ�С����ʹ��PryDown����
//    �²��������裺
//    ��ͼ����и�˹�ں˾��
//            ������ż���к���ȥ��
//    �²�������ͼ��ѹ�����ᶪʧͼ����Ϣ��
//    �ϲ������裺
//    ��ͼ����ÿ������Ŵ�Ϊԭ�����������������к�����0��䣻
//    ʹ����ǰͬ�����ںˣ�����4����Ŵ���ͼ����������������صĽ���ֵ��
//    �ϡ��²���������һ�����ص����⣬�Ǿ���ͼ���ģ���ˣ���Ϊ���ŵĹ����з�������Ϣ��ʧ�����⡣Ҫ���������⣬�͵ÿ�������˹�������ˡ�

    Mat img11 = imread("D:\\clangage\\jnitest\\opcvl\\lena.jpg");
    imshow("ԭʼͼ", img11);

    Mat dst3,dst4;
    pyrUp(img11, dst3, Size(img11.cols*2, img11.rows*2)); //�Ŵ�һ��
    pyrDown(img11, dst4, Size(img11.cols * 0.5, img11.rows * 0.5)); //��СΪԭ����һ��
    imshow("�ߴ�Ŵ�֮��", dst3);
    imshow("�ߴ���С֮��", dst4);

    waitKey(0);
}