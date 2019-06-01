package yemu;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetPhoto {
    private String day;
    private int num;
    private String path="";//图片文件保存目录
    private String url="";//要输出的url地址前缀
    public GetPhoto(String day,int num){
        this.day=day;
        this.num=num;
        try {
            InputStream in = getClass().getClassLoader().getResource("config.properties").openStream();//打开配置文件
            Properties properties=new Properties();
            properties.load(in);
            path = (String) properties.get("path");
            url = (String) properties.get("url");
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JSONObject getPhoto(){
        JSONObject json=new JSONObject();
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
        Date date;
        Calendar calendar=Calendar.getInstance();
        try{
            date=df.parse(day);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<JSONObject> jsonObjects= new ArrayList<>();
        for (int i=0;i<num;i++){
            day=df.format(calendar.getTime());
            if (!check(day+".jpg").equals("")){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("name",day+".jpg");
                jsonObject.put("url",check(day+".jpg"));
                jsonObjects.add(jsonObject);
            }

            calendar.add(Calendar.DATE,-1);
        }
        json.put("photo",jsonObjects);
        json.put("num",jsonObjects.size());
        return json;
    }

    private String check(String photoname){//检查图片是否存在
        File file=new File(path+photoname);
        if (file.exists()){
            return url+photoname;
        }
        else {
            return "";
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
