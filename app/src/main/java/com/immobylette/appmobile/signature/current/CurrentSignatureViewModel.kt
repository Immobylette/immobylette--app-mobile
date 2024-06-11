package com.immobylette.appmobile.signature.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.immobylette.appmobile.data.dto.SignatureDto
import com.immobylette.appmobile.data.enum.SignatureType
import com.immobylette.appmobile.signature.SignatureState
import com.immobylette.appmobile.utils.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID


class CurrentSignatureViewModel: ViewModel() {

    private val _currentSignature = MutableStateFlow(SignatureState())

    val currentSignature: StateFlow<SignatureState>  get() {
        return _currentSignature.asStateFlow()
    }

    fun setCurrentSignature(signature: SignatureState) {
        _currentSignature.value = signature
    }

    fun signInventory(inventoryId: UUID, type: SignatureType) {
        val signatureDto = SignatureDto(type)
        viewModelScope.launch {
            RetrofitHelper.inventoryService.sign(inventoryId, signatureDto)
        }
    }
}