# NobarQ1 Implementation Plan - Phase 2: Splash Screen Animation

This phase focuses on adding a cinematic splash screen that transitions to the Home Screen after a 2-second animation, enhancing the initial user experience.

## 1. Objectives
- Create a dedicated Splash Screen.
- Implement a 2-second animation (e.g., logo scaling or fading).
- Set up basic navigation to transition from Splash to Home.

## 2. Technical Approach
- **UI:** A new `@Composable` function `SplashScreen`.
- **Animation:** Use Compose's `Animatable` or `LaunchedEffect` with `delay` to manage the timing.
- **Navigation:** Since we haven't integrated a full navigation library (like Compose Navigation) yet, we will use a simple state-based navigation in `App.kt` to swap between Splash and Home.

## 3. Implementation Steps

### Step 1: Create Splash Screen
- Create `composeApp/src/commonMain/kotlin/com/example/nobarq1/features/splash/SplashScreen.kt`.
- Implement the UI:
  - Center the NobarQ1 logo (using Netflix Red).
  - Add an entry animation (e.g., scale from 0.5f to 1.0f).
- Use `LaunchedEffect` to wait for 2000ms.

### Step 2: Update App Navigation
- Update `App.kt` to manage the current screen state.
- Define a sealed class or enum `Screen` (Splash, Home).
- Logic: Start with `Screen.Splash`, and after the delay in `SplashScreen`, callback to update the state to `Screen.Home`.

### Step 3: Design Refinement
- Ensure the Splash Screen background is `PureBlack` to match the Netflix theme.
- The logo should be prominent and use the `NetflixRed` defined in our design system.

## 4. Verification
- Run on Android/iOS to confirm the splash shows for exactly 2 seconds.
- Verify smooth transition to the Home Screen with category lists.

---
**Next Immediate Action:** Implement the Splash Screen UI and logic.
