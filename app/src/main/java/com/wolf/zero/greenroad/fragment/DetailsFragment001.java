package com.wolf.zero.greenroad.fragment;//package com.android.htc.greenroad.fragment;
//
//import android.Manifest;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.res.AssetManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.media.Ringtone;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.TranslateAnimation;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import R;
//import SpinnerPopupWindow;
//import PreviewDetailActivity;
//import ShowActivity;
//import com.android.htc.greenroad.activity.license.MemoryCameraActivity;
//import com.android.htc.greenroad.activity.license.PermissionActivity;
//import DetailsRecyclerAdapter;
//import DividerGridItemDecoration;
//import GoodsTextAdapter;
//import RecycleViewDivider;
//import SpinnerAdapter;
//import SureGoodsAdapter;
//import DetailInfoBean;
//import com.android.htc.greenroad.httpresultbean.GoodsBean;
//import PathTitleBean;
//import SerializableGoods;
//import HttpUtilsApi;
//import TextChangeWatcher;
//import SupportBlack;
//import SupportDetail;
//import SupportDraftOrSubmit;
//import SupportGoods;
//import SupportLocalGoods;
//import SupportMedia;
//import TeamItem;
//import GlobalManager;
//import ACache;
//import AnimationUtil;
//import BitmapUtil;
//import Cn2Spell;
//import GoodsComparator;
//import LicenseKeyboardUtil;
//import PingYinUtil;
//import SPUtils;
//import ToastUtils;
//import MylinearLayout;
//import NoScrollViewPager;
//import com.kernal.plateid.AuthService;
//import com.kernal.plateid.Devcode;
//import com.kernal.plateid.PlateAuthParameter;
//import com.luck.picture.lib.PictureSelector;
//import com.luck.picture.lib.compress.Luban;
//import com.luck.picture.lib.config.PictureConfig;
//import com.luck.picture.lib.config.PictureMimeType;
//import com.luck.picture.lib.entity.LocalMedia;
//import com.orhanobut.logger.Logger;
//
//import org.litepal.crud.DataSupport;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//import utills.CheckPermission;
//
//
//public class DetailsFragment001 extends Fragment implements TextChangeWatcher.AfterTextListener {
//    ArrayList<ArrayList<SerializableGoods>> All_GoodsList = new ArrayList<ArrayList<SerializableGoods>>();
//    private int mCurrenntType = 0; //标记当前指向货物的类型
//    private static final int CHOOSE_CAR_FROM_PICTURE = 1 * 911;
//    private static boolean tag;
//    Unbinder unbinder;
//    @BindView(R.id.recycler_view_shoot_photo)
//    RecyclerView mRecyclerViewShootPhoto;
//    private static TextView mTvChangeGoodsDetail;
//
//    @BindView(R.id.activity_recycler_left)
//    ImageView mRecyclerLeft;
//    @BindView(R.id.activity_recycler_right)
//    ImageView mRecyclerRight;
//
//    @BindView(R.id.goods_rl_goods_all)
//    RelativeLayout mGoodsRlGoodsAll;
//    @BindView(R.id.btn_license_identify)
//    ImageView mBtnLicenseIdentify;
//    @BindView(R.id.btn_open_goods)
//    ImageView mBtnOpenGoods;
//    @BindView(R.id.license_btn_license_sure)
//    Button mLicenseBtnSure;
//    @BindView(R.id.license_rl_license_all)
//    RelativeLayout mLicenseRlLicenseAll;
//
//    private static TextView mEtInputBox1;
//    private static TextView mEtInputBox2;
//    private static TextView mEtInputBox3;
//    private static TextView mEtInputBox4;
//    private static TextView mEtInputBox5;
//    private static TextView mEtInputBox6;
//    private static TextView mEtInputBox7;
//    private static TextView mEtInputBox8;
//    @BindView(R.id.ll_goods_hide_part)
//    LinearLayout mLlGoodsHidePart;
//    @BindView(R.id.detail_below_ll_license)
//    LinearLayout mDetailBelowLlLicense;
//    @BindView(R.id.detail_fragment_all)
//    RelativeLayout mDetailFragmentAll;
//    @BindView(R.id.btn_type_01)
//    Button mBtnType01;
//    @BindView(R.id.btn_type_02)
//    Button mBtnType02;
//    @BindView(R.id.btn_type_03)
//    Button mBtnType03;
//    @BindView(R.id.btn_type_04)
//    Button mBtnType04;
//    @BindView(R.id.btn_type_05)
//    Button mBtnType05;
//    @BindView(R.id.btn_type_06)
//    Button mBtnType06;
//    //    @BindView(R.id.goods_More)
////    RadioButton mGoodsMore;
//    private TextView[] mEditTextViews;
//
//    private DetailsRecyclerAdapter mPhotoAdapter;
//    private static ArrayList<MyBitmap> mMyBitmaps_recycler_all;
//    private Bitmap mBitmap_add;
//
//    private LinearLayoutManager mLayoutManager;
//
//    public static String sEnterType;
//    private static SupportDetail sSupportDetail;
//    private static int sLite_ID;
//
//    private static List<LocalMedia> mSelectList_photo;
//    private int mThemeTag;
//    private LicenseKeyboardUtil keyboardUtil;
//    private View mDetailView;
//    private boolean mIsFirst;
//    private static EditText mDetailTextWeight;
//    private static EditText mDetailTextFree;
//    private static EditText mDetailTextExport;
//    private ArrayList<String> mLaneList;
//    private SpinnerPopupWindow mPopupWindow;
//    private RadioGroup mRadioGroup1;
//    private RadioGroup mRadioGroup2;
//    private NoScrollViewPager mShowViewPager;
//    private ArrayList<String> mEdit_texts;
//    private String mUsername;
//    private Button mGoodsMore;
//    private ArrayList<Button> mButtonList;
//
//    public DetailsFragment001() {
//        // Required empty public constructor
//    }
//
//    String sn;
//    String authfile;
//
//    private int ReturnAuthority = -1;
//
//    public AuthService.MyBinder authBinder;
//
//    static final String[] PERMISSION = new String[]{Manifest.permission.CAMERA,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
//            Manifest.permission.READ_EXTERNAL_STORAGE, // 读取权限
//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.VIBRATE,
//            Manifest.permission.INTERNET,
////		permission.FLASHLIGHT
//    };
//    //授权验证服务绑定后的操作与start识别服务
//    public ServiceConnection authConn = new ServiceConnection() {
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            authBinder = null;
//        }
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            authBinder = (AuthService.MyBinder) service;
//            Toast.makeText(getActivity(), R.string.auth_check_service_bind_success, Toast.LENGTH_SHORT).show();
//            try {
//                PlateAuthParameter pap = new PlateAuthParameter();
//                pap.sn = sn;
//                pap.authFile = authfile;
//                pap.devCode = Devcode.DEVCODE;
//                ReturnAuthority = authBinder.getAuth(pap);
//                if (ReturnAuthority != 0) {
//                    Toast.makeText(getActivity(), getString(R.string.license_verification_failed) + ":" + ReturnAuthority, Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getActivity(), R.string.license_verification_success, Toast.LENGTH_LONG).show();
//                }
//            } catch (Exception e) {
//                Toast.makeText(getActivity(), R.string.failed_check_failure, Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            } finally {
//                if (authBinder != null) {
//                    getActivity().unbindService(authConn);
//                }
//            }
//        }
//    };
//
//    public static DetailsFragment001 newInstance(String enterType) {
//        DetailsFragment001 fragment = new DetailsFragment001();
//        sEnterType = enterType;
//        return fragment;
//    }
//
//    public static DetailsFragment001 newInstance(String enterType, SupportDetail supportDetail,
//                                                 int lite_ID) {
//        DetailsFragment001 fragment = new DetailsFragment001();
//        sEnterType = enterType;
//        sSupportDetail = supportDetail;
//        sLite_ID = lite_ID;
//
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        tag = true;
//        //mIsFirst为true时,表示从主界面进来的
//
//        mIsFirst = true;
//        if (GlobalManager.TYPE_MAIN_ENTER_SHOW.equals(sEnterType)) {
//            mIsFirst = true;
//        } else {
//            mIsFirst = false;
//        }
//        mThemeTag = (int) SPUtils.get(getContext(), SPUtils.KEY_THEME_TAG, 1);
//        getGoodACache();
//    }
//
//
//    private static EditText mGoodEditText;
//
//    @BindView(R.id.recycler_view_goods_sure)
//    RecyclerView mGoodImageRecyclerView;
//    @BindView(R.id.edit_text_qita)
//    EditText mGoodEditTextQita;
//    @BindView(R.id.btn_sure_qita)
//    Button mGoodBtnSureQita;
//    @BindView(R.id.rl_edit_qita)
//    RelativeLayout mGoodRlEditQita;
//
//    private SureGoodsAdapter mGoodsAdapter;
//
//    private String mCar_goods;
//
//    private String goodsText;
//    private static String mGoodsText;
//    private AssetManager mAssetManager;
//    private String current_kind;
//
//    private static final String KIND_SHUCAI = "kind_shucai";
//    private static final String KIND_SHUIGUO = "kind_shuiguo";
//    private static final String KIND_SHUICHANPIN = "kind_shuichanpin";
//    private static final String KIND_CHUQIN = "kind_xuqin";
//    private static final String KIND_ROUDANNAI = "kind_roudannai";
//    private static final String KIND_ZALIANG = "kind_zaliang";
//
//    private static final String GOODS_DIR_SHUCAI = "goods_shucai";
//    private static final String GOODS_DIR_SHUIGUO = "goods_shuiguo";
//    private static final String GOODS_DIR_SHUICHANPIN = "goods_shuichanpin";
//    private static final String GOODS_DIR_CHUQIN = "goods_chuqin";
//    private static final String GOODS_DIR_ROUDANNAI = "goods_roudannai";
//    private static final String GOODS_DIR_ZALIANG = "goods_zaliang";
//
//    private ArrayList<SerializableGoods> mAsObject_all;
//    private ArrayList<SerializableGoods> mAsObject_shucai;
//    private ArrayList<SerializableGoods> mAsObject_shuiguo;
//    private ArrayList<SerializableGoods> mAsObject_shuichanpin;
//    private ArrayList<SerializableGoods> mAsObject_chuqin;
//    private ArrayList<SerializableGoods> mAsObject_roudannai;
//    private ArrayList<SerializableGoods> mAsObject_zaliang;
//
//    private String[] mGoodsName_shucais;
//    private String[] mGoodsName_shuiguos;
//    private String[] mGoodsName_shuichanpins;
//    private String[] mGoodsName_chuqins;
//    private String[] mGoodsName_roudannais;
//    private String[] mGoodsName_zaliangs;
//
//    private String[] mGoodsAsset_shucai;
//    private String[] mGoodsAsset_shuiguo;
//    private String[] mGoodsAsset_shuichanpin;
//    private String[] mGoodsAsset_chuqin;
//    private String[] mGoodsAsset_roudannai;
//    private String[] mGoodsAsset_zaliang;
//
//    private ArrayList<SerializableGoods> mGoodsAllList;
//    private ArrayList<SerializableGoods> mGoodsShuCais;
//    private ArrayList<SerializableGoods> mGoodsShuiGuos;
//    private ArrayList<SerializableGoods> mGoodsShuiChanPins;
//    private ArrayList<SerializableGoods> mGoodsChuQins;
//    private ArrayList<SerializableGoods> mGoodsRouDanNais;
//    private ArrayList<SerializableGoods> mGoodsZaLiangs;
//
//    /**
//     * 拿到缓存的货物照片
//     */
//    private void getGoodACache() {
//        mGoodsName_shucais = getResources().getStringArray(R.array.science_array_shucai);
//        mGoodsName_shuiguos = getResources().getStringArray(R.array.science_array_shuiguo);
//        mGoodsName_shuichanpins = getResources().getStringArray(R.array.science_array_shuichanpin);
//        mGoodsName_chuqins = getResources().getStringArray(R.array.science_array_chuqin);
//        mGoodsName_roudannais = getResources().getStringArray(R.array.science_array_roudannai);
//        mGoodsName_zaliangs = getResources().getStringArray(R.array.science_array_zaliang);
//
//
//        mAsObject_all = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject("总缓存");
//
//        mAsObject_shucai = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject(ACache.GOODSACACHE_SHUCAI);
//
//        mAsObject_shuiguo = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject(ACache.GOODSACACHE_SHUIGUO);
//
//        mAsObject_shuichanpin = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject(ACache.GOODSACACHE_SHUICHANPIN);
//
//        mAsObject_chuqin = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject(ACache.GOODSACACHE_CHUQIN);
//
//        mAsObject_roudannai = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject(ACache.GOODSACACHE_ROUDANNAI);
//
//
//        mAsObject_zaliang = (ArrayList<SerializableGoods>) ACache
//                .get(getActivity()).getAsObject(ACache.GOODSACACHE_ZALIANG);
//
//
//        mGoodsAllList = new ArrayList<>();
//        mGoodsShuCais = new ArrayList<>();
//        mGoodsShuiGuos = new ArrayList<>();
//        mGoodsShuiChanPins = new ArrayList<>();
//        mGoodsChuQins = new ArrayList<>();
//        mGoodsRouDanNais = new ArrayList<>();
//        mGoodsZaLiangs = new ArrayList<>();
//
//        mAssetManager = getContext().getAssets();
//        try {
//            mGoodsAsset_shucai = mAssetManager.list(GOODS_DIR_SHUCAI);
//            mGoodsAsset_shuiguo = mAssetManager.list(GOODS_DIR_SHUIGUO);
//            mGoodsAsset_shuichanpin = mAssetManager.list(GOODS_DIR_SHUICHANPIN);
//            mGoodsAsset_chuqin = mAssetManager.list(GOODS_DIR_CHUQIN);
//            mGoodsAsset_roudannai = mAssetManager.list(GOODS_DIR_ROUDANNAI);
//            mGoodsAsset_zaliang = mAssetManager.list(GOODS_DIR_ZALIANG);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Logger.i(e.getMessage());
//            return;
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        mDetailView = inflater.inflate(R.layout.fragment_details, container, false);
//        unbinder = ButterKnife.bind(this, mDetailView);
//        mUsername = (String) SPUtils.get(getActivity(), GlobalManager.USERNAME, "qqqq");
//        initView(mDetailView);
//
////        initRadioColor();
//        initPhotoRecyclerView();
//        initGoodsAll(mDetailView);
//        return mDetailView;
//    }
//
//    private RecyclerView mGoodTextRecycler;
//    private static ArrayList<String> mGoodsTextList;
//    private GoodsTextAdapter mGoodsTextAdapter;
//    private static StringBuilder sGoodsBuilder;
//    private LinearLayoutManager mGoodLayoutManager;
//    private ArrayList<SerializableGoods> mCurrentGoodsList;
//
//    private void initGoodsAll(View view) {
//        if (mGoodsTextList == null) {
//            mGoodsTextList = new ArrayList<>();
//        } else {
//            mGoodsTextList.clear();
//        }
//        initGoodsData();
//        initGoods(view);
//
////        initGoodRecyclerView();
//        initGoodsTextRecycler();
//    }
//
//
//    /**
//     * 添加的货物信息
//     */
//    private void initGoodsTextRecycler() {
//        mGoodLayoutManager = new LinearLayoutManager(getContext(),
//                LinearLayoutManager.HORIZONTAL, false);
//        mGoodTextRecycler.setLayoutManager(mGoodLayoutManager);
//
//        mGoodsTextAdapter = new GoodsTextAdapter(getContext(), mGoodsTextList, pos -> {
//            mGoodsTextList.remove(pos);
//
//            mGoodsTextAdapter.notifyDataSetChanged();
//        });
//        mGoodTextRecycler.setAdapter(mGoodsTextAdapter);
//    }
//
//    private void initGoodRecyclerView() {
//
//        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
//        mGoodImageRecyclerView.setLayoutManager(manager);
//
//        //货物text的recyclerView
//        mGoodsAdapter = new SureGoodsAdapter(getContext(), mCurrentGoodsList, new SureGoodsAdapter.onItemClick() {
//            @Override
//            public void itemClick(SerializableGoods serializableGoods, int position) {
//
//                String scientificName = serializableGoods.getScientific_name();
//                if (mGoodsTextList.contains(scientificName)) {
//                    ToastUtils.singleToast("已经选择了此货物");
//                } else {
//                    mGoodsTextList.add(scientificName);
//                }
//                mGoodEditText.setText("");
//                mLlGoodsHidePart.setVisibility(View.VISIBLE);
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(mGoodEditText.getWindowToken(), 0); //强制隐藏键盘
//
//                updateTextListView(mGoodsTextList);
//            }
//        });
//        mGoodImageRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext(), 3));
//        mGoodImageRecyclerView.setAdapter(mGoodsAdapter);
//    }
//
//    private void updateTextListView(ArrayList<String> mTextList) {
//        mGoodsTextAdapter.updateListView(mTextList);
//        if (mTextList.size() > 3) {
//            mGoodTextRecycler.scrollToPosition(mTextList.size() - 1);
//        }
//    }
//
//
//    private void initGoodView(View view) {
//        mGoodEditText = (EditText) view.findViewById(R.id.goods_edit_text);
//        mGoodsMore = (Button) view.findViewById(R.id.goods_More);
//        mButtonList = new ArrayList<>();
//        mButtonList.add(mBtnType01);
//        mButtonList.add(mBtnType02);
//        mButtonList.add(mBtnType03);
//        mButtonList.add(mBtnType04);
//        mButtonList.add(mBtnType05);
//        mButtonList.add(mBtnType06);
//        mButtonList.add(mGoodsMore);
//
//        mButtonList.get(0).setTextColor(getActivity().getResources().getColor(R.color.licence_selected_text_day));
//        mButtonList.get(0).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//
////        Button[] buttons = new Button[]{mBtnType01, mBtnType02, mBtnType03, mBtnType04, mBtnType05, mBtnType06};
//
////        for (int i = 0; i < 6; i++) {
////            buttons[i].setOnClickListener(view1 -> {
////                for (int j = 0; j < 6; j++) {
////                    buttons[j].setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
////                }
////                view1.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
////
////            });
////        }
//
//        List<String> typeList = DataSupport.findFirst(SupportGoods.class).getGoodTypeList();
//        typeList.add("其他");
////        final int[] currentType = new int[1];
//        if (typeList.size() > 7) {
//            for (int i = 0; i < 7; i++) {
//                if (i < 6) {
//                    mButtonList.get(i).setText(typeList.get(i));
//                    int finalI = i;
//                    mButtonList.get(i).setOnClickListener(view1 -> {
//                        closeQiTa(false);
//                        mGoodsMore.setText("更多 >>");
//                        mCurrenntType = finalI;
//                        mCurrentGoodsList = All_GoodsList.get(mCurrenntType);
//                        mGoodsAdapter.updateListView(mCurrentGoodsList);
//                        Logger.i("第" + mCurrenntType + "种类型" + typeList.get(mCurrenntType));
//                        for (int j = 0; j < 7; j++) {
//                            mButtonList.get(j).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
//                            mButtonList.get(j).setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
//                        }
//                        view1.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//                        mButtonList.get(finalI).setTextColor(getActivity().getResources().getColor(R.color.day_color_white));
//                    });
//
//                } else {
//                    mButtonList.get(i).setText("更多 >>");
//                    mButtonList.get(i).setOnClickListener(view1 -> {
//                        Logger.i("更多");
//                        openMore(typeList);
//                    });
//
//                }
//            }
//        } else {
//            for (int i = 0; i < 7; i++) {
//                if (i < typeList.size()) {
//                    int finalI = i;
//                    mButtonList.get(i).setText(typeList.get(i));
//                    mButtonList.get(i).setOnClickListener(view1 -> {
//                        mCurrenntType = finalI;
//                        Logger.i("第" + mCurrenntType + "种类型" + typeList.get(mCurrenntType));
//
//                        for (int j = 0; j < 7; j++) {
//                            mButtonList.get(j).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
//                            mButtonList.get(j).setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
//                        }
//                        view1.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//                        mButtonList.get(finalI).setTextColor(getActivity().getResources().getColor(R.color.day_color_white));
//
//                    });
//                } else {
//                    mButtonList.get(i).setVisibility(View.INVISIBLE);
//                }
//            }
//        }
//
//
////        mGoodEditText.setOnClickListener(view1 -> {
////        mLlGoodsHidePart.setVisibility(View.GONE);
////        });
//        //点击更多按钮时
////        mGoodsMore.setOnClickListener(view1 -> {
////            openMore();
////        });
//
//        mGoodEditText.setText("");
//        mGoodEditText.addTextChangedListener(new TextChangeWatcher(this));
//        mGoodTextRecycler = (RecyclerView) view.findViewById(R.id.goods_text_recycler);
//
////        mRadioGroup1 = (RadioGroup) view.findViewById(R.id.radio_group_goods_1);
////        mRadioGroup2 = (RadioGroup) view.findViewById(R.id.radio_group_goods_2);
//
////        mRadioGroup1.setOnCheckedChangeListener(this);
////        mRadioGroup2.setOnCheckedChangeListener(this);
//
////        RadioButton radioButton = (RadioButton) view.findViewById(R.id.goods_shucai);
////        radioButton.setChecked(true);
//
//
//    }
//
//    private void initGoodsData() {
//
//        getGoodsTextList(mGoodsText);
//
//
////        initGoodsJudet(mAsObject_shucai, mGoodsShuCais, mGoodsAsset_shucai, mGoodsName_shucais, GOODS_DIR_SHUCAI);
////        initGoodsJudet(mAsObject_shuiguo, mGoodsShuiGuos, mGoodsAsset_shuiguo, mGoodsName_shuiguos, GOODS_DIR_SHUIGUO);
////        initGoodsJudet(mAsObject_shuichanpin, mGoodsShuiChanPins, mGoodsAsset_shuichanpin, mGoodsName_shuichanpins, GOODS_DIR_SHUICHANPIN);
////        initGoodsJudet(mAsObject_chuqin, mGoodsChuQins, mGoodsAsset_chuqin, mGoodsName_chuqins, GOODS_DIR_CHUQIN);
////        initGoodsJudet(mAsObject_roudannai, mGoodsRouDanNais, mGoodsAsset_roudannai, mGoodsName_roudannais, GOODS_DIR_ROUDANNAI);
////        initGoodsJudet(mAsObject_zaliang, mGoodsZaLiangs, mGoodsAsset_zaliang, mGoodsName_zaliangs, GOODS_DIR_ZALIANG);
//
////        if (mGoodsAllList != null && mGoodsAllList.size() != 0) {
////            mGoodsAllList.clear();
////        }
////        int all = mGoodsName_shucais.length + mGoodsName_shuiguos.length +
////                mGoodsName_shuichanpins.length + mGoodsName_chuqins.length +
////                mGoodsName_roudannais.length + mGoodsName_zaliangs.length;
////        if (mAsObject_all != null && mAsObject_all.size() == (all)) {
////            mGoodsAllList.addAll(mAsObject_all);
////        } else {
////            mGoodsAllList.addAll(mGoodsShuCais);
////            mGoodsAllList.addAll(mGoodsShuiGuos);
////            mGoodsAllList.addAll(mGoodsShuiChanPins);
////            mGoodsAllList.addAll(mGoodsChuQins);
////            mGoodsAllList.addAll(mGoodsRouDanNais);
////            mGoodsAllList.addAll(mGoodsZaLiangs);
////        }
//    }
//
//    private ArrayList<String> getGoodsTextList(String goodTexts) {
//        if (mGoodsTextList == null) {
//            mGoodsTextList = new ArrayList<>();
//        } else {
//            mGoodsTextList.clear();
//        }
//        if (goodTexts != null && goodTexts.length() != 0) {
//
//            String[] goodsName = goodTexts.split(";");
//
//            for (int i = 0; i < goodsName.length; i++) {
//                mGoodsTextList.add(goodsName[i]);
//            }
//        }
//        return mGoodsTextList;
//    }
//
//    private void initGoodsJudet(ArrayList<SerializableGoods> asObject_kind,
//                                ArrayList<SerializableGoods> mGoodsList_kind,
//                                String[] goodsAsset_kind, String[] goodsName_kind,
//                                String goodsDirkind) {
//        if (asObject_kind != null && asObject_kind.size() != 0) {
//            if (asObject_kind.size() == goodsName_kind.length) {
//                if (mGoodsList_kind != null && mGoodsList_kind.size() != 0) {
//                    mGoodsList_kind.clear();
//                }
//                Logger.i("直接走的缓存");
//                mGoodsList_kind.addAll(asObject_kind);
//            } else {
//                Logger.i("goods未加载完毕未缓存");
//                getGoodsData(mGoodsList_kind, goodsAsset_kind, goodsName_kind, goodsDirkind);
//            }
//        } else {
//            Logger.i("goods直接从头加载数据");
//            getGoodsData(mGoodsList_kind, goodsAsset_kind, goodsName_kind, goodsDirkind);
//        }
//    }
//
//    private ArrayList<SerializableGoods> getGoodsData(ArrayList<SerializableGoods> goodsList,
//                                                      String[] goods_assets, String[] goods_names, String dir) {
//        if (goodsList != null && goodsList.size() != 0) {
//            goodsList.clear();
//        }
//        ArrayList<String> goodsImage_url = new ArrayList<>();
//        ArrayList<String> goods_name = new ArrayList<>();
//        for (int i = 0; i < goods_assets.length; i++) {
//            goodsImage_url.add(goods_assets[i]);
//        }
//        for (int i = 0; i < goods_names.length; i++) {
//            goods_name.add(goods_names[i]);
//        }
//
//        Collections.sort(goodsImage_url);
//        Collections.sort(goods_name);
//        for (int i = 0; i < goods_name.size(); i++) {
//
//            String scientific_name = goods_name.get(i).substring(4);
//            String bitmap_url = dir + "/" + goodsImage_url.get(i);
//
//            SerializableGoods goods = new SerializableGoods();
//
//            String sortLetters = Cn2Spell.getPinYinFirstLetter(scientific_name);
//            String sortKey = PingYinUtil.format(scientific_name);
//            String headChar = Cn2Spell.getPinYinHeadChar(sortKey);
//            String pinYin = Cn2Spell.getPinYin(sortKey);
//
//
//            goods.setScientific_name(scientific_name);
//            goods.setBitmapUrl(bitmap_url);
//            goods.setSortLetters(sortLetters);
//            goods.setWholeSpell(pinYin);
//            goods.setSimpleSpell(headChar);
//
//            goodsList.add(goods);
//        }
//        return goodsList;
//    }
//
//
//    private void notifyScanLicenseChange(String carNumber) {
//        ScanFragment.notifyNumberChange(carNumber);
//    }
//
//    private void notifyScanWeightChange(String weight) {
//        ScanFragment.notifyWeightChange(weight);
//    }
//
//    private void notifyScanFreeChange(String free) {
//        ScanFragment.notifyFreeChange(free);
//    }
//
//    private void notifyScanExportChange(String export) {
//        ScanFragment.notifyExportChange(export);
//    }
//
//    private boolean isBlack(String carNumber, List<SupportBlack> blackList) {
//
//        for (int i = 0; i < blackList.size(); i++) {
//            if (carNumber.equals(blackList.get(i).getLicense())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 加载选中标记的图片信息,提交以及进入photoFragment中都需要
//     * 因为从草稿详情页进入编辑修改若没有进入photoFragment直接再存储会丢失selected的数据
//     * 所以提前加载
//     */
//    private void inflateSelected() {
//        List<SupportDraftOrSubmit> supportDraftOrSubmits = DataSupport.where("lite_ID = ? and username = ?", String.valueOf(sLite_ID), mUsername).find(SupportDraftOrSubmit.class);
//        SupportMedia supportMedia = supportDraftOrSubmits.get(0).getSupportMedia();
//
//
//        if (supportMedia != null && supportMedia.getPaths() != null
//                && supportMedia.getPaths().size() != 0) {
//
//            if (mSelectList_photo == null) {
//                mSelectList_photo = new ArrayList<>();
//            } else {
//                mSelectList_photo.clear();
//            }
//            for (int i = 0; i < supportMedia.getPaths().size(); i++) {
//                LocalMedia localMedia = initSelected(supportMedia, i);
//                if (localMedia != null) {
//                    mSelectList_photo.add(localMedia);
//                }
//            }
//
//        }
//    }
//
//    private LocalMedia initSelected(SupportMedia supportMedia, int i) {
//        if (supportMedia != null) {
//            String path = supportMedia.getPaths().get(i);
//            String pictureType = supportMedia.getPictureTypes().get(i);
//            long duration = supportMedia.getDurations().get(i);
//            int mimeType = supportMedia.getMimeTypes().get(i);
//            int height = supportMedia.getHeights().get(i);
//            int width = supportMedia.getWidths().get(i);
//            int num = supportMedia.getNums().get(i);
//            int position = supportMedia.getPositions().get(i);
//            LocalMedia localMedia = new LocalMedia(path, duration, mimeType, pictureType, width, height);
//            localMedia.setNum(num);
//            localMedia.setPosition(position);
//            localMedia.setChecked(false);
//            localMedia.setCompressed(false);
//            localMedia.setCut(false);
//            if (path == null || "".equals(path)) {
//                return null;
//            } else {
//                return localMedia;
//            }
//        }
//        return null;
//    }
//
//    private void initPhotoRecyclerView() {
//        mBitmap_add = BitmapFactory.decodeResource(getResources(), R.drawable.image_photo_add);
//        if (mMyBitmaps_recycler_all == null) {
//            mMyBitmaps_recycler_all = new ArrayList<>();
//        } else {
//            mMyBitmaps_recycler_all.clear();
//        }
//
//        for (int i = 0; i < 3; i++) {
//            String title = "照片" + (i + 1);
//            MyBitmap myBitmap = new MyBitmap(mBitmap_add, title);
//            mMyBitmaps_recycler_all.add(myBitmap);
//        }
//        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        mRecyclerViewShootPhoto.setLayoutManager(mLayoutManager);
//        mPhotoAdapter = new DetailsRecyclerAdapter(getContext(), mMyBitmaps_recycler_all, () -> {
////            enterSureActivity(GlobalManager.ENTERTYPE_PHOTO);
////            ToastUtils.singleToast("拍照");
//            openPicture(8, 1, CHOOSE_CAR_FROM_PICTURE);
//        });
//        mRecyclerViewShootPhoto.setAdapter(mPhotoAdapter);
//    }
//
//    private void openPicture(int maxNum, int minNum, int result_type) {
//        // 进入相册 以下是例子：不需要的api可以不写
//        PictureSelector.create(DetailsFragment001.this)
//                .openGallery(PictureMimeType.ofImage())
//                .theme(R.style.picture_QQ_style)
//                .maxSelectNum(maxNum)
//                .minSelectNum(minNum)
//                .imageSpanCount(3)// 每行显示个数
//                .selectionMode(PictureConfig.MULTIPLE)
//                .previewImage(true)
//                .previewVideo(false)
//                .enablePreviewAudio(false) // 是否可播放音频
//                .compressGrade(Luban.THIRD_GEAR)
//                .isCamera(true)
//                .enableCrop(false)
//                .compress(false)
//                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)
//                .glideOverride(160, 160)
//                .previewEggs(true)
//                .selectionMedia(mSelectList_photo)
////                .selectionMedia(choose_type == CHOOSE_CAR_NUMBER ? mSelectList_photo :
////                        choose_type == CHOOSE_CAR_BODY ? mSelectList_cheshen : mSelectList_huowu)
//                .forResult(result_type);
//    }
//
//
//    private void initView(View view) {
//
//        mShowViewPager = (NoScrollViewPager) getActivity().findViewById(R.id.view_pager_show);
//
//        TextView title_text_toll = (TextView) view.findViewById(R.id.title_text_toll).findViewById(R.id.details_fragment_title_text);
//        TextView title_text_photo = (TextView) view.findViewById(R.id.title_text_photo).findViewById(R.id.details_fragment_title_text);
//        title_text_toll.setText("收费信息");
//        title_text_photo.setText("采集照片");
//
//        mDetailTextWeight = (EditText) view.findViewById(R.id.detail_text_weight);
//        mDetailTextFree = (EditText) view.findViewById(R.id.detail_text_free);
//        mDetailTextExport = (EditText) view.findViewById(R.id.detail_text_export);
//
//        mDetailTextWeight.addTextChangedListener(new TextChangeWatcher(editable -> {
//            notifyScanWeightChange(String.valueOf(editable));
//        }));
//        mDetailTextFree.addTextChangedListener(new TextChangeWatcher(editable -> {
//            notifyScanFreeChange(String.valueOf(editable));
//        }));
//
//        List<TeamItem> teamItemList = DataSupport.where("username = ? ", mUsername).find(TeamItem.class);
//        if (teamItemList.size() > 0) {
//
//            mDetailTextExport.setText(teamItemList.get(0).getDefaultLane());
//        } else {
//            mDetailTextExport.setText("A01");
//        }
//
//        mDetailTextExport.setOnClickListener(view1 -> {
//            selectExportText(view1);
//        });
//
//        mEtInputBox1 = (TextView) view.findViewById(R.id.et_input_box_1);
//        mEtInputBox2 = (TextView) view.findViewById(R.id.et_input_box_2);
//        mEtInputBox3 = (TextView) view.findViewById(R.id.et_input_box_3);
//        mEtInputBox4 = (TextView) view.findViewById(R.id.et_input_box_4);
//        mEtInputBox5 = (TextView) view.findViewById(R.id.et_input_box_5);
//        mEtInputBox6 = (TextView) view.findViewById(R.id.et_input_box_6);
//        mEtInputBox7 = (TextView) view.findViewById(R.id.et_input_box_7);
//        mEtInputBox8 = (TextView) view.findViewById(R.id.et_input_box_8);
//        mTvChangeGoodsDetail = (TextView) view.findViewById(R.id.tv_change_goods_detail);
//
//        mEditTextViews = new TextView[]{mEtInputBox1, mEtInputBox2,
//                mEtInputBox3, mEtInputBox4, mEtInputBox5, mEtInputBox6, mEtInputBox7, mEtInputBox8};
//
//
//        MylinearLayout linearLayout = (MylinearLayout) view.findViewById(R.id.ll_license);
//
//        linearLayout.setOnClick(new MylinearLayout.InterceptText() {
//            @Override
//            public void onClick() {
//                openLicense();
//            }
//        });
//    }
//
//    private void selectExportText(View view) {
//
//        int mWidth = mDetailTextExport.getWidth();
//
//        List<TeamItem> teamItems = DataSupport.where("username = ?", mUsername).find(TeamItem.class);
//        if (mLaneList == null) {
//            mLaneList = new ArrayList<>();
//        } else {
//            mLaneList.clear();
//        }
//        if (teamItems.size() > 0 && teamItems.get(0).getLanes().size() > 0) {
//
//            mLaneList = (ArrayList<String>) teamItems.get(0).getLanes();
//        } else {
//            mLaneList.add("A01");
////            ToastUtils.singleToast("未添加车道号,请在后台添加车道");
//        }
//
//        SpinnerAdapter mAdapterLane = new SpinnerAdapter((AppCompatActivity) getActivity(), mLaneList, position -> {
//            String export = mLaneList.get(position);
//            mDetailTextExport.setText(export);
//            notifyScanExportChange(export);
//            mPopupWindow.dismissPopWindow();
//        });
//
//        mPopupWindow = new SpinnerPopupWindow.Builder(getActivity())
//                .setmLayoutManager(null, 0)
//                .setmAdapter(mAdapterLane)
//                .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
//                .setmHeight(400).setmWidth((int) (mWidth * 1.5))
//                .setOutsideTouchable(true)
//                .setFocusable(true)
//                .build();
//
////        mPopupWindow.showPopWindow(view, (int) mDimension);
//        mPopupWindow.showPopWindowCenter(view);
//    }
//
//
//    @OnClick({R.id.tv_change_goods_detail,
//            R.id.activity_recycler_left, R.id.activity_recycler_right,
//            R.id.btn_license_identify, R.id.btn_open_goods,
//            R.id.license_btn_license_sure, R.id.goods_btn_good_sure,})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_license_identify:
//                openIdentify();
//
//                break;
//
//            case R.id.license_btn_license_sure:
//                closeLicense();
//                break;
//
//            case R.id.btn_open_goods:
//                openGoods();
//
//                break;
//            case R.id.goods_btn_good_sure:
//                closeGoods(view);
//
//                break;
//
//            case R.id.tv_change_goods_detail:
//                openGoods();
////                enterSureActivity(GlobalManager.ENTERTYPE_GOODS);
////                ToastUtils.singleToast("货物");
//
//                break;
//            //预览跳转到最左边
//            case R.id.activity_recycler_left:
//                mRecyclerViewShootPhoto.scrollToPosition(0);
//                mRecyclerLeft.setVisibility(View.INVISIBLE);
//                mRecyclerRight.setVisibility(View.VISIBLE);
//                break;
//            //预览跳转到最右边
//            case R.id.activity_recycler_right:
//                mRecyclerViewShootPhoto.scrollToPosition(mMyBitmaps_recycler_all.size() - 1);
//                mRecyclerLeft.setVisibility(View.VISIBLE);
//                mRecyclerRight.setVisibility(View.INVISIBLE);
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void openIdentify() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            CheckPermission checkPermission = new CheckPermission(getActivity());
//            if (checkPermission.permissionSet(PERMISSION)) {
//                PermissionActivity.startActivityForResult(getActivity(), 0, "true", PERMISSION);
//
//            } else {
//                closeLicense();
//                MemoryCameraActivity.actionStart((AppCompatActivity) getActivity(), DetailsFragment001.this, true);
//            }
//        } else {
//            closeLicense();
//            MemoryCameraActivity.actionStart((AppCompatActivity) getActivity(), DetailsFragment001.this, true);
//        }
//    }
//
//    /**
//     * 打开车牌号的视图
//     */
//    public void openLicense() {
//        if (mLicenseRlLicenseAll.getVisibility() == View.GONE) {
//            forbidScroll();
//            mDetailBelowLlLicense.setVisibility(View.GONE);
//            getActivity().findViewById(R.id.menu_fab).setVisibility(View.GONE);
//            TranslateAnimation animation = AnimationUtil.translate(0.0f, 0.0f, -0.8f, 0.0f);
//
//            mLicenseRlLicenseAll.startAnimation(animation);
//            mLicenseRlLicenseAll.setVisibility(View.VISIBLE);
//
//            keyboardUtil = new LicenseKeyboardUtil(getContext(), DetailsFragment001.this, mDetailView,
//                    mEditTextViews, "无".equals(getLicenseNumber()) ? 0 : getLicenseNumber().length());
////        keyboardUtil = new LicenseKeyboardUtil(getContext(), view, mEditTextViews, 0);
//            keyboardUtil.showKeyboard();
//        }
//        if (mThemeTag == 1) {
//            setTextColor(Color.DKGRAY);
//        } else {
//            setTextColor(Color.WHITE);
//        }
//    }
//
//
//    /**
//     * 关闭车牌号的视图
//     */
//    public void closeLicense() {
//        if (mLicenseRlLicenseAll.getVisibility() == View.VISIBLE) {
//            allowScroll();
//            mDetailBelowLlLicense.setVisibility(View.VISIBLE);
//            getActivity().findViewById(R.id.menu_fab).setVisibility(View.VISIBLE);
//
//            TranslateAnimation mHiddenAction = AnimationUtil.translate(0.0f, 0.0f, 0.0f, 1.0f);
//
//            mLicenseRlLicenseAll.startAnimation(mHiddenAction);
//            mLicenseRlLicenseAll.setVisibility(View.GONE);
//        }
//        checkingBlack(getLicenseNumber());
//    }
//
//    /**
//     * 打开货物的视图
//     */
//    private void openGoods() {
//
//        if (mGoodsRlGoodsAll.getVisibility() == View.GONE) {
//            forbidScroll();
//
//            getActivity().findViewById(R.id.menu_fab).setVisibility(View.GONE);
//            mDetailFragmentAll.setVisibility(View.GONE);
//            TranslateAnimation good_animation = AnimationUtil.translate(0.0f, 0.0f, -0.8f, 0.0f);
//
//            mGoodsRlGoodsAll.startAnimation(good_animation);
//
//            mGoodsRlGoodsAll.setVisibility(View.VISIBLE);
//        }
//    }
//
//    /**
//     * 关闭货物的视图
//     *
//     * @param view
//     */
//    private void closeGoods(View view) {
//        if (mGoodsRlGoodsAll.getVisibility() == View.VISIBLE) {
//            allowScroll();
//
//            mDetailFragmentAll.setVisibility(View.VISIBLE);
//
//            TranslateAnimation good_mHiddenAction = AnimationUtil.translate(0.0f, 0.0f, 0.0f, 1.0f);
//
//            mGoodsRlGoodsAll.startAnimation(good_mHiddenAction);
//            mGoodsRlGoodsAll.setVisibility(View.GONE);
//
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
//
//
//            if (sGoodsBuilder == null) {
//                sGoodsBuilder = new StringBuilder();
//            } else if (sGoodsBuilder.length() != 0) {
//                sGoodsBuilder.delete(0, sGoodsBuilder.length());
//            }
//            if (mGoodsTextList != null && mGoodsTextList.size() != 0) {
//
//                for (int i = 0; i < mGoodsTextList.size(); i++) {
//                    if (i == mGoodsTextList.size() - 1) {
//                        sGoodsBuilder.append(mGoodsTextList.get(i));
//                    } else {
//                        sGoodsBuilder.append(mGoodsTextList.get(i) + ";");
//                    }
//                }
//
//                mTvChangeGoodsDetail.setText(sGoodsBuilder.toString().trim());
//            } else {
//                mTvChangeGoodsDetail.setText("");
//            }
//        }
//        getActivity().findViewById(R.id.menu_fab).setVisibility(View.VISIBLE);
//        mGoodEditText.setText("");
//        mLlGoodsHidePart.setVisibility(View.VISIBLE);
//    }
//
//    /**
//     * 打开货物时禁止ViewPager滑动
//     */
//    private void forbidScroll() {
//        mShowViewPager.setNeedScroll(false);
//        ShowActivity.setTabLayoutCanClick(false);
//    }
//
//    /**
//     * 打开货物时允许ViewPager滑动
//     */
//    private void allowScroll() {
//        ShowActivity.setTabLayoutCanClick(true);
//        mShowViewPager.setNeedScroll(true);
//    }
//
//
//    /**
//     * 对扫描二维码后的信息进行解析
//     *
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case ShowActivity.RESULT_CODE_LICENSE:
//
//                //处理扫描结果（在界面上显示）
//                if (null != data) {
//                    String license_number = data.getStringExtra(ShowActivity.LICENSE_NUMBER);
//                    Logger.i(license_number);
////                mTvChangeNumberDetail.setText(license_number);
////                    inflateNumber(license_number);
//                    if (license_number != null && !"".equals(license_number)) {
//                        clearInputBox();
//                    }
//                    checkingBlack(license_number);
//                }
//                break;
//            case CHOOSE_CAR_FROM_PICTURE:
//
//                // 图片选择
//                if (mMyBitmaps_recycler_all == null) {
//                    mMyBitmaps_recycler_all = new ArrayList<>();
//                } else {
//                    if (mMyBitmaps_recycler_all.size() != 0) {
//                        mMyBitmaps_recycler_all.clear();
//                    }
//                }
//                Logger.i("回调成功goods");
//                mSelectList_photo = PictureSelector.obtainMultipleResult(data);
//
//                Logger.i(mSelectList_photo.size() + "");
////                for (int i = 0; i < mSelectList_photo.size(); i++) {
////                    Logger.i(mSelectList_photo.get(i).toString());
////                }
//                new Thread(() -> {
//                    if (mSelectList_photo.size() > 0) {
//                        for (int i = 0; i < mSelectList_photo.size(); i++) {
//                            LocalMedia localMedia = mSelectList_photo.get(i);
//                            String photo_path = localMedia.getPath();
//                            Bitmap bitmap = BitmapUtil.convertToBitmap(photo_path, 800, 800);
//                            String title = "照片-" + (i + 1);
//                            MyBitmap myBitmap = new MyBitmap(photo_path, bitmap, title);
//                            mMyBitmaps_recycler_all.add(myBitmap);
//                        }
//                    }
//                    addElsePicture();
//
//                    getActivity().runOnUiThread(() -> {
//                        mPhotoAdapter.updateListView(mMyBitmaps_recycler_all);
//                    });
//                }).start();
//                break;
//        }
//    }
//
//    /**
//     * 如果照片不足三张,则添加其他的带加号的图片
//     */
//    private void addElsePicture() {
//        if (mMyBitmaps_recycler_all.size() < 3) {
//            for (int i = mMyBitmaps_recycler_all.size() - 1; i < 2; i++) {
//                String title = "照片" + (i + 2);
//                MyBitmap myBitmap = new MyBitmap(mBitmap_add, title);
//                mMyBitmaps_recycler_all.add(myBitmap);
//            }
//        }
//    }
//
////    private void enterSureActivity(String type) {
////        String carNumber = mTvChangeNumberDetail.getText().toString();
//////        String goods = mTvChangeGoodsDetail.getText().toString();
////
////        SerializableMain2Sure main2Sure = new SerializableMain2Sure();
////        main2Sure.setCarNumber_I(carNumber);
//////        main2Sure.setGoods_I(goods);
////        if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(sEnterType)) {
////            SureGoodsActivity.actionStart((ShowActivity) getActivity(), main2Sure, type, sEnterType, sLite_ID);
////            sEnterType = GlobalManager.TYPE_MAIN_ENTER_SHOW;
////        } else {
////            SureGoodsActivity.actionStart(getActivity(), main2Sure, type, sEnterType);
////        }
////        tag = true;
////    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//
////    /**
////     * 改变RadioGroup时的点击事件
////     *
////     * @param group
////     * @param checkedId
////     */
////    @Override
////    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
////        switch (checkedId) {
////            case R.id.goods_shucai:
////                mRadioGroup2.clearCheck();
////                mRadioGroup1.check(R.id.goods_shucai);
////                closeQiTa(false);
////                current_kind = KIND_SHUCAI;
////                if (mCurrentGoodsList == null) {
////                    mCurrentGoodsList = new ArrayList<>();
////                }
////                if (mGoodsShuCais != null) {
////                    mCurrentGoodsList = mGoodsShuCais;
////                }
////                if (mGoodsShuCais != null && mGoodsShuCais.size() != 0) {
////                    if (mGoodsAdapter != null) {
////                        mGoodsAdapter.updateListView(mGoodsShuCais);
////                    }
////                }
////                break;
////            case R.id.goods_shuiguo:
////                mRadioGroup2.clearCheck();
////                mRadioGroup1.check(R.id.goods_shuiguo);
////                notifyImageView(mGoodsShuiGuos, KIND_SHUIGUO, false);
////                break;
////            case R.id.goods_shuichanpin:
////                mRadioGroup2.clearCheck();
////                mRadioGroup1.check(R.id.goods_shuichanpin);
////                notifyImageView(mGoodsShuiChanPins, KIND_SHUICHANPIN, false);
////                break;
////            case R.id.goods_chuqin:
////                mRadioGroup1.clearCheck();
////                mRadioGroup2.check(R.id.goods_chuqin);
////                notifyImageView(mGoodsChuQins, KIND_CHUQIN, false);
////                break;
////            case R.id.goods_roudannai:
////                mRadioGroup1.clearCheck();
////                mRadioGroup2.check(R.id.goods_roudannai);
////                notifyImageView(mGoodsRouDanNais, KIND_ROUDANNAI, false);
////                break;
////            case R.id.goods_zaliang:
////                mRadioGroup1.clearCheck();
////                mRadioGroup2.check(R.id.goods_zaliang);
////                notifyImageView(mGoodsZaLiangs, KIND_ZALIANG, false);
////                break;
////            default:
////                break;
////        }
////    }
//
//    /**
//     * 打开更多弹出窗口
//     *
//     * @param typeList
//     */
//    private void openMore(List<String> typeList) {
//        int mWidth = mGoodsMore.getWidth();
//        ArrayList<String> moreTypeList = new ArrayList<>();
//        for (int i = 6; i < typeList.size(); i++) {
//            moreTypeList.add(typeList.get(i));
//        }
////        moreTypeList.add("货种类1");
////        moreTypeList.add("货种类2");
////        moreTypeList.add("货种类3");
////        moreTypeList.add("其他");
//
//
//        SpinnerAdapter mAdapterType = new SpinnerAdapter((AppCompatActivity) getActivity(), moreTypeList, (int position) -> {
//            String typeString = moreTypeList.get(position);
//            mCurrenntType = 6 + position;
//
////            Logger.i("第" + mCurrenntType + "种类型" + typeList.get(mCurrenntType));
////            Logger.t("wolf").i(typeString);
//            mGoodsMore.setText(typeString + " >>");
////            mRadioGroup1.clearCheck();
////            mRadioGroup2.clearCheck();
//            for (int i = 0; i < mButtonList.size(); i++) {
//                mButtonList.get(i).setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
//                mButtonList.get(i).setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
//            }
//            mGoodsMore.setTextColor(getActivity().getResources().getColor(R.color.day_color_white));
//            mGoodsMore.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_day_selected));
//
//            if (mCurrenntType < typeList.size() - 1) {
//                closeQiTa(true);
//                mCurrentGoodsList = All_GoodsList.get(mCurrenntType);
//                mGoodsAdapter.updateListView(mCurrentGoodsList);
//            } else {
//                if (mGoodRlEditQita.getVisibility() == View.GONE) {
//                    mGoodRlEditQita.setVisibility(View.VISIBLE);
//                }
//                if (mGoodImageRecyclerView.getVisibility() == View.VISIBLE) {
//                    mGoodImageRecyclerView.setVisibility(View.GONE);
//                }
//                mGoodBtnSureQita.setOnClickListener(v -> {
//                    String goods_qita = mGoodEditTextQita.getText().toString().trim();
//                    if (goods_qita.length() > 0) {
//                        mGoodsTextList.add(goods_qita);
//                        updateTextListView(mGoodsTextList);
//                        mGoodEditTextQita.setText("");
//                    }
//
//                });
//            }
////            if ("货种类1".equals(typeString)) {
////                notifyImageView(mGoodsChuQins, KIND_CHUQIN, true);
////
////            } else if ("货种类2".equals(typeString)) {
////                notifyImageView(mGoodsShuCais, KIND_SHUCAI, true);
////
////            } else if ("货种类3".equals(typeString)) {
////                notifyImageView(mGoodsShuiGuos, KIND_SHUIGUO, true);
////
////            } else if ("其他".equals(typeString)) {
////                if (mGoodRlEditQita.getVisibility() == View.GONE) {
////                    mGoodRlEditQita.setVisibility(View.VISIBLE);
////                }
////                if (mGoodImageRecyclerView.getVisibility() == View.VISIBLE) {
////                    mGoodImageRecyclerView.setVisibility(View.GONE);
////                }
////                mGoodBtnSureQita.setOnClickListener(v -> {
////                    String goods_qita = mGoodEditTextQita.getText().toString().trim();
////                    if (goods_qita.length() > 0) {
////                        mGoodsTextList.add(goods_qita);
////                        updateTextListView(mGoodsTextList);
////                        mGoodEditTextQita.setText("");
////                    }
////
////                });
////            }
//
//            mPopupWindow.dismissPopWindow();
//        });
//
//        mPopupWindow = new SpinnerPopupWindow.Builder(getActivity())
//                .setmLayoutManager(null, 0)
//                .setmAdapter(mAdapterType)
//                .setmItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 5, Color.GRAY))
//                .setmHeight(600).setmWidth(mWidth * 2)
//                .setOutsideTouchable(true)
//                .setFocusable(true)
//                .build();
//
////        mPopupWindow.showPopWindow(view, (int) mDimension);
//        mPopupWindow.showPopWindowCenter(mGoodsMore);
//    }
//
//    private void notifyImageView(ArrayList<SerializableGoods> goodsTypeList, String currentKind, boolean isMore) {
//        closeQiTa(isMore);
//        if (goodsTypeList != null && goodsTypeList.size() != 0) {
//            mGoodsAdapter.updateListView(goodsTypeList);
//        }
//        current_kind = currentKind;
//        if (goodsTypeList != null) {
//            mCurrentGoodsList = goodsTypeList;
//        }
//    }
//
//    /**
//     * 打开其他,输入界面
//     */
//    private void openQita() {
//
//    }
//
//    private void closeQiTa(boolean isMore) {
//        if (!isMore) {
//            mGoodsMore.setTextColor(getActivity().getResources().getColor(R.color.licence_text_normal_day));
//            mGoodsMore.setBackground(getActivity().getResources().getDrawable(R.drawable.license_color_normal_day));
//        }
//
//        if (mGoodImageRecyclerView.getVisibility() == View.GONE) {
//            mGoodImageRecyclerView.setVisibility(View.VISIBLE);
//        }
//        if (mGoodRlEditQita.getVisibility() == View.VISIBLE) {
//            mGoodRlEditQita.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//        if (mLlGoodsHidePart.getVisibility() == View.VISIBLE) {
//            mLlGoodsHidePart.setVisibility(View.GONE);
//        }
////        AnimationUtil.translate()
////        mLlGoodsHidePart.setVisibility(View.GONE);
//        String goodString = mGoodEditText.getText().toString().trim();
//
//        if ("".equals(goodString)) {
//            mGoodsAdapter.updateListView(mCurrentGoodsList);
//        } else {
//            Logger.i(goodString);
//            List<SerializableGoods> fileterList = PingYinUtil.getInstance()
//                    .search_goods(mGoodsAllList, goodString);
//            for (int i = 0; i < fileterList.size(); i++) {
//                Logger.i(fileterList.get(i).getScientific_name());
//                Logger.i(fileterList.get(i).getSimpleSpell());
//            }
//            mGoodsAdapter.updateListView(fileterList);
//        }
//    }
//
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_All, mGoodsAllList);
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_SHUCAI, mGoodsShuCais);
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_SHUIGUO, mGoodsShuiGuos);
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_SHUICHANPIN, mGoodsShuiChanPins);
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_CHUQIN, mGoodsChuQins);
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_ROUDANNAI, mGoodsRouDanNais);
//        ACache.get(getActivity()).put(ACache.GOODSACACHE_ZALIANG, mGoodsZaLiangs);
//    }
//
//    public interface BitmapListListener {
//        void BitmapListener(ArrayList<MyBitmap> mMyBitmaps_sanzheng
//        );
//    }
//
//    public static void setDetailsConnectListener(DetailsBeanConnectListener listener) {
//
//        ArrayList<PathTitleBean> pathTitleList = null;
//        if (pathTitleList == null) {
//            pathTitleList = new ArrayList<>();
//        } else {
//            pathTitleList.clear();
//        }
//
//        if (mMyBitmaps_recycler_all != null) {
//            for (int i = 0; i < mMyBitmaps_recycler_all.size(); i++) {
//                String title = mMyBitmaps_recycler_all.get(i).getTitle();
//                String path = mMyBitmaps_recycler_all.get(i).getPath();
//                if (path != null && !"".equals(path)) {
//                    MyBitmap myBitmap = mMyBitmaps_recycler_all.get(i);
//                    PathTitleBean titleBean = new PathTitleBean(GlobalManager.PHOTO_TYPE_SANZHENG,
//                            myBitmap.getPath(), myBitmap.getTitle());
//                    pathTitleList.add(titleBean);
//                }
//            }
//            if (pathTitleList != null) {
//                Logger.i(pathTitleList.size() + "---" + "---");
//            }
//        }
//
//        String number = getLicenseNumber();
//        String goods = mTvChangeGoodsDetail.getText().toString().trim();
//
//        String text_free = mDetailTextFree.getText().toString().trim();
//        String text_export = mDetailTextExport.getText().toString().trim();
//        String text_weight = mDetailTextWeight.getText().toString().trim();
//
//        DetailInfoBean bean = new DetailInfoBean();
//        bean.setNumber(number);
//        bean.setGoods(goods);
//        bean.setText_weight(text_weight);
//        bean.setText_free(text_free);
//        bean.setText_export(text_export);
//        bean.setPath_and_title(pathTitleList);
//
//        listener.beanConnect(bean);
//    }
//
//    public interface DetailsBeanConnectListener {
//
//        void beanConnect(DetailInfoBean bean);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mMyBitmaps_recycler_all.clear();
//        int allCount = 0;
//        for (int i = 0; i < All_GoodsList.size(); i++) {
//            ACache.get(getActivity()).put(ACache.Type_ACACHE + i, All_GoodsList.get(i));
//            allCount += All_GoodsList.get(i).size();
//        }
//        ACache.get(getActivity()).put(ACache.ITEM_COUNT, allCount + "");
//        ACache.get(getActivity()).put(ACache.TYPR_COUNT, All_GoodsList.size() + "");
//        ACache.get(getActivity()).put(ACache.ALL_ACACHE, mGoodsAllList);
//
//    }
//
//    public static void notifyDataChange() {
//        mSelectList_photo = null;
//        mGoodsTextList = null;
//        mGoodsText = null;
//    }
//
////    public static void setSelectedListListener(SelectedListListener listener) {
////        listener.Selected(mSelectList_photo);
////
////    }
//
//
//    public static void notifyTag(boolean newTag) {
//        tag = newTag;
//    }
//
//    public static void notifyChangeTextColor() {
//
////        mTvChangeNumberDetail.setTextColor(Color.RED);
//    }
//
//    private void inflateNumber(String carNumber) {
//
////        if (carNumber.length() == 7) {
////            mEtInputBox8.setText("");
////
////            String edit_1_I = carNumber.substring(0, 1);
////            String edit_2_I = carNumber.substring(1, 2);
////            String edit_3_I = carNumber.substring(2, 3);
////            String edit_4_I = carNumber.substring(3, 4);
////            String edit_5_I = carNumber.substring(4, 5);
////            String edit_6_I = carNumber.substring(5, 6);
////            String edit_7_I = carNumber.substring(6, 7);
////
////            String[] edit_texts = {edit_1_I, edit_2_I, edit_3_I, edit_4_I, edit_5_I, edit_6_I, edit_7_I};
////            for (int i = 0; i < edit_texts.length; i++) {
////                mEditTextViews[i].setText(edit_texts[i]);
////            }
////        } else if (carNumber.length() == 8) {
////            String edit_1_I = carNumber.substring(0, 1);
////            String edit_2_I = carNumber.substring(1, 2);
////            String edit_3_I = carNumber.substring(2, 3);
////            String edit_4_I = carNumber.substring(3, 4);
////            String edit_5_I = carNumber.substring(4, 5);
////            String edit_6_I = carNumber.substring(5, 6);
////            String edit_7_I = carNumber.substring(6, 7);
////            String edit_8_I = carNumber.substring(7, 8);
////
////            String[] edit_texts = {edit_1_I, edit_2_I, edit_3_I, edit_4_I, edit_5_I, edit_6_I, edit_7_I, edit_8_I};
////            for (int i = 0; i < edit_texts.length; i++) {
////                mEditTextViews[i].setText(edit_texts[i]);
////            }
////        }
//        if (mEdit_texts == null) {
//            mEdit_texts = new ArrayList<>();
//        } else {
//            mEdit_texts.clear();
//        }
//
//        for (int i = 0; i < carNumber.length(); i++) {
//            String edit_text = carNumber.substring(i, i + 1);
//            mEdit_texts.add(edit_text);
//        }
//
//        for (int i = 0; i < mEdit_texts.size(); i++) {
//            mEditTextViews[i].setText(mEdit_texts.get(i));
//        }
//        notifyScanLicenseChange(carNumber);
//    }
//
//    private static String getLicenseNumber() {
//        String number = mEtInputBox1.getText().toString().trim() +
//                mEtInputBox2.getText().toString().trim() +
//                mEtInputBox3.getText().toString().trim() +
//                mEtInputBox4.getText().toString().trim() +
//                mEtInputBox5.getText().toString().trim() +
//                mEtInputBox6.getText().toString().trim() +
//                mEtInputBox7.getText().toString().trim() +
//                mEtInputBox8.getText().toString().trim();
//        Logger.i(number);
//        return number;
//    }
//
//    /**
//     * 将八个textview全部清空
//     */
//    public void clearInputBox() {
//        for (int i = 0; i < mEditTextViews.length; i++) {
//            mEditTextViews[i].setText("");
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        if (!mIsFirst) {
//            PreviewDetailActivity.setPictureListener(myBitmapList -> {
//                if (mMyBitmaps_recycler_all == null) {
//                    mMyBitmaps_recycler_all = new ArrayList<>();
//                } else {
//                    mMyBitmaps_recycler_all.clear();
//                }
//                mMyBitmaps_recycler_all.addAll(myBitmapList);
//            });
//
//            addElsePicture();
//            mPhotoAdapter.updateListView(mMyBitmaps_recycler_all);
//            mIsFirst = true;
//
//            inflateSelected();
//
//            String number = sSupportDetail.getNumber();
//            String goodsFromDraft = sSupportDetail.getGoods();
//            String draft_weight = sSupportDetail.getDetail_weight();
//            String draft_free = sSupportDetail.getDetail_free();
//            String draft_export = sSupportDetail.getDetail_export();
//            checkingBlack(number);
//            mGoodsText = goodsFromDraft;
//            updateTextListView(getGoodsTextList(mGoodsText));
//            mTvChangeGoodsDetail.setText(goodsFromDraft);
//
//            mDetailTextWeight.setText(draft_weight);
//            mDetailTextFree.setText(draft_free);
//            mDetailTextExport.setText(draft_export);
//
//        }
//    }
//
//    /**
//     * 检查返回或者得到的车牌号是否被加入黑名单
//     */
//    private void checkingBlack(String carNumber) {
//        if (carNumber.length() >= 7) {
//            List<SupportBlack> blackList = DataSupport.findAll(SupportBlack.class);
//            if (blackList != null && blackList.size() != 0) {
//                boolean isBlack = isBlack(carNumber, blackList);
//                if (isBlack) {
//                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                    Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
//                    r.play();
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                    builder.setTitle("该车牌为黑名单车牌");
//                    builder.setPositiveButton("了解", (dialog, which) -> {
//                        dialog.dismiss();
//                    });
//                    builder.setCancelable(false);
//                    builder.show();
//
//                    setTextColor(Color.RED);
//                } else {
//                    if (mThemeTag == 1) {
//                        setTextColor(Color.DKGRAY);
//                    } else {
//                        setTextColor(Color.WHITE);
//                    }
//                }
//            } else {
//                if (mThemeTag == 1) {
//                    setTextColor(Color.DKGRAY);
//                } else {
//                    setTextColor(Color.WHITE);
//                }
//            }
//        }
//        inflateNumber(carNumber);
//    }
//
//    private void setTextColor(int color) {
//        for (int i = 0; i < mEditTextViews.length; i++) {
//            mEditTextViews[i].setTextColor(color);
//        }
//    }
//
//    public static void setSelectedListListener(SelectedListListener listener) {
//        listener.Selected(mSelectList_photo);
//    }
//
//    public interface SelectedListListener {
//        void Selected(List<LocalMedia> medias_photo);
//    }
//
//
//    private void initGoods(View view) {
//        initGoodsData01();
//        initGoodView(view);
//        initGoodRecyclerView01();
////        initTypeView();
//    }
//
//
//    private void initData() {
//        String BASE_url = "http://10.0.2.2:3000/data/";
////        String BASE_url = "https://api.douban.com/v2/movie/";
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(5, TimeUnit.SECONDS);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                // .client(genericClient())
//                .client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(BASE_url)
//                .build();
//
//        HttpUtilsApi httpUtilsApi = retrofit.create(HttpUtilsApi.class);
//
//        Observable<GoodsBean> goods = httpUtilsApi.getGoods("goods");
//
//
//        Subscriber<GoodsBean> subscriber = new Subscriber<GoodsBean>() {
//            @Override
//            public void onCompleted() {
//                Logger.i("完成");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//                Logger.i("错误" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(GoodsBean goodsBean) {
//                Logger.i(goodsBean.getCode() + "");
//                List<GoodsBean.DataBean.SubjectsBean> subjectsBeanList = goodsBean.getData().getSubjects();
//                List<String> typeList = goodsBean.getData().getGoodTypeList();
//                Logger.i(typeList.get(0));
//                for (int i = 0; i < typeList.size(); i++) {
//
//                }
//                int count = goodsBean.getData().getCount();
//
//                Logger.i(subjectsBeanList.toString());
//                Logger.i(count + "");
////                ArrayList<String> typeList = new ArrayList<>();
////                List<String> typeNames = goodsBean.getData().getGoodTypeList();
////                typeList.addAll(typeNames);
//                DataSupport.deleteAll(SupportGoods.class);
//                for (int i = 0; i < subjectsBeanList.size(); i++) {
//                    SupportGoods supportGoods = new SupportGoods();
////                    supportGoods.setCount(count);
////                    supportGoods.setGoodTypeList(typeList);
//                    supportGoods.setName(subjectsBeanList.get(i).getName());
//                    supportGoods.setType(subjectsBeanList.get(i).getType());
//                    supportGoods.setImageUrl(subjectsBeanList.get(i).getImageUrl());
//                    supportGoods.setSortId(subjectsBeanList.get(i).getSortId());
//                    supportGoods.save();
//                }
//                SupportGoods supportGoods = new SupportGoods();
//                supportGoods.setCount(count);
//                supportGoods.setGoodTypeList(typeList);
//                supportGoods.updateAll();
//
//
////                List<SupportGoods> supportGoodsList = DataSupport.findAll(SupportGoods.class);
////                for (int i = 0; i < supportGoodsList.size(); i++) {
////                    Logger.i(supportGoodsList.get(i).getName());
////                    Logger.i(supportGoodsList.get(0).getGoodTypeList().get(0));
////                }
//
//            }
//        };
//        goods.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//
//    }
//
//    private void initTypeView() {
//        SupportGoods supportGoods1 = DataSupport.findFirst(SupportGoods.class);
////        List<SupportGoods> all = DataSupport.findAll(SupportGoods.class);
////
//        Logger.i(supportGoods1.getCount() + "");
//        Logger.i(supportGoods1.getGoodTypeList().get(0).toString());
//
//
//    }
//
//    //加载本地的图片等数据
//    private void initGoodsData01() {
//        List<SupportLocalGoods> all = DataSupport.findAll(SupportLocalGoods.class);
//        List<String> typeList = DataSupport.findFirst(SupportGoods.class).getGoodTypeList();
////        ArrayList<ArrayList<String>> All_GoodsName = new ArrayList<>();
////        ArrayList<ArrayList<String>> All_GoodsImage = new ArrayList<>();
//
//        String typeCount = ACache.get(getActivity()).getAsString(ACache.TYPR_COUNT);
//        String allCount = ACache.get(getActivity()).getAsString(ACache.ITEM_COUNT);
//        Logger.i(typeCount + "---" + allCount);
//        if (mGoodsAllList != null && mGoodsAllList.size() != 0) {
//            mGoodsAllList.clear();
//        }
//        if (allCount != null && (all.size() + "").equals(allCount) && typeCount != null) {
//            Logger.i("直接走的缓存");
//            for (int i = 0; i < Integer.parseInt(typeCount); i++) {
//                ArrayList<SerializableGoods> mAsObject_Type = (ArrayList<SerializableGoods>) ACache
//                        .get(getActivity()).getAsObject(ACache.Type_ACACHE + i);
//                All_GoodsList.add(mAsObject_Type);
//            }
//            ArrayList<SerializableGoods> mAsObject_All = (ArrayList<SerializableGoods>) ACache
//                    .get(getActivity()).getAsObject(ACache.ALL_ACACHE);
//            mGoodsAllList.addAll(mAsObject_All);
//        } else {
//            Logger.i("重新加载数据未走缓存");
//            for (int i = 0; i < typeList.size(); i++) {
//                ArrayList<SerializableGoods> localGoodList = new ArrayList<>();
//                ArrayList<String> goodPinyinList = new ArrayList<>();
//                ArrayList<String> goodNameList = new ArrayList<>();
//                ArrayList<Integer> goodSortIdList = new ArrayList<>();
//                ArrayList<String> goodIamgeUrlList = new ArrayList<>();
//
//                List<SupportLocalGoods> goodsList = DataSupport.where("type = ?", typeList.get(i)).find(SupportLocalGoods.class);
//                for (int j = 0; j < goodsList.size(); j++) {
////                    Logger.i(goodsList.size() + "----" + goodsList.get(j).getType());
//                    goodPinyinList.add(goodsList.get(j).getPinyin());
//                    goodNameList.add(goodsList.get(j).getName());
//                    goodSortIdList.add(goodsList.get(j).getSortId());
//                    goodIamgeUrlList.add(goodsList.get(j).getImageUrl());
//                }
//                All_GoodsList.add(getGoodsData01(localGoodList, goodIamgeUrlList, goodPinyinList, goodNameList, goodSortIdList));
//            }
//            for (int i = 0; i < All_GoodsList.size(); i++) {
////                Logger.i(All_GoodsList.get(i).get(0).getScientific_name());
//                mGoodsAllList.addAll(All_GoodsList.get(i));
//            }
//        }
//
//
//        mCurrentGoodsList = All_GoodsList.get(0);
//    }
//
//    private ArrayList<SerializableGoods> getGoodsData01(ArrayList<SerializableGoods> goodsList,
//                                                        ArrayList<String> goods_urls, ArrayList<String> goods_pinyins,
//                                                        ArrayList<String> goods_names, ArrayList<Integer> goodSortIdList) {
//        if (goodsList != null && goodsList.size() != 0) {
//            goodsList.clear();
//        }
////        ArrayList<String> goodsImage_url = new ArrayList<>();
////        ArrayList<String> goods_name = new ArrayList<>();
////        for (int i = 0; i < goods_url.size(); i++) {
////            goodsImage_url.add(goods_url.get(i));
////            Logger.i(goods_url.get(i)+"------"+goods_pinyin.get(i));
////        }
////        for (int i = 0; i < goods_pinyin.size(); i++) {
////            goods_name.add(goods_pinyin.get(i));
////        }
//
////        Collections.sort(goodsImage_url);
////        Collections.sort(goods_name);
//
//        Logger.i(goods_pinyins.size() + "");
//
//        for (int i = 0; i < goods_pinyins.size(); i++) {
//
//            String scientific_name = goods_names.get(i);
////            Logger.i(scientific_name + goods_urls.get(i));
////            String bitmap_url = dir + "/" + goodsImage_url.get(i);
//
//            SerializableGoods goods = new SerializableGoods();
//
//            String sortLetters = Cn2Spell.getPinYinFirstLetter(scientific_name);
//            String sortKey = PingYinUtil.format(scientific_name);
//            String headChar = Cn2Spell.getPinYinHeadChar(sortKey);
//            String pinYin = Cn2Spell.getPinYin(sortKey);
//
//            goods.setScientific_name(scientific_name);
//            goods.setBitmapUrl(goods_urls.get(i));
//            goods.setSortLetters(sortLetters);
//            goods.setWholeSpell(pinYin);
//            goods.setSimpleSpell(headChar);
//            goods.setSortId(goodSortIdList.get(i));
//
//            goodsList.add(goods);
//        }
//        GoodsComparator comparator = new GoodsComparator();
//        Collections.sort(goodsList, comparator);
//        return goodsList;
//    }
//
//    private void initGoodRecyclerView01() {
//
//        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false);
//        mGoodImageRecyclerView.setLayoutManager(manager);
//
//        //货物text的recyclerView
//        mGoodsAdapter = new SureGoodsAdapter(getContext(), mCurrentGoodsList, new SureGoodsAdapter.onItemClick() {
//            @Override
//            public void itemClick(SerializableGoods serializableGoods, int position) {
//
//                String scientificName = serializableGoods.getScientific_name();
//                if (mGoodsTextList.contains(scientificName)) {
//                    ToastUtils.singleToast("已经选择了此货物");
//                } else {
//                    mGoodsTextList.add(scientificName);
//                }
//                mGoodEditText.setText("");
//                mLlGoodsHidePart.setVisibility(View.VISIBLE);
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(mGoodEditText.getWindowToken(), 0); //强制隐藏键盘
//
//                updateTextListView(mGoodsTextList);
//                //进行置顶操作
////                serializableGoods.setTop(1);
////                serializableGoods.setTime(System.currentTimeMillis());
////                refreshView(mCurrentGoodsList);
//            }
//        });
//        mGoodImageRecyclerView.addItemDecoration(new DividerGridItemDecoration(getContext(), 3));
//        mGoodImageRecyclerView.setAdapter(mGoodsAdapter);
//    }
//}
