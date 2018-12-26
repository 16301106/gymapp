package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity{
    private EditText userID;
    private EditText password;
    private EditText repassword;
    private Button BtRegister;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        userID = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText6);
        repassword = (EditText) findViewById(R.id.editText7);
        BtRegister = (Button) findViewById(R.id.button);
        btn = (Button) findViewById(R.id.button1);
        BtRegister.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser user = new BmobUser();
                user.setUsername(userID.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Log.e("注册失败", "原因", e);
                        }
                    }
                });
            }
        });

        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
