package com.tuandm.codeme.model.request;

import com.google.gson.annotations.SerializedName;

public class LikeSendForm {

    @SerializedName("userId")
    private String userId;

    @SerializedName("postId")
    private String postId;

    public LikeSendForm(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
