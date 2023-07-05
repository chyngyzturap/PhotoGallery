package com.pharos.photogallery.utils

enum class PhotoSortOrder(val value: String) {
    LATEST("latest"),
    OLDEST("oldest"),
    POPULAR("popular");

    companion object {
        fun getRandom(): PhotoSortOrder {
            val values = values()
            return values.random()
        }
    }
}