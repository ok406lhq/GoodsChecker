package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.interfacy.TextFragmentListener;
import com.wolf.zero.greenroad.tools.ActionBarTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
public class ConclusionActivity extends BaseActivity {

    private static String CONCLUSIONS = "mConclusions";
    private static String ACTION_CONCLUSION = "action_conclusion";

    @BindView(R.id.toolbar_conclusion)
    Toolbar mToolbar;
    private Unbinder mUnbinder;
    @BindView(R.id.check_111_001)
    CheckBox mCheck111001;
    @BindView(R.id.check_222_001)
    CheckBox mCheck222001;
    @BindView(R.id.check_222_002)
    CheckBox mCheck222002;
    @BindView(R.id.check_222_003)
    CheckBox mCheck222003;
    @BindView(R.id.check_222_004)
    CheckBox mCheck222004;
    @BindView(R.id.check_333_001)
    CheckBox mCheck333001;
    @BindView(R.id.check_333_003)
    CheckBox mCheck333003;
    @BindView(R.id.check_333_004)
    CheckBox mCheck333004;
    @BindView(R.id.check_333_002)
    CheckBox mCheck333002;
    @BindView(R.id.check_444_001)
    CheckBox mCheck444001;
    @BindView(R.id.check_444_002)
    CheckBox mCheck444002;
    @BindView(R.id.check_555_001)
    CheckBox mCheck555001;
    @BindView(R.id.check_555_002)
    CheckBox mCheck555002;
    @BindView(R.id.check_666_001)
    CheckBox mCheck666001;
    @BindView(R.id.check_666_002)
    CheckBox mCheck666002;
    @BindView(R.id.check_666_003)
    CheckBox mCheck666003;
    @BindView(R.id.check_777_001)
    CheckBox mCheck777001;
    @BindView(R.id.btn_sure_conclusion)
    Button mBtnSureConclusion;

    private static StringBuilder mBuilder;

    private static CheckBox[] mCheckBoxes;
    private String[] mConclusionArrays;
    private String mConclusions;

    public static void actionStart(Context context, String conclusions) {
        Intent intent = new Intent(context, ConclusionActivity.class);
        intent.setAction(ACTION_CONCLUSION);
        intent.putExtra(CONCLUSIONS, conclusions);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclusion);
        mUnbinder = ButterKnife.bind(this);

        initToolbar();
        getIntentData();

        initCheckBox();
        initView();

    }

    /**
     * 进入界面时首先将点击的checkbox显示出来
     */
    private void initCheckBox() {

        mCheckBoxes = new CheckBox[]{mCheck111001, mCheck222001, mCheck222002, mCheck222003,
                mCheck222004, mCheck333001, mCheck333002, mCheck333003, mCheck333004,
                mCheck444001, mCheck444002, mCheck555001, mCheck555002,
                mCheck666001, mCheck666002, mCheck666003, mCheck777001};
        mConclusionArrays = getResources().getStringArray(R.array.checked_conclusion);

        for (int i = 0; i < mCheckBoxes.length; i++) {
            mCheckBoxes[i].setText(mConclusionArrays[i]);
        }
        if (mConclusions != null && mConclusions.length() != 0) {
            initCheckBoxItem(mCheck111001);
            initCheckBoxItem(mCheck222001);
            initCheckBoxItem(mCheck222002);
            initCheckBoxItem(mCheck222003);
            initCheckBoxItem(mCheck222004);
            initCheckBoxItem(mCheck333001);
            initCheckBoxItem(mCheck333002);
            initCheckBoxItem(mCheck333003);
            initCheckBoxItem(mCheck333004);
            initCheckBoxItem(mCheck444001);
            initCheckBoxItem(mCheck444002);
            initCheckBoxItem(mCheck555001);
            initCheckBoxItem(mCheck555002);
            initCheckBoxItem(mCheck666001);
            initCheckBoxItem(mCheck666002);
            initCheckBoxItem(mCheck666003);
            initCheckBoxItem(mCheck777001);
        }
    }

    private void initCheckBoxItem(CheckBox checkBox) {
        if (mConclusions.contains(checkBox.getText().toString() + ";")) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    private void initView() {
        initOnclick(mBuilder, mCheck111001);
        initOnclick(mBuilder, mCheck222001);
        initOnclick(mBuilder, mCheck222002);
        initOnclick(mBuilder, mCheck222003);
        initOnclick(mBuilder, mCheck222004);
        initOnclick(mBuilder, mCheck333001);
        initOnclick(mBuilder, mCheck333002);
        initOnclick(mBuilder, mCheck333003);
        initOnclick(mBuilder, mCheck333004);
        initOnclick(mBuilder, mCheck444001);
        initOnclick(mBuilder, mCheck444002);
        initOnclick(mBuilder, mCheck555001);
        initOnclick(mBuilder, mCheck555002);
        initOnclick(mBuilder, mCheck666001);
        initOnclick(mBuilder, mCheck666002);
        initOnclick(mBuilder, mCheck666003);
        initOnclick(mBuilder, mCheck777001);

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (ACTION_CONCLUSION.equals(intent.getAction())) {
            mConclusions = intent.getStringExtra(CONCLUSIONS);
            inflateConclusion(mConclusions);
        }
    }


    private void initToolbar() {
        setSupportActionBar(mToolbar);

        TextView title_text_view = ActionBarTool.getInstance(this, 991).getTitle_text_view();
        title_text_view.setText("检查结论");

        mToolbar.setNavigationIcon(R.drawable.back_up_logo);

        mToolbar.setNavigationOnClickListener(v -> backToChecked());
    }

    /**
     * 返回到检查结论的Fragment
     */
    private void backToChecked() {
        onBackPressed();
    }

    @OnClick(R.id.btn_sure_conclusion)
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 对所有的checkBox进行点击操作
     *
     * @param builder
     */
    private void initOnclick(StringBuilder builder, CheckBox checkBox) {
        checkBox.setOnClickListener(v -> {
            String newStr = "";
            if (checkBox.isChecked()) {
                builder.append(checkBox.getText() + ";");
                newStr = builder.toString();
            } else {
                String str = checkBox.getText() + ";";
                newStr = builder.toString().replaceAll(str, "");
                builder.delete(0, builder.length());
                builder.append(newStr);
            }
            Logger.i(builder.toString());
        });
    }

    public static void setTextChangeListener(TextFragmentListener listener) {
        if (mBuilder != null) {
            listener.textChanged(mBuilder.toString().trim());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
    /**
     * 当采集界面退出时,初始化numberfragment的数据
     */
    public static void notifyDataChange() {
        if (mBuilder != null) {
            mBuilder.delete(0,mBuilder.length());
        }
    }
    /**
     * 当采集界面退出时,初始化numberfragment的数据
     */
    public static void notifyDataChangeFromDraft(String conclusions) {
        inflateConclusion(conclusions);
    }
    private static void inflateConclusion(String conclusions) {
        if (mBuilder == null) {
            mBuilder = new StringBuilder();
        } else if (mBuilder.length() > 0) {
            mBuilder.delete(0, mBuilder.length());
        }
        mBuilder.append(conclusions);
    }
}
