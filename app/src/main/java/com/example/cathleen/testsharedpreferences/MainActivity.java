package com.example.cathleen.testsharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //SharedPreferences文件名
    private final static String SharedPreferencesFileName="config";   //键
    private final static String Key_UserName="UserName";//用户名
    private final static String Key_LoginDate="LoginDate";//登录时间
    private final static String Key_UserType="UserType";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //获得SharedPreferences实例
        preferences=getSharedPreferences(SharedPreferencesFileName,MODE_PRIVATE);
        editor=preferences.edit();
        Button btnWrite=(Button)findViewById(R.id.write);
        Button btnRead=(Button)findViewById(R.id.read);
        final EditText UserName=(EditText)findViewById(R.id.editText);
        final EditText UserStyle=(EditText)findViewById(R.id.editText2);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());
                editor.putString(Key_UserName, UserName.getText()+"");
                editor.putString(Key_LoginDate, strLoginDate);
                String UserStyle_String=UserStyle.getText().toString();
                int UserStyle_int=Integer.parseInt(UserStyle_String);
                editor.putInt(Key_UserType, UserStyle_int);
                editor.apply();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	String strUserName = preferences.getString(Key_UserName, null);
                String strLoginDate = preferences.getString(Key_LoginDate, null);
                int nUserType=preferences.getInt(Key_UserType,0);
                if (strUserName != null && strLoginDate != null)
                    Toast.makeText(MainActivity.this, "用户名:" + strUserName + "登录时间:" + strLoginDate+"用户类型:"+nUserType, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
            }
        });
    }



}
