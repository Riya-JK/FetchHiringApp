package com.app.hiring.domain.repository

import com.app.hiring.data.remote.dto.HiringListDto

interface HiringRepository {

    suspend fun getHiringList() : HiringListDto

}