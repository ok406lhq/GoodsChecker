package com.wolf.zero.greenroad.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3ctivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button mBtn;
    //    private TextView mTextView;
    private TextView mTextView;
    private int mA=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3ctivity);
        ButterKnife.bind(this);

         mTextView = (TextView) findViewById(R.id.text);
//        mText.setText("asjd");

        System.out.println(mA);




        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              aaa();
            }
        });
        aaa();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(mA);
    }

    private void aaa() {

        mA = 20;
        mTextView.setText("891237");
        System.out.println(mA);
    }
}
