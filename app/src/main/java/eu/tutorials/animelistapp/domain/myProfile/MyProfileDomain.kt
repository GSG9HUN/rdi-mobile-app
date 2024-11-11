package eu.tutorials.animelistapp.domain.myProfile

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myProfile.MyProfileData
import kotlinx.coroutines.flow.Flow

interface MyProfileDomain {
    fun getMyProfileData(): Flow<Resource<MyProfileData>>
    fun insertMyProfileData(myProfileData: MyProfileData): Flow<Resource<Int>>
}