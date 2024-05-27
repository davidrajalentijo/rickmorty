# Rick and Morty Character Explorer

Rick and Morty Character Explorer is an Android application developed in Kotlin. 
The application allows users to explore characters from the Rick and Morty series.

## Description

This application is divided into different modules:

- The Network module contains the logic of the communication with the Rick and Morty API.
- The UI module contains the UI components of the application and other related logic.
- The App module is the main module of the app. It contains the UI and the ViewModel.

The application follows an MVVM architecture and uses:
- Retrofit for network requests.
- Koin for dependency injection.
- Coil for image loading.
- Coroutines for asynchronous programming.
- mockk for mocking objects in tests.
- JUnit for unit tests.
- Espresso for UI tests.
- Detekt for static code analysis.
- Compose for the UI.

## Setup

### Prerequisites

- Android Studio Jellyfish | 2023.3.1 Patch 1
- Kotlin

### Installation

1. Clone the repository: `git clone https://github.com/davidrajalentijo/rickAndMorty.git`
2. Open the project in Android Studio.
3. Run the application on an emulator or physical device.

## Usage

To run the app, just run the app in an emulator or a physical device.

## Features

The application has the following features:

- Explore characters from the Rick and Morty series.
- Details of each character.

https://github.com/davidrajalentijo/rickmorty/assets/6725999/f65d87d3-10b0-41fa-8aa8-53ec6005102f

https://github.com/davidrajalentijo/rickmorty/assets/6725999/ae3437d7-a3b4-429c-9375-9a7ee7059d18

## Roadmap

- Add more information about the characters.
- Favorite characters.
- Search for characters.
- Filter characters by status, species,
- Move Characters Logic to a separate feature module.

## License

This project is licensed under the Apache License, Version 2.0. You can find the full license text in the LICENSE file.
