package com.immobylette.appmobile.signature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.ui.shared.component.GraphicFooter
import com.immobylette.appmobile.ui.shared.component.Signature
import com.immobylette.appmobile.ui.shared.component.Title
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun SignaturePage(
    state: SignatureState,
    title: String,
    onSign: () -> Unit,
    onNavigate: () -> Unit
) {

    fun onCheckedChange(checked: Boolean) {
        if (checked) {
            onSign()
            onNavigate()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(32.dp)
        ) {
            Row {
                Title(text = title)
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier
                           .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(16.dp)
            ){
                Signature(name = state.signatory, onCheckedChange = { onCheckedChange(it) })
            }
        }
    }
    GraphicFooter()
}

@Preview(showBackground = true)
@Composable
fun SignaturePagePreview() {
    ImmobyletteappmobileTheme {
        SignaturePage(
            state = SignatureState(),
            title = "Agent Signature",
            onSign = {},
            onNavigate = {}
        )
    }
}