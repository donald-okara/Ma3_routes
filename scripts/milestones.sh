#!/bin/bash

# Ma3routes Milestone Management Script
# This script creates project milestones based on the development roadmap using the GitHub CLI (gh)

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}Creating Ma3routes Project Milestones...${NC}"

# Milestones based on ROADMAP.md phases
gh api repos/:owner/:repo/milestones -f title="Phase 1: Foundation & Core Infrastructure" \
  -f description="Establish the architectural backbone and design system. Setup multi-module structure, DI, and data layer foundation."

gh api repos/:owner/:repo/milestones -f title="Phase 2: Authentication & User Profile" \
  -f description="Enable user identity and personalization. Implement Auth module, profile management, and basic sync logic."

gh api repos/:owner/:repo/milestones -f title="Phase 3: Routes & Stages (Core Features)" \
  -f description="Deliver the primary value proposition — finding transit information. Implement route discovery and offline-first repositories."

gh api repos/:owner/:repo/milestones -f title="Phase 4: Navigation & Real-time Tracking" \
  -f description="Assist users during their commute. Implement real-time navigation, geofencing, and crowdsourced corrections."

gh api repos/:owner/:repo/milestones -f title="Phase 5: Sync, Analytics & Polish" \
  -f description="Optimize performance, data freshness, and user experience. Implement periodic sync engine and final UI polish."

echo -e "${GREEN}Milestone creation complete!${NC}"
