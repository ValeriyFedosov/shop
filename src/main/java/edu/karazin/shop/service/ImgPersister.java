package edu.karazin.shop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImgPersister {

    public static byte[] imgPersist(MultipartFile img) throws IOException {
        if (img.isEmpty()) return null;
        String imgName = img.getOriginalFilename();
        File upl = new File("src/main/webapp/WEB-INF/static/".concat(imgName));
        upl.createNewFile();
        try (FileOutputStream fout = new FileOutputStream(upl)) {
            fout.write(img.getBytes());
        }
        Path path = Paths.get("src/main/webapp/WEB-INF/static/".concat(imgName));
        Files.readAllBytes(path);
        return Files.readAllBytes(path);
    }
}
