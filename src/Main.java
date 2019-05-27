

public class Main {

    public static void main(String[] args) throws Exception {
        GetBingWallpaper getBingWallpaper=new GetBingWallpaper();
        try{

            getBingWallpaper.get_wallpaper(0);//获取今日图片
        }
        catch (Exception e){
            throw e;
        }

    }
}
