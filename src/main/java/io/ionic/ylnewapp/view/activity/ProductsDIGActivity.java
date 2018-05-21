package io.ionic.ylnewapp.view.activity;

import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

import io.ionic.ylnewapp.R;
import io.ionic.ylnewapp.view.base.BaseActivity;

public class ProductsDIGActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_item);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),225);
    }
}
