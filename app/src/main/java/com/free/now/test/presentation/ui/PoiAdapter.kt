package com.free.now.test.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.free.now.test.R
import com.free.now.test.data.model.Poi

class PoiAdapter(
    private val list: List<Poi>,
    private val poiListener: (Poi) -> Unit
) : RecyclerView.Adapter<PoiAdapter.PoiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_poi_list, parent, false)
        view.isClickable = true
        return PoiViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PoiViewHolder, position: Int) {
        val poi = list[position]
        holder.fleetType.text = holder.itemView.context.getString(
            if (poi.fleetType.equals("taxi", true)) {
                R.string.fleet_type_taxi
            } else {
                R.string.fleet_type_pool
            }
        )
        (poi.coordinate.latitude.toString() + " , " + poi.coordinate.longitude.toString()).also {
            holder.coordinate.text = it
        }
        holder.layout.setOnClickListener {
            poiListener(poi)
        }
    }

    class PoiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ConstraintLayout = itemView.findViewById(R.id.poi_item_layout)
        val fleetType: TextView = itemView.findViewById(R.id.fleet_type)
        val coordinate: TextView = itemView.findViewById(R.id.coordinates)
    }

}