package com.free.now.test.domain.usecase

import com.free.now.test.data.model.PoiDataList
import com.free.now.test.data.model.PoiRequestHeader
import com.free.now.test.data.repository.PoiRepository
import com.free.now.test.domain.UseCase
import com.free.now.test.domain.model.DesiredPoiCoordinates

open class GetPoiListUseCase(
    private val poiRepository: PoiRepository
) : UseCase<DesiredPoiCoordinates, PoiDataList> {

    override suspend fun execute(input: DesiredPoiCoordinates): PoiDataList =
        poiRepository.getAllPoi(
            PoiRequestHeader(
                input.p1Lat,
                input.p1Lon,
                input.p2Lat,
                input.p2Lon
            )
        )
}