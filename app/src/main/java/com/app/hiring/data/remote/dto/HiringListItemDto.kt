package com.app.hiring.data.remote.dto

import com.app.hiring.domain.model.HiringListItem

data class HiringListItemDto(
    val id: Int,
    val listId: Int,
    val name: String
)

fun HiringListItemDto.toItem() : HiringListItem {
    return HiringListItem(
        id = id,
        name = name
    )
}