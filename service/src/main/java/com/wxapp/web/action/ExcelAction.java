package com.wxapp.web.action;


import com.wxapp.frame.base.BaseAction;
import com.wxapp.frame.util.ZipUtil;
import com.wxapp.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
/*@RequestMapping("/book")*/
public class ExcelAction extends BaseAction {

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/upExcelZip_old", method = RequestMethod.GET)
    private String upExcelZip_old(Model model) {
        return "upExcelZip_old";// WEB-INF/jsp/"list".jsp
    }

    @RequestMapping("upload_old")
    public String upload_old(@RequestParam("file") CommonsMultipartFile file, Model model) throws IOException {
        if (file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4, file.getOriginalFilename().length())
                .indexOf("zip") <= 0) {
            return "error";
        }

        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String path = ExcelAction.class.getClassLoader().getResource("").getPath()
                .replace("WEB-INF/classes/", "")
                + "upZip/";
        String fileName = new Date().getTime() + "" + file.getOriginalFilename();
        String filePath = path + fileName;

        File newFile = new File(filePath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + String.valueOf(endTime - startTime) + "ms");


        try {
            ZipUtil.unZipFiles(filePath, path + fileName.substring(0, fileName.length() - 4) + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        File zipFile = new File(path + fileName.substring(0, fileName.length() - 4));
        excelService.workingXlsByZip_old(zipFile.getPath());
//        ZipUtil.ZipFiles(new File(path + fileName.substring(0, fileName.length() - 4) + "_ok.zip"),
//                "整理", zipFile);

        try {
            ZipUtil.packToolFiles(zipFile.getPath(), path + fileName.substring(0, fileName.length() - 4) + "_ok.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("uri", "/upZip/" + fileName.substring(0, fileName.length() - 4) + "_ok.zip");
        return "success";
    }




    @RequestMapping(value = "/upExcelZip", method = RequestMethod.GET)
    private String upExcelZip(Model model) {
        return "upExcelZip";// WEB-INF/jsp/"list".jsp
    }

    @RequestMapping("upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file, Model model) throws IOException {
        if (file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4, file.getOriginalFilename().length())
                .indexOf("zip") <= 0) {
            return "error";
        }

        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + file.getOriginalFilename());
        String path = ExcelAction.class.getClassLoader().getResource("").getPath()
                .replace("WEB-INF/classes/", "")
                + "upZip/";
        String fileName = new Date().getTime() + "" + file.getOriginalFilename();
        String filePath = path + fileName;

        File newFile = new File(filePath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + String.valueOf(endTime - startTime) + "ms");


        try {
            ZipUtil.unZipFiles(filePath, path + fileName.substring(0, fileName.length() - 4) + "/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        File zipFile = new File(path + fileName.substring(0, fileName.length() - 4));
        excelService.workingXlsByZip(zipFile.getPath());
//        ZipUtil.ZipFiles(new File(path + fileName.substring(0, fileName.length() - 4) + "_ok.zip"),
//                "整理", zipFile);

        try {
            ZipUtil.packToolFiles(zipFile.getPath(), path + fileName.substring(0, fileName.length() - 4) + "_ok.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("uri", "/upZip/" + fileName.substring(0, fileName.length() - 4) + "_ok.zip");
        return "success";
    }

}
