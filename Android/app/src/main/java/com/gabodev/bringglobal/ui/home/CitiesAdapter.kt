package com.gabodev.bringglobal.ui.home

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.gabodev.bringglobal.data.entities.City
import com.gabodev.bringglobal.extensions.makeIconURL
import com.gabodev.bringglobal.databinding.ItemCityBinding

class CitiesAdapter(private val listener: CityItemListener) : RecyclerView.Adapter<CityViewHolder>() {

    interface CityItemListener {
        fun onClickedCity(cityName: String)
    }

    private val items = ArrayList<City>()

    fun setItems(items: ArrayList<City>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding: ItemCityBinding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) = holder.bind(items[position])
}

class CityViewHolder(private val itemBinding: ItemCityBinding, private val listener: CitiesAdapter.CityItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var city: City

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: City) {
        this.city = item
        Log.i("APP_BRING_GLOBAL", "Weather: ${city.weather.toString()}")
        // val urlImage = makeIconURL(item.weather?.get(0)?.icon!!)
        itemBinding.tvCity.text = item.name
        itemBinding.tvMain.text = item.weather?.get(0)?.description
        itemBinding.tvTemperature.text = "${item.main?.temp.toString()}°"
       // Glide.with(itemView.context).load(urlImage).into(itemBinding.ivStatus)
    }

    override fun onClick(v: View?) {
        listener.onClickedCity(city.name)
    }
}