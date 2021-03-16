package com.thread.utils;

import java.io.*;

public class FileCopyUtil {

    public static void main(String[] args) throws IOException {

        long time1 = System.currentTimeMillis();

        /**
         * 字节流 2G 28s
         */
        //输入路径
        FileInputStream fileInputStream = new FileInputStream("F:\\apache-maven-3.6.3\\MavenRepository.zip");

        //输入路径
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\Google Chrome\\MavenRepository.zip");

        byte[] bytes = new byte[1024];

        int length;

        // while((length = fileInputStream.read(bytes)) != -1){
        //     fileOutputStream.write(bytes, 0 , length);
        // }
        // long time2 = System.currentTimeMillis();
        //
        // System.out.println("耗时：" + (time2-time1));


        /**
         * buffer流 2G 4.6s
         */
        length = 0;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        while((length = bufferedInputStream.read()) != -1){
            bufferedOutputStream.write(bytes, 0, length);
        }

        long time3 = System.currentTimeMillis();
        System.out.println("耗时：" + (time3-time1));

        //先开后关

        bufferedOutputStream.close();
        bufferedInputStream.close();
        fileOutputStream.close();
        fileInputStream.close();

    }
}
