// operator_system.cpp: �������̨Ӧ�ó������ڵ㡣
//

#include<iostream>
#include <mutex>
#include <condition_variable>
#include <windows.h>
#include <thread>

using namespace std;

static const int buffer_size = 10000; // �����С
static const int item_total = 100; //�ܹ�Ҫ���� item_total��item

// ����ṹ��, ʹ��ѭ�����е�������
struct Buffer
{
    int buffer[buffer_size];
    size_t read_position; // ��ǰ��λ��
    size_t write_position; // ��ǰдλ��
    mutex mtx; // ��д����
    //��������
    condition_variable not_full;
    condition_variable not_empty;
}buffer_res;

typedef struct Buffer Buffer;

void porduce_item(Buffer *b, int item)
{
    unique_lock<mutex> lock(b->mtx);//���û�����

    while(((b->write_position + 1) % buffer_size) == b->read_position) {
        //��ǰ�����Ѿ�����
        cout << "buffer is full now, producer is wating....." << endl;
        (b->not_full).wait(lock); // �ȴ������full
    }
    // �򻺴������item
    (b->buffer)[b->write_position] = item;
    (b->write_position)++;

    // ���������һ��, дλ����λ0
    if (b->write_position == buffer_size)
        b->write_position = 0;

    (b->not_empty).notify_all();
    lock.unlock();
}

int consume_item(Buffer *b)
{
    int data;
    unique_lock <mutex> lock(b->mtx);
    while (b->write_position == b->read_position)
    {   // ��ǰbuffer Ϊ��
        cout << "buffer is empty , consumer is waiting....." << endl;
        (b->not_empty).wait(lock);
    }

    data = (b->buffer)[b->read_position];
    (b->read_position)++;

    if (b->read_position >= buffer_size)
        b->read_position = 0;

    (b->not_full).notify_all();
    lock.unlock();

    return data;
}

//����������
void producer() {
    int k = 0;
    while (1) {
//        for (int i = 1; i <= item_total; i++) {
//            cout << "prodece the " << i << "^th item ..." << endl;
            porduce_item(&buffer_res, ++k);
//        }
    }
}

//����������
void consumer()
{
    static int cnt = 0;
    while(1) {
        Sleep(1);
        int item = consume_item(&buffer_res);
        cout << "consume the " << item << "^th item" << endl;
//        if (++cnt == item_total)
//            break;
    }
}

//��ʼ�� buffer
void init_buffer(Buffer *b)
{
    b->write_position = 0;
    b->read_position = 0;
}

int main()
{
    init_buffer(&buffer_res);
    thread prodece(producer);
    thread consume(consumer);
    prodece.join();
    consume.join();
    getchar();
}
