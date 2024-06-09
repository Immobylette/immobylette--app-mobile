package com.immobylette.appmobile.signature.tenant

import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.R
import com.immobylette.appmobile.data.enum.SignatureType
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.signature.SignaturePage
import com.immobylette.appmobile.signature.SignatureState
import com.immobylette.appmobile.signature.current.CurrentSignatureViewModel

const val signatureTenantRoute = "signature-tenant"
fun NavGraphBuilder.signatureTenantNavigation(
    currentSignatureViewModel: CurrentSignatureViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    onNavigateToEndingPage: () -> Unit
) {
    composable(signatureTenantRoute) {
        val state: SignatureState by currentSignatureViewModel.currentSignature.collectAsStateWithLifecycle()
        val signatory = currentPropertyViewModel.currentProperty.value.currentTenant

        if (signatory != null) {
            currentSignatureViewModel.setCurrentSignature(
                SignatureState(
                    signatory = signatory.name,
                )
            )
        }

        SignaturePage(
            state = state,
            title = stringResource(id = R.string.label_tenant_signature),
            onSign = {
                currentSignatureViewModel.signInventory(currentPropertyViewModel.currentProperty.value.id, SignatureType.TENANT)
            },
            onNavigate = onNavigateToEndingPage,
        )
    }
}

fun NavController.navigateToTenantSignature() {
    navigate(signatureTenantRoute)
}