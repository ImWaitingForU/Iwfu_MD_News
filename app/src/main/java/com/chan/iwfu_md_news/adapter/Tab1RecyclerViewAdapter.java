package com.chan.iwfu_md_news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chan.iwfu_md_news.R;
import com.chan.iwfu_md_news.activity.NewsDetailActivity;
import com.chan.iwfu_md_news.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Chan on 2016/5/27.
 *
 * tab1的RecyclerView适配器
 */
public class Tab1RecyclerViewAdapter
		extends
			RecyclerView.Adapter<Tab1RecyclerViewAdapter.MyViewHolder> {

	private Context context;
	private List<NewsBean> beanList;

	public Tab1RecyclerViewAdapter(Context context, List<NewsBean> beanList) {
		this.context = context;
		this.beanList = beanList;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.tab1_list_item, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, final int position) {
		String img = beanList.get(position).getImg();
		if (img.equals("")) {
			holder.iv_img.setImageResource(R.mipmap.shibai);
		} else {
			Picasso.with(context).load(img).into(holder.iv_img);
		}

		holder.tv_title.setText(beanList.get(position).getTitle());
		holder.tv_time.setText(beanList.get(position).getPdate());

		holder.cv_item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, NewsDetailActivity.class);
				intent.putExtra ("url",beanList.get (position).getUrl ());
				intent.putExtra ("img",beanList.get (position).getImg ());
				intent.putExtra ("content",beanList.get (position).getContent ());
				intent.putExtra ("fullTitle",beanList.get (position).getFull_title ());
				intent.putExtra ("src",beanList.get (position).getSrc ());
				intent.putExtra ("pdate_src",beanList.get (position).getPdate_src ());
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return beanList.size();
	}

	public static class MyViewHolder extends RecyclerView.ViewHolder {

		ImageView iv_img;
		TextView tv_title;
		TextView tv_time;
		CardView cv_item;

		public MyViewHolder(View itemView) {
			super(itemView);

			tv_title = (TextView) itemView.findViewById(R.id.tv_title_tv_item);
			tv_time = (TextView) itemView.findViewById(R.id.tv_time_tv_item);
			iv_img = (ImageView) itemView.findViewById(R.id.iv_rv_item);
			cv_item = (CardView) itemView.findViewById(R.id.cv_tab1_item);
		}
	}
}
