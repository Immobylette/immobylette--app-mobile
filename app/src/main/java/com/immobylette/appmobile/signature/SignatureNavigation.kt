package com.immobylette.appmobile.signature

import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.R
import com.immobylette.appmobile.agent.current.CurrentAgentViewModel
import com.immobylette.appmobile.data.enum.SignatureType
import com.immobylette.appmobile.inventory.current.CurrentInventoryViewModel
import com.immobylette.appmobile.property.current.CurrentPropertyViewModel
import com.immobylette.appmobile.signature.current.CurrentSignatureViewModel

const val signatureAgentRoute = "signature-agent"

const val signatureTenantRoute = "signature-tenant"

fun NavGraphBuilder.signatureNavigation(
    currentSignatureViewModel: CurrentSignatureViewModel,
    currentAgentViewModel: CurrentAgentViewModel,
    currentPropertyViewModel: CurrentPropertyViewModel,
    currentInventoryViewModel: CurrentInventoryViewModel,
    onNavigateToSignatureTenant: () -> Unit,
    onNavigateToEndingPage: () -> Unit
) {
    composable(signatureAgentRoute) {
        val state: SignatureState by currentSignatureViewModel.currentSignature.collectAsStateWithLifecycle()

        currentSignatureViewModel.setCurrentSignature(
            SignatureState(
                signatory = currentAgentViewModel.currentAgent.value.name,
            )
        )

        SignaturePage(
            state = state,
            title = stringResource(id = R.string.label_agent_signature),
            onSign = {
                currentSignatureViewModel.signInventory(currentInventoryViewModel.currentInventory.value, SignatureType.AGENT)
            },
            onNavigate = onNavigateToSignatureTenant,
        )
    }

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
                currentSignatureViewModel.signInventory(currentInventoryViewModel.currentInventory.value, SignatureType.TENANT)
            },
            onNavigate = onNavigateToEndingPage,
        )
    }
}

fun NavController.navigateToTenantSignature() {
    navigate(signatureTenantRoute)
}

fun NavController.navigateToAgentSignature() {
    navigate(signatureAgentRoute)
}