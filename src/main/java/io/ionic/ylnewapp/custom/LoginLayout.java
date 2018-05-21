package io.ionic.ylnewapp.custom;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by hah on 2018/5/3 0003.
 */

public class LoginLayout extends RelativeLayout {

    private int[] mInsets = new int[4];

    public LoginLayout(Context context) {
        super(context);
    }

    public LoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final int[] getInsets() {
        return mInsets;
    }

    /**
     * 此方法以过期，当应用最低API支持为20后，可以重写以下方法
     * @Override
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
    mInsets[0] = insets.getSystemWindowInsetLeft();
    mInsets[1] = insets.getSystemWindowInsetTop();
    mInsets[2] = insets.getSystemWindowInsetRight();
    return super.onApplyWindowInsets(insets.replaceSystemWindowInsets(0, 0, 0,
    insets.getSystemWindowInsetBottom()));
    } else {
    return insets;
    }
    }
     *  未测试……
     */
    @Override
    protected final boolean fitSystemWindows(Rect insets) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            mInsets[0] = insets.left;
            mInsets[1] = insets.top;
            mInsets[2] = insets.right;
            return super.fitSystemWindows(insets);
        } else {
            return super.fitSystemWindows(insets);
        }
    }
}