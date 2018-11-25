package com.wolf.zero.greenroad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.SpinnerPopupWindow;
import com.wolf.zero.greenroad.adapter.SettingOperatorAdapter;
import com.wolf.zero.greenroad.adapter.SpinnerAdapter;
import com.wolf.zero.greenroad.litepalbean.SupportOperator;
import com.wolf.zero.greenroad.litepalbean.TeamItem;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.tools.ActionBarTool;
import com.wolf.zero.greenroad.tools.SPUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    public static final int SETTING_REQUEST = 001;
    @BindView(R.id.toolbar_setting)
    Toolbar mToolbarSetting;
    @BindView(R.id.text_setting_road)
    TextView mTextSettingRoad;
    @BindView(R.id.text_setting_station)
    TextView mTextSettingStation;
    @BindView(R.id.setting_recycler_view)
    RecyclerView mSettingRecyclerView;

    @BindView(R.id.text_setting_add_selection)
    TextView mTextSettingAddSelection;
    @BindView(R.id.text_setting_shift)
    TextView mTextSettingShift;
    @BindView(R.id.btn_setting_shift)
    ImageButton mBtnSettingShift;

    private SettingActivity mActivity;
    private SettingOperatorAdapter mAdapter;
    private ArrayList<SupportOperator> mOperatorList;
    private String mJob_number_login;
    private String mOperator_login_name;
    private List<SupportOperator> mSupportOperators;
    private int mWidth;
    private SpinnerAdapter mAdapterLane;
    private SpinnerPopupWindow mPopupWindow;
    private float mDimension;
    private ArrayList<String> mLaneList;
    private String mUsername;
    private ArrayList<String> mShiftList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        mActivity = this;
        mUsername = (String) SPUtils.get(this, GlobalManager.USERNAME, "qqqq");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        initData();
        initToolbar();
        initRecyclerView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
        initRecyclerView();
    }


    /**
     * 初始化工作人员信息
     */
    private void initData() {

        mDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                getResources().getDisplayMetrics());


        mSupportOperators = DataSupport.where("username = ?", mUsername).find(SupportOperator.class);
        Logger.i(mSupportOperators.toString());

        mOperatorList = new ArrayList<>();
        mOperatorList.addAll(mSupportOperators);
//        for (int i = 0; i < mSupportOperators.size(); i++) {
//            SettingOperatorInfo info = new SettingOperatorInfo();
//            info.setUsername(mSupportOperators.get(i).getUsername());
//            info.setOperator_name(mSupportOperators.get(i).getOperator_name());
//            info.setJob_number(mSupportOperators.get(i).getJob_number());
//            info.setIsCheckSelected(mSupportOperators.get(i).getCheck_select());
//            info.setIsLoginSelected(mSupportOperators.get(i).getLogin_select());
//            mOperatorList.add(info);
//            Logger.i(mOperatorList.get(i).toString());
//        }
        //从app注册时的配置信息中取出数据填充线路以及收费站

        List<TeamItem> teamItems = DataSupport.where("username = ?", mUsername).find(TeamItem.class);
        if (teamItems.size() != 0) {
            mTextSettingRoad.setText(teamItems.get(0).getLine());
            mTextSettingStation.setText(teamItems.get(0).getStation());
        }
      /*
      List<TeamItem> teamItems = DataSupport.where("username = ?", mUsername).find(TeamItem.class);
        if (teamItems.size() != 0) {
            mTextSettingRoad.setText(teamItems.get(0).getLine());
            mTextSettingStation.setText(teamItems.get(0).getStation());

            List<String> laneList = teamItems.get(0).getLanes();
            if (mLaneList == null) {
                mLaneList = new ArrayList<>();
            } else {
                mLaneList.clear();
            }

            if (laneList != null && laneList.size() != 0) {
                mLaneList = (ArrayList<String>) laneList;
            } else {
                mLaneList.add("A01");
            }
            mTextSettingShift.setText(teamItems.get(0).getDefaultLane());
        } else {

            mTextSettingShift.setText("A01");
        }*/
        String currentShift = (String) SPUtils.get(this, SPUtils.CURRENT_SHIFT, "");
        mTextSettingShift.setText(currentShift);
//        if (currentShift == null || "".equals(currentShift)) {
//        } else {
//            mTextSettingShift.setText(currentShift);
//        }
        if (mShiftList == null) {
            mShiftList = new ArrayList<>();
        } else {
            mShiftList.clear();
        }
        mShiftList.add("一班");
        mShiftList.add("二班");
        mShiftList.add("三班");
        mShiftList.add("四班");

//        mTextSettingShift.setText(SPUtils.get(mActivity, SPUtils.TEXTLANE, "X08") + "");
        mBtnSettingShift.setOnClickListener(view -> {
            mWidth = mTextSettingShift.getWidth();
            mAdapterLane = new SpinnerAdapter(this, mShiftList, position -> {
                mTextSettingShift.setText(mShiftList.get(position));
                mPopupWindow.dismissPopWindow();
                SPUtils.putAndApply(mActivity, SPUtils.CURRENT_SHIFT, mShiftList.get(position));
//                TeamItem teamItem = new TeamItem();
//                teamItem.setDefaultLane(mShiftList.get(position));
//                teamItem.updateAll("username = ?", mUsername);
            });

            mPopupWindow = new SpinnerPopupWindow.Builder(SettingActivity.this)
                    .setmLayoutManager(null, 0)
                    .setmAdapter(mAdapterLane)
//                    .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
                    .setmHeight(200 * (mShiftList.size()))
                    .setmWidth((int) (mWidth * 0.8))
                    .setOutsideTouchable(true)
                    .setFocusable(true)
                    .build();

//            mPopupWindow.showPopWindow(view, (int) mDimension);
            mPopupWindow.showPopWindowCenter(view);
        });
    }


    /**
     * 初始化RecyclerView的布局
     */
    private void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        mAdapter = new SettingOperatorAdapter(mUsername, this, mOperatorList, () -> {
            for (int i = 0; i < mOperatorList.size(); i++) {
                if (mOperatorList.get(i).getCheck_select() == 1) {
                    Logger.i(mOperatorList.get(i).getJob_number());
                }
            }
            List<SupportOperator> supportOperators = DataSupport.where("username = ?", mUsername).find(SupportOperator.class);
            for (int i = 0; i < supportOperators.size(); i++) {

                if (supportOperators.get(i).getCheck_select() == 1) {
                    Logger.i(supportOperators.get(i).getJob_number() + "数据库");
                }
            }
        },
//                (SupportOperator info) -> {
//            mJob_number_login = info.getJob_number();
//            mOperator_login_name = info.getOperator_name();
//            Logger.i("login---" + mJob_number_login + "-----" + mOperator_login_name);
//
//        },
                position -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setTitle("是否确定删除该检查人/登记人");
                    builder.setPositiveButton(R.string.dialog_messge_OK, (dialog, which) -> {
                        DataSupport.deleteAll(SupportOperator.class, "job_number = ?",
                                mOperatorList.get(position).getJob_number());
                        mOperatorList.remove(position);
                        //mAdapter.notifyItemRemoved(position);
                        mAdapter.updateListView(mOperatorList);
                    });
                    builder.setNegativeButton(R.string.dialog_message_Cancel,
                            (dialog, which) -> dialog.dismiss());
                    builder.show();
                });

        mSettingRecyclerView.setLayoutManager(manager);
        mSettingRecyclerView.setAdapter(mAdapter);
    }


    private void initToolbar() {

        setSupportActionBar(mToolbarSetting);

        TextView title_text_view = ActionBarTool.getInstance(mActivity, 991).getTitle_text_view();
        title_text_view.setText(getString(R.string.setting_default));

        mToolbarSetting.setNavigationIcon(R.drawable.back_up_logo);

        mToolbarSetting.setNavigationOnClickListener(v -> finish());
    }


    @OnClick({R.id.text_setting_add_selection,
            R.id.text_setting_shift,
            R.id.text_setting_road})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.text_setting_add_selection:
                addOperator();

                break;

            default:
                break;
        }
    }

    /**
     * 添加操作员
     */
    private void addOperator() {
        Intent intent = new Intent(this, AddOperatorActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Logger.i("onDestroy");

//        if (mOperatorList != null) {
//           /* List<SupportOperator> operatorList = DataSupport.findAll(SupportOperator.class);
//            for (int i = 0; i < mOperatorList.size(); i++) {
//                for (int j = 0; j < operatorList.size(); j++) {
//                    SupportOperator operator = operatorList.get(j);
//                    if (mOperatorList.get(i).getJob_number() != null &&
//                            mOperatorList.get(i).getJob_number().equals(operator.getJob_number())) {
//                        operator.setCheck_select(1);
//                    }
//                    operator.setCheck_select(0);
//                    operator.save();
//                }*/
//
//            List<SupportOperator> operatorList = DataSupport.findAll(SupportOperator.class);
//            DataSupport.where()delete()
//            for (SupportOperator operator : operatorList) {
//            for (int i = 0; i < mOperatorList.size(); i++) {
//                //for (int j = 0; j < operatorList.size(); j++) {
//                SupportOperator operator = operatorList.get(i);
//                if (mOperatorList.get(i).getIsCheckSelected() == 1) {
//                    operator.setCheck_select(1);
//                } else {
//                    operator.setCheck_select(0);
//                }
//                operator.save();
//            }
                /*for (int i = 0; i < mOperatorList.size(); i++) {
                    String job_number = mOperatorList.get(i).getJob_number();
                    if (job_number.equals(operator.getJob_number())) {
                        operator.setLogin_select(1);
                    } else {
                        operator.setLogin_select(0);
                    }
                    operator.save();
                }*/

//        }
//        if (mJob_number_login != null) {
//            List<SupportOperator> operatorList = DataSupport.findAll(SupportOperator.class);
//            for (SupportOperator operator : operatorList) {
//                if (mJob_number_login != null && mJob_number_login.equals(operator.getJob_number())) {
//                    operator.setLogin_select(1);
//                } else {
//                    operator.setLogin_select(0);
//                }
//                operator.save();
//            }
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}