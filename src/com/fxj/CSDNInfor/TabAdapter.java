package com.fxj.CSDNInfor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**�̳���FragmentPagerAdapter����ViewPager��Adapter,
 * ��MainFragment������ViewPager��Adapter
 * */
public class TabAdapter extends FragmentPagerAdapter {

	public static String[] Titles=new String[]{"ҵ��", "�ƶ�", "�з�", "����Ա��־", "�Ƽ���"};
	
	/**������*/
	private Context context;
	
	/**�̳���FragmentPagerAdapter����ViewPager��Adapter,
	 * ��MainFragment������ViewPager��Adapter
	 * */
	public TabAdapter(FragmentManager fm,Context context) {
		super(fm);
		this.context=context;
	}

	@Override
	public Fragment getItem(int arg0) {
		MainFragment fragment=new MainFragment(arg0+1,this.context);
		return fragment;
	}

	
	@Override
	public CharSequence getPageTitle(int position) {
		return this.Titles[position%Titles.length];
	}

	@Override
	public int getCount() {
		return this.Titles.length;
	}

}
