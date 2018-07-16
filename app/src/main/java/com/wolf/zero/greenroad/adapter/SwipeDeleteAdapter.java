package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopeer.itemtouchhelperextension.Extension;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/8.
 */

public class SwipeDeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ItemTouchHelperExtension mItemTouchHelperExtension;

    private final Context mContext;


    private ArrayList<SupportDraftOrSubmit> mPreviewList;
    private final onPreviewItemClick mItemClick;


    public SwipeDeleteAdapter(Context context, onPreviewItemClick onPreviewItemClick) {
        mPreviewList = new ArrayList<>();
        mItemClick = onPreviewItemClick;
        mContext = context;

    }

    public void updateData(List<SupportDraftOrSubmit> mDatas) {
        mPreviewList.clear();
        mPreviewList.addAll(mDatas);
        notifyDataSetChanged();
    }

    public void setItemTouchHelperExtension(ItemTouchHelperExtension itemTouchHelperExtension) {
        mItemTouchHelperExtension = itemTouchHelperExtension;
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.list_item_main, parent, false);
        return new ItemSwipeWithActionWidthNoSpringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ItemBaseViewHolder baseViewHolder = (ItemBaseViewHolder) holder;
        baseViewHolder.bind(mPreviewList.get(position));
        baseViewHolder.mViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.itemClick(mPreviewList.get(position));
            }
        });
        if (holder instanceof ItemSwipeWithActionWidthViewHolder) {
            ItemSwipeWithActionWidthViewHolder viewHolder = (ItemSwipeWithActionWidthViewHolder) holder;

            viewHolder.mActionViewDelete.setOnClickListener(v -> {
                        doDelete(holder.getAdapterPosition());

                    }
            );
        }

    }

    private void doDelete(int adapterPosition) {
        DataSupport.deleteAll(SupportDraftOrSubmit.class, "lite_ID = ?",
                String.valueOf(mPreviewList.get(adapterPosition).getLite_ID()));
//        SPUtils.cut_one(mContext, SPUtils.MATH_DRAFT_LITE);
        mPreviewList.remove(adapterPosition);
        updateListView(mPreviewList);
//        notifyItemRemoved(adapterPosition);
        mItemTouchHelperExtension.closeOpened();
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<SupportDraftOrSubmit> list) {
        if (list == null) {
            this.mPreviewList = new ArrayList<>();
        } else {
            this.mPreviewList = (ArrayList<SupportDraftOrSubmit>) list;
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPreviewList.size();
    }

    class ItemBaseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.preview_text_car_number)
        TextView mPreviewTextCarNumber;
        @BindView(R.id.preview_text_check)
        TextView mPreviewTextCheck;
        @BindView(R.id.preview_text_login)
        TextView mPreviewTextLogin;
        @BindView(R.id.preview_img_isPass)
        ImageView mPreviewImgIsFree;
        @BindView(R.id.preview_text_shutTime)
        TextView mPreviewTextShutTime;
        View mViewContent;
        View mActionContainer;

        public ItemBaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mViewContent = itemView.findViewById(R.id.item_recycler_view_preview);
            mActionContainer = itemView.findViewById(R.id.view_list_repo_action_container);
        }

        public void bind(SupportDraftOrSubmit support) {
            String sub_check = support.getSupportChecked().getSiteChecks().get(0).substring(0, 6);
            String check = sub_check;
            String login = support.getSupportChecked().getSiteLogin();
            String car_number = support.getSupportDetail().getNumber();
            String shutTime = support.getCurrent_time();
            int isFree = support.getSupportChecked().getIsFree();


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
//            mPreviewImgIsFree.setText(isFree == 0 ? "否" : "是");
            mPreviewImgIsFree.setImageDrawable(isFree == 0
                    ? mContext.getResources().getDrawable(R.drawable.check_notpass)
                    : mContext.getResources().getDrawable(R.drawable.check_pass));


            itemView.setOnTouchListener((v, event) -> {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mItemTouchHelperExtension.startDrag(ItemBaseViewHolder.this);
                }
                return true;

            });
        }
    }


    class ItemSwipeWithActionWidthViewHolder extends ItemBaseViewHolder implements Extension {

        View mActionViewDelete;

        public ItemSwipeWithActionWidthViewHolder(View itemView) {
            super(itemView);
            mActionViewDelete = itemView.findViewById(R.id.view_list_repo_action_delete);
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

    class ItemSwipeWithActionWidthNoSpringViewHolder extends ItemSwipeWithActionWidthViewHolder implements Extension {

        public ItemSwipeWithActionWidthNoSpringViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public float getActionWidth() {
            return mActionContainer.getWidth();
        }
    }

    public interface onPreviewItemClick {
        void itemClick(SupportDraftOrSubmit support);
    }
}
