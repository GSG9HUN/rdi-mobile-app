package eu.tutorials.animelistapp.repository.localRepository.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eu.tutorials.animelistapp.domain.model.details.animeDetails.Aired
import eu.tutorials.animelistapp.domain.model.details.Genre
import eu.tutorials.animelistapp.domain.model.details.animeDetails.Producer
import eu.tutorials.animelistapp.domain.model.details.animeDetails.Studio
import eu.tutorials.animelistapp.domain.model.details.Theme
import eu.tutorials.animelistapp.domain.model.details.Character
import eu.tutorials.animelistapp.domain.model.details.animeDetails.animeCharacters.VoiceActor
import eu.tutorials.animelistapp.domain.model.details.mangaDetails.Published

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromJsonToAired(value: String): Aired {
        val type = object : TypeToken<Aired>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromAiredToJson(aired: Aired): String {
        return gson.toJson(aired)
    }

    @TypeConverter
    fun fromJsonToProducerList(value: String): List<Producer> {
        val type = object : TypeToken<List<Producer>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromProducerListToJson(list: List<Producer>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToStudioList(value: String): List<Studio> {
        val type = object : TypeToken<List<Studio>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStudioListToJson(list: List<Studio>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToGenreList(value: String): List<Genre> {
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGenreListToJson(list: List<Genre>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToThemeList(value: String): List<Theme> {
        val type = object : TypeToken<List<Theme>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromThemeListToJson(list: List<Theme>): String {
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromJsonToCharacter(value: String): Character {
        val type = object : TypeToken<Character>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCharacterToJson(character: Character): String {
        return gson.toJson(character)
    }

    @TypeConverter
    fun fromJsonToVoiceActorsList(value: String): List<VoiceActor> {
        val type = object : TypeToken<List<VoiceActor>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromVoiceActorListToJson(list: List<VoiceActor>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToPublished(value: String): Published {
        val type = object : TypeToken<Published>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPublishedToJson(published: Published): String {
        return gson.toJson(published)
    }

}