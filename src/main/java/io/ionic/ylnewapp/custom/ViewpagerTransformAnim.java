package io.ionic.ylnewapp.custom;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by hah on 2018/5/4 0004.
 *
 * 動畫
 */

public class ViewpagerTransformAnim implements ViewPager.PageTransformer {

    //透明度变化
    //        @Override
//        public void transformPage(View page, float position) {
//            float alpha = 0.0f;
//            if (0.0f <= position && position <= 1.0f) {
//                alpha = 1.0f - position;
//            } else if (-1.0f <= position && position < 0.0f) {
//                alpha = position + 1.0f;
//            }
//            page.setAlpha(alpha);
//        }


//大小切出
    private float MIN_SCALE = 0.85f;

    private float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to
            // shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }
            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                    / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }


//背景切出
//    private static float MIN_SCALE = 0.75f;
//
//    @SuppressLint("NewApi")
//    @Override
//    public void transformPage(View view, float position) {
//        int pageWidth = view.getWidth();
//        if (position < -1) { // [-Infinity,-1)//This page is way off-screen to the left.
//            view.setAlpha(0);
//        } else if (position <= 0) { // [-1,0]Use //the default slide transition when moving to the left page
//            view.setAlpha(1);
//            view.setTranslationX(0);
//            view.setScaleX(1);
//            view.setScaleY(1);
//        } else if (position <= 1) { // (0,1]// Fade the page out.
//            view.setAlpha(1 - position);
//            // Counteract the default slide transition
//            view.setTranslationX(pageWidth * -position);
//            // Scale the page down (between MIN_SCALE and 1)
//            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
//                    * (1 - Math.abs(position));
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);
//        } else { // (1,+Infinity]
//            // This page is way off-screen to the right.
//            view.setAlpha(0);
//
//        }
//    }
}