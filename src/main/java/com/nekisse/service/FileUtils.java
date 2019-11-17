package com.nekisse.service;

import ch.qos.logback.core.util.FileUtil;
import com.nekisse.property.FileUploadProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;

@Service
public class FileUtils {

    public static void deleteFile(String fileName) {

        System.out.println("FileName = " + fileName);
        System.out.println("fileUploadProperties.getUploadDir() = " + FileUploadProperties.uploadDir());

        File file = new File(FileUploadProperties.uploadDir() + "/" + fileName);
        System.out.println("file2 = " + file);
        System.out.println("fileName = " + file.getName());
        if (file.delete()) {
            System.out.println("삭제 완료 = ");
        }

        File folder = new File(FileUploadProperties.uploadDir());
        File[] files = folder.listFiles();
        if (files != null && files.length != 0) {
            for (File fileInFolder : files) {
                fileInFolder.delete();
            }
            System.out.println("files = " + files.length);
            System.out.println(" 폴더 내 파일 삭제 완료");
        } else {
            System.out.println("원래비어있었음");
        }

    }


}
