package com.fxj.CSDNInfor;

import java.util.List;

import com.fxj.CSDNInforSplider.bean.NewsItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**定义适配XListView的Adapter,用NewsItem集合填充XListView*/
public class NewsItemAdapter extends BaseAdapter {

	/**Layout局部加载器*/
	private LayoutInflater mInflater;
	/**NewsItem集合*/
	private List<NewsItem> mDatas;
	
	/**使用开源框架ImageLoader来加载数据*/
	private ImageLoader imageLoader=ImageLoader.getInstance();
	/**图片显示配置选项*/
	private DisplayImageOptions options;
		
	/**定义适配XListView的Adapter,用NewsItem集合填充XListView
	 * @param context----------上下文
	 * @param datas------------NewsItem集合
	 * */
	public NewsItemAdapter(Context context,List<NewsItem> datas) 
	{
		this.mDatas=datas;
		
		this.mInflater=LayoutInflater.from(context);/*从给定的上下文中得到一个布局加载器*/
		
		this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		this.options = new DisplayImageOptions.Builder().showStubImage(R.drawable.images)
				.showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
				.build();
	}

	
	public void addAll(List<NewsItem> datas)
	{
		this.mDatas.addAll(datas);
	}
	
	public List<NewsItem> getAll()
	{
		return this.mDatas;
	}
	
	@Override
	public int getCount() {
		return this.mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return this.mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=null;
		if(convertView==null)
		{
			/*加载ListView列表项的布局*/
			convertView=this.mInflater.inflate(R.layout.news_item_layout,null);
			
			holder=new ViewHolder();
			
			holder.mTitle=(TextView)convertView.findViewById(R.id.newsItem_title);
			holder.mImage=(ImageView) convertView.findViewById(R.id.newsItem_newsImg);
			holder.mContent=(TextView) convertView.findViewById(R.id.newsItem_content);
			holder.mDate=(TextView) convertView.findViewById(R.id.newsItem_date);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		NewsItem newsItem=this.mDatas.get(position);/*从NewsItem集合中取出NewsItem元素*/
		
		/*给ListView列表项设置其中组件的内容*/
		holder.mTitle.setText(newsItem.getTitle());
		holder.mContent.setText(newsItem.getContent());
		holder.mDate.setText(newsItem.getDate());
		
		if(newsItem.getImgLink()!=null)
		{
			holder.mImage.setVisibility(View.VISIBLE);
			
			this.imageLoader.displayImage(newsItem.getImgLink(),holder.mImage, this.options);
		}else{
			holder.mImage.setVisibility(View.GONE);
		}
		
		return convertView;
	}

	private final class ViewHolder
	{
		/**新闻标题*/
		TextView mTitle;
		/**新闻图片*/
		ImageView mImage;
		/**新闻简介内容*/
		TextView mContent;
		/**新闻发布日期*/
		TextView mDate;
	}
}
