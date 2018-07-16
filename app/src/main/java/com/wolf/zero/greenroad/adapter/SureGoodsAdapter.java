package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.bean.SerializableGoods;
import com.wolf.zero.greenroad.tools.ImageUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/6/23.
 */

public class SureGoodsAdapter extends RecyclerView.Adapter<SureGoodsAdapter.SureGoodsHolder> {

    private final Context mContext;
    private ArrayList<SerializableGoods> mList;
    private onItemClick mItemClick;


    public SureGoodsAdapter(Context context, ArrayList<SerializableGoods> allContactsList, onItemClick itemClick) {
        mContext = context;
        mList = allContactsList;
        mItemClick = itemClick;
    }

    @Override
    public SureGoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sure_recycler_goods, null);
        return new SureGoodsHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(SureGoodsHolder holder, int position) {
        SerializableGoods serializableGoods = mList.get(position);
        holder.bindHolder(serializableGoods, position);
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<SerializableGoods> list) {
        if (list == null) {
            this.mList = new ArrayList<SerializableGoods>();
        } else {
            this.mList = (ArrayList<SerializableGoods>) list;
        }
        notifyDataSetChanged();
    }


    public class SureGoodsHolder extends RecyclerView.ViewHolder {

        private final TextView scientific_textView;
       // private final TextView alias;
        private final ImageView mImageView;

        public SureGoodsHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_recycler_goods);
            scientific_textView = (TextView) itemView.findViewById(R.id.tv_sure_recycler_scientific_name);
           // alias = (TextView) itemView.findViewById(R.id.tv_sure_recycler_alias);
        }

        public void bindHolder(final SerializableGoods model, int position) {

            String scientificname = model.getScientific_name();
//            if (scientificname.length() > 3) {
//                scientific_textView.setTextSize(CommonUtils.sp2px(mContext,6));
//                scientific_textView.setText(scientificname);
//            } else {
//                scientific_textView.setTextSize(CommonUtils.sp2px(mContext,8));
//            }
                scientific_textView.setText(scientificname);
         //   alias.setText(model.getAlias());

            String bitmapUrl = model.getBitmapUrl();
//            Logger.i(bitmapUrl);
            Bitmap bitmap = ImageUtils.getLoacalBitmap(bitmapUrl);

            mImageView.setImageBitmap(bitmap);
         //   mImageView.setBackground(mContext.getResources().getDrawable(R.drawable.demo));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClick.itemClick(model, position);
                }
            });
        }
    }

    //从Assets中读取图片
    private Bitmap getImageFromAssetsFile(String fileName) {
        AssetManager am = null;
        Bitmap image = null;
        if (am == null) {
            am = mContext.getResources().getAssets();
        }
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public interface onItemClick {
        void itemClick(SerializableGoods serializableGoods, int position);
    }
}
