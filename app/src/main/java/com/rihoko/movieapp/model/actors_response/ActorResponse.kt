package com.rihoko.movieapp.model.actors_response

import kotlinx.serialization.Serializable

@Serializable
data class ActorResponse(
    val cast: List<Cast>,
    val id: Int
)