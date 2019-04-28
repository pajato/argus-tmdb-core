package com.pajato.tmdb.core

import kotlinx.serialization.json.Json
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
        is Collection -> Json.parse(Collection.serializer(), json)
        is Keyword -> Json.parse(Keyword.serializer(), json)
        is Network -> Json.parse(Network.serializer(), json)
        is ProductionCompany -> Json.parse(ProductionCompany.serializer(), json)
        is Movie -> Json.parse(Movie.serializer(), json)
        is Person -> Json.parse(Person.serializer(), json)
        is TvSeries -> Json.parse(TvSeries.serializer(), json)
        is TmdbError -> item
    }

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
