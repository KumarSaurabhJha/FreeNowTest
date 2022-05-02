package com.free.now.test

import com.free.now.test.data.model.Coordinate
import com.free.now.test.data.model.Poi
import com.free.now.test.data.model.PoiDataList
import com.free.now.test.data.model.PoiRequestHeader
import com.free.now.test.domain.model.DesiredPoiCoordinates

val desiredPoiCoordinates = DesiredPoiCoordinates(0.0, 1.1, 1.3, 2.3)
val poiRequestHeader = PoiRequestHeader(0.0, 1.1, 1.3, 2.3)

val poiList = PoiDataList(
    listOf(
        Poi(coordinate = Coordinate(5.12, 12.0), fleetType = "TAXI", heading = 12.3, 1),
        Poi(coordinate = Coordinate(12.0, 1.0), fleetType = "TAXI", heading = 12.3, 2),
        Poi(coordinate = Coordinate(4.0, 3.0), fleetType = "POOL", heading = 12.3, 3),
        Poi(coordinate = Coordinate(35.0, 2.0), fleetType = "TAXI", heading = 1.3, 4),
        Poi(coordinate = Coordinate(54.0, 5.40), fleetType = "POOL", heading = 122.3, 5),
        Poi(coordinate = Coordinate(4.0, 5.0), fleetType = "POOL", heading = 9.3, 6),
        Poi(coordinate = Coordinate(2.0, .0), fleetType = "POOL", heading = 12.3, 7),
        Poi(coordinate = Coordinate(22.0, 23.0), fleetType = "TAXI", heading = 12.3, 8),
        Poi(coordinate = Coordinate(33.0, 42.0), fleetType = "TAXI", heading = 11.3, 9),
        Poi(coordinate = Coordinate(5.0, 11.0), fleetType = "POOL", heading = 52.3, 10),
        Poi(coordinate = Coordinate(6.0, 12.0), fleetType = "POOL", heading = 126.3, 11),
        Poi(coordinate = Coordinate(.0, 23.0), fleetType = "POOL", heading = 52.3, 12),
        Poi(coordinate = Coordinate(7.0, 2.0), fleetType = "TAXI", heading = 42.3, 13),
        Poi(coordinate = Coordinate(65.0, .230), fleetType = "TAXI", heading = 1552.3, 14),
        Poi(coordinate = Coordinate(87.0, 2.0), fleetType = "POOL", heading = 152.3, 15),
        Poi(coordinate = Coordinate(33.0, 44.0), fleetType = "POOL", heading = 129.3, 16),
        Poi(coordinate = Coordinate(66.0, 42.0), fleetType = "POOL", heading = 162.3, 17)
    )
)