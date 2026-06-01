#!/bin/bash

# Ma3routes Issue-Milestone Assignment Script
# This script assigns issues to their respective milestones based on their phase labels

GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}Assigning Ma3routes Issues to Milestones...${NC}"

# Define mappings: label -> milestone_number
# Milestone 1: Phase 1: Foundation & Core Infrastructure
# Milestone 2: Phase 2: Authentication & User Profile
# Milestone 3: Phase 3: Routes & Stages (Core Features)
# Milestone 4: Phase 4: Navigation & Real-time Tracking
# Milestone 5: Phase 5: Sync, Analytics & Polish

assign_by_label() {
    local label=$1
    local milestone=$2
    
    echo -e "Assigning issues with label ${BLUE}$label${NC} to milestone ${GREEN}$milestone${NC}..."
    
    # Get all issue numbers with the specific label
    issues=$(gh issue list --label "$label" --limit 100 --json number --jq '.[].number')
    
    for issue in $issues; do
        gh issue edit "$issue" --milestone "Phase $milestone"
        echo "Assigned issue #$issue to milestone Phase $milestone"
    done
}

assign_by_label "phase:1-foundation" "1: Foundation & Core Infrastructure"
assign_by_label "phase:2-auth-profile" "2: Authentication & User Profile"
assign_by_label "phase:3-routes-stages" "3: Routes & Stages (Core Features)"
assign_by_label "phase:4-navigation" "4: Navigation & Real-time Tracking"
assign_by_label "phase:5-sync-polish" "5: Sync, Analytics & Polish"

echo -e "${GREEN}Issue assignment complete!${NC}"
