package com.wzy.study.algorithm.model;

import java.util.Random;

/**
 * 有标识的可排序类
 * @author 王志英
 */
public class StabilityObject implements Comparable<StabilityObject> {
    /**
     * 数组根据此字段进行排序
     */
    private int id;
    /**
     * 标识元素原始插入位置
     * 如果排序后id相同的元素order依然是从小到大说明排序是稳定的
     */
    private int order;

    public StabilityObject(int id, int order) {
        this.id = id;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(StabilityObject target) {
        if (this.id < target.id) {
            return -1;
        } else if (this.id > target.id) {
            return 1;
        }
        return 0;
    }
}
