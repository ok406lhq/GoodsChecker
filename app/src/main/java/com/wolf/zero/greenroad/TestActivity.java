package com.wolf.zero.greenroad;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wolf.zero.greenroad.activity.AnimatorActivity;
import com.wolf.zero.greenroad.activity.BaseActivity;


public class TestActivity extends BaseActivity {

    private int thumb_margin_left_day = 0;
    private int thumb_margin_left_night = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acticity);
    }

    public void onClickTest(View view) {
        changeTheme();
    }

    /**
     * 点击主题菜单按钮改变App的主题
     * /**
     * 切换主题时展示动画
     */

    private void changeTheme() {

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(240);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Intent intent = new Intent(getContext(), AnimatorActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        animatorSet.play(obtainCheckboxAnimator());

        animatorSet.start();
    }

    private Animator obtainCheckboxAnimator() {
        int start = getThemeTag() == -1 ? thumb_margin_left_night : thumb_margin_left_day;
        int end = getThemeTag() == -1 ? thumb_margin_left_day : thumb_margin_left_night;
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        return animator;
    }

  public void onClickTest2(View view) {
//      Intent intent = new Intent(this, MainActivity.class);
//      startActivity(intent);
    }

}
