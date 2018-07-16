package com.wolf.zero.greenroad;/*
package com.android.htc.greenroad;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import AboutActivity;
import AnimatorActivity;
import BaseActivity;
import BlackListActivity;
import ConclusionActivity;
import MainActivity;
import PreviewDetailActivity;
import RegisterActivity;
import SettingActivity;
import ShowActivity;
import SubmitActivity;
import GoodsTextAdapter;
import ItemTouchHelperCallback;
import RecycleViewDivider;
import SiteChecksAdapter;
import SureGoodsAdapter;
import SwipeDeleteAdapter;
import CheckedBean;
import DetailInfoBean;
import PathTitleBean;
import ScanInfoBean;
import com.android.htc.greenroad.bean.SerializableMain2Sure;
import CheckedFragment;
import DetailsFragment;
import com.android.htc.greenroad.fragment.PhotoFragment;
import ScanFragment;
import DeleteHelper;
import SortTime;
import HttpResultMacInfo;
import RequestMacInfo;
import TextChangeWatcher;
import SupportChecked;
import SupportDetail;
import SupportMedia;
import SupportOperator;
import SupportScan;
import GlobalManager;
import NetWorkManager;
import BlackListService;
import com.android.htc.greenroad.servicy.SubmitService;
import ActionBarTool;
import BitmapUtil;
import CommonUtils;
import DevicesInfoUtils;
import PermissionUtils;
import SDcardSpace;
import SPListUtil;
import SPUtils;
import TimeUtil;
import ToastUtils;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

*/
/**
 * Created by Administrator on 2017/9/29.
 *//*


public class AAAAAAA {
    public class LoginActivity extends BaseActivity implements View.OnClickListener {


        @BindView(R.id.login_version)
        TextView mLoginVersion;
        @BindView(R.id.login_register)
        TextView mLoginRegister;
        @BindView(R.id.rl_progress_login)
        RelativeLayout mRlProgressLogin;
        private Button mBt_login;
        @BindView(R.id.text_user_name)
        EditText mEt_user_name;
        @BindView(R.id.text_password)
        EditText mEt_password;
        @BindView(R.id.check_box_pwd)
        CheckBox mCheckBox;
        private boolean mIsConnected;
        private static int TIMEGAP = 3;
        private String macID;
        private LoginActivity mActivity;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            mActivity = this;
            ButterKnife.bind(mActivity);
            initData();
            initView();

        }

        private void initData() {
            Connector.getDatabase();

            // TODO: 2017/8/5 客户端的认证信息，移至注册账号是的返回储存

            macID = Settings.Secure
                    .getString(getContentResolver(), Settings.Secure.ANDROID_ID);

            mLoginVersion.setText("e绿通 V" + DevicesInfoUtils.getInstance().getVersion(mActivity));


        }

        private void initView() {

            mBt_login = (Button) findViewById(R.id.bt_login);

            mBt_login.setOnClickListener(mActivity);
            mLoginRegister.setOnClickListener(mActivity);


        }

        @Override
        protected void onResume() {
            super.onResume();
            String user = (String) SPUtils.get(this, SPUtils.lOGIN_USERNAME, "");
            mEt_user_name.setText(user);
            if (user != null && !"".equals(user)) {
                mCheckBox.setChecked(true);
            } else {
                mCheckBox.setChecked(false);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_login:
                    startMainActivity(v);
                    break;
                case R.id.login_register:
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);

                    break;

                default:
                    break;

            }
        }

        */
/**
         * 根据有无网络连接判断时间差内登陆的情形
         *
         * @param username
         * @param password
         *//*

        private void getTimeGap(List<String> loginList, String username, String password) {
            String userName = loginList.get(0);
            String psw = loginList.get(1);
            String save_time = loginList.get(2);

            String currentTimeToDate = TimeUtil.getCurrentTimeToDate();
            int timeGap = TimeUtil.differentDaysByMillisecond(save_time, currentTimeToDate);

            Logger.i("timeGap" + timeGap);
            if (timeGap > TIMEGAP) {
                SPListUtil.remove(mActivity, SPListUtil.LOGINNFO);
                ToastUtils.singleToast("账号已过期，请在有网状态下重新登录");
                mEt_user_name.setText("");
                mEt_password.setText("");
                mCheckBox.setChecked(false);
                return;
            } else {
                if (username.equals(userName) && password.equals(psw)) {
                    login2MainActivity();
                }
                ToastUtils.singleToast("无网络连接状态登陆成功");
            }
        }

        */
/**
         * 登录成功进入mainActivity
         *//*

        private void login2MainActivity() {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            //  mRlProgressLogin.setVisibility(View.GONE);
        }

        private boolean isPasswordValid(String password) {
            //TODO: Replace this with your own logic
            return password.length() > 4;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mActivity = this;

        mTvOperatorCheckMain = (TextView) findViewById(R.id.tv_operator_check_main);
        mTvOperatorLoginMain = (TextView) findViewById(R.id.tv_operator_login_main);
        mTvMathNumberSubmit = (TextView) findViewById(R.id.tv_math_number_submit);

        mTvChangeLaneMain.setOnClickListener(v -> openSettingActivity());
        mTvOperatorCheckMain.setOnClickListener(v -> openSettingActivity());
        mTvOperatorLoginMain.setOnClickListener(v -> openSettingActivity());

        initSpace();

        initData();
        //initSp();
        initView();

    }

    @OnClick({R.id.rl_main_draft, R.id.rl_main_submit, R.id.rl_main_blacklist, R.id.btn_enter_show})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_main_draft:
                Intent intent = new Intent(MainActivity.this, DraftActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_main_submit:
                Intent intent1 = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_main_blacklist:
                Intent intent2 = new Intent(MainActivity.this, BlackListActivity.class);
                startActivity(intent2);

                break;

            case R.id.btn_enter_show:
                List<SupportOperator> check = DataSupport.where("check_select=?", "1").
                        find(SupportOperator.class);
                List<SupportOperator> login = DataSupport.where("login_select=?", "1").
                        find(SupportOperator.class);

                if (check.size() >= 1 && login.size() == 1) {
                    ShowActivity.actionStart(MainActivity.this);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("确定选择默认检查人/登记人");
                    builder.setMessage("点击确定进入设置界面添加默认操作员;\n" +
                            "点击取消则回到界面,不可以采集车辆数据");
                    builder.setPositiveButton("确定", (dialog, which) -> {
                        openSettingActivity();
                    });
                    builder.setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    });
                    builder.show();
//                    ToastUtils.singleToast("目前请添加操作员");
                    break;
                }
                break;

            default:
                break;
        }
    }

    private void initSpace() {

        SDcardSpace sDcardSpace = new SDcardSpace(mActivity);
        mAvailSpace = sDcardSpace.getAvailSpace();
        // mAllSpace = sDcardSpace.getAllSpace();
        mAllSpace = sDcardSpace.getSDTotalSize(mActivity);

        mTvAllSpace.setText(" / " + mAllSpace);
        mTvAvailSpace.setText(mAvailSpace + "");

    }

    private void initData() {
        PermissionUtils.verifyStoragePermissions(mActivity);
        BlackListService.startActionBlack(this, mTvMathNumberBlacklist);
        macID = Settings.Secure
                .getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        List<String> strListValue = SPListUtil.getStrListValue(MainActivity.this, SPListUtil.APPCONFIGINFO);
        if (strListValue == null || strListValue.size() != 3) {
            RequestMacInfo.getInstance().getMacInfo(new Subscriber<HttpResultMacInfo>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Logger.i(e.getMessage());
                    SPListUtil.putStrListValue(MainActivity.this, SPListUtil.APPCONFIGINFO, null);
                }

                @Override
                public void onNext(HttpResultMacInfo httpResultMacInfo) {
                    int code = httpResultMacInfo.getCode();
                    Logger.i(code + "");
                    HttpResultMacInfo.DataBean dataBean = httpResultMacInfo.getData();
                    Logger.i(dataBean.toString());
                    if (code == 200) {
                        List app_config_info = new ArrayList<String>();

                        app_config_info.add(dataBean.getName());
                        app_config_info.add(dataBean.getTerminal_road());
                        app_config_info.add(dataBean.getTerminal_site());

                        Logger.i(app_config_info.toString());
                        mTvChangeStationMain.setText(dataBean.getTerminal_road());
                        SPListUtil.putStrListValue(MainActivity.this, SPListUtil.APPCONFIGINFO, app_config_info);
                    } else {
                        SPListUtil.putStrListValue(MainActivity.this, SPListUtil.APPCONFIGINFO, null);
                    }
                }
            }, macID);
        }
        List<String> new_ListValue = SPListUtil.getStrListValue(MainActivity.this, SPListUtil.APPCONFIGINFO);
        for (int i = 0; i < new_ListValue.size(); i++) {
            String string = new_ListValue.get(i).toString();
            Logger.i(string);
        }
        if (new_ListValue.size() == 3) {
            mTvChangeStationMain.setText(new_ListValue.get(2));
        }
    }

    private void initView() {
        setSupportActionBar(mToolbarMain);
        TextView title_text_view = ActionBarTool.getInstance(mActivity, 991).getTitle_text_view();
        title_text_view.setText("绿通车登记");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.footer_item_setting,
            R.id.nav_theme, R.id.footer_item_location})
    public void onFooterClick(View view) {
        switch (view.getId()) {
            case R.id.footer_item_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_theme:
                changeTheme();
                break;
            case R.id.footer_item_location:

                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_about) {
            navAbout();
        } else if (id == R.id.nav_update) {
            Logger.i("点击了更新按钮");
            updateApp();
        } else if (id == R.id.nav_backup) {
            buckUpApp();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    */
/**
     * 点击主题菜单按钮改变App的主题
     * */
/**
     * 切换主题时展示动画
     *//*


    private void changeTheme() {

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(240);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Intent intent = new Intent(getContext(), AnimatorActivity.class);
                finish();
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        animatorSet.play(obtainCheckboxAnimator());

        animatorSet.start();
    }


    private Animator obtainCheckboxAnimator() {
        int start = getThemeTag() == -1 ? thumb_margin_left_night : thumb_margin_left_day;
        int end = getThemeTag() == -1 ? thumb_margin_left_day : thumb_margin_left_night;
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        return animator;
    }

    */
/**
     * 退出程序
     *//*

    private void buckUpApp() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    */
/**
     * 关于的方法
     *//*

    private void navAbout() {
        Intent intent = new Intent(mActivity, AboutActivity.class);
        mActivity.startActivity(intent);
    }

    */
/**
     * 刷新当前页面的数据
     *//*

    private void refresh() {
        onResume();
    }


    //在onResume()方法注册
    @Override
    protected void onResume() {
        super.onResume();


        setOperatorInfo("check_select = ?", mTvOperatorCheckMain);
        setOperatorInfo("login_select = ?", mTvOperatorLoginMain);
        mTvChangeLaneMain.setText((String) SPUtils.get(this, SPUtils.TEXTLANE, "66"));

        mTvMathNumberDraft.setText(SPUtils.get(this, SPUtils.MATH_DRAFT_LITE, 0) + "");
        mTvMathNumberSubmit.setText(SPUtils.get(this, SPUtils.MATH_SUBMIT_LITE, 0) + "");

        //注册广播，接收service中启动的线程发送过来的信息，同时更新UI
        IntentFilter filter = new IntentFilter("com.example.updateUI");
        mBroadCaseReceiver = new MyBroadCaseReceiver();
        registerReceiver(mBroadCaseReceiver, filter);
    }

    private void setOperatorInfo(String condition, TextView textView) {
        List<SupportOperator> operatorList = DataSupport.where(condition, "1").find(SupportOperator.class);
        //ArrayList<String> checkOperatorList = new ArrayList<>();
        String operator = "";
        if (operatorList.size() != 0) {
            for (int i = 0; i < operatorList.size(); i++) {
                String job_number = operatorList.get(i).getJob_number();
                String operator_name = operatorList.get(i).getOperator_name();
                if (i == 0) {
                    operator = job_number + "/" + operator_name;
                } else {
                    operator = operator + "\n" + job_number + "/" + operator_name;
                }
            }
        } else {
            operator = "500001/苏三";
        }
        Logger.i(operator);
        if (operatorList.size() == 3) {
            textView.setTextSize(CommonUtils.sp2px(this, 7));
        } else {
            textView.setTextSize(CommonUtils.sp2px(this, 9));
        }
        textView.setText(operator);

    }

    public class DraftActivity extends BaseActivity implements View.OnClickListener {


        @BindView(R.id.toolbar_draft)
        Toolbar mToolbarPreview;
        @BindView(R.id.recycler_view_preview)
        RecyclerView mRecyclerViewPreview;

        private DraftActivity mActivity;
        private Context mContext;

        private List<SupportDraftOrSubmit> mDraftList;
        public ItemTouchHelperExtension mItemTouchHelper;
        public ItemTouchHelperExtension.Callback mCallback;
        private SwipeDeleteAdapter mRecyclerAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_draft);
            ButterKnife.bind(this);

            mActivity = this;
            mContext = this;
            initToolbar();
        }

        @Override
        protected void onResume() {
            super.onResume();
            initData();
            initView();
        }

        private void initView() {

            LinearLayoutManager manager = new LinearLayoutManager(mActivity,
                    LinearLayoutManager.VERTICAL, false);
            mRecyclerViewPreview.setLayoutManager(manager);
            mRecyclerViewPreview.addItemDecoration(new RecycleViewDivider(mContext,
                    LinearLayoutManager.HORIZONTAL, 10, Color.WHITE));
            mRecyclerAdapter = new SwipeDeleteAdapter(this, support -> {
                PreviewDetailActivity.actionStart(mContext, support, PreviewDetailActivity.ACTION_DRAFT_ITEM);
            });
            mRecyclerViewPreview.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.updateData(mDraftList);
            mCallback = new ItemTouchHelperCallback();
            mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
            mItemTouchHelper.attachToRecyclerView(mRecyclerViewPreview);
            mRecyclerAdapter.setItemTouchHelperExtension(mItemTouchHelper);

        }


        private void initData() {
            mDraftList = DataSupport.where("lite_type=?", GlobalManager.TYPE_DRAFT_LITE).find(SupportDraftOrSubmit.class);
            SortTime sortDraftTime = new SortTime();
            Collections.sort(mDraftList, sortDraftTime);
        }

        private void initToolbar() {
            setSupportActionBar(mToolbarPreview);
            TextView title_text_view = ActionBarTool.getInstance(mActivity, 992).getTitle_text_view();
            title_text_view.setText("草稿列表");
            mToolbarPreview.setNavigationIcon(R.drawable.back_up_logo);
            mToolbarPreview.setNavigationOnClickListener(v -> finish());
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete_preview_7:
                    ToastUtils.singleToast("清除七天前记录");
                    DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, 7, supportList -> {
                        mRecyclerAdapter.updateListView(supportList);
                    });
                    break;
                case R.id.delete_preview_15:
                    ToastUtils.singleToast("清除15天前记录");
                    DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, 15, supportList -> {
                        mRecyclerAdapter.updateListView(supportList);
                    });
                    break;
                case R.id.delete_preview_30:
                    ToastUtils.singleToast("清除30天前记录");
                    DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, 30, supportList -> {
                        mRecyclerAdapter.updateListView(supportList);
                    });
                    break;
                case R.id.delete_preview_all:
                    ToastUtils.singleToast("清空所有记录");
                    DeleteHelper.getInstance().deleteAllInfos(mContext, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, supportList -> {
                        mRecyclerAdapter.updateListView(supportList);
                    });

                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_preview, menu);
            return true;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.preview_item_car_number:

                    break;

                default:
                    break;
            }
        }
    }
package com.android.htc.greenroad.bean;

import java.io.Serializable;
import java.util.Calendar;

    */
/**
     * Created by Administrator on 2017/6/27.
     *//*


    public class SerializableGoods implements Serializable, Comparable {

        */
/**
         * 是否置顶
         *//*

        public int top;

        public String kind;

        public String scientific_name;

        public String alias;


        public String bitmapUrl;
        */
/**
         * 显示数据拼音的首字母
         *//*

        public String sortLetters;
        */
/**
         * 简拼
         *//*

        public String simpleSpell;
        */
/**
         * 全拼
         *//*

        public String wholeSpell;


        public long time;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public long getTime() {
            return time;
        }

        public String getBitmapUrl() {
            return bitmapUrl;
        }

        public void setBitmapUrl(String bitmapUrl) {
            this.bitmapUrl = bitmapUrl;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public String getScientific_name() {
            return scientific_name;
        }

        public void setScientific_name(String scientific_name) {
            this.scientific_name = scientific_name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getSortLetters() {
            return sortLetters;
        }

        public void setSortLetters(String sortLetters) {
            this.sortLetters = sortLetters;
        }

        public String getSimpleSpell() {
            return simpleSpell;
        }

        public void setSimpleSpell(String simpleSpell) {
            this.simpleSpell = simpleSpell;
        }

        public String getWholeSpell() {
            return wholeSpell;
        }

        public void setWholeSpell(String wholeSpell) {
            this.wholeSpell = wholeSpell;
        }

        @Override
        public int compareTo(Object another) {
            if (another == null || !(another instanceof SerializableGoods)) {
                return -1;
            }

            SerializableGoods otherSession = (SerializableGoods) another;
            */
/**置顶判断 ArrayAdapter是按照升序从上到下排序的，就是默认的自然排序
             * 如果是相等的情况下返回0，包括都置顶或者都不置顶，返回0的情况下要
             * 再做判断，拿它们置顶时间进行判断
             * 如果是不相等的情况下，otherSession是置顶的，则当前session是非置顶的，应该在otherSession下面，所以返回1
             *  同样，session是置顶的，则当前otherSession是非置顶的，应该在otherSession上面，所以返回-1
             * *//*

            int result = 0 - (top - otherSession.getTop());
            if (result == 0) {
                result = 0 - compareToTime(time, otherSession.getTime());
            }
            return result;
        }

        */
/**
         * 根据时间对比
         *//*

        public static int compareToTime(long lhs, long rhs) {
            Calendar cLhs = Calendar.getInstance();
            Calendar cRhs = Calendar.getInstance();
            cLhs.setTimeInMillis(lhs);
            cRhs.setTimeInMillis(rhs);
            return cLhs.compareTo(cRhs);

        }

    }

    */
/**
     * Created by Administrator on 2017/8/14.
     *//*


    public class SupportDraftOrSubmit extends DataSupport implements Parcelable {

        private String lite_type;
        private int lite_ID;
        private String current_time;

        private SupportDetail mSupportDetail;
        private SupportScan mSupportScan;
        private SupportChecked mSupportChecked;
        private SupportMedia mSupportMedia;


        public SupportDraftOrSubmit() {
        }

        protected SupportDraftOrSubmit(Parcel in) {
            lite_type = in.readString();
            lite_ID = in.readInt();
            current_time = in.readString();
            mSupportDetail = in.readParcelable(SupportDetail.class.getClassLoader());
            mSupportScan = in.readParcelable(SupportScan.class.getClassLoader());
            mSupportChecked = in.readParcelable(SupportChecked.class.getClassLoader());
            mSupportMedia = in.readParcelable(SupportMedia.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(lite_type);
            dest.writeInt(lite_ID);
            dest.writeString(current_time);
            dest.writeParcelable(mSupportDetail, flags);
            dest.writeParcelable(mSupportScan, flags);
            dest.writeParcelable(mSupportChecked, flags);
            dest.writeParcelable(mSupportMedia, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<SupportDraftOrSubmit> CREATOR = new Creator<SupportDraftOrSubmit>() {
            @Override
            public SupportDraftOrSubmit createFromParcel(Parcel in) {
                return new SupportDraftOrSubmit(in);
            }

            @Override
            public SupportDraftOrSubmit[] newArray(int size) {
                return new SupportDraftOrSubmit[size];
            }
        };

        public String getLite_type() {
            return lite_type;
        }

        public void setLite_type(String lite_type) {
            this.lite_type = lite_type;
        }

        public int getLite_ID() {
            return lite_ID;
        }

        public void setLite_ID(int lite_ID) {
            this.lite_ID = lite_ID;
        }

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public SupportScan getSupportScan() {
            return DataSupport.where("lite_ID = ?", String.valueOf(lite_ID)).findFirst(SupportScan.class);
        }

        public void setSupportScan(SupportScan supportScan) {
            mSupportScan = supportScan;
        }

        public SupportDetail getSupportDetail() {
            return DataSupport.where("lite_ID = ?", String.valueOf(lite_ID)).findFirst(SupportDetail.class);
        }

        public void setSupportDetail(SupportDetail supportDetail) {
            mSupportDetail = supportDetail;
        }

        public SupportChecked getSupportChecked() {
            return DataSupport.where("lite_ID = ?", String.valueOf(lite_ID)).findFirst(SupportChecked.class);
        }

        public SupportMedia getSupportMedia() {
            return DataSupport.where("lite_ID = ?", String.valueOf(lite_ID)).findFirst(SupportMedia.class);
        }

        public void setSupportMedia(SupportMedia supportMedia) {
            mSupportMedia = supportMedia;
        }

        public void setSupportChecked(SupportChecked supportChecked) {
            mSupportChecked = supportChecked;
        }

        @Override
        public String toString() {
            return "SupportDraftOrSubmit{" +
                    "lite_type='" + lite_type + '\'' +
                    ", lite_ID=" + lite_ID +
                    ", current_time='" + current_time + '\'' +
                    ", mSupportDetail=" + mSupportDetail +
                    ", mSupportScan=" + mSupportScan +
                    ", mSupportChecked=" + mSupportChecked +
                    ", mSupportMedia=" + mSupportMedia +
                    '}';
        }
    }

    private static DetailInfoBean mDetailInfoBean_Q;
    private static ScanInfoBean mScanInfoBean_Q;
    private static CheckedBean mCheckedBean_Q;

    private static List<LocalMedia> mLocalMedias_sanzheng_Q;
    private static List<LocalMedia> mLocalMedias_cheshen_Q;
    private static List<LocalMedia> mSelectList_huowu;

    private static String mStation_Q;
    private static String mRoad_Q;
    private File mFile;
    private static String mFilePath_str;
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SUBMIT = "com.zero.wolf.greenroad.submitservice.action.FOO";
    private static final String ACTION_SAVE = "com.zero.wolf.greenroad.submitservice.action.BAZ";
    public static final String ARG_BROADCAST_DRAFT = "arg_broadcast_draft";
    public static final String ARG_BROADCAST_SUBMIT = "arg_broadcast_submit";

    public static final String ARG_TYPE_SAVE = "arg_type_save";
    public static final String ARG_TYPE_SUBMIT = "arg_type_submit";

    public static final String ARG_TYPE_SHOW_SUBMIT = "arg_type_show_submit";
    public static final String ARG_TYPE_SHOW_SAVE = "arg_type_show_save";


    private String mSubmitTime;

    private static ShowActivity sActivity;
    private static Context sContext_draft;
    private static Context sContext_submit;
    private String mShowType_submit;
    private static ArrayList<String> mNew_path_list;

    public SubmitService() {
        super("SubmitService");
    }


    public static void startActionSubmit(ShowActivity activity, Context context, String enterType, String showType) {
        sContext_submit = context;
        sActivity = activity;
        Intent intent = new Intent(sActivity, SubmitService.class);
        intent.setAction(ACTION_SUBMIT);
        intent.putExtra(ARG_TYPE_SUBMIT, enterType);
        intent.putExtra(ARG_TYPE_SHOW_SUBMIT, showType);
        sActivity.startService(intent);
    }

    public static void startActionSave(Context context, String enterType, String showType) {
        sContext_draft = context;
        Intent intent = new Intent(sContext_draft, SubmitService.class);
        intent.setAction(ACTION_SAVE);
        intent.putExtra(ARG_TYPE_SAVE, enterType);
        intent.putExtra(ARG_TYPE_SHOW_SAVE, showType);
        sContext_draft.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            List<String> strListValue = SPListUtil.getStrListValue(getContext(), SPListUtil.APPCONFIGINFO);
            if (strListValue.size() == 3) {
                mRoad_Q = strListValue.get(1).toString();
                mStation_Q = strListValue.get(2).toString();
            }

            final String action = intent.getAction();
            if (ACTION_SUBMIT.equals(action)) {

                String enterType = intent.getStringExtra(ARG_TYPE_SUBMIT);
                mShowType_submit = intent.getStringExtra(ARG_TYPE_SHOW_SUBMIT);

                if (mFile == null) {
                    mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "GreenPicture");
                    mFile.mkdirs();
                }
                mFilePath_str = mFile.getPath();


                mSubmitTime = TimeUtil.getCurrentTimeToDate();

                getListenerData(enterType);

                if (NetWorkManager.isnetworkConnected(sActivity)) {
                    postPictureAndJson(mSubmitTime);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(sActivity);
                    builder.setTitle("网络无连接");
                    builder.setMessage("是否保存为草稿");
                    builder.setPositiveButton("保存草稿", (dialog, which) -> {
                        save2Litepal(mSubmitTime, GlobalManager.TYPE_DRAFT_LITE, ACTION_SUBMIT, mShowType_submit);
                    });
                    builder.setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    });

                    ToastUtils.singleToast("网络无连接");
                }


            } else {
                if (ACTION_SAVE.equals(action)) {
                    String enterType = intent.getStringExtra(ARG_TYPE_SAVE);
                    String showType = intent.getStringExtra(ARG_TYPE_SHOW_SAVE);

                    getListenerData(enterType);

                    String saveTime = TimeUtil.getCurrentTimeToDate();
                    save2Litepal(saveTime, GlobalManager.TYPE_DRAFT_LITE, ACTION_SAVE, showType);
                }
            }
        }
    }

    */
/**
     * 拿到两个fragment中被监听的数据
     * 在提交至服务器以及保存草稿中都要用到
     *//*

    private void getListenerData(String enterType) {
        Logger.i("---------------" + enterType);

        DetailsFragment.setDetailsConnectListener((bean) -> {
            mDetailInfoBean_Q = bean;
        });

        if (ShowActivity.TYPE_MAIN_ENTER_SHOW.equals(enterType)) {

            PhotoFragment.setSelectedListListener((medias_sanzheng, medias_cheshen, medias_huozhao) -> {
                mLocalMedias_sanzheng_Q = medias_sanzheng;
                mLocalMedias_cheshen_Q = medias_cheshen;
                mSelectList_huowu = medias_huozhao;
            });
        } else if (ShowActivity.TYPE_DRAFT_ENTER_SHOW.equals(enterType)) {

            DetailsFragment.setSelectedListListener((medias_sanzheng, medias_cheshen, medias_huozhao) -> {
                mLocalMedias_sanzheng_Q = medias_sanzheng;
                mLocalMedias_cheshen_Q = medias_cheshen;
                mSelectList_huowu = medias_huozhao;
            });
        }


        //拿到checkFragment的数据
        CheckedFragment.setCheckedBeanConnectListener(bean -> {
            mCheckedBean_Q = bean;
            Logger.i(mCheckedBean_Q.toString());
        });

        ScanFragment.setScanConnectListener(bean -> {
            mScanInfoBean_Q = bean;
            Logger.i(mScanInfoBean_Q.toString());
        });

    }

    public static List<MultipartBody.Part> getBodyPart1(List<PathTitleBean> bitmapList, String type) {

        MultipartBody.Builder builder = new MultipartBody.Builder();


        for (int i = 0; i < bitmapList.size(); i++) {

            String mFilePath_str_new = null;
            try {
                Bitmap bitmap = BitmapUtil.convertToBitmap(bitmapList.get(i).getPath(), 700, 960);

                mFilePath_str_new = mFilePath_str + "/" + TimeUtil.getCurrentTime()
                        + type + (i + 1) + ".jpg";

                saveFile(bitmap, mFilePath_str_new);

            } catch (Exception e) {
                e.printStackTrace();

            }

            //String path = bitmapList.get(i).getPath();
            if (mFilePath_str_new != null) {
                Logger.i(mFilePath_str_new);
                File file = new File(mFilePath_str_new);//filePath 图片地址
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);//image/png
                //RequestBody imageBody = RequestBody.create(MediaType.parse("image/jpg"), file);//image/png
                builder.addFormDataPart(type + (i + 1), file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }

    //存储进SD卡
    public static void saveFile(Bitmap bm, String fileName) throws Exception {
        File dirFile = new File(fileName);
        //检测图片是否存在
        if (dirFile.exists()) {
            dirFile.delete();  //删除原图片
        }
        File myCaptureFile = new File(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        //100表示不进行压缩，70表示压缩率为30%
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }

    public class CharacterParser {
        private static int[] pyvalue = new int[]{-20319, -20317, -20304, -20295, -20292, -20283, -20265, -20257, -20242, -20230, -20051, -20036, -20032,
                -20026, -20002, -19990, -19986, -19982, -19976, -19805, -19784, -19775, -19774, -19763, -19756, -19751, -19746, -19741, -19739, -19728,
                -19725, -19715, -19540, -19531, -19525, -19515, -19500, -19484, -19479, -19467, -19289, -19288, -19281, -19275, -19270, -19263, -19261,
                -19249, -19243, -19242, -19238, -19235, -19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006, -19003, -18996, -18977, -18961,
                -18952, -18783, -18774, -18773, -18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697, -18696, -18526, -18518, -18501, -18490,
                -18478, -18463, -18448, -18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201, -18184, -18183, -18181, -18012, -17997, -17988,
                -17970, -17964, -17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752, -17733, -17730, -17721, -17703, -17701, -17697, -17692,
                -17683, -17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427, -17417, -17202, -17185, -16983, -16970, -16942, -16915, -16733,
                -16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470, -16465, -16459, -16452, -16448, -16433, -16429, -16427, -16423, -16419,
                -16412, -16407, -16403, -16401, -16393, -16220, -16216, -16212, -16205, -16202, -16187, -16180, -16171, -16169, -16158, -16155, -15959,
                -15958, -15944, -15933, -15920, -15915, -15903, -15889, -15878, -15707, -15701, -15681, -15667, -15661, -15659, -15652, -15640, -15631,
                -15625, -15454, -15448, -15436, -15435, -15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369, -15363, -15362, -15183, -15180,
                -15165, -15158, -15153, -15150, -15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121, -15119, -15117, -15110, -15109, -14941,
                -14937, -14933, -14930, -14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902, -14894, -14889, -14882, -14873, -14871, -14857,
                -14678, -14674, -14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429, -14407, -14399, -14384, -14379, -14368, -14355, -14353,
                -14345, -14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135, -14125, -14123, -14122, -14112, -14109, -14099, -14097, -14094,
                -14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907, -13906, -13905, -13896, -13894, -13878, -13870, -13859, -13847, -13831,
                -13658, -13611, -13601, -13406, -13404, -13400, -13398, -13395, -13391, -13387, -13383, -13367, -13359, -13356, -13343, -13340, -13329,
                -13326, -13318, -13147, -13138, -13120, -13107, -13096, -13095, -13091, -13076, -13068, -13063, -13060, -12888, -12875, -12871, -12860,
                -12858, -12852, -12849, -12838, -12831, -12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556, -12359, -12346, -12320, -12300,
                -12120, -12099, -12089, -12074, -12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798, -11781, -11604, -11589, -11536, -11358,
                -11340, -11339, -11324, -11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041, -11038, -11024, -11020, -11019, -11018, -11014,
                -10838, -10832, -10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533, -10519, -10331, -10329, -10328, -10322, -10315, -10309,
                -10307, -10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254};
        public static String[] pystr = new String[]{"a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng", "bi", "bian",
                "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao", "ce", "ceng", "cha", "chai", "chan", "chang", "chao", "che",
                "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong", "cou", "cu", "cuan",
                "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", "de", "deng", "di", "dian", "diao", "die", "ding", "diu", "dong", "dou", "du",
                "duan", "dui", "dun", "duo", "e", "en", "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu", "ga", "gai", "gan", "gang",
                "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo", "ha", "hai", "han", "hang",
                "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun", "huo", "ji", "jia", "jian",
                "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai", "kan", "kang", "kao", "ke", "ken",
                "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan", "lang", "lao", "le", "lei", "leng",
                "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "lv", "luan", "lue", "lun", "luo", "ma", "mai",
                "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu", "mo", "mou", "mu", "na", "nai",
                "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning", "niu", "nong", "nu", "nv", "nuan",
                "nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao", "pie", "pin", "ping", "po", "pu",
                "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao", "re",
                "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao", "se", "sen", "seng", "sha",
                "shai", "shan", "shang", "shao", "she", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang", "shui", "shun",
                "shuo", "si", "song", "sou", "su", "suan", "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "teng", "ti", "tian", "tiao",
                "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu", "xi",
                "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya", "yan", "yang", "yao", "ye", "yi",
                "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze", "zei", "zen", "zeng", "zha",
                "zhai", "zhan", "zhang", "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai", "zhuan", "zhuang", "zhui",
                "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"};
        private StringBuilder buffer;
        private String resource;
        private static CharacterParser characterParser = new CharacterParser();

        public static CharacterParser getInstance() {
            return characterParser;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        */
/**
         * 汉字转成ASCII码 * * @param chs * @return
         *//*

        private int getChsAscii(String chs) {
            int asc = 0;
            try {
                byte[] bytes = chs.getBytes("gb2312");
                if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
                    throw new RuntimeException("illegal resource string");
                }
                if (bytes.length == 1) {
                    asc = bytes[0];
                }
                if (bytes.length == 2) {
                    int hightByte = 256 + bytes[0];
                    int lowByte = 256 + bytes[1];
                    asc = (256 * hightByte + lowByte) - 256 * 256;
                }
            } catch (Exception e) {
                System.out.println("ERROR:ChineseSpelling.class-getChsAscii(String chs)" + e);
            }
            return asc;
        }

        */
/**
         * 单字解析 * * @param str * @return
         *//*

        public String convert(String str) {
            String result = null;
            int ascii = getChsAscii(str);
            if (ascii > 0 && ascii < 160) {
                result = String.valueOf((char) ascii);
            } else {
                for (int i = (pyvalue.length - 1); i >= 0; i--) {
                    if (pyvalue[i] <= ascii) {
                        result = pystr[i];
                        break;
                    }
                }
            }
            return result;
        }

        */
/**
         * 词组解析 * * @param chs * @return
         *//*

        public String getSelling(String chs) {
            String key, value;
            buffer = new StringBuilder();
            for (int i = 0; i < chs.length(); i++) {
                key = chs.substring(i, i + 1);
                if (key.getBytes().length >= 2) {
                    value = (String) convert(key);
                    if (value == null) {
                        value = "unknown";
                    }
                } else {
                    value = key;
                }
                buffer.append(value);
            }
            return buffer.toString();
        }

        public String getSpelling() {
            return this.getSelling(this.getResource());
        }
package com.android.htc.greenroad;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

        */
/**
         * Created by Administrator on 2017/6/21.
         *//*


        public class SpinnerPopupWindow {
            private Context mContext;

            private PopupWindow mPopWindow;

            private RecyclerView mRecyclerView;

            private RecyclerView.LayoutManager mLayoutManager;

            private RecyclerView.ItemDecoration mItemDecoration;

            private RecyclerView.ItemAnimator mItemAnimato;

            private View view;

            private boolean isFocusable;//获取焦点

            private boolean isOutsideTouchable;//点击外面是否可以消失

            private Drawable mDrawable;

            private int mWidth;

            private int mHeight;

            private RecyclerView.Adapter mAdapter;
            private static int sStype_id;

            */
/**
             * 初始化
             *
             * @param b
             *//*


            private SpinnerPopupWindow(Builder b) {
                buildPopupWindow(b);
            }

            public View getView() {
                return view;
            }

            public void setView(View view) {
                this.view = view;
            }

            private void buildPopupWindow(Builder b) {
                initParams(b);

                view = LayoutInflater.from(mContext).inflate(R.layout.view_spinner, null);

                mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_spinner_list);

                initRecyclerView();

                initPopWindows();
            }

            private void initParams(Builder b) {
                this.mContext = b.mContext;
                mLayoutManager = b.mLayoutManager;
                mItemDecoration = b.mItemDecoration;
                mItemAnimato = b.mItemAnimato;
                mAdapter = b.mAdapter;
                isFocusable = b.isFocusable;
                isOutsideTouchable = b.isOutsideTouchable;
                mDrawable = b.mDrawable;
                mWidth = b.mWidth;
                mHeight = b.mHeight;
            }


            */
/**
             * 初始化RecyclerView
             *//*

            private void initRecyclerView() {

                if (null == mItemAnimato)
                    mItemAnimato = new DefaultItemAnimator();

                mRecyclerView.setItemAnimator(mItemAnimato); //设置动画

                if (null != mItemDecoration)
                    mRecyclerView.addItemDecoration(mItemDecoration);//设置分割线

                if (null == mLayoutManager) {
                    if (sStype_id == 1) {
                        mLayoutManager = new GridLayoutManager(mContext, 4);
                    } else if (sStype_id == 0) {
                        mLayoutManager = new LinearLayoutManager(mContext);
                    }
                }

                mRecyclerView.setLayoutManager(mLayoutManager);

                if (null != mAdapter)
                    mRecyclerView.setAdapter(mAdapter);
            }


            */
/**
             * 初始化弹框
             *//*

            private void initPopWindows() {

                mPopWindow = new PopupWindow(view, mWidth, mHeight);

                mPopWindow.setFocusable(isFocusable);

                mPopWindow.setOutsideTouchable(isOutsideTouchable);

                if (null == mDrawable)
                    mDrawable = new BitmapDrawable();

                mPopWindow.setBackgroundDrawable(mDrawable);

            }

            */
/**
             * 显示Pop在view的右下方
             *//*

            public void showPopWindow(View view, int left) {
                int[] point = {0, 0};
                view.getLocationOnScreen(point);
                mPopWindow.showAtLocation(view, Gravity.LEFT | Gravity.TOP, left, point[1] + view.getHeight());
            }

            */
/**
             * 自定义显示的位置
             *//*

            public void showPopWindow(View parent, int gravity, int x, int y) {
                mPopWindow.showAtLocation(parent, gravity, x, y);
            }

            */
/**
             * 显示在正下方
             *
             * @param v
             *//*

            public void showPopWindowCenter(View v) {
                int[] point = {0, 0};
                v.getLocationOnScreen(point);
                //v距离左边的位置 - 弹框的宽度一半，弹框处于v的左边 超出v的宽度的一半
                mPopWindow.showAtLocation(view, Gravity.TOP | Gravity.LEFT, point[0] - mWidth / 2 + v.getWidth() / 2, point[1] + v.getHeight());
            }

            */
/**
             * 隐藏Pop
             *//*

            public void dismissPopWindow() {
                mPopWindow.dismiss();
            }

            public void setFocusable(boolean isFocusable) {
                if (isFocusable) {
                    mPopWindow.setFocusable(true);
                } else {
                    mPopWindow.setFocusable(false);
                }

            }

            public boolean isShowing() {
                return mPopWindow.isShowing();
            }

            public static class Builder {
                private Context mContext;

                private RecyclerView.LayoutManager mLayoutManager;

                private RecyclerView.ItemDecoration mItemDecoration;
                private RecyclerView.ItemAnimator mItemAnimato;
                private static RecyclerView.Adapter mAdapter;
                private boolean isFocusable;//获取焦点
                private boolean isOutsideTouchable;//点击外面是否可以消失
                private Drawable mDrawable;
                private int mWidth;
                private int mHeight;
                private GridLayoutManager mGridLayoutManager;

                public GridLayoutManager getGridLayoutManager() {
                    return mGridLayoutManager;
                }

                public void setGridLayoutManager(GridLayoutManager gridLayoutManager) {
                    mGridLayoutManager = gridLayoutManager;
                }

                public Builder(Context context) {
                    this.mContext = context;
                }

                public SpinnerPopupWindow build() {
                    return new SpinnerPopupWindow(this);
                }

                public RecyclerView.LayoutManager getmLayoutManager() {
                    return mLayoutManager;
                }

                public Builder setmLayoutManager(RecyclerView.LayoutManager mLayoutManager, int stype_id) {
                    sStype_id = stype_id;
                    this.mLayoutManager = mLayoutManager;
                    return this;
                }


                public RecyclerView.ItemDecoration getmItemDecoration() {
                    return mItemDecoration;
                }

                public Builder setmItemDecoration(RecyclerView.ItemDecoration mItemDecoration) {
                    this.mItemDecoration = mItemDecoration;
                    return this;
                }

                public RecyclerView.ItemAnimator getmItemAnimato() {
                    return mItemAnimato;
                }

                public Builder setmItemAnimato(RecyclerView.ItemAnimator mItemAnimato) {
                    this.mItemAnimato = mItemAnimato;
                    return this;
                }

                public static RecyclerView.Adapter getmAdapter() {
                    return mAdapter;
                }

                public Builder setmAdapter(RecyclerView.Adapter mAdapter) {
                    this.mAdapter = mAdapter;
                    return this;
                }

                public boolean isFocusable() {
                    return isFocusable;
                }

                public Builder setFocusable(boolean focusable) {
                    isFocusable = focusable;
                    return this;
                }

                public boolean isOutsideTouchable() {
                    return isOutsideTouchable;
                }

                public Builder setOutsideTouchable(boolean outsideTouchable) {
                    isOutsideTouchable = outsideTouchable;
                    return this;
                }

                public Drawable getmDrawable() {
                    return mDrawable;
                }

                public Builder setmDrawable(Drawable mDrawable) {
                    this.mDrawable = mDrawable;
                    return this;
                }

                public int getmWidth() {
                    return mWidth;
                }

                public Builder setmWidth(int mWidth) {
                    this.mWidth = mWidth;
                    return this;
                }

                public int getmHeight() {
                    return mHeight;
                }


                public Builder setmHeight(int mHeight) {
                    this.mHeight = mHeight;
                    return this;
                }

            }
        }

        */
/**
         * 这是一个缓存工具类，提供数据的本地化方法
         *//*

        public class ACache {

            public static final String GOODSACACHE_All = "goodsacache_all";
            public static final String GOODSACACHE_SHUCAI = "goodsacache_shucai";
            public static final String GOODSACACHE_SHUIGUO = "goodsacache_shuiguo";
            public static final String GOODSACACHE_SHUICHANPIN = "goodsacache_shuichanpin";
            public static final String GOODSACACHE_CHUQIN = "goodsacache_chuqin";
            public static final String GOODSACACHE_ROUDANNAI = "goodsacache_roudannai";
            public static final String GOODSACACHE_ZALIANG = "goodsacache_zaliang";


            public static String TEXTGOODS = "textgoods";
            public static String TEXTNUMBER = "textnumber";
            public static String TEXTCONCLUSION = "textconclusion";

            public static final int TIME_HOUR = 60 * 60;
            public static final int TIME_DAY = TIME_HOUR * 24;
            private static final int MAX_SIZE = 1000 * 1000 * 50; // 50 mb
            private static final int MAX_COUNT = Integer.MAX_VALUE; // 不限制存放数据的数量
            private static Map<String, ACache> mInstanceMap = new HashMap<String, ACache>();
            private ACacheManager mCache;

            public static ACache get(Context ctx) {
                return get(ctx, "ACache");
            }

            public static ACache get(Context ctx, String cacheName) {
                File f = new File(ctx.getCacheDir(), cacheName);
                return get(f, MAX_SIZE, MAX_COUNT);
            }

            public static ACache get(File cacheDir) {
                return get(cacheDir, MAX_SIZE, MAX_COUNT);
            }

            public static ACache get(Context ctx, long max_zise, int max_count) {
                File f = new File(ctx.getCacheDir(), "ACache");
                return get(f, max_zise, max_count);
            }

            public static ACache get(File cacheDir, long max_zise, int max_count) {
                ACache manager = mInstanceMap.get(cacheDir.getAbsoluteFile() + myPid());
                if (manager == null) {
                    manager = new ACache(cacheDir, max_zise, max_count);
                    mInstanceMap.put(cacheDir.getAbsolutePath() + myPid(), manager);
                }
                return manager;
            }

            private static String myPid() {
                return "_" + android.os.Process.myPid();
            }

            private ACache(File cacheDir, long max_size, int max_count) {
                if (!cacheDir.exists() && !cacheDir.mkdirs()) {
                    throw new RuntimeException("can't make dirs in " + cacheDir.getAbsolutePath());
                }
                mCache = new ACacheManager(cacheDir, max_size, max_count);
            }

            */
/**
             * Provides a means to save a cached file before the data are available.
             * Since writing about the file is complete, and its close method is called,
             * its contents will be registered in the cache. Example of use:
             * <p>
             * ACache cache = new ACache(this) try { OutputStream stream =
             * cache.put("myFileName") stream.write("some bytes".getBytes()); // now
             * update cache! stream.close(); } catch(FileNotFoundException e){
             * e.printStackTrace() }
             *//*

            class xFileOutputStream extends FileOutputStream {
                File file;

                public xFileOutputStream(File file) throws FileNotFoundException {
                    super(file);
                    this.file = file;
                }

                public void close() throws IOException {
                    super.close();
                    mCache.put(file);
                }
            }

            // =======================================
            // ============ String数据 读写 ==============
            // =======================================

            */
/**
             * 保存 String数据 到 缓存中
             *
             * @param key   保存的key
             * @param value 保存的String数据
             *//*

            public void put(String key, String value) {
                File file = mCache.newFile(key);
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter(file), 1024);
                    out.write(value);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    mCache.put(file);
                }
            }

            */
/**
             * 保存 String数据 到 缓存中
             *
             * @param key      保存的key
             * @param value    保存的String数据
             * @param saveTime 保存的时间，单位：秒
             *//*

            public void put(String key, String value, int saveTime) {
                put(key, Utils.newStringWithDateInfo(saveTime, value));
            }

            */
/**
             * 读取 String数据
             *
             * @param key
             * @return String 数据
             *//*

            public String getAsString(String key) {
                File file = mCache.get(key);
                if (!file.exists())
                    return null;
                boolean removeFile = false;
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader(file));
                    String readString = "";
                    String currentLine;
                    while ((currentLine = in.readLine()) != null) {
                        readString += currentLine;
                    }
                    if (!Utils.isDue(readString)) {
                        return Utils.clearDateInfo(readString);
                    } else {
                        removeFile = true;
                        return null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (removeFile)
                        remove(key);
                }
            }

            // =======================================
            // ============= JSONObject 数据 读写 ==============
            // =======================================

            */
/**
             * 保存 JSONObject数据 到 缓存中
             *
             * @param key   保存的key
             * @param value 保存的JSON数据
             *//*

            public void put(String key, JSONObject value) {
                put(key, value.toString());
            }

            */
/**
             * 保存 JSONObject数据 到 缓存中
             *
             * @param key      保存的key
             * @param value    保存的JSONObject数据
             * @param saveTime 保存的时间，单位：秒
             *//*

            public void put(String key, JSONObject value, int saveTime) {
                put(key, value.toString(), saveTime);
            }

            */
/**
             * 读取JSONObject数据
             *
             * @param key
             * @return JSONObject数据
             *//*

            public JSONObject getAsJSONObject(String key) {
                String JSONString = getAsString(key);
                try {
                    JSONObject obj = new JSONObject(JSONString);
                    return obj;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            // =======================================
            // ============ JSONArray 数据 读写 =============
            // =======================================

            */
/**
             * 保存 JSONArray数据 到 缓存中
             *
             * @param key   保存的key
             * @param value 保存的JSONArray数据
             *//*

            public void put(String key, JSONArray value) {
                put(key, value.toString());
            }

            */
/**
             * 保存 JSONArray数据 到 缓存中
             *
             * @param key      保存的key
             * @param value    保存的JSONArray数据
             * @param saveTime 保存的时间，单位：秒
             *//*

            public void put(String key, JSONArray value, int saveTime) {
                put(key, value.toString(), saveTime);
            }

            */
/**
             * 读取JSONArray数据
             *
             * @param key
             * @return JSONArray数据
             *//*

            public JSONArray getAsJSONArray(String key) {
                String JSONString = getAsString(key);
                try {
                    JSONArray obj = new JSONArray(JSONString);
                    return obj;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            // =======================================
            // ============== byte 数据 读写 =============
            // =======================================

            */
/**
             * 保存 byte数据 到 缓存中
             *
             * @param key   保存的key
             * @param value 保存的数据
             *//*

            public void put(String key, byte[] value) {
                File file = mCache.newFile(key);
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file);
                    out.write(value);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    mCache.put(file);
                }
            }

            public class GoodsFragment extends Fragment implements TextChangeWatcher.AfterTextListener, RadioGroup.OnCheckedChangeListener {

                private static final String GOODS_DIR_SHUCAI = "goods_shucai";
                private static final String GOODS_DIR_SHUIGUO = "goods_shuiguo";
                private static final String GOODS_DIR_SHUICHANPIN = "goods_shuichanpin";
                private static final String GOODS_DIR_CHUQIN = "goods_chuqin";
                private static final String GOODS_DIR_ROUDANNAI = "goods_roudannai";
                private static final String GOODS_DIR_ZALIANG = "goods_zaliang";
                private static final String GOODS_DIR_QITA = "goods_qita";

                private static final String KIND_SHUCAI = "kind_shucai";
                private static final String KIND_SHUIGUO = "kind_shuiguo";
                private static final String KIND_SHUICHANPIN = "kind_shuichanpin";
                private static final String KIND_CHUQIN = "kind_xuqin";
                private static final String KIND_ROUDANNAI = "kind_roudannai";
                private static final String KIND_ZALIANG = "kind_zaliang";
                private static final String KIND_QITA = "kind_qita";
                private static GoodsFragment sFragment;
                Unbinder unbinder;

                private static EditText mEditText;

                @BindView(R.id.recycler_view_goods_sure)
                RecyclerView mRecyclerView;
                @BindView(R.id.edit_text_qita)
                EditText mEditTextQita;
                @BindView(R.id.btn_sure_qita)
                Button mBtnSureQita;
                @BindView(R.id.rl_edit_qita)
                RelativeLayout mRlEditQita;

                private SureGoodsAdapter mGoodsAdapter;

                private String mCar_goods;

                private String goodsText;
                private static SerializableMain2Sure mMain2Sure;
                private static String mGoods_i;
                private AssetManager mAssetManager;

                private String[] mGoodsAsset_shucai;
                private String[] mGoodsAsset_shuiguo;
                private String[] mGoodsAsset_shuichanpin;
                private String[] mGoodsAsset_chuqin;
                private String[] mGoodsAsset_roudannai;
                private String[] mGoodsAsset_zaliang;

                private ArrayList<SerializableGoods> mGoodsAllList;
                private ArrayList<SerializableGoods> mGoodsShuCais;
                private ArrayList<SerializableGoods> mGoodsShuiGuos;
                private ArrayList<SerializableGoods> mGoodsShuiChanPins;
                private ArrayList<SerializableGoods> mGoodsChuQins;
                private ArrayList<SerializableGoods> mGoodsRouDanNais;
                private ArrayList<SerializableGoods> mGoodsZaLiangs;

                private ArrayList<SerializableGoods> mAsObject_all;
                private ArrayList<SerializableGoods> mAsObject_shucai;
                private ArrayList<SerializableGoods> mAsObject_shuiguo;
                private ArrayList<SerializableGoods> mAsObject_shuichanpin;
                private ArrayList<SerializableGoods> mAsObject_chuqin;
                private ArrayList<SerializableGoods> mAsObject_roudannai;
                private ArrayList<SerializableGoods> mAsObject_zaliang;

                private String[] mGoodsName_shucais;
                private String[] mGoodsName_shuiguos;
                private String[] mGoodsName_shuichanpins;
                private String[] mGoodsName_chuqins;
                private String[] mGoodsName_roudannais;
                private String[] mGoodsName_zaliangs;

                private String current_kind;

                private RecyclerView mGoodTextRecycler;
                private static ArrayList<String> mTextList;
                private GoodsTextAdapter mTextAdapter;
                private static StringBuilder sBuilder;
                private LinearLayoutManager mLayoutManager;
                private ArrayList<SerializableGoods> mCurrentGoodsList;


                public static GoodsFragment newInstance(String goods) {
                    if (sFragment == null) {
                        sFragment = new GoodsFragment();
                    }
                    mGoods_i = goods;
                    return sFragment;
                }

                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);

                    mGoodsName_shucais = getResources().getStringArray(R.array.science_array_shucai);
                    mGoodsName_shuiguos = getResources().getStringArray(R.array.science_array_shuiguo);
                    mGoodsName_shuichanpins = getResources().getStringArray(R.array.science_array_shuichanpin);
                    mGoodsName_chuqins = getResources().getStringArray(R.array.science_array_chuqin);
                    mGoodsName_roudannais = getResources().getStringArray(R.array.science_array_roudannai);
                    mGoodsName_zaliangs = getResources().getStringArray(R.array.science_array_zaliang);

                    mAsObject_all = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_All);

                    mAsObject_shucai = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_SHUCAI);

                    mAsObject_shuiguo = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_SHUIGUO);

                    mAsObject_shuichanpin = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_SHUICHANPIN);

                    mAsObject_chuqin = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_CHUQIN);

                    mAsObject_roudannai = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_ROUDANNAI);


                    mAsObject_zaliang = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.GOODSACACHE_ZALIANG);


                    mGoodsAllList = new ArrayList<>();
                    mGoodsShuCais = new ArrayList<>();
                    mGoodsShuiGuos = new ArrayList<>();
                    mGoodsShuiChanPins = new ArrayList<>();
                    mGoodsChuQins = new ArrayList<>();
                    mGoodsRouDanNais = new ArrayList<>();
                    mGoodsZaLiangs = new ArrayList<>();

                    mAssetManager = getContext().getAssets();
                    try {
                        mGoodsAsset_shucai = mAssetManager.list(GOODS_DIR_SHUCAI);
                        mGoodsAsset_shuiguo = mAssetManager.list(GOODS_DIR_SHUIGUO);
                        mGoodsAsset_shuichanpin = mAssetManager.list(GOODS_DIR_SHUICHANPIN);
                        mGoodsAsset_chuqin = mAssetManager.list(GOODS_DIR_CHUQIN);
                        mGoodsAsset_roudannai = mAssetManager.list(GOODS_DIR_ROUDANNAI);
                        mGoodsAsset_zaliang = mAssetManager.list(GOODS_DIR_ZALIANG);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Logger.i(e.getMessage());
                        return;
                    }
                }

                private void initGoodsData() {

                    if (mTextList == null) {
                        mTextList = new ArrayList<>();
                    } else {
                        mTextList.clear();
                    }
                    if (mGoods_i != null && mGoods_i.length() != 0) {

                        String[] goodsName = mGoods_i.split(";");

                        for (int i = 0; i < goodsName.length; i++) {
                            mTextList.add(goodsName[i]);
                        }
                    }


                    initGoodsJudet(mAsObject_shucai, mGoodsShuCais, mGoodsAsset_shucai, mGoodsName_shucais, GOODS_DIR_SHUCAI);
                    initGoodsJudet(mAsObject_shuiguo, mGoodsShuiGuos, mGoodsAsset_shuiguo, mGoodsName_shuiguos, GOODS_DIR_SHUIGUO);
                    initGoodsJudet(mAsObject_shuichanpin, mGoodsShuiChanPins, mGoodsAsset_shuichanpin, mGoodsName_shuichanpins, GOODS_DIR_SHUICHANPIN);
                    initGoodsJudet(mAsObject_chuqin, mGoodsChuQins, mGoodsAsset_chuqin, mGoodsName_chuqins, GOODS_DIR_CHUQIN);
                    initGoodsJudet(mAsObject_roudannai, mGoodsRouDanNais, mGoodsAsset_roudannai, mGoodsName_roudannais, GOODS_DIR_ROUDANNAI);
                    initGoodsJudet(mAsObject_zaliang, mGoodsZaLiangs, mGoodsAsset_zaliang, mGoodsName_zaliangs, GOODS_DIR_ZALIANG);

                    if (mGoodsAllList != null && mGoodsAllList.size() != 0) {
                        mGoodsAllList.clear();
                    }
                    int all = mGoodsName_shucais.length + mGoodsName_shuiguos.length +
                            mGoodsName_shuichanpins.length + mGoodsName_chuqins.length +
                            mGoodsName_roudannais.length + mGoodsName_zaliangs.length;
                    if (mAsObject_all != null && mAsObject_all.size() == (all)) {
                        mGoodsAllList.addAll(mAsObject_all);
                    } else {
                        mGoodsAllList.addAll(mGoodsShuCais);
                        mGoodsAllList.addAll(mGoodsShuiGuos);
                        mGoodsAllList.addAll(mGoodsShuiChanPins);
                        mGoodsAllList.addAll(mGoodsChuQins);
                        mGoodsAllList.addAll(mGoodsRouDanNais);
                        mGoodsAllList.addAll(mGoodsZaLiangs);
                    }
                }


                public static CheckedFragment newInstance(String enterType) {
                    if (sFragment == null) {
                        sFragment = new CheckedFragment();
                    }
                    sEnterType = enterType;
                    return sFragment;
                }

                public static CheckedFragment newInstance(String enterType, SupportChecked supportChecked) {
                    if (sFragment == null) {
                        sFragment = new CheckedFragment();
                    }
                    sEnterType = enterType;
                    sSupportChecked = supportChecked;
                    return sFragment;
                }

                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);

                }

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    View view = inflater.inflate(R.layout.fragment_checked, container, false);
                    unbinder = ButterKnife.bind(this, view);

                    initData();
                    initView(view);

                    //初始化各个checkbox的状态
                    initRecycler();

                    return view;
                }

                private void initRecycler() {
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    mChecksAdapter = new SiteChecksAdapter(getContext(), (ArrayList<String>) mCheckOperators, mDimension, mOperators);

                    mRecycleCheck.setLayoutManager(manager);
                    mRecycleCheck.setAdapter(mChecksAdapter);

                }

                private void initData() {
                    mOperatorList = DataSupport.findAll(SupportOperator.class);
                    mOperators = new ArrayList<>();
                    for (int i = 0; i < mOperatorList.size(); i++) {
                        mOperators.add(mOperatorList.get(i).getJob_number() + "/" +
                                mOperatorList.get(i).getOperator_name());
                    }

                    mDimension = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120,
                            getResources().getDisplayMetrics());

                }

                private void initView(View view) {
                    mTextConclusionView = (TextView) view.findViewById(R.id.checked_conclusion_text);
                    mEditDescriptionView = (EditText) view.findViewById(R.id.checked_description_text);
                    mToggleIsRoom = (ToggleButton) view.findViewById(R.id.toggle_is_room);
                    mToggleIsFree = (ToggleButton) view.findViewById(R.id.toggle_is_free);

                    mSiteLogin = (TextView) view.findViewById(R.id.site_login_operator);

                    mTextConclusionView.setOnClickListener(this);
                    mSiteLogin.setOnClickListener(this);

                    if (ShowActivity.TYPE_DRAFT_ENTER_SHOW.equals(sEnterType)) {
                        ConclusionActivity.notifyDataChangeFromDraft(sSupportChecked.getConclusion());
                        mEditDescriptionView.setText(sSupportChecked.getDescription());
                        //默认是是   是为1   点击是否 否为0（有点绕）
                        mToggleIsRoom.setChecked(sSupportChecked.getIsRoom() == 0 ? true : false);
                        mToggleIsFree.setChecked(sSupportChecked.getIsFree() == 0 ? true : false);
                        mCheckOperators = sSupportChecked.getSiteChecks();
                        mLoginOperator = sSupportChecked.getSiteLogin();
                    } else if (ShowActivity.TYPE_MAIN_ENTER_SHOW.equals(sEnterType)) {
                        mCheckOperators = setCheckOperatorInfo("check_select = ?");
                        mLoginOperator = setLoginOperatorInfo("login_select = ?");
                    }
                    mSiteLogin.setText(mLoginOperator);
                }


                private String setLoginOperatorInfo(String condition) {
                    List<SupportOperator> operatorList = DataSupport.where(condition, "1").find(SupportOperator.class);
                    if (operatorList.size() != 0) {
                        Logger.i(operatorList.toString());
                        String mJob_number = operatorList.get(0).getJob_number();
                        String mOperator_name = operatorList.get(0).getOperator_name();
                        return mJob_number + "/" + mOperator_name;
                    }
                    return "500001/苏三";
                }

                private List<String> setCheckOperatorInfo(String condition) {
                    List<SupportOperator> operatorList = DataSupport.where(condition, "1").find(SupportOperator.class);
                    List<String> checkList = null;
                    if (checkList == null) {
                        checkList = new ArrayList<>();
                    } else {
                        checkList.clear();
                    }
                    for (int i = 0; i < operatorList.size(); i++) {
                        String mJob_number = operatorList.get(i).getJob_number();
                        String mOperator_name = operatorList.get(i).getOperator_name();
                        checkList.add(mJob_number + "/" + mOperator_name);
                    }

                    return checkList;
                }


                @Override
                public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                    super.onViewCreated(view, savedInstanceState);

                }


                public static void setCheckedBeanConnectListener(CheckedBeanConnectListener listener) {
                    if (mTextConclusionView != null) {
                        sConclusionQ = mTextConclusionView.getText().toString().trim();
                    }
                    if (mEditDescriptionView != null) {
                        sDescriptionQ = mEditDescriptionView.getText().toString().trim();
                    }
                    //拿到现场检查人,登记人
                    if (mToggleIsFree != null) {
                        sIsFree = mToggleIsFree.isChecked();
                    }
                    //拿到容积,是否免费的信息
                    if (mToggleIsRoom != null) {
                        sIsRoom = mToggleIsRoom.isChecked();
                    }
                    CheckedBean bean = new CheckedBean();

                    bean.setConclusion(sConclusionQ);
                    bean.setDescription(sDescriptionQ);
                    if (mCheckOperators != null) {
                        bean.setSiteChecks(mCheckOperators);
                    }
                    if (mLoginOperator != null) {
                        bean.setSiteLogin(mSiteLogin.getText().toString());
                    }
                    //默认是"是"的状态,所以点击后为true,返回0"否"的状态
                    bean.setIsRoom(sIsRoom ? 0 : 1);
                    bean.setIsFree(sIsFree ? 0 : 1);

                    Logger.i(bean.toString());
                    listener.beanConnect(bean);

                }


*/
