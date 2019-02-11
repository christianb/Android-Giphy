This project fetches gifs from Giphy https://developers.giphy.com/
* 100% Kotlin
* AndroidX
* MVVM
* Clean Architecture
* Modules: App, Data, Domain, Common

## Testing
* UnitTests with JUnit.
* No lint warnings & error.
* Add Kotlin Detekt codestyle checker: `./gradlew detektCheck`
* Tested on a Nexus6 real device and Nexus5 emulator.

## Libraries
* Koin for a lightweight dependency injection
* Retrofit for network calls
* RxJava for reactive observation
* Mockito for mocking
* AssertJ for fluent assertions
* Glide for gif loading

## What I did NOT
* Espresso Tests
* Ensure scrolling performance does not drop under 60fps on a 64-bit architecture
* Focus on performance
* Implementing my own code for gif rendering
* Apply some nice animations to your gif browser layout on device rotation
* Support an offline mode, which stores the gifs on disk, so you can enjoy all fetched gifs while there is no network connection available
