package com.example.androidgroupproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Regidter extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_regidter);
        TextView loginScreen = (TextView) findViewById(R.id.reg_lgn_txt);
        loginScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(Regidter.this, Login.class);
                startActivity(i);
            }
        });
    }
    public void onSignUpClick(View v){
        if(v.getId() == R.id.register_from_register_act)
        {
            EditText uname = (EditText)findViewById(R.id.reg_uname_editText);
            EditText email = (EditText)findViewById(R.id.reg_email_editText);
            EditText pass1 = (EditText)findViewById(R.id.reg_pass1_editText);
            EditText pass2 = (EditText)findViewById(R.id.reg_pass2_editText);

            String unamestr = uname.getText().toString();
            String emailstr = email.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if (unamestr.trim().isEmpty() || emailstr.trim().isEmpty() || pass1str.trim().isEmpty() || pass2str.trim().isEmpty()){
                Toast tpass = Toast.makeText(Regidter.this, "Please fill all feilds", Toast.LENGTH_LONG);
                tpass.show();
            }else {
                boolean result = helper.checkEmail(emailstr);
                if(result == true)
                {
                    Toast temp = Toast.makeText(Regidter.this, "Email Is Already Exists", Toast.LENGTH_LONG);
                    temp.show();
                }else{
                    if (!emailstr.trim().matches(emailPattern)) {
                        Toast temp = Toast.makeText(Regidter.this, "Please Enter a Valid Email", Toast.LENGTH_LONG);
                        temp.show();
                    }else{
                        if(!pass1str.equals(pass2str))
                        {
                            Toast tpass = Toast.makeText(Regidter.this, "Passwords Don't Match", Toast.LENGTH_LONG);
                            tpass.show();
                        }else {
                            User c = new User();
                            c.setUname(unamestr);
                            c.setEmail(emailstr);
                            c.setPass(pass1str);
                            helper.insertContact(c);
                            Toast tpass = Toast.makeText(Regidter.this, "Successfully registered", Toast.LENGTH_LONG);
                            tpass.show();
                        }
                    }

                }

            }


        }

    }
}

