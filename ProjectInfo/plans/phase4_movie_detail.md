# NobarQ1 Implementation Plan - Phase 4: Movie Detail Screen

This phase introduces a detailed view for movies, providing users with more information and the ability to watch trailers.

## 1. Objectives
- Navigate to a Movie Detail Screen when a movie poster is tapped.
- Fetch and display detailed movie information using the TMDB `movie/{id}` endpoint.
- Fetch movie videos (trailers) using the TMDB `movie/{id}/videos` endpoint.
- Provide a way to "Play Trailer" (e.g., opening a YouTube link).

## 2. Technical Approach
- **UI:**
  - `DetailScreen`: A scrollable view with a large backdrop image, title, rating, genres, runtime, and overview.
  - "Play" button for the trailer.
- **Networking:**
  - Add `getMovieDetails(id)` to `MovieRepository`.
  - Add `getMovieVideos(id)` to `MovieRepository` (to get the YouTube key).
- **Navigation:**
  - Update `Screen` sealed class in `App.kt` to `Detail(movieId: Int)`.
  - Add click listeners to `MovieCard` in both `HomeScreen` and `SearchScreen`.
- **State Management:** `DetailViewModel` to handle fetching by `movieId`.

## 3. Implementation Steps

### Step 1: Data Layer Updates
- Create `MovieDetailDto` and `VideoResponseDto` classes.
- Update `MovieRepository` and `MovieRepositoryImpl` with the new methods.

### Step 2: Detail Feature UI
- Create `composeApp/src/commonMain/kotlin/com/example/nobarq1/features/detail/ui/DetailScreen.kt`.
- Create `DetailViewModel.kt`.
- Implement a cinematic layout with a gradient overlay on the backdrop.

### Step 3: Navigation Integration
- Modify `App.kt` to handle `Screen.Detail(movieId)`.
- Pass click events from `HomeScreen` and `SearchScreen` posters up to `App.kt`.

## 4. Verification
- Tap a movie in Home/Search and verify the correct details load.
- Check that the backdrop and genres are displayed correctly.
- Verify that the "Play Trailer" action triggers (e.g., logs the YouTube URL or opens it).

---
**Next Immediate Action:** Update the Data Layer with movie detail and video endpoints.
