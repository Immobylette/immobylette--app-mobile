package com.immobylette.appmobile.ui.shared.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.core.view.WindowCompat
import com.immobylette.appmobile.R
import com.immobylette.appmobile.toasts.ToastEvent
import com.immobylette.appmobile.toasts.ToastService
import kotlinx.coroutines.launch

private val DarkColorScheme = darkColorScheme(
    primary = Pink,
    secondary = Blue,
    tertiary = Pink80,
    background = Grey // Set the background color for all views
)

private val LightColorScheme = lightColorScheme(
    primary = Pink,
    secondary = Blue,
    tertiary = Pink40,
    background = Grey // Set the background color for all views
)

@Composable
fun ImmobyletteappmobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val fredokaFontFamily = FontFamily(
        Font(googleFont = GoogleFont("Fredoka"), fontProvider = provider)
    )

    val interFontFamily = FontFamily(
        Font(googleFont = GoogleFont("Inter"), fontProvider = provider)
    )

    val typography = Typography

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            ToastService.toastErrorEvent.collect { event: ToastEvent ->
                Log.d("ToastService", "ToastEvent received: $event")
                ToastService.showToast(
                    activity = context as Activity,
                    title = event.title,
                    message = event.message,
                    type = event.type
                )
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
    )
}