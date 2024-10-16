package eu.tutorials.animelistapp.domain.usecase.myProfile

import eu.tutorials.animelistapp.domain.MyProfileDomain
import eu.tutorials.animelistapp.domain.model.myProfile.MyProfileData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertMyProfileDataUseCase @Inject constructor(private val profileDataDomain: MyProfileDomain) {
    operator fun invoke(myProfileData: MyProfileData) =
        profileDataDomain.insertMyProfileData(myProfileData = myProfileData)
}