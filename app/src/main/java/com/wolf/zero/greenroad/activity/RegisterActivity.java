package com.wolf.zero.greenroad.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.SpinnerPopupWindow;
import com.wolf.zero.greenroad.adapter.RecycleViewDivider;
import com.wolf.zero.greenroad.adapter.SpinnerAdapter;
import com.wolf.zero.greenroad.httpresultbean.HttpResult;
import com.wolf.zero.greenroad.https.RequestRegistered;
import com.wolf.zero.greenroad.litepalbean.SupportLine;
import com.wolf.zero.greenroad.tools.SPListUtil;
import com.wolf.zero.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;


public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_road)
    ImageButton mRegisterRoad;
    @BindView(R.id.register_station)
    ImageButton mRegisterStation;
    @BindView(R.id.register_code)
    EditText mRegisterCode;
    @BindView(R.id.register_user)
    EditText mRegisterUser;
    @BindView(R.id.register_psw)
    EditText mRegisterPsw;
    @BindView(R.id.register_sure_psw)
    EditText mRegisterSurePsw;
    @BindView(R.id.btn_sure_register)
    Button mBtnSureRegister;
    @BindView(R.id.text_road_register)
    TextView mTextRoadRegister;
    @BindView(R.id.text_station_register)
    TextView mTextStationRegister;

    private SpinnerAdapter mAdapter_road;
    private SpinnerAdapter mAdapter_station;
    private SpinnerPopupWindow mPopupWindow_road;
    private SpinnerPopupWindow mPopupWindow_station;
    private float mDimension;
    private int mWidth;
    private List<SupportLine> mSupportLineList;
    private ArrayList<String> mRoadList;
    private String mSelectRoad;
    private List<String> mStationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initData();
    }


    @OnClick({R.id.register_road, R.id.register_station, R.id.btn_sure_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_road:
                mTextStationRegister.setText("");
                mWidth = mTextRoadRegister.getWidth();
                mAdapter_road = new SpinnerAdapter(this, mRoadList, position -> {
                    mSelectRoad = mRoadList.get(position);
                    mTextRoadRegister.setText(mSelectRoad);
                    mPopupWindow_road.dismissPopWindow();
                });

                mPopupWindow_road = new SpinnerPopupWindow.Builder(RegisterActivity.this)
                        .setmLayoutManager(null, 0)
                        .setmAdapter(mAdapter_road)
                        .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
                        .setmHeight(600).setmWidth(mWidth)
                        .setOutsideTouchable(true)
                        .setFocusable(true)
                        .build();

                mPopupWindow_road.showPopWindow(view, (int) mDimension);

                break;
            case R.id.register_station:
                int width = mTextStationRegister.getWidth();

                if (mSelectRoad == null || "".equals(mSelectRoad)) {
                    ToastUtils.singleToast("请确定路段后再选择收费站");
                    return;
                }
                List<SupportLine> supportLines = DataSupport.where("line = ? ", mSelectRoad).find(SupportLine.class);
                if (supportLines.size() != 0) {
                    mStationList = supportLines.get(0).getStations();
                }
//                if (mStationList == null) {
//
//                    mStationList = new ArrayList<>();
//                } else {
//                    mStationList.clear();
//                }
//                mStationList.add("玛多收费站");
//                mStationList.add("铁盖收费站");
//                mStationList.add("塔拉收费站");

                mAdapter_station = new SpinnerAdapter(this, (ArrayList<String>) mStationList, position -> {
                    mTextStationRegister.setText(mStationList.get(position));
                    mPopupWindow_station.dismissPopWindow();
                });

                mPopupWindow_station = new SpinnerPopupWindow.Builder(RegisterActivity.this)
                        .setmLayoutManager(null, 0)
                        .setmAdapter(mAdapter_station)
                        .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
                        .setmHeight(800).setmWidth(width)
                        .setOutsideTouchable(true)
                        .setFocusable(true)
                        .build();


                mPopupWindow_station.showPopWindow(view, (int) mDimension);
                break;
            case R.id.btn_sure_register:
                String roadText = mTextRoadRegister.getText().toString().trim();
                String stationText = mTextStationRegister.getText().toString().trim();

                String register_code = mRegisterCode.getText().toString().trim();
                String register_user = mRegisterUser.getText().toString().trim();
                String register_psw = mRegisterPsw.getText().toString().trim();
                String register_sure_psw = mRegisterSurePsw.getText().toString().trim();


                if (TextUtils.isEmpty(roadText)) {
                    ToastUtils.singleToast("请确定注册路段");
                    return;
                }

                if (TextUtils.isEmpty(stationText)) {
                    ToastUtils.singleToast("请确定注册收费站");
                    return;
                }


                if (TextUtils.isEmpty(register_code)) {
                    mRegisterCode.setError(getString(R.string.error_field_required));
                    mRegisterCode.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(register_user)) {
                    mRegisterUser.setError("请输入用户名");
                    mRegisterUser.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(register_psw) || !isPasswordValid(register_psw)) {
                    mRegisterPsw.setError("密码必须由大于四位数的数字或英文字母组成");
                    mRegisterPsw.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(register_sure_psw)) {
                    mRegisterSurePsw.setError("请再定确定密码");
                    mRegisterSurePsw.requestFocus();
                    return;
                }
                if (!register_psw.equals(register_sure_psw)) {
                    mRegisterSurePsw.setError("两次密码不相同,请重新确定密码");
                    mRegisterSurePsw.requestFocus();
                }


                Subscriber<HttpResult> subscriber = new Subscriber<HttpResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i(e.getMessage());
                        ToastUtils.singleToast("连接服务器失败,请确定端口是否正确");
                    }

                    @Override
                    public void onNext(HttpResult httpResult) {
                        int code = httpResult.getCode();
                        Logger.i(code + httpResult.getMsg());
                        if (code == 200) {
                            ToastUtils.singleToast("注册成功,请登录");
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);

                            List app_config_info = new ArrayList<String>();

                            app_config_info.add(register_user);
                            app_config_info.add(roadText);
                            app_config_info.add(stationText);

                            Logger.i(app_config_info.toString());
                            SPListUtil.putStrListValue(RegisterActivity.this, SPListUtil.APPCONFIGINFO, app_config_info);

//                            RequestLane.getInstance().getLanes(new Subscriber<List<HttpResultLane.DataBean>>() {
//                                @Override
//                                public void onCompleted() {
//
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Logger.i(e.getMessage());
//                                }
//
//                                @Override
//                                public void onNext(List<HttpResultLane.DataBean> dataBeen) {
//                                    ArrayList<String> laneList = new ArrayList<>();
//                                    DataSupport.deleteAll(SupportLane.class);
//                                    for (int i = 0; i < dataBeen.size(); i++) {
//                                        Logger.i(dataBeen.get(i).toString());
//                                        laneList.add(dataBeen.get(i).getLane());
//                                    SupportLane supportLane = new SupportLane();
//                                    supportLane.setLane(dataBeen.get(i).getLane());
//                                    supportLane.save();
//                                    }
//                                    SPUtils.putAndApply(RegisterActivity.this, SPUtils.TEXTLANE, dataBeen.get(0).getLane());
//                                }
//                            },stationText);

                        } else {
                            ToastUtils.singleToast(httpResult.getMsg());

                        }
                    }
                };
                RequestRegistered.getInstance().postRegistered(subscriber, roadText, stationText,
                        register_code, register_user, register_psw);

                break;

            default:
                break;
        }
    }

    private void initData() {
        mSupportLineList = DataSupport.findAll(SupportLine.class);
        if (mRoadList == null) {

            mRoadList = new ArrayList<>();
        } else {
            mRoadList.clear();

        }
        if (mSupportLineList.size() != 0) {
            for (int s = 0; s < mSupportLineList.size(); s++) {
                mRoadList.add(mSupportLineList.get(s).getLine());
            }
        }
        mDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120,
                getResources().getDisplayMetrics());

//        mRoadList.add("共玉高速");

    }

    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z_]*$");
        Matcher matcher = pattern.matcher(password);
        boolean isMatcher = matcher.matches();
        Logger.i(isMatcher + "");
        if (isMatcher && password.length() > 4) {
            return true;
        }
        return false;
    }
}
