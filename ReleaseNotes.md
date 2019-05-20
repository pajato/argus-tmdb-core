Version 0.0.13

+ Restore some dropped functionality for managing release vs snapshot deployment.

+ Improved error handling for malformed JSON.

Version 0.0.12

+ Provide Kotlin DSL code for handling publications.

Version 0.0.11

+ Overhaul support for buildSrc versions eliminating cruft from the code (https://github/msink/libui) used as the model.

+ Add a clean target for the root project.

Version 0.0.10 moves to a Gradle multi-project format, adopting the buildSrc concept.

Version 0.0.9 handles parsing invalid JSON strings to a TmdbError object.

Version 0.0.8 moves the Page class (and tests) to the argus-tmdb-client project and adds Jacoco code coverage to the build.

Version 0.0.7 makes the parse() method public; exposes the extension functions KClass.getListName() and String.toTmdbData().

Version 0.0.4 reverts to using a KClass TmdbData subclass rather than the list name to identify the list.

Version 0.0.3 updates the Kotlin version.

Version 0.0.2 changes the Page signature.

Version 0.0.1 provides the basic data types used in both the Argus TMDB client and server.
