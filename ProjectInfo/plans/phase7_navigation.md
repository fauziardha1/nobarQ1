# NobarQ1 Implementation Plan - Phase 7: Navigation Migration (Swipe-to-Go-Back)

Migrating the app to `Compose Navigation` to enable native-like swipe-to-go-back gestures on iOS.

## 1. Objectives
- Replace manual `currentScreen` state logic with `Compose Navigation`.
- Enable swipe-to-go-back behavior for iOS users.
- Clean up navigation logic.

## 2. Technical Approach
- **Dependencies:** Add `navigation-compose` to `libs.versions.toml`.
- **Navigation Graph:** Define routes for all screens: `Splash`, `Login`, `Profile`, `Home`, `Search`, `Liked`, `Detail`.
- **Gesture Support:** By using the standard `NavHost` in Compose Multiplatform, the swipe-to-go-back gesture is enabled by default on iOS.

## 3. Implementation Steps

### Step 1: Add Dependencies
- Update `libs.versions.toml` with `navigation-compose`.
- Add to `composeApp/build.gradle.kts`.

### Step 2: Refactor Navigation Graph
- Update `App.kt` to use `NavHost` and `composable` destinations.
- Pass required arguments (e.g., `movieId` for `Detail`).

### Step 3: Cleanup
- Remove the `Screen` sealed class (or update it to be serializable).
- Remove the manual state-based `when` block in `App.kt`.

## 4. Verification
- Verify swipe-to-go-back works on iOS Simulator.
- Ensure all screen transitions are functional and maintain backstack state.

---
**Next Immediate Action:** Add navigation dependencies and prepare for refactoring.
