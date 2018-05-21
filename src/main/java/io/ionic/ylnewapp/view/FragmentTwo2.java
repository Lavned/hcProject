//package io.ionic.ylnewapp.view;
//
//
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.OrientationHelper;
//import android.support.v7.widget.RecyclerView;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.shizhefei.view.indicator.IndicatorViewPager;
//import com.shizhefei.view.indicator.ScrollIndicatorView;
//import com.shizhefei.view.indicator.slidebar.ColorBar;
//import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
//
//
//import org.xutils.view.annotation.ContentView;
//import org.xutils.view.annotation.ViewInject;
//import org.xutils.x;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import io.ionic.ylnewapp.R;
//import io.ionic.ylnewapp.adpater.TwoMainAdapter;
//import io.ionic.ylnewapp.utils.DisplayUtil;
//import io.ionic.ylnewapp.view.base.BaseFragment;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//@ContentView(R.layout.fragment_two2)
//public class FragmentTwo2 extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener{
//
//
//    private IndicatorViewPager indicatorViewPager;
//    @ViewInject(R.id.moretab_viewPager)
//    ViewPager viewPager;
//
//    @ViewInject(R.id.moretab_indicator)
//    ScrollIndicatorView scrollIndicatorView;
//
//
//    private SwipeRefreshLayout refreshLayout;
//    private RecyclerView recyclerView;
//    private List<String> list;
//
//    private int lastVisibleItem = 0;
//    private final int PAGE_COUNT = 10;
//    private GridLayoutManager mLayoutManager;
//    private TwoMainAdapter adapter;
//    private Handler mHandler = new Handler(Looper.getMainLooper());
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view =  x.view().inject(this,inflater,container);
//        iniview();
//        return view;
//
//    }
//
//    private void initData() {
//        list = new ArrayList<>();
//        for (int i = 1; i <= 40; i++) {
//            list.add("size" + i);
//        }
//    }
//
//
//    private void initRefreshLayout() {
//        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
//                android.R.color.holo_orange_light, android.R.color.holo_green_light);
//        refreshLayout.setOnRefreshListener(this);
//    }
//
//    private void initRecyclerView() {
//        adapter = new TwoMainAdapter(getDatas(0, PAGE_COUNT), getActivity(), getDatas(0, PAGE_COUNT).size() > 0 ? true : false);
//        mLayoutManager = new GridLayoutManager(getActivity(), 1);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    if (adapter.isFadeTips() == false && lastVisibleItem + 1 == adapter.getItemCount()) {
//                        mHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
//                            }
//                        }, 500);
//                    }
//
//                    if (adapter.isFadeTips() == true && lastVisibleItem + 2 == adapter.getItemCount()) {
//                        mHandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
//                            }
//                        }, 500);
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
//            }
//        });
//    }
//
//    private List<String> getDatas(final int firstIndex, final int lastIndex) {
//        List<String> resList = new ArrayList<>();
//        for (int i = firstIndex; i < lastIndex; i++) {
//            if (i < list.size()) {
//                resList.add(list.get(i));
//            }
//        }
//        return resList;
//    }
//
//    private void updateRecyclerView(int fromIndex, int toIndex) {
//        List<String> newDatas = getDatas(fromIndex, toIndex);
//        if (newDatas.size() > 0) {
//            adapter.updateList(newDatas, true);
//        } else {
//            adapter.updateList(null, false);
//        }
//    }
//
//    @Override
//    public void onRefresh() {
//        refreshLayout.setRefreshing(true);
//        adapter.resetDatas();
//        updateRecyclerView(0, PAGE_COUNT);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                refreshLayout.setRefreshing(false);
//            }
//        }, 1000);
//    }
//
//
//
//
//    private void iniview() {
//        float unSelectSize = 12;
//        float selectSize = unSelectSize * 1.3f;
//        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFFFEA620,Color.GRAY).setSize(selectSize, unSelectSize));
//        scrollIndicatorView.setScrollBar(new ColorBar(getActivity(), 0xFFFEA620, 4));
//        viewPager.setOffscreenPageLimit(3);
//        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
//        indicatorViewPager.setAdapter(new TbViewAdapter());
//    }
//
//    private class TbViewAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
//        private String[] versions = {"111", "222", "112", "222", "1221", "12121", "121212", "Jelly Bean", "4454"};
//        private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶", "姜饼", "蜂巢", "冰激凌三明治", "果冻豆", "奇巧巧克力棒"};
//
//        @Override
//        public int getCount() {
//            return versions.length;
//        }
//
//        @Override
//        public View getViewForTab(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.fragment2, container, false);
//            }
//            TextView textView = convertView.findViewById(R.id.tv_fragment_text);
//            textView.setText(versions[position]);
//
//            int witdh = getTextWidth(textView);
//            int padding = DisplayUtil.dipToPix(getActivity().getApplicationContext(), 8);
//            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
//            //1.3f是根据上面字体大小变化的倍数1.3f设置
//            textView.setWidth((int) (witdh * 1.3f) + padding);
//
//            return convertView;
//        }
//
//        @Override
//        public View getViewForPage(int position, View convertView, ViewGroup container) {
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.fragemt_tab_01, container, false);
//                findView(convertView);
//                initData();
//                initRefreshLayout();
//                initRecyclerView();
//            }
//            return convertView;
//        }
//
//
//
//        @Override
//        public int getItemPosition(Object object) {
//            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
//            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
//            return PagerAdapter.POSITION_UNCHANGED;
//        }
//
//        private int getTextWidth(TextView textView) {
//            if (textView == null) {
//                return 0;
//            }
//            Rect bounds = new Rect();
//            String text = textView.getText().toString();
//            Paint paint = textView.getPaint();
//            paint.getTextBounds(text, 0, text.length(), bounds);
//            int width = bounds.left + bounds.width();
//            return width;
//        }
//
//    }
//    private void findView(View view) {
//        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//
//    }
//
//
//
//
//}
