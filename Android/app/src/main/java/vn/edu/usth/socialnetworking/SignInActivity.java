package vn.edu.usth.socialnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SignInActivity extends AppCompatActivity {
    EditText taikkhoan;
    EditText matkhau;
    Button dangnhap;
    LinearLayout dangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        taikkhoan=findViewById(R.id.editUser);
        matkhau=findViewById(R.id.editPass);
        dangnhap=findViewById(R.id.dangnhap);
        dangky=findViewById(R.id.signup);


        dangnhap.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent home=new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(home);
            }
        });


        dangky.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signup=new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signup);
            }
        });
    }
}
