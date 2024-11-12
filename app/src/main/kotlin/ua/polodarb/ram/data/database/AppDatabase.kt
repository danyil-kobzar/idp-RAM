package ua.polodarb.ram.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ua.polodarb.ram.data.database.core.Converters
import ua.polodarb.ram.data.database.dao.CharactersDao
import ua.polodarb.ram.data.database.dao.EpisodesDao
import ua.polodarb.ram.data.database.entity.character.CharacterEntity
import ua.polodarb.ram.data.database.entity.episodes.EpisodeEntity
import ua.polodarb.ram.data.database.entity.episodes.SeasonEntity
import ua.polodarb.ram.data.database.entity.paging.CharacterRemoteKey
import ua.polodarb.ram.data.database.entity.paging.EpisodesRemoteKey

@Database(
    version = 1,
    entities = [
        CharacterEntity::class,
        CharacterRemoteKey::class,
        EpisodeEntity::class,
        EpisodesRemoteKey::class,
        SeasonEntity::class
    ],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

    abstract fun episodesDao(): EpisodesDao

}
