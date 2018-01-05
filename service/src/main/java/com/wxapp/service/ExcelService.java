package com.wxapp.service;

public interface ExcelService {
    public String workingXlsByZip(String fileUrl);

    public String workingXlsByZip_old(String fileUrl);

    public void unZip(String path);
}
