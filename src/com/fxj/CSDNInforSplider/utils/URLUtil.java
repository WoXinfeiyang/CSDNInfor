package com.fxj.CSDNInforSplider.utils;

/**获取新闻类型对应的URL*/
public class URLUtil {
	
	public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
	/**“移动”*/
	public static final String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
	/**“研发”*/
	public static final String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
	/**“云计算”*/
	public static final String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
	/**“程序员杂志”*/
	public static final String NEWS_LIST_URL_ZAZHI = "http://programmer.csdn.net/programmer";
	/**“业界”*/
	public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";
	
	/**根据新闻类型和当前页码获取对应URL
	 * @param newsType-----------------新闻类型
	 * @param currentPage--------------当前页码
	 * */
	public static String generateURL(int newsType,int currentPage)
	{
		String urlString = null;
		currentPage=currentPage>0?currentPage:1;/*限定currentPage的范围确保在正确的范围内*/
		
		switch(newsType)
		{
		/*当新闻类型为“业界”时*/
		case NewsType.NEWS_TYPE_YEJIE:
			urlString=NEWS_LIST_URL_YEJIE;
			break;
		
		/*当新闻类型为“移动”时*/
		case NewsType.NEWS_TYPE_YIDONG:
			urlString=NEWS_LIST_URL_YIDONG;
			break;			
		
		/*当新闻类型为“研发”时*/
		case NewsType.NEWS_TYPE_YANFA:
			urlString=NEWS_LIST_URL_YANFA;
			break;
		
			/*当新闻类型为“程序员杂志”时*/
		case NewsType.NEWS_TYPE_CHENGXUYUANZAZHI:
			urlString=NEWS_LIST_URL_ZAZHI;
			break;
		
		/*当新闻类型为“云计算”时*/
		case NewsType.NEWS_TYPE_YUNJISUAN:
			urlString=NEWS_LIST_URL_YUNJISUAN;
			break;
		}
		urlString+="/"+currentPage;
		return urlString;
	}
}
