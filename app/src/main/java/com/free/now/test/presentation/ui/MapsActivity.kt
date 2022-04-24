package com.free.now.test.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.free.now.test.R
import com.free.now.test.data.model.Poi
import com.free.now.test.databinding.ActivityMapsBinding
import com.free.now.test.presentation.util.setDivider
import com.free.now.test.presentation.viewmodel.POIViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var adapter: PoiAdapter

    private val poiViewModel: POIViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        poiViewModel.init()
        setObservers()

    }

    private fun setObservers() {
        poiViewModel.poiDisplayList.observe(this) {
            it.getContentIfNotHandled()?.let { poiList ->
                setupAdapter(poiList)
                addPoiMarkersToMap(poiList)
            }
        }
    }

    private fun setupAdapter(poiList: List<Poi>) {

        val recyclerView = binding.rvPoiDataList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setDivider(R.drawable.recycler_view_divider)
        adapter = PoiAdapter(poiList) {
            onListItemClick(it)
        }

        recyclerView.adapter = adapter
    }

    private fun addPoiMarkersToMap(poiList: List<Poi>) {
        poiList.forEach {
            displayMarkerOnMap(it.coordinate.latitude, it.coordinate.longitude, it.fleetType)
        }
    }


    private fun onListItemClick(poi: Poi) {
        val coordinates = LatLng(poi.coordinate.latitude, poi.coordinate.longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12.0f))
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        displayMarkerOnMap(53.5511, 9.9937)
    }


    private fun displayMarkerOnMap(lat: Double, lon: Double, markerName: String = "") {
        if (this::mMap.isInitialized) {
            val coordinates = LatLng(lat, lon)
            if (markerName.isNotEmpty()) {
                mMap.addMarker(MarkerOptions().position(coordinates).title(markerName))
            }else{
                mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(9.0f))
            }
        }
    }
}