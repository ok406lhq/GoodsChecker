package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.GreenRoadApplication;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.httpresultbean.HttpResult;
import com.wolf.zero.greenroad.https.RequestLogin;
import com.wolf.zero.greenroad.interfacy.TextChangeWatcher;
import com.wolf.zero.greenroad.litepalbean.SupportLoginInfo;
import com.wolf.zero.greenroad.litepalbean.TeamItem;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.presenter.NetWorkManager;
import com.wolf.zero.greenroad.tools.PermissionUtils;
import com.wolf.zero.greenroad.tools.SPUtils;
import com.wolf.zero.greenroad.tools.TimeUtil;
import com.wolf.zero.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextChangeWatcher.AfterTextListener {


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
        PermissionUtils.verifyStoragePermissions(mActivity);
        // TODO: 2017/8/5 客户端的认证信息，移至注册账号是的返回储存

//        mLoginVersion.setText("e绿通 V" + DevicesInfoUtils.getInstance().getVersion(mActivity));
    }

    private void initView() {

        mEt_user_name.addTextChangedListener(new TextChangeWatcher(this));


        mBt_login = (Button) findViewById(R.id.bt_login);

        mBt_login.setOnClickListener(mActivity);
//        mLoginRegister.setOnClickListener(mActivity);


    }

    @Override
    protected void onResume() {
        super.onResume();
        String user = (String) SPUtils.get(this, GlobalManager.USERNAME, "");
        mEt_user_name.setText(user);

        SupportLoginInfo loginInfo = DataSupport.where("username = ?", user).findFirst(SupportLoginInfo.class);

        if (loginInfo == null) {
            mCheckBox.setChecked(false);
        } else {
            mCheckBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
//                licence();
                startMainActivity(v);
                break;

            default:
                break;

        }
    }

//    private void licence() {
//        Intent intent = new Intent(LoginActivity.this, com.kernal.plateid.MainActivity.class);
//        startActivity(intent);
//    }

    private void startMainActivity(View view) {
        PermissionUtils.verifyStoragePermissions(mActivity);
        String username = mEt_user_name.getText().toString().trim();
        String password = mEt_password.getText().toString().trim();
//        String username = "qqqq";
//        String password = "123456";
        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mEt_user_name.setError(getString(R.string.error_field_required));
            mEt_user_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mEt_password.setError(getString(R.string.error_invalid_password));
            mEt_password.requestFocus();
            return;
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

//        mRlProgressLogin.setVisibility(View.VISIBLE);

        mIsConnected = NetWorkManager.isnetworkConnected(this);
        // TODO: 2018/2/4
        if (mIsConnected) {
            String url = (String) SPUtils.get(GreenRoadApplication.sApplication, SPUtils.CONFIG_PORT, "88");
            if ("88".equals(url)) {
                UrlAlert("登录失败,当前网络端口未配置");
                return;
            }
            loginFromNet(username, password);
//            loginFromNet("qqqq", "123456");

        } else {


            loginFromNotNet(username, password);


            ToastUtils.singleToast("当前网络无连接,请检查网络连接状态");


//            List<String> loginList = SPListUtil.getStrListValue(mActivity, SPListUtil.LOGINNFO);
//            if (loginList == null || loginList.size() == 0) {
//                ToastUtils.singleToast("本地无账号缓存，请连接网络登录");
//            } else if (loginList.size() == 3) {
//                getTimeGap(loginList, username, password);
//            }
//            mRlProgressLogin.setVisibility(View.GONE);
        }

    }

    /**
     * 无网状态下登录,拿到上次有网登录时记录更新的登录状态
     * TimeGap做时间差判断
     *
     * @param
     * @param username
     */
    private void loginFromNotNet(String username, String password) {
        SupportLoginInfo loginInfo = DataSupport.where("username=?", username).findFirst(SupportLoginInfo.class);
        if (loginInfo == null) {
            Logger.i("数据库没有记录");
            ToastUtils.singleToast("本地无账号缓存，请连接网络登录");
            mEt_user_name.setText("");
            mEt_password.setText("");
            mCheckBox.setChecked(false);
        } else {

            String currentTimeToDate = TimeUtil.getCurrentTimeToDate();
            String save_time = loginInfo.getLoginTime();
            int timeGap = TimeUtil.differentDaysByMillisecond(save_time, currentTimeToDate);

            Logger.i(loginInfo.toString());
            if (password.equals(loginInfo.getPassword())) {
                if (timeGap > TIMEGAP) {

                    Logger.i("账号保存时间已经超过有效期,请在有网情况下登录");
                    ToastUtils.singleToast("账号保存时间已经超过有效期,请在有网情况下登录");
//                    SPUtils.putAndApply(LoginActivity.this, GlobalManager.USERNAME, username);

                } else {
                    Logger.i("账号密码都正确,可无网登录");
                    SPUtils.putAndApply(LoginActivity.this, GlobalManager.USERNAME, username);
                    login2MainActivity();
                }
            } else {
                Logger.i("该账号有缓存,但输入密码不正确");
                ToastUtils.singleToast("该账号有缓存,但输入密码不正确");
            }
        }
    }
/*


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
     * 有网络的状态下登录
     *
     * @param username
     * @param password
     */
    private void loginFromNet(String username, String password) {
        Subscriber<HttpResult> subscriber = new Subscriber<HttpResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.i(e.getMessage());
                mRlProgressLogin.setVisibility(View.GONE);
                UrlAlert("登录失败,网络存在异常");

            }

            @Override
            public void onNext(HttpResult httpResult) {
                int code = httpResult.getCode();
//                String msg = httpResult.getMsg();
//                Logger.i(code + msg+httpResult.toString());
                if (code == 200) {
                    getLoginInfo(username, password);
                    String line = httpResult.getData().getLine();
                    String station = httpResult.getData().getStation();
                    List<String> laneList = httpResult.getData().getLanes();
//                    for (int i = 0; i < laneList.size(); i++) {
//                        Logger.i(laneList.get(i));
//                    }
                    Logger.i(httpResult.getData().toString());
                    SPUtils.putAndApply(LoginActivity.this, GlobalManager.USERNAME, username);
//                    if (itemList.size() == 0) {
                    List<TeamItem> itemList = DataSupport.where("username=?", username).find(TeamItem.class);
                    if (itemList.size() != 0) {
                        TeamItem teamItem = new TeamItem();
//                    teamItem.setUsername(username);
                        teamItem.setLine(line);
                        teamItem.setStation(station);
                        teamItem.setLanes(laneList);
                        teamItem.setDefaultLane(laneList.get(0));
                        teamItem.updateAll("username = ?", username);
                    } else {
                        TeamItem teamItem = new TeamItem();
                        teamItem.setUsername(username);
                        teamItem.setLine(line);
                        teamItem.setStation(station);
                        teamItem.setLanes(laneList);
                        teamItem.setDefaultLane(laneList.get(0));
                        teamItem.save();
                    }

//                    List<TeamItem> all = DataSupport.findAll(TeamItem.class);
//                    Logger.i(all.size() + "");
//                    for (int i = 0; i < all.size(); i++) {
//                        Logger.i(all.get(i).toString());
//                        Logger.i(all.get(i).getUsername());
//                    }
                    login2MainActivity();

                } else if (code == 201) {
                    mEt_user_name.setError("账号不存在");
                    mEt_user_name.requestFocus();
                    mRlProgressLogin.setVisibility(View.GONE);
                } else if (code == 202) {
                    mEt_password.setError("密码错误");
                    mEt_password.requestFocus();
                    mRlProgressLogin.setVisibility(View.GONE);
                } else if (code == 203) {
                    mEt_user_name.setError("此账号已登录,请检查账号安全");
                    mEt_user_name.requestFocus();
                    mRlProgressLogin.setVisibility(View.GONE);
                } else if (code == 204) {
                    mEt_user_name.setError("此账号被禁用");
                    mEt_user_name.requestFocus();
                    mRlProgressLogin.setVisibility(View.GONE);
                } else if (code == 205) {
                    mEt_user_name.setError("该终端尚未被注册");
                    mEt_user_name.requestFocus();
                    mRlProgressLogin.setVisibility(View.GONE);
                } else {
                    mRlProgressLogin.setVisibility(View.GONE);
                    ToastUtils.singleToast("网络异常,请重新登录");
                }
                //  mRlProgressLogin.setVisibility(View.GONE);
            }
        };
        RequestLogin.getInstance().
                postLogin(subscriber, username, password);

    }

    /**
     * 弹出域名没有配置或者配置错误等窗口
     */
    private void UrlAlert(String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setTitle(title);
        dialog.setIcon(getResources().getDrawable(R.drawable.alert_faild_icon));
        dialog.setMessage("点击确定重新配置网络端口\n点击取消请尝试再次登录\n\n当前网络端口:" + SPUtils.get(GreenRoadApplication.sApplication, SPUtils.CONFIG_PORT, "88"));
        dialog.setNegativeButton("取消", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        });
        dialog.setPositiveButton("确定", (dialogInterface, i) -> {
            mEt_user_name.setText("");
            mEt_password.setText("");
            mCheckBox.setChecked(false);
            LineConfigActivity.actionStart(LoginActivity.this, GlobalManager.OTHER2PORT);
            dialogInterface.dismiss();
        });
        dialog.show();
//        ToastUtils.singleToast("网络异常,请重新登录");
    }

    /**
     * 登陆成功返回该用户所有的需要的信息Info
     *
     * @param username
     * @param password
     */
    private void getLoginInfo(String username, String password) {
        SupportLoginInfo loginInfo = DataSupport.where("username = ?", username).findFirst(SupportLoginInfo.class);
//            int timeGap = TimeUtil.differentDaysByMillisecond(save_time, currentTimeToDate);
        String currentTimeToDate = TimeUtil.getCurrentTimeToDate();
        if (loginInfo == null) {

            //只有在CheckBox选中时才保存
            if (mCheckBox.isChecked()) {
                SupportLoginInfo supportLoginInfo = new SupportLoginInfo();
                supportLoginInfo.setUsername(username);
                supportLoginInfo.setPassword(password);
                supportLoginInfo.setLoginTime(currentTimeToDate);
//                supportLoginInfo.setIsCheck(1);
                supportLoginInfo.save();

            }

        } else {
            //只有在CheckBox选中时才更新
            if (mCheckBox.isChecked()) {

                SupportLoginInfo supportLoginInfo = new SupportLoginInfo();
                supportLoginInfo.setUsername(username);
                supportLoginInfo.setLoginTime(password);
                supportLoginInfo.setLoginTime(currentTimeToDate);
                supportLoginInfo.updateAll("username = ?", username);
            } else {
                //CheckBox未选中则删除保存记录
                DataSupport.deleteAll(SupportLoginInfo.class, "username = ?", username);
            }


        }

        List<SupportLoginInfo> all = DataSupport.findAll(SupportLoginInfo.class);
        for (int i = 0; i < all.size(); i++) {
            Logger.i(all.get(i).toString());
        }
    }


    /**
     * 登录成功进入mainActivity
     */
    private void login2MainActivity() {

        MainActivity.actionStart(LoginActivity.this, GlobalManager.LOGINTOMAIN);
        finish();
        //  mRlProgressLogin.setVisibility(View.GONE);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    /**
     * 监听username输入框内容  , 如果数据库有缓存 , 则checkBox显示选中
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {
        String userName = mEt_user_name.getText().toString().trim();
        Logger.i("userName :" + userName);
        SupportLoginInfo loginInfo = DataSupport.where("username = ?", userName).findFirst(SupportLoginInfo.class);
        if (loginInfo != null) {
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }
    }
/*

    private void loginTrueValue(String username) {
        if ("qqqq".equals(username)) {
            SPUtils.putAndApply(this, GlobalManager.USERNAME, username);
            List<TeamItem> itemList = DataSupport.where("username=?", username).find(TeamItem.class);
            if (itemList.size() == 0) {
                TeamItem teamItem = new TeamItem();
                teamItem.setUsername(username);
                teamItem.setLine("上杭线");
                teamItem.setStation("安庆收费站");
                //登陆成功再做一个路线的请求
                ArrayList<String> lanes = new ArrayList<>();
                lanes.add("X01");
                lanes.add("X02");
                lanes.add("X03");
                teamItem.setLanes(lanes);

                teamItem.setDefaultLane("X01");

                teamItem.save();

            }


            login2MainActivity();
        } else {
            List<TeamItem> itemList = DataSupport.where("username=?", username).find(TeamItem.class);
            SPUtils.putAndApply(this, GlobalManager.USERNAME, username);
            if (itemList.size() == 0) {
                TeamItem teamItem = new TeamItem();
                teamItem.setUsername(username);
                teamItem.setLine("京东线");
                teamItem.setStation("上海收费站");
//                    if (itemList.get(0).getLine() == null) {

                ArrayList<String> lanes = new ArrayList<>();
                lanes.add("A01");
                lanes.add("A02");
                lanes.add("A03");
                teamItem.setLanes(lanes);

                teamItem.setDefaultLane("A01");

//                    }
                teamItem.save();
            }
        }
    }
*/
}
