package com.panther.events_app.models.group_event_model

import com.google.gson.annotations.SerializedName

data class PostCommentImage(
    @SerializedName("id")
    val id:String,
    @SerializedName("url")
    val url:String
)