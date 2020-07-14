package com.tuandm.codeme.view.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuandm.codeme.R;
import com.tuandm.codeme.model.response.UserInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    public interface OnFriendClickListener {
        void onFiendClick();
    }

    private ArrayList<UserInfo> friendList;
    private OnFriendClickListener listener;

    public FriendListAdapter(ArrayList<UserInfo> friendList, OnFriendClickListener listener) {
        this.friendList = friendList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(friendList.get(position));
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_avatar)
        CircleImageView imvAvatar;
        @BindView(R.id.txt_full_name)
        TextView txtFullName;
        @BindView(R.id.imv_add)
        ImageView imvAdd;
        @BindView(R.id.imv_check)
        ImageView imvCheck;

        private UserInfo friend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    friend.setSelected(!friend.isSelected());
                    notifyDataSetChanged();
                    listener.onFiendClick();
                }
            });
        }

        private void bindData(UserInfo friend) {
            this.friend = friend;

            Glide.with(itemView.getContext()).load(friend.getAvatar()).into(imvAvatar);
            txtFullName.setText(friend.getFullName());

            if (friend.isSelected()) {
                imvAdd.setVisibility(View.GONE);
                imvCheck.setVisibility(View.VISIBLE);
            } else {
                imvAdd.setVisibility(View.VISIBLE);
                imvCheck.setVisibility(View.GONE);
            }
        }
    }
}
