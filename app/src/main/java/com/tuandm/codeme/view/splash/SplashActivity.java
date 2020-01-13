package com.tuandm.codeme.view.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tuandm.codeme.R;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.view.authen.AuthenActivity;
import com.tuandm.codeme.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.img_splash)
    ImageView splashImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(splashImg);
        Glide.with(this).load(R.drawable.app_logo).into(splashImg);

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
