//
// Created by wenyz on 2018/12/20.
//

#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s1;//初始化字符串，空字符串
    string s2 = s1; //拷贝初始化，深拷贝字符串
    string s3 = "I am Yasuo"; //直接初始化，s3存了字符串
    string s4(10, 'a'); //s4存的字符串是aaaaaaaaaa
    string s5(s4); //拷贝初始化，深拷贝字符串
    string s6("I am Ali"); //直接初始化
    string s7 = string(6, 'c'); //拷贝初始化，cccccc

    //string的各种操作
    string s8 = s3 + s6;//将两个字符串合并成一个
    s3 = s6;//用一个字符串来替代另一个字符串的对用元素

    cin >> s1;

    cout << s1 << endl;
    cout << s2 << endl;
    cout << s3 << endl;
    cout << s4 << endl;
    cout << s5 << endl;
    cout << s6 << endl;
    cout << s7 << endl;
    cout << s8 << endl;
    cout << "s7 size = " << s7.size() << endl; //字符串长度，不包括结束符
    cout << (s2.empty() ? "This string is empty" : "This string is not empty") << endl;;

    system("pause");
    return 0;
}