package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/17.
 */

public class GoodsTextAdapter extends RecyclerView.Adapter<GoodsTextAdapter.GoodsTextHolder> {

    private final Context mContext;
    private ArrayList<String> mTextList;
    private final DeleteTextListener mListener;


    public GoodsTextAdapter(Context context, ArrayList<String> textList,
                            DeleteTextListener deleteTextListener) {
        mContext = context;
        mTextList = textList;
        mListener = deleteTextListener;
    }

    @Override
    public GoodsTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goods_text_recycler, parent, false);
        return new GoodsTextHolder(view);
    }

    @Override
    public int getItemCount() {
        return mTextList.size();
    }

    @Override
    public void onBindViewHolder(GoodsTextHolder holder, int position) {
        holder.bindHolder(position);
    }

    public class GoodsTextHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_goods)
        TextView mTextGoods;
        @BindView(R.id.delete_goods)
        ImageButton mDeleteGoods;

        public GoodsTextHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindHolder(int pos) {
            mTextGoods.setText(mTextList.get(pos));
            mDeleteGoods.setOnClickListener(v -> {
                mListener.deleteTextItem(pos);
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

    public interface DeleteTextListener {
        void deleteTextItem(int pos);
    }
}
