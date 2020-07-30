package com.example.restaurantmenu.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
	override fun migrate(database: SupportSQLiteDatabase) {
		database.execSQL("ALTER TABLE cart_item ADD COLUMN dish_id BIGINT NOT NULL DEFAULT 0")
	}
}