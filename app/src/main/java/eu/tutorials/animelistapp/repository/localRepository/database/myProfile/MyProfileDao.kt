package eu.tutorials.animelistapp.repository.localRepository.database.myProfile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class MyProfileDao {
    @Query("Select * from my_profile_data where id =:id")
    abstract suspend fun getMyProfileData(id: Int = 1): MyProfileDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMyProfileData(myProfileDataEntity: MyProfileDataEntity)

}