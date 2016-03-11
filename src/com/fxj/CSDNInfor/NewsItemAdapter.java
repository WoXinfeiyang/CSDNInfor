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

/**��������XListView��Adapter,��NewsItem�������XListView*/
public class NewsItemAdapter extends BaseAdapter {

	/**Layout�ֲ�������*/
	private LayoutInflater mInflater;
	/**NewsItem����*/
	private List<NewsItem> mDatas;
	
	/**ʹ�ÿ�Դ���ImageLoader����������*/
	private ImageLoader imageLoader=ImageLoader.getInstance();
	/**ͼƬ��ʾ����ѡ��*/
	private DisplayImageOptions options;
		
	/**��������XListView��Adapter,��NewsItem�������XListView
	 * @param context----------������
	 * @param datas------------NewsItem����
	 * */
	public NewsItemAdapter(Context context,List<NewsItem> datas) 
	{
		this.mDatas=datas;
		
		this.mInflater=LayoutInflater.from(context);/*�Ӹ������������еõ�һ�����ּ�����*/
		
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
			/*����ListView�б���Ĳ���*/
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
		
		NewsItem newsItem=this.mDatas.get(position);/*��NewsItem������ȡ��NewsItemԪ��*/
		
		/*��ListView�б��������������������*/
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
		/**���ű���*/
		TextView mTitle;
		/**����ͼƬ*/
		ImageView mImage;
		/**���ż������*/
		TextView mContent;
		/**���ŷ�������*/
		TextView mDate;
	}
}
