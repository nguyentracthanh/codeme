package com.tuandm.codeme.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tuandm.codeme.R;
import com.tuandm.codeme.model.response.Status;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    public interface OnStatusClickListener {
        void onLikeClick(int position);

        void onCommentClick(Status status);

        void onAvatarAndNameClick(String username);
    }


    private List<Status> statusList;
    private OnStatusClickListener listener;

    public StatusAdapter(List<Status> statusList, OnStatusClickListener listener) {
        this.statusList = statusList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_status, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(statusList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_avatar)
        CircleImageView imvAvatar;
        @BindView(R.id.tv_full_name)
        TextView txtFullName;
        @BindView(R.id.tv_create_date)
        TextView txtCreateDate;
        @BindView(R.id.tv_content)
        TextView txtContent;
        @BindView(R.id.tv_like_number)
        TextView txtLikeNumber;
        @BindView(R.id.tv_comment_number)
        TextView txtCommentNumber;

        @BindView(R.id.imv_like)
        ImageView imvLike;
        @BindView(R.id.tv_like)
        TextView txtLike;
        @BindView(R.id.item_like)
        LinearLayout itemLike;


        @BindView(R.id.item_comment)
        LinearLayout itemComment;

        private int position;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLikeClick(position);
                }
            });

            txtFullName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAvatarAndNameClick(statusList.get(position).getAuthor());
                }
            });

            imvAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAvatarAndNameClick(statusList.get(position).getAuthor());
                }
            });
        }

        private void bindData(Status status, int position) {
            this.position = position;

            Glide.with(itemView.getContext()).load(status.getAuthorAvatar()).into(imvAvatar);
            txtFullName.setText(status.getAuthorName());
            txtCreateDate.setText(status.getCreateDate());
            txtContent.setText(status.getContent());
            txtLikeNumber.setText(String.valueOf(status.getNumberLike()));
            txtCommentNumber.setText(String.format(Locale.getDefault(), "%d bình luận", status.getNumberComment()));

            if (status.isLike()) { // mình đã like bài viết này
                imvLike.setBackground(itemView.getResources().getDrawable(R.drawable.ic_like));
                txtLike.setText("Bỏ thích");
                txtLike.setTextColor(itemView.getResources().getColor(R.color.colorRed400));
            } else { // mình chưa like
                imvLike.setBackground(itemView.getResources().getDrawable(R.drawable.ic_dont_like));
                txtLike.setText("Thích");
                txtLike.setTextColor(itemView.getResources().getColor(R.color.colorGray700));
            }
        }
    }
}
