-- WARNING: This schema is for context only and is not meant to be run.
-- Table order and constraints may not be valid for execution.

CREATE TABLE public.routes (
  id text NOT NULL,
  number text NOT NULL UNIQUE,
  corridor text,
  created_at timestamp without time zone DEFAULT now(),
  CONSTRAINT routes_pkey PRIMARY KEY (id)
);
CREATE TABLE public.stages (
  id text NOT NULL,
  name text NOT NULL,
  area text,
  lat double precision,
  lng double precision,
  created_at timestamp without time zone DEFAULT now(),
  CONSTRAINT stages_pkey PRIMARY KEY (id)
);
CREATE TABLE public.route_destinations (
  id bigint NOT NULL DEFAULT nextval('route_destinations_id_seq'::regclass),
  route_id text,
  destination text NOT NULL,
  variant text,
  route_destinations ARRAY DEFAULT '{}'::text[],
  CONSTRAINT route_destinations_pkey PRIMARY KEY (id),
  CONSTRAINT route_destinations_route_id_fkey FOREIGN KEY (route_id) REFERENCES public.routes(id)
);
CREATE TABLE public.stage_routes (
  id bigint NOT NULL DEFAULT nextval('stage_routes_id_seq'::regclass),
  stage_id text,
  route_id text,
  role text DEFAULT 'boarding'::text CHECK (role = ANY (ARRAY['boarding'::text, 'alighting'::text])),
  confidence double precision DEFAULT 1.0,
  source text DEFAULT 'system'::text,
  CONSTRAINT stage_routes_pkey PRIMARY KEY (id),
  CONSTRAINT stage_routes_stage_id_fkey FOREIGN KEY (stage_id) REFERENCES public.stages(id),
  CONSTRAINT stage_routes_route_id_fkey FOREIGN KEY (route_id) REFERENCES public.routes(id)
);
CREATE TABLE public.corrections (
  id bigint NOT NULL DEFAULT nextval('corrections_id_seq'::regclass),
  entity_type text,
  entity_id text,
  field text,
  old_value text,
  new_value text,
  status text DEFAULT 'pending'::text,
  created_at timestamp without time zone DEFAULT now(),
  CONSTRAINT corrections_pkey PRIMARY KEY (id)
);