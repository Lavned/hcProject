package io.ionic.ylnewapp.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseFragment;
import io.ionic.ylnewapp.view.twofragment.Tab1Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab2Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab3Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab4Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab5Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab6Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab7Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab8Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_two)
public class FragmentTwo extends BaseFragment{



    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private List<String > mTabTitles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  x.view().inject(this,inflater,container);
        mTabLayout = (TabLayout) view.findViewById(R.id.id_tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewPager);
//        initData();
        return view;

    }




    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initTab();
        }else {

        }
    }



    //初始化Tab
    private void initTab() {
        mFragments = new ArrayList<>();
        mTabTitles = new ArrayList<>();
        mTabTitles.clear();
        mTabLayout.removeAllTabs();
        mTabTitles.add("币余宝");
        mTabTitles.add("智能投顾");
        mTabTitles.add("ETF指数");
        mTabTitles.add("OTC套利");
        mTabTitles.add("BTC指数");
        mTabTitles.add("ICO基金");
        mTabTitles.add("ETF交易");
        mTabTitles.add("BTC交易");
        mFragments.add(new Tab1Fragment());
        mFragments.add(new Tab2Fragment());
        mFragments.add(new Tab3Fragment());
        mFragments.add(new Tab4Fragment());
        mFragments.add(new Tab5Fragment());
        mFragments.add(new Tab6Fragment());
        mFragments.add(new Tab7Fragment());
        mFragments.add(new Tab8Fragment());
        //设置TabLayout的一系列属性
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        mTabLayout.setTabTextColors(Color.parseColor("#333333"), Color.parseColor("#FEA620"));
        mTabLayout.removeAllTabs();
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(4)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(5)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(6)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabTitles.get(7)));

            mAdapter = new ViewPagerAdapter(getChildFragmentManager());
            if(mViewPager.getAdapter() == null)
                mViewPager.setAdapter(mAdapter);
            mTabLayout.setupWithViewPager(mViewPager);


//        mTabLayout.setTabsFromPagerAdapter(mAdapter);
//
//        //设置Tab监听
//        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager.setCurrentItem(tab.getPosition());
//                Log.e("TAG", "Selected Tab Index为：" + tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                Log.e("TAG", "Unselected Tab Index为：" + tab.getPosition());
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                Log.e("TAG", "Reselected Tab Index为：" + tab.getPosition());
//            }
//        });


    }



    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        FragmentManager fm;
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm = fm;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fm.beginTransaction().show(fragment).commit();
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Fragment fragment = mFragments.get(position);
            fm.beginTransaction().hide(fragment).commit();
        }



        @Override
        public Fragment getItem(int position) {
            Log.e("TAG", "获取的Fragement的索引值为：" + position);

            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.e("TAG", "返回Tab的标题");
            return mTabTitles.get(position);
        }
        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }
    }



    /* private void iniview() {
        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFFFEA620,Color.GRAY).setSize(selectSize, unSelectSize));
        scrollIndicatorView.setScrollBar(new ColorBar(getActivity(), 0xFFFEA620, 4));
        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new TbViewAdapter());
    }

    private class TbViewAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"111", "222", "112", "222", "1221", "12121", "121212", "Jelly Bean", "4454"};
        private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶", "姜饼", "蜂巢", "冰激凌三明治", "果冻豆", "奇巧巧克力棒"};

        @Override
        public int getCount() {
            return versions.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_text, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(versions[position]);

            int witdh = getTextWidth(textView);
            int padding = DisplayUtil.dipToPix(getActivity().getApplicationContext(), 8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.3f) + padding);

            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.two_main, container, false);
                findView(convertView);
                initData();
                initRefreshLayout();
                initRecyclerView();
            }
            return convertView;
        }



        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }

    }
*/



}
