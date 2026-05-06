# NobarQ1 Implementation Plan - Phase 1: Structure & Design

This plan outlines the setup for **NobarQ1**, a Netflix-like movie platform built with Compose Multiplatform.

## 1. Design Rules (Netflix-Inspired)

We will implement a custom `Theme` in `commonMain` following these rules:
- **Palette:** 
  - `Primary`: `#E50914` (Netflix Red)
  - `Background`: `#000000` (Pure Black for cinematic feel)
  - `Surface`: `#121212` (Dark Grey for cards/modals)
  - `OnBackground/OnSurface`: `#FFFFFF` (White text)
  - `SecondaryText`: `#B3B3B3` (Light Grey for metadata)
- **Typography:** Bold sans-serif for headings (mimicking Netflix Sans), regular sans-serif for body.
- **Components:** High-contrast movie posters, semi-transparent overlays, and horizontal scrolling lists ("The Stack").

## 2. Technical Stack
- **UI:** Compose Multiplatform (Material 3)
- **Networking:** Ktor with Kotlinx Serialization
- **Local Database:** SQLDelight
- **Dependency Injection:** Koin
- **Architecture:** Clean Architecture (MVVM)
  - `data`: Repositories, API (Ktor), DB (SQLDelight)
  - `domain`: Use Cases, Models
  - `ui`: ViewModels, Screens, Components

## 3. Project Structure Refactoring

We will organize `composeApp/src/commonMain/kotlin/com/example/nobarq1/` as follows:
```text
├── core/
│   ├── designsystem/ (Theme, Colors, Typography, Icons)
│   ├── network/ (Ktor setup)
│   ├── database/ (SQLDelight setup)
│   └── di/ (Koin modules)
├── features/
│   ├── home/ (Featured movie, categories)
│   ├── search/
│   └── detail/
└── App.kt
```

## 4. Implementation Steps

### Step 1: Design System Setup
- Define `Color.kt` with Netflix palette.
- Define `Theme.kt` and `Type.kt`.
- Update `App.kt` to use the new `NobarTheme` and ensure it respects **Safe Areas** (using `safeContentPadding` or `Scaffold` with system insets) for both Android and iOS.

### Step 2: Dependency Injection & Networking
- Add Koin, Ktor, and SQLDelight dependencies to `libs.versions.toml`.
- Configure `Koin` in `App.kt` (or platform specific entry points).
- Set up `Ktor` client with:
  - **Base URL:** `https://api.themoviedb.org/3/`
  - **Auth Header:** `Authorization: Bearer <TOKEN>` (using the provided token)
  - **Accept Header:** `application/json`
  - **Default Parameters:** `language=en`
- Implement `genre/movie/list` and `discover/movie` (for movies by genre) as the initial network calls.

### Step 3: SQLDelight Setup
- Create `.sq` files for movie persistence (e.g., `FavoriteMovie`, `CacheMovie`).
- Configure the SQLDelight gradle plugin.

### Step 4: TMDB Integration (Core Data Layer)
- Create `MovieRepository` interface.
- Implement `MovieRepositoryImpl` using Ktor and SQLDelight.
- *Note: User will need to provide a TMDB API Key.*

### Step 5: Home Screen Implementation
- Create `HomeViewModel` with a `HomeState` (Loading, Success, Error).
- **Safe Area:** Ensure the main layout respects system bars and notches.
- **Loading State:** Show a `CircularProgressIndicator` centered on screen.
- **Success State:** 
  - A `LazyColumn` (Vertical) to display different genres as rows.
  - Each genre row contains a `Text` title and a `LazyRow` (Horizontal) to display movie posters.
- **Image Loading:** Use a KMP compatible image loading library (like Coil 3).

## 6. Verification & Testing
- **Visual Check:** Verify the dark theme and "Netflix Red" accents in Android/iOS simulators.
- **Dependency Check:** Run `./gradlew build` to ensure all new libraries are correctly linked.
- **Network Check:** (Post-Phase 1) Verify API calls to TMDB.

---
**Next Immediate Action:** Set up the Design System and project structure.
