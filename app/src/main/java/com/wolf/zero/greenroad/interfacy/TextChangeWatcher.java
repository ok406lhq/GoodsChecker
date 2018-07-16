package com.wolf.zero.greenroad.interfacy;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author sineom
 * @version 1.0
 * @time 2017/7/17 22:44
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/7/17
 * @updataDes ${描述更新内容}
 */

public class TextChangeWatcher implements TextWatcher {


    private AfterTextListener mAfterTextListener;

    public TextChangeWatcher(AfterTextListener afterTextListener) {
        mAfterTextListener = afterTextListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mAfterTextListener.afterTextChanged(s);
    }

    public interface AfterTextListener {
        void afterTextChanged(Editable editable);
    }

    public void setAfterTextListener(AfterTextListener afterTextListener) {
        mAfterTextListener = afterTextListener;
    }
}
