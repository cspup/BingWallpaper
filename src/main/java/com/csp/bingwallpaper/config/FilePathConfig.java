package com.csp.bingwallpaper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author csp
 * @date 2022/2/27 17:11
 * @description
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {
    @Value("${BingWallpaper.basePath}")
    private String filePath;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (filePath.isBlank()) {
            ApplicationHome h = new ApplicationHome(getClass());
            System.out.println(h);
            String path = String.valueOf(h);
            filePath = path + File.separator;
        }
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + filePath);
    }
}
