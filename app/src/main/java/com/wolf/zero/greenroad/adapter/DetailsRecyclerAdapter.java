package com.wolf.zero.greenroad.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.fragment.MyBitmap;
import com.wolf.zero.greenroad.tools.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.litepal.LitePalApplication.getContext;


/**
 * @author sineom
 * @version 1.0
 * @time 2017/8/6 1:46
 * @des ${TODO}
 * @updateAuthor ${Author}
 * @updataTIme 2017/8/6
 * @updataDes ${描述更新内容}
 */

public class DetailsRecyclerAdapter extends RecyclerView.Adapter<DetailsRecyclerAdapter.DetailsRecyclerHolder> {


    private final Context mContext;
    private final itemClickListener mLisener;

    private List<MyBitmap> mBitmapList;
    private int mThemeTag;


    public DetailsRecyclerAdapter(Context context, List<MyBitmap> myBitmaps, itemClickListener itemClickLisener) {
        mContext = context;
        mBitmapList = myBitmaps;
        mLisener = itemClickLisener;
        mThemeTag = (int) SPUtils.get(getContext(), SPUtils.KEY_THEME_TAG, 1);
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<MyBitmap> list) {
        if (list == null) {
            mBitmapList = new ArrayList<>();
        } else {
            mBitmapList = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public DetailsRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_detail_fragment_image, parent, false);

        return new DetailsRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsRecyclerHolder holder, int position) {
        holder.bindHolder(position);
    }

    @Override
    public int getItemCount() {
        return mBitmapList.size();

    }


    public class DetailsRecyclerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_fragment_image)
        ImageView mDetailFragmentImage;
        @BindView(R.id.detail_fragment_text)
        TextView mDetailFragmentText;

        public DetailsRecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindHolder(int position) {
            if (mBitmapList != null) {

                MyBitmap myBitmap = mBitmapList.get(position);
                if (myBitmap != null) {
                    mDetailFragmentImage.setImageBitmap(myBitmap.getBm());

                    if (mThemeTag == 1) {
                        mDetailFragmentText.setTextColor(Color.DKGRAY);
                    } else {
                        mDetailFragmentText.setTextColor(Color.WHITE);
                    }
                    mDetailFragmentText.setText(myBitmap.getTitle());
                }
//                if (position < 3) {
                    mDetailFragmentImage.setOnClickListener(v -> {
                        mLisener.itemListener();
                    });
//                } else {
//                    mDetailFragmentImage.setClickable(false);
//                }
            }
        }
    }

    public interface itemClickListener {
        void itemListener();
    }
}
