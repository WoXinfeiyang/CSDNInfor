package com.fxj.CSDNInforSplider.bean;

/**ListView或者XListView列表项中解析后HTML数据对象*/
public class NewsItem {
	private int id;
	/**标题*/
	private String title;
	/**链接*/
	private String link;
	/**新闻发布日期*/
	private String date;
	/**图片链接*/
	private String imgLink;
	/**新闻简介内容*/
	private String content;
	/**新闻类型*/
	private int newsType;
	
	public int getNewsType() {
		return newsType;
	}
	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString()
	{
		return "NewsItem [id="+this.id+",title="+this.title+",date="+this.date+",newsType="+this.newsType+"," +
				"link="+this.link+",imgLink="+this.imgLink+"]\n";		
	}
}
