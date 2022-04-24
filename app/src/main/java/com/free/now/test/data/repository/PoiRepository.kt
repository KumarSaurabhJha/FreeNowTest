package com.free.now.test.data.repository

import com.free.now.test.data.api.RestApi
import com.free.now.test.data.model.PoiDataList
import com.free.now.test.data.model.PoiRequestHeader
import okio.IOException
import retrofit2.Response

class PoiRepository(private val restApi: RestApi) {

    suspend fun getAllPoi(poiRequestHeader: PoiRequestHeader): PoiDataList = processResponse(
        restApi.getVehicleList(
            poiRequestHeader.p1Lat,
            poiRequestHeader.p1Lon,
            poiRequestHeader.p2Lat,
            poiRequestHeader.p2Lon
        )
    )

    private fun processResponse(response: Response<PoiDataList>): PoiDataList = when {
        response.isSuccessful -> {
            response.body() ?: throw IOException()
        }
        else -> throw IOException()
    }
}
