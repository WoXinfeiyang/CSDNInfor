package com.fxj.CSDNInforSplider.utils;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.helper.HttpConnection;

/**获取URL链接地址对应HTML数据工具类*/
public class DataUtil {
	
	/**根据URL链接地址获取对应页面的HTML数据*/
	public static String doGet(String urlStr) throws CommonException
	{
		StringBuilder sb =new StringBuilder();
		
		/*使用HttpURLConnection访问网络资源步骤:
		 * 1、通过调用URL对象openConnection()来创建HttpURLConnection对象
		 * 2.设置HttpURLConnection的参数和普通请求属性
		 * */
		try
		{
			URL url=new URL(urlStr);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			if(conn.getResponseCode()==200)
			{
				InputStream is=conn.getInputStream();
				int len=0;
				byte[] buf=new byte[1024];
				while((len=is.read(buf))!=-1)
				{
					sb.append(new String(buf, 0, len,"utf-8"));				
				}			
				is.close();/*回收资源,将输入输出流对象关闭*/
			}else{
				throw new CommonException("访问网络失败!");
			}
			
			
		}catch(Exception e){
			throw new CommonException("访问网络失败!");
		}
		return sb.toString();
	}
}
