import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/*
获取壁纸
 */
public class GetBingWallpaper {
    private String file_path="";//文件路径默认为空

    public void setFile_path(String file_path) {//设置路径
        this.file_path = file_path;
    }

    public String getFile_path() {
        return file_path;
    }

    public void get_wallpaper(int idx) throws Exception {//获取idx天前的图片，Bing接口支持7最近7天
        String json=get_json(idx);
        if (json!=null){
//            System.out.println(json);//显示返回数据
            download_paper(get_down_url(json),get_file_name(json));
        }
        else{
            System.out.println("获取失败！");
        }
    }
    public String get_json(int idx){//获取前idx天json
        try {
            URL url = new URL("http://cn.bing.com/HPImageArchive.aspx?format=js&idx="+idx+"&n=1");//bing每日图片接口返回值是json，idx:今日之前天数
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestMethod("GET");
            InputStream inputStream = conn.getInputStream();

            String json = "";
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bf.readLine()) != null) {//一行一行的读返回的json
                json += line;
            }
            inputStream.close();
            return json;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void download_paper(URL url,String filename) {//下载图片
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//建立连接
            conn.setRequestMethod("GET");//GEt方式
            conn.setConnectTimeout(1000);//设置超时
            InputStream inputStream = conn.getInputStream();//输入流
            byte[] data = readInputStream(inputStream);
            File file = new File(filename);//建立图片
            FileOutputStream outputStream = new FileOutputStream(file);//建立文件输出流
            outputStream.write(data);//写入文件
            outputStream.close();//关闭输出流
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static byte[] readInputStream(InputStream inStream) throws Exception{//从输入流中读取图片

            ByteArrayOutputStream outputStream = new ByteArrayOutputStreamEx();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
        return outputStream.toByteArray();
    }
    public String get_file_name(String json){//获取文件名（以日期命名）
        String filename=json.substring(json.indexOf("enddate")+10,json.indexOf("enddate")+18);
        return file_path+filename+".jpg";//返回设置的路径加文件名，如果没有设置路径保存到运行目录下
    }
    public URL get_down_url(String json) throws MalformedURLException {//从获取到的json里获取图片url
        String paper_link;
        paper_link=json.substring(json.indexOf("url")+6,json.indexOf(".jpg")+4);//在json中找出url
        if (paper_link.contains("https://")){//json中的地址可能有两种格式，这做了判断
            paper_link=paper_link.replace("https","http");
            URL down_link=new URL(paper_link);
            return down_link;
        }
        else {
            URL down_link = new URL("http://s.cn.bing.net" + paper_link);
            return down_link;
        }
    }
}
