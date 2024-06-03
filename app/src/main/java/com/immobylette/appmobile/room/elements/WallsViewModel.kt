package com.immobylette.appmobile.room.elements

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.immobylette.appmobile.data.dto.StepSentDto
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID
import com.immobylette.appmobile.data.enum.ElementState as ElementStateEnum
import kotlin.io.path.createTempFile

class WallsViewModel: ViewModel() {

    private val _walls = MutableStateFlow(ElementListState())

    val walls: StateFlow<ElementListState>
        get() {
            return _walls.asStateFlow()
        }

    fun fetchWallList(inventoryId: UUID){
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getWalls(inventoryId)

            if (result.isSuccessful) {
                val walls = ElementListState(elements = result.body()!!.map { it.toState() })

                _walls.update { current ->
                    current.copy(elements = walls.elements)
                }
            }else {
                Log.e("RequestError", "Error fetching walls")
            }
        }
    }

    fun fetchWall(inventoryId: UUID, wallId: UUID){
        viewModelScope.launch {
            val result = RetrofitHelper.inventoryService.getElement(inventoryId, wallId)

            if (result.isSuccessful) {
                val wall = result.body()!!.toState()

                _walls.update { current ->
                    current.copy(
                        elements = current.elements.map { element ->
                            if (element.id == wallId) {
                                ElementState(
                                    id = element.id,
                                    name = wall.name,
                                    description = wall.description,
                                    type = wall.type,
                                    attributes = wall.attributes,
                                    photo = wall.photo,
                                    basePhotos = wall.basePhotos,
                                    previousPhotos = wall.previousPhotos,
                                    state = element.state,
                                    checked = element.checked,
                                    nbBasePhotos = element.nbBasePhotos,
                                    nbPreviousPhotos = element.nbPreviousPhotos,
                                    fetched = true
                                )
                            } else {
                                element
                            }
                        }
                    )
                }
            }else {
                Log.e("RequestError", "Error fetching wall")
            }
        }
    }


    fun check(id: UUID, state: ElementStateEnum, inventoryId: UUID) {

            viewModelScope.launch {
                val element: ElementState =
                    _walls.value.elements.find { element -> element.id == id }!!

                val photoDescriptions = element.previousPhotos.map { photo -> photo.description ?: "" }

                val stepDto = StepSentDto(null, null, element.state, photoDescriptions)

                val files = element.previousPhotos.map { photo ->
                        val inputStream = photo.url.openStream()

                        val tempFile = createTempFile().toFile()

                        tempFile.outputStream().use { fileOutput ->
                            inputStream.copyTo(fileOutput)
                        }

                        tempFile
                }

                val gson = Gson()
                val stepJson = gson.toJson(stepDto).trimIndent()

                val stepBody = stepJson.toRequestBody("application/json".toMediaTypeOrNull())

                val fileParts = files.map { file ->
                    val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("photos", file.name, requestFile)
                }

                val result = RetrofitHelper.inventoryService.postElementStep(
                    inventoryId,
                    element.id,
                    stepBody,
                    fileParts
                )

                if (result.isSuccessful) {
                    _walls.update { current ->
                        current.copy(
                            elements = current.elements.map { element ->
                                if (element.id == id) {
                                    element.copy(state = state, checked = true)
                                } else {
                                    element
                                }
                            }
                        )
                    }
                } else {
                    Log.e("RequestError", "Error adding step")
                }
            }
    }

    fun checkAll(inventoryId: UUID) {
        _walls.value.elements.forEach { element ->
            if (!element.checked) {
                check(element.id, element.state, inventoryId) // We apply the previous state as the new one
            }
        }
    }
}