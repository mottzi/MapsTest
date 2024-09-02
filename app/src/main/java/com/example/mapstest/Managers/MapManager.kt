package com.example.mapstest.Managers

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapManager : ViewModel() {
    private val _cameraPositionState = MutableStateFlow(CameraPositionState(position = CameraPosition.fromLatLngZoom(LatLng(47.51820352086017, 7.691802061359962), 13f)))
    val cameraPositionState = _cameraPositionState.asStateFlow()

//    fun updateCameraPosition(newPosition: CameraPosition) {
//        viewModelScope.launch {
//            _cameraPositionState.value = CameraPositionState(newPosition)
//        }
//    }
}