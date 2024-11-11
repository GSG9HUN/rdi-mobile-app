package eu.tutorials.animelistapp.repository.localRepository.database.myProfile

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.tutorials.animelistapp.domain.model.myProfile.MyProfileData

@Entity("my_profile_data")
class MyProfileDataEntity(
    @PrimaryKey val id: Int = 1,
    val receiveNotifications: Boolean,
    val enableDarkMode: Boolean,
    val gender: String,
    val notes: String,
) {
    fun toMyProfileData() =
        MyProfileData(
            id = id,
            receiveNotifications = receiveNotifications,
            enableDarkMode = enableDarkMode,
            gender = gender,
            notes = notes
        )
}
