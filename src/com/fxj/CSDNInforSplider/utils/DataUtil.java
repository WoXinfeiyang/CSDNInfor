package com.fxj.CSDNInforSplider.utils;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.helper.HttpConnection;

/**��ȡURL���ӵ�ַ��ӦHTML���ݹ�����*/
public class DataUtil {
	
	/**����URL���ӵ�ַ��ȡ��Ӧҳ���HTML����*/
	public static String doGet(String urlStr) throws CommonException
	{
		StringBuilder sb =new StringBuilder();
		
		/*ʹ��HttpURLConnection����������Դ����:
		 * 1��ͨ������URL����openConnection()������HttpURLConnection����
		 * 2.����HttpURLConnection�Ĳ�������ͨ��������
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
				is.close();/*������Դ,���������������ر�*/
			}else{
				throw new CommonException("��������ʧ��!");
			}
			
			
		}catch(Exception e){
			throw new CommonException("��������ʧ��!");
		}
		return sb.toString();
	}
}
