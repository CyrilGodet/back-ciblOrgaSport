-- ============================
-- Création des équipes
-- ============================

INSERT INTO equipe (nom_equipe) VALUES ('Team Alpha');
INSERT INTO equipe (nom_equipe) VALUES ('Team Beta');
INSERT INTO equipe (nom_equipe) VALUES ('Team Gamma');


-- ============================
-- Création des sportifs
-- ============================

-- Team Alpha (2 sportifs)
INSERT INTO sportif (nom, prenom) VALUES ('Lefebvre', 'Thomas');
INSERT INTO sportif (nom, prenom) VALUES ('Martin', 'Julie');

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.nom_equipe = 'Team Alpha' AND s.nom = 'Lefebvre' AND s.prenom = 'Thomas';

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.nom_equipe = 'Team Alpha' AND s.nom = 'Martin' AND s.prenom = 'Julie';


-- Team Beta (2 sportifs)
INSERT INTO sportif (nom, prenom) VALUES ('Dubois', 'Sophie');
INSERT INTO sportif (nom, prenom) VALUES ('Petit', 'Lucas');

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.nom_equipe = 'Team Beta' AND s.nom = 'Dubois' AND s.prenom = 'Sophie';

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.nom_equipe = 'Team Beta' AND s.nom = 'Petit' AND s.prenom = 'Lucas';


-- Team Gamma (1 sportif)
INSERT INTO sportif (nom, prenom) VALUES ('Moreau', 'Antoine');

INSERT INTO equipe_sportif (equipe_id, sportif_id)
SELECT e.id_equipe, s.id_sportif
FROM equipe e, sportif s
WHERE e.nom_equipe = 'Team Gamma' AND s.nom = 'Moreau' AND s.prenom = 'Antoine';