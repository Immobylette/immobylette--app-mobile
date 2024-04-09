package com.immobylette.appmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immobylette.appmobile.ui.shared.component.AppButton
import com.immobylette.appmobile.ui.shared.theme.ImmobyletteappmobileTheme
import com.immobylette.appmobile.ui.shared.theme.LocalAppButtonWidth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImmobyletteappmobileTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AppButton(
                            text = "Quitter",
                            onClick = { println("Clic bouton Quitter")},
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .width(LocalAppButtonWidth.current)
                        )

                        AppButton(
                            text = "Suivant",
                            onClick = { println("Clic bouton Suivant")},
                            isOnLeftSide = false,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .width(LocalAppButtonWidth.current)
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImmobyletteappmobileTheme {
        Greeting("Android")
    }
}