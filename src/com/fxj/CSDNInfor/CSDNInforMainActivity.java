package com.fxj.CSDNInfor;

import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.Activity;


/**
 * 文件名称:CSDN资讯APP
 * 时间：2015-10-7 13:15
 * 说明：1、UI框架:TabPageIndicator+ViewPager+Fragment+XListView
 * 主界面中含有一个TabPageIndicator和ViewPager,适配ViewPager的Adapter用Fragment来填充，
 * 而Fragment中含有XListView
 * 2、使用HttpURLConnection访问网络资源步骤:
 * a、通过调用URL对象openConnection()来创建HttpURLConnection对象
 * b、设置HttpURLConnection的参数和普通请求属性
 * 3、熟练使用AsyncTask执行异步任务
 * 4、Context提供了一个应用的运行环境，在Context的大环境里，
 * 应用才可以访问资源，才能完成和其他组件、服务的交互
 * 5、Android应用要求所有应用程序组件（Activity、Service、ContentProvider、BroadcastReceiver）
 * 都必须显示进行配置.
 * */

public class CSDNInforMainActivity extends FragmentActivity {
	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	/**适配ViewPager的Adapter*/
	private TabAdapter tabAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.mIndicator=(TabPageIndicator) this.findViewById(R.id.mainPager_indicator);
		this.mViewPager=(ViewPager) this.findViewById(R.id.mainPager_viewPager);
		
		this.tabAdapter=new TabAdapter(getSupportFragmentManager(),getApplicationContext());
		this.mViewPager.setAdapter(tabAdapter);
		
		this.mIndicator.setViewPager(mViewPager,0);/*将TabPageIndicator对象与ViewPager对象绑定*/
	}


}
