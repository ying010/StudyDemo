package com.wzy.study.algorithm;

/**
 * 排序算法
 * @author 王志英
 */
public class MySortAlgorithm {

    /**
     * 选择排序
     */
    public static class Xuanze implements Sort {
        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            // 总共要经过 N-1 轮比较
            for (int i = 0; i < target.length - 1; i++) {
                int min = i;

                // 每轮需要比较的次数 N-i
                for (int j = i + 1; j < target.length; j++) {
                    if (target[j].compareTo(target[min]) < 0) {
                        // 记录目前能找到的最小值元素的下标
                        min = j;
                    }
                }

                // 将找到的最小值和i位置所在的值进行交换
                if (i != min) {
                    T tmp = target[i];
                    target[i] = target[min];
                    target[min] = tmp;
                }

            }
        }
    }

    /**
     * 冒泡排序
     */
    public static class Maopao implements Sort {

        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            long t1 = System.currentTimeMillis();
            boolean flag = true;
            for (int i = 0; i < target.length - 1; i++) {
                for (int j = 0; j < target.length - 1 - i; j++) {
                    if (target[j].compareTo(target[j + 1]) > 0) {
                        T temp = target[j];
                        target[j] = target[j + 1];
                        target[j + 1] = temp;
                        flag = false;
                    }
                }
                if (flag) {
                    break;
                }
            }
            long t2 = System.currentTimeMillis();
            System.err.println("冒泡排序运行时间" + (t2 - t1));
        }
    }

    /**
     * 插入排序
     */
    public static class Charu implements Sort {
        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            long t1 = System.currentTimeMillis();
            int j;
            for (int i = 1; i < target.length; i++) {
                T temp = target[i];
                for (j = i; j > 0 && temp.compareTo(target[j - 1]) < 0; j--) {
                    target[j] = target[j - 1];
                }
                target[j] = temp;
            }
            long t2 = System.currentTimeMillis();
            System.err.println("插入排序运行时间：" + (t2 - t1));
        }
    }

    /**
     * 快速排序
     */
    public static class Kuaisu implements Sort {

        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            sort(target, 0 , target.length - 1);
        }

        private <T extends Comparable<? super T>> void sort(T[] target, int low, int high) {
            if (low < high) {
                int partitionIndex = partition(target, low, high);
                sort(target, low, partitionIndex - 1);
                sort(target, partitionIndex + 1, high);
            }
        }

        private <T extends Comparable<? super T>> int partition(T[] target, int low, int high) {
            // 设定基准值（pivot）
            int pivot = low;
            int index = pivot + 1;
            for (int i = index; i <= high; i++) {
                if (target[i].compareTo(target[pivot]) < 0) {
                    swap(target, i, index);
                    index++;
                }
            }
            swap(target, pivot, index - 1);
            return index - 1;
        }

        private <T extends Comparable<? super T>> void swap(T[] arr, int i, int j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
