package com.zerg.tiamat.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;


@Slf4j
public class FileUtils {
    private static final int byteArrayLenght = 8192;

    /**
     * 临时文件路径
     */
    public static final String TEMP_PATH = "/tmp/";
    /**
     * 表格文件后缀
     */
    public static final String EXCEL_SUFFIX = ".xlsx";

    /**
     * 输入流转文件
     */
    public static File inputStreamToFile(InputStream in, String fileName) {
        File file = new File(fileName);
        inputStreamToFile(in, file);
        return file;
    }

    /**
     * 输入流到指定文件
     */
    public static void inputStreamToFile(InputStream in, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            int bytesRead = 0;
            byte[] buffer = new byte[byteArrayLenght];
            while ((bytesRead = in.read(buffer, 0, byteArrayLenght)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("inputStreamToFile fail", e);
        }
    }

    /**
     * 使用临时文件，执行文件回调
     *
     * @param pathName  文件名
     * @param filesByte 文件内容
     * @param callback  回调
     */
    public static void doWithTempFile(String pathName, byte[] filesByte, Consumer<Path> callback) {
        Path file = null;
        try {
            // 删除冲突文件
            if (Files.exists(Paths.get(pathName))) {
                Files.delete(Paths.get(pathName));
            }
            // 创建路径及文件
            file = Files.createFile(Paths.get(pathName));
            // 写入文件
            Files.write(file, filesByte);
            // 执行回调
            if (callback != null) {
                callback.accept(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    Files.delete(file);
                } catch (IOException e) {
                    log.warn("删除文件异常", e);
                }
            }
        }
    }

}
