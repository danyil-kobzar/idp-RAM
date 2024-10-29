package ua.polodarb.ram.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.polodarb.ram.data.database.core.Converters
import ua.polodarb.ram.data.database.dao.CharactersDao
import ua.polodarb.ram.data.database.entity.CharacterEntity

@Database(
    version = 1,
    entities = [
        CharacterEntity::class,
        // todo add
    ],
    exportSchema = true,
)
@TypeConverters(Converters::class) // remove
abstract class AppDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

}