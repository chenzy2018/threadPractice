package com.thread.classLoader;

import java.io.*;
import java.util.List;

/**
 * 自定义类加载器
 *
 * 继承ClassLoader，然后实现findClass方法
 */
public class CustomClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //定义需要加载类的路径
        File file = new File("E:/Google Chrome", name.replaceAll(".", "/").concat(".class"));

        try {
            FileInputStream fileInputStream = new FileInputStream(file);//文件输入流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//二进制输出流
            int b = 0;
            while ((b = fileInputStream.read()) != 0){
                outputStream.write(b);
            }
            byte[] bytes = outputStream.toByteArray();

            outputStream.close();
            fileInputStream.close();

            return defineClass(name, bytes, 0, bytes.length);//将二进制文件转成class对象
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
    }
}
