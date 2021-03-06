package com.free.now.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.now.test.data.model.Poi
import com.free.now.test.domain.model.DesiredPoiCoordinates
import com.free.now.test.domain.usecase.GetPoiListUseCase
import com.free.now.test.presentation.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class POIViewModel(private val getPoiListUseCase: GetPoiListUseCase) : ViewModel() {

    private val _poiDisplayList = MutableLiveData<Event<List<Poi>>>()
    val poiDisplayList: LiveData<Event<List<Poi>>> get() = _poiDisplayList
    private val _error = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> get() = _error

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
            if (list.poiList.isEmpty()) {
                _error.postValue(Event(true))
            } else {
                _poiDisplayList.postValue(Event(list.poiList))
            }
        }
    }

}