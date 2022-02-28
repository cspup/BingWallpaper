package com.csp.bingwallpaper.Service;

import com.csp.bingwallpaper.BingWallpaperApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author csp
 * @date 2022/2/27 10:14
 * @description
 */
@Service
public class BingWallpaperService {

    @Value("${BingWallpaper.basePath}")
    private String filePath;


    public void getBingWallpaper() throws Exception {
        GetBingWallpaper getBingWallpaper = new GetBingWallpaper();
        if (filePath.isBlank()) {
            ApplicationHome h = new ApplicationHome(getClass());
            System.out.println(h);
            String path = String.valueOf(h);
            filePath = path + "/";
        }
        getBingWallpaper.setFilePath(filePath);
        getBingWallpaper.getWallpaper(0);
    }


    public String getWallpaperUPath(String fileName){
        return filePath+fileName;
    }
}
