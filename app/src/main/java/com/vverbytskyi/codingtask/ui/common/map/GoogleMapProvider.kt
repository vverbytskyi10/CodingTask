package com.vverbytskyi.codingtask.ui.common.map

import android.graphics.Bitmap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapProvider(
    supportMapFragment: SupportMapFragment,
    private val callback: () -> Unit
) : OnMapReadyCallback, MapProvider {

    private lateinit var map: GoogleMap

    init {
        supportMapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        callback.invoke()
    }

    override fun moveCamera(latitude: Double, longitude: Double, zoom: Float) {
        map.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(LatLng(latitude, longitude), zoom)
        )
    }

    override fun addMarker(
        latitude: Double,
        longitude: Double,
        markerTitle: String?,
        markerImage: Bitmap?
    ) {
        MarkerOptions().position(LatLng(latitude, longitude))
            ?.apply {
                markerTitle?.also { title(markerTitle) }
                markerImage?.also { icon(BitmapDescriptorFactory.fromBitmap(markerImage)) }
            }
            ?.also { map.addMarker(it) }
    }
}