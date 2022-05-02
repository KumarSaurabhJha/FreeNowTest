package com.free.now.test.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.free.now.test.data.api.RestApi
import com.free.now.test.data.repository.PoiRepository
import com.free.now.test.desiredPoiCoordinates
import com.free.now.test.domain.usecase.GetPoiListUseCase
import com.free.now.test.poiList
import com.free.now.test.poiRequestHeader
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPoiListUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var poiRepository: PoiRepository

    @Mock
    private lateinit var restApi: RestApi

    lateinit var getPoiListUseCase: GetPoiListUseCase

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getPoiListUseCase = GetPoiListUseCase(poiRepository)

        runBlocking {
            runBlocking {
                Mockito.`when`(poiRepository.getAllPoi(poiRequestHeader)).thenReturn(poiList)
                Mockito.`when`(
                    restApi.getVehicleList(
                        Mockito.anyDouble(),
                        Mockito.anyDouble(),
                        Mockito.anyDouble(),
                        Mockito.anyDouble()
                    )
                ).thenReturn(poiList)

            }
        }
    }

    @Test
    fun test_execute_get_list(){
        runBlocking {
          val list =  getPoiListUseCase.execute(desiredPoiCoordinates)

            assertNotNull(list)
            assertTrue(list.poiList.size == 17)
        }
    }

}