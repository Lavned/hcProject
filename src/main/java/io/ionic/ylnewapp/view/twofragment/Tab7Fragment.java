package io.ionic.ylnewapp.view.twofragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;
import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.TETFBean;
import io.ionic.ylnewapp.constants.Constants;
import io.ionic.ylnewapp.utils.ActivityUtils;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.StringUtils;
import io.ionic.ylnewapp.utils.T;

/**
 * Created by cmo on 16-7-21.
 */
public class Tab7Fragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener{


    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<TETFBean> etfList;

    private int lastVisibleItem = 0;
    private final int PAGE_COUNT = 10;
    private GridLayoutManager mLayoutManager;
    private TETFAdapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Activity mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragemt_tab_01, container, false);
        findView(view);
        initRefreshLayout();
        mContext = getActivity();
        return view;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser){
//            T.showShort("show");
            initData("");
        }else {
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 网请求
     * @param search
     */
    public void initData(String search) {
        OkGo.<String>get(Constants.URL_BASE + "product/products?type=TETF&search="+search)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//
                        Gson gson = new Gson();
                        TETFBean javaBean =gson.fromJson(data.toString(),TETFBean.class);
                        etfList = javaBean.getTETF();
                        if(etfList!=null)
                                initRecyclerView();
                        }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        T.showShort(response.toString());
                    }
                });
    }


    /**
     * 初始化view
     * @param view
     */
    private void findView(View view) {
        refreshLayout =view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);

    }

    //view参数设置
    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
    }

    //初始化刷新列表
    private void initRecyclerView() {
        adapter = new TETFAdapter(getDatas(0, PAGE_COUNT), getActivity(), getDatas(0, PAGE_COUNT).size() > 0 ? true : false);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.notifyDataSetChanged();
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

    //
    private List<TETFBean> getDatas(final int firstIndex, final int lastIndex) {
        List<TETFBean> resList = new ArrayList<>();
        for (int i = firstIndex; i < lastIndex; i++) {
            if (i < etfList.size()) {
                resList.add(etfList.get(i));
            }
        }
        return resList;
    }

    private void updateRecyclerView(int fromIndex, int toIndex) {
        List<TETFBean> newDatas = getDatas(fromIndex, toIndex);
        if (newDatas.size() > 0) {
            adapter.updateList(newDatas, true);
        } else {
            adapter.updateList(null, false);
        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        initData("");
        if(adapter!=null){
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


    /**
     * 内部适配器
     */
    class TETFAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<TETFBean> datas;
        private Context context;
        private int normalType = 0;
        private int footType = 1;
        private int headerType = 2;
        private boolean hasMore = true;
        private boolean fadeTips = false;
        private Handler mHandler = new Handler(Looper.getMainLooper());


        public TETFAdapter(List<TETFBean> datas, Context context, boolean hasMore) {
            this.datas = datas;
            this.context = context;
            this.hasMore = hasMore;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == normalType) {
                return new NormalHolder(LayoutInflater.from(context).inflate(R.layout.two_recycle_items2, null));
            } else if(viewType == footType){
                return new FootHolder(LayoutInflater.from(context).inflate(R.layout.footview, null));
            }else{
                return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.headerviewsearch, null));
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HeaderHolder) {
                HeaderHolder headerHolder = (HeaderHolder) holder;
            } else if (holder instanceof NormalHolder) {
                ((NormalHolder) holder).name.setText(datas.get(position -1).getName());
                ((NormalHolder) holder).content1.setText(datas.get(position -1).getContent().get(0));
                ((NormalHolder) holder).content2.setText(datas.get(position -1).getContent().get(1));
                ((NormalHolder) holder).number.setText(StringUtils.sliptStr(datas.get(position -1).getOrderid()));
                ((NormalHolder) holder).day.setText(DateUtil.getYmdforJson(datas.get(position -1).getDate()));
                ((NormalHolder) holder).btnVal.setText(""+datas.get(position -1).getBtn());
                if(datas.get(position -1).getBtn().equals("已锁定")) {
                    ((NormalHolder) holder).btnVal.setBackgroundResource(R.mipmap.lockbtn);
                }else{
                    ((NormalHolder) holder).btnVal.setBackgroundResource(R.mipmap.main_btn);
                }

            } else {
                ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
                if (hasMore == true) {
                    fadeTips = false;
                    if (datas.size() > 0) {
                        ((FootHolder) holder).tips.setText("正在加载更多...");
                    }
                } else {
                    if (datas.size() > 0) {
                        ((FootHolder) holder).tips.setText("没有更多数据了");
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ((FootHolder) holder).tips.setVisibility(View.GONE);
                                fadeTips = true;
                                hasMore = true;
                            }
                        }, 500);
                    }
                }
            }
        }


        @Override
        public int getItemCount() {
            return datas.size()  +2;
        }

        public int getRealLastPosition() {
            return datas.size();
        }


        public void updateList(List<TETFBean> newDatas, boolean hasMore) {
            if (newDatas != null) {
                datas.addAll(newDatas);
            }
            this.hasMore = hasMore;
            notifyDataSetChanged();
        }


        /*头部Item*/
        class HeaderHolder extends RecyclerView.ViewHolder {
            public EditText mSearch;
            public ImageView searchHeader;

            public HeaderHolder(View itemView) {
                super(itemView);
                mSearch = itemView.findViewById(R.id.search_ed);
                mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            // 当按了搜索之后关闭软键盘
                            ActivityUtils.colseIM(mSearch);
                            initData(mSearch.getText().toString().trim());
                            return true;
                        }
                        return false;
                    }
                });
                searchHeader  = itemView.findViewById(R.id.search);
                searchHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDateTime();
                    }
                });
            }
        }


        public void showDateTime() {
            //时间选择器
            final DatePicker picker = new DatePicker((Activity) context);
            ActivityUtils.setWheelStyle(picker,context);
            picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                @Override
                public void onDatePicked(String year, String month, String day) {
                    initData(year+month+day);
                }
            });
            picker.show();
        }




        //普通view
        class NormalHolder extends RecyclerView.ViewHolder {
            private TextView name,content1,content2,number,day,btnVal;

            public NormalHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.item_name);
                content1 = itemView.findViewById(R.id.item_content_1);
                content2 = itemView.findViewById(R.id.item_content_2);
                number = itemView.findViewById(R.id.item_number);
                day = itemView.findViewById(R.id.item_time);
                btnVal = itemView.findViewById(R.id.item_btns);
            }
        }


        /**
         * 底部view
         */
        class FootHolder extends RecyclerView.ViewHolder {
            private TextView tips;

            public FootHolder(View itemView) {
                super(itemView);
                tips = itemView.findViewById(R.id.tips);
            }
        }

        public boolean isFadeTips() {
            return fadeTips;
        }

        public void resetDatas() {
            datas = new ArrayList<>();
        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0){
                return headerType;
            }
            else if (position == getItemCount() - 1) {
                return footType;
            } else {
                return normalType;
            }
        }
    }


}
