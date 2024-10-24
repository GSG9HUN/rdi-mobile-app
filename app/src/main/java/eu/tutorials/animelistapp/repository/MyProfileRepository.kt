package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.localRepository.database.myProfile.MyProfileDataEntity

interface MyProfileRepository {
    suspend fun getMyProfileData(): MyProfileDataEntity?

    suspend fun insertMyProfileData(myProfileDataEntity: MyProfileDataEntity)
}