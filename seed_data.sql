-- ============================
-- RESET COMPLET DE LA BASE
-- ============================


-- Suppression des enfants d'abord
-- TRUNCATE TABLE
-- participation,
-- etape_epreuve,
-- etape_equipe,
-- competition_phases,
-- epreuve,
-- equipe_sportif,
-- sportif,
-- equipe,
-- competition
-- RESTART IDENTITY CASCADE;

-- Réinitialisation des séquences existantes
-- ALTER SEQUENCE equipe_id_equipe_seq RESTART WITH 1;
-- ALTER SEQUENCE sportif_id_sportif_seq RESTART WITH 1;
-- ALTER SEQUENCE participation_id_participation_seq RESTART WITH 1;
-- ALTER SEQUENCE epreuve_id_epreuve_seq RESTART WITH 1;
-- ALTER SEQUENCE competition_id_competition_seq RESTART WITH 1;

-- ============================
-- Création des équipes
-- ============================

INSERT INTO equipe (nom_equipe) VALUES
('Les Foulées Rapides'),
('Sprint Nord'),
('Les Solitaires du Stade'),
('Run Power'),
('Les Gazelles'),
('Les Flèches Rouges'),
('Ultra Solo'),
('Les Coureurs Libres'),
('Solo Performance'),
('Nord Running'),
('Speed Force'),
('Les Endurants'),
('Solo Elite'),
('Run Storm'),
('Les Turbos'),
('Les Rapides'),
('Solo Horizon'),
('Les Sprinteurs'),
('Endurance Max'),
('Solo Victory'),

-- équipes duo
('Duo Dynamique'),
('Les Binômes Rapides'),
('Double Impact'),
('Les Frères de Piste'),
('Duo Nordique'),
('Les Inséparables'),
('Double Endurance'),
('Les Fusées'),
('Duo Sprint'),
('Les Compagnons de Course'),
('Twin Speed'),
('Les Coureurs Unis'),
('Double Energie'),
('Duo Horizon'),
('Les Alliés du Run');


-- ============================
-- Création des sportifs (20)
-- ============================

INSERT INTO sportif (nom, prenom) VALUES
('Martin','Lucas'),
('Bernard','Hugo'),
('Dubois','Nathan'),
('Thomas','Louis'),
('Robert','Jules'),
('Richard','Adam'),
('Petit','Léo'),
('Durand','Noah'),
('Leroy','Ethan'),
('Moreau','Gabriel'),
('Simon','Arthur'),
('Laurent','Paul'),
('Lefebvre','Tom'),
('Michel','Enzo'),
('Garcia','Raphaël'),
('David','Maxime'),
('Bertrand','Antoine'),
('Roux','Mathis'),
('Vincent','Alexis'),
('Fournier','Clément');


-- ============================
-- Liaisons équipes ↔ sportifs
-- ============================

-- 20 équipes solo
INSERT INTO equipe_sportif VALUES (1,1);
INSERT INTO equipe_sportif VALUES (2,2);
INSERT INTO equipe_sportif VALUES (3,3);
INSERT INTO equipe_sportif VALUES (4,4);
INSERT INTO equipe_sportif VALUES (5,5);
INSERT INTO equipe_sportif VALUES (6,6);
INSERT INTO equipe_sportif VALUES (7,7);
INSERT INTO equipe_sportif VALUES (8,8);
INSERT INTO equipe_sportif VALUES (9,9);
INSERT INTO equipe_sportif VALUES (10,10);
INSERT INTO equipe_sportif VALUES (11,11);
INSERT INTO equipe_sportif VALUES (12,12);
INSERT INTO equipe_sportif VALUES (13,13);
INSERT INTO equipe_sportif VALUES (14,14);
INSERT INTO equipe_sportif VALUES (15,15);
INSERT INTO equipe_sportif VALUES (16,16);
INSERT INTO equipe_sportif VALUES (17,17);
INSERT INTO equipe_sportif VALUES (18,18);
INSERT INTO equipe_sportif VALUES (19,19);
INSERT INTO equipe_sportif VALUES (20,20);

-- 15 équipes duo (réutilisation des sportifs)
INSERT INTO equipe_sportif VALUES (21,1),(21,2);
INSERT INTO equipe_sportif VALUES (22,3),(22,4);
INSERT INTO equipe_sportif VALUES (23,5),(23,6);
INSERT INTO equipe_sportif VALUES (24,7),(24,8);
INSERT INTO equipe_sportif VALUES (25,9),(25,10);
INSERT INTO equipe_sportif VALUES (26,11),(26,12);
INSERT INTO equipe_sportif VALUES (27,13),(27,14);
INSERT INTO equipe_sportif VALUES (28,15),(28,16);
INSERT INTO equipe_sportif VALUES (29,17),(29,18);
INSERT INTO equipe_sportif VALUES (30,19),(30,20);
INSERT INTO equipe_sportif VALUES (31,1),(31,3);
INSERT INTO equipe_sportif VALUES (32,5),(32,7);
INSERT INTO equipe_sportif VALUES (33,9),(33,11);
INSERT INTO equipe_sportif VALUES (34,13),(34,15);
INSERT INTO equipe_sportif VALUES (35,17),(35,19);

-- ============================
-- Création des lieux
-- ============================

INSERT INTO lieu (nom_lieu, ville, adresse) VALUES
('Stade Pierre-Mauroy', 'Lille', '261 Boulevard de Tournai'),
('Parc des Sports', 'Roubaix', 'Avenue Alfred Motte'),
('Complexe Sportif Jean Bouin', 'Paris', '20 Avenue du Général Sarrail'),
('Stade Vélodrome', 'Marseille', '3 Boulevard Michelet'),
('Stade de Gerland', 'Lyon', '353 Avenue Jean Jaurès'),
('Complexe Sportif Bordeaux Lac', 'Bordeaux', 'Rue du Petit Barail'),
('Stade Marcel Picot', 'Nancy', '90 Boulevard Jean Jaurès'),
('Stade de la Mosson', 'Montpellier', '345 Avenue de Heidelberg'),
('Complexe Sportif Nantes Métropole', 'Nantes', '2 Boulevard de Berlin'),
('Piscine Olympique', 'Paris', '1 Avenue des Sports');