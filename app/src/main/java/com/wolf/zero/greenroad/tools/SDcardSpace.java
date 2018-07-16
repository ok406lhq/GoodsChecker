package com.wolf.zero.greenroad.tools;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by Administrator on 2017/6/21.
 */

public class SDcardSpace {

    public Context mContext;
    public  String path = Environment.getExternalStorageDirectory()
            .getAbsolutePath();
    public  SDcardSpace mSDcardSpace;

    public SDcardSpace(Context context) {
        mContext = context;
    }


    public String getAvailSpace() {
        StatFs statFs = new StatFs(path);
        long blockSize;
        long availableBlocks;

        // 由于API18（Android4.3）以后getBlockSize过时并且改为了getBlockSizeLong
// 因此这里需要根据版本号来使用那一套API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
        {
            blockSize = statFs.getBlockSizeLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        }
        else {
            blockSize = statFs.getBlockSize();
            availableBlocks = statFs.getAvailableBlocks();
        }
        long result = blockSize * availableBlocks;
        String  availableSize = Formatter.formatFileSize(mContext, result);
        return availableSize;
    }

    /**
     * 获得SD卡总大小
     *
     * @return
     */
    public String getSDTotalSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(context, blockSize * totalBlocks);
    }

}
