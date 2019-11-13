package com.nekisse.service;

import ch.qos.logback.core.util.FileUtil;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {

    public static void deleteFile(String fileName) {
        System.out.println("FileUadskfojasdofjaweoifoaiwejftils = " + fileName);


        File file2 = new File("uploads/"+fileName);
        System.out.println("file2 = " + file2);
        String name = file2.getName();
        System.out.println("name = " + name);
        if (file2.delete()) {
            System.out.println("삭제 완료 = ");
        }



    }


}
