package com.wolf.zero.greenroad.servicy;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.google.gson.Gson;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.activity.MainActivity;
import com.wolf.zero.greenroad.activity.ShowActivity;
import com.wolf.zero.greenroad.bean.CheckedBean;
import com.wolf.zero.greenroad.bean.DetailInfoBean;
import com.wolf.zero.greenroad.bean.PathTitleBean;
import com.wolf.zero.greenroad.bean.ScanInfoBean;
import com.wolf.zero.greenroad.fragment.CheckedFragment;
import com.wolf.zero.greenroad.fragment.DetailsFragment;
import com.wolf.zero.greenroad.fragment.ScanFragment;
import com.wolf.zero.greenroad.httpresultbean.HttpResultCode;
import com.wolf.zero.greenroad.https.PostInfo;
import com.wolf.zero.greenroad.https.RequestJson;
import com.wolf.zero.greenroad.https.RequestPicture;
import com.wolf.zero.greenroad.litepalbean.SupportCarTypeAndConfig;
import com.wolf.zero.greenroad.litepalbean.SupportChecked;
import com.wolf.zero.greenroad.litepalbean.SupportDetail;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;
import com.wolf.zero.greenroad.litepalbean.SupportMedia;
import com.wolf.zero.greenroad.litepalbean.SupportScan;
import com.wolf.zero.greenroad.litepalbean.TeamItem;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.presenter.NetWorkManager;
import com.wolf.zero.greenroad.tools.BitmapUtil;
import com.wolf.zero.greenroad.tools.SPUtils;
import com.wolf.zero.greenroad.tools.TimeUtil;
import com.wolf.zero.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

import static com.wolf.zero.greenroad.activity.ShowActivity.mLite_id;


public class SubmitIntentService extends IntentService {
    private static DetailInfoBean mDetailInfoBean_Q;
    private static ScanInfoBean mScanInfoBean_Q;
    private static CheckedBean mCheckedBean_Q;

    private static List<LocalMedia> mLocalMedias_Photo;
//    private static List<LocalMedia> mLocalMedias_cheshen_Q;
//    private static List<LocalMedia> mSelectList_huowu;

    private static String mStation_Q;
    private static String mRoad_Q;
    private File mFile;
    private static String mFilePath_str;
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SUBMIT = "com.zero.wolf.greenroad.submitservice.action.FOO";
    private static final String ACTION_SAVE = "com.zero.wolf.greenroad.submitservice.action.BAZ";
    public static final String ARG_BROADCAST_DRAFT = "arg_broadcast_draft";
    public static final String ARG_BROADCAST_SUBMIT = "arg_broadcast_submit";

    public static final String ARG_TYPE_SAVE = "arg_type_save";
    public static final String ARG_TYPE_SUBMIT = "arg_type_submit";

    public static final String ARG_TYPE_SHOW_SUBMIT = "arg_type_show_submit";
    public static final String ARG_TYPE_SHOW_SAVE = "arg_type_show_save";
    public static final String SAVE_OR_BACK = "save_or_back";


    private String mSubmitTime;

    private static ShowActivity sActivity;
    private static Context sContext_draft;
    private static Context sContext_submit;
    private String mShowType_submit;
    private static ArrayList<String> mNew_path_list;
    private boolean mBlackTag;
    private String mUsername;
    private ArrayList mMustList;

    public SubmitIntentService() {
        super("SubmitService");
    }


    public static void startActionSubmit(ShowActivity activity, Context context, String enterType, String showType) {
        sContext_submit = context;
        sActivity = activity;
        Intent intent = new Intent(sActivity, SubmitIntentService.class);
        intent.setAction(ACTION_SUBMIT);
        intent.putExtra(ARG_TYPE_SUBMIT, enterType);
        intent.putExtra(ARG_TYPE_SHOW_SUBMIT, showType);
        sActivity.startService(intent);
    }

    public static void startActionSave(Context context, String enterType, String showType, int id) {
        sContext_draft = context;
        Intent intent = new Intent(sContext_draft, SubmitIntentService.class);
        intent.setAction(ACTION_SAVE);
        intent.putExtra(ARG_TYPE_SAVE, enterType);
        intent.putExtra(ARG_TYPE_SHOW_SAVE, showType);
        intent.putExtra(SAVE_OR_BACK, id);

        sContext_draft.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            mUsername = (String) SPUtils.get(getApplicationContext(), GlobalManager.USERNAME, "qqqq");
            SupportDraftOrSubmit submit = DataSupport.where("username = ?", mUsername).findFirst(SupportDraftOrSubmit.class);
            if (submit != null){
                submit.setIsPass(0);
                submit.save();
            }
            try {
                TeamItem teamItem = DataSupport.where("username = ?", mUsername).findFirst(TeamItem.class);
                mRoad_Q = teamItem.getLine();
                mStation_Q = teamItem.getStation();
            } catch (Exception e) {
                e.printStackTrace();
            }


//            List<String> strListValue = SPListUtil.getStrListValue(getContext(), SPListUtil.APPCONFIGINFO);
//            if (strListValue.size() == 3) {
//                mRoad_Q = strListValue.get(1).toString();
//                mStation_Q = strListValue.get(2).toString();
//            }

            final String action = intent.getAction();



            if (ACTION_SUBMIT.equals(action)) {

                String enterType = intent.getStringExtra(ARG_TYPE_SUBMIT);
                mShowType_submit = intent.getStringExtra(ARG_TYPE_SHOW_SUBMIT);

                if (mFile == null) {
                    mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "GreenPicture");
                    mFile.mkdirs();
                }
                mFilePath_str = mFile.getPath();


                mSubmitTime = TimeUtil.getCurrentTimeToDate();
               int liteId  = TimeUtil.getTimeId();

                getListenerData(enterType);
                String currentShift;

                if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(enterType)) {
                     currentShift = DataSupport.where("username = ? and lite_ID = ? ", mUsername, mLite_id + "").findFirst(SupportDraftOrSubmit.class).getCurrentShift();
                } else {
                    currentShift = (String) SPUtils.get(this, SPUtils.CURRENT_SHIFT, "");
                }
                if (NetWorkManager.isnetworkConnected(sActivity)) {
                    if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(enterType)) {
                        postPictureAndJson(mSubmitTime, mLite_id,currentShift);
                    } else {
                        postPictureAndJson(mSubmitTime, liteId,currentShift);
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(sActivity);
                    builder.setTitle("网络无连接");
                    builder.setMessage("是否保存为草稿");
                    builder.setPositiveButton("保存草稿", (dialog, which) -> {
                        save2Litepal(mSubmitTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SUBMIT, mShowType_submit,currentShift);
                    });
                    builder.setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                    });

                    ToastUtils.singleToast("网络无连接");
                }


            } else {
                if (ACTION_SAVE.equals(action)) {
                    String enterType = intent.getStringExtra(ARG_TYPE_SAVE);
                    String showType = intent.getStringExtra(ARG_TYPE_SHOW_SAVE);
                    int id = intent.getIntExtra(SAVE_OR_BACK, 0);

                    getListenerData(enterType);
                    String currentShift;
                    if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(enterType)) {
                        currentShift = DataSupport.where("username = ? and lite_ID = ? ", mUsername, mLite_id + "").findFirst(SupportDraftOrSubmit.class).getCurrentShift();
                    } else {
                        currentShift = (String) SPUtils.get(this, SPUtils.CURRENT_SHIFT, "");
                    }
                    String saveTime = TimeUtil.getCurrentTimeToDate();
                    int liteId = TimeUtil.getTimeId();
                    if (id == 0) {
                        save2Litepal(saveTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SAVE, showType, currentShift);
                    } else {
                        save2Litepal(saveTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SAVE, showType, currentShift);
                        ShowActivity.notifyDataChangeAndFinish();
                    }
                }
            }
        }
    }

    /**
     * 拿到两个fragment中被监听的数据
     * 在提交至服务器以及保存草稿中都要用到
     */
    private void getListenerData(String enterType) {
        Logger.i("---------------" + enterType);

        DetailsFragment.setDetailsConnectListener((bean) -> {
            mDetailInfoBean_Q = bean;
        });

        if (GlobalManager.TYPE_MAIN_ENTER_SHOW.equals(enterType)) {

            DetailsFragment.setSelectedListListener((medias_sanzheng) -> {
                mLocalMedias_Photo = medias_sanzheng;
            });
        } else if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(enterType)) {
            DetailsFragment.setSelectedListListener((medias_sanzheng) -> {
                mLocalMedias_Photo = medias_sanzheng;
            });
        }


        //拿到checkFragment的数据
        CheckedFragment.setCheckedBeanConnectListener(bean -> {
            mCheckedBean_Q = bean;
            Logger.i(mCheckedBean_Q.toString());
        });

        ScanFragment.setScanConnectListener(bean -> {
            mScanInfoBean_Q = bean;
            Logger.i(mScanInfoBean_Q.toString());
        });

    }

    /**
     * 将保存草稿数据或者提交网络的数据保存之后本地服务器
     */
    private void save2Litepal(String current_time, int liteId, String lite_type, String action, String showType, String currentShift) {
//        int count = 0;
        SupportDraftOrSubmit support = new SupportDraftOrSubmit();
        if (GlobalManager.TYPE_MAIN_ENTER_SHOW.equals(showType)) {
//            count = TimeUtil.getTimeId();
//            Logger.i(count + "++++++++++++count");
            //DataSupport.count(SupportDraftOrSubmit.class);

            List<SupportDraftOrSubmit> draftOrSubmitList = DataSupport.where("lite_ID = ? and lite_type = ?", liteId + "", lite_type).find(SupportDraftOrSubmit.class);
            if (draftOrSubmitList.size() != 0) {
                ToastUtils.singleToast("该存储ID已存在,请再次存储");
                return;
            }


            support.setLite_ID(liteId);
            support.setLite_type(lite_type);
            support.setCurrent_time(current_time);
            support.setCurrent_date(TimeUtil.getStringDateShort());
            support.setCurrentShift(currentShift);
            //保存数据到表SupportScan
            SupportScan supportScan = new SupportScan();
            supportScan.setLite_ID(liteId);
            supportScan.setScan_05Q(mScanInfoBean_Q.getScan_05Q());
            supportScan.setScan_10Q(mScanInfoBean_Q.getScan_10Q());
            supportScan.setIsLimit(mScanInfoBean_Q.getIsLimit());
            supportScan.save();

            //保存数据到表SupportDetail
            SupportDetail supportDetail = new SupportDetail();
            supportDetail.setLite_ID(liteId);
            supportDetail.setNumber(mDetailInfoBean_Q.getNumber());
//            supportDetail.setColor(mDetailInfoBean_Q.getColor());
            supportDetail.setGoods(mDetailInfoBean_Q.getGoods());
            supportDetail.setDetail_weight(mDetailInfoBean_Q.getText_weight());
            supportDetail.setDetail_free(mDetailInfoBean_Q.getText_free());
            supportDetail.setDetail_export(mDetailInfoBean_Q.getText_export());
            supportDetail.setDetail_carType(mDetailInfoBean_Q.getText_carType());
            supportDetail.setStation(mStation_Q);
            supportDetail.setRoad(mRoad_Q);
            supportDetail.setLane(mDetailInfoBean_Q.getText_export());

            ArrayList<String> picturePath = new ArrayList<>();
            ArrayList<String> pictureTitle = new ArrayList<>();
            List<PathTitleBean> path_all = mDetailInfoBean_Q.getPath_and_title();

            if (path_all != null) {
                for (int i = 0; i < path_all.size(); i++) {
                    picturePath.add(path_all.get(i).getPath());
                    pictureTitle.add(path_all.get(i).getTitle());
                }
            }

            supportDetail.setPicturePath(picturePath);
            supportDetail.setPictureTitle(pictureTitle);

            supportDetail.save();

            //保存数据到表SupportChecked
            SupportChecked supportChecked = new SupportChecked();
            supportChecked.setLite_ID(liteId);
            supportChecked.setIsRoom(mCheckedBean_Q.getIsRoom());
            supportChecked.setIsFree(mCheckedBean_Q.getIsFree());
            supportChecked.setConclusion(mCheckedBean_Q.getConclusion());
            supportChecked.setDescription(mCheckedBean_Q.getDescription());
            supportChecked.setSiteChecks(mCheckedBean_Q.getSiteChecks());
            supportChecked.setSiteLogin(mCheckedBean_Q.getSiteLogin());
            supportChecked.save();

            if (GlobalManager.TYPE_DRAFT_LITE.equals(lite_type)) {
                SupportMedia supportMedia = new SupportMedia();
//                ArrayList<String> photoTypes = new ArrayList<>();
                ArrayList<String> paths = new ArrayList<>();
                ArrayList<String> pictureTypes = new ArrayList<>();
                ArrayList<Long> mDurations = new ArrayList<>();
                ArrayList<Integer> nums = new ArrayList<>();
                ArrayList<Integer> mimeTypes = new ArrayList<>();
                ArrayList<Integer> widths = new ArrayList<>();
                ArrayList<Integer> heights = new ArrayList<>();
                ArrayList<Integer> positions = new ArrayList<>();
                if (mLocalMedias_Photo != null && mLocalMedias_Photo.size() != 0) {
                    for (int i = 0; i < mLocalMedias_Photo.size(); i++) {
                        LocalMedia media = mLocalMedias_Photo.get(i);
                        paths.add(media.getPath());
                        pictureTypes.add(media.getPictureType());
                        mDurations.add(media.getDuration());
                        nums.add(media.getNum());
                        heights.add(media.getHeight());
                        widths.add(media.getWidth());
                        positions.add(media.getPosition());
                        mimeTypes.add(media.getMimeType());
                    }
                }
//

                supportMedia.setPaths(paths);
                supportMedia.setPictureTypes(pictureTypes);
                supportMedia.setDurations(mDurations);
                supportMedia.setNums(nums);
                supportMedia.setHeights(heights);
                supportMedia.setWidths(widths);
                supportMedia.setMimeTypes(mimeTypes);
                supportMedia.setPositions(positions);
                supportMedia.setLite_ID(liteId);
                supportMedia.save();

                support.setSupportMedia(supportMedia);
            }

            support.setSupportScan(supportScan);
            support.setSupportDetail(supportDetail);
            support.setSupportChecked(supportChecked);
            support.setUsername(mUsername);

            support.save();

            if (action.equals(ACTION_SUBMIT)) {
                if (GlobalManager.TYPE_DRAFT_LITE.equals(lite_type)) {
//                    SPUtils.add_one(sActivity, SPUtils.MATH_DRAFT_LITE);
                    ToastUtils.singleToast("提交失败并保存至草稿列表");
                } else if (GlobalManager.TYPE_SUBMIT_LITE.equals(lite_type)) {
//                    SPUtils.add_one(sActivity, SPUtils.MATH_SUBMIT_LITE);
                    ToastUtils.singleToast("提交成功并保存至提交列表");
                }
                List<SupportDraftOrSubmit> drafts = DataSupport.where("username = ? and lite_type = ?", mUsername, GlobalManager.TYPE_DRAFT_LITE).find(SupportDraftOrSubmit.class);
                List<SupportDraftOrSubmit> submits = DataSupport.where("username = ? and lite_type = ?", mUsername, GlobalManager.TYPE_SUBMIT_LITE).find(SupportDraftOrSubmit.class);

                Intent intent = new Intent("com.example.updateUI");
                intent.putExtra(ARG_BROADCAST_DRAFT, drafts.size());
                intent.putExtra(ARG_BROADCAST_SUBMIT, submits.size());
                sendBroadcast(intent);
            } else if (action.equals(ACTION_SAVE)) {
                ToastUtils.singleToast("已保存至草稿列表");
//                SPUtils.add_one(sContext_draft, SPUtils.MATH_DRAFT_LITE);
            }
        } else if (GlobalManager.TYPE_DRAFT_ENTER_SHOW.equals(showType)) {
//            count = mLite_id;
//            Logger.i(count + "-------------count");

            SupportDraftOrSubmit draftOrSubmit = DataSupport.where("lite_ID = ?"
                    , String.valueOf(mLite_id)).findFirst(SupportDraftOrSubmit.class);

            support.setUsername(mUsername);
            support.setLite_type(lite_type);
            support.setCurrent_time(draftOrSubmit.getCurrent_time());
            support.setCurrent_date(draftOrSubmit.getCurrent_date());
            support.setCurrentShift(currentShift);

            //保存数据到表SupportScan
            SupportScan supportScan = new SupportScan();
            supportScan.setScan_05Q(mScanInfoBean_Q.getScan_05Q());
            supportScan.setScan_10Q(mScanInfoBean_Q.getScan_10Q());
            supportScan.setIsLimit(mScanInfoBean_Q.getIsLimit());
            supportScan.updateAll("lite_ID = ?", String.valueOf(mLite_id));

            //保存数据到表SupportDetail
            SupportDetail supportDetail = new SupportDetail();
            supportDetail.setNumber(mDetailInfoBean_Q.getNumber());
            supportDetail.setGoods(mDetailInfoBean_Q.getGoods());
            supportDetail.setDetail_weight(mDetailInfoBean_Q.getText_weight());
            supportDetail.setDetail_free(mDetailInfoBean_Q.getText_free());
            supportDetail.setDetail_export(mDetailInfoBean_Q.getText_export());
            supportDetail.setDetail_carType(mDetailInfoBean_Q.getText_carType());
            supportDetail.setStation(mStation_Q);
            supportDetail.setRoad(mRoad_Q);
            supportDetail.setLane(mDetailInfoBean_Q.getText_export());

            ArrayList<String> picturePath = new ArrayList<>();
            ArrayList<String> pictureTitle = new ArrayList<>();
            List<PathTitleBean> path_all = mDetailInfoBean_Q.getPath_and_title();

            if (path_all != null) {
                for (int i = 0; i < path_all.size(); i++) {
                    picturePath.add(path_all.get(i).getPath());
                    pictureTitle.add(path_all.get(i).getTitle());
                }
            }

            supportDetail.setPicturePath(picturePath);
            supportDetail.setPictureTitle(pictureTitle);

            //supportDetail.save();
            supportDetail.updateAll("lite_ID = ?", String.valueOf(mLite_id));

            //保存数据到表SupportChecked
            SupportChecked supportChecked = new SupportChecked();
            // supportChecked.setLite_ID(count);
            supportChecked.setIsRoom(mCheckedBean_Q.getIsRoom());
            supportChecked.setIsFree(mCheckedBean_Q.getIsFree());
            supportChecked.setConclusion(mCheckedBean_Q.getConclusion());
            supportChecked.setDescription(mCheckedBean_Q.getDescription());
            supportChecked.setSiteChecks(mCheckedBean_Q.getSiteChecks());
            supportChecked.setSiteLogin(mCheckedBean_Q.getSiteLogin());
            //supportChecked.save();
            supportChecked.updateAll("lite_ID = ?", String.valueOf(mLite_id));

            if (GlobalManager.TYPE_DRAFT_LITE.equals(lite_type)) {
                SupportMedia supportMedia = new SupportMedia();
//                ArrayList<String> photoTypes = new ArrayList<>();
                ArrayList<String> paths = new ArrayList<>();
                ArrayList<String> pictureTypes = new ArrayList<>();
                ArrayList<Long> mDurations = new ArrayList<>();
                ArrayList<Integer> nums = new ArrayList<>();
                ArrayList<Integer> mimeTypes = new ArrayList<>();
                ArrayList<Integer> widths = new ArrayList<>();
                ArrayList<Integer> heights = new ArrayList<>();
                ArrayList<Integer> positions = new ArrayList<>();
                if (mLocalMedias_Photo != null && mLocalMedias_Photo.size() != 0) {
                    for (int i = 0; i < mLocalMedias_Photo.size(); i++) {
                        LocalMedia media = mLocalMedias_Photo.get(i);
                        paths.add(media.getPath());
                        pictureTypes.add(media.getPictureType());
                        mDurations.add(media.getDuration());
                        nums.add(media.getNum());
                        heights.add(media.getHeight());
                        widths.add(media.getWidth());
                        positions.add(media.getPosition());
                        mimeTypes.add(media.getMimeType());
                    }
                }

                supportMedia.setPaths(paths);
                supportMedia.setPictureTypes(pictureTypes);
                supportMedia.setDurations(mDurations);
                supportMedia.setNums(nums);
                supportMedia.setHeights(heights);
                supportMedia.setWidths(widths);
                supportMedia.setMimeTypes(mimeTypes);
                supportMedia.setPositions(positions);
                supportMedia.updateAll("lite_ID=?", String.valueOf(mLite_id));

                support.setSupportMedia(supportMedia);
            }

            support.setSupportScan(supportScan);
            support.setSupportDetail(supportDetail);
            support.setSupportChecked(supportChecked);

            //  support.save();
            support.updateAll("lite_ID = ?", String.valueOf(mLite_id));

            if (action.equals(ACTION_SUBMIT)) {
                if (GlobalManager.TYPE_DRAFT_LITE.equals(lite_type)) {
                    //SPUtils.add_one(sActivity, SPUtils.MATH_DRAFT_LITE);
                    ToastUtils.singleToast("提交失败并更新至草稿列表");
                } else if (GlobalManager.TYPE_SUBMIT_LITE.equals(lite_type)) {
//                    SPUtils.add_one(sActivity, SPUtils.MATH_SUBMIT_LITE);
//                    SPUtils.cut_one(sActivity, SPUtils.MATH_DRAFT_LITE);
                    ToastUtils.singleToast("提交成功并更新至提交列表");
                }
                List<SupportDraftOrSubmit> drafts = DataSupport.where("username = ? and lite_type = ?", mUsername, GlobalManager.TYPE_DRAFT_LITE).find(SupportDraftOrSubmit.class);
                List<SupportDraftOrSubmit> submits = DataSupport.where("username = ? and lite_type = ?", mUsername, GlobalManager.TYPE_SUBMIT_LITE).find(SupportDraftOrSubmit.class);

                Intent intent = new Intent("com.example.updateUI");
                intent.putExtra(ARG_BROADCAST_DRAFT, drafts.size());
                intent.putExtra(ARG_BROADCAST_SUBMIT, submits.size());

                sendBroadcast(intent);
            } else if (action.equals(ACTION_SAVE)) {
                ToastUtils.singleToast("已更新至草稿列表");
                //  SPUtils.add_one(sContext_draft, SPUtils.MATH_DRAFT_LITE);
            }
        }

    }

    private void postPictureAndJson(String postTime, int liteId, String currentShift) {

        PostInfo info = new PostInfo();


        info.setLiteId(liteId);

        info.setCurrentShift(currentShift);

        info.setNumber(mDetailInfoBean_Q.getNumber());
        info.setGoods(mDetailInfoBean_Q.getGoods());

        info.setScan_01Q(mDetailInfoBean_Q.getNumber());
        info.setScan_04Q(mDetailInfoBean_Q.getText_weight());
        info.setScan_06Q(mDetailInfoBean_Q.getText_free());
        info.setScan_12Q(mDetailInfoBean_Q.getText_export());


        // 从主界面拿到的信息
        info.setRoad(mRoad_Q);
        info.setStation(mStation_Q);
        info.setLane(mDetailInfoBean_Q.getText_export());
        info.setCarType(mDetailInfoBean_Q.getText_carType());

        //从checkedFragment中拿到的数据
        List<String> siteChecks = mCheckedBean_Q.getSiteChecks();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < siteChecks.size(); i++) {
            if (i == 0) {
                builder.append(siteChecks.get(i));
            } else {
                builder.append("-" + siteChecks.get(i));
            }
        }
        info.setIsFree(mCheckedBean_Q.getIsFree());
        info.setIsRoom(mCheckedBean_Q.getIsRoom());
        info.setConclusion(mCheckedBean_Q.getConclusion());
        info.setDescription(mCheckedBean_Q.getDescription());
        info.setSiteCheck(builder.toString());
        info.setSiteLogin(mCheckedBean_Q.getSiteLogin());


        //扫描的到的信息
        info.setScan_05Q(mScanInfoBean_Q.getScan_05Q());
        info.setScan_10Q(mScanInfoBean_Q.getScan_10Q());
        info.setLimit(mScanInfoBean_Q.getIsLimit());
        info.setCurrent_time(postTime);


        if (info.getNumber() == null || info.getNumber().length() < 7) {
            Logger.i(info.getNumber() + info.getNumber().length());
            if (!"无".equals(info.getNumber().toString().trim())) {
                ToastUtils.singleToast("车牌号的长度至少7位");
                return;
            }
        }

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
//        if (mMustList.contains("称重质量")) {
//            if (info.getScan_04Q() == null || "".equals(info.getScan_04Q())) {
//                ToastUtils.singleToast("请确定称重质量");
//                return;
//            }
////            mDetailTextWeight.setFocusable(false);
////            mDetailTextWeight.setFocusableInTouchMode(false);
//        } else {
//            mDetailTextWeight.addTextChangedListener(new TextChangeWatcher(editable -> {
//                notifyScanWeightChange(String.valueOf(editable));
//            }));
//        }

        if (mMustList.contains("称重质量")&&(info.getScan_04Q() == null || "".equals(info.getScan_04Q()))) {
            ToastUtils.singleToast("请确定称重质量");
            return;
        }
        if (mMustList.contains("免费金额")&&(info.getScan_06Q() == null || "".equals(info.getScan_06Q()))) {
            ToastUtils.singleToast("请确定免费金额");
            return;
        }

        if (mMustList.contains("出口车道")&&(info.getScan_12Q() == null || "".equals(info.getScan_12Q()))) {

            ToastUtils.singleToast("请确定出口车道");
            return;
        }
        if (mMustList.contains("车型")&&(info.getCarType() == null || "".equals(info.getCarType()))) {
            ToastUtils.singleToast("请确定车型");
            return;
        }

        if (info.getGoods() == null || "".equals(info.getGoods())) {
            ToastUtils.singleToast("请确定货物名称");
            return;
        }
        if (info.getSiteCheck() == null || "".equals(info.getSiteCheck())) {
            ToastUtils.singleToast("请确定现场检查人");
            return;
        }
        if (info.getConclusion() == null || "".equals(info.getConclusion())) {
            ToastUtils.singleToast("请确定选择检查结论");
            return;
        }


        ArrayList<PathTitleBean> pathTitle_sanzheng = new ArrayList<>();
//        ArrayList<PathTitleBean> pathTitle_cheshen = new ArrayList<>();
//        ArrayList<PathTitleBean> pathTitle_huozhao = new ArrayList<>();
        for (int i = 0; i < mDetailInfoBean_Q.getPath_and_title().size(); i++) {
            String photo_type = mDetailInfoBean_Q.getPath_and_title().get(i).getPhoto_type();
            if (GlobalManager.PHOTO_TYPE_SANZHENG.equals(photo_type)) {
                pathTitle_sanzheng.add(mDetailInfoBean_Q.getPath_and_title().get(i));
            }
        }

        List<MultipartBody.Part> sanzheng = null;

        if (pathTitle_sanzheng == null || pathTitle_sanzheng.size() < 3) {
            ToastUtils.singleToast("请拍摄或选择三张照片");
            return;
        }


        sActivity.notifyDataChangeAndFinish();
        sActivity.notifyActivityFinish();
        Intent intent = new Intent(sActivity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


        sanzheng = getBodyPart1(pathTitle_sanzheng, "sanzheng");


        Gson gson = new Gson();
        String route = gson.toJson(info);
        Logger.i(route);

        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), route);
        Logger.i("json  string" + route);

        List<MultipartBody.Part> finalSanzheng = sanzheng;

        Subscriber<HttpResultCode> subscriber_json = new Subscriber<HttpResultCode>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.i(e.getMessage());
                save2Litepal(mSubmitTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SUBMIT, mShowType_submit, currentShift);
                Logger.i("json未上传成功走了草稿保存的方法");
                ToastUtils.singleToast("提交失败,已保存至草稿");
            }

            @Override
            public void onNext(HttpResultCode httpResultCode) {
                int code = httpResultCode.getCode();
                Logger.i(code + "----json");
                if (code == 200) {
                    Logger.i("json上传成功");

                    RequestPicture.getInstance().postPicture(new Subscriber<HttpResultCode>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Logger.i(e.getMessage());
                            save2Litepal(mSubmitTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SUBMIT, mShowType_submit, currentShift);
                            Logger.i("json上传成功图片未上传成功走了草稿保存的方法");
                            ToastUtils.singleToast("提交失败,已保存至草稿");
                        }

                        @Override
                        public void onNext(HttpResultCode httpResultCode) {
                            int code = httpResultCode.getCode();
                            Logger.i(code + "----photo");

                            if (code == 200) {
                                // ToastUtils.singleToast("图片上传成功");
                                Logger.i("图片上传成功");
                                save2Litepal(mSubmitTime, liteId, GlobalManager.TYPE_SUBMIT_LITE, ACTION_SUBMIT, mShowType_submit, currentShift);
                                Logger.i("走了提交保存的方法");
                            } else {
                                save2Litepal(mSubmitTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SUBMIT, mShowType_submit, currentShift);
                                Logger.i("图片上传返回错误走了草稿保存的方法");
                            }
                        }
//                    }, postTime, finalSanzheng, finalCheshen, finalHuozhao);
                    }, postTime, finalSanzheng);

                } else {
                    Logger.i("json上传返回错误走了草稿保存的方法");
                    save2Litepal(mSubmitTime, liteId, GlobalManager.TYPE_DRAFT_LITE, ACTION_SUBMIT, mShowType_submit, currentShift);
                }
                //  ToastUtils.singleToast("json上传成功");
            }
        };
        RequestJson.getInstance().postJson(subscriber_json, body);

    }

    public static List<MultipartBody.Part> getBodyPart1(List<PathTitleBean> bitmapList, String type) {

        MultipartBody.Builder builder = new MultipartBody.Builder();


        for (int i = 0; i < bitmapList.size(); i++) {

            String mFilePath_str_new = null;
            try {
                Bitmap bitmap = BitmapUtil.convertToBitmap(bitmapList.get(i).getPath(), 700, 960);

                mFilePath_str_new = mFilePath_str + "/" + TimeUtil.getCurrentTime()
                        + type + (i + 1) + ".jpg";

                saveFile(bitmap, mFilePath_str_new);

            } catch (Exception e) {
                e.printStackTrace();

            }

            //String path = bitmapList.get(i).getPath();
            if (mFilePath_str_new != null) {
                Logger.i(mFilePath_str_new);
                File file = new File(mFilePath_str_new);//filePath 图片地址
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);//image/png
                //RequestBody imageBody = RequestBody.create(MediaType.parse("image/jpg"), file);//image/png
                builder.addFormDataPart(type + (i + 1), file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }

    //    存储进SD卡
    public static void saveFile(Bitmap bm, String fileName) throws Exception {
        File dirFile = new File(fileName);
        //检测图片是否存在
        if (dirFile.exists()) {
            dirFile.delete();  //删除原图片
        }
        File myCaptureFile = new File(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        //100表示不进行压缩，70表示压缩率为30%
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }


}
