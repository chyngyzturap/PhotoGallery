package com.pharos.photogallery.domain.model

data class Photo(
        val alt_description: String,
        val id: String,
        val urls: Urls,
    ) {
        data class Urls(
            val full: String,
            val raw: String,
            val regular: String,
            val small: String,
            val small_s3: String,
            val thumb: String
        )
    }
