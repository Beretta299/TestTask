package com.karas.testtask.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karas.testtask.db.DBContract

@Entity(DBContract.UserEntity.TABLE_NAME)
data class UserEntity(@PrimaryKey var id: Int? = null, @ColumnInfo(DBContract.UserEntity.TOTAL_DISMISSALS_ALLOWED) var totalDismissAllowed: Int = 5,
                      @ColumnInfo(DBContract.UserEntity.INTERVAL_BETWEEN_DISMISSALS) var dismissIntervalValue: Int = 20,
                        @ColumnInfo(DBContract.UserEntity.DISMISSED_TOTAL) var dismissedTotal: Int = 0)