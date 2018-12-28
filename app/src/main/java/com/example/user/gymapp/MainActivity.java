package com.example.user.gymapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    private EditText userID;
    private EditText password;
    private Button BtLogin;
    private Button BtRegister;
    private Button BtQQ;
    private static Tencent mTencent;
    private UserInfo mInfo;
    private String APP_ID="1107972357";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"9fc8743f6bda86ec7f2a8a6472f42f01");
        initView();
    }


    private void initView(){
        userID=(EditText) findViewById(R.id.editText1);
        password=(EditText) findViewById(R.id.editText2);
        BtLogin=(Button) findViewById(R.id.button);
        BtQQ=(Button) findViewById(R.id.buttonqq);
        mTencent=Tencent.createInstance(APP_ID,getApplicationContext());
        BtLogin.setOnClickListener(new Button.OnClickListener(){
           @Override
           public void onClick(View v){
               BmobUser user=new BmobUser();
               user.setUsername(userID.getText().toString());
               user.setPassword(password.getText().toString());
               user.login(new SaveListener<BmobUser>() {
                   @Override
                   public void done(BmobUser bmobUser, BmobException e){
                       if(e==null){
                           Intent intent=new Intent(MainActivity.this,ContentActivity.class);
                           startActivity(intent);
                       }
                       else {
                           Log.e("登录失败","原因",e);
                       }
                   }
               });
           }
        });
        BtRegister=(Button) findViewById(R.id.button2);
        BtRegister.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        BtQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mTencent.isSessionValid()) {
                    mTencent.login(MainActivity.this, "all", loginListener);
                }
            }
        });
    }

    IUiListener loginListener=new BaseUiListener(){
        @Override
        public void doComplete(JSONObject values){
            Log.i("test",values.toString());
            initOpenidAndToken(values);
            updateUserInfo();
        }

    };

    private class BaseUiListener implements IUiListener{
        @Override
        public void onComplete(Object response){
            if(response==null){
                Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                return;
            }
            JSONObject jsonObject=(JSONObject) response;
            if(jsonObject!=null && jsonObject.length()==0){
                Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,ContentActivity.class);
            startActivity(intent);
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values){

        }

        @Override
        public void onError(UiError uiError){
            Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(){
            Toast.makeText(getApplicationContext(), "onCancel", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {
                }
                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    Log.i("tag", response.toString());
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                }
                @Override
                public void onCancel() {
                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        }
    }
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            JSONObject response=(JSONObject) msg.obj;
            if(response.has("nickname")){
                Intent intent=new Intent(MainActivity.this,ContentActivity.class);
                startActivity(intent);
            }
        }
    };
}
