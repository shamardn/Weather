package com.shamardn.weather.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.shamardn.weather.ui.main.MainActivity
import com.shamardn.weather.util.Constants.PERMISSION_REQUEST_ACCESS_LOCATION
import java.util.*
import javax.inject.Inject


class FitchLocation @Inject constructor(
    private val activity: MainActivity
    ) {
    private val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    private var longitude = 0.0
    private var latitude = 0.0

    private fun setLatitude(lat: String){
        latitude = lat.toDouble()
    }

    private fun setLongitude(long: String){
        longitude = long.toDouble()
    }

    fun getCityName(): String {
        if (latitude != 0.0 && longitude != 0.0)
        return getCity(latitude,longitude)
        return getCity(27.25,33.82)
    }

    private fun getCity(lat: Double,long: Double): String{
        var cityName: String?
        val geoCoder = Geocoder(activity, Locale.getDefault())
        val address = geoCoder.getFromLocation(lat, long,1)
        cityName = address?.get(0)?.adminArea
        if (cityName == null){
            cityName = address?.get(0)?.locality
            if (cityName == null){
                cityName = address?.get(0)?.subAdminArea
            }
        }
        return cityName.toString()
    }

    init {
        getCurrentLocation()
    }

    fun getCurrentLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(activity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(activity,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(activity) { task ->
                    val location: Location?= task.result
                    if (location == null) {
                        Toast.makeText(activity, "NULL Received" , Toast.LENGTH_SHORT).show()
                    }else {
                        setLongitude(location.longitude.toString())
                        setLatitude(location.latitude.toString())
                    }
                }
            } else {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                activity.startActivity(intent)
            }
        } else {
            requestPermission()
        }
    }

    private fun checkPermissions(): Boolean {
        return (ActivityCompat.checkSelfPermission(activity,
            android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }
}