package com.immobylette.appmobile.ui.shared.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.data.enum.ElementState
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import kotlin.math.round

@Composable
fun Slider(
    onStateChanged: (ElementState) -> Unit,
    modifier : Modifier = Modifier,
    state: ElementState = ElementState.NEW,
){
    val map = getElementStateSliderMap(0f..100f)

    var sliderPosition by remember { mutableFloatStateOf(getSliderPosition(state, 0f..100f)) }
    Column(modifier = modifier) {
        Slider(
            value = sliderPosition,
            steps = ElementState.entries.size - 2,
            onValueChange = { value -> sliderPosition = round(value) },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                onStateChanged(map[sliderPosition] ?: ElementState.NEW)
            },
            modifier = Modifier.padding(horizontal = 50.dp),
        )
        Row {
            ElementState.entries.reversed().forEach {
                Text(
                    text = it.label.uppercase(),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

fun getElementStateSliderMap(range: ClosedFloatingPointRange<Float>): Map<Float, ElementState> {
    val map = mutableMapOf<Float, ElementState>()
    val step = range.endInclusive / (ElementState.entries.size - 1)
    var current = range.endInclusive
    ElementState.entries.forEach {
        map[current] = it
        current -= step
    }
    return map
}

fun getSliderPosition(elementState: ElementState, range: ClosedFloatingPointRange<Float>): Float {
    val map = getElementStateSliderMap(range)
    return map.entries.find { it.value == elementState }?.key ?: 0f
}

@Preview(showBackground=true)
@Composable
fun SliderPreview(){
    ImmobyletteappmobileTheme {
        Slider(
            state = ElementState.GOOD,
            onStateChanged = {},
            modifier = Modifier.padding(20.dp)
        )
    }
}