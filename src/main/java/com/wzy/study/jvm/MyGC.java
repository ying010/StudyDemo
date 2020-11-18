package com.wzy.study.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 王志英
 */
public class MyGC {
    private int[] a = new int[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        List<MyGC> gcs = new ArrayList<>();
        while (true) {
            MyGC gc = new MyGC();
            gcs.add(gc);
            if (new Random().nextInt(10) > 3) gcs.remove(0);
            Thread.sleep(30);
        }
    }
}
