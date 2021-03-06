package com.tuandm.codeme.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tuandm.codeme.R;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.view.authen.AuthenActivity;
import com.tuandm.codeme.view.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLogin();
            }
        }, 1000);
    }

    private void checkLogin() {
        UserInfo userInfo = RealmContext.getInstance().getUser();
        if (userInfo != null) {
            goToHomeScreen();
        } else {
            goToAuthenScreen();
        }
    }

    protected void goToHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void goToAuthenScreen() {
        Intent intent = new Intent(this, AuthenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
