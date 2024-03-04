package com.app.hiring.data.remote.dto

import retrofit2.http.GET

interface HiringApi {
    @GET("/hiring.json")
    suspend fun getHiringList() : HiringListDto
}