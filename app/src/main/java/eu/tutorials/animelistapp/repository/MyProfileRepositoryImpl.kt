package eu.tutorials.animelistapp.repository

import eu.tutorials.animelistapp.repository.localRepository.database.myProfile.MyProfileDataEntity
import eu.tutorials.animelistapp.repository.localRepository.datasource.myProfile.MyProfileLocalDataSource
import javax.inject.Inject

class MyProfileRepositoryImpl @Inject constructor(
    private val myProfileLocalDataSource: MyProfileLocalDataSource,
) : MyProfileRepository {
    override suspend fun getMyProfileData() = myProfileLocalDataSource.getMyProfileData()

    override suspend fun insertMyProfileData(myProfileDataEntity: MyProfileDataEntity) =
        myProfileLocalDataSource.insertMyProfileData(myProfileDataEntity = myProfileDataEntity)
}