package com.wxapp.service.impl;

import com.wxapp.frame.base.BaseService;
import com.wxapp.frame.dao.JdbcDaoMySql;
import com.wxapp.service.ExcelService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;


@Service
public class ExcelServiceImpl extends BaseService implements ExcelService {

    private List<File> getDirXlsFileList(File dir) {
        List<File> list = new ArrayList<>();
        File[] fs = dir.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                list.addAll(getDirXlsFileList(f));
            } else {
                if (f.getName().indexOf("xlsx") > 0||f.getName().indexOf("xls") > 0) {
                    list.add(f);
                }
            }
        }
        return list;
    }

    @Override
    public String workingXlsByZip(String fileUrl) {

        return "";
    }


    @Override
    public String workingXlsByZip_old(String fileUrl) {

        return "";
    }



    public String workingXlsByZip_bak(String fileUrl) {

        return "";
    }


    public void unZip(String path) {

    }
}
