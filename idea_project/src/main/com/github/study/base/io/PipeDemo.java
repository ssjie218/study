package com.github.study.base.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by pc on 2018/11/2.
 */
public class PipeDemo {
    public static void main(String[] args) throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
        Runnable r1 = () -> {
            String str = "hello pipe!!";
            try {
                pipedOutputStream.write(str.getBytes());
                pipedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                byte[] result=new byte[1024];
                int len=pipedInputStream.read(result);
                System.out.println(new String(result,0,len));
                pipedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);
        t1.start();
        t2.start();
    }
}
