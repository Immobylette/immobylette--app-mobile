package com.immobylette.appmobile.data.enum

enum class ElementState(val label: String = "") {
    NEW("NOUVEAU"),
    VERY_GOOD("TRES BON"),
    GOOD("BON"),
    SO_SO("MOYEN"),
    BAD("MAUVAIS"),
    VERY_BAD("TRES MAUVAIS");

    // Find the ElementState from a given label
    companion object {
        infix fun from(value: String): ElementState? = entries.firstOrNull { it.label == value }
    }
}