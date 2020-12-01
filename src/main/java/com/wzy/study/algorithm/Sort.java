package com.wzy.study.algorithm;

import java.util.Collections;
import java.util.List;

/**
 * 排序接口
 * @author 王志英
 */
public interface Sort {
    /**
     * 排序
     * @param target 待排序数组
     * @param <T> 数组中的元素必须是可比较的，即实现了Comparable接口并重写了{@link Collections#sort(List) Collections.sort}方法
     */
    <T extends Comparable<? super T>> void sort(T[] target);
}
