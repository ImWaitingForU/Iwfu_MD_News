package com.chan.iwfu_md_news.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chan.iwfu_md_news.MainActivity;
import com.chan.iwfu_md_news.R;
import com.chan.iwfu_md_news.adapter.Tab1RecyclerViewAdapter;
import com.chan.iwfu_md_news.bean.NewsBean;
import com.chan.iwfu_md_news.utils.NetUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends Fragment {

	// 要查询的关键字
	private String q = null;

	private List<NewsBean> newsBeanList;
	private String result;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == NetUtils.GET_DATE_FINISHED) {
				result = (String) msg.obj;
				NetUtils.parseNewsJson(result, newsBeanList);
				initRecyclerView();

				// 如果正在刷新，数据返回完成，关闭spl
				if (swipeRefreshLayout.isRefreshing()) {
					swipeRefreshLayout.setRefreshing(false);
					Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	};

	@ViewInject(R.id.rv_tab1)
	private RecyclerView recyclerView;
	@ViewInject(R.id.spl_tab1)
	private SwipeRefreshLayout swipeRefreshLayout;

	private View view;

	public Tab1Fragment() {
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		newsBeanList = new ArrayList<>();
		NetUtils.getData(q, MainActivity.requestQueue, handler);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_tab1, container, false);
		ViewUtils.inject(this, view);
		initSwipeRefreshLayout();
		return view;
	}

	private void initRecyclerView() {

		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		Tab1RecyclerViewAdapter adapter = new Tab1RecyclerViewAdapter(
				getContext(), newsBeanList);

		recyclerView.setAdapter(adapter);

		// recyclerView.addItemDecoration (new RecyclerView.ItemDecoration () {
		// @Override
		// public void getItemOffsets (Rect outRect, View view, RecyclerView
		// parent, RecyclerView.State
		// state) {
		// if (parent.getChildLayoutPosition (view) != 0) {
		// outRect.top = 5;
		// }
		// }
		// });

	}

	/**
	 * 初始化下拉刷新
	 */
	private void initSwipeRefreshLayout() {
		swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
		swipeRefreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						// 先清空之前的数据，否则会直接添加在后面
						newsBeanList.clear();
						NetUtils.getData(q, MainActivity.requestQueue, handler);
					}
				});
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}
