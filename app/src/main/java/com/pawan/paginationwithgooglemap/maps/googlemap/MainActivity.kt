package com.pawan.paginationwithgooglemap.maps.googlemap


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.pawan.paginationwithgooglemap.BuildConfig
import com.pawan.paginationwithgooglemap.R
import com.pawan.paginationwithgooglemap.maps.place.Place
import com.pawan.paginationwithgooglemap.maps.place.PlacesReader
import java.lang.String


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ElevationViewModel
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorPrimary)
        BitmapHelper.vectorToBitmap(this, R.drawable.baseline_crop_square_24, color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[ElevationViewModel::class.java]
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
            focusMarkers(googleMap)
            googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
            googleMap.setOnMarkerClickListener { marker ->
                val latAndLng =
                    String.format("%f,%f", marker.position.latitude, marker.position.longitude)
                viewModel.getElevation(
                    latAndLng,
                    //"28.6708243%2077.0671703",
                    BuildConfig.GOOGLE_MAPS_API_KEY
                )
                viewModel.observeMovieLiveData().observe(this) { currentElevation ->
                    Toast.makeText(
                        this@MainActivity,
                        "Found Elevation: " + currentElevation[0].elevation,
                        Toast.LENGTH_LONG
                    ).show()

                }
                false
            }
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
                    .icon(bicycleIcon)
            )
            marker?.tag = place
        }
    }

    private fun focusMarkers(googleMap: GoogleMap) {
        val builder = LatLngBounds.Builder()
        places.forEach { place ->
            builder.include(place.latLng)
        }
        val bounds = builder.build()
        val padding = 0 // offset from edges of the map in pixels
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        googleMap.animateCamera(cu);
    }
}