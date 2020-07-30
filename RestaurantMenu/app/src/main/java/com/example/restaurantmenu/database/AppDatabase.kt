package com.example.restaurantmenu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
	abstract fun cartItemDao(): CartItemDao

	companion object {

		private lateinit var INSTANCE: AppDatabase

		fun getDatabase(context: Context): AppDatabase {
			synchronized(AppDatabase::class.java) {
				if (!::INSTANCE.isInitialized) {
					INSTANCE = Room.databaseBuilder(
						context.applicationContext,
						AppDatabase::class.java, "cafe_database"
					)
						.addMigrations(MIGRATION_1_2)
						.build()
				}
			}

			return INSTANCE
		}
	}
}