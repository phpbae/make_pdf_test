package com.example.demo.component;


import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ZipFileMakerComponent {

    private byte[] buf = new byte[4096];

    private final String SUFFIX = ".zip";
    private final String OUTPUT_PATH = "c:/test/";


    public File createZipFile(List<File> fileList) {

        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(OUTPUT_PATH + "file" + SUFFIX));

            for (int i = 0; i < fileList.size(); i++) {
                FileInputStream in = new FileInputStream(fileList.get(i));
                ZipEntry ze = new ZipEntry(fileList.get(i).getName());
                out.putNextEntry(ze);
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new File(OUTPUT_PATH + "file" + SUFFIX);


    }

}
