package eu.tutorials.animelistapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.tutorials.animelistapp.repository.remoteRepository.ApiService
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRemoteDataSource
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRemoteDataSource
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import eu.tutorials.animelistapp.repository.MangaRepositoryImpl
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRepository
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRepository
import eu.tutorials.animelistapp.repository.localRepository.database.AppDatabase
import eu.tutorials.animelistapp.repository.localRepository.datasource.anime.AnimeLocalDataSource
import eu.tutorials.animelistapp.repository.localRepository.datasource.manga.MangaLocalDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeRemoteDataSource: AnimeRemoteDataSource,
        animeLocalDataSource: AnimeLocalDataSource
    ): AnimeRepository {
        return AnimeRepositoryImpl(
            animeRemoteDataSource, animeLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideMangaRepository(
        mangaRemoteDataSource: MangaRemoteDataSource,
        mangaLocalDataSource: MangaLocalDataSource
    ): MangaRepository {
        return MangaRepositoryImpl(
            mangaRemoteDataSource, mangaLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "anime_manga_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAnimeDao(database: AppDatabase) = database.animeDao()

    @Provides
    fun provideAnimeCharacterDao(database: AppDatabase) = database.animeCharacterDao()

    @Provides
    fun provideAnimeRecommendationDao(database: AppDatabase) = database.animeRecommendationDao()

    @Provides
    fun provideAnimeDetailsDao(database: AppDatabase) = database.animeDetailsDao()

    @Provides
    fun provideMangaDao(database: AppDatabase) = database.mangaDao()

    @Provides
    fun provideMangaCharacterDao(database: AppDatabase) = database.mangaCharacterDao()

    @Provides
    fun provideMangaRecommendationDao(database: AppDatabase) = database.mangaRecommendationDao()

    @Provides
    fun provideMangaDetailsDao(database: AppDatabase) = database.mangaDetailsDao()

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
