package vn.edu.usth.socialnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    EditText editname,editpass,editaccount,editConfirmpass,editmail,editPhone;
    Button btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editaccount=findViewById(R.id.editUser);
        editname=findViewById(R.id.nameuser);
        editConfirmpass=findViewById(R.id.confirmPass);
        editpass=findViewById(R.id.editPass1);
        editmail=findViewById(R.id.editmail);
        editPhone=findViewById(R.id.editphone);
        btnsignup=findViewById(R.id.dangky);
        btnsignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent main=new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

}
