package com.vasertoker.wallperapp.models

data class Result(
    val alt_description: String,
    val blur_hash: String,
    val breadcrumbs: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val promoted_at: String,
    val slug: String,
    val sponsorship: Any,
    val tags: List<Tag>,
    val topic_submissions: TopicSubmissionsX,
    val updated_at: String,
    val urls: Urls,
    val user: UserX,
    val width: Int
)