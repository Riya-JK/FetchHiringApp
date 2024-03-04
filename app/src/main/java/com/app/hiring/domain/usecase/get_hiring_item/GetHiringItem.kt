package com.app.hiring.domain.usecase.get_hiring_item

import com.app.hiring.common.Resource
import com.app.hiring.domain.model.HiringListItem
import com.app.hiring.domain.usecase.get_hiring_list.GetHiringListByGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHiringItem @Inject constructor(
    private val hiringList: GetHiringListByGroup
) {
    operator fun invoke(listId: String) : Flow<Resource<ArrayList<HiringListItem>>> = flow {
        try {
            emit(Resource.Loading<ArrayList<HiringListItem>>())
            var hiringListItem : ArrayList<HiringListItem> = ArrayList()
            hiringList.invoke().onEach {item ->
                if(item.data != null && item.data.keys.contains(listId.toInt()))
                    hiringListItem = item.data[listId.toInt()]!!
            }
            emit(Resource.Success<ArrayList<HiringListItem>>(hiringListItem))
        } catch(e: HttpException) {
            emit(Resource.Error<ArrayList<HiringListItem>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<ArrayList<HiringListItem>>("Couldn't reach server. Check your internet connection."))
        }
    }
}