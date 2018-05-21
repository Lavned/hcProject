package io.ionic.ylnewapp.view.twofragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jiangyy.easydialog.LoadingDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.adpater.OneMainAdapter;
import io.ionic.ylnewapp.bean.response.DIGBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by cmo on 16-7-21.
 */
public class Tab1Fragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener{


    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private int lastVisibleItem = 0;
    private final int PAGE_COUNT = 10;
    private GridLayoutManager mLayoutManager;
    private OneMainAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    List<DIGBean> digList;

    LoadingDialog.Builder mBuilder ;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragemt_tab_01, container, false);
        findView(view);
        initRefreshLayout();
        context = getActivity();

        //在oncreateview中调用这个
        if(!isViewShown){
            initData();
        }
        return view;
    }

    //为获取数据创建布尔值
    private boolean isViewShown = false;

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
////            T.showShort("show");
//            initData();
//        }else {
//            context = getActivity();
////            T.showShort("hidden");
//        }
        if(getView()!= null){
            isViewShown = true;
            // 包含当页面被选择时显示数据的逻辑主要asynctask填充数据
           initData();
        } else {
            isViewShown = false;
        }
    }


    private void initData() {
        mBuilder = new LoadingDialog.Builder(context);
        mBuilder.setTitle("加载中...").show();
        //加载数据
        OkGo.<String>get(Constants.URL_BASE + "product/products?type=DIG")//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        mBuilder.dismiss();
                        String data = response.body();//
                        Gson gson = new Gson();
                        DIGBean javaBean =gson.fromJson(data.toString(),DIGBean.class);
                        digList = javaBean.getDIG();
                        if(digList!=null)
                            if(digList.size()>0){
                                initRecyclerView();
                            }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.message());
                        mBuilder.dismiss();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        mBuilder.dismiss();
                    }
                });

    }


    private void findView(View view) {
        refreshLayout =view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);

    }

    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
    }

    private void initRecyclerView() {
        adapter = new OneMainAdapter(getDatas(0, PAGE_COUNT), getActivity(), getDatas(0, PAGE_COUNT).size() > 0 ? true : false);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (adapter.isFadeTips() == false && lastVisibleItem + 1 == adapter.getItemCount()) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
                            }
                        }, 500);
                    }

                    if (adapter.isFadeTips() == true && lastVisibleItem + 2 == adapter.getItemCount()) {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT);
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private List<DIGBean> getDatas(final int firstIndex, final int lastIndex) {
        List<DIGBean> resList = new ArrayList<>();
        for (int i = firstIndex; i < lastIndex; i++) {
            if (i < digList.size()) {
                resList.add(digList.get(i));
            }
        }
        return resList;
    }

    private void updateRecyclerView(int fromIndex, int toIndex) {
        List<DIGBean> newDatas = getDatas(fromIndex, toIndex);
        if (newDatas.size() > 0) {
            adapter.updateList(newDatas, true);
        } else {
            adapter.updateList(null, false);
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        if(adapter!=null) {
            adapter.resetDatas();
            updateRecyclerView(0, PAGE_COUNT);
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}

