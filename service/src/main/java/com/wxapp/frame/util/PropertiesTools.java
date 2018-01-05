package com.wxapp.frame.util;


import java.io.*;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Base: lovesuper_ao
 * Date: 13-10-1
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesTools {

    public static Properties initProperties() {

        //InputStream in = SystemTools.class.getClassLoader().getResourceAsStream("Config.properties");

        String filePath = PropertiesTools.class.getClassLoader().getResource("").getPath() + "Config.properties";
/*
        String[] paths = PropertiesTools.class.getClassLoader().getResource("/").getPath().split(String.valueOf(File.separatorChar));
        String filePath = String.valueOf(File.separatorChar);
        for (int i = 1; i < paths.length - 2; i++) {
            filePath = filePath + paths[i] + File.separatorChar;
        }
        filePath = filePath + "WEB-INF" + File.separator + "classes" + File.separatorChar + "Config.properties";
*/

        System.out.println("PropertiesTools === " + filePath);


        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return p;
    }

    private static Properties p = initProperties();
    public static String JEDIS_IP = p.getProperty("JEDIS_IP");


    public static Map CITY_MAP = new HashMap();
    public static Map SHENG_CITY_MAP = new HashMap();
    public static Map CAR_SERIES_MAP = new HashMap();//车系
    public static Map CAR_BRAND_MAP = new HashMap();//品牌

    static {
        String filePath = PropertiesTools.class.getClassLoader().getResource("").getPath() + "city";
        try {
            List<String> list = FileTools.loadFileForList(filePath);
            for (String s : list) {
                String[] d = s.split("\t");
                PropertiesTools.CITY_MAP.put(d[1], d[0]);
            }

            for (String s : list) {
                String[] d = s.split("\t");
                if (SHENG_CITY_MAP.containsKey(d[2])) {
                    List t = (List) SHENG_CITY_MAP.get(d[2]);
                    t.add(d[1]);
                    SHENG_CITY_MAP.put(d[2], t);
                }else{
                    List t = new ArrayList();
                    t.add(d[1]);
                    SHENG_CITY_MAP.put(d[2], t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        filePath = PropertiesTools.class.getClassLoader().getResource("").getPath() + "car";
        try {
            List<String> list = FileTools.loadFileForList(filePath);
            for (String s : list) {
                String[] d = s.split("\t");
                PropertiesTools.CAR_SERIES_MAP.put(d[1], d[0]);
                PropertiesTools.CAR_BRAND_MAP.put(d[2], d[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        // System.out.println(SystemTools.class.getClassLoader().getResourceAsStream("Config.properties"));

    }
}
