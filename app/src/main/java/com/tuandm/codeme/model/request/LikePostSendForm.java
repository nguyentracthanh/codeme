package com.tuandm.codeme.model.request;

import com.google.gson.annotations.SerializedName;

public class LikePostSendForm {

    @SerializedName("userId")
    private String userId;

    @SerializedName("postId")
    private String postId;

    public LikePostSendForm(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
