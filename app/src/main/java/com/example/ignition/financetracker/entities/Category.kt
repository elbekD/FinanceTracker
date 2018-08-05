package com.example.ignition.financetracker.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Elbek D. on 03.08.2018.
 */
@Entity(tableName = "category")
data class Category(val name: String,
                    @PrimaryKey(autoGenerate = true)
                    val id: Long = 0)