package com.longnp.lnsocial.business.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoSeed(
    val pk: String, // id
    val videoLink: String,
    val hashTagVideo: String,
    val soundId: String,
    val soundTitle: String,
    val soundThumbnail: String,
    val authProfileId: String,
    val numberLike: String,
    val numberComments: String,
    val numberShared: String,
    val description: String,
    val typesVideos: String,
    val nickName: String,
    val avatarLink: String,
): Parcelable