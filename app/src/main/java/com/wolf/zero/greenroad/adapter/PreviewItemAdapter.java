package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/28.
 */

public class PreviewItemAdapter extends RecyclerView.Adapter<PreviewItemAdapter.PreviewPhotoHolder> {


    private final Context mContext;


    private final AppCompatActivity mActivity;
    private final onPreviewItemClick mItemClick;

    private ArrayList<SupportDraftOrSubmit> mPreviewList;
    private final onLongItemClick mLongItemClick;
    // private final onItemClick mItemClick;


    public PreviewItemAdapter(Context context, AppCompatActivity activity,
                              ArrayList<SupportDraftOrSubmit> previewList,
                              onPreviewItemClick onPreviewItemClick,
                              onLongItemClick longItemClick) {

        mContext = context;
        mPreviewList = previewList;
        mActivity = activity;
        //   mItemClick = onItemClick;
        mItemClick = onPreviewItemClick;
        mLongItemClick = longItemClick;
    }


    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List list) {
        if (list == null) {
            this.mPreviewList = new ArrayList();
        } else {
            this.mPreviewList = (ArrayList<SupportDraftOrSubmit>) list;
        }
        notifyDataSetChanged();
    }

    @Override
    public PreviewPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PreviewPhotoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_preview, parent, false));

    }

    @Override
    public void onBindViewHolder(PreviewPhotoHolder holder, int position) {

        holder.bindHolder(mPreviewList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mPreviewList.size();
    }


    public class PreviewPhotoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.preview_text_car_number)
        TextView mPreviewTextCarNumber;
        @BindView(R.id.preview_text_check)
        TextView mPreviewTextCheck;
        @BindView(R.id.preview_text_login)
        TextView mPreviewTextLogin;
        @BindView(R.id.preview_img_isPass)
        ImageView mPreviewImgIsPass;

        @BindView(R.id.preview_text_shutTime)
        TextView mPreviewTextShutTime;


        public PreviewPhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindHolder(SupportDraftOrSubmit support, int position) {

            String sub_check = support.getSupportChecked().getSiteChecks().get(0).substring(0, 6);
            String check = sub_check;
            String login = support.getSupportChecked().getSiteLogin();
            String car_number = support.getSupportDetail().getNumber();
            String shutTime = support.getCurrent_time();
            int yesOrNot = support.getIsPass();

            if (position % 2 == 0) {
                itemView.setBackgroundColor(Color.WHITE);
            }
            if (check != null) {
                String[] checks = check.split("/");
                mPreviewTextCheck.setText(checks[0]);
            }
            if (login != null) {
                String[] logins = login.split("/");
                mPreviewTextLogin.setText(logins[0]);
            }
            mPreviewTextCarNumber.setText(car_number);
            mPreviewTextShutTime.setText(shutTime);
//            mPreviewTextIsFree.setText(yesOrNot == 0 ? "否" : "是");
            mPreviewImgIsPass.setImageDrawable(yesOrNot == 0
                    ? mActivity.getResources().getDrawable(R.drawable.check_wait)                  //待审核状态
                    : yesOrNot == 1 ? mActivity.getResources().getDrawable(R.drawable.check_pass)   //通过状态
                    : mActivity.getResources().getDrawable(R.drawable.check_notpass));                 //审核失败状态


            itemView.setOnClickListener(v -> {
                mItemClick.itemClick(support);
            });

            itemView.setOnLongClickListener(v -> {
                mLongItemClick.longClick(itemView, support.getLite_ID(), position);
                return true;
            });
            /*itemView.setOnLongClickListener(v -> {
                mItemClick.itemClick(support);
            });*/
        }
    }

    public interface onPreviewItemClick {
        void itemClick(SupportDraftOrSubmit support);
    }

    public interface onLongItemClick {
        void longClick(View itemView, int lite_ID, int position);
    }
}

