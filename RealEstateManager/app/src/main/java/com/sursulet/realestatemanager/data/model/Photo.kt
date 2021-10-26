package com.sursulet.realestatemanager.data.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val image: Bitmap,
    val estateId: Long
)
