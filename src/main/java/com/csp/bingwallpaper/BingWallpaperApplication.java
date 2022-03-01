package com.csp.bingwallpaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

@EnableScheduling
@SpringBootApplication
public class BingWallpaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingWallpaperApplication.class, args);
        ApplicationHome h = new ApplicationHome(BingWallpaperApplication.class);
        File jarF = h.getSource();
        System.out.println(jarF.getParentFile().toString());
    }

}
