package com.immobylette.appmobile.element.newstep

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.immobylette.appmobile.step.StepState
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID

class NewStepViewModel: ViewModel() {
    fun addStep(inventoryId: UUID, elementId: UUID, step: StepState, onStepAdded: () -> Unit) {
        viewModelScope.launch {
            val gson = Gson()
            val stepDto = step.toDto()
            val stepJson = gson.toJson(stepDto).trimIndent()

            val stepBody = stepJson.toRequestBody("application/json".toMediaTypeOrNull())

            val files = step.photos.map { photo -> photo.file }

            val fileParts = files.map { file ->
                val requestFile = file!!.asRequestBody("image/jpeg".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("photos", file.name, requestFile)
            }

            val result = RetrofitHelper.inventoryService.postElementStep(inventoryId, elementId, stepBody, fileParts)

            if (result.isSuccessful) {
                onStepAdded()
            } else {
                Log.e("RequestError", "Error adding step")
            }
        }
    }
}