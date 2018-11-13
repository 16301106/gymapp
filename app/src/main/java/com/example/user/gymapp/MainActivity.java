package com.example.user.gymapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity{
    private EditText userID;
    private EditText password;
    private Button BtLogin;
    private Button BtRegister;


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
    }

}
