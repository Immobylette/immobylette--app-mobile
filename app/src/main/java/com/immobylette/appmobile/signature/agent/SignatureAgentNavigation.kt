package com.immobylette.appmobile.signature.agent

import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.immobylette.appmobile.R
import com.immobylette.appmobile.agent.current.CurrentAgentViewModel
import com.immobylette.appmobile.data.enum.SignatureType
import com.immobylette.appmobile.signature.SignaturePage
import com.immobylette.appmobile.signature.SignatureState
import com.immobylette.appmobile.signature.current.CurrentSignatureViewModel

const val signatureAgentRoute = "signature-agent"
fun NavGraphBuilder.signatureAgentNavigation(
    currentSignatureViewModel: CurrentSignatureViewModel,
    currentAgentViewModel: CurrentAgentViewModel,
    onNavigateToSignatureTenant: () -> Unit
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
                currentSignatureViewModel.signInventory(currentAgentViewModel.currentAgent.value.id, SignatureType.AGENT)
            },
            onNavigate = onNavigateToSignatureTenant,
        )
    }
}

fun NavController.navigateToAgentSignature() {
    navigate(signatureAgentRoute)
}