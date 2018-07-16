package com.wolf.zero.greenroad.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.adapter.PreviewItemAdapter;
import com.wolf.zero.greenroad.adapter.RecycleViewDivider;
import com.wolf.zero.greenroad.helper.DeleteHelper;
import com.wolf.zero.greenroad.helper.SortTime;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.tools.ActionBarTool;
import com.wolf.zero.greenroad.tools.ImageProcessor;
import com.wolf.zero.greenroad.tools.SPUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class SubmitActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar_draft)
    Toolbar mToolbarPreview;
    @BindView(R.id.recycler_view_preview)
    RecyclerView mRecyclerViewPreview;
    @BindView(R.id.text_free_or_pass)
    TextView mTextFreeOrPass;

    private SubmitActivity mActivity;
    private Context mContext;
    private List<SupportDraftOrSubmit> mSubmitList;
    private PreviewItemAdapter mAdapter;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        ButterKnife.bind(this);
        mUsername = (String) SPUtils.get(this, GlobalManager.USERNAME, "qqqq");
        mTextFreeOrPass.setText("通过审核");
        mActivity = this;
        mContext = this;

        initToolbar();
        initData();
        initView();
    }


    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewPreview.setLayoutManager(manager);

        mRecyclerViewPreview.addItemDecoration(new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, 10, Color.WHITE));

        mAdapter = new PreviewItemAdapter(mContext, mActivity,
                (ArrayList<SupportDraftOrSubmit>) mSubmitList, (support) -> {
            PreviewDetailActivity.actionStart(mContext, support,
                    PreviewDetailActivity.ACTION_SUBMIT_ITEM);
        }, (itemView, lite_ID, position) -> {

        });

        mRecyclerViewPreview.setAdapter(mAdapter);
    }


    private void initData() {
        mSubmitList = DataSupport.
                where(GlobalManager.LITE_CONDITION, mUsername, GlobalManager.TYPE_SUBMIT_LITE).find(SupportDraftOrSubmit.class);

//        SPUtils.putAndApply(this,SPUtils.MATH_SUBMIT_LITE,mSubmitList.size());

//        for (int i = 0; i < mSubmitList.size(); i++) {
//            Logger.i("------------" + mSubmitList.get(i).toString());
//
//        }
        SortTime sortDraftTime = new SortTime();

        Collections.sort(mSubmitList, sortDraftTime);
//        for (int i = 0; i < mSubmitList.size(); i++) {
//            Logger.i("++++++++++++" + mSubmitList.get(i).toString());
//        }
    }

    private void initToolbar() {

        setSupportActionBar(mToolbarPreview);

        TextView title_text_view = ActionBarTool.getInstance(mActivity, 992).getTitle_text_view();
        title_text_view.setText("提交列表");

        mToolbarPreview.setNavigationIcon(R.drawable.back_up_logo);
        mToolbarPreview.setNavigationOnClickListener(v -> finish());
    }

    /**
     * @param photoPath 得到缩小的Bitmap
     * @return
     */
    private Bitmap getBitmap(String photoPath) {
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);

        ImageProcessor processor = new ImageProcessor(bitmap);
        return processor.scale((float) 0.25);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ACache.get(this).put("preview", (ArrayList<SerializableNumber>) mNumberList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_preview_7:
                DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_SUBMIT_LITE, 7, supportList -> {
                    mAdapter.updateListView(supportList);
                });
                break;
            case R.id.delete_preview_15:
                DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_SUBMIT_LITE, 15, supportList -> {
                    mAdapter.updateListView(supportList);
                });
                break;
            case R.id.delete_preview_30:
                DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_SUBMIT_LITE, 30, supportList -> {
                    mAdapter.updateListView(supportList);
                });
                break;
            case R.id.delete_preview_all:
                DeleteHelper.getInstance().deleteAllInfos(mContext, GlobalManager.TYPE_SUBMIT_LITE, supportList -> {
                    mAdapter.updateListView(supportList);
                });
                break;

            default:
                break;
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preview, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.preview_item_car_number:

                break;

            default:
                break;
        }
    }
}
