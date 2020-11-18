package com.wzy.study.jvm.classloader;

import java.io.*;

public class MyFileClassLoader extends ClassLoader {
    private String dir;

    /**
     * 有参构造器，指定加载器的加载路径，本示例加载本地问价，参数为本地路径
     * 没有指定父加载器时默认为系统类加载器
     * @param dir
     */
    public MyFileClassLoader(String dir) {
        this.dir = dir;
    }

    /**
     * 也可以显示设置父类加载器，如果父类加载器传null，那么代表父类加载器是启动类(Bootstrap)加载器
     * @param parent
     * @param dir
     */
    public MyFileClassLoader(ClassLoader parent, String dir) {
        super(parent);
        this.dir = dir;
    }

    /**
     * 重载父类的findClass方法
     * 本示例为文件类加载器，加载本地文件中的类，根据加载路径和类全名获取.class文件绝对路径，并读取二进制字节码
     * 将二进制字节码传给defineClass获取类对象
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filePath = dir + name.replace(".", File.separator) + ".class";
        try (InputStream in = new FileInputStream(filePath); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            byte[] b = bos.toByteArray();
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception{
//        MyFileClassLoader myFileClassLoader = new MyFileClassLoader("D:/");
//        Class clazz = myFileClassLoader.loadClass("com.wzy.Demo");
//        clazz.newInstance();
//        System.out.println(myFileClassLoader.getParent());

//        MyFileClassLoader myFileClassLoader2 = new MyFileClassLoader(null, "D:/");
//        Class clazz2 = myFileClassLoader2.loadClass("java.sql.DriverManager");
//        System.out.println(clazz2.getClassLoader());

//        MyFileClassLoader myFileClassLoader3 = new MyFileClassLoader("D:/");
//        Class clazz3 = myFileClassLoader3.loadClass("com.wzy.Demo1");
//        System.out.println(clazz3.getClassLoader());
    }
}
