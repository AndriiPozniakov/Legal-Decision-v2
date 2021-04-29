package com.hazymoonstudio.legaldecision.utils

enum class ComponentType(val type: Int) {
    TEXT(0), TITLE(1), TITLE_AND_SUBTITLE(2), IMG(3);

    companion object {
        fun create(type: Int): ComponentType {
            return when (type) {
                1 -> TITLE
                2 -> TITLE_AND_SUBTITLE
                3 -> IMG
                else -> TEXT
            }
        }
    }
}