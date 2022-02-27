package com.csp.bingwallpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;

@SpringBootApplication
public class BingWallpaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingWallpaperApplication.class, args);
        ApplicationHome h = new ApplicationHome(BingWallpaperApplication.class);
        File jarF = h.getSource();
        System.out.println(jarF.getParentFile().toString());
    }

}
