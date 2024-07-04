package com.karas.testtask.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karas.testtask.db.DBContract

@Entity(tableName = DBContract.BootsEntity.TABLE_NAME)
data class BootsEntity(@PrimaryKey(autoGenerate = true)
                       @ColumnInfo(name = DBContract.BootsEntity.BOOT_ID) var id: Int? = null,
                       @ColumnInfo(name = DBContract.BootsEntity.BOOT_STAMP) var timeStamp: Long)
