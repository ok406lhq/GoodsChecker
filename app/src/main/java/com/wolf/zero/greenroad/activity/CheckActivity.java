package com.wolf.zero.greenroad.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;

public class CheckActivity extends AppCompatActivity {

    private TextView mTvStandardWeight;
    private TextView mTvDeviation;
    private TextView mtvMoreWeight;
    private TextView mIsOverWeight;
    private TextView mCheck1;
    private TextView mCheck2;
    private TextView mCheck3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT * 1 / 3;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().setAttributes(params);
        setContentView(R.layout.activity_check);

        initView();
    }

    private void initView() {
        mTvStandardWeight = (TextView) findViewById(R.id.tv_standardWeight);
        mTvDeviation = (TextView) findViewById(R.id.tv_deviation);
        mtvMoreWeight = (TextView) findViewById(R.id.tv_moreWeight);
        mIsOverWeight = (TextView) findViewById(R.id.tv_showIsOverWeight);
        mCheck1 = (TextView) findViewById(R.id.tv_check1);
        mCheck2 = (TextView) findViewById(R.id.tv_check2);
        mCheck3 = (TextView) findViewById(R.id.tv_check3);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double standardWeight = bundle.getDouble("standardWeight");
        double index = bundle.getDouble("index");
        double deviation = bundle.getDouble("deviation");

        mTvStandardWeight.setText(Double.toString(standardWeight));
        mTvDeviation.setText(Integer.toString((int) (index * 100)) + "%");
        mtvMoreWeight.setText(Double.toString(deviation) + "吨   ");
        if (Math.abs(index) > 0.3) {
            mIsOverWeight.setText("超重了");
            mTvStandardWeight.setTextColor(Color.rgb(255, 0, 0));
            mTvDeviation.setTextColor(Color.rgb(255, 0, 0));
            mtvMoreWeight.setTextColor(Color.rgb(255, 0, 0));
            mIsOverWeight.setTextColor(Color.rgb(255, 0, 0));
            mCheck1.setTextColor(Color.rgb(255, 0, 0));
            mCheck2.setTextColor(Color.rgb(255, 0, 0));
            mCheck3.setTextColor(Color.rgb(255, 0, 0));
        } else {
            mIsOverWeight.setText("标准范围内");
            mTvStandardWeight.setTextColor(Color.rgb(45, 228, 32));
            mTvDeviation.setTextColor(Color.rgb(45, 228, 32));
            mtvMoreWeight.setTextColor(Color.rgb(45, 228, 32));
            mIsOverWeight.setTextColor(Color.rgb(45, 228, 32));
            mCheck1.setTextColor(Color.rgb(45, 228, 32));
            mCheck2.setTextColor(Color.rgb(45, 228, 32));
            mCheck3.setTextColor(Color.rgb(45, 228, 32));
        }
//        double sWeight = Double.valueOf(getIntent().getExtras().getString("sWeight"));
//        double sVolume = Double.valueOf(getIntent().getExtras().getString("sVolume"));
//        double sWeight = 11.0;
//        double sVolume = 23.1;
//        ScanFragment fragment = ScanFragment.newInstance(GlobalManager.TYPE_MAIN_ENTER_SHOW);
//        fragment.setCallBack(new ScanFragment.CallBack() {
//            @Override
//            public void setTexts(String sWeight, String sVolume) {
//                mtvMoreWeight.setText(Double.toString((Double.valueOf(sVolume) - Double.valueOf(sWeight))));
//                mTvDeviation.setText(Double.toString((Double.valueOf(sVolume) / Double.valueOf(sWeight))));
//                mtvMoreWeight.setText(Double.toString((Double.valueOf(sVolume) + Double.valueOf(sWeight))));
//            }
//        });

    }


}

