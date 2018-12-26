package ui;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.text.SimpleDateFormat;

import cn.bmob.v3.BmobUser;


public class DynamicActivity extends AppCompatActivity {


    private MyDatabaseHelper dbHelper;
    private EditText author;
    private EditText dynamic;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic);

        dbHelper = new MyDatabaseHelper(this, "MyDynamic.db", null, 1);

        author = (EditText) findViewById(R.id.editText2);
        dynamic = (EditText) findViewById(R.id.editText);

        BmobUser user=BmobUser.getCurrentUser();
        author.setText(user.getUsername());

        Button addData = (Button) findViewById(R.id.send_button);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getWritableDatabase()会返回一个SQLiteDatabase对象
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //获取作者和动态内容
                final String author1 = author.getText().toString();
                final String dynamic1 = dynamic.getText().toString();

                //获取系统时间
                SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                Date curDate =  new Date(System.currentTimeMillis());
                String   time_now   =   formatter.format(curDate);

                values.put("dynamic", dynamic1);
                values.put("author",author1);
                values.put("time",time_now);
                //insert（）方法中第一个参数是表名，第二个参数是表示给表中未指定数据的自动赋值为NULL。第三个参数是一个ContentValues对象
                db.insert("MyDynamic", null, values);
                values.clear();

                dynamic.setText("");
                Intent intent = new Intent(DynamicActivity.this, Activity1.class);
                startActivity(intent);
            }
        });

        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicActivity.this, Activity1.class);
                startActivity(intent);
            }
        });
    }

}
