package com.wolf.zero.greenroad.polling;//package com.android.htc.greenroad.polling;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//
//import R;
//import MainActivity;
//
//public class PollingService extends Service {
//    public static final String ACTION = "com.ryantang.service.PollingService";
//
//    private Notification mNotification;
//    private NotificationManager mManager;
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//    @Override
//    public void onCreate() {
//        initNotifiManager();
//    }
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        new PollingThread().start();
//    }
//    //初始化通知栏配置
//    private void initNotifiManager() {
//        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        int icon = R.drawable.about_app;
//        mNotification = new Notification();
//        mNotification.icon = icon;
//        mNotification.tickerText = "New Message";
//        mNotification.defaults |= Notification.DEFAULT_SOUND;
//        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
//    }
//    //弹出Notification
//    private void showNotification() {
//
//        //Navigator to the new activity when click the notification title
//        Intent i = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
//                PendingIntent.FLAG_ONE_SHOT);
//       // mNotification.setLatestEventInfo(this,
//         //       getResources().getString(R.string.app_name), "You have new message!", pendingIntent);
//        Notification notification = new Notification.Builder(this)
//                .setAutoCancel(true)
//                .setContentTitle("title")
//                .setContentText("describe")
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(R.drawable.about_app)
//                .setWhen(System.currentTimeMillis())
//                .build();
//        mManager.notify(0, notification);
//    }
//    /**
//     * Polling thread
//     * 模拟向Server轮询的异步线程
//     * @Author Ryan
//     * @Create 2013-7-13 上午10:18:34
//     */
//    int count = 0;
//    class PollingThread extends Thread {
//        @Override
//        public void run() {
//            System.out.println("Polling...");
//            count ++;
//            //当计数能被5整除时弹出通知
//            if (count % 5 == 0) {
//                showNotification();
//                System.out.println("New message!");
//            }
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        System.out.println("Service:onDestroy");
//    }
//}
