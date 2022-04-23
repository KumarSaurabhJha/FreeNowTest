package com.free.now.test.domain.usecase

import com.free.now.test.data.model.PoiDataList
import com.free.now.test.data.model.PoiRequestHeader
import com.free.now.test.data.repository.PoiRepository
import com.free.now.test.domain.UseCase

class GetPoiListUseCase(
    private val poiRepository: PoiRepository
) : UseCase<PoiRequestHeader, PoiDataList> {

    override suspend fun execute(input: PoiRequestHeader): PoiDataList =
        poiRepository.getAllPoi(input)
}