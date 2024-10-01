package eu.tutorials.animelistapp.repository.localRepository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eu.tutorials.animelistapp.repository.localRepository.database.anime.AnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeCharacters.AnimeCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.AnimeDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.animeDetails.animeRecommendations.AnimeRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.MangaDetailsDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaCharacters.MangaCharacterDao
import eu.tutorials.animelistapp.repository.localRepository.database.details.mangaDetails.mangaRecommendation.MangaRecommendationDao
import eu.tutorials.animelistapp.repository.localRepository.database.manga.MangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.anime.MyFavouriteAnimeEntity
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaDao
import eu.tutorials.animelistapp.repository.localRepository.database.myFavouriteList.manga.MyFavouriteMangaEntity
import eu.tutorials.animelistapp.repository.remoteRepository.model.anime.AnimeDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.RecommendationDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AnimeDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.AnimeCharactersDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.MangaDetailsDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.mangaCharacters.MangaCharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.manga.MangaDto


@Database(
    entities = [
        AnimeDto::class,
        MangaDto::class,
        AnimeDetailsDto::class,
        AnimeCharactersDto::class,
        MangaDetailsDto::class,
        MangaCharacterDto::class,
        RecommendationDto::class,
        MyFavouriteAnimeEntity::class,
        MyFavouriteMangaEntity::class
    ],
    version = 17,
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