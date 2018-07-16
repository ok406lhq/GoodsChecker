package com.wolf.zero.greenroad.tools;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.fragment.DetailsFragment;


/**
 * Created by Administrator on 2017/8/8.
 */

public class LicenseKeyboardUtil {
    private KeyboardView keyboardView;
    private Keyboard k1;// 省份简称键盘
    private Keyboard k2;// 数字字母键盘
    private String provinceShort[];
    private String letterAndDigit[];
    private TextView edits[];

    private final Context mContext;
    private int currentEditText;//默认当前光标在第一个EditText
    private final DetailsFragment mFragment;


    public LicenseKeyboardUtil(Context context, DetailsFragment detailsFragment, View view, TextView[] edits, int numberLength) {
        mFragment = detailsFragment;
        mContext = context;
        this.edits = edits;
        currentEditText = numberLength;
        k1 = new Keyboard(mContext, R.xml.province_short_keyboard);
        k2 = new Keyboard(mContext, R.xml.lettersanddigit_keyboard);
        keyboardView = (KeyboardView) (view.findViewById(R.id.keyboard_view));
        if (currentEditText == 0) {
            keyboardView.setKeyboard(k1);
        } else {
            keyboardView.setKeyboard(k2);
        }
        keyboardView.setEnabled(true);
        //设置为true时,当按下一个按键时会有一个popup来显示<key>元素设置的android:popupCharacters=""
        keyboardView.setPreviewEnabled(false);
        //设置键盘按键监听器
        keyboardView.setOnKeyboardActionListener(listener);
        keyboardView.setLongClickable(false);
        provinceShort = new String[]{
                "粤", "闽", "赣", "湘", "琼", "京", "津",
                "冀", "晋", "蒙", "辽", "吉", "黑", "沪",
                "苏", "浙", "皖", "鲁", "豫", "鄂", "桂",
                "渝", "川", "贵", "云", "藏", "陕", "甘",
                "青", "宁", "新", "港", "澳", "无"};

        letterAndDigit = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
                , "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
                , "K", "L", "M", "N", "O", "P", "Q", "R", "S"
                , "T", "U", "V", "W", "X", "Y", "Z"};

    }


    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {

            if (primaryCode == 112) { //xml中定义的删除键值为112

                currentEditText--;
                edits[currentEditText].setText("");//将当前EditText置为""并currentEditText-1

                if (currentEditText < 1) {
                    //切换为省份简称键盘
                    keyboardView.setKeyboard(k1);
                    currentEditText = 0;
                }

            } else { //其它字符按键
                if (currentEditText == 0) {
                    //如果currentEditText==0代表当前为省份键盘,
                    // 按下一个按键后,设置相应的EditText的值
                    // 然后切换为字母数字键盘
                    //currentEditText+1
                    if (primaryCode != 33) {

                        edits[0].setText(provinceShort[primaryCode]);
                /*    edits[0].setFocusable(true);
                    edits[0].setSelection(1);*/
                        currentEditText = 1;
                        //切换为字母数字键盘
                        keyboardView.setKeyboard(k2);
                    } else {
                        edits[0].setText(provinceShort[primaryCode]);
                        currentEditText = 0;
                        mFragment.closeLicense();
                    }
                } else {
                    //第二位必须大写字母
                    if (currentEditText == 1 && !letterAndDigit[primaryCode].matches("[A-Z]{1}")) {
                        return;
                    }
                    if (currentEditText == 8) {
                        return;
                    }
                    edits[currentEditText].setText(letterAndDigit[primaryCode]);
         /*           edits[currentEditText].setFocusable(true);
                    edits[currentEditText].setSelection(1);*/
                    currentEditText++;
                    if (currentEditText > 8) {
                        currentEditText = 8;
                    }
                }
            }
        }
    };

    /**
     * 显示键盘
     */
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }
}
