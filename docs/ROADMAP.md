# Development Roadmap: Ma3routes

This roadmap outlines the phased development of the Ma3routes application, focusing on building a robust, offline-first navigation system for Nairobi's matatu network.

---

## Phase 1: Foundation & Core Infrastructure
*Goal: Establish the architectural backbone and design system.*

- **1.1 Project Scaffolding**
    - Setup multi-module structure (Core, Features, Datasources).
    - Configure Dependency Injection (Hilt/Koin).
    - Establish CI/CD pipelines and linting rules.
- **1.2 Core UI & Design System**
    - Implement the "Matatu Yellow" dark mode theme.
    - Build reusable atomic components (Buttons, Cards, Inputs).
    - Setup Navigation component architecture.
- **1.3 Data Layer Foundation**
    - Implement Room database with the defined schema (`routes`, `stages`, etc.).
    - Create Domain models and Mappers (DTO ↔ Domain ↔ Entity).
    - Setup Retrofit/Ktor for Remote Datasource abstractions.

---

## Phase 2: Authentication & User Profile
*Goal: Enable user identity and personalization.*

- **2.1 Auth Module**
    - Implement Sign-in/Sign-up flows (Remote API integration).
    - Secure session management (DataStore for tokens).
- **2.2 Profile & Preferences**
    - Build User Profile management screen.
    - Implement app-wide Preferences (Theme toggle, notification settings).
- **2.3 Local-Remote Bridge**
    - Implement basic sync logic for user-specific data.

---

## Phase 3: Routes & Stages (Core Features)
*Goal: Deliver the primary value proposition — finding transit information.*

- **3.1 Route Discovery**
    - `Routes List`: Searchable overview of all matatu routes.
    - `Route Details`: High-level summary of a route's corridor and destinations.
- **3.2 Stage Information**
    - `Stage List`: Display all stages associated with a specific route.
    - Map integration for Stage locations (Lat/Lng visualization).
- **3.3 Offline-First Implementation**
    - Implement Repository patterns that expose `Flows` from Room.
    - Ensure UI remains functional without network connectivity.

---

## Phase 4: Navigation & Real-time Tracking
*Goal: Assist users during their commute.*

- **4.1 Navigation Feature**
    - `Navigation View`: Real-time tracking of the user's position relative to stages.
    - Implement Geofencing for "Next Stage" alerts.
- **4.2 Crowdsourced Corrections**
    - Implement the `Corrections` module to allow users to report data inaccuracies.
    - UI for submitting new stages or route variants.
- **4.3 Transit Blue Integration**
    - Visual differentiation for walking paths vs. vehicle movement in the UI.

---

## Phase 5: Sync, Analytics & Polish
*Goal: Optimize performance, data freshness, and user experience.*

- **5.1 Periodic Sync Engine**
    - Implement WorkManager-based background synchronization.
    - Handle conflict resolution between local edits and remote updates.
- **5.2 Analytics & Monitoring**
    - Integrate centralized logging and event tracking.
    - Monitor sync performance and data reliability scores.
- **5.3 Final Polish**
    - Performance optimization for large stage lists.
    - Accessibility audit (High-contrast legibility).
    - Beta testing and bug fixing.

---

## Future Scope (Post-MVP)
- Real-time matatu occupancy/availability.
- Fare price integration and comparison.
- Multi-modal routing (combining multiple matatu routes).
