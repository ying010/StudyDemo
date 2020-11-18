package com.wzy.study.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出测试
 * VM Args -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./my_out/analyzer
 * @author 王志英
 */
public class MyHeapOutOfMemory {
    static class OOMObject {

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
