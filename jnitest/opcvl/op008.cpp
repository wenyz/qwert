//
// Created by wenyz on 2019/1/2.
//
// 霍夫变换，它是图像中识别各种几何形状的基本算法之一
// OpenCV中支持三种霍夫线变换，分别是标准霍夫线变换、多尺度霍夫线变换、累计概率霍夫线变换
// HoughLines来调用标准霍夫线变换和多尺度霍夫线变换
// HoughLinesP函数用于调用累积概率霍夫线变换
#include "op008.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;
using namespace cv;

int main(){


    Mat srcImage = imread("D:\\clangage\\jnitest\\opcvl\\2.jpg");
    imshow("Src Pic", srcImage);

    Mat midImage, dstImage;
    //边缘检测
    Canny(srcImage, midImage, 50, 200, 3);
    //灰度化
    cvtColor(midImage, dstImage, CV_GRAY2BGR);
    // 定义矢量结构存放检测出来的直线
    vector<Vec2f> lines;
    //通过这个函数，我们就可以得到检测出来的直线集合了
    HoughLines(midImage, lines, 1, CV_PI / 180, 150, 0, 0);
    //这里注意第五个参数，表示阈值，阈值越大，表明检测的越精准，速度越快，得到的直线越少（得到的直线都是很有把握的直线）
    //这里得到的lines是包含rho和theta的，而不包括直线上的点，所以下面需要根据得到的rho和theta来建立一条直线

    //依次画出每条线段
    for (size_t i = 0; i < lines.size(); i++)
    {
        float rho = lines[i][0]; //就是圆的半径r
        float theta = lines[i][1]; //就是直线的角度
        Point pt1, pt2;
        double a = cos(theta), b = sin(theta);
        double x0 = a*rho, y0 = b*rho;
        pt1.x = cvRound(x0 + 1000 * (-b));
        pt1.y = cvRound(y0 + 1000*(a));
        pt2.x = cvRound(x0 - 1000*(-b));
        pt2.y = cvRound(y0 - 1000 * (a));

        line(dstImage, pt1, pt2, Scalar(55, 100, 195), 1, LINE_AA); //Scalar函数用于调节线段颜色，就是你想检测到的线段显示的是什么颜色
    }
    imshow("边缘检测后的图1", midImage);
    imshow("最终效果图1", dstImage);

    // HoughLinesP用法

    Mat srcImage2 = imread("D:\\clangage\\jnitest\\opcvl\\3.jpg");
    imshow("Src Pic", srcImage2);

    Mat midImage2, dstImage2;

    Canny(srcImage2, midImage2, 50, 200, 3);
    cvtColor(midImage2, dstImage2, CV_GRAY2BGR);

    vector<Vec4i> lines2;
    //与HoughLines不同的是，HoughLinesP得到lines的是含有直线上点的坐标的，所以下面进行划线时就不再需要自己求出两个点来确定唯一的直线了
    HoughLinesP(midImage2, lines2, 1, CV_PI / 180, 80, 50, 10);//注意第五个参数，为阈值

    //依次画出每条线段
    for (size_t i = 0; i < lines2.size(); i++)
    {
        Vec4i l = lines2[i];

        line(dstImage2, Point(l[0], l[1]), Point(l[2], l[3]), Scalar(186, 88, 255), 1, LINE_AA); //Scalar函数用于调节线段颜色
    }
    imshow("边缘检测后的图2", midImage2);
    imshow("最终效果图2", dstImage2);



//    重映射
//    重映射就是把一幅图像中某个位置的像素放置到另一个图片中指定位置的过程。

    Mat srcImage3 = imread("D:\\clangage\\jnitest\\opcvl\\2.jpg");

    if (!srcImage3.data)
    {
        cout << "找不到这张图片！" << endl;
        return -1;
    }

    imshow("Src Pic", srcImage3);

    Mat dstImage3, map_x, map_y;
    dstImage3.create(srcImage3.size(), srcImage3.type());//创建和原图一样的效果图
    map_x.create(srcImage3.size(), CV_32FC1);
    map_y.create(srcImage3.size(), CV_32FC1);

    //遍历每一个像素点，改变map_x & map_y的值,实现翻转180度
    for (int j = 0; j < srcImage3.rows; j++)
    {
        for (int i = 0; i < srcImage3.cols; i++)
        {
            // 水平180反转 x坐标集合不变 y坐标集合倒转
            map_x.at<float>(j, i) = static_cast<float>(i);
            map_y.at<float>(j, i) = static_cast<float>(srcImage3.rows - j);
            // 这里的map x map y 是 一个坐标的集合
        }
    }

    //进行重映射操作
    remap(srcImage3, dstImage3, map_x, map_y, INTER_LINEAR, BORDER_CONSTANT, Scalar(0, 0, 0));
    imshow("重映射效果图", dstImage3);

//    仿射变换
//    仿射变换指的是一个向量空间进行一次线性变换并接上一个平移，变换为另一个向量空间的过程。
//    图像进行仿射变换后，有以下几个特点：
//    二维图形之间的相对位置关系保持不变，平行线依旧是平行线，且直线上的点的位置顺序保持不变。
//    一个任意的仿射变换都可以表示为乘以一个矩阵（线性变换）接着再加上一个向量（平移）的形式
    // T = A[x,y]+B


//    三种常见形式：
//    旋转，rotation（线性变换）
//    平移，translation（向量加）
//    缩放，scale（线性变换）
//    仿射变换本质是一个2* 3的矩阵M乘上原图的每个坐标，得到目标图的对应点坐标。2*3矩阵M中的2表示目标点坐标的x与y，3中的第三维是平移分量。因此需要做的就是找到矩阵M，OpenCV提供 getAffineTransform 求出仿射变换， getRotationMatrix2D 来获得旋转矩阵。
//    这里简单说说仿射变换是怎么做到的。
//    现在有两幅图像（如下图），图像二是图像一经过放射变化得来的。那问题来了，我们怎么从这两个图像信息里挖掘出两图之间的映射关系？
//    很简单，只要在图像一种拿出三个点（1,2,3），图像二也拿出对应的三个点（1,2,3），就可以求出两图间的映射关系！
//    OpenCV通过两个函数的组合使用来实现仿射变换：
//    使用warpAffine来实现简单重映射
//            使用getRotationMatrix2D来获得旋转矩阵

    Mat src = imread("D:\\clangage\\jnitest\\opcvl\\3.jpg");
    Mat dst_warp, dst_warpRotateScale;
    Point2f srcPoints[3];//原图中的三点
    Point2f dstPoints[3];//目标图中的三点

    //第一种仿射变换的调用方式：三点法
    //三个点对的值,上面也说了，只要知道你想要变换后图的三个点的坐标，就可以实现仿射变换
    srcPoints[0] = Point2f(0, 0);
    srcPoints[1] = Point2f(0, src.rows - 1);
    srcPoints[2] = Point2f(src.cols - 1, 0);
    //映射后的三个坐标值
    dstPoints[0] = Point2f(0, src.rows*0.3);
    dstPoints[1] = Point2f(src.cols*0.25, src.rows*0.75);
    dstPoints[2] = Point2f(src.cols*0.75, src.rows*0.25);

    Mat M1 = getAffineTransform(srcPoints, dstPoints);//由三个点对计算变换矩阵
    warpAffine(src, dst_warp, M1, src.size());//仿射变换


    //第二种仿射变换的调用方式：直接指定角度和比例
    //旋转加缩放
    Point2f center(src.cols / 2, src.rows / 2);//旋转中心
    double angle = 45;//逆时针旋转45度
    double scale = 0.5;//缩放比例

    Mat M2 = getRotationMatrix2D(center, angle, scale);//计算旋转加缩放的变换矩阵
    warpAffine(dst_warp, dst_warpRotateScale, M2, src.size());//仿射变换

    imshow("原始图", src);
    imshow("仿射变换1", dst_warp);
    imshow("仿射变换2", dst_warpRotateScale);
    waitKey(0);

    return 0;
}