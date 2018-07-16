package com.wolf.zero.greenroad.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.adapter.BasePhotoAdapter;
import com.wolf.zero.greenroad.adapter.BasePhotoViewHolder;
import com.wolf.zero.greenroad.adapter.RecycleViewDivider;
import com.wolf.zero.greenroad.litepalbean.SupportBlack;
import com.wolf.zero.greenroad.tools.ActionBarTool;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlackListActivity extends BaseActivity {

    @BindView(R.id.toolbar_black_list)
    Toolbar mToolbarBlackList;
    @BindView(R.id.recycler_black_list)
    RecyclerView mRecyclerBlackList;
    private List<SupportBlack> mSupportBlacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_list);
        ButterKnife.bind(this);

        initToolbar();
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecycleViewDivider divider = new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 5, Color.GRAY);
        mRecyclerBlackList.setLayoutManager(manager);
        mRecyclerBlackList.addItemDecoration(divider);

        BasePhotoAdapter<SupportBlack> adapter = new BasePhotoAdapter<SupportBlack>(this,
                R.layout.item_black_text, (ArrayList<SupportBlack>) mSupportBlacks) {
            @Override
            public void convert(BasePhotoViewHolder holder, int position, SupportBlack supportBlack) {
                TextView textView = holder.getView(R.id.text_item_black);
                textView.setText(supportBlack.getLicense().toString());
            }
        };
        mRecyclerBlackList.setAdapter(adapter);
    }

    private void initData() {
        mSupportBlacks = DataSupport.findAll(SupportBlack.class);

    }

    private void initToolbar() {
        setSupportActionBar(mToolbarBlackList);

        TextView title_text_view = ActionBarTool.getInstance(this, 991).getTitle_text_view();
        title_text_view.setText("黑名单列表");

        mToolbarBlackList.setNavigationIcon(R.drawable.back_up_logo);

        mToolbarBlackList.setNavigationOnClickListener(v -> finish());
    }
}
