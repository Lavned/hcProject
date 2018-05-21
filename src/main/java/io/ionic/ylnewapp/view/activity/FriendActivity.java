package io.ionic.ylnewapp.view.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.security.cert.PolicyNode;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.custom.MyDialog;
import io.ionic.ylnewapp.utils.T;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class FriendActivity extends BaseActivity {

    @ViewInject(R.id.tv_title)
    TextView title;
    @ViewInject(R.id.span_1)
    TextView span_1;
    @ViewInject(R.id.span_2)
    TextView span_2;
    @ViewInject(R.id.span_3)
    TextView span_3;
    @ViewInject(R.id.span_4)
    TextView span_4;
    @ViewInject(R.id.span_5)
    TextView span_5;
    @ViewInject(R.id.span_6)
    TextView span_6;
    @ViewInject(R.id.span_7)
    TextView span_7;

    @ViewInject(R.id.copy_text)
    TextView copy_text;


    @Event(type = View.OnClickListener.class,value ={ R.id.tv_back ,R.id.fr_copy,R.id.invitation})
    private void click(View v){
        switch (v.getId()){
            case R.id.tv_back :
                finish();
                break;
            case R.id.fr_copy :
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);// 将文本内容放到系统剪贴板里。
                cm.setText(copy_text.getText().toString().trim());
                T.showShort("复制成功，可以发给朋友们了");
                break;
            case R.id.invitation:
                MyDialog dialog = new MyDialog(this);
                dialog.show();
                setFinishOnTouchOutside(true);//
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
        title.setText("邀请好友");
        setTextColor();
    }

    private void setTextColor() {
        span_1.setText(Html.fromHtml(getResources().getString(R.string.span_1)));
        span_2.setText(Html.fromHtml(getResources().getString(R.string.span_2)));
        span_3.setText(Html.fromHtml(getResources().getString(R.string.span_3)));
        span_4.setText(Html.fromHtml(getResources().getString(R.string.span_4)));
        span_5.setText(Html.fromHtml(getResources().getString(R.string.span_5)));
        span_6.setText(Html.fromHtml(getResources().getString(R.string.span_6)));
        span_7.setText(Html.fromHtml(getResources().getString(R.string.span_7)));
    }
}
