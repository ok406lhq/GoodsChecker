package com.wolf.zero.greenroad.servicy;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.activity.BaseActivity;
import com.wolf.zero.greenroad.litepalbean.SupportChecked;
import com.wolf.zero.greenroad.litepalbean.SupportDetail;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;
import com.wolf.zero.greenroad.tools.ActionBarTool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowNotificationActivity extends BaseActivity {

    @BindView(R.id.toolbar_notification)
    Toolbar mToolbarNotification;
    @BindView(R.id.text_notify_user)
    TextView mTextNotifyUser;
    @BindView(R.id.text_notify_carType)
    TextView mTextNotifyCarType;
    @BindView(R.id.text_notify_number)
    TextView mTextNotifyNumber;
    @BindView(R.id.text_notify_huowu)
    TextView mTextNotifyHuowu;
    @BindView(R.id.text_notify_export)
    TextView mTextNotifyExport;
    @BindView(R.id.text_notify_check)
    TextView mTextNotifyCheck;
    @BindView(R.id.text_notify_login)
    TextView mTextNotifyLogin;
    @BindView(R.id.text_notify_failure)
    TextView mTextNotifyFailure;
    @BindView(R.id.text_notify_submit_time)
    TextView mTextNotifySubmitTime;
    @BindView(R.id.ll_reason_failure)
    LinearLayout mLlReasonFailure;
    private ShowNotificationActivity mActivity;
    private SupportDraftOrSubmit mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);
        mActivity = this;
        ButterKnife.bind(this);


        initToolbar();

        mSubmit = getIntent().getParcelableExtra("notifyData");
        Logger.i(mSubmit.toString());
//        mTextNotification.setText(submit.toString());

        initData();
    }

    private void initData() {
        int isPass = mSubmit.getIsPass();
        mLlReasonFailure.setVisibility(isPass == 0 || isPass == 1 ? View.GONE : View.VISIBLE);

        if (isPass == 2) {
            mTextNotifyFailure.setText(mSubmit.getMessage());
        }

        SupportDetail supportDetail = mSubmit.getSupportDetail();
        SupportChecked supportChecked = mSubmit.getSupportChecked();
//        SupportScan supportScan = mSubmit.getSupportScan();

        mTextNotifySubmitTime.setText(mSubmit.getCurrent_time());

        //采集信息的条目
        mTextNotifyUser.setText(mSubmit.getUsername());
        mTextNotifyExport.setText(supportDetail.getLane());
        mTextNotifyLogin.setText(supportChecked.getSiteLogin());
        mTextNotifyNumber.setText(supportDetail.getNumber());
        mTextNotifyCheck.setText(supportChecked.getSiteChecks().get(0));
        mTextNotifyCarType.setText(supportDetail.getDetail_carType());
        mTextNotifyHuowu.setText(supportDetail.getGoods());


    }

    private void initToolbar() {
        setSupportActionBar(mToolbarNotification);

        TextView title_text_view = ActionBarTool.getInstance(mActivity, 991).getTitle_text_view();
        title_text_view.setText(getString(R.string.check_isPass));

        mToolbarNotification.setNavigationIcon(R.drawable.back_up_logo);

        mToolbarNotification.setNavigationOnClickListener(v -> finish());
    }
}
