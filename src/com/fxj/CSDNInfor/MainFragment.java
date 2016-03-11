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

/**�Զ���Fragment*/
public class MainFragment extends Fragment implements IXListViewRefreshListener, IXListViewLoadMore
{
	/**Ĭ����������*/
	private int newsType=NewsType.NEWS_TYPE_YEJIE;
	/**��ǰҳ��*/
	private int currentPage=1;
	
	/**������ˢ�µ���չListView����*/
	private XListView mListView;
	/**����XListView��Adapter*/
	NewsItemAdapter mItemAdapter;
	
	/**ҵ�������,��HTML���ݽ�����NewsItem*/
	private NewsItemBiz biz;
	
	/**NewsItem����*/
	private List<NewsItem> mDatas=new ArrayList<NewsItem>();
	
	/**������*/
	Context ct;
	
	public MainFragment(int newsType,Context ct) {
		this.newsType = newsType;
		this.biz=new NewsItemBiz();
		this.ct=ct;
	}

	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*��ʼ������XListView��Adapter*/
		this.mItemAdapter=new NewsItemAdapter(getActivity(), this.mDatas);
		
		/*��ȡ�����ļ��е�XListView�ؼ�*/
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
		new LoadDatasTask().execute();/*ִ��һ���첽����,��Ҫ�ڴ����е��ô˷���,�����첽�����ִ��*/
	}
	
	/**ʹ��NewsItemBiz�����첽����*/
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
		
		/*����̨��������ʱ���˷������ᱻ����*/
		@Override
		protected void onPostExecute(Void result) {
			/*��������NewsItem�������ݼӵ�����XListView��Adapter������*/
			mItemAdapter.addAll(mDatas);
			mItemAdapter.notifyDataSetChanged();
			mListView.stopRefresh();
		}
	}
	
}
