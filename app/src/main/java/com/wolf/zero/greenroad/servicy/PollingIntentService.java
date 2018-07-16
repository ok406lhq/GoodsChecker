package com.wolf.zero.greenroad.servicy;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.tools.TimeUtil;

import java.util.Timer;
import java.util.TimerTask;



public class PollingIntentService extends IntentService {
    private static final String ACTION_FOO = "com.android.htc.greenroad.servicy.action.FOO";
    private static final String ACTION_BAZ = "com.android.htc.greenroad.servicy.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.android.htc.greenroad.servicy.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.android.htc.greenroad.servicy.extra.PARAM2";

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(sContext)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("aaaa")
                            .setContentText("bbbbb")
                            .setAutoCancel(true)
                            .setOnlyAlertOnce(true)
                            // 需要VIBRATE权限
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .setPriority(Notification.PRIORITY_DEFAULT);

                    NotificationManager notificationManager = (NotificationManager) sContext
                            .getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(TimeUtil.getTimeId(), builder.build());

                    break;

            }
            return false;
        }
    });
    private static Context sContext;
    private Timer mTimer;

    public PollingIntentService() {
        super("PollingService");
    }

    public static void startActionFoo(Context context, String param1, String param2) {
        sContext = context;
        Intent intent = new Intent(sContext, PollingIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        sContext.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                Logger.i(param1 + "-----" + param2);

                mTimer = new Timer();
                Task task = new Task();
                //schedule 计划安排，时间表 period
                mTimer.schedule(task, 6 * 1000);


//                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class Task extends TimerTask {
        @Override
        public void run() {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = "你好";
            mHandler.sendMessage(message);
            Log.e("AAA", "开始执行执行timer定时任务...");
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    getData();


//                }
//            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
}
