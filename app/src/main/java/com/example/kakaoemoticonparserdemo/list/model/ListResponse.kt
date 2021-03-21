package com.example.kakaoemoticonparserdemo.list.model

import com.google.gson.annotations.SerializedName

class ListResponse : ArrayList<ListResponse.ListResponseItem>() {
    data class ListResponseItem(
        @SerializedName("albumId")
        val albumId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )
}