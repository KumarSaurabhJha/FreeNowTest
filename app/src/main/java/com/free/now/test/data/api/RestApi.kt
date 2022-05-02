package com.free.now.test.data.api

import com.free.now.test.data.model.PoiDataList
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET(".")
    suspend fun getVehicleList(
        @Query("p1Lat") p1Lat: Double,
        @Query("p1Lon") p1Lon: Double,
        @Query("p2Lat") p2Lat: Double,
        @Query("p2Lon") p2Lon: Double
    ): PoiDataList
}