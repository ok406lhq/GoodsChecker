package com.wolf.zero.greenroad.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.bean.SerializableStation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/6/26.
 */

public class SureCarStationAdapter extends RecyclerView.Adapter<SureCarStationAdapter.SureCarStationHolder>{



    private final AppCompatActivity mActivity;
    private ArrayList<SerializableStation> mListLocal;

    private onItemClick itemClick;

    public SureCarStationAdapter(AppCompatActivity activity,
                                 List<SerializableStation> list_head, onItemClick itemClick) {
        mActivity = activity;
        mListLocal = (ArrayList<SerializableStation>) list_head;
        this.itemClick = itemClick;
    }

    @Override
    public SureCarStationHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SureCarStationHolder holder = new SureCarStationHolder(LayoutInflater.from(
                mActivity).inflate(R.layout.item_station, parent,
                false));

       // View view = LayoutInflater.from(mActivity).inflate(R.layout.item_test, parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(SureCarStationHolder holder, int position) {
        SerializableStation station = mListLocal.get(position);
        

        holder.bindHolder(station,position,holder);
    }

    @Override
    public int getItemCount() {
        return mListLocal.size();
    }

    public void updateListView(List<SerializableStation> stationList) {
        if (stationList == null) {
            this.mListLocal = new ArrayList<SerializableStation>();
        } else {
            this.mListLocal = (ArrayList<SerializableStation>) stationList;
        }
        notifyDataSetChanged();
    }

    public class SureCarStationHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView_station_header;
        private final TextView mTextView_station_end;

        public SureCarStationHolder(View itemView) {
            super(itemView);
            mTextView_station_header = (TextView) itemView.findViewById(R.id.text_view_station_header);
            mTextView_station_end = (TextView) itemView.findViewById(R.id.text_view_station_end);

        }

        public void bindHolder(final SerializableStation station, final int position, final SureCarStationHolder holder) {
            String stationName = station.getStationName();
            String station_header = stationName.substring(0, stationName.length() - 3);

            mTextView_station_header.setText(station_header);
            mTextView_station_end.setText(R.string.shou_fei_zhan);

            // mTextView.setSelected(position == selectedItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //      selectedItem = holder.getLayoutPosition();
                    notifyDataSetChanged();
                    itemClick.itemClick(station);
                }
            });
        }

    }


    public interface onItemClick {
        void itemClick(SerializableStation station);

    }

}
