# NobarQ1 Implementation Plan - Phase 3: Search Feature

This phase focuses on adding a search feature, allowing users to find specific movies by title.

## 1. Objectives
- Add a search icon to the Home Screen's top bar.
- Create a dedicated Search Screen with a search input field.
- Implement movie searching using the TMDB `search/movie` endpoint.
- Display search results in a grid or list format.

## 2. Technical Approach
- **UI:** 
  - Update `HomeScreen` to include a `TopAppBar` with a search action.
  - Create `SearchScreen` with a `TextField` for input and a `LazyVerticalGrid` for results.
- **Networking:** Use Ktor to call `https://api.themoviedb.org/3/search/movie`.
- **State Management:** `SearchViewModel` to manage search queries, results, and loading states.
- **Navigation:** Extend the simple state-based navigation in `App.kt` to include `Screen.Search`.

## 3. Implementation Steps

### Step 1: Network & Data Layer
- Add `search/movie` endpoint call to `MovieRepository`.
- Create `SearchMovieResponse` DTO.

### Step 2: Search UI (Feature)
- Create `composeApp/src/commonMain/kotlin/com/example/nobarq1/features/search/ui/SearchScreen.kt`.
- Create `SearchViewModel.kt`.
- Implement a 3-column grid for search results (movie posters).

### Step 3: Navigation & Integration
- Update `Screen` sealed class in `App.kt` to include `Search`.
- Add a search icon to `HomeScreen`.
- Ensure seamless transition back to `Home` from `Search`.

## 4. Verification
- Verify that typing in the search bar triggers the API call (ideally with debouncing).
- Confirm that results are displayed correctly in the grid.
- Ensure the back navigation works as expected.

---
**Next Immediate Action:** Update the Data layer for searching.
