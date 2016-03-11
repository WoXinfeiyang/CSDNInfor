package com.fxj.CSDNInforSplider.utils;

/**��ȡ�������Ͷ�Ӧ��URL*/
public class URLUtil {
	
	public static final String NEWS_LIST_URL = "http://www.csdn.net/headlines.html";
	/**���ƶ���*/
	public static final String NEWS_LIST_URL_YIDONG = "http://mobile.csdn.net/mobile";
	/**���з���*/
	public static final String NEWS_LIST_URL_YANFA = "http://sd.csdn.net/sd";
	/**���Ƽ��㡱*/
	public static final String NEWS_LIST_URL_YUNJISUAN = "http://cloud.csdn.net/cloud";
	/**������Ա��־��*/
	public static final String NEWS_LIST_URL_ZAZHI = "http://programmer.csdn.net/programmer";
	/**��ҵ�硱*/
	public static final String NEWS_LIST_URL_YEJIE = "http://news.csdn.net/news";
	
	/**�����������ͺ͵�ǰҳ���ȡ��ӦURL
	 * @param newsType-----------------��������
	 * @param currentPage--------------��ǰҳ��
	 * */
	public static String generateURL(int newsType,int currentPage)
	{
		String urlString = null;
		currentPage=currentPage>0?currentPage:1;/*�޶�currentPage�ķ�Χȷ������ȷ�ķ�Χ��*/
		
		switch(newsType)
		{
		/*����������Ϊ��ҵ�硱ʱ*/
		case NewsType.NEWS_TYPE_YEJIE:
			urlString=NEWS_LIST_URL_YEJIE;
			break;
		
		/*����������Ϊ���ƶ���ʱ*/
		case NewsType.NEWS_TYPE_YIDONG:
			urlString=NEWS_LIST_URL_YIDONG;
			break;			
		
		/*����������Ϊ���з���ʱ*/
		case NewsType.NEWS_TYPE_YANFA:
			urlString=NEWS_LIST_URL_YANFA;
			break;
		
			/*����������Ϊ������Ա��־��ʱ*/
		case NewsType.NEWS_TYPE_CHENGXUYUANZAZHI:
			urlString=NEWS_LIST_URL_ZAZHI;
			break;
		
		/*����������Ϊ���Ƽ��㡱ʱ*/
		case NewsType.NEWS_TYPE_YUNJISUAN:
			urlString=NEWS_LIST_URL_YUNJISUAN;
			break;
		}
		urlString+="/"+currentPage;
		return urlString;
	}
}
