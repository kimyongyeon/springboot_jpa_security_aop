package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by kimyongyeon on 2016-12-23.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}