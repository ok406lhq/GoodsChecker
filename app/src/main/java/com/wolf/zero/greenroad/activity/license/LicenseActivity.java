package com.wolf.zero.greenroad.activity.license;

import android.Manifest.permission;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.kernal.plateid.AuthService;
import com.kernal.plateid.Devcode;
import com.kernal.plateid.PlateAuthParameter;
import com.wolf.zero.greenroad.R;
import com.wolf.zero.greenroad.activity.BaseActivity;

import utills.CheckPermission;


public class LicenseActivity extends BaseActivity implements OnClickListener{
    String sn;
    String authfile;

    private int ReturnAuthority = -1;

    public AuthService.MyBinder authBinder;

    private Button videoReg;

    //授权验证服务绑定后的操作与start识别服务
    public ServiceConnection authConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            authBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            authBinder = (AuthService.MyBinder) service;
            Toast.makeText(getApplicationContext(), R.string.auth_check_service_bind_success, Toast.LENGTH_SHORT).show();
            try {
                PlateAuthParameter pap = new PlateAuthParameter();
                pap.sn = sn;
                pap.authFile = authfile;
                pap.devCode = Devcode.DEVCODE;
                ReturnAuthority = authBinder.getAuth(pap);
                if (ReturnAuthority != 0) {
                    Toast.makeText(getApplicationContext(),getString(R.string.license_verification_failed)+":"+ReturnAuthority,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.license_verification_success,Toast.LENGTH_LONG).show();
                }
            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), R.string.failed_check_failure, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }finally{
                if (authBinder != null) {
                    unbindService(authConn);
                }
            }
        }

    };

    static final String[] PERMISSION = new String[] {permission.CAMERA,
            permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            permission.READ_EXTERNAL_STORAGE, // 读取权限
            permission.READ_PHONE_STATE,
            permission.VIBRATE, permission.INTERNET,
//		permission.FLASHLIGHT
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.test_plate_activity);
        findViews();
    }



    private void findViews() {
//		videoReg = (Button) findViewById(R.id.videoReg);
        videoReg.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //拍照识别入口
        if(getResources()
                .getIdentifier("videoReg", "id", this.getPackageName())==v.getId()){
            //视频识别入口
            Intent video_intent = new Intent();
            video_intent.putExtra("camera", true);
            video_intent = new Intent(LicenseActivity.this,MemoryCameraActivity.class);
            if (Build.VERSION.SDK_INT >= 23) {
                CheckPermission checkPermission = new CheckPermission(LicenseActivity.this);
                if (checkPermission.permissionSet(PERMISSION)) {
                    PermissionActivity.startActivityForResult(LicenseActivity.this,0,"true",  PERMISSION);

                } else {
                    video_intent.setClass(getApplicationContext(), MemoryCameraActivity.class);
                    video_intent.putExtra("camera", true);
                    startActivity(video_intent);
                    finish();
                }
            } else {
                video_intent.setClass(getApplicationContext(), MemoryCameraActivity.class);
                video_intent.putExtra("camera", true);
                startActivity(video_intent);
                finish();
            }
        }

    }

}
