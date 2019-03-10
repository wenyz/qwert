//
// Created by wenyz on 2019/1/21.
//

#include "op010.h"
#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace cv;
using namespace std;

struct user_datas {
    Mat img;
    string windowName = "blur threshold";
};

void onChangeBlur(int threshold, void *userdata);

int main() {

    Mat img = imread("D:\\clangage\\jnitest\\opcvl\\1.jpg", IMREAD_GRAYSCALE);

    if (img.data == NULL) {
        cout << "can not find the image" << endl;
        return 0;
    }
    int threshold = 100;


    struct user_datas userData;
    userData.img = img;
    namedWindow(userData.windowName,WINDOW_NORMAL);
    createTrackbar("threshold", userData.windowName, &threshold, 255, onChangeBlur, &userData);

    onChangeBlur(threshold, &userData);
    waitKey(0);

}

void onChangeBlur(int thre, void *userdata) {

    Mat dst;
    threshold(((user_datas *) userdata)->img, dst, thre, 255, THRESH_BINARY_INV);
    imshow(((user_datas *) userdata)->windowName, dst);

}
