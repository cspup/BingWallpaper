package com.csp.bingwallpaper.controller;

import com.csp.bingwallpaper.Service.BingWallpaperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Scheduled(cron = "0 0 2,3 1/1 * ? *")
    @RequestMapping(value = "/getWallpaperTask",method = RequestMethod.GET)
    @ResponseBody
    public void get(){
        try {
            bingWallpaperService.getBingWallpaper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getWallpaper",method = RequestMethod.GET)
    @ResponseBody
    public String getWallpaper(String day, int num) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        HashMap<String,Object> result = new HashMap<>();
        List<String> urlList = new ArrayList<>();

        for (int i=0;i<num;i++){
            calendar.add(Calendar.DATE, -num);
            urlList.add("/img/"+simpleDateFormat.format(calendar.getTime())+".jpg");
        }
        result.put("num",num);
        result.put("url",urlList);


        String res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            res = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }


}
