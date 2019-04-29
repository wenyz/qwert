//
// Created by wenyz on 2019/1/11.
//

#include "se001.h"

#include <opencv2/opencv.hpp>

#define  KKKK cout << "gggg"
#define QQQ "KKK"
#define GGG  __attribute__ ((visibility ("default")))

#include <queue>

#define aaa qqq

#ifndef KGC_H
#define KGC_H
using namespace std;
#endif

#define kkkkg 1

int QQQFx(int k) {
    cout << "fx" << endl;
    return k;
}

GGG int qog(int k) {
    cout << "gggg";
}

#if kkkkg >= 1

GGG int qog() {
    cout << "ffffff";
}

#endif

struct tuo_luo_yi {
    int x;
    int y;
    int z;
//    int a;
//    int b;
    string msg;
} kgc;


class Line {
public:
    void setLength(double len);

    double getLength(void);

    Line(int ggg);

    Line();   // 这是构造函数声明
    ~Line();  // 这是析构函数声明

private:
    double length;
};

Line::Line(int ggg) {
    length = ggg;
}

void Line::setLength(double len) {
    length = len;
}

double Line::getLength() {
    return length;
}

Line::~Line() {
    length = 0;
    cout << "~ is excuted" << endl;
}

#define offsetof(s, m) (size_t)&(((s*)0)->m)


struct BF1 {
    char f1:3;
    char f2:4;
    char f3:5;
};

#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace cv;
using namespace std;

struct user_datas {
    int threadhold;
    int grey;
    Mat image;
};


void on_trackbar(int threshold22, void *user_data) {
    Mat local;

    cout << "1111";
    user_datas k = *(user_datas *) user_data;

    threshold(k.image, local, threshold22, 255, CV_THRESH_BINARY_INV);
    imshow("11111", local);
}

extern "C" void testkge(int p) {
    cout << "11111" << endl;
    printf("1122%d", p);
}


void testkge(int p, int b) {
    cout << "22222" << endl;
    printf("1122%d", p);
}


int main() {

//    Mat image = imread("D:\\clangage\\jnitest\\opcvl\\1.jpg", CV_LOAD_IMAGE_GRAYSCALE); //注意了，必须是载入灰度图
//
//    if (image.empty()) {
//        cout << "read image failure" << endl;
//        return -1;
//    }
//
//    Mat global;
//    threshold(image, global, 100, 255, CV_THRESH_BINARY_INV);
//
//
//    int threshold = 0;
//    char barName[50];
//    sprintf(barName, "yuzhi %d", threshold);
//
//    namedWindow("11111",1);
//    struct user_datas user_data;
//
//    printf("user data before %d", sizeof(user_data));
//
//    user_data.image = image;
//    printf("user data after %d", sizeof(user_data));
//    createTrackbar("pos", "11111", &threshold, 255, on_trackbar, &user_data);
//
//    on_trackbar(threshold,  &user_data);
//
//    waitKey(0);

//    testkge(3);
//    testkge(4,5);

//    char *a = "abcdefg";
//    int *k = (int*)a-1;
//    char *b = (char*)k;
//
//
//
//    printf("%s",b);
//
//    user_datas *a;
//    printf("aaa%p",a);
//    user_datas *b = a+1;
//    printf("bbb%p",b);

//    char cc[10] = "123123";
//    char ff[1024*1024];
//
//    for(int i=0;i<1024*1024;i++){
//        printf("%c",ff[i]);
//    }

    struct user_datas *aaa;

    printf("size is %d",sizeof(long long));
    printf("size * is %d",sizeof(long));
    printf("size * is %d",sizeof(int));

}




