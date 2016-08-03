package com.chan.iwfu_md_news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chan.iwfu_md_news.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

	@ViewInject(R.id.toolbar_newsDetail)
	private Toolbar toolbar_newsDetail;
	// @ViewInject(R.id.tv_news_detail)
	// private TextView tv_news_detail;
	// @ViewInject(R.id.tv_src_news_detail)
	// private TextView tv_src_news_detail;
	// @ViewInject(R.id.tv_pdatesrc_news_detail)
	// private TextView tv_pdatesrc_news_detail;
	@ViewInject(R.id.iv_news_detail)
	private ImageView imageView;
	@ViewInject(R.id.webView)
	private WebView webView;

	private RequestQueue requestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		requestQueue = Volley.newRequestQueue(this);
		ViewUtils.inject(this);
		initData();
	}

	private void initData() {

		Intent intent = getIntent();

		String img = intent.getStringExtra("img");
		String text = intent.getStringExtra("content");
		String src = intent.getStringExtra("src");
		String url = intent.getStringExtra("url");
		String pdate_src = intent.getStringExtra("pdate_src");

		webView.loadUrl(url);
		// 自适应屏幕
		// webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		// webView.getSettings().setLoadWithOverviewMode(true);
		// 设置加载进来的页面自适应手机屏幕
		WebSettings settings = webView.getSettings();
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);

		// 标题
		String fullTitle = intent.getStringExtra("fullTitle");
		toolbar_newsDetail.setTitle(fullTitle);
		setSupportActionBar(toolbar_newsDetail);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// 图片
		if (!img.equals("")) {
			Picasso.with(this).load(img).placeholder(R.mipmap.jiazai)
					.error(R.mipmap.shibai).into(imageView);
		}
		//
		// // 来源
		// tv_src_news_detail.setText(src);
		// // 时间
		// tv_pdatesrc_news_detail.setText(pdate_src);
		// // 内容
		// // tv_news_detail.setText(text);
		// Log.d("aaaanews", url);
		//
		// if (url != null) {
		//
		// StringRequest request = new StringRequest(url,
		// new Response.Listener<String>() {
		// @Override
		// public void onResponse(String response) {
		// Log.d("aaaanews", response);
		// tv_news_detail.setText(Html.fromHtml(response));
		// }
		// }, new Response.ErrorListener() {
		// @Override
		// public void onErrorResponse(VolleyError error) {
		// Log.d("aaaanews", error + "");
		// }
		// });
		//
		// requestQueue.add(request);
		// } else {
		// Snackbar.make(tv_news_detail, "网页为空，无数据返回",
		// Snackbar.LENGTH_INDEFINITE);
		// }
	}

}
