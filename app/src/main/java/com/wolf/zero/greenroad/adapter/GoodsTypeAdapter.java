package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wolf.zero.greenroad.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zerowolf on 2018/3/1.
 */

public class GoodsTypeAdapter extends RecyclerView.Adapter<GoodsTypeAdapter.GoodsTypeHolder> {

    private final List<String> mTypeList;
    private final Context mContext;
    private final ArrayList<Button> mButtons;

    public GoodsTypeAdapter(Context context, List<String> typeList) {
        mContext = context;
        mTypeList = typeList;
        mButtons = new ArrayList<>();
    }

    @Override
    public GoodsTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_good_type_item, parent, false);

        return new GoodsTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsTypeHolder holder, int position) {
        holder.bindHolder(position);
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }

    public class GoodsTypeHolder extends RecyclerView.ViewHolder{

        private final Button mBtnGoodType;

        public GoodsTypeHolder(View itemView) {
            super(itemView);
            mBtnGoodType = (Button) itemView.findViewById(R.id.btn_good_type);

            for (int i = 0; i < mTypeList.size(); i++) {
                mButtons.add(mBtnGoodType);
            }
            mBtnGoodType.setOnClickListener(view -> {
                for (int i = 0; i < mTypeList.size(); i++) {
                    mButtons.get(i).setBackground(mContext.getResources().getDrawable(R.drawable.license_color_normal_day));
                }
                mBtnGoodType.setTextColor(mContext.getResources().getColor(R.color.day_color_white));
                mBtnGoodType.setBackground(mContext.getResources().getDrawable(R.drawable.license_color_day_selected));

            });
        }

        public void bindHolder(int position) {
            mBtnGoodType.setText(mTypeList.get(position));
        }
    }
}
