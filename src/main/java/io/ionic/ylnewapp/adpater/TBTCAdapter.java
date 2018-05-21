package io.ionic.ylnewapp.adpater;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.TBTCBean;
import io.ionic.ylnewapp.utils.DateUtil;
import io.ionic.ylnewapp.utils.StringUtils;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.twofragment.Tab7Fragment;
import io.ionic.ylnewapp.view.twofragment.Tab8Fragment;

/**
 * Created by lijianchang@yy.com on 2017/4/12.
 */

public class TBTCAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TBTCBean> datas;
    private Context context;
    private int normalType = 0;
    private int footType = 1;
    private int headerType = 2;
    private boolean hasMore = true;
    private boolean fadeTips = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());


    CustomPopWindow mCustomPopWindow;

    public TBTCAdapter(List<TBTCBean> datas, Context context, boolean hasMore) {
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
            headerHolder.mSearch.setText("");
        } else if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).name.setText(datas.get(position -1).getName());
            ((NormalHolder) holder).content1.setText(datas.get(position -1).getContent().get(0));
            ((NormalHolder) holder).content2.setText(datas.get(position -1).getContent().get(1));
            ((NormalHolder) holder).number.setText(StringUtils.sliptStr(datas.get(position-1).getOrderid()));
            ((NormalHolder) holder).day.setText(DateUtil.getYmdforJson(datas.get(position -1).getDate()));
            ((NormalHolder) holder).btnVal.setText(""+datas.get(position -1).getBtn());
            if(datas.get(position -1).getBtn().equals("已锁定"))
                ((NormalHolder) holder).btnVal.setBackgroundResource(R.mipmap.lockbtn);

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


    public void updateList(List<TBTCBean> newDatas, boolean hasMore) {
        if (newDatas != null) {
            datas.addAll(newDatas);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }


    /*头部Item*/
  public  class HeaderHolder extends RecyclerView.ViewHolder {
        public EditText mSearch;
        public ImageView searchHeader;

        public HeaderHolder(final View itemView) {
            super(itemView);
            mSearch =  itemView.findViewById(R.id.search_ed);
            mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        // 当按了搜索之后关闭软键盘
                        InputMethodManager imm = ( InputMethodManager ) mSearch.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
                        if ( imm.isActive( ) ) {
                            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        }
                        Tab8Fragment jj = new Tab8Fragment();
                        jj.setUserVisibleHint(true);
                        jj.initData("time|20171101");
                            return true;
                    }
                    return false;
                }
            });
            searchHeader = itemView.findViewById(R.id.search);
            searchHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View contentView = LayoutInflater.from(context).inflate(R.layout.pop_menu,null);
                    //处理popWindow 显示内容
                    handleLogic(contentView);
                    //创建并显示popWindow
                    mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(context)
                            .setView(contentView)
                            .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                            .setBgDarkAlpha(0.7f) // 控制亮度
                            .create()
                            .showAsDropDown(searchHeader,0,20);
                }
            });
        }
    }


    /**
     * 处理弹出显示内容、点击事件等逻辑
     * @param contentView
     */
    private void handleLogic(View contentView){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCustomPopWindow!=null){
                    mCustomPopWindow.dissmiss();
                }
                String showContent = "";
                switch (v.getId()){
                    case R.id.menu1:
                        showDateTime();
                        break;
                    case R.id.menu2:
                        showContent = "点击 Item菜单2";
                        break;
                    case R.id.menu3:
                        showContent = "点击 Item菜单3";
                        break;
                }
                T.showShort(showContent);
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
    }




    int vyear,vmonth,vday;
    public  void getTime(){
        long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=new Date(time);
        String t1=format.format(d1);
        vyear = Integer.parseInt(t1.substring(0,4));
        vmonth = Integer.parseInt( t1.substring(5,7));
        vday =  Integer.parseInt(t1.substring(8,10));
    }

    void showDateTime(){
        getTime();
        //时间选择器
        final DatePicker picker = new DatePicker((Activity)context);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(context, 10));
        picker.setRangeEnd(2030, 1, 11);
        picker.setRangeStart(1970, 1, 1);
        picker.setSelectedItem(vyear,vmonth,vday);
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                T.showShort(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        private TextView name,content1,content2,number,day,btnVal;

        public NormalHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            content1 = (TextView) itemView.findViewById(R.id.item_content_1);
            content2 = (TextView) itemView.findViewById(R.id.item_content_2);
            number = (TextView) itemView.findViewById(R.id.item_number);
            day = (TextView) itemView.findViewById(R.id.item_time);
            btnVal = itemView.findViewById(R.id.item_btns);
    }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;

        public FootHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.tips);
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
