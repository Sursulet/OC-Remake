package com.sursulet.realestatemanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Estate(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String,
    val price: Double
)
