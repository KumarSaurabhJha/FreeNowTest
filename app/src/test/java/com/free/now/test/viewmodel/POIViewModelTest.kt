package com.free.now.test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.free.now.test.data.api.RestApi
import com.free.now.test.data.model.PoiRequestHeader
import com.free.now.test.data.repository.PoiRepository
import com.free.now.test.domain.model.DesiredPoiCoordinates
import com.free.now.test.domain.usecase.GetPoiListUseCase
import com.free.now.test.poiList
import com.free.now.test.presentation.viewmodel.POIViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyDouble
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class POIViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var poiRepository: PoiRepository

    @Mock
    private lateinit var restApi: RestApi

    @Mock
    private lateinit var getPoiListUseCase: GetPoiListUseCase

    private lateinit var viewModel: POIViewModel

    private val desiredPoiCoordinates = DesiredPoiCoordinates(0.0, 1.1, 1.3, 2.3)
    private val poiRequestHeader = PoiRequestHeader(0.0, 1.1, 1.3, 2.3)
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = POIViewModel(
            getPoiListUseCase
        )

        runBlocking {
            `when`(poiRepository.getAllPoi(poiRequestHeader)).thenReturn(poiList)
            `when`(
                restApi.getVehicleList(
                    anyDouble(),
                    anyDouble(),
                    anyDouble(),
                    anyDouble()
                )
            ).thenReturn(poiList)

            `when`(
                getPoiListUseCase.execute(desiredPoiCoordinates)
            ).thenReturn(poiList)


        }
    }

    @Test
    fun test_init() {

        runBlocking {
            viewModel.init()

            assertTrue(viewModel.poiDisplayList.value?.peekContent()?.size == 17)
        }

    }
}