package com.app.hiring.presentation.hiring_list

import com.app.hiring.domain.model.HiringListItem

class HiringListState (
    val isLoading : Boolean = false,
    val itemList : Map<Int, ArrayList<HiringListItem>> = mutableMapOf<Int, ArrayList<HiringListItem>>(),
    val error : String = ""
)

