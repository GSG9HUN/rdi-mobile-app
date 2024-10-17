package eu.tutorials.animelistapp.repository.localRepository.datasource.myProfile

import eu.tutorials.animelistapp.repository.localRepository.database.myProfile.MyProfileDao
import eu.tutorials.animelistapp.repository.localRepository.database.myProfile.MyProfileDataEntity
import javax.inject.Inject

class MyProfileLocalDataSource @Inject constructor(private val myProfileDao: MyProfileDao) {

    suspend fun getMyProfileData() = myProfileDao.getMyProfileData()

    suspend fun insertMyProfileData(myProfileDataEntity: MyProfileDataEntity) =
        myProfileDao.insertMyProfileData(myProfileDataEntity = myProfileDataEntity)
}