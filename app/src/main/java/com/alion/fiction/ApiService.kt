package com.alion.fiction

import com.alion.fiction.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        //此类接口的基地址
        val baseUrl = "http://api.o821.com/"
    }
    /*suspend fun getFictions(): BaseResp<List<Fiction>>*/

    @GET("search/{name}")
    suspend fun getSearch(@Path("name") name:String): MutableList<FictionSearch>

    @GET("page")
    suspend fun getList(@Query("url") url:String): FictionList

    @GET("content")///25/25547/12441746.html
    suspend fun getDetail(@Query("link") link:String): FictionDetail


}