-- ============================
-- RESET COMPLET DE LA BASE
-- ============================

-- Suppression des enfants d'abord
DELETE FROM participation;
DELETE FROM competition_phases;
DELETE FROM epreuve;
DELETE FROM equipe_sportif;
DELETE FROM sportif;
DELETE FROM equipe;
DELETE FROM competition;

-- Réinitialisation des séquences existantes
ALTER SEQUENCE equipe_id_equipe_seq RESTART WITH 1;
ALTER SEQUENCE sportif_id_sportif_seq RESTART WITH 1;
ALTER SEQUENCE participation_id_participation_seq RESTART WITH 1;
ALTER SEQUENCE epreuve_id_epreuve_seq RESTART WITH 1;
ALTER SEQUENCE competition_id_competition_seq RESTART WITH 1;

-- ============================
-- Création des équipes
-- ============================

INSERT INTO equipe (nom_equipe) VALUES 
('Team 1'), ('Team 2'), ('Team 3'), ('Team 4'), ('Team 5'),
('Team 6'), ('Team 7'), ('Team 8'), ('Team 9'), ('Team 10');


-- ============================
-- Création des sportifs
-- ============================

-- 5 premières équipes : 1 sportif chacune
INSERT INTO sportif (nom, prenom) VALUES
('Nom1', 'Prénom1'),
('Nom2', 'Prénom2'),
('Nom3', 'Prénom3'),
('Nom4', 'Prénom4'),
('Nom5', 'Prénom5');

-- 5 suivantes : 2 sportifs chacune (10 sportifs)
INSERT INTO sportif (nom, prenom) VALUES
('Nom6', 'Prénom6'),
('Nom7', 'Prénom7'),
('Nom8', 'Prénom8'),
('Nom9', 'Prénom9'),
('Nom10', 'Prénom10'),
('Nom11', 'Prénom11'),
('Nom12', 'Prénom12'),
('Nom13', 'Prénom13'),
('Nom14', 'Prénom14'),
('Nom15', 'Prénom15');


-- ============================
-- Liaisons équipes ↔ sportifs
-- ============================

-- 5 premières équipes (1 sportif chacune)
INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 1 AND s.id_sportif = 1;

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 2 AND s.id_sportif = 2;

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 3 AND s.id_sportif = 3;

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 4 AND s.id_sportif = 4;

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 5 AND s.id_sportif = 5;

-- 5 suivantes (2 sportifs chacune)
INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 6 AND s.id_sportif IN (6,7);

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 7 AND s.id_sportif IN (8,9);

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 8 AND s.id_sportif IN (10,11);

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 9 AND s.id_sportif IN (12,13);

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.id_equipe = 10 AND s.id_sportif IN (14,15);