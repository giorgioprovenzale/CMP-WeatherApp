# Compose Multiplatform Weather App

This is a Compsoe Multiplatform weather application built using Kotlin Multiplatform and Jetpack Compose. It allows users to search for
locations, view current weather conditions, and keeps track of recent searches using a local database.

## Inspiration

This project was inspired by [Philipp Lackner](https://www.youtube.com/@PhilippLackner) and his excellent video tutorial. You can find his
work here: [https://www.youtube.com/watch?v=WT9-4DXUqsM](https://www.youtube.com/watch?v=WT9-4DXUqsM)

## Features

* **Multiplatform:** Built with Compose Multiplatform, ensuring code reusability across different platforms (Android, iOS).
* **Location Search:** Allows users to search for weather information by location.
* **Current Weather Data:** Displays real-time weather information such as temperature, conditions, and wind speed.
* **Forecast Data:** Displays forecast information such as temperature, conditions, and wind speed.
* **Recent Searches:** Saves recently searched locations to a local database for quick access.
* **Current Location:** Fetches user current location using `compass` library.

## Tech Stack

* **Kotlin Multiplatform:** For cross-platform development.
* **Jetpack Compose:** For building shared declarative UI.
* **MVVM Architecture:** Follows the Model-View-ViewModel architectural pattern.
* **Jetpack Navigation:** For navigation between screens.
* **ViewModel:** To manage UI-related data and lifecycle.
* **Room:** For local database management of recent searches.
* **Compass:** For location and permission management: [https://github.com/jordond/compass](https://github.com/jordond/compass)
* **Open-Meteo.com:** For weather data.

## Architectural Patterns

* **MVVM (Model-View-ViewModel):** Separates the UI from the data and business logic.
* **UI State Management:** The project adopts a robust approach to managing UI state, drawing inspiration from this
  article: [Modeling ViewModel State in Android: A guide to clean, scalable patterns](https://medium.com/clean-android-dev/modeling-viewmodel-state-in-android-a-guide-to-clean-scalable-patterns-d42932ade940)
* **Global State Management:** The project implements a global state management approach inspired
  by: [Mastering Global State Management in Android with Jetpack Compose](https://proandroiddev.com/mastering-global-state-management-in-android-with-jetpack-compose-e99350fad822)

## Dependencies & Resources

* [Open-Meteo](https://open-meteo.com): Provides free weather data.
* [Compass](https://github.com/jordond/compass): Handles location fetching and permission requests.
* [Philipp Lackner YouTube Channel](https://www.youtube.com/@PhilippLackner): Inspiration and tutorial source.
* [Modeling ViewModel State in Android](https://medium.com/clean-android-dev/modeling-viewmodel-state-in-android-a-guide-to-clean-scalable-patterns-d42932ade940)
* [Mastering Global State Management in Android with Jetpack Compose](https://proandroiddev.com/mastering-global-state-management-in-android-with-jetpack-compose-e99350fad822)

## Contributing

Contributions to the project are welcome. Feel free to submit pull requests or open issues to suggest improvements.