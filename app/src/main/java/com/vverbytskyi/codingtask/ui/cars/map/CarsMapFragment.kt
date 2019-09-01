package com.vverbytskyi.codingtask.ui.cars.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.cars.model.CarsData
import com.vverbytskyi.codingtask.ui.MainViewModel
import com.vverbytskyi.codingtask.ui.common.CompletedState
import dagger.android.support.DaggerFragment

class CarsMapFragment : DaggerFragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    private lateinit var model: MainViewModel

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

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        model.getCarsLiveData().observe(this, Observer { state ->
            when (state) {
                is CompletedState<*> -> {
                    (state.data as? CarsData)?.also { cardData ->
                        cardData.cars.forEach {
                            map.addMarker(
                                MarkerOptions()
                                    .position(LatLng(it.coordinates.latitude, it.coordinates.longitude))
                                    .title(it.id)
                                    .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_car)))
                        }
                    }
                }
            }
        })
    }

    private fun initView() {
        (childFragmentManager.findFragmentById(R.id.fragmentMap) as? SupportMapFragment)?.getMapAsync(this)
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setTint(Color.GREEN)
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}