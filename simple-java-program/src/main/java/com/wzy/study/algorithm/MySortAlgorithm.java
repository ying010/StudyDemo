package com.wzy.study.algorithm;

/**
 * 排序算法
 *
 * @author 王志英
 */
public class MySortAlgorithm {

    private static final int CUTOFF = 10;

    /**
     * 选择排序
     */
    public static class Xuanze implements Sort {
        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            long t1 = System.currentTimeMillis();
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
                    swap(target, i, min);
                }
            }
            long t2 = System.currentTimeMillis();
            System.err.println("选择排序运行时间：" + (t2 - t1));
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
                        swap(target, j, j + 1);
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
     * 归并排序
     */
    public static class Guibing implements Sort {

        /**
         * 排序入口方法
         *
         * @param target 待排序数组
         * @param <T>
         */
        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            long t1 = System.currentTimeMillis();
            T[] tmpArray = (T[]) new Comparable[target.length];

            sort(target, tmpArray, 0, target.length - 1);
            long t2 = System.currentTimeMillis();
            System.err.println("归并排序运行时间：" + (t2 - t1));
        }

        /**
         * 递归排序
         *
         * @param target   待排序数组
         * @param tmpArray 临时空间
         * @param left     起始位
         * @param right    结束位
         * @param <T>
         */
        private <T extends Comparable<? super T>> void sort(T[] target, T[] tmpArray, int left, int right) {
            if (left < right) {
                int center = (left + right) / 2;
                sort(target, tmpArray, left, center);
                sort(target, tmpArray, center + 1, right);
                merge(target, tmpArray, left, center + 1, right);
            }
        }

        /**
         * 将两个已排序数组合并为一个已排序数组
         *
         * @param target   待排序数组
         * @param tmpArray 临时空间
         * @param lPos     左半边已排序数组的开始下标
         * @param rPos     右半边已排序数组的开始下标
         * @param rEnd     右半边已排序数组的结束下标
         * @param <T>      可比较元素
         */
        private <T extends Comparable<? super T>> void merge(T[] target, T[] tmpArray, int lPos, int rPos, int rEnd) {
            // 左半边已排序数组的结束下标
            int lEnd = rPos - 1;
            // 本次排序开始
            int tmpPos = lPos;
            // 本次排序总排序元素数
            int numElements = rEnd - lPos + 1;

            // 主要排序逻辑
            // 从两个已排序数组开始位开始比较，将较小的数存入临时数组，如果相同存左边的数以保证稳定性
            while (lPos <= lEnd && rPos <= rEnd) {
                if (target[lPos].compareTo(target[rPos]) <= 0) {
                    tmpArray[tmpPos++] = target[lPos++];
                } else {
                    tmpArray[tmpPos++] = target[rPos++];
                }
            }

            // 有一边已执行完毕另一边未执行完毕时，将另一边依次存入临时数组
            while (lPos <= lEnd) {
                tmpArray[tmpPos++] = target[lPos++];
            }
            while (rPos <= rEnd) {
                tmpArray[tmpPos++] = target[rPos++];
            }

            // 将临时数组存回待排序数组
            for (int i = 0; i < numElements; i++, rEnd--) {
                target[rEnd] = tmpArray[rEnd];
            }

        }
    }

    /**
     * 快速排序
     */
    public static class Kuaisu implements Sort {

        /**
         * 驱动程序
         *
         * @param target 待排序数组
         * @param <T>
         */
        @Override
        public <T extends Comparable<? super T>> void sort(T[] target) {
            long t1 = System.currentTimeMillis();
            sort(target, 0, target.length - 1);
            long t2 = System.currentTimeMillis();
            System.err.println("快速排序运行时间：" + (t2 - t1));
        }

        /**
         * 递归排序
         *
         * @param target 待排序数组
         * @param low    排序开始位
         * @param high   排序结束位
         * @param <T>
         */
        private <T extends Comparable<? super T>> void sort(T[] target, int low, int high) {
            if (low + CUTOFF < high) {
                int partitionIndex = partition(target, low, high);
                sort(target, low, partitionIndex - 1);
                sort(target, partitionIndex + 1, high);
            } else {
                charu(target, low, high);
            }
        }

        /**
         * 将元素根据基准值排序，小于基准值的放左边，大于基准值的放右边，并返回最终的基准值位置
         *
         * @param target 待排序数组
         * @param low    排序开始位
         * @param high   排序结束位
         * @param <T>
         * @return
         */
        protected <T extends Comparable<? super T>> int partition(T[] target, int low, int high) {
            // 基准值（pivot）
            int pivot = low;
            // 待交换位
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

        private <T extends Comparable<? super T>> void charu(T[] target, int low, int high) {
            for (int i = low; i <= high; i++) {
                for (int j = i; j > low && target[j].compareTo(target[j - 1]) < 0; j--) {
                    swap(target, j, j - 1);
                }
            }
        }
    }

    /**
     * 快速排序,使用三分法优化
     */
    public static class Kuaisu1 extends Kuaisu {
        /**
         * 重写基准值获取逻辑与根据基准值排序逻辑
         * @param target 待排序数组
         * @param low    排序开始位
         * @param high   排序结束位
         * @param <T>
         * @return
         */
        @Override
        protected <T extends Comparable<? super T>> int partition(T[] target, int low, int high) {
            T pivot = sanFen(target, low, high);
            int i = low, j = high - 1;
            while (true) {
                while (target[++i].compareTo(pivot) < 0) {
                }
                while (target[--j].compareTo(pivot) > 0) {
                }
                if (i < j) {
                    swap(target, i, j);
                } else {
                    break;
                }
            }

            swap(target, i, high - 1);
            return i;
        }

        /**
         * 获取低、中、高三个位置元素的中间值
         *
         * @param target 目标数组
         * @param low    数组起始位
         * @param high   数组结束位
         * @param <T>
         * @return 返回大小处于中间的元素
         */
        private <T extends Comparable<? super T>> T sanFen(T[] target, int low, int high) {
            int center = (low + high) / 2;
            if (target[center].compareTo(target[low]) < 0) {
                swap(target, low, center);
            }
            if (target[high].compareTo(target[low]) < 0) {
                swap(target, low, high);
            }
            if (target[high].compareTo(target[center]) < 0) {
                swap(target, high, center);
            }

            swap(target, center, high - 1);
            return target[high - 1];
        }
    }
}
