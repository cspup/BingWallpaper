package com.csp.bingwallpaper.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * @author yemuc
 * 获取壁纸
 */
public class GetBingWallpaper {

    Logger logger = LoggerFactory.getLogger(Logger.class);
    /**
     * 文件路径
     */
    private String filePath ="";

    public void setFilePath(String filePath) {//设置路径
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void getWallpaper(int idx) throws Exception {//获取idx天前的图片，Bing接口支持7最近7天
        String json=getJson(idx);
        if (json!=null){
//            System.out.println(json);//显示返回数据
            downloadPaper(getDownUrl(json),getFileName(json));
        }
        else{

            System.out.println("获取失败！");
        }
    }

    /**
     * 获取前idx天json
     * @param idx 天数
     * @return json
     */
    public String getJson(int idx){
        try {
            //bing每日图片接口返回值是json，idx:今日之前天数
            URL url = new URL("http://cn.bing.com/HPImageArchive.aspx?format=js&idx="+idx+"&n=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();

            StringBuilder json = new StringBuilder();
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {
                json.append(line);
            }
            inputStream.close();
            return json.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void downloadPaper(URL url, String filename) {
        try {
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(1000);
            InputStream inputStream = conn.getInputStream();
            byte[] data = readInputStream(inputStream);
            File file = new File(filename);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.close();
            logger.info("下载成功！");
            logger.info("保存为："+filename);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception{
        //从输入流中读取图片

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        return outputStream.toByteArray();
    }
    public String getFileName(String json){//获取文件名（以日期命名）
        String filename=json.substring(json.indexOf("enddate")+10,json.indexOf("enddate")+18);
        //返回设置的路径加文件名，如果没有设置路径保存到运行目录下
        return filePath+filename+".jpg";
    }

    /**
     * 从获取到的json里获取图片url
     * @param json json
     * @return url
     */
    public URL getDownUrl(String json) throws MalformedURLException {
        String paperLink;
        //在json中找出url
        paperLink=json.substring(json.indexOf("url")+6,json.indexOf(".jpg")+4);
        //json中的地址可能有两种格式
        String https = "https://";
        if (paperLink.contains(https)){
            paperLink=paperLink.replace("https","http");
            return new URL(paperLink);
        }
        else {
            return new URL("http://s.cn.bing.net" + paperLink);
        }
    }
}
