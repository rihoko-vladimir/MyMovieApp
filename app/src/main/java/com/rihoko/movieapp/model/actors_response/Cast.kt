package com.rihoko.movieapp.model.actors_response

import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    val cast_id: Int,
    val character: String,
    val original_name: String,
    val profile_path: String
)