package com.free.now.test.data.repository

import com.free.now.test.data.api.RestApi
import com.free.now.test.data.model.PoiDataList
import com.free.now.test.data.model.PoiRequestHeader

open class PoiRepository(private val restApi: RestApi) {

    suspend fun getAllPoi(poiRequestHeader: PoiRequestHeader): PoiDataList = restApi.getVehicleList(
        poiRequestHeader.p1Lat,
        poiRequestHeader.p1Lon,
        poiRequestHeader.p2Lat,
        poiRequestHeader.p2Lon
    )
}
