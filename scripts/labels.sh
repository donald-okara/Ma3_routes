#!/bin/bash

# Ma3routes Label Management Script
# This script creates project-specific labels using the GitHub CLI (gh)

# Colors (Optional for script output)
GREEN='\033[0;32m'
NC='\033[0m' # No Color

echo -e "${GREEN}Creating Ma3routes Project Labels...${NC}"

# 1. Phases (from ROADMAP.md)
gh label create "phase:1-foundation" --color "EDEDED" --description "Foundation & Core Infrastructure"
gh label create "phase:2-auth-profile" --color "EDEDED" --description "Authentication & User Profile"
gh label create "phase:3-routes-stages" --color "EDEDED" --description "Routes & Stages (Core Features)"
gh label create "phase:4-navigation" --color "EDEDED" --description "Navigation & Real-time Tracking"
gh label create "phase:5-sync-polish" --color "EDEDED" --description "Sync, Analytics & Polish"

# 2. Modules (from ARCHITECTURE.md)
# Core Modules
gh label create "core:ui" --color "0052CC" --description "Shared UI components and theme"
gh label create "core:sync" --color "0052CC" --description "Synchronization engine logic"
gh label create "core:domain" --color "0052CC" --description "Domain models and mappers"
gh label create "core:analytics" --color "0052CC" --description "Analytics and logging"

# Feature Modules
gh label create "feature:routes" --color "FBCA04" --description "Routes list and details"
gh label create "feature:stages" --color "FBCA04" --description "Stages and navigation"
gh label create "feature:profile" --color "FBCA04" --description "User profile"
gh label create "feature:preferences" --color "FBCA04" --description "App preferences"
gh label create "feature:auth" --color "FBCA04" --description "Authentication"

# Datasource Modules
gh label create "datasource:remote" --color "A2EEF2" --description "Remote API and network"
gh label create "datasource:local" --color "A2EEF2" --description "Local database and caching"

# 3. Types
gh label create "type:feature" --color "0E8A16" --description "New functional requirement"
gh label create "type:bug" --color "D93F0B" --description "Something isn't working"
gh label create "type:enhancement" --color "C2E0C6" --description "Improvement to existing feature"
gh label create "type:refactor" --color "F9D0C4" --description "Code change that neither fixes a bug nor adds a feature"
gh label create "type:docs" --color "0075CA" --description "Documentation only changes"

# 4. Priority
gh label create "priority:high" --color "B60205" --description "Must be addressed immediately"
gh label create "priority:medium" --color "E99695" --description "Important but not urgent"
gh label create "priority:low" --color "D4C5F9" --description "Nice to have"

# 5. Progress
gh label create "status:planned" --color "D1D5DB" --description "Task is scheduled but not yet started"
gh label create "status:work-in-progress" --color "3B82F6" --description "Task is currently being worked on"
gh label create "status:in-review" --color "F59E0B" --description "Task is complete and awaiting review"
gh label create "status:done" --color "10B981" --description "Task is fully completed and verified"

echo -e "${GREEN}Label creation complete!${NC}"
