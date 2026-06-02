# Ma3 Routes (Matatu Route Helper)

Ma3 Routes is a specialized navigation assistant designed for Nairobi's informal transit network (Matatus). It focuses on providing a high-legibility, minimalist interface optimized for fast-paced, real-world commuting.

## Project Overview

Nairobi's transit system is vibrant but often complex to navigate for new users or when exploring new routes. Ma3 Routes aims to bridge this gap by providing clear, reliable, and locally-grounded route information.

## Dependency Graph

```mermaid
graph TD
    subgraph App
        app[":app"]
    end

    subgraph Features
        auth[":features:authentication"]
        prefs[":features:preferences"]
        profile[":features:profile"]
        r_details[":features:routes:details"]
        r_list[":features:routes:list"]
        s_list[":features:stages:list"]
        s_nav[":features:stages:navigation"]
    end

    subgraph Core
        analytics[":core:analytics"]
        domain[":core:domain"]
        sync[":core:sync"]
        ui[":core:ui"]
    end

    subgraph Datasources
        local[":datasources:local"]
        remote[":datasources:remote"]
    end

    %% app dependencies
    app --> auth
    app --> prefs
    app --> profile
    app --> r_details
    app --> r_list
    app --> s_list
    app --> s_nav
    app --> analytics
    app --> domain
    app --> sync
    app --> ui

    %% sync dependencies
    sync --> auth
    sync --> prefs
    sync --> profile
    sync --> r_details
    sync --> r_list
    sync --> s_list
    sync --> s_nav
    sync --> domain
    sync --> analytics

    %% features dependencies
    auth --> analytics
    auth --> domain
    auth --> ui
    auth --> local
    auth --> remote

    prefs --> analytics
    prefs --> domain
    prefs --> ui
    prefs --> local
    prefs --> remote

    profile --> analytics
    profile --> domain
    profile --> ui
    profile --> local
    profile --> remote

    r_details --> analytics
    r_details --> domain
    r_details --> ui
    r_details --> local
    r_details --> remote

    r_list --> analytics
    r_list --> domain
    r_list --> ui
    r_list --> local
    r_list --> remote

    s_list --> analytics
    s_list --> domain
    s_list --> ui
    s_list --> local
    s_list --> remote

    s_nav --> analytics
    s_nav --> domain
    s_nav --> ui
    s_nav --> local
    s_nav --> remote
```

## Key Features

- **Matatu Yellow Theme**: A high-contrast design system optimized for outdoor and night-time legibility.
- **Route Guidance**: Quick access to route numbers, stages, and destinations.
- **Utility-First UI**: Minimalist interface built with Jetpack Compose for speed and clarity.

## Documentation

Comprehensive documentation is available in the `docs/` directory:

- [**Architecture**](docs/ARCHITECTURE.md): Technical stack, system design, and application structure.
- [**Design System**](docs/DESIGN.md): Visual language, typography, and color specifications.
- [**Data Model & Schema**](docs/schema.sql): Database schema for routes and stages.
- [**Data Strategy**](docs/DATA.md): Data sourcing and handling principles.

## Design Links

The project's visual specifications and prototypes can be found here:
- [**Stitch Design Specs**](https://stitch.withgoogle.com/projects/14128945751857227509)

## Getting Started

1. Open the project in Android Studio.
2. Ensure you have the latest Android SDK - Panda (or newer) installed.
3. Sync the project with Gradle files.
4. Run the `app` module on an emulator or physical device.

## Core Technologies

- **Platform**: Android
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Theme**: Material 3
