package com.pharos.photogallery.utils

data class Error(val status_code: Int = 0,
                 val detail: String? = null)