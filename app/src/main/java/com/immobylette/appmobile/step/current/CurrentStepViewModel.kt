package com.immobylette.appmobile.step.current

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.immobylette.appmobile.data.enum.ElementState
import com.immobylette.appmobile.data.model.Photo
import com.immobylette.appmobile.step.StepState

class CurrentStepViewModel: ViewModel() {
    private val _currentStep = mutableStateOf(StepState())
    val currentStep: State<StepState> get() = _currentStep

    fun getStep(): StepState {
        return _currentStep.value
    }

    fun getState(): ElementState {
        return _currentStep.value.state
    }
    fun getPhotos(): MutableList<Photo> {
        return _currentStep.value.photos
    }
    fun resetStepWithStateAndIsWall(state: ElementState, isWall: Boolean) {
        _currentStep.value = StepState(state = state, isWall = isWall)
    }
    fun changeState(state: ElementState) {
        _currentStep.value = currentStep.value.copy(state = state)
    }

    fun changeDescription(description: String) {
        _currentStep.value = currentStep.value.copy(description = description)
    }
    fun changeErrorDescription(errorDescription: String) {
        _currentStep.value = currentStep.value.copy(errorDescription = errorDescription)
    }

    fun addPhoto(photo: Photo) {
        val photos = _currentStep.value.photos

        photos.add(photo)

        _currentStep.value = _currentStep.value.copy(photos = photos)
    }

    fun removePhoto(index: Int) {
        val photos = _currentStep.value.photos

        photos.removeAt(index)

        _currentStep.value = _currentStep.value.copy(photos = photos)
    }
}