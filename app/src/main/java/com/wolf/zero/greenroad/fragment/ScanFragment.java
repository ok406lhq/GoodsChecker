package com.wolf.zero.greenroad.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.SpinnerPopupWindow;
import com.wolf.zero.greenroad.adapter.SpinnerAdapter;
import com.wolf.zero.greenroad.bean.ScanInfoBean;
import com.wolf.zero.greenroad.litepalbean.SupportDetail;
import com.wolf.zero.greenroad.litepalbean.SupportScan;
import com.wolf.zero.greenroad.litepalbean.TeamItem;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.tools.SPUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScanFragment extends Fragment {

    private static final int REQUEST_CODE_SCAN = 901;


    Unbinder unbinder;

    /* @BindView(R.id.scan_qr_code)
     TextView mScanQrCode;*/
   /* @BindView(R.id.btn_edit_able)
    ToggleButton mBtnEditAble;*/
    private static ToggleButton mToggleIsLimit;
    private static TextView mText_table_1;
    private static TextView mText_table_4;
    private static TextView mText_table_5;
    private static TextView mText_table_6;
    private static TextView mText_table_10;
    private static TextView mText_table_12;

    private static ScanFragment sFragment;
    private static SupportScan sSupportScan;
    private static String enterType;

    private EditText[] mEditTextsScan;
    private int mThemeTag;
    private String mLicenceNumber;
    private int mWidth;
    private SpinnerAdapter mAdapterLane;
    private SpinnerPopupWindow mPopupWindow;
    private float mDimension;
    private ArrayList<String> mLaneList;
    private static SupportDetail sSupportDetail;

    public ScanFragment() {
        // Required empty public constructor
    }

    public static ScanFragment newInstance(String type) {
        if (sFragment == null) {
            sFragment = new ScanFragment();
        }
        enterType = type;
        return sFragment;
    }

    public static ScanFragment newInstance(String type, SupportScan scanInfoBean, SupportDetail supportDetail) {
        if (sFragment == null) {
            sFragment = new ScanFragment();
        }
        sSupportScan = scanInfoBean;
        sSupportDetail = supportDetail;
        enterType = type;
        return sFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThemeTag = (int) SPUtils.get(getContext(), SPUtils.KEY_THEME_TAG, 1);
        mDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120,
                getResources().getDisplayMetrics());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView(view);
        return view;
    }

    private void initView(View view) {


        mText_table_1 = (TextView) view.findViewById(R.id.text_table_1);
        mText_table_4 = (TextView) view.findViewById(R.id.text_table_4);
        mText_table_5 = (TextView) view.findViewById(R.id.text_table_5);
        mText_table_6 = (TextView) view.findViewById(R.id.text_table_6);
        mText_table_10 = (TextView) view.findViewById(R.id.text_table_10);
        mText_table_12 = (TextView) view.findViewById(R.id.text_table_12);
        mToggleIsLimit = (ToggleButton) view.findViewById(R.id.toggle_is_limit);

        List<TeamItem> teamItems = DataSupport.where("username = ? ", SPUtils.get(getActivity(), GlobalManager.USERNAME, "qqqq") + "").find(TeamItem.class);
//        String station = SPListUtil.getStrListValue(getActivity(), SPListUtil.APPCONFIGINFO).get(2);
        if (teamItems.size() > 0) {
            mText_table_10.setText(teamItems.get(0).getStation());
            mText_table_12.setText(teamItems.get(0).getDefaultLane());
        } else {
            mText_table_10.setText("无");
            mText_table_12.setText("A01");
        }


        //从草稿的详情页进入采集界面进行修改,会初始化扫描的内容
        if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(enterType)) {
            mText_table_5.setText(sSupportScan.getScan_05Q());
            mText_table_10.setText(sSupportScan.getScan_10Q());
            mToggleIsLimit.setChecked(sSupportScan.getIsLimit() == 0 ? false : true);

            mText_table_1.setText(sSupportDetail.getNumber());
            mText_table_4.setText(sSupportDetail.getDetail_weight());
            mText_table_6.setText(sSupportDetail.getDetail_free());
            mText_table_12.setText(sSupportDetail.getDetail_export());
        } else {
            mToggleIsLimit.setChecked(true);
        }

    }


    @OnClick({
            R.id.toggle_is_limit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toggle_is_limit:
                isLimit();
                break;
            default:
                break;
        }
    }

    /**
     * 超限率,是否超限,默认否
     */
    private void isLimit() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static void setScanConnectListener(ScanBeanConnectListener listener) {

        String scan_05Q = mText_table_5.getText().toString().trim();
        String scan_10Q = mText_table_10.getText().toString().trim();
        boolean isLimit = mToggleIsLimit.isChecked();

        ScanInfoBean bean = new ScanInfoBean();

        bean.setScan_05Q(scan_05Q);
        bean.setScan_10Q(scan_10Q);
        bean.setIsLimit(isLimit ? 0 : 1);


        Logger.i("!!!!!!!!!!!!!!!!!" + bean.toString());
        listener.beanConnect(bean);
    }

    public static void notifyNumberChange(String carNumber) {
        if (carNumber != null && mText_table_1 != null) {
            Logger.i(carNumber);
            mText_table_1.setText(carNumber);
        }
    }

    public static void notifyGoodsChange(String goods) {
        if (goods != null && mText_table_5 != null) {
            Logger.i(goods);
            mText_table_5.setText(goods);
        }
    }

    public static void notifyWeightChange(String weight) {
        if (weight != null && mText_table_4 != null) {
            Logger.i(weight);
            mText_table_4.setText(weight);
        }
    }

    public static void notifyFreeChange(String free) {
        if (free != null && mText_table_6 != null) {
            Logger.i(free);
            mText_table_6.setText(free);
        }
    }

    public static void notifyExportChange(String export) {
        if (export != null && mText_table_12 != null) {
            Logger.i(export);
            mText_table_12.setText(export);
        }
    }


    public interface ScanBeanConnectListener {
        void beanConnect(ScanInfoBean bean);
    }
}
