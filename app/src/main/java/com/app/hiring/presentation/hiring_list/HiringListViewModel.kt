package com.app.hiring.presentation.hiring_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hiring.common.Resource
import com.app.hiring.domain.usecase.get_hiring_list.GetHiringListByGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HiringListViewModel @Inject constructor(
    private val getHiringListByGroup: GetHiringListByGroup
) : ViewModel() {

    private val _state = mutableStateOf(HiringListState())
    val state : State<HiringListState> = _state

    init {
        getHiringList()
    }

    private fun getHiringList(){
        getHiringListByGroup().onEach {result ->
            when(result){
                is Resource.Success ->{
                    _state.value = HiringListState(itemList = result.data ?: mutableMapOf())
                }
                is Resource.Error -> {
                    _state.value = HiringListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = HiringListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}