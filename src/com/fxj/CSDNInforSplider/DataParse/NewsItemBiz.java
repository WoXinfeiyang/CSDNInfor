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

/**解析业务处理类,
 * 将网络数据解析成NewsItem数据*/
public class NewsItemBiz {
	
	/**将网络数据解析成NewsItem集合数据*/
	public List<NewsItem> getNewsItems(int newsType,int currentPage) throws CommonException 
	{
		
		List<NewsItem> newsItems=new ArrayList<NewsItem>();
		/**根据newsType新闻类型和currentPage获取要访问网络数据的URL*/
		String urlStr=URLUtil.generateURL(newsType, currentPage);
		
		String htmlStr=DataUtil.doGet(urlStr);
		
		NewsItem newsItem=null;
		
		Document doc=Jsoup.parse(htmlStr);/*将HTML解析成Document*/
		Elements units=doc.getElementsByClass("unit");/*通过类名的方式遍历获取元素*/
		
		for(int i=0;i<units.size();i++)
		{
			newsItem=new NewsItem();
			
			newsItem.setNewsType(newsType);/*设置NewsItem对象的新闻类型*/
			
			Element unit_ele=units.get(i);/*得到Elements单个类名为“unit”的Element元素*/
			
			Element h1_ele=unit_ele.getElementsByTag("h1").get(0);/*得到标签为"h1"的元素*/
			Element h1_a_ele=h1_ele.child(0);/*获取标签“h1”中的子标签“a”*/
			
			String title=h1_a_ele.text();
			String href=h1_a_ele.attr("href");
			
			newsItem.setTitle(title);
			newsItem.setLink(href);
			
			Element h4_ele=unit_ele.getElementsByTag("h4").get(0);/*得到标签为"h4"的元素*/
			Element ago_ele=h4_ele.getElementsByClass("ago").get(0);
			String date=ago_ele.text();/*获取新闻发布的时间*/
			
			newsItem.setDate(date);
			
			Element dl_ele=unit_ele.getElementsByTag("dl").get(0);/*d1*/
			Element dt_ele=dl_ele.child(0);/*dt*/
			try
			{
			Element dt_a_ele=dt_ele.child(0);/*dt_a*/			
			/*可能没有图片*/
			String imgLink_imgsrc=dt_a_ele.child(0).attr("src");/*获取新闻图片的链接地址*/
			newsItem.setImgLink(imgLink_imgsrc);/*设置新闻图片的链接地址*/
			}catch(IndexOutOfBoundsException e){		
			}
			
			
			Element dd_ele=dl_ele.child(1);/*dd*/
			String content=dd_ele.text();/*新闻简介内容*/
			
			newsItem.setContent(content);/*设置新闻简介内容*/
			
			newsItems.add(newsItem);
		}
		
		
		return newsItems;
	}
}
