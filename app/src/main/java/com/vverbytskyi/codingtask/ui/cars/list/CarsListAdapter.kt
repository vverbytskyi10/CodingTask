package com.vverbytskyi.codingtask.ui.cars.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.carslist.model.Car
import com.vverbytskyi.codingtask.ui.common.extensions.autoNotify
import kotlin.properties.Delegates

class CarsListAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

    var items: List<Car> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_car, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val context = itemView.context

    private val image = itemView.findViewById<ImageView>(R.id.imageViewCar)
    private val vendor = itemView.findViewById<TextView>(R.id.textViewVendor)
    private val transmission = itemView.findViewById<TextView>(R.id.textViewTransmissionType)
    private val fuel = itemView.findViewById<TextView>(R.id.textViewFuelType)
    private val plate = itemView.findViewById<TextView>(R.id.textViewPlate)

    fun bindView(car: Car) {
        // TODO: add image showing
        Glide.with(image).load(car.imageUrl).placeholder(R.drawable.ic_broken_image).into(image)

        vendor.text = car.make
        transmission.text = context.getString(R.string.transmission_type, car.transmission)
        fuel.text = context.getString(R.string.fuel_type, car.fuelType)
        plate.text = context.getString(R.string.plate_number, car.licensePlate)
    }
}