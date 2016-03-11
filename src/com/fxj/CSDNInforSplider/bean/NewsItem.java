package com.fxj.CSDNInforSplider.bean;

/**ListView����XListView�б����н�����HTML���ݶ���*/
public class NewsItem {
	private int id;
	/**����*/
	private String title;
	/**����*/
	private String link;
	/**���ŷ�������*/
	private String date;
	/**ͼƬ����*/
	private String imgLink;
	/**���ż������*/
	private String content;
	/**��������*/
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
