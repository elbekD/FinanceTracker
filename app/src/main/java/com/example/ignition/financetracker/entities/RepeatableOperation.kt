package com.example.ignition.financetracker.entities

import android.arch.persistence.room.*

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
        val id: Long = 0,
        val walletName: String,
        val operationId: Long,
        val periodDate: Int)

class PeriodicOperation {
    @Embedded
    var rOperation: RepeatableOperation? = null
    @Relation(entity = Operation::class,
            parentColumn = "operationId",
            entityColumn = "id")
    var operations: List<Operation>? = null
}