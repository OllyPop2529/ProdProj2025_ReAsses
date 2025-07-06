package com.example.pp2025_reasses.pages

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.pp2025_reasses.ATD_ViewModel
import com.example.pp2025_reasses.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


@Composable
fun LocationPage(
    onSettingsClick: () -> Unit,
    viewModel: ATD_ViewModel
)
{
    val modifier : Modifier = Modifier
        .fillMaxSize()


    Column(
        modifier = Modifier.fillMaxSize()
    )

    {
        // Top 2/3
        // Contains Connection Prompt / Device status
        Column(
            modifier = modifier
                .weight(8f)
                .padding(top = 15.dp)
                .background(MaterialTheme.colorScheme.background)
        )
        {
            Box()
            {
                GoogleMapView(viewModel)
                Text(
                    text =
                    viewModel.getUserLocation()
                        .trim(),
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                        .align(Alignment.BottomCenter)

                )
            }
        }



        ////////////////////////////////////////////////////////

        // Lower 1/3
        // Contains setting + navigation
        Column(
            modifier = modifier
                .weight(2f)
                .padding(bottom = 15.dp)
                .background(MaterialTheme.colorScheme.primary)

        )
        {
            val buttonModifier : Modifier = Modifier
                .padding(all = 25.dp)
                .fillMaxSize()

            NavigationButton(
                onClick = onSettingsClick,
                modifier = buttonModifier,
                text = stringResource(R.string.settings_button),
                image = R.drawable.setting)
        }
        ////////////////////////////////////////////////////////
    }

}

@Composable
fun NavigationButton(
    onClick: () -> Unit,
    modifier: Modifier,
    image: Int,
    text : String
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(5.dp,0.dp),
        colors = ButtonColors(
            containerColor = Color(54, 84, 100),
            contentColor = Color.White,
            disabledContainerColor = Color(59, 89, 105),
            disabledContentColor = Color.LightGray
        )
    )
    {
        Row(
            modifier = Modifier
        )
        {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)

            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .weight(1.5f)
                    .padding(5.dp)
                    .align(Alignment.CenterVertically)

            )

        }

    }
}

@Composable
fun GoogleMapView(
    viewModel: ATD_ViewModel
) {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            onCreate(Bundle())
        }
    }

    val mapType by viewModel.mapType.collectAsState()

    var permissionGranted by remember { mutableStateOf(false) }

    // Ask for location permission
    RequestLocationPermission {
        permissionGranted = true
    }

    AndroidView(factory = { mapView }) { _ ->
        mapView.getMapAsync { googleMap ->
            googleMap.mapType = mapType

            if (permissionGranted) {
                try {
                    googleMap.isMyLocationEnabled = true


                    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        if (location != null) {
                            val userLatLng = LatLng(location.latitude, location.longitude)
                            viewModel.updateUserLocation(userLatLng)
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15f))
                        }
                    }

                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            } else {
                // Optional fallback marker if location isn't available yet
                val fallbackLocation = LatLng(53.8, -1.59) //LBU Headingley
                googleMap.addMarker(MarkerOptions().position(fallbackLocation).title("Default Marker"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fallbackLocation, 10f))
            }
        }
    }

    // Map lifecycle binding
    val lifecycleObserver = rememberMapLifecycle(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
}



@Composable
fun RequestLocationPermission(onGranted: () -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) onGranted()
    }

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                onGranted()
            }
            else -> {
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }
}


@Composable
fun rememberMapLifecycle(mapView: MapView): DefaultLifecycleObserver {
    return remember {
        object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) = mapView.onResume()
            override fun onStart(owner: LifecycleOwner) = mapView.onStart()
            override fun onStop(owner: LifecycleOwner) = mapView.onStop()
            override fun onPause(owner: LifecycleOwner) = mapView.onPause()
            override fun onDestroy(owner: LifecycleOwner) = mapView.onDestroy()
            override fun onCreate(owner: LifecycleOwner) = mapView.onCreate(Bundle())
        }
    }
}


@Preview
@Composable
fun ActivityPreview()
{

}
