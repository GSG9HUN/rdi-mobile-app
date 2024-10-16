package eu.tutorials.animelistapp.domain.model.myProfile

import eu.tutorials.animelistapp.repository.localRepository.database.myProfile.MyProfileDataEntity

data class MyProfileData(
    val receiveNotifications: Boolean,
    val enableDarkMode: Boolean,
    val gender: String,
    val notes: String,
    val id: Int,
) {
    fun toMyProfileEntity() = MyProfileDataEntity(
        id = id,
        receiveNotifications = receiveNotifications,
        enableDarkMode = enableDarkMode,
        gender = gender,
        notes = notes
    )
}
