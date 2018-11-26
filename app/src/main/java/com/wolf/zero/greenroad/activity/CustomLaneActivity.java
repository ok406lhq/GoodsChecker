package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jet.flowtaglayout.FlowTagLayout;
import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.litepalbean.SupportBanci;
import com.wolf.zero.greenroad.tools.ActionBarTool;
import com.wolf.zero.greenroad.tools.SPUtils;
import com.wolf.zero.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomLaneActivity extends BaseActivity {
    private CustomLaneActivity mActivity;
    @BindView(R.id.toolbar_custom_lane)
    Toolbar mCustomLane;
    @BindView(R.id.flowTagLayout)
    FlowTagLayout flowTagLayout;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    @BindView(R.id.et_custom_lane)
    EditText mEtCusLane;

    private boolean isSave = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_lane);
        ButterKnife.bind(this);
        mActivity = this;

        initToolbar();
        initData();
    }

    private void initData() {
        List<SupportBanci> BanciList = DataSupport.findAll(SupportBanci.class);
        SupportBanci banci = BanciList.get(0);
        ArrayList<String> dataList = banci.getBancis();

        // 添加tag
        flowTagLayout.addTags(dataList);  // 添加tag的列表，该方法会把之前的tags全部清空

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cus_lane = mEtCusLane.getText().toString().trim();
                if (!cus_lane.isEmpty()) {
                    isSave = false;
                    dataList.add(cus_lane);
                    Logger.i("dataList:" + dataList);
                    flowTagLayout.addTag(cus_lane);
                    mEtCusLane.setText("");
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow
                            (CustomLaneActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    ToastUtils.singleToast("添加成功");
                } else {
                    ToastUtils.singleToast("内容不能为空哦~");
                }
            }
        });

        flowTagLayout.setTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void tagClick(int position) {
                // getChildAt(position)方法在这很实用
                flowTagLayout.getChildAt(position).setSelected(!flowTagLayout.getChildAt(position).isSelected());
                if (position > 3) {
                    AlertDialog dialog = new AlertDialog.Builder(CustomLaneActivity.this).setTitle("操作提示")
                            .setNegativeButton("取消", null).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //处理确认按钮的点击事件
                                    flowTagLayout.removeTagOfIndex(position);
                                    isSave = false;
                                    dataList.remove(position);
                                }
                            }).setMessage("是否删除？").create();
                    dialog.show();
                } else {
                    ToastUtils.singleToast("该班次不可删除");
                }
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSave = true;
                DataSupport.deleteAll(SupportBanci.class);
                SupportBanci banci = new SupportBanci();
                banci.setBancis(dataList);
                //保存数据一定要save！
                banci.save();
                ToastUtils.singleToast("保存成功！");
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mCustomLane);

        TextView title_text_view = ActionBarTool.getInstance(mActivity, 991).getTitle_text_view();
        title_text_view.setText("自定义班次");

        mCustomLane.setNavigationIcon(R.drawable.back_up_logo);
        mCustomLane.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSave) {
                    finish();
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(CustomLaneActivity.this).setTitle("操作提示")
                            .setNegativeButton("取消", null).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setMessage("内容尚未保存，确定要退出？").create();
                    dialog.show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isSave) {
            finish();
        } else {
            AlertDialog dialog = new AlertDialog.Builder(CustomLaneActivity.this).setTitle("操作提示")
                    .setNegativeButton("取消", null).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setMessage("内容尚未保存，确定要退出？").create();
            dialog.show();
        }
    }
}
