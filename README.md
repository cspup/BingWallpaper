# 一个java小程序，用来获取必应每日壁纸。
+ Bing壁纸官方接口 https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1 ,可以获取idx天前n张图片，图片仅可用于桌面壁纸。  
+ 核心代码是GetBingWallper.java,用来根据bing接口获取每日图片或获取最近7天图片,可设置存储路径，不设置存储则在运行路径下。   
+ BingWallpape.jar是带有简单GUI的客户端，从http://wallpaper.yemuc.xyz 获取图片。  
# 自己写的网络api接口说明
+ 网址： http://api.wallpaper.yemuc.xyz/
+ 请求方式：GET
+ 响应内容：json字符串 例：{"photo":[{"name":"20190501.jpg","url":"http://wallpaper.yemuc.xyz/20190501.jpg"},
{"name":"20190502.jpg","url":"http://wallpaper.yemuc.xyz/20190502.jpg"}]} 
+ 参数说明：day--要获取图片的起始日期 num--获取图片张数
+ 例：获取2019年5月1号之前的5张图片 http://api.wallpaper.yemuc.xyz/?day=20190501&num=5
