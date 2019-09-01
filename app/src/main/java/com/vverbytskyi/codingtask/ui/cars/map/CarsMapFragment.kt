package com.vverbytskyi.codingtask.ui.cars.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.SupportMapFragment
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.cars.model.Car
import com.vverbytskyi.codingtask.domain.cars.model.CarsData
import com.vverbytskyi.codingtask.ui.MainViewModel
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.NetworkState
import com.vverbytskyi.codingtask.ui.common.map.GoogleMapProvider
import com.vverbytskyi.codingtask.ui.common.map.MapProvider
import dagger.android.support.DaggerFragment

private const val MUNICH_COORDS_LAT = 48.137154
private const val MUNICH_COORDS_LON = 11.576124

class CarsMapFragment : DaggerFragment() {

    private lateinit var model: MainViewModel

    private lateinit var mapProvider: MapProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cars_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        initView()
    }

    private fun initView() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap) as? SupportMapFragment
                ?: throw IllegalStateException("SupportMapFragment wasn't found in the fragment manager")

        mapProvider = GoogleMapProvider(mapFragment) {
            mapProvider.moveCamera(MUNICH_COORDS_LAT, MUNICH_COORDS_LON)

            model.getCarsLiveData().observe(this, Observer { handleNetworkState(it) })
        }
    }

    private fun handleNetworkState(networkState: NetworkState) {
        when (networkState) {
            is CompletedState<*> -> {
                (networkState.data as? CarsData)?.also { updateCarMarkers(it.cars) }
            }
        }
    }

    private fun updateCarMarkers(carsList: List<Car>) {
        mapProvider.clearMarkers()
        carsList.forEach {
            mapProvider.addMarker(
                it.coordinates.latitude,
                it.coordinates.longitude,
                it.id,
                getColoredMapIcon(it.color)
            )
        }
    }

    private fun getColoredMapIcon(@ColorRes color: Int): Bitmap? {
        return context?.let {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_car)?.run {
                setTint(ContextCompat.getColor(it, color))
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
                    .apply { draw(Canvas(this)) }
            }
        }
    }
}