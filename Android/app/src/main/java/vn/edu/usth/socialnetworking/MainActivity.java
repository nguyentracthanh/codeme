package vn.edu.usth.socialnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button btnlogin;
    Button loginwithGG;
    LinearLayout btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin=findViewById(R.id.login);
        btnsignup=findViewById(R.id.signup);
        loginwithGG=findViewById(R.id.sign_in_button);
        btnlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signIn=new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signIn);
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent signup=new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signup);
            }
        });

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
}
