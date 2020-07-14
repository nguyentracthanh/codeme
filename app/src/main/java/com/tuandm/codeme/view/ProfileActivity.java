package com.tuandm.codeme.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.request.RequestOptions;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.tuandm.codeme.R;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.response.Profile;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.network.RetrofitService;
import com.tuandm.codeme.network.RetrofitUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    public static final String USER_NAME_KEY = "user_name_key";

    @BindView(R.id.slider)
    SliderLayout sliderLayout;

    private UserInfo userInfo = RealmContext.getInstance().getUser();
    private RetrofitService retrofitService = RetrofitUtils.getInstance().createService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        String username = getIntent().getStringExtra(USER_NAME_KEY);
        getProfile(username);
    }

    private void getProfile(String username) {
        retrofitService.getProfile(username, userInfo.getId()).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile profile = response.body();
                if (response.code() == 200 && profile != null) {
                    ArrayList<String> coverPhotos = profile.getCoverPhoto();
                    initSlider(coverPhotos);
                } else {

                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    private void initSlider(ArrayList<String> images) {

        sliderLayout.removeAllSliders();

        for (String image : images) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView.image(image);
            sliderLayout.addSlider(defaultSliderView);
        }

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateDown);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setDuration(4000);
    }
}
