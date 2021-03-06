package com.wxapp.frame.util;

import java.io.*;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: yibo.ma
 * Date: 13-8-1
 * Time: 上午10:11
 * To change this template use File | Settings | File Templates.
 */
public class FileTools {

    public static String loadFile(String path) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        String str = "";
        String s = null;
        while ((s = reader.readLine()) != null) {
            str = str + s + "\n";
        }

        isr.close();
        reader.close();

        return str;
    }

    public static LinkedList<String> loadFileForList(String path) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        LinkedList<String> rList = new LinkedList<>();
        String s = null;
        while ((s = reader.readLine()) != null) {
            rList.add(s);
        }

        isr.close();
        reader.close();

        return rList;
    }


    /**
     * 读取文件第一行内容
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String loadFileFirst(String path) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        String str = reader.readLine();

        isr.close();
        reader.close();

        return str;
    }

    /**
     * 读取文件第一行内容，并删除
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String loadFilePoll(String path) throws IOException {

        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        String str = reader.readLine();

        String write = "";
        String s = null;
        while ((s = reader.readLine()) != null) {
            write = write + s + "\n";
        }

        isr.close();
        reader.close();

        FileWriter fw = new FileWriter(new File(path));
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(write);

        bw.close();
        fw.close();

        return str;
    }

    public static void writeFile(String path, String str) throws IOException {
        FileWriter fw = new FileWriter(new File(path));
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(str);

        bw.close();
        fw.close();
    }

    public static void writeFile(String path, String str, boolean b) throws IOException {
        FileWriter fw = new FileWriter(new File(path), b);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(str);

        bw.close();
        fw.close();
    }



    public static void main(String[] args) throws IOException {
        System.out.println(FileTools.loadFilePoll("C:\\Users\\yibo.ma\\Documents\\红日.txt"));
    }
}
