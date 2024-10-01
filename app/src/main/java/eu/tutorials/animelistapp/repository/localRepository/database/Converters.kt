package eu.tutorials.animelistapp.repository.localRepository.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import eu.tutorials.animelistapp.repository.remoteRepository.model.ImagesDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.CharacterDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.GenreDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.ThemeDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.AiredDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.ProducerDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.StudioDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.TrailerDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.animeDetails.animeCharacters.VoiceActorDto
import eu.tutorials.animelistapp.repository.remoteRepository.model.details.mangaDetails.PublishedDto

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromJsonToAired(value: String): AiredDto {
        val type = object : TypeToken<AiredDto>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromAiredToJson(aired: AiredDto): String {
        return gson.toJson(aired)
    }

    @TypeConverter
    fun fromJsonToProducerList(value: String): List<ProducerDto> {
        val type = object : TypeToken<List<ProducerDto>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromProducerListToJson(list: List<ProducerDto>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToStudioList(value: String): List<StudioDto> {
        val type = object : TypeToken<List<StudioDto>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStudioListToJson(list: List<StudioDto>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToGenreList(value: String): List<GenreDto> {
        val type = object : TypeToken<List<GenreDto>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromGenreListToJson(list: List<GenreDto>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToThemeList(value: String): List<ThemeDto> {
        val type = object : TypeToken<List<ThemeDto>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromThemeListToJson(list: List<ThemeDto>): String {
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromJsonToCharacter(value: String): CharacterDto {
        val type = object : TypeToken<CharacterDto>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCharacterToJson(character: CharacterDto): String {
        return gson.toJson(character)
    }

    @TypeConverter
    fun fromJsonToVoiceActorsList(value: String): List<VoiceActorDto> {
        val type = object : TypeToken<List<VoiceActorDto>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromVoiceActorListToJson(list: List<VoiceActorDto>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromJsonToPublished(value: String): PublishedDto {
        val type = object : TypeToken<PublishedDto>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPublishedToJson(published: PublishedDto): String {
        return gson.toJson(published)
    }

    @TypeConverter
    fun fromJsonToImages(value: String): ImagesDto {
        val type = object : TypeToken<ImagesDto>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromImagesToJson(images: ImagesDto): String {
        return gson.toJson(images)
    }

    @TypeConverter
    fun fromJsonToTrailer(value: String): TrailerDto {
        val type = object : TypeToken<TrailerDto>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTrailerToJson(trailer: TrailerDto): String {
        return gson.toJson(trailer)
    }
}