# argus-tmdb-core

![TMDB PoweredBy Logo](https://www.themoviedb.org/assets/2/v4/logos/408x161-powered-by-rectangle-green-bb4301c10ddc749b4e79463811a68afebeae66ef43d17bcfd8ff0e60ded7ce99.png)

![license: LGPL v3](https://img.shields.io/badge/license-LGPL%20v3-blue.svg)
![build: passing](https://img.shields.io/badge/build-passing-brightgreen.svg)
![codecov: 100](https://img.shields.io/badge/codecov-100%25-brightgreen.svg)

## Overview

A multi-platform Kotlin library providing access to The Movie Database (tmdb) dataset objects.

Support will be provided in the first release for Android and iOS.

## Current release: (Maven Central)

Maven coordinates: **com.pajato.argus:argus-tmdb-core:0.0.14**

## API

```kotlin

/** The wrapper class for TMDB dataset subclasses. */
sealed class TmdbData

/** Each TMDB subclass identifies a dataset list name and a create function. */
interface TmdbDataFactory {
    val listName: String
    fun create(json: String): TmdbData
}

/** The set of collections used in TMDB. */
@Serializable
data class Collection(val id: Int = 0, val name: String = "") : TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "collection_ids"
        override fun create(json: String): TmdbData = createFromJson(json, Collection())
    }
}

/** The set of keywords used in TMDB. */
@Serializable
data class Keyword(val id: Int = 0, val name: String = "") : TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "keyword_ids"
        override fun create(json: String): TmdbData = createFromJson(json, Keyword())
    }
}

/** The set of movies known to TMDB. */
@Serializable
data class Movie(
    val adult: Boolean = false,
    val id: Int = -1,
    val original_title: String = "",
    val popularity: Double = 0.0,
    val video: Boolean = false
) : TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "movie_ids"
        override fun create(json: String): TmdbData = createFromJson(json, Movie())
    }
}

/** The set of TV networks known to TMDB. */
@Serializable
data class Network(val id: Int = -1, val name: String = ""): TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "tv_network_ids"
        override fun create(json: String): TmdbData = createFromJson(json, Network())
    }
}

/** The set of people (cast, crew, etc.) known to TMDB. */
@Serializable
data class Person(
    val adult: Boolean = false,
    val id: Int = -1,
    val name: String = "",
    val popularity: Double = 0.0
) : TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "person_ids"
        override fun create(json: String): TmdbData = createFromJson(json, Person())
    }
}

/** The set of production companies known to TMDB. */
@Serializable
data class ProductionCompany(val id: Int = 0, val name: String = "") : TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "production_company_ids"
        override fun create(json: String): TmdbData = createFromJson(json, ProductionCompany())
    }
}

/** The set of TV shows known to TMDB. */
@Serializable
data class TvSeries(val id: Int = -1, val original_name: String = "", val popularity: Double = 0.0) : TmdbData() {
    companion object : TmdbDataFactory {
        override val listName = "tv_series_ids"
        override fun create(json: String): TmdbData = createFromJson(json, TvSeries())
    }
}

/** A special TMDB error class providing granular message data for errors. */
data class TmdbError(val message: String) : TmdbData()

/** The page wrapper class. */
class Page<T1 : TmdbData, T2: TmdbData>(val type: KClass<T1>, val pageSize: Int, val list: MutableList<T2>) {

    fun hasError(): Boolean = list.size == 1 && list[0] is TmdbError

    fun getError(): String {
        fun getErrorMessage(item: TmdbData): String = when (item) {
            is TmdbError -> item.message
            else -> ""
        }

        return if (list.size == 0) "" else getErrorMessage(list[0])
    }
}
```
