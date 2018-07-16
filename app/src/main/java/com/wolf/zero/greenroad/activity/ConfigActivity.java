package com.wolf.zero.greenroad.activity;//package com.android.htc.greenroad.activity;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.Toolbar;
//import android.widget.TextView;
//
//import R;
//import ActionBarTool;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by zerowolf on 2018/3/13.
// */
//
//public class ConfigActivity extends BaseActivity {
//    @BindView(R.id.toolbar_config)
//    Toolbar mToolbarConfig;
//    private ConfigActivity mActivity;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_config);
//        mActivity = this;
//        ButterKnife.bind(mActivity);
//
//        initToolbar();
//    }
//
//
//    private void initToolbar() {
//
//        setSupportActionBar(mToolbarConfig);
//
//        TextView title_text_view = ActionBarTool.getInstance(mActivity, 991).getTitle_text_view();
//        title_text_view.setText(getString(R.string.config_default));
//
//        mToolbarConfig.setNavigationIcon(R.drawable.back_up_logo);
//
//        mToolbarConfig.setNavigationOnClickListener(v -> finish());
//    }
//}
