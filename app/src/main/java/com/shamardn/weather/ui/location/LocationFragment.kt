package com.shamardn.weather.ui.location

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.shamardn.weather.R
import com.shamardn.weather.databinding.FragmentLocationBinding
import com.shamardn.weather.ui.base.BaseFragment
import com.shamardn.weather.util.Constants
import com.shamardn.weather.util.FetchLocation
import com.shamardn.weather.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment @Inject constructor(
) : BaseFragment<FragmentLocationBinding>() {
    override val layoutIdFragment = R.layout.fragment_location
    override val viewModel: LocationViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(false)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@LocationFragment.viewModel
        }
        getCurrentLocation()
        handleEvents()
    }


    private fun getCurrentLocation() {
        if (FetchLocation.checkPermissions(requireActivity())) {
            if (FetchLocation.isLocationEnabled(requireActivity())) {
                if (ActivityCompat.checkSelfPermission(requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(requireContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    FetchLocation.requestPermission(requireActivity())
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                        val location: Location?= task.result
                        if (location == null) {
                            Toast.makeText(activity, "NULL Received" , Toast.LENGTH_SHORT).show()
                        }else {
                            lifecycleScope.launch {
                                Toast.makeText(requireContext()," lat == ${location.latitude} lon == ${location.longitude}",Toast.LENGTH_SHORT).show()
                                viewModel.saveLatitude(location.latitude)
                                viewModel.saveLongitude(location.longitude)
                            }
                        }
                }
            } else {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                requireActivity().startActivity(intent)
            }
        } else {
            FetchLocation.requestPermission(requireActivity())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if ( requestCode == Constants.PERMISSION_REQUEST_ACCESS_LOCATION ) {
            if (permissions.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "Granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            } else {
                Toast.makeText(requireContext(), "Denied", Toast.LENGTH_SHORT).show()
                FetchLocation.checkPermissions(requireActivity())
            }
        }
    }

    private fun handleEvents() {
        viewModel.navigateToHome.observeEvent(viewLifecycleOwner) {
            getCurrentLocation()
            findNavController().navigate(R.id.action_locationFragment_to_homeFragment)
        }
    }
}