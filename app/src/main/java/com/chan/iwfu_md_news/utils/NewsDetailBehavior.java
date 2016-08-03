package com.chan.iwfu_md_news.utils;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Chan on 2016/6/6.
 *
 * 自定义新闻详情的布局行为
 */
public class NewsDetailBehavior
		extends
			CoordinatorLayout.Behavior<FloatingActionButton> {

	private static float  degress=10;

	public NewsDetailBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	/**
	 * 参数1-CoordinatorLayout布局
	 *     2-传入的泛型，即具体要用到这个Behavior的控件
	 *     3-View dependency:当前用到这个Behavior的控件是依附于哪个View的
	 *
	 * 返回值：一般写成 dependency instanceof XXX;为布尔值
	 */
	public boolean layoutDependsOn(CoordinatorLayout parent,
			FloatingActionButton child, View dependency) {
		return dependency instanceof AppBarLayout;
	}

	@Override
	/**
	 * 如果依赖的控件的位置或者大小发生改变，使用Viewcompat操控使用这个Behavior的控件进行对应的变化
	 */
	public boolean onDependentViewChanged(CoordinatorLayout parent,
			FloatingActionButton child, View dependency) {
		degress+=10;
		ViewCompat.setRotation (child,degress);
		return super.onDependentViewChanged(parent, child, dependency);
	}
}
