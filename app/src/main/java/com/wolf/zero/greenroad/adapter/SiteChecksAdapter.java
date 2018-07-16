package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.SpinnerPopupWindow;
import com.wolf.zero.greenroad.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.litepal.LitePalApplication.getContext;

/**
 * Created by Administrator on 2017/8/17.
 */

public class SiteChecksAdapter extends RecyclerView.Adapter<SiteChecksAdapter.SiteCheckHolder> {

    private final Context mContext;


    private ArrayList<String> mTextList;
    private int mDimension;
    private final ArrayList<String> mOperators;

    public SiteChecksAdapter(Context context, ArrayList<String> textList, int dimension,
                             ArrayList<String> mCheckOperators) {
        mContext = context;
        mTextList = textList;
        mDimension = dimension;
        mOperators = mCheckOperators;
    }

    @Override
    public SiteCheckHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_site_check_operator_checked, parent, false);
        return new SiteCheckHolder(view);
    }

    @Override
    public int getItemCount() {
        return mTextList.size();
    }

    @Override
    public void onBindViewHolder(SiteCheckHolder holder, int position) {
        holder.bindHolder(position);
    }

    public class SiteCheckHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.site_check_operator)
        TextView mSiteCheckOperator;
        @BindView(R.id.site_check_delete)
        ImageButton mSiteCheckDelete;
        @BindView(R.id.site_check_header)
        TextView mSiteCheckHeader;
        @BindView(R.id.add_check_item)
        ImageButton mAddCheckItem;

        private SpinnerPopupWindow mPopupWindow_check;
        private SpinnerPopupWindow mPopupWindow_add;

        public SiteCheckHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolder(int pos) {
            if (pos == mTextList.size() - 1 && mTextList.size() != 3) {
                mAddCheckItem.setVisibility(View.VISIBLE);
            } else {
                mAddCheckItem.setVisibility(View.GONE);
            }
            mSiteCheckOperator.setText(mTextList.get(pos));

            if (mTextList.size() > 1) {
                mSiteCheckHeader.setText("现场检查人" + (pos + 1));
                mSiteCheckDelete.setVisibility(View.VISIBLE);
            } else {
                mSiteCheckHeader.setText("现场检查人");
                mSiteCheckDelete.setVisibility(View.GONE);
            }


            mAddCheckItem.setOnClickListener(v -> {

                BasePhotoAdapter<String> adapter_check = new BasePhotoAdapter<String>(getContext(), R.layout.item_check_popup_text, mOperators) {
                    @Override
                    public void convert(BasePhotoViewHolder holder, int position, String s) {
                        TextView textView = holder.getView(R.id.text_item_popup);
                        textView.setText(s);
                        textView.setOnClickListener(v1 -> {
                            if (mTextList.contains(s)) {
                                ToastUtils.singleToast("该默认检查人已被选择");
                                mPopupWindow_add.dismissPopWindow();
                                return;
                            }
                            mTextList.add(s);
//                            notifyDataSetChanged();
                            updateListView(mTextList);
                            mPopupWindow_add.dismissPopWindow();
                        });
                    }
                };

                mPopupWindow_add = new SpinnerPopupWindow.Builder(getContext())
                        .setmLayoutManager(null, 0)
                        .setmAdapter(adapter_check)
                        .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.DKGRAY))
                        .setmHeight(800).setmWidth(600)
                        .setOutsideTouchable(true)
                        .setFocusable(true)
                        .build();

                mPopupWindow_add.showPopWindow(v, mDimension);
            });

            mSiteCheckDelete.setOnClickListener(v -> {
                mTextList.remove(pos);

                updateListView(mTextList);
            });

            mSiteCheckOperator.setOnClickListener(v -> {
                BasePhotoAdapter<String> adapter_check = new BasePhotoAdapter<String>(getContext(), R.layout.item_check_popup_text, mOperators) {
                    @Override
                    public void convert(BasePhotoViewHolder holder, int position, String s) {
                        TextView textView = holder.getView(R.id.text_item_popup);

                        textView.setText(s);
                        textView.setOnClickListener(v1 -> {
                            if (mTextList.contains(s)) {
                                ToastUtils.singleToast("该默认检查人已存在");
                                mPopupWindow_check.dismissPopWindow();
                                return;
                            }
                            mSiteCheckOperator.setText(s);
                            mTextList.remove(pos);
                            mTextList.add(pos, s);
                            mPopupWindow_check.dismissPopWindow();
                        });
                    }
                };

                mPopupWindow_check = new SpinnerPopupWindow.Builder(getContext())
                        .setmLayoutManager(null, 0)
                        .setmAdapter(adapter_check)
                        .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.DKGRAY))
                        .setmHeight(800).setmWidth(600)
                        .setOutsideTouchable(true)
                        .setFocusable(true)
                        .build();

                mPopupWindow_check.showPopWindow(v, mDimension);
            });
        }
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<String> list) {
        if (list == null) {
            mTextList = new ArrayList<>();
        } else {
            mTextList = (ArrayList<String>) list;
        }
        notifyDataSetChanged();
    }
}
