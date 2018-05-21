package io.ionic.ylnewapp.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import io.ionic.ylnewapp.R;

/**
 * Created by hah on 2018/5/7 0007.
 */

public class MyDialog extends Dialog {

    public MyDialog(Context context) {
        super(context, R.style.Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitation_item);
        findViewById(R.id.root_div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}