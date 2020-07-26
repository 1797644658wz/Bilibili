package com.example.bilibili.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bilibili.R;
import com.example.bilibili.database_Test.MyDatabaseHelper;

public class RegisterActivity_Test extends AppCompatActivity {

    MyDatabaseHelper databaseHelper;
    EditText register_username_ed;
    EditText register_password_ed;
    Button register_button;
    ImageView imageView_22;
    ImageView imageView_33;
    boolean flag=false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__test);

        databaseHelper=new MyDatabaseHelper(this,"User_M.db",null,1);
        register_button=findViewById(R.id.register_button);
        register_username_ed=findViewById(R.id.register_username_ed);
        register_password_ed=findViewById(R.id.register_password_ed);
        imageView_22=findViewById(R.id.b22);
        imageView_33=findViewById(R.id.b33);

        register_password_ed.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //得到焦点
                if (hasFocus){
                    imageView_22.setImageResource(R.drawable.ic_22_hide);
                    imageView_33.setImageResource(R.drawable.ic_33_hide);
                }else {
                    //失去焦点
                    imageView_22.setImageResource(R.drawable.ic_22);
                    imageView_33.setImageResource(R.drawable.ic_33);
                }
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=register_username_ed.getText().toString();
                String password=register_password_ed.getText().toString();

                if (name.length()==0||password.length()==0){
                    Toast.makeText(RegisterActivity_Test.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                if (CheckIsDataAlreadyInDBorNot(name)){
                    Toast.makeText(RegisterActivity_Test.this, "用户已存在", Toast.LENGTH_SHORT).show();
                }else {
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    //开始组装数据
                    values.put("name", name);
                    values.put("password", password);
                    db.insert("user", null, values);
                    values.clear();
                    Toast.makeText(RegisterActivity_Test.this, "注册成功!", Toast.LENGTH_SHORT).show();
                    Intent Login_test=new Intent(RegisterActivity_Test.this,LoginActivity_Test.class);
                    startActivity(Login_test);
                    finish();
                }
                }
            }
        });
    }
    //检查用户是否存在
    public boolean CheckIsDataAlreadyInDBorNot(String username) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.query("user", null, "name=?", new String[]{username},
                null, null, null);
        while (cursor.moveToNext()) {
            String user = cursor.getString(1);
            if (!user.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
