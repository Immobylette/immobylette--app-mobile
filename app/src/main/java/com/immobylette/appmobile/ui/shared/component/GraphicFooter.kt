package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme

@Composable
fun GraphicFooter(modifier: Modifier = Modifier) {
    val colors = MaterialTheme.colorScheme
    Canvas(modifier = modifier.fillMaxSize()) {
        drawCurve(
            startX = size.width,
            startY = size.height * 0.7f,
            controlX1 = size.width,
            controlY1 = size.height * 0.8f,
            controlX2 = size.width * 0.45f,
            controlY2 = size.height * 0.9f,
            endX = 0f,
            endY = size.height,
            color = colors.secondary
        )
        drawCurve(
            startX = 0f,
            startY = size.height * 0.7f,
            controlX1 = size.width * 0.25f,
            controlY1 = size.height * 0.9f,
            controlX2 = size.width * 0.85f,
            controlY2 = size.height * 0.95f,
            endX = size.width,
            endY = size.height * 0.9f,
            color = colors.primary
        )
    }
}

@Preview(showBackground=true)
@Composable
fun GraphicFooterPreview() {
    ImmobyletteappmobileTheme(darkTheme = false, dynamicColor = false) {
        GraphicFooter()
    }
}

private fun DrawScope.drawCurve(
    startX: Float,
    startY: Float,
    controlX1: Float,
    controlY1: Float,
    controlX2: Float,
    controlY2: Float,
    endX: Float,
    endY: Float,
    color: Color
) {
    val path = Path()
    path.moveTo(startX, startY)
    path.cubicTo(
        controlX1, controlY1,
        controlX2, controlY2,
        endX, endY
    )
    path.lineTo(endX, size.height)
    path.lineTo(startX, size.height)

    drawPath(
        path = path,
        color = color
    )
}