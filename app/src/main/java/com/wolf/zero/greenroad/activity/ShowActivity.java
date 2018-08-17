package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.adapter.ShowViewPagerAdapter;
import com.wolf.zero.greenroad.bean.CheckedBean;
import com.wolf.zero.greenroad.bean.DetailInfoBean;
import com.wolf.zero.greenroad.bean.ScanInfoBean;
import com.wolf.zero.greenroad.fragment.DetailsFragment;
import com.wolf.zero.greenroad.httpresultbean.HttpResultBlack;
import com.wolf.zero.greenroad.https.RequestSubmitBlackList;
import com.wolf.zero.greenroad.litepalbean.SupportChecked;
import com.wolf.zero.greenroad.litepalbean.SupportDetail;
import com.wolf.zero.greenroad.litepalbean.SupportScan;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.presenter.NetWorkManager;
import com.wolf.zero.greenroad.servicy.SubmitIntentService;
import com.wolf.zero.greenroad.tools.PermissionUtils;
import com.wolf.zero.greenroad.tools.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;


/**
 * Created by Administrator on 2017/6/20.
 */

public class ShowActivity extends BaseActivity {

    public static final int RESULT_CODE_LICENSE = 00 * 1;
    public static final String LICENSE_NUMBER = "license_number";
    private static String ARG_SUPPORT_SCAN = "arg_support_scan";
    private static String ARG_SUPPORT_DETAIL = "arg_support_detail";
    private static String ARG_SUPPORT_CHECKED = "arg_support_checked";
    private static String ARG_SUPPORT_MEDIA = "arg_support_media";
    private static String ARG_STATION = "arg_station";

    private static String ACTION_MAIN_ENTER_SHOW = "action_main_enter_show";

    private static String ACTION_DRAFT_ENTER_SHOW = "action_draft_enter_show";

    public static String ACTION_MEMORY_ENTER_SHOW = "action_memory_enter_show";
    private static TabLayout mTabShow;
    @BindView(R.id.view_pager_show)
    ViewPager mViewPagerShow;
    //    @BindView(R.id.toolbar_show)
//    Toolbar mToolbarShow;
    @BindView(R.id.fab_draft)
    FloatingActionButton mFabDraft;
    @BindView(R.id.fab_submit)
    FloatingActionButton mFabSubmit;
    @BindView(R.id.menu_fab)
    FloatingActionMenu mMenuFab;

    private AppCompatActivity mActivity;

    private ShowViewPagerAdapter mPagerAdapter;

    private static DetailInfoBean mDetailInfoBean_Q;
    private static ScanInfoBean mScanInfoBean_Q;


    private static CheckedBean mCheckedBean_Q;
    //    private static String mStation_Q;
//    private static String mRoad_Q;
    private Handler mUiHandler = new Handler();
    private static List<LocalMedia> mLocalMedias_sanzheng_Q;
    private static List<LocalMedia> mLocalMedias_cheshen_Q;
    private static List<LocalMedia> mLocalMedias_huozhao_Q;

    private File mFile;
    public static String mShowType;
    public static int mLite_id;
    private boolean mBlackTag;
    private ArrayList<String> mLicenseList;

    public static void actionStart(Context context, SupportDetail supportDetail, SupportScan supportScan,
                                   SupportChecked supportChecked, int lite_ID) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.setAction(ACTION_DRAFT_ENTER_SHOW);

        intent.putExtra(ARG_SUPPORT_DETAIL, supportDetail);
        intent.putExtra(ARG_SUPPORT_SCAN, supportScan);
        intent.putExtra(ARG_SUPPORT_CHECKED, supportChecked);
        intent.putExtra(ARG_SUPPORT_MEDIA, lite_ID);

        intent.setType(GlobalManager.TYPE_DRAFT_ENTER_SHOW);
        context.startActivity(intent);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.setAction(ACTION_MAIN_ENTER_SHOW);
        intent.setType(GlobalManager.TYPE_MAIN_ENTER_SHOW);
        context.startActivity(intent);
    }

    public static void actionStart(Context context, String action, String type) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.setAction(action);
        intent.setType(type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        ButterKnife.bind(this);
        mActivity = this;

        mTabShow = (TabLayout) findViewById(R.id.tab_show);

        initFab();
        initData();

        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        mShowType = intent.getType();
        if (ACTION_DRAFT_ENTER_SHOW.equals(intent.getAction())) {
            SupportDetail supportDetail = intent.getParcelableExtra(ARG_SUPPORT_DETAIL);
            SupportScan supportScan = intent.getParcelableExtra(ARG_SUPPORT_SCAN);
            SupportChecked supportChecked = intent.getParcelableExtra(ARG_SUPPORT_CHECKED);
            mLite_id = intent.getIntExtra(ARG_SUPPORT_MEDIA, 1);
            Logger.i(mLite_id + "qqq");

            mPagerAdapter = new ShowViewPagerAdapter(getSupportFragmentManager(), this,
                    supportDetail, supportScan, supportChecked, mLite_id, mShowType);

        } else if (ACTION_MAIN_ENTER_SHOW.equals(intent.getAction())) {
            mShowType = intent.getType();
            mPagerAdapter = new ShowViewPagerAdapter(getSupportFragmentManager(), this, mShowType);
        }
        mViewPagerShow.setOffscreenPageLimit(3);//设置viewpager预加载页面数
        mViewPagerShow.setAdapter(mPagerAdapter);  // 给Viewpager设置适配器
//        mViewpager.setCurrentItem(1); // 设置当前显示在哪个页面
        mTabShow.setupWithViewPager(mViewPagerShow);
    }

    private void initFab() {
        mMenuFab.setClosedOnTouchOutside(true);

        mMenuFab.hideMenuButton(false);
        int delay = 400;
        mUiHandler.postDelayed(() -> {
                    mMenuFab.showMenuButton(true);
                }
                , delay);
        mMenuFab.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMenuFab.isOpened()) {
                    mFabDraft.showButtonInMenu(true);
                    mFabSubmit.showButtonInMenu(true);
                }

                mMenuFab.toggle(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        List<String> strListValue = SPListUtil.getStrListValue(getContext(), SPListUtil.APPCONFIGINFO);
//        mRoad_Q = strListValue.get(1).toString();
//        mStation_Q = strListValue.get(2).toString();
    }

    private void initData() {
        PermissionUtils.verifyStoragePermissions(mActivity);
    }


    @OnClick({R.id.fab_submit, R.id.fab_draft})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fab_submit:
                mFabDraft.hideButtonInMenu(true);
                mFabSubmit.hideButtonInMenu(true);
                mMenuFab.toggle(false);

                DetailsFragment.setDetailsConnectListener((bean) -> {
                    mDetailInfoBean_Q = bean;
                });
                String licence = mDetailInfoBean_Q.getNumber();
                if (licence.length() < 7) {
                    if (!"无".equals(licence)) {
                        ToastUtils.singleToast("车牌号的长度至少7位");
                        return;
                    } else {
                        SubmitIntentService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
                        return;
                    }
                }
                String licence_header = licence.substring(0, 2);
                Logger.i(licence_header);
                if (NetWorkManager.isnetworkConnected(getApplicationContext())) {
                    Logger.i("有网加载黑名单数据的服务");

//                    RequestSubmitBlackList.getInstance().getBlackList(new
                    RequestSubmitBlackList.getInstance().getBlackList(new Subscriber<List<HttpResultBlack.DataBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Logger.i(e.getMessage());
                            ToastUtils.singleToast("网络异常,请检查服务器...");
                        }

                        @Override
                        public void onNext(List<HttpResultBlack.DataBean> dataBeans) {
//                            List<SupportBlack> supportBlacks = DataSupport.findAll(SupportBlack.class);

                            if (mLicenseList == null) {
                                mLicenseList = new ArrayList<>();
                            } else {
                                mLicenseList.clear();
                            }
                            Logger.i(dataBeans.toString());
                            if (dataBeans.size() == 1 && "kong".equals(dataBeans.get(0))) {
                                SubmitIntentService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
                            } else {
                                for (int i = 0; i < dataBeans.size(); i++) {
                                    mLicenseList.add(dataBeans.get(i).getPlate_number());
                                }

                                boolean isBlack = isBlack(licence, mLicenseList);
                                if (isBlack) {
                                    DetailsFragment.notifyChangeTextColor();
                                    mBlackTag = false;
                                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                    r.play();

                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(ShowActivity.this);
                                    builder1.setTitle("该车牌为黑名单车牌" + "\n" + "不可以提交,请保存为草稿");
                                    builder1.setPositiveButton("了解", (dialog, which) -> {
                                        dialog.dismiss();
                                    });
                                    builder1.setCancelable(false);
                                    builder1.show();
                                } else {
                                    mBlackTag = true;
                                    SubmitIntentService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
                                }
                            }
                        }
                    }, licence_header);
                } else {
                    Logger.i("无网未加载黑名单数据");
                    SubmitIntentService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
                }
                    /*RequestBlackList.getInstance().getBlackList(new Subscriber<List<HttpResultBlack.DataBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Logger.i(e.getMessage());
                            ToastUtils.singleToast("网络异常,请检查服务器...");
                        }

                        @Override
                        public void onNext(List<HttpResultBlack.DataBean> dataBeen) {
                            List<SupportBlack> supportBlacks = DataSupport.findAll(SupportBlack.class);
                            if (supportBlacks.size() == dataBeen.size()) {
                                mBlackTag = true;
//                                SubmitService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
                                justifyBlackSubmit();
//                                return;
                            } else {
                                DataSupport.deleteAll(SupportBlack.class);
                                for (int i = 0; i < dataBeen.size(); i++) {
                                    Logger.i(dataBeen.get(i).getPlate_number());
                                    SupportBlack supportBlack = new SupportBlack();
                                    supportBlack.setLicense(dataBeen.get(i).getPlate_number());
                                    supportBlack.save();
                                }
                                justifyBlackSubmit();

                            }
                        }

                    });
                } else {
                    Logger.i("无网未加载黑名单数据");
                    SubmitService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
                }*/

                break;
            case R.id.fab_draft:
                mFabDraft.hideButtonInMenu(true);
                mFabSubmit.hideButtonInMenu(true);
                mMenuFab.toggle(false);
                saveDraft(0);
                break;

            default:
                break;
        }
    }
/*
    private void justifyBlackSubmit() {
        List<SupportBlack> blackList = DataSupport.findAll(SupportBlack.class);
        if (blackList != null && blackList.size() != 0) {
            boolean isBlack = isBlack(mDetailInfoBean_Q.getNumber(), blackList);
            if (isBlack) {
                DetailsFragment.notifyChangeTextColor();
                mBlackTag = false;
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(ShowActivity.this);
                builder1.setTitle("该车牌为黑名单车牌" + "\n" + "不可以提交,请保存为草稿");
                builder1.setPositiveButton("了解", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder1.setCancelable(false);
                builder1.show();
//                return;
            } else {
                mBlackTag = true;
                SubmitService.startActionSubmit(ShowActivity.this, ShowActivity.this, DetailsFragment.sEnterType, mShowType);
//                return;
            }
        }
    }*/

    /**
     * 保存草稿
     * 1.进入草稿的activity并保存
     * 2.将数据保存到数据库
     */
    private void saveDraft(int id) {
        SubmitIntentService.startActionSave(this, DetailsFragment.sEnterType, mShowType, id);
    }

    @Override
    public void onBackPressed() {
        if (mMenuFab.isOpened()) {
            mFabDraft.hideButtonInMenu(true);
            mFabSubmit.hideButtonInMenu(true);
            mMenuFab.toggle(false);
        } else {
            backToMain();

        }
    }

    private void backToMain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("是否保存为草稿");
        builder.setMessage("点击确定保存为草稿并退出\n点击直接退出则清空当前采集");
        builder.setNegativeButton("直接退出", (dialog, which) -> {
            dialog.dismiss();
            Intent intent = new Intent(ShowActivity.this, MainActivity.class);
            startActivity(intent);
            notifyDataChangeAndFinish();
            mActivity.finish();
        });

        builder.setPositiveButton("保存并退出", (dialog, which) -> {
            //在这里做保存草稿的操作
            saveDraft(1);
            Intent intent = new Intent(ShowActivity.this, MainActivity.class);
            startActivity(intent);
            mActivity.finish();
        });
        builder.show();
    }

    public static void notifyDataChangeAndFinish() {
        ConclusionActivity.notifyDataChange();
        DetailsFragment.notifyDataChange();
    }

    public void notifyActivityFinish() {
        mActivity.finish();
    }

    private boolean isBlack(String carNumber, ArrayList<String> blackList) {

        for (int i = 0; i < blackList.size(); i++) {
            if (carNumber.equals(blackList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static void setTabLayoutCanClick(boolean canClick) {
        LinearLayout tabStrip = (LinearLayout) mTabShow.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if (tabView != null) {
                tabView.setClickable(canClick);
            }
        }
    }
}
