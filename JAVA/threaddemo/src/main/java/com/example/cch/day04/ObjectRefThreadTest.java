package com.example.cch.day04;

import java.util.ArrayList;
import java.util.List;

class ThreadUnsafe {
    public void method(int v) {
        List<Integer> list = new ArrayList<>();
        while (v > 0) {
            add(list);
            remove(list);
            v--;
        }
    }

    public void add(List<Integer> list) {
        list.add(1);    
    }

    public void remove(List<Integer> list) {
        list.remove(0);
    }
}

class Sub extends ThreadUnsafe {
    @Override
    public void remove(List<Integer> list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}
public class ObjectRefThreadTest {
    public static void main(String[] args) {
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                threadUnsafe.method(600);
            }, "T"+(i+1)).start();
        }
    }
}
