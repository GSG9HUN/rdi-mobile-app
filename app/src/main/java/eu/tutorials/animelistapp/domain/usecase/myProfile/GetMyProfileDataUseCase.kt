package eu.tutorials.animelistapp.domain.usecase.myProfile

import eu.tutorials.animelistapp.domain.MyProfileDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyProfileDataUseCase @Inject constructor(private val profileDataDomain: MyProfileDomain) {
    operator fun invoke() = profileDataDomain.getMyProfileData()
}