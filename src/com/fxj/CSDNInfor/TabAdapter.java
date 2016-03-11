package com.fxj.CSDNInfor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**继承自FragmentPagerAdapter适配ViewPager的Adapter,
 * 用MainFragment来适配ViewPager的Adapter
 * */
public class TabAdapter extends FragmentPagerAdapter {

	public static String[] Titles=new String[]{"业界", "移动", "研发", "程序员杂志", "云计算"};
	
	/**上下文*/
	private Context context;
	
	/**继承自FragmentPagerAdapter适配ViewPager的Adapter,
	 * 用MainFragment来适配ViewPager的Adapter
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
