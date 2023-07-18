package com.vasertoker.wallperapp.models

data class Image(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)