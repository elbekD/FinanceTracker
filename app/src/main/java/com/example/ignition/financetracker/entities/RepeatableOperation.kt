package com.example.ignition.financetracker.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Elbek D. on 06.08.2018.
 */
@Entity(tableName = "repeatable_operation",
        foreignKeys = [ForeignKey(entity = Operation::class,
                parentColumns = ["id"],
                childColumns = ["operationId"],
                onDelete = ForeignKey.CASCADE)])
data class RepeatableOperation(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0, val operationId: Long,
        val periodDate: Int)