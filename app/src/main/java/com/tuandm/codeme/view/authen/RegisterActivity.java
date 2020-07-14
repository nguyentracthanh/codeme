package com.tuandm.codeme.view.authen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tuandm.codeme.R;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.request.RegisterSendForm;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.network.RetrofitService;
import com.tuandm.codeme.network.RetrofitUtils;
import com.tuandm.codeme.view.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_password_confirm)
    EditText edtRePassword;
    @BindView(R.id.edt_full_name)
    EditText edtFullName;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.btn_register)
    Button btnRegister;

    RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        addListener();
    }

    private void init() {
        ButterKnife.bind(this);
        retrofitService = RetrofitUtils.getInstance().createService(RetrofitService.class);
    }


    public void addListener() {
        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String rePassword = edtRePassword.getText().toString();
            String fullName = edtFullName.getText().toString();
            String email = edtEmail.getText().toString();
            String phone = edtPhone.getText().toString();
            if (username.isEmpty()) {
                showToast("Username must be not empty!");
            } else if (password.isEmpty()) {
                showToast("Password must be not empty!");
            } else if (!password.equals(rePassword)) {
                showToast("Password does not match!");
            } else if (fullName.isEmpty()) {
                showToast("FullName must be not empty!");
            } else if (email.isEmpty()) {
                showToast("Email must be not empty!");
            } else if (phone.isEmpty()) {
                showToast("Phone must be not empty!");
            } else {
                RegisterSendForm registerSendForm = new RegisterSendForm(username, password, fullName, email, phone);
                register(registerSendForm);
            }
        });
    }

    private void register(RegisterSendForm registerSendForm) {

        retrofitService.register(registerSendForm).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                UserInfo userInfo = response.body();
                if (response.code() == 200 && userInfo != null) {
                    showToast("Đăng kí thành công!");
                    RealmContext.getInstance().addUser(userInfo);
                    gotoHome();
                } else if (response.code() == 409) {
                    showToast("Tài khoản đã tồn tại!");
                } else {
                    showToast("Đăng kí thất bại! Vui lòng liên hệ nhà phát triển!");
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                showToast("Đăng kí thất bại! Vui lòng kiểm tra lại kết nối mạng!");
            }
        });
    }

    private void gotoHome() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
