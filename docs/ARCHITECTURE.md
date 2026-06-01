# Architecture Documentation

## Overview
This project follows a hybrid architecture combining **MVI (Model-View-Intent)** for the presentation layer and **Clean Architecture** for the overall system structure. It is designed to be highly modular, scalable, and offline-first.

The application adheres to the principle of **Dependency Inversion**, ensuring that high-level modules do not depend on low-level modules, but both depend on abstractions.

## Core Principles
- **Offline-First:** Features read data directly from the local database via `Flows`. The UI updates automatically when the database changes.
- **Unidirectional Data Flow (MVI):** State management is handled through immutable states and intents to ensure predictability.
- **Feature Modularity:** Each feature is self-contained and owns its own repository.
- **Sync Strategy:** The app performs periodic synchronization between remote and local data sources.

---

## Project Structure

### 1. Features
Each feature module contains its own UI, ViewModels, and Repositories.

- **Routes:** 
    - `list`: Overview of available matatu routes.
    - `details`: Detailed information about a specific route.
- **Stages:**
    - `list`: List of stages for a specific route.
    - `navigation`: Real-time navigation and stage tracking.
- **Profile:** User profile management.
- **Preferences:** User settings and app configurations.
- **Authentication:** User sign-in, sign-up, and session management.

### 2. Datasources
Handles data retrieval and persistence.

- **Remote:**
    - Handles API calls, authentication network requests, and external service integrations.
- **Local:**
    - Manages the local database (SQLite/Room), preferences storage, and caching.

### 3. Core
Shared infrastructure used across the entire application.

- **UI:**
    - **Theme:** Design system implementation (Colors, Typography, Shapes).
    - **Components:** Reusable UI elements.
    - **Navigation:** App-wide navigation logic (not location-based).
- **Sync:**
    - Orchestrates the synchronization logic. It depends on features to define how to reconcile remote and local data.
- **Domain:**
    - Holds the "Source of Truth" models.
    - Includes **Mappers** to convert between DTOs (Data Transfer Objects), Domain models, and Database Entities.
- **Analytics:**
    - Centralized logging and event tracking.

---

## Data Flow & Synchronization

### Feature Repositories
Each feature owns its own repository. The repository is responsible for:
1. Providing a `Flow` of data from the **Local Datasource**.
2. Exposing methods for the **Sync** module to trigger updates from the **Remote Datasource**.

### Sync Logic
The `sync` module runs periodically in the background. It interacts with feature repositories to:
1. Fetch the latest data from the **Remote Datasource**.
2. Update the **Local Datasource**.
3. Handle conflict resolution between local and remote states.

### UI Interaction
1. **View** observes a `State` from the **ViewModel**.
2. **ViewModel** interacts with the **Repository** to get a `Flow` of data.
3. **User Actions** are sent as **Intents** to the **ViewModel**.
4. **ViewModel** processes intents and updates the `State`.

---

## Dependencies
The architecture follows a strict dependency hierarchy:
- **Features** depend on **Core** and **Datasources** (via abstractions).
- **Sync** depends on **Features** to perform data reconciliation.
- **Domain** (within Core) is the most stable layer and has no outgoing dependencies.