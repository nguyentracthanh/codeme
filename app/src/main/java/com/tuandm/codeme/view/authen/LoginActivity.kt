package com.tuandm.codeme.view.authen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tuandm.codeme.R
import com.tuandm.codeme.database.RealmContext
import com.tuandm.codeme.model.request.LoginSendForm
import com.tuandm.codeme.model.response.UserInfo
import com.tuandm.codeme.network.RetrofitUtils
import com.tuandm.codeme.view.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val retrofitService = RetrofitUtils.getInstance().createService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addListener()
    }

    private fun addListener() {
        btn_sign_in.setOnClickListener {
            val username = edt_username.text.toString()
            val password = edt_password.text.toString()
            login(username, password)
        }

        layout_register.setOnClickListener { goToRegisterScreen() }
    }

    private fun login(username: String, password: String) {
        val sendForm = LoginSendForm(username, password)
        retrofitService.login(sendForm).enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val userInfo = response.body()
                if (response.code() == 200 && userInfo != null) {
                    RealmContext.getInstance().addUser(userInfo)
                    gotoHome()
                } else {
                    showToast("Username or password is incorrect!")
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {}
        })
    }

    private fun gotoHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    protected fun goToRegisterScreen() {
        val intentToLoginWithAccount = Intent(this, RegisterActivity::class.java)
        startActivity(intentToLoginWithAccount)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
