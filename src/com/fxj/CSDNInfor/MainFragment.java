package com.fxj.CSDNInfor;

import java.util.ArrayList;
import java.util.List;

import com.fxj.CSDNInforSplider.DataParse.NewsItemBiz;
import com.fxj.CSDNInforSplider.bean.NewsItem;
import com.fxj.CSDNInforSplider.utils.CommonException;
import com.fxj.CSDNInforSplider.utils.NewsType;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**自定义Fragment*/
public class MainFragment extends Fragment implements IXListViewRefreshListener, IXListViewLoadMore
{
	/**默认新闻类型*/
	private int newsType=NewsType.NEWS_TYPE_YEJIE;
	/**当前页面*/
	private int currentPage=1;
	
	/**带下拉刷新的扩展ListView对象*/
	private XListView mListView;
	/**适配XListView的Adapter*/
	NewsItemAdapter mItemAdapter;
	
	/**业务类对象,将HTML数据解析成NewsItem*/
	private NewsItemBiz biz;
	
	/**NewsItem集合*/
	private List<NewsItem> mDatas=new ArrayList<NewsItem>();
	
	/**上下文*/
	Context ct;
	
	public MainFragment(int newsType,Context ct) {
		this.newsType = newsType;
		this.biz=new NewsItemBiz();
		this.ct=ct;
	}

	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*初始化适配XListView的Adapter*/
		this.mItemAdapter=new NewsItemAdapter(getActivity(), this.mDatas);
		
		/*获取布局文件中的XListView控件*/
		this.mListView=(XListView) getView().findViewById(R.id.id_xlistView);
		
		this.mListView.setAdapter(mItemAdapter);
		
		this.mListView.setPullRefreshEnable(this);
		this.mListView.setPullLoadEnable(this);
		this.mListView.startRefresh();
		
		this.mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
				List<NewsItem> newsItems=mItemAdapter.getAll();
				NewsItem item=newsItems.get(position-1);
				
				String urlString=item.getLink();
				
				Intent intent=new Intent(ct,NewsDetailActivity.class);
				
				intent.putExtra("URL",urlString);
				
				startActivity(intent);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_item_fragment_main,null);
		
//		TextView tip=(TextView) view.findViewById(R.id.id_tip);
//		tip.setText(TabAdapter.Titles[this.newsType]);
		return view;
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		new LoadDatasTask().execute();/*执行一个异步任务,需要在代码中调用此方法,触发异步任务的执行*/
	}
	
	/**使用NewsItemBiz建立异步任务*/
	class LoadDatasTask extends AsyncTask<Void,Void,Void>
	{
		@Override
		protected Void doInBackground(Void... params) {
			
			try {
				List<NewsItem> newsItems=biz.getNewsItems(newsType, currentPage);
				mDatas=newsItems;
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		
		/*当后台操作结束时，此方法将会被调用*/
		@Override
		protected void onPostExecute(Void result) {
			/*将解析到NewsItem集合数据加到适配XListView的Adapter对象中*/
			mItemAdapter.addAll(mDatas);
			mItemAdapter.notifyDataSetChanged();
			mListView.stopRefresh();
		}
	}
	
}
