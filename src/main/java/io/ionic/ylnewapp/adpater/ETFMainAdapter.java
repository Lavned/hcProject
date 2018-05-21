package io.ionic.ylnewapp.adpater;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.bean.response.TBTCBean;

/**
 * Created by lijianchang@yy.com on 2017/4/12.
 */

public class ETFMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TBTCBean> datas =new ArrayList<>();
    private Context context;
    private int normalType = 0;
    private int footType = 1;
    private int headerType = 2;
    private boolean hasMore = true;
    private boolean fadeTips = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public ETFMainAdapter( Context context, boolean hasMore) {
       // this.datas = datas;
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
            headerHolder.textViewHeader.setText("");
        } else if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).textView.setText(datas.get(position-1).getDate());
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
        return datas.size()+2 ;

    }

    public int getRealLastPosition() {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return getItemId(position);
    }

    public void updateList(List<TBTCBean> newDatas, boolean hasMore) {
        if (newDatas != null) {
            datas.clear();
            datas.addAll(newDatas);
        }
        this.hasMore = hasMore;
        notifyDataSetChanged();
    }


    /*头部Item*/
    class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView textViewHeader;

        public HeaderHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.text1sss);
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public NormalHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_name);
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
