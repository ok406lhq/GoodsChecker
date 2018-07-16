package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.httpresultbean.HttpResultLineStation;
import com.wolf.zero.greenroad.https.RequestLineStation;
import com.wolf.zero.greenroad.litepalbean.SupportLine;
import com.wolf.zero.greenroad.tools.SPUtils;
import com.wolf.zero.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class LineConfigActivity extends BaseActivity {

    @BindView(R.id.text_line_config)
    EditText mTextLineConfig;
    @BindView(R.id.btn_sure_line)
    Button mBtnSureLine;
    @BindView(R.id.rl_progress_port)
    RelativeLayout mRlProgressPort;
    private String mConfigPort;
    private String mType;

    public static void actionStart(Context context, String type) {
        Intent intent = new Intent(context, LineConfigActivity.class);
        intent.setType(type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_config);
        ButterKnife.bind(this);

        //initToolbar();

        getIntentData();

        mBtnSureLine.setOnClickListener(view -> {
            mConfigPort = mTextLineConfig.getText().toString();
            // if (!TextUtils.isEmpty(mConfigPort)) {
              SPUtils.putAndApply(this, SPUtils.CONFIG_PORT, mConfigPort);
//            if (GlobalManager.LOGIN2PORT.equals(mType)) {
//                mRlProgressPort.setVisibility(View.VISIBLE);
//                initLine();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                this.finish();

//            } else {
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//                this.finish();
//            }
            //  }
        });
//        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        mType = intent.getType();
//        if (GlobalManager.LOGIN2PORT.equals(mType)) {
//            mBtnSureLine.setText("申请注册");
//        } else {
            mBtnSureLine.setText("确定配置");
//        }
    }

    /**
     * 拿到线路以及收费站的信息保存到本地数据库
     */
    private void initLine() {
        RequestLineStation.getInstance().getLineStation(new Subscriber<List<HttpResultLineStation.DataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.i(e.getMessage());
                mRlProgressPort.setVisibility(View.GONE);
                ToastUtils.singleToast("连接服务器失败,请检查端口配置是否正确");
            }

            @Override
            public void onNext(List<HttpResultLineStation.DataBean> dataBeen) {
                DataSupport.deleteAll(SupportLine.class);
                for (int i = 0; i < dataBeen.size(); i++) {
                    Logger.i(dataBeen.get(i).getLine() + "");
                    Logger.i(dataBeen.get(i).getStations() + "");
                    String[] stations = dataBeen.get(i).getStations().split(",");
                    ArrayList<String> stationList = new ArrayList<>();
                    for (int j = 0; j < stations.length; j++) {
                        stationList.add(stations[j]);
                    }
                    SupportLine supportLine = new SupportLine();
                    supportLine.setLine(dataBeen.get(i).getLine());
                    supportLine.setStations(stationList);
                    supportLine.save();
                }
                mRlProgressPort.setVisibility(View.GONE);
                Intent intent = new Intent(LineConfigActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
