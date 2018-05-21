package io.ionic.ylnewapp.view.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class MustSeeActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;

    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_must_see);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        title.setText("新手必看");
    }
}
