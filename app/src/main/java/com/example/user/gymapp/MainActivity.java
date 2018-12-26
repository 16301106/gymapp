package com.example.user.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    private EditText userID;
    private EditText password;
    private Button BtLogin;
    private Button BtRegister;
    private Button BtQQ;
    private Tencent mTencent;
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
                mTencent=Tencent.createInstance(APP_ID,getApplicationContext());
                if(!mTencent.isSessionValid()) {
                    mTencent.login(MainActivity.this, "all", loginListener);
                    Intent intent=new Intent(MainActivity.this,ContentActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    IUiListener loginListener=new BaseUiListener(){
        @Override
        protected void doComplete(JSONObject values){
            Log.i("test",values.toString());
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
}
