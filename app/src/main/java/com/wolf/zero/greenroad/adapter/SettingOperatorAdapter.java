package com.wolf.zero.greenroad.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.activity.SettingActivity;
import com.wolf.zero.greenroad.litepalbean.SupportOperator;
import com.wolf.zero.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/3.
 */

public class SettingOperatorAdapter extends RecyclerView.Adapter<SettingOperatorAdapter.SettingOperatorHolder> {

    private final SettingActivity mActivity;
    private ArrayList<SupportOperator> mList;
    private final OnCheckSeleckedListener mCheckListener;
    //    private final OnLoginSeleckedListener mLoginListener;
    private final OnItemDeletListener mDeletListener;
    private final String mUsername;


    public SettingOperatorAdapter(String username, SettingActivity settingActivity, ArrayList<SupportOperator> list,
                                  OnCheckSeleckedListener onCheckSeleckedListener,
                                  OnItemDeletListener deletListener) {
        mActivity = settingActivity;
        mList = list;
        mUsername = username;
        mCheckListener = onCheckSeleckedListener;
//        mLoginListener = onLoginSeleckedListener;
        mDeletListener = deletListener;
    }


    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<SupportOperator> list) {
        if (list == null) {
            this.mList = new ArrayList<>();
        } else {
            this.mList = (ArrayList<SupportOperator>) list;
        }
        notifyDataSetChanged();
    }


    @Override
    public SettingOperatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(
                R.layout.recycler_view_item_show_operator, parent, false);
        return new SettingOperatorHolder(view);
    }


    @Override
    public void onBindViewHolder(SettingOperatorHolder holder, int position) {
        SupportOperator info = mList.get(position);
        holder.bindHolder(info, position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SettingOperatorHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_setting_recycler_delete)
        TextView mTextSettingRecyclerDelete;
        @BindView(R.id.ll_setting_operator)
        LinearLayout mLlSettingOperator;
        @BindView(R.id.text_setting_recycler_job_number)
        TextView mTextSettingRecyclerJobNumber;
        @BindView(R.id.text_setting_recycler_name)
        TextView mTextSettingRecyclerName;
        @BindView(R.id.operator_check_select)
        CheckBox mOperatorCheckSelect;
        @BindView(R.id.operator_login_select)
        CheckBox mOperatorLoginSelect;

        public SettingOperatorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolder(SupportOperator info, int position) {
//            if (position % 2 == 0) {
//                mLlSettingOperator.setBackgroundColor(Color.WHITE);
//
//            }

            String job_number = info.getJob_number();
            mTextSettingRecyclerJobNumber.setText(job_number);
            mTextSettingRecyclerName.setText(info.getOperator_name());

            mOperatorCheckSelect.setChecked(info.getCheck_select() == 0 ? false : true);
            mOperatorLoginSelect.setChecked(info.getLogin_select() == 0 ? false : true);
            mOperatorCheckSelect.setOnClickListener(v -> {
               /* for (SupportOperator operatorInfo : mList) {
                    if (operatorInfo.getCheck_select()==1) {
                        operatorInfo.setCheck_select(0);
                    }
                }*/
                if (mOperatorCheckSelect.isChecked()) {
                    Logger.t("zero").i("选中");
                    int length = 0;
                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).getCheck_select() == 1) {
                            length += 1;
                        }
                    }
                    if (length == 3) {
                        mOperatorCheckSelect.setChecked(false);
                        ToastUtils.singleToast("最多只能添加三个默认检查人");
                        return;
                    } else {
                        info.setCheck_select(1);
                        List<SupportOperator> supportOperators = DataSupport
                                .where("username =?  and job_number = ?", mUsername, job_number)
                                .find(SupportOperator.class);
                        SupportOperator operator = new SupportOperator();
                        operator.setCheck_select(1);//但是这种用法有一点需要注意，就是如果我们想把某一条数据修改成默认值，比如说将评论数修改成0，只是调用updateNews.setCommentCount(0)这样是不能修改成功的，因为即使不调用这行代码，commentCount的值也默认是0。所以如果想要将某一列的数据修改成默认值的话，还需要借助setToDefault()方法。
                        operator.updateAll("username = ? and job_number = ?", mUsername, job_number);

                    }

                } else {
                    Logger.t("zero").i("取消选中");
                    info.setCheck_select(0);
                    SupportOperator operator = new SupportOperator();
//                    operator.setCheck_select(0);
                    operator.setToDefault("check_select");
                    operator.updateAll("username = ? and job_number = ?", mUsername, job_number);

                }
                notifyDataSetChanged();

                mCheckListener.checkListener();
            });
            mOperatorLoginSelect.setOnClickListener(v -> {
                if (info.getLogin_select() == 0) {
                    for (SupportOperator operatorInfo : mList) {
//                    if (operatorInfo.getLogin_select() == 1)
                        operatorInfo.setLogin_select(0);
                    }
                    info.setLogin_select(1);
                    //将数据库所有的登录人都置为0
                    SupportOperator operator = new SupportOperator();
//                    operator.setCheck_select(0);
                    operator.setToDefault("login_select");
                    operator.updateAll("username = ? ", mUsername);

                    //将数据库当前选中登录人都置为1
                    SupportOperator supportOperator = new SupportOperator();
//                    operator.setCheck_select(0);
                    supportOperator.setLogin_select(1);
                    supportOperator.updateAll("username = ? and job_number = ?", mUsername, job_number);

                    notifyDataSetChanged();
                }else {
                    mOperatorLoginSelect.setChecked(true);
                }


            });
            //点击了删除的按钮
            mTextSettingRecyclerDelete.setOnClickListener(v -> {
                mDeletListener.delete(position);
            });
        }
    }

    public interface OnCheckSeleckedListener {
        void checkListener();
    }

    public interface OnLoginSeleckedListener {
        void loginListener(SupportOperator info);
    }

    public interface OnItemDeletListener {
        void delete(int position);
    }

}
