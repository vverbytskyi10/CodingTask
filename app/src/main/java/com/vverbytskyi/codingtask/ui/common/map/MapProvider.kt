package com.vverbytskyi.codingtask.ui.common.map

import android.graphics.Bitmap

const val ZOOM_LEVEL_DEFAULT = 11.5F

interface MapProvider {

    fun moveCamera(latitude: Double, longitude: Double, zoom: Float = ZOOM_LEVEL_DEFAULT)

    fun addMarker(latitude: Double, longitude: Double, markerTitle: String? = null, markerImage: Bitmap? = null)

    fun clearMarkers()
}