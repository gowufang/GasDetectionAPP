package com.example.administrator.gasdetectionapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/21.
 */


    public class LoginActivity extends Activity {
        private EditText accountEdit;
        private EditText passwordEdit;
        private Button login;
        private SharedPreferences preferences;
        private SharedPreferences.Editor editor;
        private CheckBox rememberpassword;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
            preferences= PreferenceManager.getDefaultSharedPreferences(this);//create file
            accountEdit = (EditText) findViewById(R.id.account);
            passwordEdit = (EditText) findViewById(R.id.password);
            rememberpassword= (CheckBox) findViewById(R.id.remember_pass);
            boolean isRemember=preferences.getBoolean("remember_password",false);
            if(isRemember){
                String account=preferences.getString("account","");
                String password=preferences.getString("password","");
                accountEdit.setText(account);
                passwordEdit.setText(password);
                rememberpassword.setChecked(true);

            }
            login = (Button) findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String account = accountEdit.getText().toString();
                    String password = passwordEdit.getText().toString();
// 如果账号是admin且密码是123456，就认为登录成功
                    savePassword(account,password);
                    if (account.equals("admin") && password.equals("123456")) {

                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "account or password is invalid",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    public void savePassword(String account, String password){
        editor=preferences.edit();//1
        if(rememberpassword.isChecked()){//2
            editor.putBoolean("remember_password",true);
            editor.putString("account",account);
            editor.putString("password",password);


        }else{
            editor.clear();
        }
        editor.commit();//3
    }
}
