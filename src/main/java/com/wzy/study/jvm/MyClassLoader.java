package com.wzy.study.jvm;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader {
    public static void main(String[] args) throws Exception {
//        String[] a = System.getProperty("sun.boot.class.path").split(";");
//        String[] a = System.getProperty("java.ext.dirs").split(";");
//        String[] a = System.getProperty("java.class.path").split(";");
//        for (String a1 : a) {
//            System.out.println(a1);
//        }

//        ClassLoader loader = MyClassLoader.class.getClassLoader();
//        while (loader != null) {
//            System.out.println(loader.toString());
//            loader = loader.getParent();
//        }

        /*File file = new File("d:/");
        URL url = file.toURI().toURL();

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
        Class<?> aClass = urlClassLoader.loadClass("com.wzy.Demo");
        Object newInstance = aClass.newInstance();

        System.out.println("classLoader:" + aClass.getClassLoader());
        System.out.println("parent:" + aClass.getClassLoader().getParent());

        MyFileClassLoader myFileClassLoader = new MyFileClassLoader("D:/");
        Class clazz = myFileClassLoader.loadClass("com.wzy.Demo");
        Class clazz2 = myFileClassLoader.loadClass("com.wzy.Demo");
        Object newInstance2 = clazz.newInstance();
        System.out.println("classLoader:" + clazz.getClassLoader());
        System.out.println("parent:" + clazz.getClassLoader().getParent());
        System.out.println(newInstance2.equals(newInstance));
        System.out.println(clazz == clazz2);*/


        URL url1 = new URL("http://localhost:8080/");
        URLClassLoader urlClassLoader1 = new URLClassLoader(new URL[]{url1});
        Class<?> aClass1 = urlClassLoader1.loadClass("com.wzy.Demo");
        Object newInstance1 = aClass1.newInstance();

        System.out.println("classLoader:" + aClass1.getClassLoader());
        System.out.println("parent:" + aClass1.getClassLoader().getParent());
        System.out.println(newInstance1.equals(newInstance1));
    }
}
