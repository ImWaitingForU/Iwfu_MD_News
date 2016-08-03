package com.chan.iwfu_md_news;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chan.iwfu_md_news.adapter.ViewPagerAdapter;
import com.chan.iwfu_md_news.fragment.Tab1Fragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

	@ViewInject(R.id.toolbar)
	private Toolbar toolbar;
	@ViewInject(R.id.navigationView)
	private NavigationView navigationView;
	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;
	@ViewInject(R.id.drawerLayout)
	private DrawerLayout drawerLayout;
	@ViewInject(R.id.tabLayout)
	private TabLayout tabLayout;

	private List<Fragment> fragmentList;

	private String[] tabTitleArray = {"要闻", "英雄联盟", "守望先锋", "NBA", "程序员", "电竞",
			"经济"};

	/* volley-用于网络请求的队列 */
	public static RequestQueue requestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestQueue = Volley.newRequestQueue(this);
		ViewUtils.inject(this);
		initToolbar();
		initNavigationView();
		initViewPager();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.appbar_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void initNavigationView() {
		navigationView.inflateMenu(R.menu.navigation_menu);
		navigationView.setBackgroundColor(getResources().getColor(
				R.color.colorPrimary));
		navigationView.setItemIconTintList(null);
		// 设置监听，返回false则navigation的item不可以被选择,返回true则可以被选择
		navigationView
				.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(MenuItem item) {
						return true;
					}
				});

	}

	private void initToolbar() {
		toolbar.setTitle("MD新闻");
		setSupportActionBar(toolbar);
		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
				this, drawerLayout, toolbar, R.string.open_drawer,
				R.string.close_drawer);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
	}

	private void initViewPager() {

		/* 先初始化tab再初始化viewpager */


//		for (int i = 0; i < tabTitleArray.length; i++) {
//			tabLayout.addTab(tabLayout.newTab().setText(tabTitleArray[i]));
//		}

		fragmentList = new ArrayList<>();
		Tab1Fragment fragment1 = new Tab1Fragment();
		fragment1.setQ(tabTitleArray[0]);
		Tab1Fragment fragment2 = new Tab1Fragment();
		fragment2.setQ(tabTitleArray[1]);
		Tab1Fragment fragment3 = new Tab1Fragment();
		fragment3.setQ(tabTitleArray[2]);
		Tab1Fragment fragment4 = new Tab1Fragment();
		fragment4.setQ(tabTitleArray[3]);
		Tab1Fragment fragment5 = new Tab1Fragment();
		fragment5.setQ(tabTitleArray[4]);
		Tab1Fragment fragment6 = new Tab1Fragment();
		fragment6.setQ(tabTitleArray[5]);
		Tab1Fragment fragment7 = new Tab1Fragment();
		fragment7.setQ(tabTitleArray[6]);

		fragmentList.add(fragment1);
		fragmentList.add(fragment2);
		fragmentList.add(fragment3);
		fragmentList.add(fragment4);
		fragmentList.add(fragment5);
		fragmentList.add(fragment6);
		fragmentList.add(fragment7);

		ViewPagerAdapter adapter = new ViewPagerAdapter(
				getSupportFragmentManager(), this, fragmentList, tabTitleArray);
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager);


	}

}
