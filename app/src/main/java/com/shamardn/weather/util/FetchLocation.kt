package com.shamardn.weather.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.shamardn.weather.util.Constants.PERMISSION_REQUEST_ACCESS_LOCATION
import java.util.*


object FetchLocation {
//    fun getCityName(activity: Activity): String {
//        if (latitude != 0.0 && longitude != 0.0)
//        return getCity(activity, latitude,longitude)
//        return getCity(activity, 15.25,30.82)
//    }

    private fun getCity(activity: Activity,lat: Double,long: Double): String{
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

     fun checkPermissions(activity: Activity): Boolean {
        return (ActivityCompat.checkSelfPermission(activity,
            android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_REQUEST_ACCESS_LOCATION
        )
    }

    fun isLocationEnabled(activity: Activity): Boolean {
        val locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }
}