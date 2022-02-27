package com.csp.bingwallpaper.controller;

import com.csp.bingwallpaper.Service.BingWallpaperService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author csp
 * @date 2022/2/27 10:13
 * @description
 */
@Controller
public class BingWallpaperController {

    final private BingWallpaperService bingWallpaperService;
    BingWallpaperController(BingWallpaperService bingWallpaperService){
        this.bingWallpaperService = bingWallpaperService;
    }

    @RequestMapping(value = "/getWallpaperTask",method = RequestMethod.GET)
    @ResponseBody
    public void get(){
        try {
            bingWallpaperService.getBingWallpaper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
