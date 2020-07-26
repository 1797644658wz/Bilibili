package com.example.bilibili.widget;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.bilibili.MainActivity;
import com.example.bilibili.R;
import com.example.bilibili.SplashActivity;
import com.example.bilibili.database_Test.MyDatabaseHelper;

public class LoginActivity_Test extends AppCompatActivity {

    private MyDatabaseHelper databaseHelper;
    private Button login_button;
    private Button register_button;
    EditText username_login;
    EditText password_login;
    SharedPreferences preferences;
    CheckBox rem_password_checkbox,auto_password_textbox;
    private SharedPreferences.Editor editor;
    ImageView imageView_22;
    ImageView imageView_33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__test);

        initview();
        preferences = this.getSharedPreferences("userInfo",MODE_PRIVATE);
        Boolean isremember_password=preferences.getBoolean("remember_password",false);
        Boolean isautologin=preferences.getBoolean("auto_login",false);

        if (isremember_password){
            //将账号密码设置到文本框中
            String account = preferences.getString("account","");
            String password = preferences.getString("password","");
            username_login.setText(account);
            password_login.setText(password);
            rem_password_checkbox.setChecked(true);
            if (isautologin){
                Intent main_Activity = new Intent(LoginActivity_Test.this, SplashActivity.class);
                startActivity(main_Activity);
                Toast.makeText(LoginActivity_Test.this, "自动登录成功！", Toast.LENGTH_SHORT).show();
                auto_password_textbox.setChecked(true);
            }
        }
        databaseHelper=new MyDatabaseHelper(this,"User_M.db",null,1);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username_login.getText().toString();
                String password=password_login.getText().toString();
                if (name.length()==0&&password.length()==0){
                    Toast.makeText(LoginActivity_Test.this, "账号密码不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                    if (Checkusername(name,password)){
                        editor = preferences.edit();
                        Intent main_Activity = new Intent(LoginActivity_Test.this, SplashActivity.class);
                        startActivity(main_Activity);
                        Toast.makeText(LoginActivity_Test.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        if (rem_password_checkbox.isChecked()){
                            editor.putBoolean("remember_password",true);
                            editor.putString("account",name);
                            editor.putString("password",password);
                            editor.putBoolean("auto_login",true);
                            Intent main_Activity_1= new Intent(LoginActivity_Test.this, SplashActivity.class);
                            startActivity(main_Activity_1);
                            Toast.makeText(LoginActivity_Test.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        }else {
                            editor.clear();
                        }
                        editor.commit();
                        finish();//关闭
                    }else {
                        Toast.makeText(LoginActivity_Test.this, "账号或密码错误，请重新输入：", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_button=new Intent(LoginActivity_Test.this,RegisterActivity_Test.class);
                startActivity(register_button);
            }
        });
        password_login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
    }
    private void initview() {
        login_button=findViewById(R.id.login_button);
        register_button=findViewById(R.id.login_register_button);
        username_login=findViewById(R.id.login_username_ed);
        password_login=findViewById(R.id.login_password_ed);
        rem_password_checkbox=findViewById(R.id.remember_password);
        auto_password_textbox=findViewById(R.id.auto_login);
        imageView_22=findViewById(R.id.b22);
        imageView_33=findViewById(R.id.b33);
    }
    //验证登录
    public boolean Checkusername(String username,String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = "select*from user where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username,password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
