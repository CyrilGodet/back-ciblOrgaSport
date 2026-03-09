-- Fix schema for Epreuve, EtapeEpreuve and Match
-- Run this in your PostgreSQL database (D glop)

-- 1. Add missing Column
ALTER TABLE epreuve ADD COLUMN IF NOT EXISTS nombre_equipe_par_match INT NOT NULL DEFAULT 2;

-- 2. Drop faulty check constraints caused by Enum renaming/additions
-- Hibernate creates these constraints when ddl-auto is used.
-- Changing enums in Java (adding values or renaming) doesn't automatically update these constraints.
ALTER TABLE competition_phases DROP CONSTRAINT IF EXISTS competition_phases_phases_check;
ALTER TABLE etape_epreuve DROP CONSTRAINT IF EXISTS etape_epreuve_etape_epreuve_enum_check;

-- Optional: If you see issues with MatchStatusEnum as well
-- ALTER TABLE match DROP CONSTRAINT IF EXISTS match_status_check;
