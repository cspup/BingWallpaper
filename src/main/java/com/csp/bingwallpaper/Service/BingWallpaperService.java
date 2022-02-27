package com.csp.bingwallpaper.Service;

import org.springframework.stereotype.Service;

/**
 * @author csp
 * @date 2022/2/27 10:14
 * @description
 */
@Service
public class BingWallpaperService {

    public void getBingWallpaper() throws Exception {
        GetBingWallpaper getBingWallpaper = new GetBingWallpaper();
        getBingWallpaper.getWallpaper(0);
    }
}
