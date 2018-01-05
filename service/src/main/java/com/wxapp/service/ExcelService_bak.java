package com.wxapp.service;

import com.wxapp.frame.dao.JdbcDaoMySql;
import com.wxapp.frame.util.XlsUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ExcelService_bak extends JdbcDaoMySql {

    private List<File> getDirXlsFileList(File dir) {
        List<File> list = new ArrayList<>();
        File[] fs = dir.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                list.addAll(getDirXlsFileList(f));
            } else {
                if (f.getName().indexOf("xlsx") > 0) {
                    list.add(f);
                }
            }
        }
        return list;
    }

    public Map<String, List<String[]>> getXlsData() {
        Map<String, List<String[]>> rMap = new HashMap();
        List<String[]> tList;

        String fileUrl = "F:\\马博的文件夹\\自有项目\\郭春鹏\\1105";

        List<File> list = getDirXlsFileList(new File(fileUrl));

        for (File file : list) {
            System.out.println(file.getPath());
            try {
                LinkedList<String> ll = XlsUtil.read(file.getPath());
                for (int i = 1; i < ll.size(); i++) {
                    System.out.println(ll.get(i));
                    String[] c = ll.get(i).split("\t");
                    String key = c[2] + "-" + c[3] + "-" + c[5] + "-" + c[6];
                    if (rMap.containsKey(key)) {
                        tList = rMap.get(key);
                        tList.add(new String[]{c[0], c[1]});
                    } else {
                        tList = new ArrayList<>();
                        tList.add(new String[]{"姓名", "电话"});
                        tList.add(new String[]{c[0], c[1]});
                    }
                    rMap.put(key, tList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        return rMap;
    }


    public void printXls(Map map) {
        int jishu = 1;
        for (Object o : map.keySet()) {
            try {
                XlsUtil.write("F:\\马博的文件夹\\自有项目\\郭春鹏\\1105\\1105_ok\\" + jishu + "-" + o + ".xlsx", (List<String[]>) map.get(o));
                jishu++;
                if (jishu > 4) {
                    jishu = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void printXls2(String fileUrl) {
        File dirFile = new File(fileUrl);
        List<File> fileList = getDirXlsFileList(dirFile);

        for (File file : fileList) {
            String tempFilePath = file.getPath().replace(fileUrl, "");
            //tempFilePath = tempFilePath.substring(0, tempFilePath.lastIndexOf("\\"));
            String newDir = fileUrl + File.separator + "ok" + tempFilePath;
            System.out.println(newDir);
            new File(newDir).mkdirs();

            Map<String, List<String[]>> tempMap = new HashMap();
            List<String[]> tList;
            try {
                LinkedList<String> ll = XlsUtil.read(file.getPath());
                for (int i = 1; i < ll.size(); i++) {
                    System.out.println(ll.get(i));
                    String[] c = ll.get(i).split("\t");
                    String key = c[2] + "-" + c[3] + "-" + c[5] + "-" + c[6];

                    if (tempMap.containsKey(key)) {
                        tList = tempMap.get(key);
                        tList.add(new String[]{c[0], c[1]});
                    } else {
                        tList = new ArrayList<>();
                        tList.add(new String[]{"姓名", "电话"});
                        tList.add(new String[]{c[0], c[1]});
                    }
                    tempMap.put(key, tList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            int jishu = 1;
            for (String key : tempMap.keySet()) {
                try {
                    XlsUtil.write(newDir + File.separator + jishu + "-" + key + ".xlsx", tempMap.get(key));
                    jishu++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        ExcelService_bak es = new ExcelService_bak();
        es.printXls2("F:\\马博的文件夹\\自有项目\\郭春鹏\\1105");

    }
}
