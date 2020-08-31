package com.example.demo.config;

import com.example.demo.services.FileStorageServiceImpl;
import com.example.demo.util.AppConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;


@ConfigurationProperties("storage")
public class FileStorageProperties {

    private String location = "uploadDir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
