package com.pajato.tmdb.core

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonException
import kotlin.reflect.KClass

internal const val tmdbBlankErrorMessage = "Blank JSON argument encountered."

/** Parse a TMDB export data set record given the list name and the line to parse. */
internal fun parse(listName: String, line: String): TmdbData =
    when (listName) {
        Collection.listName -> Collection.create(line)
        Keyword.listName -> Keyword.create(line)
        Movie.listName -> Movie.create(line)
        Network.listName -> Network.create(line)
        Person.listName -> Person.create(line)
        ProductionCompany.listName -> ProductionCompany.create(line)
        TvSeries.listName -> TvSeries.create(line)
        else -> TmdbError("Unsupported type: $listName.")
    }

/** Return a TMDB subclass given a type string. */
internal fun createDefaultFromType(type: String): TmdbData = when (type) {
    "Collection" -> Collection()
    "Keyword" -> Keyword()
    "Movie" -> Movie()
    "Network" -> Network()
    "Person" -> Person()
    "ProductionCompany" -> ProductionCompany()
    "TvSeries" -> TvSeries()
    else -> TmdbError("Attempt to create an invalid TMDB data item!")
}

/** Return a TMDB subclass for a given TMDB default data item and a JSON spec. */
internal fun createFromJson(json: String, item: TmdbData): TmdbData =
    if (json.isBlank()) TmdbError(tmdbBlankErrorMessage) else when (item) {
        is Collection -> json.parse(Collection.serializer())
        is Keyword -> json.parse(Keyword.serializer())
        is Network -> json.parse(Network.serializer())
        is ProductionCompany -> json.parse(ProductionCompany.serializer())
        is Movie -> json.parse(Movie.serializer())
        is Person -> json.parse(Person.serializer())
        is TvSeries -> json.parse(TvSeries.serializer())
        is TmdbError -> item
    }

// Extension functions.

/** An extension to access the list name given a TmdbData item. */
internal fun TmdbData.getListName(): String = when (this) {
    is Collection -> Collection.listName
    is Keyword -> Keyword.listName
    is Movie -> Movie.listName
    is Network -> Network.listName
    is Person -> Person.listName
    is ProductionCompany -> ProductionCompany.listName
    is TvSeries -> TvSeries.listName
    is TmdbError -> ""
}

/** An extensions to return a TMDB export data set list name for a given TmdbData subclass. */
fun KClass<out TmdbData>.getListName(): String {
    val name = this.simpleName ?: return ""
    return createDefaultFromType(name).getListName()
}

/** Parse the string receiver returning the TmdbError wrapped string on a parsing error. */
internal fun <T : TmdbData> String.parse(deserializer: DeserializationStrategy<T>): TmdbData =
        try { Json.parse(deserializer, this) } catch (exc: JsonException) { TmdbError(this) }
