# NobarQ1 Implementation Plan - Phase 5: Auth & Profile Selection

This phase introduces user authentication and profile selection, similar to the Netflix onboarding flow.

## 1. Objectives
- **Login Screen:** User inputs email/password to sign in.
- **Profile Selection:** After login, display a profile grid (e.g., "Who is watching?") before proceeding to the Home Screen.
- **Session Management:** Persist the user state (logged in) and current selected profile.

## 2. Technical Approach
- **UI:**
  - `LoginScreen`: Email/Password inputs with a "Sign In" button.
  - `ProfileSelectionScreen`: Grid of avatars/names.
- **Data Layer:** 
  - `AuthRepository`: Simple interface for `login` and `getProfiles`.
- **Navigation:**
  - Update `App.kt` flow: `Splash` -> `Login` -> `ProfileSelection` -> `Home`.
  - Use `MutableStateFlow` to manage the authenticated session.

## 3. Implementation Steps

### Step 1: Data Layer
- Create `AuthRepository` and `AuthRepositoryImpl` (mock implementation for now).
- Define simple `User` and `Profile` models.

### Step 2: Auth & Profile UI
- Create `LoginScreen.kt` in `features/auth/ui`.
- Create `ProfileSelectionScreen.kt` in `features/auth/ui`.

### Step 3: Navigation Integration
- Add `Screen.Login` and `Screen.ProfileSelection` to the `App.kt` navigation state.
- Update `App` logic to redirect based on auth status.

## 4. Verification
- Verify that a user cannot bypass login.
- Confirm that the profile selection screen appears after successful login.
- Verify that selecting a profile correctly redirects to the Home screen.

---
**Next Immediate Action:** Create Auth data layer and UI scaffolding.
