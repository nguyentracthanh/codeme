package com.tuandm.codeme.view.authen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tuandm.codeme.R;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthenActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_login_with_account)
    Button btnLogin;
    @BindView(R.id.layout_register)
    RelativeLayout layoutRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(this);
        layoutRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_with_account:
                goToLoginScreen();
                break;
            case R.id.layout_register:
                goToRegisterScreen();
                break;
            default:
                break;
        }
    }

    protected void goToRegisterScreen() {
        Intent intentToLoginWithAccount = new Intent(this, RegisterActivity.class);
        startActivity(intentToLoginWithAccount);
    }

    protected void goToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
