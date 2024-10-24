package eu.tutorials.animelistapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.tutorials.animelistapp.domain.anime.AnimeDomain
import eu.tutorials.animelistapp.domain.anime.AnimeDomainImpl
import eu.tutorials.animelistapp.domain.animeDetails.AnimeDetailsDomain
import eu.tutorials.animelistapp.domain.animeDetails.AnimeDetailsDomainImpl
import eu.tutorials.animelistapp.domain.animeSearch.AnimeSearchDomain
import eu.tutorials.animelistapp.domain.animeSearch.AnimeSearchDomainImpl
import eu.tutorials.animelistapp.domain.manga.MangaDomain
import eu.tutorials.animelistapp.domain.manga.MangaDomainImpl
import eu.tutorials.animelistapp.domain.mangaDetails.MangaDetailsDomain
import eu.tutorials.animelistapp.domain.mangaDetails.MangaDetailsDomainImpl
import eu.tutorials.animelistapp.domain.mangaSearch.MangaSearchDomain
import eu.tutorials.animelistapp.domain.mangaSearch.MangaSearchDomainImpl
import eu.tutorials.animelistapp.domain.myAnimeFavouriteList.MyFavouriteAnimeDomain
import eu.tutorials.animelistapp.domain.myAnimeFavouriteList.MyFavouriteAnimeDomainImpl
import eu.tutorials.animelistapp.domain.myMangaFavouriteList.MyFavouriteMangaDomain
import eu.tutorials.animelistapp.domain.myMangaFavouriteList.MyFavouriteMangaDomainImpl
import eu.tutorials.animelistapp.domain.myProfile.MyProfileDomain
import eu.tutorials.animelistapp.domain.myProfile.MyProfileDomainImpl
import eu.tutorials.animelistapp.repository.remoteRepository.ApiService
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.anime.AnimeRemoteDataSource
import eu.tutorials.animelistapp.repository.remoteRepository.datasource.manga.MangaRemoteDataSource
import eu.tutorials.animelistapp.repository.AnimeRepositoryImpl
import eu.tutorials.animelistapp.repository.MangaRepositoryImpl
import eu.tutorials.animelistapp.repository.MyProfileRepositoryImpl
import eu.tutorials.animelistapp.repository.AnimeRepository
import eu.tutorials.animelistapp.repository.MangaRepository
import eu.tutorials.animelistapp.repository.MyProfileRepository
import eu.tutorials.animelistapp.repository.localRepository.database.AppDatabase
import eu.tutorials.animelistapp.repository.localRepository.datasource.anime.AnimeLocalDataSource
import eu.tutorials.animelistapp.repository.localRepository.datasource.manga.MangaLocalDataSource
import eu.tutorials.animelistapp.repository.localRepository.datasource.myProfile.MyProfileLocalDataSource
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
        animeLocalDataSource: AnimeLocalDataSource,
    ): AnimeRepository {
        return AnimeRepositoryImpl(
            animeRemoteDataSource, animeLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideMyProfileRepository(
        myProfileLocalDataSource: MyProfileLocalDataSource,
    ): MyProfileRepository {
        return MyProfileRepositoryImpl(
            myProfileLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideMangaRepository(
        mangaRemoteDataSource: MangaRemoteDataSource,
        mangaLocalDataSource: MangaLocalDataSource,
    ): MangaRepository {
        return MangaRepositoryImpl(
            mangaRemoteDataSource, mangaLocalDataSource
        )
    }

    @Provides
    @Singleton
    fun provideAnimeDomain(animeRepository:AnimeRepository): AnimeDomain {
        return AnimeDomainImpl(animeRepository)
    }

    @Provides
    @Singleton
    fun provideAnimeDetailsDomain(animeRepository:AnimeRepository): AnimeDetailsDomain {
        return AnimeDetailsDomainImpl(animeRepository)
    }

    @Provides
    @Singleton
    fun provideAnimeSearchDomain(animeRepository:AnimeRepository): AnimeSearchDomain {
        return AnimeSearchDomainImpl(animeRepository)
    }

    @Provides
    @Singleton
    fun provideMyFavouriteAnimeDomain(animeRepository:AnimeRepository): MyFavouriteAnimeDomain {
        return MyFavouriteAnimeDomainImpl(animeRepository)
    }

    @Provides
    @Singleton
    fun provideMangaDomain(mangaRepository:MangaRepository): MangaDomain {
        return MangaDomainImpl(mangaRepository)
    }

    @Provides
    @Singleton
    fun provideMangaDetailsDomain(mangaRepository:MangaRepository): MangaDetailsDomain {
        return MangaDetailsDomainImpl(mangaRepository)
    }


    @Provides
    @Singleton
    fun provideMangaSearchDomain(mangaRepository:MangaRepository): MangaSearchDomain {
        return MangaSearchDomainImpl(mangaRepository)
    }

    @Provides
    @Singleton
    fun provideMyFavouriteMangaDomain(mangaRepository:MangaRepository): MyFavouriteMangaDomain {
        return MyFavouriteMangaDomainImpl(mangaRepository)
    }

    @Provides
    @Singleton
    fun provideMyProfileDomain(myProfileRepository: MyProfileRepository): MyProfileDomain {
        return MyProfileDomainImpl(myProfileRepository)
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
    fun provideAnimeDetailsDao(database: AppDatabase) = database.animeDetailsDao()

    @Provides
    fun provideAnimeCharacterDao(database: AppDatabase) = database.animeCharacterDao()

    @Provides
    fun provideAnimeRecommendationDao(database: AppDatabase) = database.animeRecommendationDao()

    @Provides
    fun provideMangaDao(database: AppDatabase) = database.mangaDao()

    @Provides
    fun provideMangaDetailsDao(database: AppDatabase) = database.mangaDetailsDao()

    @Provides
    fun provideMangaCharacterDao(database: AppDatabase) = database.mangaCharacterDao()

    @Provides
    fun provideMangaRecommendationDao(database: AppDatabase) = database.mangaRecommendationDao()

    @Provides
    fun provideMyFavouriteAnimeDao(database: AppDatabase) = database.myFavouriteAnimeDao()

    @Provides
    fun provideMyFavouriteMangaDao(database: AppDatabase) = database.myFavouriteMangaDao()

    @Provides
    fun provideMyProfileDao(database: AppDatabase) = database.myProfileDao()

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
