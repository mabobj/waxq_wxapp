package com.wxapp.frame.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    /**
     * 压缩文件-由于out要在递归调用外,所以封装一个方法用来
     * 调用ZipFiles(ZipOutputStream out,String path,File... srcFiles)
     *
     * @param zip
     * @param path
     * @param srcFiles
     * @throws IOException
     * @author isea533
     */
    public static void ZipFiles(File zip, String path, File... srcFiles) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
        ZipUtil.ZipFiles(out, path, srcFiles);
        out.close();
        System.out.println("*****************压缩完毕*******************");
    }

    /**
     * 压缩文件-File
     *
     * @param srcFiles 被压缩源文件
     * @author isea533
     */
    public static void ZipFiles(ZipOutputStream out, String path, File... srcFiles) {
        path = path.replaceAll("\\*", "/");
        if (!path.endsWith("/")) {
            path += "/";
        }
        byte[] buf = new byte[1024];
        try {
            for (int i = 0; i < srcFiles.length; i++) {
                if (srcFiles[i].isDirectory()) {
                    File[] files = srcFiles[i].listFiles();
                    String srcPath = srcFiles[i].getName();
                    srcPath = srcPath.replaceAll("\\*", "/");
                    if (!srcPath.endsWith("/")) {
                        srcPath += "/";
                    }
                    out.putNextEntry(new ZipEntry(path + srcPath));
                    ZipFiles(out, path + srcPath, files);
                } else {
                    FileInputStream in = new FileInputStream(srcFiles[i]);
                    System.out.println(path + srcFiles[i].getName());
                    out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压到指定目录
     *
     * @param zipPath
     * @param descDir
     * @author isea533
     */
    public static void unZipFiles(String zipPath, String descDir) throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     *
     * @param zipFile
     * @param descDir
     * @author isea533
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            ;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            //输出文件路径信息
            System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }

    /**
     * 把一个目录打包到zip文件中的某目录
     *
     * @param dirpath  目录绝对地址
     */

    public static void packToolFiles(String dirpath, String zipFilePath)
            throws Exception {

        OutputStream out = null;
        BufferedOutputStream bos = null;
        ZipArchiveOutputStream zaos = null;
        File zipFile = new File(zipFilePath);
        if (!zipFile.getParentFile().exists()) {
            zipFile.getParentFile().mkdirs();
        }
        zipFile.delete();
        try {
            out = new FileOutputStream(zipFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bos = new BufferedOutputStream(out);
        zaos = new ZipArchiveOutputStream(bos);
        zaos.setEncoding("GBK");
        packToolFiles(zaos, dirpath, "");
        zaos.flush();
        zaos.close();
        bos.flush();
        bos.close();
        out.flush();
        out.close();
    }

    /**
     * 把一个目录打包到一个指定的zip文件中
     *
     * @param dirpath  目录绝对地址
     * @param pathName zip文件抽象地址
     */

    private static void packToolFiles(ZipArchiveOutputStream zaos,
                                      String dirpath, String pathName) throws FileNotFoundException,
            IOException {
        ByteArrayOutputStream tempbaos = new ByteArrayOutputStream();
        BufferedOutputStream tempbos = new BufferedOutputStream(tempbaos);
        File dir = new File(dirpath);
        // 返回此绝对路径下的文件
        File[] files = dir.listFiles();
        if (files == null || files.length < 1) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            // 判断此文件是否是一个文件夹
            if (files[i].isDirectory()) {
                packToolFiles(zaos, files[i].getAbsolutePath(), pathName
                        + files[i].getName() + File.separator);
            } else {
                zaos.putArchiveEntry(new ZipArchiveEntry(pathName
                        + files[i].getName()));
                IOUtils.copy(new FileInputStream(files[i].getAbsolutePath()),
                        zaos);
                zaos.closeArchiveEntry();
            }
        }
        tempbaos.flush();
        tempbaos.close();
        tempbos.flush();
        tempbos.close();
    }


    public static void main(String[] args) throws IOException {
        /**
         * 压缩文件
         */
        File[] files = new File[]{new File("d:/English"), new File("d:/发放数据.xls"), new File("d:/中文名称.xls")};
        File zip = new File("d:/压缩.zip");
        ZipFiles(zip, "abc", files);

        /**
         * 解压文件
         */
        File zipFile = new File("d:/压缩.zip");
        String path = "d:/zipfile/";
        unZipFiles(zipFile, path);
    }
}
