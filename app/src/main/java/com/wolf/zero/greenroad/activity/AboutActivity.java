package com.wolf.zero.greenroad.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.tools.DevicesInfoUtils;


/**
 * Created by Administrator on 2017/7/3.
 */

public class AboutActivity extends AppCompatActivity {


    private TextView mActivityAboutActivationCode;
    private TextView mActivityAboutVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT*1/3;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().setAttributes(params);

        setContentView(R.layout.activity_about);

        initView();

    }

    private void initView() {
        mActivityAboutActivationCode = (TextView) findViewById(R.id.activity_about_activation_code);
        mActivityAboutVersion = (TextView) findViewById(R.id.activity_about_version);
        mActivityAboutVersion.setText(DevicesInfoUtils.getInstance().getVersion(this));

        String macID = Settings.Secure
                .getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        mActivityAboutActivationCode.setText(macID);

    }
}
