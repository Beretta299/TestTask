package com.karas.testtask.db

object DBContract {
    object BootsEntity {
        const val TABLE_NAME = "boots_entity"
        const val BOOT_ID = "id"
        const val BOOT_STAMP = "timestamp"
    }

    object UserEntity {
        const val TABLE_NAME = "user_entity"
        const val TOTAL_DISMISSALS_ALLOWED = "total_dismissals_allowed"
        const val INTERVAL_BETWEEN_DISMISSALS = "interval_between_dismissals"
        const val DISMISSED_TOTAL = "dismissed_total"
    }
}