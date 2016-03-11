package com.fxj.CSDNInforSplider.DataParse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fxj.CSDNInforSplider.bean.NewsItem;
import com.fxj.CSDNInforSplider.utils.CommonException;
import com.fxj.CSDNInforSplider.utils.DataUtil;
import com.fxj.CSDNInforSplider.utils.URLUtil;

/**����ҵ������,
 * ���������ݽ�����NewsItem����*/
public class NewsItemBiz {
	
	/**���������ݽ�����NewsItem��������*/
	public List<NewsItem> getNewsItems(int newsType,int currentPage) throws CommonException 
	{
		
		List<NewsItem> newsItems=new ArrayList<NewsItem>();
		/**����newsType�������ͺ�currentPage��ȡҪ�����������ݵ�URL*/
		String urlStr=URLUtil.generateURL(newsType, currentPage);
		
		String htmlStr=DataUtil.doGet(urlStr);
		
		NewsItem newsItem=null;
		
		Document doc=Jsoup.parse(htmlStr);/*��HTML������Document*/
		Elements units=doc.getElementsByClass("unit");/*ͨ�������ķ�ʽ������ȡԪ��*/
		
		for(int i=0;i<units.size();i++)
		{
			newsItem=new NewsItem();
			
			newsItem.setNewsType(newsType);/*����NewsItem�������������*/
			
			Element unit_ele=units.get(i);/*�õ�Elements��������Ϊ��unit����ElementԪ��*/
			
			Element h1_ele=unit_ele.getElementsByTag("h1").get(0);/*�õ���ǩΪ"h1"��Ԫ��*/
			Element h1_a_ele=h1_ele.child(0);/*��ȡ��ǩ��h1���е��ӱ�ǩ��a��*/
			
			String title=h1_a_ele.text();
			String href=h1_a_ele.attr("href");
			
			newsItem.setTitle(title);
			newsItem.setLink(href);
			
			Element h4_ele=unit_ele.getElementsByTag("h4").get(0);/*�õ���ǩΪ"h4"��Ԫ��*/
			Element ago_ele=h4_ele.getElementsByClass("ago").get(0);
			String date=ago_ele.text();/*��ȡ���ŷ�����ʱ��*/
			
			newsItem.setDate(date);
			
			Element dl_ele=unit_ele.getElementsByTag("dl").get(0);/*d1*/
			Element dt_ele=dl_ele.child(0);/*dt*/
			try
			{
			Element dt_a_ele=dt_ele.child(0);/*dt_a*/			
			/*����û��ͼƬ*/
			String imgLink_imgsrc=dt_a_ele.child(0).attr("src");/*��ȡ����ͼƬ�����ӵ�ַ*/
			newsItem.setImgLink(imgLink_imgsrc);/*��������ͼƬ�����ӵ�ַ*/
			}catch(IndexOutOfBoundsException e){		
			}
			
			
			Element dd_ele=dl_ele.child(1);/*dd*/
			String content=dd_ele.text();/*���ż������*/
			
			newsItem.setContent(content);/*�������ż������*/
			
			newsItems.add(newsItem);
		}
		
		
		return newsItems;
	}
}
