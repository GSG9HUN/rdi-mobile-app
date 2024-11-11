package eu.tutorials.animelistapp.domain.myProfile.useCase

import eu.tutorials.animelistapp.domain.myProfile.MyProfileDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyProfileDataUseCase @Inject constructor(private val profileDataDomain: MyProfileDomain) {
    operator fun invoke() = profileDataDomain.getMyProfileData()
}