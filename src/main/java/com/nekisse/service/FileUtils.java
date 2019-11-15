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

        File file2 = new File(FileUploadProperties.uploadDir() + "/" + fileName);
        System.out.println("file2 = " + file2);
        String name = file2.getName();
        System.out.println("name = " + name);
        if (file2.delete()) {
            System.out.println("삭제 완료 = ");
        }


    }


}
