package com.chan.iwfu_md_news.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.chan.iwfu_md_news.bean.NewsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Chan 2016/5/27.
 * <p/>
 * 用于网络请求
 */
public class NetUtils {

    private static String news_url = "http://op.juhe.cn/onebox/news/query?key=3bef93d08191d2b6d9be614c4291e1c2&q=";
    public static int GET_DATE_FINISHED = 0x123;

    /**
     * 从网络获取数据
     *
     * @param q 检索新闻的关键字
     */
    public static void getData (String q, RequestQueue requestQueue, final Handler handler) {
        Log.d ("tagurl","要查询的url:"+news_url+q);

        StringRequest stringRequest = new StringRequest (news_url + q, new Response.Listener<String> () {
            @Override
            public void onResponse (String response) {
                Log.d ("tag",response);
                Message message = Message.obtain ();
                message.obj = response;
                message.what = GET_DATE_FINISHED;
                handler.sendMessage (message);
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse (VolleyError error) {
                Log.d ("tag", error + "");
            }
        });

        requestQueue.add (stringRequest);
    }

    /**
     * 解析新闻Json
     */
    public static void parseNewsJson (String result, List<NewsBean> newsList) {
        try {
            JSONObject jsonObject = new JSONObject (result);
            if (jsonObject.getInt ("error_code") == 0) {
                //如果返回成功，继续解析
                JSONArray resultArray = jsonObject.getJSONArray ("result");
                for (int i = 0; i < resultArray.length (); i++) {
                    JSONObject resultObject = resultArray.getJSONObject (i);
                    NewsBean bean = new NewsBean ();
                    bean.setTitle (resultObject.getString ("title"));
                    bean.setContent (resultObject.getString ("content"));
                    bean.setFull_title (resultObject.getString ("full_title"));
                    bean.setPdate (resultObject.getString ("pdate"));
                    bean.setSrc (resultObject.getString ("src"));
                    bean.setImg (resultObject.getString ("img"));
                    bean.setUrl (resultObject.getString ("url"));
                    bean.setPdate_src (resultObject.getString ("pdate_src"));
                    newsList.add (bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace ();
        }

    }

}
