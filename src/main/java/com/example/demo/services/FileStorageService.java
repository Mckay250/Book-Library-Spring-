package com.example.demo.services;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileStorageService {

    public String storeFile(MultipartFile file) throws IOException;

    public Resource loadFileAsResource(String filename);
}
