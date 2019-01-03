//
// Created by wenyz on 2019/1/2.
//
// ����任������ͼ����ʶ����ּ�����״�Ļ����㷨֮һ
// OpenCV��֧�����ֻ����߱任���ֱ��Ǳ�׼�����߱任����߶Ȼ����߱任���ۼƸ��ʻ����߱任
// HoughLines�����ñ�׼�����߱任�Ͷ�߶Ȼ����߱任
// HoughLinesP�������ڵ����ۻ����ʻ����߱任
#include "op008.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;
using namespace cv;

int main(){


    Mat srcImage = imread("D:\\clangage\\jnitest\\opcvl\\2.jpg");
    imshow("Src Pic", srcImage);

    Mat midImage, dstImage;
    //��Ե���
    Canny(srcImage, midImage, 50, 200, 3);
    //�ҶȻ�
    cvtColor(midImage, dstImage, CV_GRAY2BGR);
    // ����ʸ���ṹ��ż�������ֱ��
    vector<Vec2f> lines;
    //ͨ��������������ǾͿ��Եõ���������ֱ�߼�����
    HoughLines(midImage, lines, 1, CV_PI / 180, 150, 0, 0);
    //����ע��������������ʾ��ֵ����ֵԽ�󣬱�������Խ��׼���ٶ�Խ�죬�õ���ֱ��Խ�٣��õ���ֱ�߶��Ǻ��а��յ�ֱ�ߣ�
    //����õ���lines�ǰ���rho��theta�ģ���������ֱ���ϵĵ㣬����������Ҫ���ݵõ���rho��theta������һ��ֱ��

    //���λ���ÿ���߶�
    for (size_t i = 0; i < lines.size(); i++)
    {
        float rho = lines[i][0]; //����Բ�İ뾶r
        float theta = lines[i][1]; //����ֱ�ߵĽǶ�
        Point pt1, pt2;
        double a = cos(theta), b = sin(theta);
        double x0 = a*rho, y0 = b*rho;
        pt1.x = cvRound(x0 + 1000 * (-b));
        pt1.y = cvRound(y0 + 1000*(a));
        pt2.x = cvRound(x0 - 1000*(-b));
        pt2.y = cvRound(y0 - 1000 * (a));

        line(dstImage, pt1, pt2, Scalar(55, 100, 195), 1, LINE_AA); //Scalar�������ڵ����߶���ɫ�����������⵽���߶���ʾ����ʲô��ɫ
    }
    imshow("��Ե�����ͼ1", midImage);
    imshow("����Ч��ͼ1", dstImage);

    // HoughLinesP�÷�

    Mat srcImage2 = imread("D:\\clangage\\jnitest\\opcvl\\3.jpg");
    imshow("Src Pic", srcImage2);

    Mat midImage2, dstImage2;

    Canny(srcImage2, midImage2, 50, 200, 3);
    cvtColor(midImage2, dstImage2, CV_GRAY2BGR);

    vector<Vec4i> lines2;
    //��HoughLines��ͬ���ǣ�HoughLinesP�õ�lines���Ǻ���ֱ���ϵ������ģ�����������л���ʱ�Ͳ�����Ҫ�Լ������������ȷ��Ψһ��ֱ����
    HoughLinesP(midImage2, lines2, 1, CV_PI / 180, 80, 50, 10);//ע������������Ϊ��ֵ

    //���λ���ÿ���߶�
    for (size_t i = 0; i < lines2.size(); i++)
    {
        Vec4i l = lines2[i];

        line(dstImage2, Point(l[0], l[1]), Point(l[2], l[3]), Scalar(186, 88, 255), 1, LINE_AA); //Scalar�������ڵ����߶���ɫ
    }
    imshow("��Ե�����ͼ2", midImage2);
    imshow("����Ч��ͼ2", dstImage2);



//    ��ӳ��
//    ��ӳ����ǰ�һ��ͼ����ĳ��λ�õ����ط��õ���һ��ͼƬ��ָ��λ�õĹ��̡�

    Mat srcImage3 = imread("D:\\clangage\\jnitest\\opcvl\\2.jpg");

    if (!srcImage3.data)
    {
        cout << "�Ҳ�������ͼƬ��" << endl;
        return -1;
    }

    imshow("Src Pic", srcImage3);

    Mat dstImage3, map_x, map_y;
    dstImage3.create(srcImage3.size(), srcImage3.type());//������ԭͼһ����Ч��ͼ
    map_x.create(srcImage3.size(), CV_32FC1);
    map_y.create(srcImage3.size(), CV_32FC1);

    //����ÿһ�����ص㣬�ı�map_x & map_y��ֵ,ʵ�ַ�ת180��
    for (int j = 0; j < srcImage3.rows; j++)
    {
        for (int i = 0; i < srcImage3.cols; i++)
        {
            // ˮƽ180��ת x���꼯�ϲ��� y���꼯�ϵ�ת
            map_x.at<float>(j, i) = static_cast<float>(i);
            map_y.at<float>(j, i) = static_cast<float>(srcImage3.rows - j);
            // �����map x map y �� һ������ļ���
        }
    }

    //������ӳ�����
    remap(srcImage3, dstImage3, map_x, map_y, INTER_LINEAR, BORDER_CONSTANT, Scalar(0, 0, 0));
    imshow("��ӳ��Ч��ͼ", dstImage3);

//    ����任
//    ����任ָ����һ�������ռ����һ�����Ա任������һ��ƽ�ƣ��任Ϊ��һ�������ռ�Ĺ��̡�
//    ͼ����з���任�������¼����ص㣺
//    ��άͼ��֮������λ�ù�ϵ���ֲ��䣬ƽ����������ƽ���ߣ���ֱ���ϵĵ��λ��˳�򱣳ֲ��䡣
//    һ������ķ���任�����Ա�ʾΪ����һ���������Ա任�������ټ���һ��������ƽ�ƣ�����ʽ
    // T = A[x,y]+B


//    ���ֳ�����ʽ��
//    ��ת��rotation�����Ա任��
//    ƽ�ƣ�translation�������ӣ�
//    ���ţ�scale�����Ա任��
//    ����任������һ��2* 3�ľ���M����ԭͼ��ÿ�����꣬�õ�Ŀ��ͼ�Ķ�Ӧ�����ꡣ2*3����M�е�2��ʾĿ��������x��y��3�еĵ���ά��ƽ�Ʒ����������Ҫ���ľ����ҵ�����M��OpenCV�ṩ getAffineTransform �������任�� getRotationMatrix2D �������ת����
//    �����˵˵����任����ô�����ġ�
//    ����������ͼ������ͼ����ͼ�����ͼ��һ��������仯�����ġ����������ˣ�������ô��������ͼ����Ϣ���ھ����ͼ֮���ӳ���ϵ��
//    �ܼ򵥣�ֻҪ��ͼ��һ���ó������㣨1,2,3����ͼ���Ҳ�ó���Ӧ�������㣨1,2,3�����Ϳ��������ͼ���ӳ���ϵ��
//    OpenCVͨ���������������ʹ����ʵ�ַ���任��
//    ʹ��warpAffine��ʵ�ּ���ӳ��
//            ʹ��getRotationMatrix2D�������ת����

    Mat src = imread("D:\\clangage\\jnitest\\opcvl\\3.jpg");
    Mat dst_warp, dst_warpRotateScale;
    Point2f srcPoints[3];//ԭͼ�е�����
    Point2f dstPoints[3];//Ŀ��ͼ�е�����

    //��һ�ַ���任�ĵ��÷�ʽ�����㷨
    //������Ե�ֵ,����Ҳ˵�ˣ�ֻҪ֪������Ҫ�任��ͼ������������꣬�Ϳ���ʵ�ַ���任
    srcPoints[0] = Point2f(0, 0);
    srcPoints[1] = Point2f(0, src.rows - 1);
    srcPoints[2] = Point2f(src.cols - 1, 0);
    //ӳ������������ֵ
    dstPoints[0] = Point2f(0, src.rows*0.3);
    dstPoints[1] = Point2f(src.cols*0.25, src.rows*0.75);
    dstPoints[2] = Point2f(src.cols*0.75, src.rows*0.25);

    Mat M1 = getAffineTransform(srcPoints, dstPoints);//��������Լ���任����
    warpAffine(src, dst_warp, M1, src.size());//����任


    //�ڶ��ַ���任�ĵ��÷�ʽ��ֱ��ָ���ǶȺͱ���
    //��ת������
    Point2f center(src.cols / 2, src.rows / 2);//��ת����
    double angle = 45;//��ʱ����ת45��
    double scale = 0.5;//���ű���

    Mat M2 = getRotationMatrix2D(center, angle, scale);//������ת�����ŵı任����
    warpAffine(dst_warp, dst_warpRotateScale, M2, src.size());//����任

    imshow("ԭʼͼ", src);
    imshow("����任1", dst_warp);
    imshow("����任2", dst_warpRotateScale);
    waitKey(0);

    return 0;
}