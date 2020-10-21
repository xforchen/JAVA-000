package com.xforchen.demo.jvm;

import java.io.*;
import java.lang.reflect.Method;

public class ClassLoaderDemo extends ClassLoader {

    private static final String PATH = "/home/xforchen/study/JVM/Hello.xlass";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        File file = new File(name);

        InputStream in;
        byte[] bytes = null;

        try {
            in = new FileInputStream(file);
            bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }

        return defineClass("Hello", bytes, 0, bytes.length);
    }

    public static void main(String[] args) {
        try {
            Object o = new ClassLoaderDemo().findClass(PATH).newInstance();
            Method method = o.getClass().getMethod("hello");
            method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
