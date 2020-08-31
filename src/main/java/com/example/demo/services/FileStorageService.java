package com.example.demo.services;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public interface FileStorageService {

    void init();

    Path loadFile(String filename);

    Stream<Path> loadAllFiles();

    String getFilePath(String filename) throws IOException;

    String storeFile(MultipartFile file) throws IOException;

    Resource loadFileAsResource(String filename);

    void deleteAllFiles();
}
