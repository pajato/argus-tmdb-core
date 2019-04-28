Version 0.0.1 provides the basic data types used in both the Argus TMDB client and server.

Version 0.0.2 changes the Page signature.

Version 0.0.3 updates the Kotlin version.

Version 0.0.4 reverts to using a KClass TmdbData subclass rather than the list name to identify the list.

Version 0.0.7 makes the parse() method public; exposes the extension functions KClass.getListName() and String.toTmdbData().
