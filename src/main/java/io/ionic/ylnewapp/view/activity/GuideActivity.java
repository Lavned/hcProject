package io.ionic.ylnewapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;

import java.util.Timer;
import java.util.TimerTask;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.PreferenceUtils;
import io.ionic.ylnewapp.view.MainActivity;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class GuideActivity extends BaseActivity {

    /**
     * 欢迎
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ImmersionBar.with(this).init();

        if(PreferenceUtils.getPrefBoolean(GuideActivity.this,"loginStatus",false) == false)
            intentClass(2);
        else
            intentClass(1);


    }

    /**
     * 选择跳转
     * @param type
     */
    private void intentClass(final int type) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(type ==1){
                    Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(GuideActivity.this, MainActivity.class);//login
                    startActivity(intent);
                    finish();
                }

            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //释放沉浸式Title,必须调用该方法，防止内存泄漏
    }
}
