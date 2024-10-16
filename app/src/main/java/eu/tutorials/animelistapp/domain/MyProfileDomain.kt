package eu.tutorials.animelistapp.domain

import eu.tutorials.animelistapp.constants.Resource
import eu.tutorials.animelistapp.domain.model.myProfile.MyProfileData
import eu.tutorials.animelistapp.repository.MyProfileRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyProfileDomain @Inject constructor(
    private val myProfileRepository: MyProfileRepositoryImpl,
) {
    fun getMyProfileData(): Flow<Resource<MyProfileData>> {
        return flow {
            emit(Resource.Loading())
            try {
                val myProfileData =
                    myProfileRepository.getMyProfileData()
                myProfileData?.let {
                    emit(Resource.Success(myProfileData.toMyProfileData()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

    fun insertMyProfileData(myProfileData: MyProfileData): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading())
            try {
                myProfileRepository.insertMyProfileData(myProfileData.toMyProfileEntity())
                emit(Resource.Success(1))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }

}