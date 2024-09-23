package eu.tutorials.animelistapp.repository.localRepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterEntity
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationEntity
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaEntity
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity


@Database(
    entities = [
        AnimeEntity::class,
        MangaEntity::class,
        AnimeDetailsEntity::class,
        AnimeCharacterEntity::class,
        AnimeRecommendationEntity::class,
        MangaDetailsEntity::class,
        MangaCharacterEntity::class,
        MangaRecommendationEntity::class,
        MyFavouriteAnimeEntity::class,
        MyFavouriteMangaEntity::class
    ],
    version = 13,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
    abstract fun mangaDao(): MangaDao
    abstract fun animeDetailsDao(): AnimeDetailsDao
    abstract fun animeCharacterDao(): AnimeCharacterDao
    abstract fun animeRecommendationDao(): AnimeRecommendationDao
    abstract fun mangaDetailsDao(): MangaDetailsDao
    abstract fun mangaCharacterDao(): MangaCharacterDao
    abstract fun mangaRecommendationDao(): MangaRecommendationDao
    abstract fun myFavouriteAnimeDao(): MyFavouriteAnimeDao
    abstract fun myFavouriteMangaDao(): MyFavouriteMangaDao
}