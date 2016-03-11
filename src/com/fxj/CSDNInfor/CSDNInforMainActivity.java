package com.fxj.CSDNInfor;

import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.Activity;


/**
 * �ļ�����:CSDN��ѶAPP
 * ʱ�䣺2015-10-7 13:15
 * ˵����1��UI���:TabPageIndicator+ViewPager+Fragment+XListView
 * �������к���һ��TabPageIndicator��ViewPager,����ViewPager��Adapter��Fragment����䣬
 * ��Fragment�к���XListView
 * 2��ʹ��HttpURLConnection����������Դ����:
 * a��ͨ������URL����openConnection()������HttpURLConnection����
 * b������HttpURLConnection�Ĳ�������ͨ��������
 * 3������ʹ��AsyncTaskִ���첽����
 * 4��Context�ṩ��һ��Ӧ�õ����л�������Context�Ĵ󻷾��
 * Ӧ�òſ��Է�����Դ��������ɺ��������������Ľ���
 * 5��AndroidӦ��Ҫ������Ӧ�ó��������Activity��Service��ContentProvider��BroadcastReceiver��
 * ��������ʾ��������.
 * */

public class CSDNInforMainActivity extends FragmentActivity {
	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	/**����ViewPager��Adapter*/
	private TabAdapter tabAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.mIndicator=(TabPageIndicator) this.findViewById(R.id.mainPager_indicator);
		this.mViewPager=(ViewPager) this.findViewById(R.id.mainPager_viewPager);
		
		this.tabAdapter=new TabAdapter(getSupportFragmentManager(),getApplicationContext());
		this.mViewPager.setAdapter(tabAdapter);
		
		this.mIndicator.setViewPager(mViewPager,0);/*��TabPageIndicator������ViewPager�����*/
	}


}
