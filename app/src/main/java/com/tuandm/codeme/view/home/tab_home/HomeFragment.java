package com.tuandm.codeme.view.home.tab_home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.tuandm.codeme.base.BaseFragment;
import com.tuandm.codeme.R;
import com.tuandm.codeme.common.LoadingDialog;
import com.tuandm.codeme.database.RealmContext;
import com.tuandm.codeme.model.request.CreateStatusSendForm;
import com.tuandm.codeme.model.request.LikeSendForm;
import com.tuandm.codeme.model.response.Status;
import com.tuandm.codeme.model.response.UserInfo;
import com.tuandm.codeme.network.RetrofitService;
import com.tuandm.codeme.network.RetrofitUtils;
import com.tuandm.codeme.view.ProfileActivity;
import com.tuandm.codeme.view.adapter.StatusAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements StatusAdapter.OnStatusClickListener {


    public HomeFragment() {
    }

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.rcv_status)
    RecyclerView rcvStatus;
    @BindView(R.id.imv_send)
    ImageView imvSend;
    @BindView(R.id.edt_status_content)
    EditText edtStatusContent;

    private UserInfo userInfo;
    private RetrofitService retrofitService;

    private List<Status> statuses;
    private StatusAdapter statusAdapter;

    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
        addListener();
        getAllStatus();
    }

    private void init() {

        loadingDialog = new LoadingDialog(getActivity());

        userInfo = RealmContext.getInstance().getUser();
        retrofitService = RetrofitUtils.getInstance().createService();

        statuses = new ArrayList<>();
        statusAdapter = new StatusAdapter(statuses, this);
        rcvStatus.setAdapter(statusAdapter);
        rcvStatus.setLayoutManager(new LinearLayoutManager(
                getContext(), RecyclerView.VERTICAL, false));
    }

    private void addListener() {
        imvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtStatusContent.getText().toString();
                if (content.isEmpty()) {
                    showToast("Bạn vui lòng nhập nội dung!");
                } else {
                    loadingDialog.show();
                    createStatus(content);
                }
            }
        });
    }

    private void getAllStatus() {
        retrofitService.getAllStatus(userInfo.getId()).enqueue(new Callback<ArrayList<Status>>() {
            @Override
            public void onResponse(Call<ArrayList<Status>> call, Response<ArrayList<Status>> response) {
                ArrayList<Status> list = response.body();
                if (response.code() == 200 && list != null && !list.isEmpty()) {
                    statuses.clear();
                    statuses.addAll(list);
                    statusAdapter.notifyDataSetChanged();
                    viewFlipper.setDisplayedChild(3);
                } else {
                    viewFlipper.setDisplayedChild(1);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Status>> call, Throwable t) {
                viewFlipper.setDisplayedChild(2);
            }
        });
    }

    private void createStatus(String content) {
        CreateStatusSendForm statusSendForm = new CreateStatusSendForm(userInfo.getId(), content);

        retrofitService.createStatus(statusSendForm).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if (response.code() == 200 && status != null) {
                    statuses.add(0, status);
                    statusAdapter.notifyDataSetChanged();
                    edtStatusContent.setText("");
                    showToast("Đăng bài thành công!");
                } else {
                    showToast("Đăng bài thất bại! Vui lòng thử lại sau!");
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                showToast("Đăng bài thất bại! Vui lòng thử lại sau!");
                loadingDialog.dismiss();
            }
        });
    }

    private void likeStatus(int position) {
        LikeSendForm sendForm = new LikeSendForm(userInfo.getId(), statuses.get(position).getPostId());
        retrofitService.likeStatus(sendForm).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    showToast("Thành công");
                } else {
                    handleLikeAction(position);
                    showToast("Thất bại");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                handleLikeAction(position);
                showToast("Thất bại");
            }
        });
    }

    private void handleLikeAction(int position) {
        statuses.get(position).setLike(!statuses.get(position).isLike());
        if (statuses.get(position).isLike()) {
            statuses.get(position).setNumberLike(statuses.get(position).getNumberLike() + 1);
        } else {
            statuses.get(position).setNumberLike(statuses.get(position).getNumberLike() - 1);
        }
        statusAdapter.notifyItemChanged(position); // thay đổi giao diện
    }

    @Override
    public void onLikeClick(int position) {
        handleLikeAction(position);
        likeStatus(position);
    }

    @Override
    public void onCommentClick(Status status) {

    }

    @Override
    public void onAvatarAndNameClick(String username) {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra(ProfileActivity.USER_NAME_KEY, username);
        startActivity(intent);
    }
}
