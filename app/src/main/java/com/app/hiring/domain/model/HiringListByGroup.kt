package com.app.hiring.domain.model

data class HiringListByGroup(
    val hiringList : Map<Int, ArrayList<HiringListItem>> = mutableMapOf<Int, ArrayList<HiringListItem>>()
)
