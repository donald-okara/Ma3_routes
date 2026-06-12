# Nairobi Matatu Transit Data Schema

This [schema](../docs/schema.sql) models Nairobi’s informal matatu transit system as a relational graph. It is designed to support navigation, discovery, and crowdsourced correction of transit data.

The model is intentionally flexible to accommodate inconsistent routing patterns, overlapping services, and evolving real-world behavior.

---

# 1. routes

Represents a canonical matatu route.

Each route is a stable identity tied to a route number used by commuters.

## Fields

- **id** (text, PK)
  Stable internal identifier for the route.

- **number** (text, unique, not null)
  Human-facing route label (e.g. `33F`, `111`, `46K`).

- **corridor** (text, nullable)
  High-level directional grouping (e.g. Thika, Ngong, Jogoo).
  Used for classification, not strict routing logic.

- **created_at** (timestamp)
  Record creation time.

---

# 2. stages

Represents physical boarding or alighting points in the city.

Stages are the primary user-facing navigation anchors.

## Fields

- **id** (text, PK)
  Stable identifier for the stage.

- **name** (text, not null)
  Display name of the stage (e.g. Kencom, OTC, Ngara).

- **area** (text, nullable)
  General locality or region (e.g. CBD, Westlands).

- **lat** (float, nullable)
  Geographic latitude for mapping.

- **lng** (float, nullable)
  Geographic longitude for mapping.

- **created_at** (timestamp)
  Record creation time.

---

# 3. route_destinations

Represents endpoints and variants associated with a route.

A route may serve multiple destinations or variations (loop, express, alternate).

## Fields

- **id** (bigint, PK)
  Auto-generated identifier.

- **route_id** (text, FK → routes.id)
  Associated route.

- **destination** (text, not null)
  Endpoint or served location.

- **variant** (text, nullable)
  Optional qualifier describing route behavior:
    - loop
    - express
    - alternate
    - short

- **route_destinations** (array of text)
  List of destinations served by this route variant.

---

# 4. stage_routes

Represents the many-to-many relationship between stages and routes.

This is the core graph structure of the system.

## Fields

- **id** (bigint, PK)
  Auto-generated identifier.

- **stage_id** (text, FK → stages.id)
  Stage where interaction occurs.

- **route_id** (text, FK → routes.id)
  Route serving the stage.

- **role** (text, default: `boarding`)
  Defines interaction type:
    - boarding
    - alighting

- **confidence** (float, default: 1.0)
  Confidence score for data reliability.
  Useful for inferred or crowdsourced entries.

- **source** (text, default: `system`)
  Origin of the record:
    - system
    - user
    - inferred

---

# 5. corrections

Stores user-submitted or system-detected corrections to existing data.

Enables continuous dataset refinement through crowdsourcing.

## Fields

- **id** (bigint, PK)
  Auto-generated identifier.

- **entity_type** (text)
  Target entity type:
    - route
    - stage
    - stage_route

- **entity_id** (text)
  Identifier of affected entity.

- **field** (text)
  Field being corrected.

- **old_value** (text)
  Existing value in the system.

- **new_value** (text)
  Proposed correction.

- **status** (text, default: `pending`)
  Review state:
    - pending
    - approved
    - rejected

- **created_at** (timestamp)
  Submission time.

---

# Relationship Model

## Core structure

- A **route** can serve many destinations.
- A **stage** can serve many routes.
- A **route** can appear in many stages.
- Relationships are modeled explicitly via `stage_routes`.

---

## Logical view

- routes → defines transport identity
- stages → defines physical access points
- stage_routes → defines connectivity graph
- route_destinations → defines route endpoints
- corrections → defines system evolution layer

---

# Design Intent

This schema is designed for:

- Informal transit modeling (non-deterministic routes)
- Stage-based navigation (user mental model)
- Graph-based routing and discovery
- Incremental enrichment through crowdsourcing
- Separation of canonical data from inferred data

The system prioritizes flexibility and correctness over strict normalization, reflecting the evolving nature of real-world matatu operations.
