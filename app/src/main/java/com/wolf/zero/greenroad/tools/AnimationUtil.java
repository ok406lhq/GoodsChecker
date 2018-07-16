package com.wolf.zero.greenroad.tools;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by zerowolf on 2017/11/24.
 */

public class AnimationUtil {

    public static TranslateAnimation translate(float start_x, float end_x, float start_y, float end_y) {

        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                start_x, Animation.RELATIVE_TO_SELF, end_x,
                Animation.RELATIVE_TO_SELF, start_y, Animation.RELATIVE_TO_SELF,
                end_y);

        mShowAction.setDuration(500);
        return mShowAction;
    }
}
