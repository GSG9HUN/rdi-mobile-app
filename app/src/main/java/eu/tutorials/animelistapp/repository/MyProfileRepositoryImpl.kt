package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.localRepository.database.myProfile.MyProfileDataEntity
import eu.tutorials.animelistapp.repository.localRepository.datasource.myProfile.MyProfileLocalDataSource
import javax.inject.Inject

class MyProfileRepositoryImpl @Inject constructor(
    private val myProfileLocalDataSource: MyProfileLocalDataSource) {
    suspend fun getMyProfileData() = myProfileLocalDataSource.getMyProfileData()

    suspend fun insertMyProfileData(myProfileDataEntity: MyProfileDataEntity) =
        myProfileLocalDataSource.insertMyProfileData(myProfileDataEntity = myProfileDataEntity)
}