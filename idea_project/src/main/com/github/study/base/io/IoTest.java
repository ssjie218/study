package com.github.study.base.io;

import java.io.*;

/**
 * 字符流：顾名思义，该流只能处理字符，但处理字符速度很快
 字节流：可以处理所有以bit为单位储存的文件，也就是说可以处理所有的文件，但是在处理字符上的速度不如字符流
 * Created by pc on 2018/11/1.
 */
public class IoTest {
    public static void main(String[] args) throws IOException {

        inputStream();
        outputStream();;

    }
    private static void outputStream() throws IOException{
        String fileName="D:"+File.separator+"hello.txt";
        File f=new File(fileName);
        OutputStream out =new FileOutputStream(f);
        String str="Hello World！！";
        byte[] b=str.getBytes();
        for (int i = 0; i < b.length; i++) {
            out.write(b[i]);
        }
        out.close();
        out =new FileOutputStream(f,true);
        OutputStreamWriter writer=new OutputStreamWriter(out,"utf-8");
        writer.append(str);
        writer.close();
        out.close();

    }
    //文件字节流读写
    private static void inputStream() throws IOException{
        String fileName="D:"+ File.separator+"hello.txt";
        File f=new File(fileName);
        InputStream in=new FileInputStream(f);
        System.out.println(f.length());
        byte[] b=new byte[1024];
        int len=in.read(b);
        in.close();
        System.out.println("读入长度为：" + len);
        System.out.println(new String(b,0,len));
        System.out.println(new String(b));
        System.out.println("1");
        in=new FileInputStream(f);
        InputStreamReader reader=new InputStreamReader(in,"utf-8");
        int i;
        while((i=reader.read()) != -1){
            System.out.print((char)i);  //輸出  陳
        }
        reader.close();
        in.close();
    }
}
