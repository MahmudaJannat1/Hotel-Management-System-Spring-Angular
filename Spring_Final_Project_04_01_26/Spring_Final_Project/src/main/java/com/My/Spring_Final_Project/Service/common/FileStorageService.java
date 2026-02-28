package com.My.Spring_Final_Project.Service.common;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path uploadDir = Paths.get("uploads"); // directory name

    public FileStorageService() {
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir); // create if not exists
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload folder!", e);
        }
    }

    public String saveFile(MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = uploadDir.resolve(originalFilename);

        try {
            file.transferTo(targetLocation.toFile());
            return originalFilename; // return filename to save in DB
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + originalFilename, e);
        }
    }

    public void saveFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            saveFile(file);
        }
    }
}
