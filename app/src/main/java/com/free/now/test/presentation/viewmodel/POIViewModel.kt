package com.free.now.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.now.test.data.model.PoiDataList
import com.free.now.test.domain.model.DesiredPoiCoordinates
import com.free.now.test.domain.usecase.GetPoiListUseCase
import com.free.now.test.presentation.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class POIViewModel(private val getPoiListUseCase: GetPoiListUseCase) : ViewModel() {

    private val _poiDisplayList = MutableLiveData<Event<PoiDataList>>()
    val poiDisplayList: LiveData<Event<PoiDataList>> get() = _poiDisplayList

    private val desiredPoiCoordinates =
        DesiredPoiCoordinates(53.694865, 9.757589, 53.394655, 10.099891)

    private var fetchDataJob: Job? = null

    fun cancelDataFetch() {
        fetchDataJob?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }

    fun init() {
        fetchDataJob = viewModelScope.launch(Dispatchers.IO) {
            val list = getPoiListUseCase.execute(desiredPoiCoordinates)
            _poiDisplayList.postValue(Event(list))
        }
    }

}