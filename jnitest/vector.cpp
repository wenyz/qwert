//
// Created by wenyz on 2018/12/20.
//

#include <iostream>
#include <vector>
#include <string>

using namespace std;

template<typename T>
void showvector(vector<T> v) {

    for (typename::vector<T>::iterator it = v.begin(); it != v.end(); it++) {
        cout << *it;
    }
    cout << endl;
}

int main() {

    vector<string> v6 = {"hi", "my", "name", "is", "wen"};
    v6.resize(3);
    showvector(v6);

    vector<int> v5 = {1, 2, 3, 4, 5};
    cout << v5.front() << endl;
    cout << v5.back() << endl;

    showvector(v5);
    v5.pop_back();
    showvector(v5);
    v5.push_back(6);
    showvector(v5);

    v5.insert(v5.begin() + 3, 9);
    showvector(v5);

    v5.erase(v5.begin() + 3);
    showvector(v5);

    v5.insert(v5.begin() + 4, 7, 8);
    showvector(v5);

    v5.clear();

    cout << "111";
    return 0;
}