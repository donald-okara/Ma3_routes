#!/bin/bash

# Ma3routes Issue Creation Script
# This script creates GitHub issues for the development roadmap using the GitHub CLI (gh)

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}Starting Ma3routes Issue Creation...${NC}"

# Phase 1: Foundation & Core Infrastructure
echo -e "${GREEN}Creating Phase 1 issues...${NC}"

gh issue create --title "1.1 Project Scaffolding" --label "phase:1-foundation,type:feature,priority:high,status:planned" --body "## Description
Setup the initial project structure and infrastructure.

## Tasks
- Setup multi-module structure (Core, Features, Datasources).
- Configure Dependency Injection (Hilt/Koin).
- Establish CI/CD pipelines and linting rules."

gh issue create --title "1.2 Core UI & Design System" --label "phase:1-foundation,core:ui,type:feature,priority:high,status:planned" --body "## Description
Implement the foundation of the design system as defined in docs/DESIGN.md.

## Tasks
- Implement the 'Matatu Yellow' dark mode theme.
- Build reusable atomic components (Buttons, Cards, Inputs).
- Setup Navigation component architecture."

gh issue create --title "1.3 Data Layer Foundation" --label "phase:1-foundation,datasource:local,datasource:remote,core:domain,type:feature,priority:high,status:planned" --body "## Description
Establish the data persistence and networking layers.

## Tasks
- Implement Room database with the defined schema (routes, stages, etc.).
- Create Domain models and Mappers (DTO ↔ Domain ↔ Entity).
- Setup Retrofit/Ktor for Remote Datasource abstractions."

# Phase 2: Authentication & User Profile
echo -e "${GREEN}Creating Phase 2 issues...${NC}"

gh issue create --title "2.1 Auth Module" --label "phase:2-auth-profile,feature:auth,datasource:remote,type:feature,priority:medium,status:planned" --body "## Description
Implement user identity management.

## Tasks
- Implement Sign-in/Sign-up flows (Remote API integration).
- Secure session management (DataStore for tokens)."

gh issue create --title "2.2 Profile & Preferences" --label "phase:2-auth-profile,feature:profile,feature:preferences,type:feature,priority:medium,status:planned" --body "## Description
Enable user personalization and settings.

## Tasks
- Build User Profile management screen.
- Implement app-wide Preferences (Theme toggle, notification settings)."

gh issue create --title "2.3 Local-Remote Bridge" --label "phase:2-auth-profile,core:sync,type:feature,priority:medium,status:planned" --body "## Description
Setup basic synchronization logic.

## Tasks
- Implement basic sync logic for user-specific data."

# Phase 3: Routes & Stages (Core Features)
echo -e "${GREEN}Creating Phase 3 issues...${NC}"

gh issue create --title "3.1 Route Discovery" --label "phase:3-routes-stages,feature:routes,type:feature,priority:high,status:planned" --body "## Description
Implement the core route discovery functionality.

## Tasks
- Routes List: Searchable overview of all matatu routes.
- Route Details: High-level summary of a route's corridor and destinations."

gh issue create --title "3.2 Stage Information" --label "phase:3-routes-stages,feature:stages,type:feature,priority:high,status:planned" --body "## Description
Implement stage visualization and listing.

## Tasks
- Stage List: Display all stages associated with a specific route.
- Map integration for Stage locations (Lat/Lng visualization)."

gh issue create --title "3.3 Offline-First Implementation" --label "phase:3-routes-stages,datasource:local,type:enhancement,priority:high,status:planned" --body "## Description
Ensure the app works seamlessly offline using the established repository pattern.

## Tasks
- Implement Repository patterns that expose Flows from Room.
- Ensure UI remains functional without network connectivity."

# Phase 4: Navigation & Real-time Tracking
echo -e "${GREEN}Creating Phase 4 issues...${NC}"

gh issue create --title "4.1 Navigation Feature" --label "phase:4-navigation,feature:stages,type:feature,priority:medium,status:planned" --body "## Description
Assist users during their commute with real-time tracking.

## Tasks
- Navigation View: Real-time tracking of the user's position relative to stages.
- Implement Geofencing for 'Next Stage' alerts."

gh issue create --title "4.2 Crowdsourced Corrections" --label "phase:4-navigation,core:domain,type:feature,priority:medium,status:planned" --body "## Description
Enable users to improve data accuracy.

## Tasks
- Implement the Corrections module to allow users to report data inaccuracies.
- UI for submitting new stages or route variants."

gh issue create --title "4.3 Transit Blue Integration" --label "phase:4-navigation,core:ui,type:enhancement,priority:low,status:planned" --body "## Description
Apply visual differentiation for walking paths.

## Tasks
- Visual differentiation for walking paths vs. vehicle movement in the UI (using Transit Blue)."

# Phase 5: Sync, Analytics & Polish
echo -e "${GREEN}Creating Phase 5 issues...${NC}"

gh issue create --title "5.1 Periodic Sync Engine" --label "phase:5-sync-polish,core:sync,type:feature,priority:medium,status:planned" --body "## Description
Automate background data synchronization.

## Tasks
- Implement WorkManager-based background synchronization.
- Handle conflict resolution between local edits and remote updates."

gh issue create --title "5.2 Analytics & Monitoring" --label "phase:5-sync-polish,core:analytics,type:feature,priority:low,status:planned" --body "## Description
Track app performance and data reliability.

## Tasks
- Integrate centralized logging and event tracking.
- Monitor sync performance and data reliability scores."

gh issue create --title "5.3 Final Polish" --label "phase:5-sync-polish,type:enhancement,priority:medium,status:planned" --body "## Description
General optimizations and accessibility audit.

## Tasks
- Performance optimization for large stage lists.
- Accessibility audit (High-contrast legibility).
- Beta testing and bug fixing."

echo -e "${BLUE}Issue creation complete!${NC}"
