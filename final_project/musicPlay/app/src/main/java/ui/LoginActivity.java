package ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etusername;
    private EditText etpassword;
    private Button login;
    private Button sign;
    String Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //这里的AppLication ID 写上自己创建项目得到的那个AppLication ID
        Bmob.initialize(this, "1a7a1cf93d94f96b0d868f364a7279e9");
        initialize();
        initView();
    }

    private void initView() {

    }

    private void initialize() {

        etusername = (EditText) findViewById(R.id.et_username);
        etpassword = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                final String username = etusername.getText().toString();
                String password = etpassword.getText().toString();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                    final BmobUser bmobUser = new BmobUser();
                    bmobUser.setUsername(username);
                    bmobUser.setPassword(password);

                    bmobUser.login(new SaveListener<BmobUser>() {

                        @Override
                        public void done(BmobUser bmobUser, BmobException e){
                            if(e==null){
                                getTitles();
                                setTitles(username);
                                Intent intent=new Intent(LoginActivity.this,Activity1.class);
                                startActivity(intent);
                            }
                            else {
                                Log.e("登录失败","原因",e);
                            }
                        }
                    });

                }
                break;
            case R.id.sign:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    public String getTitles(){
        return Title;
    }

    public void setTitles(String aa) {
        this.Title = aa;
    }
}
