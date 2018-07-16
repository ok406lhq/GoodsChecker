package com.wolf.zero.greenroad.servicy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.httpresultbean.HttpResultPolling;
import com.wolf.zero.greenroad.https.RequestPolling;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;
import com.wolf.zero.greenroad.manager.GlobalManager;
import com.wolf.zero.greenroad.tools.SPUtils;
import com.wolf.zero.greenroad.tools.TimeUtil;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rx.Subscriber;


public class PollingService extends Service {
    private Timer mTimer;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    HttpResultPolling.DataBean dataBean = (HttpResultPolling.DataBean) message.obj;
                    Logger.i("message=1" + dataBean.toString());
                    handleNotify(dataBean, 1);
                    break;

                case 2:

                    HttpResultPolling.DataBean dataBean1 = (HttpResultPolling.DataBean) message.obj;
                    Logger.i("message=1" + dataBean1.toString());

                    handleNotify(dataBean1, 2);
//                    SupportDraftOrSubmit submitData1 = DataSupport.where("username = ? and lite_ID = ?", mUsername, dataBean1.getLite_ID() + "").findFirst(SupportDraftOrSubmit.class);
//
//
//                    Intent notifyIntent1 = new Intent(getApplicationContext(), ShowNotificationActivity.class);
//                    notifyIntent1.putExtra("notifyData", submitData1);
//
//                    notifyIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                    PendingIntent notifyPendingIntent1 =
//                            PendingIntent.getActivity(getApplicationContext(), dataBean1.getLite_ID(), notifyIntent1,
//                                    PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//                    android.support.v7.app.NotificationCompat.Builder builder1 = (NotificationCompat.Builder) new NotificationCompat.Builder(mContext)
//                            .setSmallIcon(R.drawable.ic_launcher)
//                            .setContentTitle(submitData1.getSupportDetail().getDetail_carType() + submitData1.getSupportDetail().getNumber() + "审核失败")
//                            .setContentText("失败原因......" + dataBean1.getMessage())
//                            .setAutoCancel(true)
//                            .setOnlyAlertOnce(true)
//                            // 需要VIBRATE权限
//                            .setDefaults(Notification.DEFAULT_VIBRATE)
//                            .setDefaults(Notification.DEFAULT_SOUND)
//                            .setPriority(Notification.PRIORITY_DEFAULT)
//                            .setContentIntent(notifyPendingIntent1);
//                    ;
//
//                    NotificationManager notificationManager1 = (NotificationManager) mContext
//                            .getSystemService(Context.NOTIFICATION_SERVICE);
//                    notificationManager1.notify(dataBean1.getLite_ID(), builder1.build());

                    break;
            }
            return false;
        }

        /**
         * 上传成功与失败所做处理差不多,简单的进行封装
         * @param dataBean
         * @param msgID
         */
        private void handleNotify(HttpResultPolling.DataBean dataBean, int msgID) {
            SupportDraftOrSubmit submitData = DataSupport.where("username = ? and lite_ID = ?", mUsername, dataBean.getLite_ID() + "").findFirst(SupportDraftOrSubmit.class);
            Intent notifyIntent = new Intent(getApplicationContext(), ShowNotificationActivity.class);
            notifyIntent.putExtra("notifyData", submitData);
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent notifyPendingIntent =
                    PendingIntent.getActivity(getApplicationContext(), dataBean.getLite_ID(), notifyIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(submitData.getSupportDetail().getDetail_carType() + " "+submitData.getSupportDetail().getNumber() + (msgID == 1 ? "---审核通过" : "---审核失败"))
                    .setContentText(msgID == 1 ? "上传时间:" + submitData.getCurrent_time()
                    :"失败原因......" + dataBean.getMessage())
                    .setAutoCancel(true)
                    .setOnlyAlertOnce(true)
                    // 需要VIBRATE权限
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setContentIntent(notifyPendingIntent);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(dataBean.getLite_ID(), builder.build());

        }
    });
    private Context mContext;
    private String mUsername;

    @Override
    public void onCreate() {
        Logger.i("TestService -> onCreate, Thread ID: " + Thread.currentThread().getId());
        super.onCreate();


        mUsername = (String) SPUtils.get(this, SPUtils.lOGIN_USERNAME, "qqqq");
        mUsername = (String) SPUtils.get(this, GlobalManager.USERNAME, "qqqq");

        mContext = this;
        mTimer = new Timer();
        Task task = new Task();
        //schedule 计划安排，时间表 period
        mTimer.schedule(task, 5 * 1000, 8 * 1000);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Logger.i("TestService -> onStartCommand, startId: " + startId + ", Thread ID: " + Thread.currentThread().getId());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.i("TestService -> onBind, Thread ID: " + Thread.currentThread().getId());
        return null;
    }

    @Override
    public void onDestroy() {
//        Logger.i("TestService -> onDestroy, Thread ID: " + Thread.currentThread().getId());
        super.onDestroy();
        Logger.i("当前服务中的用户onDestroy" + mUsername);

        Logger.i("PollingService onDestroy");
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    public class Task extends TimerTask {
        @Override
        public void run() {
//            String currentTime = TimeUtil.getCurrentTimeToDate();
//
//            int lastTime = TimeUtil.getLastTime(currentTime);
//            Logger.i("lastTime=" + lastTime + "-----" + "currentTime=" + currentTime);
//
//            String current_time = DataSupport.findFirst(SupportDraftOrSubmit.class).getCurrent_time();
//            Logger.i(current_time);

//            Logger.i("当前服务中的用户Task" + mUsername);

            List<SupportDraftOrSubmit> submitList = DataSupport.where("username = ? and current_date = ? and lite_type = ? and isPass = ?",
                    mUsername, TimeUtil.getStringDateShort(), GlobalManager.TYPE_SUBMIT_LITE, "0").find(SupportDraftOrSubmit.class);

            if (submitList.size() != 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < submitList.size(); i++) {
                    if (i < submitList.size() - 1) {
                        buffer.append(submitList.get(i).getLite_ID() + ",");
                    } else {
                        buffer.append(submitList.get(i).getLite_ID() + "");

                    }
                }
                String pollingStr = buffer.toString();
                Logger.i("提交的待审核的数据..." + submitList.size() + pollingStr);

                Subscriber<HttpResultPolling> subscriber = new Subscriber<HttpResultPolling>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("错误" + e.getMessage());
                    }

                    @Override
                    public void onNext(HttpResultPolling httpResultPolling) {
                        Logger.i(httpResultPolling.toString());

                        int code = httpResultPolling.getCode();
//                        List<Integer> liteIdList = httpResultPolling.getData();
                        if (code == 200) {
                            //当天提交的未审核通过的车辆

                            List<HttpResultPolling.DataBean> dataBeanList = httpResultPolling.getData();

                            for (int i = 0; i < dataBeanList.size(); i++) {
                                if (dataBeanList.get(i).getIsPass() == 0) {
                                    Logger.i("审核中...." + dataBeanList.get(i).toString());
                                    Logger.i("审核中....");
                                } else if (dataBeanList.get(i).getIsPass() == 1) {
                                    Logger.i("审核通过...." + dataBeanList.get(i).toString());
//                                    SupportDraftOrSubmit supportSubmit = new SupportDraftOrSubmit();
//                                    supportSubmit.setCurrent_date(submitList.get(i).getCurrent_date());
//                                    supportSubmit.setCurrent_time(submitList.get(i).getCurrent_time());
//                                    supportSubmit.setUsername(submitList.get(i).getUsername());
//                                    supportSubmit.setLite_type(submitList.get(i).getLite_type());
//                                    supportSubmit.setSupportChecked(submitList.get(i).getSupportChecked());
//                                    supportSubmit.setSupportDetail(submitList.get(i).getSupportDetail());
//                                    supportSubmit.setSupportMedia(submitList.get(i).getSupportMedia());
//                                    supportSubmit.setSupportScan(submitList.get(i).getSupportScan());
//                                    supportSubmit.setIsPass(1);
//                                    supportSubmit.setLite_ID(submitList.get(i).getLite_ID());
//                                    supportSubmit.updateAll("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), dataBeanList.get(i).getLite_ID() + "");

                                    SupportDraftOrSubmit submit1 = DataSupport.where("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), dataBeanList.get(i).getLite_ID() + "").findFirst(SupportDraftOrSubmit.class);
                                    Logger.i("submit111111........" + submit1);
                                    submit1.setIsPass(1);
                                    submit1.save();
                                    SupportDraftOrSubmit submit2 = DataSupport.where("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), dataBeanList.get(i).getLite_ID() + "").findFirst(SupportDraftOrSubmit.class);
                                    Logger.i("submit222222........" + submit2);

//                                    Logger.i("当前审核成功的车辆" + supportSubmit.toString());

                                    Message message = Message.obtain();
                                    message.what = 1;
                                    message.obj = dataBeanList.get(i);
                                    mHandler.sendMessage(message);
                                    Log.e("AAA", "开始执行执行timer定时任务...");

                                } else {
                                    Logger.i("审核失败...." + dataBeanList.get(i).toString());
//                                    SupportDraftOrSubmit supportSubmit = new SupportDraftOrSubmit();
//                                    supportSubmit.setCurrent_date(submitList.get(i).getCurrent_date());
//                                    supportSubmit.setCurrent_time(submitList.get(i).getCurrent_time());
//                                    supportSubmit.setUsername(submitList.get(i).getUsername());
//                                    supportSubmit.setLite_type(submitList.get(i).getLite_type());
//                                    supportSubmit.setSupportChecked(submitList.get(i).getSupportChecked());
//                                    supportSubmit.setSupportDetail(submitList.get(i).getSupportDetail());
//                                    supportSubmit.setSupportMedia(submitList.get(i).getSupportMedia());
//                                    supportSubmit.setSupportScan(submitList.get(i).getSupportScan());
//                                    supportSubmit.setMessage(dataBeanList.get(i).getMessage());
//                                    supportSubmit.setIsPass(2);
//                                    supportSubmit.setLite_ID(submitList.get(i).getLite_ID());
//                                    supportSubmit.updateAll("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), dataBeanList.get(i).getLite_ID() + "");

//                                    Logger.i("当前审核失败的车辆" + supportSubmit.toString());

                                    SupportDraftOrSubmit submit1 = DataSupport.where("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), dataBeanList.get(i).getLite_ID() + "").findFirst(SupportDraftOrSubmit.class);
                                    Logger.i("submit111111........" + submit1);
                                    submit1.setIsPass(2);
                                    submit1.setMessage(dataBeanList.get(i).getMessage());
                                    submit1.save();
                                    SupportDraftOrSubmit submit2 = DataSupport.where("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), dataBeanList.get(i).getLite_ID() + "").findFirst(SupportDraftOrSubmit.class);
                                    Logger.i("submit222222........" + submit2);

                                    Message message = Message.obtain();
                                    message.what = 2;
                                    message.obj = dataBeanList.get(i);
                                    mHandler.sendMessage(message);
                                    Log.e("BBB", "开始执行执行timer定时任务...");

                                }
                            }
                            List<SupportDraftOrSubmit> submitList = DataSupport.where("username = ? and current_date = ? and lite_type = ? and isPass = ?",
                                    mUsername, TimeUtil.getStringDateShort(), GlobalManager.TYPE_SUBMIT_LITE, "0").find(SupportDraftOrSubmit.class);

                            Logger.i("submitList.size()" + submitList.size() + "");

                            for (int i = 0; i < submitList.size(); i++) {
                                Logger.i(submitList.get(i).toString());
                            }

//                            for (int i = 0; i < submitList.size(); i++) {
//
//                                for (int j = 0; j < liteIdList.size(); j++) {
//                                    Logger.i("liteIdList.size()" + liteIdList.size() + "11111");
//
//                                    if (submitList.get(i).getLite_ID() == liteIdList.get(j)) {
//                                        Logger.i("走了多少遍");
//                                        SupportDraftOrSubmit supportSubmit = new SupportDraftOrSubmit();
//                                        supportSubmit.setCurrent_date(submitList.get(i).getCurrent_date());
//                                        supportSubmit.setCurrent_time(submitList.get(i).getCurrent_time());
//                                        supportSubmit.setUsername(submitList.get(i).getUsername());
//                                        supportSubmit.setLite_type(submitList.get(i).getLite_type());
//                                        supportSubmit.setSupportChecked(submitList.get(i).getSupportChecked());
//                                        supportSubmit.setSupportDetail(submitList.get(i).getSupportDetail());
//                                        supportSubmit.setSupportMedia(submitList.get(i).getSupportMedia());
//                                        supportSubmit.setSupportScan(submitList.get(i).getSupportScan());
//                                        supportSubmit.setIsPass(1);
//                                        supportSubmit.setLite_ID(submitList.get(i).getLite_ID());
//                                        supportSubmit.updateAll("current_date = ? and lite_ID = ?", TimeUtil.getStringDateShort(), liteIdList.get(j) + "");
//
//                                    }
//                                }
//
//                            }


//                            Logger.i("liteIdList.size()" + liteIdList.size() + "22222");
//                            for (int i = 0; i < liteIdList.size(); i++) {
//                                List<SupportDraftOrSubmit> submits = DataSupport.where("lite_ID = ? and lite_type = ?", liteIdList.get(i) + "", GlobalManager.TYPE_SUBMIT_LITE).find(SupportDraftOrSubmit.class);
//
//                                Logger.i(submits.get(0).toString());
//
//                                Message message = Message.obtain();
//                                message.what = 1;
//                                message.obj = submits.get(0);
//                                mHandler.sendMessage(message);
//                                Log.e("AAA", "开始执行执行timer定时任务...");

//                                SupportDraftOrSubmit supportDraftOrSubmit = new SupportDraftOrSubmit();
//                                supportDraftOrSubmit.setLite_ID(0);
//                                supportDraftOrSubmit.updateAll("lite_ID = ? and lite_type = ?", liteIdList.get(i) + "", GlobalManager.TYPE_SUBMIT_LITE);

                        }
                    }
                };

                //请求返回的是当天才审核通过的车辆唯一标识
                RequestPolling.getInstance().postPolling(subscriber, pollingStr);

            } else {

                Message message = Message.obtain();
                message.what = 3;
                message.obj = "你好";
                mHandler.sendMessage(message);
//                Log.e("CCC", "不发送通知");
//                Logger.i("当天没有未审核通过的车辆或所有未审核车辆都在待审核状态");
//                ToastUtils.singleToast("当天没有未审核通过的车辆");
            }
        }
    }
}
