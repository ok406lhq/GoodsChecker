package com.wolf.zero.greenroad.servicy;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wolf.zero.greenroad.httpresultbean.HttpResultBlack;
import com.wolf.zero.greenroad.https.RequestBlackList;
import com.wolf.zero.greenroad.litepalbean.SupportBlack;
import com.wolf.zero.greenroad.presenter.NetWorkManager;
import com.wolf.zero.greenroad.tools.SPUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import rx.Subscriber;


public class BlackListService extends IntentService {

    private static final String ACTION_BLACK_LIST = "com.zero.wolf.greenroad.blacklistservice.action.FOO";
    private static final String ACTION_BLACK_SUBMIT = "com.zero.wolf.greenroad.blacklistservice.action.BAZ";
    private static Context sContext;
    private static TextView sNumberBlacklist;


    public BlackListService() {
        super("BlackListService");
    }


    public static void startActionBlack(Context context, TextView tvMathNumberBlacklist) {
        sContext = context;
        sNumberBlacklist = tvMathNumberBlacklist;
        Intent intent = new Intent(sContext, BlackListService.class);
        intent.setAction(ACTION_BLACK_LIST);

        sContext.startService(intent);
    }

    public static void startActionBaz(Context context) {
        sContext = context;
        Intent intent = new Intent(context, BlackListService.class);
        intent.setAction(ACTION_BLACK_SUBMIT);

        sContext.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_BLACK_LIST.equals(action)) {
                if (NetWorkManager.isnetworkConnected(sContext)) {
//                Logger.i("有网加载黑名单数据的服务");

                    RequestBlackList.getInstance().getBlackList(new Subscriber<List<HttpResultBlack.DataBean>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Logger.i(e.getMessage());
                            List<SupportBlack> supportBlacks = DataSupport.findAll(SupportBlack.class);
                            SPUtils.putAndApply(getApplicationContext(), SPUtils.COUNT_BLACKLIST, supportBlacks.size());
                            sNumberBlacklist.setText(supportBlacks.size() + "");
                        }

                        @Override
                        public void onNext(List<HttpResultBlack.DataBean> dataBeen) {
                            List<SupportBlack> supportBlacks = DataSupport.findAll(SupportBlack.class);
                            if (supportBlacks.size() == dataBeen.size()) {
                                SPUtils.putAndApply(getApplicationContext(), SPUtils.COUNT_BLACKLIST, supportBlacks.size());
                                sNumberBlacklist.setText(supportBlacks.size() + "");
                                return;
                            } else {
                                DataSupport.deleteAll(SupportBlack.class);
                                for (int i = 0; i < dataBeen.size(); i++) {
//                                    Logger.i(dataBeen.get(i).getPlate_number());
                                    SupportBlack supportBlack = new SupportBlack();
                                    supportBlack.setLicense(dataBeen.get(i).getPlate_number());
                                    supportBlack.save();
                                }
                                List<SupportBlack> blackList = DataSupport.findAll(SupportBlack.class);
                                SPUtils.putAndApply(getApplicationContext(), SPUtils.COUNT_BLACKLIST, blackList.size());
                                sNumberBlacklist.setText(blackList.size() + "");
                            }
                        }

                    });
                } else {
                    Logger.i("无网未加载黑名单数据");
                    List<SupportBlack> supportBlacks = DataSupport.findAll(SupportBlack.class);
                    try {
                        sNumberBlacklist.post(() -> sNumberBlacklist.setText(supportBlacks.size() + ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


}
