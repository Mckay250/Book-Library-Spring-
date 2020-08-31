package com.example.demo.services;

import com.example.demo.Exception.FileStorageException;
import com.example.demo.Exception.MyFileNotFoundException;
import com.example.demo.config.FileStorageProperties;
import com.example.demo.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;


@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new FileStorageException(
                        "Cannot store file with relative path outside current directory" + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e ) {
            throw new FileStorageException("Failed to store file" + filename, e);
        }
        return getFilePath(filename);
    }

    @Override
    public String getFilePath(String filename) throws IOException {
        Path path = Paths.get(rootLocation.toString() +"/" + filename);
        Path realPath = path.toRealPath(LinkOption.NOFOLLOW_LINKS);
        return realPath.toString();
    }

    @Override
    public Stream<Path> loadAllFiles() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path loadFile(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadFileAsResource(String filename) {
        try{
            Path file = loadFile(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new MyFileNotFoundException("Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new MyFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAllFiles() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new FileStorageException("Could not initialize storage", e);
        }
    }
}
