package eu.tutorials.animelistapp.repository.localRepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.animeDetails.AnimeDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.animeDetails.AnimeDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity


@Database(
    entities = [AnimeEntity::class, MangaEntity::class, AnimeDetailsEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun mangaDao(): MangaDao
    abstract fun animeDetailsDao(): AnimeDetailsDao
}