package eu.tutorials.animelistapp.domain.myProfile.useCase

import eu.tutorials.animelistapp.domain.myProfile.MyProfileDomain
import eu.tutorials.animelistapp.domain.model.myProfile.MyProfileData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertMyProfileDataUseCase @Inject constructor(private val profileDataDomain: MyProfileDomain) {
    operator fun invoke(myProfileData: MyProfileData) =
        profileDataDomain.insertMyProfileData(myProfileData = myProfileData)
}