package com.example.kakaoemoticonparserdemo.common.remote

import com.example.kakaoemoticonparserdemo.list.model.ListResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface EmoticonInterface {

    @GET("/photos")
    fun getListResponse(): Flowable<ListResponse>
}
