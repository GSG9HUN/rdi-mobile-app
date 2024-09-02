package eu.tutorials.animelistapp.constants

sealed class Resource<out T> constructor(val data: T?) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(val error: Throwable?) : Resource<T>(null)
    class Loading<T>() : Resource<T>(null)
}