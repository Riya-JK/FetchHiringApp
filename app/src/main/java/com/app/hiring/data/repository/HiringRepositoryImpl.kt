package com.app.hiring.data.repository

import android.util.Log
import com.app.hiring.data.remote.dto.HiringApi
import com.app.hiring.data.remote.dto.HiringListDto
import com.app.hiring.domain.repository.HiringRepository
import javax.inject.Inject

class HiringRepositoryImpl @Inject constructor(
    private val api : HiringApi
) : HiringRepository {

    private val TAG: String? = HiringRepositoryImpl::class.java.simpleName

    override suspend fun getHiringList(): HiringListDto {
        Log.d(TAG, "getHiringList() called")
        return api.getHiringList()
    }

}