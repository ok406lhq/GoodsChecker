package com.wolf.zero.greenroad.fragment;/*
package com.android.htc.greenroad.fragment;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import R;
import SpinnerPopupWindow;
import PreviewDetailActivity;
import ShowActivity;
import com.android.htc.greenroad.activity.license.MemoryCameraActivity;
import com.android.htc.greenroad.activity.license.PermissionActivity;
import DetailsRecyclerAdapter;
import DividerGridItemDecoration;
import GoodsTextAdapter;
import SpinnerAdapter;
import SureGoodsAdapter;
import DetailInfoBean;
import PathTitleBean;
import SerializableGoods;
import TextChangeWatcher;
import SupportBlack;
import SupportCarTypeAndConfig;
import SupportDetail;
import SupportDraftOrSubmit;
import SupportGoods;
import SupportLocalGoods;
import SupportMedia;
import TeamItem;
import GlobalManager;
import ACache;
import AnimationUtil;
import BitmapUtil;
import Cn2Spell;
import GoodsComparator;
import LicenseKeyboardUtil;
import PingYinUtil;
import SPUtils;
import ToastUtils;
import MylinearLayout;
import NoScrollViewPager;
import com.kernal.plateid.AuthService;
import com.kernal.plateid.MainActivity;
import com.kernal.plateid.PlateAuthParameter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utills.CheckPermission;


public class DetailsFragment1 extends Fragment implements TextChangeWatcher.AfterTextListener {


    private ArrayList<SerializableGoods> mGoodsAllList = new ArrayList<>();
    ArrayList<ArrayList<SerializableGoods>> All_GoodsList = new ArrayList<ArrayList<SerializableGoods>>();
    private int mCurrentType = 0; //标记当前指向货物的类型
    private int mLastType = 0; //标记当前指向货物的类型
    private static final int CHOOSE_CAR_FROM_PICTURE = 1 * 911;
    private static boolean tag;
    Unbinder unbinder;
    @BindView(R.id.recycler_view_shoot_photo)
    RecyclerView mRecyclerViewShootPhoto;
    private static TextView mTvChangeGoodsDetail;

    @BindView(R.id.activity_recycler_left)
    ImageView mRecyclerLeft;
    @BindView(R.id.activity_recycler_right)
    ImageView mRecyclerRight;

    @BindView(R.id.goods_rl_goods_all)
    RelativeLayout mGoodsRlGoodsAll;
    @BindView(R.id.btn_license_identify)
    ImageView mBtnLicenseIdentify;
    @BindView(R.id.btn_open_goods)
    ImageView mBtnOpenGoods;
    @BindView(R.id.license_btn_license_sure)
    Button mLicenseBtnSure;
    @BindView(R.id.license_rl_license_all)
    RelativeLayout mLicenseRlLicenseAll;

    private static TextView mEtInputBox1;
    private static TextView mEtInputBox2;
    private static TextView mEtInputBox3;
    private static TextView mEtInputBox4;
    private static TextView mEtInputBox5;
    private static TextView mEtInputBox6;
    private static TextView mEtInputBox7;
    private static TextView mEtInputBox8;
    @BindView(R.id.ll_goods_hide_part)
    LinearLayout mLlGoodsHidePart;
    @BindView(R.id.detail_below_ll_license)
    LinearLayout mDetailBelowLlLicense;
    @BindView(R.id.detail_fragment_all)
    RelativeLayout mDetailFragmentAll;
    @BindView(R.id.btn_type_01)
    Button mBtnType01;
    @BindView(R.id.btn_type_02)
    Button mBtnType02;
    @BindView(R.id.btn_type_03)
    Button mBtnType03;
    @BindView(R.id.btn_type_04)
    Button mBtnType04;
    @BindView(R.id.btn_type_05)
    Button mBtnType05;
    @BindView(R.id.btn_type_06)
    Button mBtnType06;
    //    @BindView(R.id.goods_More)
//    RadioButton mGoodsMore;
    private TextView[] mEditTextViews;

    private DetailsRecyclerAdapter mPhotoAdapter;
    private static ArrayList<MyBitmap> mMyBitmaps_recycler_all;
    private Bitmap mBitmap_add;

    private LinearLayoutManager mLayoutManager;

    public static String sEnterType;
    private static SupportDetail sSupportDetail;
    private static int sLite_ID;

    private static List<LocalMedia> mSelectList_photo;
    private int mThemeTag;
    private LicenseKeyboardUtil keyboardUtil;
    private View mDetailView;
    private boolean mIsFirst;
    private static EditText mDetailTextWeight;
    private static EditText mDetailTextFree;
    private static EditText mDetailTextExport;
    private static EditText mDetailTextCar;
    private ArrayList<String> mLaneList;
    private SpinnerPopupWindow mPopupWindow;
    private NoScrollViewPager mShowViewPager;
    private ArrayList<String> mEdit_texts;
    private String mUsername;
    private Button mGoodsMore;
    private ArrayList<Button> mButtonList;
    private List<String> mTypeList;

    //    private List<String> carTypeList = new ArrayList<>();
    private String[] carTypes = {"大卡车", "大卡车B", "大卡车C", "大卡车D", "大卡车E", "大卡车F", "车型A", "车型B", "车型C", "车型D", "车型E", "车型F", "车型G", "车型H"
            , "货车", "小轿车", "电动汽车", "拖拉机拖拉机"};
    private String mCurrentCarType;
    private ArrayList<String> mMustList;

    public DetailsFragment1() {
        // Required empty public constructor
    }

    String sn;
    String authfile;

    private int ReturnAuthority = -1;

    public AuthService.MyBinder authBinder;

    static final String[] PERMISSION = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, // 读取权限
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.VIBRATE,
            Manifest.permission.INTERNET,
//		permission.FLASHLIGHT
    };
    //授权验证服务绑定后的操作与start识别服务

    public ServiceConnection authConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            authBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            authBinder = (AuthService.MyBinder) service;
            try {
                PlateAuthParameter pap = new PlateAuthParameter();
                pap.sn = sn;
                ReturnAuthority = authBinder.getAuth(pap);
                if (ReturnAuthority != 0) {
                    ToastUtils.singleToast("授权验证失败");
//                    Toast.makeText(getApplicationContext(),getString(com.kernal.plateid.R.string.license_verification_failed)+":"+ReturnAuthority,Toast.LENGTH_LONG).show();
                } else {
                    ToastUtils.singleToast("授权验证成功");
//                    Toast.makeText(getApplicationContext(), com.kernal.plateid.R.string.license_verification_success,Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                ToastUtils.singleToast("激活文件体验失败");
//                Toast.makeText(getApplicationContext(), com.kernal.plateid.R.string.failed_check_failure, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } finally {
                if (authBinder != null) {
                    getActivity().unbindService(authConn);
                }
            }
        }
    };
//    static final String[] PERMISSION = new String[] {permission.CAMERA,
//            permission.WRITE_EXTERNAL_STORAGE,// 写入权限
//            permission.READ_EXTERNAL_STORAGE, // 读取权限
//            permission.READ_PHONE_STATE,
//            permission.VIBRATE, permission.INTERNET,
//    };


    public static DetailsFragment1 newInstance(String enterType) {
        DetailsFragment1 fragment = new DetailsFragment1();
        sEnterType = enterType;
        return fragment;
    }

    public static DetailsFragment1 newInstance(String enterType, SupportDetail supportDetail,
                                               int lite_ID) {
        DetailsFragment1 fragment = new DetailsFragment1();
        sEnterType = enterType;
        sSupportDetail = supportDetail;
        sLite_ID = lite_ID;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tag = true;
        //mIsFirst为true时,表示从主界面进来的

        mIsFirst = true;
        if (GlobalManager.TYPE_MAIN_ENTER_SHOW.equals(sEnterType)) {
            mIsFirst = true;
        } else {
            mIsFirst = false;
        }
        mThemeTag = (int) SPUtils.get(getContext(), SPUtils.KEY_THEME_TAG, 1);
    }


    private static EditText mGoodEditText;

    @BindView(R.id.recycler_view_goods_sure)
    RecyclerView mGoodImageRecyclerView;
    @BindView(R.id.edit_text_qita)
    EditText mGoodEditTextQita;
    @BindView(R.id.btn_sure_qita)
    Button mGoodBtnSureQita;
    @BindView(R.id.rl_edit_qita)
    RelativeLayout mGoodRlEditQita;

    private SureGoodsAdapter mGoodsAdapter;

    private static String mGoodsText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDetailView = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, mDetailView);
        mUsername = (String) SPUtils.get(getActivity(), GlobalManager.USERNAME, "qqqq");
        initView(mDetailView);

//        initRadioColor();
        initPhotoRecyclerView();
        initGoodsAll(mDetailView);

        Intent authIntent = new Intent(getActivity(), AuthService.class);
        getActivity().bindService(authIntent, authConn, Service.BIND_AUTO_CREATE);
//
        Button button = (Button) mDetailView.findViewById(R.id.yanzheng);
        button.setOnClickListener(view -> {
//            if (Build.VERSION.SDK_INT >= 23) {
//                CheckPermission checkPermission = new CheckPermission(getActivity());
//                if (checkPermission.permissionSet(PERMISSION)) {
//                    com.kernal.plateid.PermissionActivity.startActivityForResult(getActivity(),0,"AuthService",  PERMISSION);
//                }
//                CreatViewtoAuthservice();
//            }else{
//                CreatViewtoAuthservice();
//            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        });


        return mDetailView;
    }

    public void CreatViewtoAuthservice() {
        EditText editText = new EditText(getActivity().getApplicationContext());
        editText.setTextColor(Color.BLACK);
        new android.app.AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(editText)
                .setPositiveButton(R.string.license_verification, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sn = editText.getText().toString().toUpperCase();
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                        Intent authIntent = new Intent(getActivity(), AuthService.class);
                        getActivity().bindService(authIntent, authConn, Service.BIND_AUTO_CREATE);
                        dialog.dismiss();

                    }
                })
                .show();

    }
   */
/* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SYSTEM_RESULT_CODE == requestCode && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("recogImagePath", pic);
            startActivity(intent);
            finish();
        }
        if (requestCode == SELECT_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            String picPathString = null;
            Uri uri = data.getData();
            picPathString = Utils.getPath(getApplicationContext(), uri);
            if (picPathString != null && !picPathString.equals("") && !picPathString.equals(" ") && !picPathString.equals("null")) {
                File file = new File(picPathString);
                Intent intentResult = new Intent(getApplicationContext(), ResultActivity.class);
                if (!file.exists() || file.isDirectory()) {
                    ToastUtils.singleToast("请选择一张正确的图片");
                } else {
                    if (picPathString.endsWith(".jpg") || picPathString.endsWith(".JPG") || picPathString.endsWith(".png") || picPathString.endsWith(".PNG")) {
                        intentResult.putExtra("recogImagePath", picPathString);
                        startActivity(intentResult);
                        getActivity().finish();
                    } else {
                        ToastUtils.singleToast("请选择一张正确的图片");
                    }
                }

            }


        }
    }*//*


        private RecyclerView mGoodTextRecycler;
    private static ArrayList<String> mGoodsTextList;
    private GoodsTextAdapter mGoodsTextAdapter;
    private static StringBuilder sGoodsBuilder;
    private LinearLayoutManager mGoodLayoutManager;
    private ArrayList<SerializableGoods> mCurrentGoodsList;

    private void initGoodsAll(View view) {
        if (mGoodsTextList == null) {
            mGoodsTextList = new ArrayList<>();
        } else {
            mGoodsTextList.clear();
        }
        initGoodsData();
        initGoodView(view);
        initGoodRecyclerView();
        initGoodsTextRecycler();
    }


    */
/**
     * 添加的货物信息
     *//*

    private void initGoodsTextRecycler() {
        mGoodLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        mGoodTextRecycler.setLayoutManager(mGoodLayoutManager);

        mGoodsTextAdapter = new GoodsTextAdapter(getContext(), mGoodsTextList, pos -> {
            mGoodsTextList.remove(pos);

            mGoodsTextAdapter.notifyDataSetChanged();
        });
        mGoodTextRecycler.setAdapter(mGoodsTextAdapter);
    }

    private void updateTextListView(ArrayList<String> mTextList) {
        mGoodsTextAdapter.updateListView(mTextList);
        if (mTextList.size() > 3) {
            mGoodTextRecycler.scrollToPosition(mTextList.size() - 1);
        }
    }


    private void initGoodView(View view) {
        mGoodEditText = (EditText) view.findViewById(R.id.goods_edit_text);
        mGoodsMore = (Button) view.findViewById(R.id.goods_More);
        mButtonList = new ArrayList<>();
        mButtonList.add(mBtnType01);
        mButtonList.add(mBtnType02);
        mButtonList.add(mBtnType03);
        mButtonList.add(mBtnType04);
        mButtonList.add(mBtnType05);
        mButtonList.add(mBtnType06);
        mButtonList.add(mGoodsMore);

        mButtonList.get(0).setTextColor(getActivity().getResources().getColor(R.color.licence_selected_text_day));
        mButtonList.get(0).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));

        List<String> typeList = DataSupport.findFirst(SupportGoods.class).getGoodsTypeList();
        typeList.add("其他");
        if (typeList.size() > 7) {
            for (int i = 0; i < 7; i++) {
                if (i < 6) {
                    mButtonList.get(i).setText(typeList.get(i));
                    int finalI = i;
                    mButtonList.get(i).setOnClickListener(view1 -> {
                        closeQiTa(false);
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

                        mGoodsMore.setText("更多 >>");
                        mCurrentType = finalI;
                        mLastType = mCurrentType;
                        Logger.i("mLastType   " + mLastType + "mCurrentType" + mCurrentType);

                        mCurrentGoodsList = All_GoodsList.get(mCurrentType);
                        mGoodsAdapter.updateListView(mCurrentGoodsList);
//                        Logger.i("第" + mCurrentType + "种类型" + typeList.get(mCurrentType));

                        setButtonStyle(view1, finalI);
                    });
                } else {
                    mButtonList.get(i).setText("更多 >>");
                    mButtonList.get(i).setOnClickListener(view1 -> {
//                        Logger.i("更多");
                        openMore(typeList);
                    });
                }
            }
        } else {
            for (int i = 0; i < 7; i++) {
                if (i < typeList.size()) {
                    int finalI = i;
                    mButtonList.get(i).setText(typeList.get(i));
                    if (i < typeList.size() - 1) {
                        mButtonList.get(i).setOnClickListener(view1 -> {
                            closeQiTa(false);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

                            mCurrentType = finalI;
                            mLastType = mCurrentType;
                            Logger.i("mLastType   " + mLastType + "mCurrentType" + mCurrentType);

                            mCurrentGoodsList = All_GoodsList.get(mCurrentType);
                            mGoodsAdapter.updateListView(mCurrentGoodsList);
                            setButtonStyle(view1, finalI);
                        });
                    } else {
                        mLastType = mCurrentType;

                        mCurrentType = finalI;
                        Logger.i("mLastType   " + mLastType + "mLastType  " + mCurrentType);

                        mButtonList.get(i).setOnClickListener(view1 -> {
                            openQiTa();
                            setButtonStyle(view1, finalI);
                        });
                    }
                } else {
                    mButtonList.get(i).setVisibility(View.INVISIBLE);
                }
            }
        }

        mGoodEditText.setText("");
        mGoodEditText.addTextChangedListener(new TextChangeWatcher(this));
        mGoodTextRecycler = (RecyclerView) view.findViewById(R.id.goods_text_recycler);

    }

    */
/**
     * 点击类型按钮时,切换Button的样式
     *
     * @param view
     * @param finalI
     *//*

    private void setButtonStyle(View view, int finalI) {
        for (int j = 0; j < 7; j++) {
            mButtonList.get(j).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
            mButtonList.get(j).setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
        }
        view.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
        mButtonList.get(finalI).setTextColor(getActivity().getResources().getColor(R.color.day_color_white));

    }


    private ArrayList<String> getGoodsTextList(String goodTexts) {
        if (mGoodsTextList == null) {
            mGoodsTextList = new ArrayList<>();
        } else {
            mGoodsTextList.clear();
        }
        if (goodTexts != null && goodTexts.length() != 0) {

            String[] goodsName = goodTexts.split(";");

            for (int i = 0; i < goodsName.length; i++) {
                mGoodsTextList.add(goodsName[i]);
            }
        }
        return mGoodsTextList;
    }


    private void notifyScanLicenseChange(String carNumber) {
        ScanFragment.notifyNumberChange(carNumber);
    }

    private void notifyScanWeightChange(String weight) {
        ScanFragment.notifyWeightChange(weight);
    }

    private void notifyScanFreeChange(String free) {
        ScanFragment.notifyFreeChange(free);
    }

    private void notifyScanExportChange(String export) {
        ScanFragment.notifyExportChange(export);
    }

    private boolean isBlack(String carNumber, List<SupportBlack> blackList) {

        for (int i = 0; i < blackList.size(); i++) {
            if (carNumber.equals(blackList.get(i).getLicense())) {
                return true;
            }
        }
        return false;
    }

    */
/**
     * 加载选中标记的图片信息,提交以及进入photoFragment中都需要
     * 因为从草稿详情页进入编辑修改若没有进入photoFragment直接再存储会丢失selected的数据
     * 所以提前加载
     *//*

    private void inflateSelected() {
        List<SupportDraftOrSubmit> supportDraftOrSubmits = DataSupport.where("lite_ID = ? and username = ?", String.valueOf(sLite_ID), mUsername).find(SupportDraftOrSubmit.class);
        SupportMedia supportMedia = supportDraftOrSubmits.get(0).getSupportMedia();


        if (supportMedia != null && supportMedia.getPaths() != null
                && supportMedia.getPaths().size() != 0) {

            if (mSelectList_photo == null) {
                mSelectList_photo = new ArrayList<>();
            } else {
                mSelectList_photo.clear();
            }
            for (int i = 0; i < supportMedia.getPaths().size(); i++) {
                LocalMedia localMedia = initSelected(supportMedia, i);
                if (localMedia != null) {
                    mSelectList_photo.add(localMedia);
                }
            }

        }
    }

    private LocalMedia initSelected(SupportMedia supportMedia, int i) {
        if (supportMedia != null) {
            String path = supportMedia.getPaths().get(i);
            String pictureType = supportMedia.getPictureTypes().get(i);
            long duration = supportMedia.getDurations().get(i);
            int mimeType = supportMedia.getMimeTypes().get(i);
            int height = supportMedia.getHeights().get(i);
            int width = supportMedia.getWidths().get(i);
            int num = supportMedia.getNums().get(i);
            int position = supportMedia.getPositions().get(i);
            LocalMedia localMedia = new LocalMedia(path, duration, mimeType, pictureType, width, height);
            localMedia.setNum(num);
            localMedia.setPosition(position);
            localMedia.setChecked(false);
            localMedia.setCompressed(false);
            localMedia.setCut(false);
            if (path == null || "".equals(path)) {
                return null;
            } else {
                return localMedia;
            }
        }
        return null;
    }

    private void initPhotoRecyclerView() {
        mBitmap_add = BitmapFactory.decodeResource(getResources(), R.drawable.image_photo_add);
        if (mMyBitmaps_recycler_all == null) {
            mMyBitmaps_recycler_all = new ArrayList<>();
        } else {
            mMyBitmaps_recycler_all.clear();
        }

        for (int i = 0; i < 3; i++) {
            String title = "照片" + (i + 1);
            MyBitmap myBitmap = new MyBitmap(mBitmap_add, title);
            mMyBitmaps_recycler_all.add(myBitmap);
        }
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerViewShootPhoto.setLayoutManager(mLayoutManager);
        mPhotoAdapter = new DetailsRecyclerAdapter(getContext(), mMyBitmaps_recycler_all, () -> {
//            enterSureActivity(GlobalManager.ENTERTYPE_PHOTO);
//            ToastUtils.singleToast("拍照");
            openPicture(8, 1, CHOOSE_CAR_FROM_PICTURE);
        });
        mRecyclerViewShootPhoto.setAdapter(mPhotoAdapter);
    }

    private void openPicture(int maxNum, int minNum, int result_type) {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(DetailsFragment1.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_QQ_style)
                .maxSelectNum(maxNum)
                .minSelectNum(minNum)
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .previewVideo(false)
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)
                .isCamera(true)
                .enableCrop(false)
                .compress(false)
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)
                .glideOverride(160, 160)
                .previewEggs(true)
                .selectionMedia(mSelectList_photo)
//                .selectionMedia(choose_type == CHOOSE_CAR_NUMBER ? mSelectList_photo :
//                        choose_type == CHOOSE_CAR_BODY ? mSelectList_cheshen : mSelectList_huowu)
                .forResult(result_type);
    }


    private void initView(View view) {

        mShowViewPager = (NoScrollViewPager) getActivity().findViewById(R.id.view_pager_show);

        TextView title_text_toll = (TextView) view.findViewById(R.id.title_text_toll).findViewById(R.id.details_fragment_title_text);
        TextView title_text_photo = (TextView) view.findViewById(R.id.title_text_photo).findViewById(R.id.details_fragment_title_text);
        title_text_toll.setText("收费信息");
        title_text_photo.setText("采集照片");

        mDetailTextWeight = (EditText) view.findViewById(R.id.detail_text_weight);
        mDetailTextFree = (EditText) view.findViewById(R.id.detail_text_free);
        mDetailTextExport = (EditText) view.findViewById(R.id.detail_text_export);
        mDetailTextCar = (EditText) view.findViewById(R.id.detail_text_car);

        //动态修改必须提交项
        SupportCarTypeAndConfig supportConfig = DataSupport.findFirst(SupportCarTypeAndConfig.class);
        Logger.i("supportConfig" + "---------" + supportConfig.toString());
        List<String> configMustList = supportConfig.getConfigMustList();
//        initMustItem(configMustList);
        if (mMustList == null) {
            mMustList = new ArrayList<>();
        } else {
            mMustList.clear();
        }

        if (configMustList != null) {
            mMustList.addAll(configMustList);
        } else {
            mMustList.add("h");//如果为空则添一条不相关的字符串,避免报错
        }
        if (!mMustList.contains("称重质量")) {
            mDetailTextWeight.setFocusable(false);
            mDetailTextWeight.setFocusableInTouchMode(false);
        } else {
            mDetailTextWeight.addTextChangedListener(new TextChangeWatcher(editable -> {
                notifyScanWeightChange(String.valueOf(editable));
            }));
        }
        if (!mMustList.contains("免费金额")) {
            mDetailTextFree.setFocusable(false);
            mDetailTextFree.setFocusableInTouchMode(false);
        } else {
            mDetailTextFree.addTextChangedListener(new TextChangeWatcher(editable -> {
                notifyScanFreeChange(String.valueOf(editable));
            }));
        }
        if (mMustList.contains("出口车道")) {
            List<TeamItem> teamItemList = DataSupport.where("username = ? ", mUsername).find(TeamItem.class);
            if (teamItemList.size() > 0) {
                mDetailTextExport.setText(teamItemList.get(0).getDefaultLane());
            } else {
                mDetailTextExport.setText("A01");
            }
            mDetailTextExport.setOnClickListener(view1 -> {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                selectExportOrCarText(view1, "export", mDetailTextExport);
            });
        } else {
            mDetailTextExport.setText("");
        }
        if (mMustList.contains("车型")) {

            SupportCarTypeAndConfig firstConfig = DataSupport.findFirst(SupportCarTypeAndConfig.class);
            if (firstConfig != null) {
                List<String> carTypeList = firstConfig.getCarTypeList();
                if (carTypeList != null && carTypeList.size() != 0) {
                    mDetailTextCar.setText(carTypeList.get(0));
                    mCurrentCarType = carTypeList.get(0);
                } else {
                    mDetailTextCar.setText("货车");
                }
            } else {
                mDetailTextCar.setText("货车");
            }
            mDetailTextCar.setOnClickListener(view1 -> {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

                selectExportOrCarText(view1, "car", mDetailTextCar);
            });
        } else {
            mDetailTextCar.setText("");
        }

        mEtInputBox1 = (TextView) view.findViewById(R.id.et_input_box_1);
        mEtInputBox2 = (TextView) view.findViewById(R.id.et_input_box_2);
        mEtInputBox3 = (TextView) view.findViewById(R.id.et_input_box_3);
        mEtInputBox4 = (TextView) view.findViewById(R.id.et_input_box_4);
        mEtInputBox5 = (TextView) view.findViewById(R.id.et_input_box_5);
        mEtInputBox6 = (TextView) view.findViewById(R.id.et_input_box_6);
        mEtInputBox7 = (TextView) view.findViewById(R.id.et_input_box_7);
        mEtInputBox8 = (TextView) view.findViewById(R.id.et_input_box_8);
        mTvChangeGoodsDetail = (TextView) view.findViewById(R.id.tv_change_goods_detail);

        mEditTextViews = new TextView[]{mEtInputBox1, mEtInputBox2,
                mEtInputBox3, mEtInputBox4, mEtInputBox5, mEtInputBox6, mEtInputBox7, mEtInputBox8};


        MylinearLayout linearLayout = (MylinearLayout) view.findViewById(R.id.ll_license);

        linearLayout.setOnClick(() -> openLicense());
    }

//    private void initMustItem(List<String> configMustList) {

//
//        if (!configMustList.contains("车型")) {
//            mDetailTextCar.setFocusable(false);
//
//            mDetailTextCar.setFocusableInTouchMode(false);
////            mDetailTextCar.setClickable(false);
//        }
//        if (!configMustList.contains("出口")) {
//            mDetailTextExport.setFocusable(false);
//
//            mDetailTextExport.setFocusableInTouchMode(false);
////            mDetailTextExport.setClickable(false);
//        }


//        boolean lane = configMustList.contains("车道");
//        boolean goods = configMustList.contains("货物");
//        Logger.i("车道" + lane + "-----" + "货物" + goods);
//
//    }

    private void selectExportOrCarText(View view, String type, EditText editText) {
        int mWidth = mDetailTextExport.getWidth();
        ArrayList<String> arrayList = new ArrayList<>();
        if ("export".equals(type)) {
            List<TeamItem> teamItems = DataSupport.where("username = ?", mUsername).find(TeamItem.class);

            if (teamItems.size() > 0 && teamItems.get(0).getLanes().size() > 0) {
                arrayList.addAll(teamItems.get(0).getLanes());
            } else {
                arrayList.add("A01");
            }
        } else if ("car".equals(type)) {
            SupportCarTypeAndConfig firstConfig = DataSupport.findFirst(SupportCarTypeAndConfig.class);
            if (firstConfig != null) {
                List<String> carTypeList = firstConfig.getCarTypeList();
                if (carTypeList != null && carTypeList.size() != 0) {
                    arrayList.addAll(carTypeList);
                } else {
                    arrayList.add("货车");
                }
            } else {
                arrayList.add("货车");
            }
        }

        SpinnerAdapter mAdapterLane = new SpinnerAdapter((AppCompatActivity) getActivity(), arrayList, position -> {
            String title = arrayList.get(position);
            editText.setText(title);
            notifyScanExportChange(title);
            mPopupWindow.dismissPopWindow();
        });

        mPopupWindow = new SpinnerPopupWindow.Builder(getActivity())
                .setmLayoutManager(null, 0)
                .setmAdapter(mAdapterLane)
//                .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
                .setmHeight(200 * (arrayList.size()) > 700 ? 700 : 200 * (arrayList.size()))
                .setmWidth("car".equals(type) ? (int) (mWidth * 1.6) : (int) (mWidth * 1.3))
                .setOutsideTouchable(true)
                .setFocusable(true)
                .build();

//        mPopupWindow.showPopWindow(view, (int) mDimension);
        mPopupWindow.showPopWindowCenter(view);
    }


    @OnClick({R.id.tv_change_goods_detail,
            R.id.activity_recycler_left, R.id.activity_recycler_right,
            R.id.btn_license_identify, R.id.btn_open_goods,
            R.id.license_btn_license_sure, R.id.goods_btn_good_sure,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_license_identify:
//                CreatDialog();
                openIdentify();

                break;

            case R.id.license_btn_license_sure:
                closeLicense();
                break;

            case R.id.btn_open_goods:
                openGoods();

                break;
            case R.id.goods_btn_good_sure:
                closeGoods(view);

                break;

            case R.id.tv_change_goods_detail:
                openGoods();
//                enterSureActivity(GlobalManager.ENTERTYPE_GOODS);
//                ToastUtils.singleToast("货物");

                break;
            //预览跳转到最左边
            case R.id.activity_recycler_left:
                mRecyclerViewShootPhoto.scrollToPosition(0);
                mRecyclerLeft.setVisibility(View.INVISIBLE);
                mRecyclerRight.setVisibility(View.VISIBLE);
                break;
            //预览跳转到最右边
            case R.id.activity_recycler_right:
                mRecyclerViewShootPhoto.scrollToPosition(mMyBitmaps_recycler_all.size() - 1);
                mRecyclerLeft.setVisibility(View.VISIBLE);
                mRecyclerRight.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }
*/
/*    *//*
*/
/**
     * @return void    返回类型
     * @throws
     * @Title: CreatDDialog
     * @Description: TODO(这里用一句话描述这个方法的作用) 创建选择对话框，选择识别模式,只在进入视频识别时进行选择，拍照识别默认快速识别模式
     *//*
*/
/*
    private void CreatDialog() {
        // TODO Auto-generated method stub
        Intent video_intent = new Intent();
        video_intent.putExtra("camera", true);

        //true  精准模式
        RecogService.recogModel = true;

        video_intent = new Intent(getActivity(), MemoryCameraActivity.class);
        if (Build.VERSION.SDK_INT >= 23) {
            CheckPermission checkPermission = new CheckPermission(getActivity());
            if (checkPermission.permissionSet(PERMISSION)) {
                PermissionActivity.startActivityForResult(getActivity(), 0, "true", PERMISSION);

            } else {
                video_intent.setClass(getActivity().getApplicationContext(), MemoryCameraActivity.class);
                video_intent.putExtra("camera", true);
                startActivity(video_intent);
                getActivity().finish();
            }
        } else {
            video_intent.setClass(getActivity().getApplicationContext(), MemoryCameraActivity.class);
            video_intent.putExtra("camera", true);
            startActivity(video_intent);
            getActivity().finish();
        }
    }*//*


    private void openIdentify() {
        if (Build.VERSION.SDK_INT >= 23) {
            CheckPermission checkPermission = new CheckPermission(getActivity());
            if (checkPermission.permissionSet(PERMISSION)) {
                PermissionActivity.startActivityForResult(getActivity(), 0, "true", PERMISSION);

            } else {
                closeLicense();
                MemoryCameraActivity.actionStart((AppCompatActivity) getActivity(), DetailsFragment1.this, true);
            }
        } else {
            closeLicense();
            MemoryCameraActivity.actionStart((AppCompatActivity) getActivity(), DetailsFragment1.this, true);
        }
    }

    */
/**
     * 打开车牌号的视图
     *//*

    public void openLicense() {
        if (mLicenseRlLicenseAll.getVisibility() == View.GONE) {
            forbidScroll();
            mDetailBelowLlLicense.setVisibility(View.GONE);
            getActivity().findViewById(R.id.menu_fab).setVisibility(View.GONE);
            TranslateAnimation animation = AnimationUtil.translate(0.0f, 0.0f, -0.8f, 0.0f);

            mLicenseRlLicenseAll.startAnimation(animation);
            mLicenseRlLicenseAll.setVisibility(View.VISIBLE);

            keyboardUtil = new LicenseKeyboardUtil(getContext(), DetailsFragment1.this, mDetailView,
                    mEditTextViews, "无".equals(getLicenseNumber()) ? 0 : getLicenseNumber().length());
//        keyboardUtil = new LicenseKeyboardUtil(getContext(), view, mEditTextViews, 0);
            keyboardUtil.showKeyboard();
        }
        if (mThemeTag == 1) {
            setTextColor(Color.DKGRAY);
        } else {
            setTextColor(Color.WHITE);
        }
    }


    */
/**
     * 关闭车牌号的视图
     *//*

    public void closeLicense() {
        if (mLicenseRlLicenseAll.getVisibility() == View.VISIBLE) {
            allowScroll();
            mDetailBelowLlLicense.setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.menu_fab).setVisibility(View.VISIBLE);

            TranslateAnimation mHiddenAction = AnimationUtil.translate(0.0f, 0.0f, 0.0f, 1.0f);

            mLicenseRlLicenseAll.startAnimation(mHiddenAction);
            mLicenseRlLicenseAll.setVisibility(View.GONE);
        }
        checkingBlack(getLicenseNumber());
    }

    */
/**
     * 打开货物的视图
     *//*

    private void openGoods() {

        if (mGoodsRlGoodsAll.getVisibility() == View.GONE) {
            forbidScroll();

            getActivity().findViewById(R.id.menu_fab).setVisibility(View.GONE);
            mDetailFragmentAll.setVisibility(View.GONE);
            TranslateAnimation good_animation = AnimationUtil.translate(0.0f, 0.0f, -0.8f, 0.0f);

            mGoodsRlGoodsAll.startAnimation(good_animation);

            mGoodsRlGoodsAll.setVisibility(View.VISIBLE);
        }
    }

    */
/**
     * 关闭货物的视图
     *
     * @param view
     *//*

    private void closeGoods(View view) {
        if (mGoodsRlGoodsAll.getVisibility() == View.VISIBLE) {
            allowScroll();

            mDetailFragmentAll.setVisibility(View.VISIBLE);

            TranslateAnimation good_mHiddenAction = AnimationUtil.translate(0.0f, 0.0f, 0.0f, 1.0f);

            mGoodsRlGoodsAll.startAnimation(good_mHiddenAction);
            mGoodsRlGoodsAll.setVisibility(View.GONE);

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘


            if (sGoodsBuilder == null) {
                sGoodsBuilder = new StringBuilder();
            } else if (sGoodsBuilder.length() != 0) {
                sGoodsBuilder.delete(0, sGoodsBuilder.length());
            }
            if (mGoodsTextList != null && mGoodsTextList.size() != 0) {

                for (int i = 0; i < mGoodsTextList.size(); i++) {
                    if (i == mGoodsTextList.size() - 1) {
                        sGoodsBuilder.append(mGoodsTextList.get(i));
                    } else {
                        sGoodsBuilder.append(mGoodsTextList.get(i) + ";");
                    }
                }

                mTvChangeGoodsDetail.setText(sGoodsBuilder.toString().trim());
            } else {
                mTvChangeGoodsDetail.setText("");
            }
        }
        getActivity().findViewById(R.id.menu_fab).setVisibility(View.VISIBLE);
        mGoodEditText.setText("");
        mLlGoodsHidePart.setVisibility(View.VISIBLE);
    }

    */
/**
     * 打开货物时禁止ViewPager滑动
     *//*

    private void forbidScroll() {
        mShowViewPager.setNeedScroll(false);
        ShowActivity.setTabLayoutCanClick(false);
    }

    */
/**
     * 打开货物时允许ViewPager滑动
     *//*

    private void allowScroll() {
        ShowActivity.setTabLayoutCanClick(true);
        mShowViewPager.setNeedScroll(true);
    }

    */
/**
     * 对扫描二维码后的信息进行解析
     *
     * @param requestCode
     * @param resultCode
     * @param data
     *//*

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ShowActivity.RESULT_CODE_LICENSE:

                //处理扫描结果（在界面上显示）
                if (null != data) {
                    String license_number = data.getStringExtra(ShowActivity.LICENSE_NUMBER);
                    Logger.i(license_number);
//                mTvChangeNumberDetail.setText(license_number);
//                    inflateNumber(license_number);
                    if (license_number != null && !"".equals(license_number)) {
                        clearInputBox();
                    }
                    checkingBlack(license_number);
                }
                break;
            case CHOOSE_CAR_FROM_PICTURE:

                // 图片选择
                if (mMyBitmaps_recycler_all == null) {
                    mMyBitmaps_recycler_all = new ArrayList<>();
                } else {
                    if (mMyBitmaps_recycler_all.size() != 0) {
                        mMyBitmaps_recycler_all.clear();
                    }
                }
                Logger.i("回调成功goods");
                mSelectList_photo = PictureSelector.obtainMultipleResult(data);

                Logger.i(mSelectList_photo.size() + "");
//                for (int i = 0; i < mSelectList_photo.size(); i++) {
//                    Logger.i(mSelectList_photo.get(i).toString());
//                }
                new Thread(() -> {
                    if (mSelectList_photo.size() > 0) {
                        for (int i = 0; i < mSelectList_photo.size(); i++) {
                            LocalMedia localMedia = mSelectList_photo.get(i);
                            String photo_path = localMedia.getPath();
                            Bitmap bitmap = BitmapUtil.convertToBitmap(photo_path, 800, 800);
                            String title = "照片-" + (i + 1);
                            MyBitmap myBitmap = new MyBitmap(photo_path, bitmap, title);
                            mMyBitmaps_recycler_all.add(myBitmap);
                        }
                    }
                    addElsePicture();

                    getActivity().runOnUiThread(() -> {
                        mPhotoAdapter.updateListView(mMyBitmaps_recycler_all);
                    });
                }).start();
                break;
        }
    }

    */
/**
     * 如果照片不足三张,则添加其他的带加号的图片
     *//*

    private void addElsePicture() {
        if (mMyBitmaps_recycler_all.size() < 3) {
            for (int i = mMyBitmaps_recycler_all.size() - 1; i < 2; i++) {
                String title = "照片" + (i + 2);
                MyBitmap myBitmap = new MyBitmap(mBitmap_add, title);
                mMyBitmaps_recycler_all.add(myBitmap);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    */
/**
     * 打开更多弹出窗口
     *
     * @param typeList
     *//*

    private void openMore(List<String> typeList) {
        int mWidth = mGoodsMore.getWidth();
        ArrayList<String> moreTypeList = new ArrayList<>();
        for (int i = 6; i < typeList.size(); i++) {
            moreTypeList.add(typeList.get(i));
        }
        SpinnerAdapter mAdapterType = new SpinnerAdapter((AppCompatActivity) getActivity(), moreTypeList, (int position) -> {
            String typeString = moreTypeList.get(position);

            mCurrentType = 6 + position;


            mGoodsMore.setText(typeString + " >>");

            for (int i = 0; i < mButtonList.size(); i++) {
                mButtonList.get(i).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
                mButtonList.get(i).setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
            }
            mGoodsMore.setTextColor(getActivity().getResources().getColor(R.color.day_color_white));
            mGoodsMore.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));

            if (mCurrentType < typeList.size() - 1) { //当前货种类为非"其他"的更多里面的项
                mLastType = mCurrentType;           //不能是最后一位
                Logger.i("mLastType   " + mLastType + "mLastType  " + mCurrentType);

                closeQiTa(true);
                mCurrentGoodsList = All_GoodsList.get(mCurrentType);
                mGoodsAdapter.updateListView(mCurrentGoodsList);
            } else {
                //记录打开其他之前所在的货物位置
                openQiTa();
            }
            mPopupWindow.dismissPopWindow();
        });

        mPopupWindow = new SpinnerPopupWindow.Builder(getActivity())
                .setmLayoutManager(null, 0)
                .setmAdapter(mAdapterType)
//                .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
                .setmHeight(200 * (typeList.size() - 6))
                .setmWidth((int) (mWidth * 1.6))
                .setOutsideTouchable(true)
                .setFocusable(true)
                .build();

        mPopupWindow.showPopWindowCenter(mGoodsMore);
    }

    */
/**
     * 打开其他的界面
     *//*

    private void openQiTa() {
        if (mGoodRlEditQita.getVisibility() == View.GONE) {
            mGoodRlEditQita.setVisibility(View.VISIBLE);
        }
        if (mGoodImageRecyclerView.getVisibility() == View.VISIBLE) {
            mGoodImageRecyclerView.setVisibility(View.GONE);
        }
        mGoodBtnSureQita.setOnClickListener(v -> {
            String goods_qita = mGoodEditTextQita.getText().toString().trim();
            if (goods_qita.length() > 0) {
                mGoodsTextList.add(goods_qita);
                updateTextListView(mGoodsTextList);
                mGoodEditTextQita.setText("");
            }
        });
    }


    private void closeQiTa(boolean isMore) {
        if (!isMore) {
            mGoodsMore.setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
            mGoodsMore.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
        }

        if (mGoodImageRecyclerView.getVisibility() == View.GONE) {
            mGoodImageRecyclerView.setVisibility(View.VISIBLE);
        }
        if (mGoodRlEditQita.getVisibility() == View.VISIBLE) {
            mGoodRlEditQita.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mLlGoodsHidePart.getVisibility() == View.VISIBLE) {
            mLlGoodsHidePart.setVisibility(View.GONE);
        }
//        AnimationUtil.translate()
//        mLlGoodsHidePart.setVisibility(View.GONE);
        String goodString = mGoodEditText.getText().toString().trim();

        Logger.i("在其他按钮上");
        closeQiTa(false);
//                mCurrentType = mLastType;
        Logger.i("mLastType   " + mLastType + "mCurrentType  " + mCurrentType);


        if ("".equals(goodString)) {
            mGoodsAdapter.updateListView(mCurrentGoodsList);
            mLlGoodsHidePart.setVisibility(View.VISIBLE);
        } else {
            Logger.i(goodString);

            // mCurrentType 所有的货种都包括(从0开始)   mTypeList 少一个其他
            if (mCurrentType == mTypeList.size()) {
                Logger.i("在其他按钮上");
                closeQiTa(false);
//                mCurrentType = mLastType;
                Logger.i("mLastType   " + mLastType + "mLastType  " + mCurrentType);
//                if (mLastType < 6) {
//
//                    mButtonList.get(mCurrentType).setTextColor(getActivity().getResources().getColor(R.color.licence_selected_text_day));
//                    mButtonList.get(mCurrentType).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//                } else {
//
//                    mGoodsMore.setText(mTypeList.get(mLastType) + " >>");
//
//                    mGoodsMore.setTextColor(getActivity().getResources().getColor(R.color.day_color_white));
//                    mGoodsMore.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//
////                    mButtonList.get(mCurrentType).setTextColor(getActivity().getResources().getColor(R.color.licence_selected_text_day));
////                    mButtonList.get(mCurrentType).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//                }
                mGoodsAdapter.updateListView(All_GoodsList.get(mLastType));
            } else {
                closeQiTa(true);
            }

            List<SerializableGoods> fileterList = PingYinUtil.getInstance()
                    .search_goods(mGoodsAllList, goodString);
            for (int i = 0; i < fileterList.size(); i++) {
                Logger.i(fileterList.get(i).getScientific_name());
                Logger.i(fileterList.get(i).getSimpleSpell());
            }
            mGoodsAdapter.updateListView(fileterList);
        }
    }


    public static void setDetailsConnectListener(DetailsBeanConnectListener listener) {

        ArrayList<PathTitleBean> pathTitleList = null;
        if (pathTitleList == null) {
            pathTitleList = new ArrayList<>();
        } else {
            pathTitleList.clear();
        }

        if (mMyBitmaps_recycler_all != null) {
            for (int i = 0; i < mMyBitmaps_recycler_all.size(); i++) {
                String title = mMyBitmaps_recycler_all.get(i).getTitle();
                String path = mMyBitmaps_recycler_all.get(i).getPath();
                if (path != null && !"".equals(path)) {
                    MyBitmap myBitmap = mMyBitmaps_recycler_all.get(i);
                    PathTitleBean titleBean = new PathTitleBean(GlobalManager.PHOTO_TYPE_SANZHENG,
                            myBitmap.getPath(), myBitmap.getTitle());
                    pathTitleList.add(titleBean);
                }
            }
            if (pathTitleList != null) {
                Logger.i(pathTitleList.size() + "---" + "---");
            }
        }

        String number = getLicenseNumber();
        String goods = mTvChangeGoodsDetail.getText().toString().trim();

        String text_weight = mDetailTextWeight.getText().toString().trim();
        String text_free = mDetailTextFree.getText().toString().trim();
        String text_carType = mDetailTextCar.getText().toString().trim();
        String text_export = mDetailTextExport.getText().toString().trim();

        DetailInfoBean bean = new DetailInfoBean();
        bean.setNumber(number);
        bean.setGoods(goods);
        bean.setText_weight(text_weight);
        bean.setText_free(text_free);
        bean.setText_export(text_export);
        bean.setText_carType(text_carType);
        bean.setPath_and_title(pathTitleList);

        listener.beanConnect(bean);
    }

    public interface DetailsBeanConnectListener {

        void beanConnect(DetailInfoBean bean);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyBitmaps_recycler_all.clear();
        int allCount = 0;
        for (int i = 0; i < All_GoodsList.size(); i++) {
            ACache.get(getActivity()).put(ACache.Type_ACACHE + i, All_GoodsList.get(i));
//            allCount += All_GoodsList.get(i).size();
        }
//        ACache.get(getActivity()).put(ACache.MARK_TIME, allCount + "");
        ACache.get(getActivity()).put(ACache.TYPR_COUNT, All_GoodsList.size() + "");
        ACache.get(getActivity()).put(ACache.ALL_ACACHE, mGoodsAllList);

    }

    public static void notifyDataChange() {
        mSelectList_photo = null;
        mGoodsTextList = null;
        mGoodsText = null;
    }

    public static void notifyTag(boolean newTag) {
        tag = newTag;
    }

    public static void notifyChangeTextColor() {

//        mTvChangeNumberDetail.setTextColor(Color.RED);
    }

    private void inflateNumber(String carNumber) {

//        if (carNumber.length() == 7) {
//            mEtInputBox8.setText("");
//
//            String edit_1_I = carNumber.substring(0, 1);
//            String edit_2_I = carNumber.substring(1, 2);
//            String edit_3_I = carNumber.substring(2, 3);
//            String edit_4_I = carNumber.substring(3, 4);
//            String edit_5_I = carNumber.substring(4, 5);
//            String edit_6_I = carNumber.substring(5, 6);
//            String edit_7_I = carNumber.substring(6, 7);
//
//            String[] edit_texts = {edit_1_I, edit_2_I, edit_3_I, edit_4_I, edit_5_I, edit_6_I, edit_7_I};
//            for (int i = 0; i < edit_texts.length; i++) {
//                mEditTextViews[i].setText(edit_texts[i]);
//            }
//        } else if (carNumber.length() == 8) {
//            String edit_1_I = carNumber.substring(0, 1);
//            String edit_2_I = carNumber.substring(1, 2);
//            String edit_3_I = carNumber.substring(2, 3);
//            String edit_4_I = carNumber.substring(3, 4);
//            String edit_5_I = carNumber.substring(4, 5);
//            String edit_6_I = carNumber.substring(5, 6);
//            String edit_7_I = carNumber.substring(6, 7);
//            String edit_8_I = carNumber.substring(7, 8);
//
//            String[] edit_texts = {edit_1_I, edit_2_I, edit_3_I, edit_4_I, edit_5_I, edit_6_I, edit_7_I, edit_8_I};
//            for (int i = 0; i < edit_texts.length; i++) {
//                mEditTextViews[i].setText(edit_texts[i]);
//            }
//        }
        if (mEdit_texts == null) {
            mEdit_texts = new ArrayList<>();
        } else {
            mEdit_texts.clear();
        }

        for (int i = 0; i < carNumber.length(); i++) {
            String edit_text = carNumber.substring(i, i + 1);
            mEdit_texts.add(edit_text);
        }

        for (int i = 0; i < mEdit_texts.size(); i++) {
            mEditTextViews[i].setText(mEdit_texts.get(i));
        }
        notifyScanLicenseChange(carNumber);
    }

    private static String getLicenseNumber() {
        String number = mEtInputBox1.getText().toString().trim() +
                mEtInputBox2.getText().toString().trim() +
                mEtInputBox3.getText().toString().trim() +
                mEtInputBox4.getText().toString().trim() +
                mEtInputBox5.getText().toString().trim() +
                mEtInputBox6.getText().toString().trim() +
                mEtInputBox7.getText().toString().trim() +
                mEtInputBox8.getText().toString().trim();
        Logger.i(number);
        return number;
    }

    */
/**
     * 将八个textview全部清空
     *//*

    public void clearInputBox() {
        for (int i = 0; i < mEditTextViews.length; i++) {
            mEditTextViews[i].setText("");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!mIsFirst) {
            PreviewDetailActivity.setPictureListener(myBitmapList -> {
                if (mMyBitmaps_recycler_all == null) {
                    mMyBitmaps_recycler_all = new ArrayList<>();
                } else {
                    mMyBitmaps_recycler_all.clear();
                }
                mMyBitmaps_recycler_all.addAll(myBitmapList);
            });

            addElsePicture();
            mPhotoAdapter.updateListView(mMyBitmaps_recycler_all);
            mIsFirst = true;

            inflateSelected();

            String number = sSupportDetail.getNumber();
            String goodsFromDraft = sSupportDetail.getGoods();
            String draft_weight = sSupportDetail.getDetail_weight();
            String draft_free = sSupportDetail.getDetail_free();
            String draft_export = sSupportDetail.getDetail_export();
            String draft_carType = sSupportDetail.getDetail_carType();
            checkingBlack(number);
            mGoodsText = goodsFromDraft;
            updateTextListView(getGoodsTextList(mGoodsText));
            mTvChangeGoodsDetail.setText(goodsFromDraft);

            mDetailTextWeight.setText(draft_weight);
            mDetailTextFree.setText(draft_free);
            mDetailTextExport.setText(draft_export);
            mDetailTextCar.setText(draft_carType);

        }
    }

    */
/**
     * 检查返回或者得到的车牌号是否被加入黑名单
     *//*

    private void checkingBlack(String carNumber) {
        if (carNumber.length() >= 7) {
            List<SupportBlack> blackList = DataSupport.findAll(SupportBlack.class);
            if (blackList != null && blackList.size() != 0) {
                boolean isBlack = isBlack(carNumber, blackList);
                if (isBlack) {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
                    r.play();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("该车牌为黑名单车牌");
                    builder.setPositiveButton("了解", (dialog, which) -> {
                        dialog.dismiss();
                    });
                    builder.setCancelable(false);
                    builder.show();

                    setTextColor(Color.RED);
                } else {
                    if (mThemeTag == 1) {
                        setTextColor(Color.DKGRAY);
                    } else {
                        setTextColor(Color.WHITE);
                    }
                }
            } else {
                if (mThemeTag == 1) {
                    setTextColor(Color.DKGRAY);
                } else {
                    setTextColor(Color.WHITE);
                }
            }
        }
        inflateNumber(carNumber);
    }

    private void setTextColor(int color) {
        for (int i = 0; i < mEditTextViews.length; i++) {
            mEditTextViews[i].setTextColor(color);
        }
    }

    public static void setSelectedListListener(SelectedListListener listener) {
        listener.Selected(mSelectList_photo);
    }

    public interface SelectedListListener {
        void Selected(List<LocalMedia> medias_photo);
    }


    //加载本地的图片等数据
    private void initGoodsData() {
        getGoodsTextList(mGoodsText);
        List<SupportLocalGoods> all = DataSupport.findAll(SupportLocalGoods.class);
        mTypeList = DataSupport.findFirst(SupportGoods.class).getGoodsTypeList();

        String typeCount = ACache.get(getActivity()).getAsString(ACache.TYPR_COUNT);
//        String allCount = ACache.get(getActivity()).getAsString(ACache.MARK_TIME);
//        Logger.i(typeCount + "---" + allCount);
        if (mGoodsAllList != null && mGoodsAllList.size() != 0) {
            mGoodsAllList.clear();
        }
        String markTime = DataSupport.findFirst(SupportGoods.class).getMarkTime();
        String mark = (String) SPUtils.get(getActivity(), SPUtils.MARK_TIME, "");
        if (markTime != null && !"".equals(markTime) && markTime.equals(mark)) {
            try {
                Logger.i("直接走的缓存");
                Logger.i(Integer.parseInt(typeCount) + "");
                for (int i = 0; i < Integer.parseInt(typeCount); i++) {
                    ArrayList<SerializableGoods> mAsObject_Type = (ArrayList<SerializableGoods>) ACache
                            .get(getActivity()).getAsObject(ACache.Type_ACACHE + i);
                    All_GoodsList.add(mAsObject_Type);
                }
                ArrayList<SerializableGoods> mAsObject_All = (ArrayList<SerializableGoods>) ACache
                        .get(getActivity()).getAsObject(ACache.ALL_ACACHE);
                mGoodsAllList.addAll(mAsObject_All);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                refreshGoodData(markTime);
            }
        } else {
            //记录标记时间

            refreshGoodData(markTime);

        }


        mCurrentGoodsList = All_GoodsList.get(0);
    }

    private void refreshGoodData(String markTime) {
        Logger.i("重新加载数据未走缓存");
        SPUtils.putAndApply(getActivity(), SPUtils.MARK_TIME, markTime);

        for (int i = 0; i < mTypeList.size(); i++) {
            ArrayList<String> goodPinyinList = new ArrayList<>();
            ArrayList<String> goodNameList = new ArrayList<>();
            ArrayList<Integer> goodSortIdList = new ArrayList<>();
            ArrayList<String> goodIamgeUrlList = new ArrayList<>();

            List<SupportLocalGoods> goodsList = DataSupport.where("type = ?", mTypeList.get(i)).find(SupportLocalGoods.class);
            for (int j = 0; j < goodsList.size(); j++) {
                goodPinyinList.add(goodsList.get(j).getPinyin());
                goodNameList.add(goodsList.get(j).getName());
                goodSortIdList.add(goodsList.get(j).getSortId());
                goodIamgeUrlList.add(goodsList.get(j).getImageUrl());
            }
            All_GoodsList.add(getGoodsTypeList(goodIamgeUrlList, goodPinyinList, goodNameList, goodSortIdList));
        }
        for (int i = 0; i < All_GoodsList.size(); i++) {
            mGoodsAllList.addAll(All_GoodsList.get(i));
        }
    }

    private ArrayList<SerializableGoods> getGoodsTypeList(ArrayList<String> goods_urls, ArrayList<String> goods_pinyins,
                                                          ArrayList<String> goods_names, ArrayList<Integer> goodSortIdList) {
        ArrayList<SerializableGoods> goodsTypeList = new ArrayList<>();

        for (int i = 0; i < goods_pinyins.size(); i++) {

            String scientific_name = goods_names.get(i);

            SerializableGoods goods = new SerializableGoods();

            String sortLetters = Cn2Spell.getPinYinFirstLetter(scientific_name);
            String sortKey = PingYinUtil.format(scientific_name);
            String headChar = Cn2Spell.getPinYinHeadChar(sortKey);
            String pinYin = Cn2Spell.getPinYin(sortKey);

            goods.setScientific_name(scientific_name);
            goods.setBitmapUrl(goods_urls.get(i));
            goods.setSortLetters(sortLetters);
            goods.setWholeSpell(pinYin);
            goods.setSimpleSpell(headChar);
            goods.setSortId(goodSortIdList.get(i));

            goodsTypeList.add(goods);
        }
        GoodsComparator comparator = new GoodsComparator();
        Collections.sort(goodsTypeList, comparator);
        return goodsTypeList;
    }

    private void initGoodRecyclerView() {

        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
        mGoodImageRecyclerView.setLayoutManager(manager);

        //货物text的recyclerView
        mGoodsAdapter = new SureGoodsAdapter(getContext(), mCurrentGoodsList, (serializableGoods, position) -> {

            String scientificName = serializableGoods.getScientific_name();
            if (mGoodsTextList.contains(scientificName)) {
                ToastUtils.singleToast("已经选择了此货物");
            } else {
                mGoodsTextList.add(scientificName);
            }
            mGoodEditText.setText("");
            mLlGoodsHidePart.setVisibility(View.VISIBLE);
            InputMethodManager imm = (InputMethodManager) DetailsFragment1.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mGoodEditText.getWindowToken(), 0); //强制隐藏键盘
            if (mTypeList.size() < 6) {
//                    closeQiTa(false);
                mButtonList.get(mTypeList.size()).setTextColor(DetailsFragment1.this.getActivity().getResources().getColor(R.color.licence_text_normal_day));
                mButtonList.get(mTypeList.size()).setBackground(DetailsFragment1.this.getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
            }
            if (mLastType < 6) {
                mButtonList.get(mLastType).setTextColor(DetailsFragment1.this.getActivity().getResources().getColor(R.color.licence_selected_text_day));
                mButtonList.get(mLastType).setBackground(DetailsFragment1.this.getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
            } else {
                mGoodsMore.setText(mTypeList.get(mLastType) + " >>");
                mGoodsMore.setTextColor(DetailsFragment1.this.getActivity().getResources().getColor(R.color.day_color_white));
                mGoodsMore.setBackground(DetailsFragment1.this.getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
            }
            DetailsFragment1.this.updateTextListView(mGoodsTextList);
        });
        mGoodImageRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext(), 3));
        mGoodImageRecyclerView.setAdapter(mGoodsAdapter);
    }
}
*/
