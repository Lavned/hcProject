package io.ionic.ylnewapp.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.custom.ViewpagerTransformAnim;
import io.ionic.ylnewapp.view.activity.BitAssesActivity;
import io.ionic.ylnewapp.view.activity.FriendActivity;
import io.ionic.ylnewapp.view.activity.LoginActivity;
import io.ionic.ylnewapp.view.activity.MustSeeActivity;
import io.ionic.ylnewapp.view.activity.RewardActivity;
import io.ionic.ylnewapp.view.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */

@ContentView(R.layout.fragment_one)//加载布局
public class FragmentOne extends BaseFragment {


    public List<View> views;
    private ViewPagerAdapter vpAdapter;

    @ViewInject(R.id.one_viewpager)
    ViewPager one_viewpager;

    @ViewInject(R.id.hm_friend)
    TextView hm_friend;
    @ViewInject(R.id.hm_money)
    TextView hm_money;
    @ViewInject(R.id.hm_reward)
    TextView hm_reward;
    @ViewInject(R.id.hm_see)
    TextView hm_see;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        initViewPager();
        return view;//fragment注解;
    }

    @Event(type = View.OnClickListener.class ,value = {R.id.hm_see,R.id.hm_reward,R.id.hm_money,R.id.hm_friend})
    private void click(View view){
        switch (view.getId()){
            case R.id.hm_friend :
                startActivity(new Intent(getActivity(),FriendActivity.class));
                break;
            case R.id.hm_money :
                startActivity(new Intent(getActivity(),BitAssesActivity.class));
                break;
            case R.id.hm_see :
                startActivity(new Intent(getActivity(),MustSeeActivity.class));
                break;
            case R.id.hm_reward :
                startActivity(new Intent(getActivity(),LoginActivity.class));
                break;
        }
    }


    //初始化中间轮播控件
    private void initViewPager() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        views = new ArrayList<View>();
        //获取View对象
        View view1 = inflater.inflate(R.layout.main_view_items, null);
        View view2 = inflater.inflate(R.layout.main_view_items, null);
        View view3 = inflater.inflate(R.layout.main_view_items, null);
        //view对象放在List<view>中
        views.add(view1);
        views.add(view2);
        views.add(view3);
        //List<view>放在适配器ViewPagerAdapter中
        vpAdapter = new ViewPagerAdapter(views, getActivity());
        //获取ViewPage,设置适配器;
        one_viewpager.setAdapter(vpAdapter);
        one_viewpager.setPageTransformer(true, new ViewpagerTransformAnim());
    }


    class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private List<View> viewlist;
        public ViewPagerAdapter( List<View> views,Context context) {
            this.context = context;
            this.viewlist = views;

        }
        //重新4个方法
        @Override
        public int getCount() {
            return views.size();
        }
        //instantiateItem()：将当前view添加到ViewGroup中，并返回当前View
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
        //destroyItem()：删除当前的View;
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
        //isViewFromObject判断当前的View 和 我们想要的Object(值为View) 是否一样;返回 trun / false;
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

    }

}
