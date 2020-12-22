package com.wzy.study.algorithm;

import com.wzy.study.algorithm.model.StabilityObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 大数组生成工具
 *
 * @author 王志英
 */
@Slf4j
public class GenerateBigArrayUtil {

    /**
     * 获取随机数组存入文件
     *
     * @param count
     */
    public static void getArray(int count) {
        File f = new File("D:/out/temp/");
        if (!f.exists()) {
            f.mkdirs();
        }
        try (Writer writer = new FileWriter("D:/out/temp/array.txt")) {
            int x = new Random().nextInt(10000 * 10000);
            writer.write("" + x);
            for (int i = 0; i < count; i++) {
                x = new Random().nextInt(10000 * 10000);
                writer.write(("," + x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个有重复元素的随机数组存入文件
     *
     * @param count
     */
    public static void getArrayAllowRepeat(int count) {
        File f = new File("D:/out/temp/");
        if (!f.exists()) {
            f.mkdirs();
        }
        try (Writer writer = new FileWriter("D:/out/temp/arrayAllowRepeat.txt")) {
            int x = (int) (Math.random() * 1000);
            writer.write(x);
            for (int i = 0; i < count; i++) {
                x = (int) (Math.random() * 1000);
                writer.write(("," + x));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个可检测稳定性的数组
     * @param size 数组长度
     * @return
     */
    public static StabilityObject[] getStableArray(int size) {
        StabilityObject[] data = new StabilityObject[size];
        for (int i = 0; i < size; i++) {
            int random = new Random().nextInt(10);
            StabilityObject t = new StabilityObject(random, i);
            data[i] = t;
        }
        return data;
    }

    /**
     * 从文件中读取数组
     *
     * @return
     * @throws Exception
     */
    public static Integer[] readArray() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/out/temp/array.txt"))) {
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            String[] ss = sb.toString().split(",");
            Integer[] ret = new Integer[ss.length];
            for (int i = 0; i < ss.length; i++) {
                ret[i] = Integer.valueOf(ss[i]);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * 使用工厂模式获取各个排序算法
     * @param sortName
     * @return
     */
    public static Sort getSortFactory(String sortName) {
        switch (sortName) {
            case "选择":
                return new MySortAlgorithm.Xuanze();
            case "冒泡":
                return new MySortAlgorithm.Maopao();
            case "插入":
                return new MySortAlgorithm.Charu();
            case "归并":
                return new MySortAlgorithm.Guibing();
            case "快速":
                return new MySortAlgorithm.Kuaisu();
            case "快速优化1":
                return new MySortAlgorithm.Kuaisu1();
            default:
                throw new RuntimeException("算法名输入错误");
        }
    }

    /**
     * 判断数组是否有序
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean checkOrder(T[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i].compareTo(target[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测数组排序的稳定性
     * 大小相同的元素排序前后相对位置不变是稳定，相对改变是不稳定
     * @param target
     * @return
     */
    public static boolean checkStability(StabilityObject[] target) {
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i].compareTo(target[i + 1]) == 0 && target[i].getOrder() > target[i + 1].getOrder()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 二分查找
     * log(n),对数执行，时间极短
     *
     * @param source
     * @param target
     * @return
     */
    public static int binarySearch(Integer[] source, int target) {
        int low = 0, high = source.length - 1;
        long t1 = System.currentTimeMillis();
        int a = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (source[mid] < target) {
                low = mid + 1;
            } else if (source[mid] > target) {
                high = mid - 1;
            } else {
                a = mid;
                break;
            }
        }
        long t2 = System.currentTimeMillis();
        System.err.println("折半查找时间" + (t2 - t1));
        return a;
    }

    /**
     * 顺序查找
     * 线性执行，时间较短
     *
     * @param source
     * @param target
     * @return
     */
    public static int line(Integer[] source, int target) {
        long t1 = System.currentTimeMillis();
        int a = -1;
        for (int i = 0; i < source.length; i++) {
            if (source[i] == target) {
                a = i;
            }
        }
        long t2 = System.currentTimeMillis();
        System.err.println("顺序查找执行时间" + (t2 - t1));
        return a;
    }

    /**
     * 获取随机数组存入文件
     */
    @Test
    public void getArray() {
        GenerateBigArrayUtil.getArray(10000 * 10);
    }

    @Test
    public void main() throws Exception {
        Integer[] array = readArray();
        new MySortAlgorithm.Guibing().sort(array);
        System.err.println(line(array, 36194523));
        System.err.println(binarySearch(array, 36194523));
    }

    /**
     * 比较算法稳定性
     * @throws Exception
     */
    @Test
    public void compareSortStable() throws Exception {
        List<String> methods = Arrays.asList("选择", "冒泡", "插入", "归并", "快速");
        StabilityObject[] array;
        for (String methodName : methods) {
            array = getStableArray(100);
            log.info("{}排序前是否稳定{}", methodName, checkStability(array));
            GenerateBigArrayUtil.getSortFactory(methodName).sort(array);
            log.info("{}排序后是否稳定{}", methodName, checkStability(array));
            log.info("----------------------------------");
            Thread.sleep(1000);
        }
    }

    /**
     * 比较各算法运行时间
     * @throws Exception
     */
    @Test
    public void compareSortTime() throws Exception {
        List<String> methods = Arrays.asList("选择", "冒泡", "插入", "归并", "快速", "快速优化1");
        Integer[] array;
        for (String methodName : methods) {
            array = readArray();
            log.info("{}排序前是否有序{}", methodName, checkOrder(array));
            GenerateBigArrayUtil.getSortFactory(methodName).sort(array);
            log.info("{}排序后是否有序{}", methodName, checkOrder(array));
            log.info("----------------------------------");
        }
    }

    /**
     * 对已排序数组比较各算法运行时间
     * @throws Exception
     */
    @Test
    public void compareSortTimeForSorted() throws Exception {
        List<String> methods = Arrays.asList("选择", "冒泡", "插入", "归并", "快速", "快速优化1");
        Integer[] array;
        array = readArray();
        GenerateBigArrayUtil.getSortFactory("归并").sort(array);
        for (String methodName : methods) {
            log.info("{}排序前是否有序{}", methodName, checkOrder(array));
            GenerateBigArrayUtil.getSortFactory(methodName).sort(array);
            log.info("{}排序后是否有序{}", methodName, checkOrder(array));
            log.info("----------------------------------");
        }
    }
}
