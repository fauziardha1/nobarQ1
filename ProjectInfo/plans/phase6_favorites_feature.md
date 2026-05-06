# NobarQ1 Implementation Plan - Phase 6: Favorites Feature

This phase allows users to "like" movies and view them in a dedicated favorites list.

## 1. Objectives
- **Detail Screen:** Add a "Like/Heart" button to toggle the favorite status of a movie.
- **Persistence:** Store liked movies in the local SQLDelight database.
- **Liked Movies Screen:** Add a new screen to list all saved favorites.
- **Home Screen:** Add an entry point to the "Liked" screen.

## 2. Technical Approach
- **Data Layer:**
  - Add `isFavorite(movieId)` and `toggleFavorite(movie)` methods to `MovieRepository`.
  - Update `NobarDatabase.sq` to include a `FavoriteMovie` table.
- **UI:**
  - `DetailScreen`: Add an `IconButton` that toggles favorite state.
  - `LikedScreen`: A `LazyVerticalGrid` showing all liked movies.
- **Navigation:**
  - Update `Screen` sealed class in `App.kt` to include `Screen.Liked`.
  - Add navigation from `HomeScreen` to `LikedScreen`.

## 3. Implementation Steps

### Step 1: Database Schema
- Define `FavoriteMovie` table in `NobarDatabase.sq`.
- Add `insertFavorite`, `deleteFavorite`, and `getAllFavorites` queries.

### Step 2: Repository Update
- Implement favorite toggle logic in `MovieRepository`.

### Step 3: UI Implementation
- Update `DetailScreen` to display the favorite icon and handle clicks.
- Create `LikedScreen.kt` in `features/liked/ui`.

### Step 4: Navigation
- Add a "Liked" icon button to `HomeScreen`'s top bar.
- Update `App.kt` routing.

## 4. Verification
- Like a movie in the Detail screen.
- Go to the Liked screen and verify the movie appears there.
- Unlike the movie in the Detail screen or Liked screen and ensure it is removed.

---
**Next Immediate Action:** Update the SQLDelight schema.
