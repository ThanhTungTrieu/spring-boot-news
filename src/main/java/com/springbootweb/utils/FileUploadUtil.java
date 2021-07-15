package com.springbootweb.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class FileUploadUtil {

    public final String root = "/home/thanhtungtrieu/Pictures/springboot";

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
}
