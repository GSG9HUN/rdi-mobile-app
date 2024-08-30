package eu.tutorials.animelistapp.repository.localRepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity


@Database(entities = [AnimeEntity::class, MangaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun mangaDao(): MangaDao
}