package com.app.hiring.domain.usecase.get_hiring_list

import android.util.Log
import com.app.hiring.common.Resource
import com.app.hiring.data.remote.dto.toItem
import com.app.hiring.domain.model.HiringListItem
import com.app.hiring.domain.repository.HiringRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHiringListByGroup @Inject constructor(
private val repository: HiringRepository
) {
    private val TAG: String? = GetHiringListByGroup::class.java.simpleName

    operator fun invoke() : Flow<Resource<Map<Int, ArrayList<HiringListItem>>>> = flow{
        try {
            emit(Resource.Loading<Map<Int, ArrayList<HiringListItem>>>())
            val hiringList = repository.getHiringList()
            Log.d(TAG, "hiringList = $hiringList")
            var hiringListByGroup = mutableMapOf<Int, ArrayList<HiringListItem>>()
            for (item in hiringList) {
                if(!item.name.isNullOrBlank() && item.name != "null"){
                    if (item.listId in hiringListByGroup.keys) {
                        hiringListByGroup[item.listId]?.add(item.toItem())
                    } else {
                        hiringListByGroup[item.listId] = arrayListOf(item.toItem())
                    }
                    hiringListByGroup[item.listId]?.sortBy { it ->
                        it.id
                    }
                }
            }
            val sortedMap = hiringListByGroup.toSortedMap(compareBy { it })
            emit(Resource.Success<Map<Int, ArrayList<HiringListItem>>>(sortedMap))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Map<Int, ArrayList<HiringListItem>>>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Map<Int, ArrayList<HiringListItem>>>>("Couldn't reach server. Check your internet connection."))
        }
    } as Flow<Resource<Map<Int, ArrayList<HiringListItem>>>>
}