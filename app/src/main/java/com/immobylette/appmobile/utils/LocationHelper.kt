package com.immobylette.appmobile.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@SuppressLint("StaticFieldLeak")
object LocationHelper {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var context: Context

    fun init(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        this.context = context
    }

    suspend fun getLocation(): Pair<Double, Double> {
        if (::fusedLocationClient.isInitialized) {
            val location = suspendCancellableCoroutine<Location?> { continuation ->
                if (ActivityCompat.checkSelfPermission(
                        this.context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this.context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    continuation.resumeWithException(SecurityException("Location permission not granted"))
                }
                fusedLocationClient.lastLocation.addOnSuccessListener { result ->
                    continuation.resume(result)
                }.addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
            }
            return Pair(location!!.latitude, location.longitude)
        } else {
            error("LocationHelper not initialized")
        }
    }
}