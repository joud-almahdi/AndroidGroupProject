package com.example.androidgroupproject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        TextView registerScreen = (TextView) findViewById(R.id.login_reg_txt);
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(Login.this, Regidter.class);
                startActivity(i);
            }
        });
    }
    public void onSigninClick(View v){
        if(v.getId() == R.id.Login_btn)
        {
            EditText Email= (EditText)findViewById(R.id.login_email_editText);
            String strEmail = Email.getText().toString();
            EditText Pass = (EditText)findViewById(R.id.login_pass_editText);
            String strPass = Pass.getText().toString();

            if (strEmail.trim().isEmpty() || strPass.trim().isEmpty()){
                Toast temp = Toast.makeText(Login.this, "Please fill all feilds", Toast.LENGTH_LONG);
                temp.show();
            }
            boolean result = helper.checkUser(strEmail, strPass);
            if(result == true)
            {
                Toast temp = Toast.makeText(Login.this, "fouuuuund", Toast.LENGTH_LONG);
                temp.show();
            }
            else{
                Toast temp = Toast.makeText(Login.this, "incorrect username or passwords", Toast.LENGTH_LONG);
                temp.show();
            }
        }

    }
}