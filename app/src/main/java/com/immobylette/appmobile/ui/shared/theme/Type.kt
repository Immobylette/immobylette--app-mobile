package com.immobylette.appmobile.ui.shared.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.immobylette.appmobile.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fredokaFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Fredoka"), fontProvider = provider)
)

val interFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Inter"), fontProvider = provider)
)

val robotoFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(fontFamily = fredokaFontFamily),
    headlineLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Black
    ),
    bodyLarge = TextStyle(
        fontFamily = fredokaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = Black
    ),
    headlineSmall = TextStyle(
        fontFamily = fredokaFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = Pink
    ),
    displayMedium = TextStyle(
        fontFamily = interFontFamily
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)