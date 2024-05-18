package com.immobylette.appmobile.data.dto

import java.net.URL

data class PhotoUrlDto(
    val url: URL = URL("http://placekitten.com/200/300"),
    val description: String? = ""
)
