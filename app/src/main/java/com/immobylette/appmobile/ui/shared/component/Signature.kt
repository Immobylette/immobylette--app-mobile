package com.immobylette.appmobile.ui.shared.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.R
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.PinkExtraLight

@Composable
fun Signature(
    name: String,
    modifier: Modifier = Modifier
) {
    var signed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .height(150.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = name,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(id = R.string.label_is_about_to_sign),
                    style = MaterialTheme.typography.displayMedium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = signed,
                onCheckedChange = {
                    signed = it
                },
            )
        }
    }
}

@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
){
    var _checked by remember { mutableStateOf(checked) }
    val iconOffset by animateDpAsState(
        targetValue = if (_checked) 155.dp else 0.dp,
        finishedListener = {
            if (_checked) {
                onCheckedChange(_checked)
            }
        },
        label = "signature",
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        )
    )
    val backgroundColor = if (_checked) PinkExtraLight else MaterialTheme.colorScheme.primary
    val iconBackgroundColor = if (_checked) MaterialTheme.colorScheme.primary else PinkExtraLight

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .height(50.dp)
            .width(200.dp)
            .animateContentSize()
            .background(backgroundColor)
            .clickable {
                _checked = !_checked
            }
    ){
        Box(
            modifier = Modifier
                .padding(5.dp)
                .offset(iconOffset, 0.dp)
        ){
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(iconBackgroundColor)
                    .size(35.dp),
                tint = backgroundColor
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.label_switch_to_sign),
                style = MaterialTheme.typography.titleSmall,
                color = iconBackgroundColor,
                fontWeight = FontWeight(600)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SignaturePreview(){
    ImmobyletteappmobileTheme {
        Signature(
            name = "John Doe"
        )
    }
}