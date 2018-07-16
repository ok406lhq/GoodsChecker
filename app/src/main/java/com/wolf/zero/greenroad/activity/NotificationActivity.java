package com.wolf.zero.greenroad.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.litepalbean.SupportDraftOrSubmit;
import com.wolf.zero.greenroad.servicy.ShowNotificationActivity;

import org.litepal.crud.DataSupport;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends BaseActivity {

    @BindView(R.id.btn_notification)
    Button mBtnNotification;
    @BindView(R.id.btn_notification1)
    Button mBtnNotification1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);



//        PollingIntentService.startActionFoo(this, "aaa", "bbb");


        mBtnNotification.setOnClickListener(view -> {
//            showNotification(this, TimeUtil.getTimeId(), "你好", "通知通知通知");
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setAutoCancel(true);

            //正常通知点击会跳转到MainActivity intent携带的参数可以自己获取
            Intent intentClick = new Intent(this, ShowNotificationActivity.class);
            SupportDraftOrSubmit first = DataSupport.findFirst(SupportDraftOrSubmit.class);
            intentClick.putExtra("data", first);
            intentClick.putExtra("title", "通知标题");
            intentClick.putExtra("message", "通知内容。。。。。。");
            PendingIntent pendingIntentClick = PendingIntent.getActivity(this, 0,
                    intentClick, PendingIntent.FLAG_ONE_SHOT);

            //滑动清除和点击删除事件
            Intent intentCancel = new Intent(this, ShowNotificationActivity.class);
            intentCancel.setAction("notification_cancelled");
            intentCancel.putExtra("type", 3);
            intentCancel.putExtra("message", "message");
            PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(this, 0,
                    intentCancel, PendingIntent.FLAG_ONE_SHOT);

            android.support.v4.app.NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle("message title")
                    .setContentText("message content")
                    .setContentIntent(pendingIntentClick)//正常点击
                    .setDeleteIntent(pendingIntentCancel)//取消消息回调
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setPriority(Notification.PRIORITY_DEFAULT);

            mNotificationManager.notify(103, notificationBuilder.build());
        });
        mBtnNotification1.setOnClickListener(view -> {
//            showNotification(this, TimeUtil.getTimeId(), "你好", "通知通知通知");
//            PlaySound(this);
            Intent intent = new Intent(this, DraftActivity.class);
            startActivity(intent);

        });

    }
    // 播放默认铃声
    // 返回Notification id
    public static int PlaySound(final Context context) {
        NotificationManager mgr = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification nt = new Notification();
        nt.defaults = Notification.DEFAULT_SOUND;
        int soundId = new Random(System.currentTimeMillis())
                .nextInt(Integer.MAX_VALUE);
        mgr.notify(soundId, nt);
        return soundId;
    }
    private void showNotification(Context context, int id, String title, String text) {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                // 需要VIBRATE权限
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setPriority(Notification.PRIORITY_DEFAULT);

        // Creates an explicit intent for an Activity in your app
        //自定义打开的界面
//        Intent resultIntent = new Intent(context, FlashPageActivity.class);
//        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("notification ondestroy");
    }
}
