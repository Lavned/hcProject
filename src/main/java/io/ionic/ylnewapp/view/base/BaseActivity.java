package io.ionic.ylnewapp.view.base;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jaeger.library.StatusBarUtil;

import org.xutils.x;

import io.ionic.ylnewapp.BLApplication;
import io.ionic.ylnewapp.R;

/**
 * Created by hah on 2018/5/3 0003.
 */

public class BaseActivity extends AppCompatActivity
{


    public Context mContext;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
        mContext = this;
        x.view().inject(this);

        if (isAddToStack())
        {
            ((BLApplication) getApplication()).addActivity(this);
        }
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }


    /** 获取最上层的Activity负责 弹dialog,防止出现 can't add dialog 出错 */
    protected Context getDialogContext()
    {
        Activity activity = this;
        while (activity.getParent() != null)
        {
            activity = activity.getParent();
        }
        Log.d("Dialog", "context:" + activity);
        return activity;
    }

    @Override
    public void finish()
    {
        if (isAddToStack())
        {
            ((BLApplication) getApplication()).removeActivty(this);
        }
        super.finish();
    }
    /**
     * 是否添加至堆栈
     *
     * @return
     */
    protected boolean isAddToStack()
    {
        return true;
    }
}
