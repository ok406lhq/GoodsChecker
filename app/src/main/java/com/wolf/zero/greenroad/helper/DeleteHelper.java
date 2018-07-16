package com.wolf.zero.greenroad.helper;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.litepalbean.SupportChecked;
import com.wolf.zero.greenroad.litepalbean.SupportDetail;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;
import com.wolf.zero.greenroad.litepalbean.SupportMedia;
import com.wolf.zero.greenroad.litepalbean.SupportScan;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.tools.SPUtils;
import com.wolf.zero.greenroad.tools.TimeUtil;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class DeleteHelper {

    private static DeleteHelper sHelper;
    private File mFile;

    public static DeleteHelper getInstance() {
        if (sHelper == null) {
            sHelper = new DeleteHelper();
        }
        return sHelper;

    }

    /**
     * 清除所有的记录
     *
     * @param context
     * @param typeLite
     */

    public void deleteAllInfos(Context context, String typeLite, DataChangeListener listener) {
        String username = (String) SPUtils.get(context, GlobalManager.USERNAME, "qqqq");
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("清空本地保存的草稿数据");
        dialog.setMessage("点击“确定”将删除所有采集记录" + "\"" +
                "点击“取消”将取消删除操作");
        dialog.setCancelable(false);
        dialog.setPositiveButton(context.getString(R.string.dialog_messge_OK), (dialog1, which) -> {

            DataSupport.deleteAll(SupportDraftOrSubmit.class, GlobalManager.LITE_CONDITION, username, typeLite);
            if (GlobalManager.TYPE_SUBMIT_LITE.equals(typeLite)) {
                deleteAllNewPhoto();
            }
//            SPUtils.putAndApply(context, SpUtil_info, 0);
            List<SupportDraftOrSubmit> supportList =
                    DataSupport.where(GlobalManager.LITE_CONDITION, username, typeLite).find(SupportDraftOrSubmit.class);
            listener.dataChange(supportList);

        });
        dialog.setNegativeButton(context.getString(R.string.dialog_message_Cancel), (dialog1, which) -> {
            dialog1.dismiss();
        });
        dialog.show();
    }

    /**
     * 清除几天之前的记录
     */
    public void deleteInfos(Context context, String typeLite, int day, DataChangeListener listener) {
        String username = (String) SPUtils.get(context, GlobalManager.USERNAME, "qqqq");

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("清除" + day + "天之前本地保存的拍摄数据");
        dialog.setMessage("点击“确定”将删除" + day + "天以前的拍摄记录" + "\"" +
                "点击“取消”将取消删除操作");
        dialog.setCancelable(false);
        dialog.setPositiveButton(context.getString(R.string.dialog_messge_OK), (dialog1, which) -> {
            String currentTimeToDate = TimeUtil.getCurrentTimeToDate();
            String currentTimeToDelete = TimeUtil.getCurrentTime();
            List<SupportDraftOrSubmit> photoLiteList = DataSupport.where(GlobalManager.LITE_CONDITION, username, typeLite).
                    find(SupportDraftOrSubmit.class);

            for (int i = 0; i < photoLiteList.size(); i++) {
                String saveTime = photoLiteList.get(i).getCurrent_time();
                int lite_id = photoLiteList.get(i).getLite_ID();
                int dayGap = TimeUtil.differentDaysByMillisecond(saveTime, currentTimeToDate);
                if (dayGap > day) {
                    DataSupport.deleteAll(SupportDraftOrSubmit.class, "lite_ID = ?", String.valueOf(lite_id));
                    DataSupport.deleteAll(SupportDetail.class, "lite_ID = ?", String.valueOf(lite_id));
                    DataSupport.deleteAll(SupportScan.class, "lite_ID = ?", String.valueOf(lite_id));
                    DataSupport.deleteAll(SupportChecked.class, "lite_ID = ?", String.valueOf(lite_id));
                    DataSupport.deleteAll(SupportMedia.class, "lite_ID = ?", String.valueOf(lite_id));
                }
            }

            if (GlobalManager.TYPE_SUBMIT_LITE.equals(typeLite)) {
                deletePhotoForData(day, currentTimeToDelete);
            }
            List<SupportDraftOrSubmit> supportDraftList = DataSupport.
                    where(GlobalManager.LITE_CONDITION, username, typeLite).find(SupportDraftOrSubmit.class);
            SortTime sortDraftTime = new SortTime();
            Collections.sort(supportDraftList, sortDraftTime);
            listener.dataChange(supportDraftList);
//            SPUtils.putAndApply(context, SpUtil_info, supportDraftList.size());
        });
        dialog.setNegativeButton(context.getString(R.string.dialog_message_Cancel), (dialog1, which) -> {
            dialog1.dismiss();
        });
        dialog.show();


    }

    private void deletePhotoForData(int day, String currentTimeToDelete) {
        Logger.i("删除提交的照片1");
        if (mFile == null) {
            mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "GreenPicture");
            mFile.mkdirs();
        }
        DeleteFileForDay(mFile, day, currentTimeToDelete);
    }

    private void DeleteFileForDay(File file, int day, String currentTimeToDelete) {
        if (file.exists() == false) {
            return;
        } else {
            if (file.isFile()) {
                Logger.i("删除提交的照片2");
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                for (File f : childFile) {
                    String submitTime = f.getName().substring(0, 14);
                    int dayGap = TimeUtil.differentDaysByTime(submitTime, currentTimeToDelete);
                    if (dayGap > day) {
                        Logger.i("删除提交的照片4");
//                        DeleteFileForDay(f,day,currentTimeToDelete);
                        f.delete();
                    }
                }
            }
        }
    }

    private void deleteAllNewPhoto() {
        Logger.i("删除提交的照片1");
        if (mFile == null) {
            mFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "GreenPicture");
            mFile.mkdirs();
        }
        DeleteFile(mFile);
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public void DeleteFile(File file) {
        if (file.exists() == false) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    DeleteFile(f);
                }
            }
        }
    }

    public interface DataChangeListener {
        void dataChange(List<SupportDraftOrSubmit> supportList);
    }
}
