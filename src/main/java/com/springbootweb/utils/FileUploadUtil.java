package com.springbootweb.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class FileUploadUtil {

    public final String root = "C:/home/thanhtungtrieu/Pictures/springboot";

    public void writeOrUpdateFile(byte[] bytes, String path) {
        //file directory = root + path -> ex: /home/thanhtungtrieu/Pictures/springboot/abc.png
        //create directory if not exists
        File subDirectory = new File(StringUtils.substringBeforeLast(root + path, "/"));
        if (!subDirectory.exists()) {
            subDirectory.mkdirs();
        }
        // write file
        // auto close fileOutputStream
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(root + path));) {
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileAsBase64(String fullPath) {
        File thumbnailFile = new File(fullPath);
        String base64 = null;
        try {
            byte[] bytes = Files.readAllBytes(thumbnailFile.toPath());
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}
