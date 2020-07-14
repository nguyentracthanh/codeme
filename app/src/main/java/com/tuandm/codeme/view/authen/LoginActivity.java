package com.tuandm.codeme.view.authen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tuandm.codeme.R;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.request.LoginSendForm;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.network.RetrofitService;
import com.tuandm.codeme.network.RetrofitUtils;
import com.tuandm.codeme.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_sign_in)
    Button btnLogin;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.layout_register)
    RelativeLayout layoutRegister;

    RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        retrofitService = RetrofitUtils.getInstance().createService();
        addListener();
    }

    public void addListener() {
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            login(username, password);
        });

        layoutRegister.setOnClickListener(v -> {
            goToRegisterScreen();
        });
    }

    private void login(String username, String password) {
        LoginSendForm sendForm = new LoginSendForm(username, password);
        retrofitService.login(sendForm).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo userInfo = response.body();
                if (response.code() == 200 && userInfo != null) {
                    RealmContext.getInstance().addUser(userInfo);
                    gotoHome();
                } else {
                    showToast("Username or password is incorrect!");
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
            }
        });
    }

    private void gotoHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void goToRegisterScreen() {
        Intent intentToLoginWithAccount = new Intent(this, RegisterActivity.class);
        startActivity(intentToLoginWithAccount);
    }

    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
