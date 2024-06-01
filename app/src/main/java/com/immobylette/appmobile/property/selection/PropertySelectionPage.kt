package com.immobylette.appmobile.property.selection

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import com.immobylette.appmobile.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.immobylette.appmobile.ui.shared.component.Button
import com.immobylette.appmobile.ui.shared.component.PropertyCard


import java.util.UUID

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PropertySelectionPage (
    state: PropertyListState,
    fetchPropertyList: () -> Unit,
    fetchProperty: (UUID) -> Unit,
    clearPropertyList: () -> Unit,
    saveCurrentProperty: (PropertyState) -> Unit,
    onNavigateToChangeAgent: () -> Unit,
    onNavigateToPropertySelected: () -> Unit
) {
    val listState = rememberLazyListState()

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    val locationPermissionsState = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val context = LocalContext.current
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            fetchPropertyList()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        clearPropertyList()
    }

    LaunchedEffect(reachedBottom) {
        if(locationPermissionsState.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
            // Permission granted
            fetchPropertyList()
        } else {
            launcherMultiplePermissions.launch(locationPermissionsState)
        }
    }

    Scaffold(
        bottomBar = {
            Button(
                text = stringResource(R.string.label_button_change_agent),
                onClick = onNavigateToChangeAgent,
                modifier = Modifier.width(300.dp),
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 50.dp),
        ) {

            LazyColumn(state = listState) {
                items(state.properties) { property ->
                    PropertyCard(
                        address = property.address!!.toFormattedString(stringResource(id = R.string.label_floor)),
                        nbRooms =  property.nbRooms,
                        propertyType = property.propertyType,
                        propertyClass = property.propertyClass,
                        owner = property.owner,
                        photoUrl = property.photo,
                        distance = property.distance,
                        inProgress = property.currentInventory,
                        onExpanded = {
                            fetchProperty(property.id)
                        },
                    ) {
                        saveCurrentProperty(property)
                        onNavigateToPropertySelected()
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }


}