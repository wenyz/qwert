package utils;

import java.util.concurrent.locks.ReentrantLock;

public class CircleBuf<E> {

    private int capaticy;
    private int firstPos;
    private int curSize;
    private Object[] data;
    private final ReentrantLock lock = new ReentrantLock(true);


    public CircleBuf(int capaticy) {
        capaticy = capaticy;
        firstPos = 0;
        curSize = 0;
    }

    public E get(int index) {

        lock.lock();
        try {
            if (index > -1 && index < capaticy) {
                int tmpPos = firstPos + index;
                return (E) data[tmpPos < capaticy ? tmpPos : tmpPos - capaticy];
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void push(E e) {

        lock.lock();
        try {

            if (curSize < capaticy) {
                data[curSize] = e;
            } else {
                data[firstPos] = e;
                if (++firstPos == capaticy) {
                    firstPos = 0;
                }
            }

            if (curSize != capaticy) {
                ++curSize;
            }

        } finally {
            lock.unlock();
        }
    }
}
