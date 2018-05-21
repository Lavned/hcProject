package io.ionic.ylnewapp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import io.ionic.ylnewapp.BLApplication;
import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.custom.CustomViewPager;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.activity.LoginActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    private Context context;
    private long mExitTime;//退出计时


    @ViewInject(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @ViewInject(R.id.vp_home)
    CustomViewPager mVpHome;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private boolean isFullScreen = false;





    @Event(type = View.OnClickListener.class ,value = R.id.tv_back)
    private void click(View view){
        startActivity(new Intent(context, LoginActivity.class));//login
//        T.showShort("ff");
//        new CommonDialog.Builder(this)
//                .setTitle("标题")
//                .setMessage("这里是提示内容")
//                .setPositiveButton("确定", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }).setNegativeButton("取消", null).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        context = this;
//        ImmersionBar.with(this).init(); //初始化，默认透明状态栏和黑色导航栏
//        initTopTitle("我是首页啊");
        initNavigatoinBar();
        initFragment();
}

    private void initFragment() {
        mVpHome.setScanScroll(false);
        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
                switch (position) {
                    case 0:
                        isFullScreen = true;
                        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
                        break;
                    default:
                        if (isFullScreen) {
                            resetFragmentView(mFragmentList.get(position));
                        }
                        StatusBarUtil.setColor(MainActivity.this, Color.parseColor("#ffffff"), 225);//
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mVpHome.setOffscreenPageLimit(2);
        mVpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }

            @Override
            public int getItemPosition(Object object) {
                //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
                // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
                return PagerAdapter.POSITION_NONE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                mFragmentList.get(position).onDestroyView();
                super.destroyItem(container, position, object);
            }
        });
    }


    @Override
    protected void setStatusBar() {
        isFullScreen = true;
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
    }

    public void resetFragmentView(Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View contentView = findViewById(android.R.id.content);
            if (contentView != null) {
                ViewGroup rootView;
                rootView = (ViewGroup) ((ViewGroup) contentView).getChildAt(0);
                if (rootView.getPaddingTop() != 0) {
                    rootView.setPadding(0, 0, 0, 0);
                }
            }
            if (fragment.getView() != null) fragment.getView().setPadding(0, getStatusBarHeight(this), 0, 0);
        }
    }

    private static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    //底部导航栏
    private void initNavigatoinBar() {
        TextBadgeItem badgeItem = new TextBadgeItem();//添加标记
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBorderWidth(0);

        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.h_home, getString(R.string.item_home)).setInactiveIconResource(R.drawable.h_home_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1)
//                        .setBadgeItem(badgeItem))//设置提醒
                ).addItem(new BottomNavigationItem(R.drawable.h_financial, getString(R.string.item_financial)).setInactiveIconResource(R.drawable.h_financial_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.h_market, getString(R.string.item_market)).setInactiveIconResource(R.drawable.h_market_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .addItem(new BottomNavigationItem(R.drawable.h_mine, getString(R.string.item_mine)).setInactiveIconResource(R.drawable.h_mine_no).setActiveColorResource(R.color.main).setInActiveColorResource(R.color.black_1))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);

        mFragmentList.add(new FragmentOne());
        mFragmentList.add(new FragmentTwo());
        mFragmentList.add(new FragmentThree());
        mFragmentList.add(new FragmentFour());
    }


    @Override
    public void onTabSelected(int position) {
        mVpHome.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                T.showShort(R.string.app_exit_prompt);
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                ((BLApplication) getApplication()).finishStack();
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
