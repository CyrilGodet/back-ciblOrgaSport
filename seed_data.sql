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
-- utilisateur,
-- equipe,
-- competition,
-- lieu
-- RESTART IDENTITY CASCADE;

-- Réinitialisation des séquences
-- ALTER SEQUENCE equipe_id_equipe_seq RESTART WITH 1;
-- ALTER SEQUENCE utilisateur_id_utilisateur_seq RESTART WITH 1;
-- ALTER SEQUENCE participation_id_participation_seq RESTART WITH 1;
-- ALTER SEQUENCE epreuve_id_epreuve_seq RESTART WITH 1;
-- ALTER SEQUENCE competition_id_competition_seq RESTART WITH 1;
-- ALTER SEQUENCE lieu_id_lieu_seq RESTART WITH 1;

-- ============================
-- Création des lieux
-- ============================

INSERT INTO lieu (nom_lieu, ville, adresse) VALUES
-- Lieux génériques (ID 1-10)
('Stade Pierre-Mauroy', 'Lille', '261 Boulevard de Tournai'),
('Parc des Sports', 'Roubaix', 'Avenue Alfred Motte'),
('Complexe Sportif Jean Bouin', 'Paris', '20 Avenue du Général Sarrail'),
('Stade Vélodrome', 'Marseille', '3 Boulevard Michelet'),
('Stade de Gerland', 'Lyon', '353 Avenue Jean Jaurès'),
('Complexe Sportif Bordeaux Lac', 'Bordeaux', 'Rue du Petit Barail'),
('Stade Marcel Picot', 'Nancy', '90 Boulevard Jean Jaurès'),
('Stade de la Mosson', 'Montpellier', '345 Avenue de Heidelberg'),
('Complexe Sportif Nantes Métropole', 'Nantes', '2 Boulevard de Berlin'),
('Piscine Olympique', 'Paris', '1 Avenue des Sports'),
-- Lieux personnels (ID 11-33)
('lucas.martin@example.com', 'Paris', 'Domicile'),
('hugo.bernard@example.com', 'Marseille', 'Domicile'),
('nathan.dubois@example.com', 'Lyon', 'Domicile'),
('louis.thomas@example.com', 'Toulouse', 'Domicile'),
('jules.robert@example.com', 'Nice', 'Domicile'),
('adam.richard@example.com', 'Nantes', 'Domicile'),
('leo.petit@example.com', 'Strasbourg', 'Domicile'),
('noah.durand@example.com', 'Montpellier', 'Domicile'),
('ethan.leroy@example.com', 'Bordeaux', 'Domicile'),
('gabriel.moreau@example.com', 'Lille', 'Domicile'),
('arthur.simon@example.com', 'Rennes', 'Domicile'),
('paul.laurent@example.com', 'Reims', 'Domicile'),
('tom.lefebvre@example.com', 'Le Havre', 'Domicile'),
('enzo.michel@example.com', 'Saint-Étienne', 'Domicile'),
('raphael.garcia@example.com', 'Toulon', 'Domicile'),
('maxime.david@example.com', 'Grenoble', 'Domicile'),
('antoine.bertrand@example.com', 'Dijon', 'Domicile'),
('mathis.roux@example.com', 'Angers', 'Domicile'),
('alexis.vincent@example.com', 'Villeurbanne', 'Domicile'),
('clement.fournier@example.com', 'Le Mans', 'Domicile'),
('admin@glop.com', 'Paris', 'Bureau Admin'),
('commissaire@glop.com', 'Lyon', 'Bureau Commissaire'),
('visiteur@glop.com', 'Marseille', 'Accueil');

-- ============================
-- Création des équipes (Maintenant via Polymorphisme Participant)
-- ============================

-- First we create the Participants, then the ParticipantEquipe
-- Let's create Participants for all 35 teams. 
-- We assume IDs will be 1 to 35 for these teams in the participant table.

INSERT INTO participant (id_participant) VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10),
(11), (12), (13), (14), (15), (16), (17), (18), (19), (20),
(21), (22), (23), (24), (25), (26), (27), (28), (29), (30),
(31), (32), (33), (34), (35);

INSERT INTO participant_equipe (nom_equipe, id_participant) VALUES
('Les Foulées Rapides', 1), ('Sprint Nord', 2), ('Les Solitaires du Stade', 3), ('Run Power', 4), ('Les Gazelles', 5),
('Les Flèches Rouges', 6), ('Ultra Solo', 7), ('Les Coureurs Libres', 8), ('Solo Performance', 9), ('Nord Running', 10),
('Speed Force', 11), ('Les Endurants', 12), ('Solo Elite', 13), ('Run Storm', 14), ('Les Turbos', 15),
('Les Rapides', 16), ('Solo Horizon', 17), ('Les Sprinteurs', 18), ('Endurance Max', 19), ('Solo Victory', 20),
('Duo Dynamique', 21), ('Les Binômes Rapides', 22), ('Double Impact', 23), ('Les Frères de Piste', 24), ('Duo Nordique', 25),
('Les Inséparables', 26), ('Double Endurance', 27), ('Les Fusées', 28), ('Duo Sprint', 29), ('Les Compagnons de Course', 30),
('Twin Speed', 31), ('Les Coureurs Unis', 32), ('Double Energie', 33), ('Duo Horizon', 34), ('Les Alliés du Run', 35);

-- ============================
-- Création des utilisateurs
-- ============================

INSERT INTO utilisateur (nom, prenom, email, age, user_type, id_lieu) VALUES
('Martin','Lucas', 'lucas.martin@example.com', 25, 'SPORTIF', 11),
('Bernard','Hugo', 'hugo.bernard@example.com', 28, 'SPORTIF', 12),
('Dubois','Nathan', 'nathan.dubois@example.com', 22, 'SPORTIF', 13),
('Thomas','Louis', 'louis.thomas@example.com', 30, 'SPORTIF', 14),
('Robert','Jules', 'jules.robert@example.com', 24, 'SPORTIF', 15),
('Richard','Adam', 'adam.richard@example.com', 26, 'SPORTIF', 16),
('Petit','Léo', 'leo.petit@example.com', 27, 'SPORTIF', 17),
('Durand','Noah', 'noah.durand@example.com', 23, 'SPORTIF', 18),
('Leroy','Ethan', 'ethan.leroy@example.com', 29, 'SPORTIF', 19),
('Moreau','Gabriel', 'gabriel.moreau@example.com', 25, 'SPORTIF', 20),
('Simon','Arthur', 'arthur.simon@example.com', 21, 'SPORTIF', 21),
('Laurent','Paul', 'paul.laurent@example.com', 32, 'SPORTIF', 22),
('Lefebvre','Tom', 'tom.lefebvre@example.com', 26, 'SPORTIF', 23),
('Michel','Enzo', 'enzo.michel@example.com', 24, 'SPORTIF', 24),
('Garcia','Raphaël', 'raphael.garcia@example.com', 28, 'SPORTIF', 25),
('David','Maxime', 'maxime.david@example.com', 27, 'SPORTIF', 26),
('Bertrand','Antoine', 'antoine.bertrand@example.com', 25, 'SPORTIF', 27),
('Roux','Mathis', 'mathis.roux@example.com', 23, 'SPORTIF', 28),
('Vincent','Alexis', 'alexis.vincent@example.com', 29, 'SPORTIF', 29),
('Fournier','Clément', 'clement.fournier@example.com', 31, 'SPORTIF', 30),
('Admin', 'Jean', 'admin@glop.com', 40, 'ADMIN', 31),
('Alice', 'Commissaire', 'commissaire@glop.com', 35, 'COMMISSAIRE', 32),
('Bob', 'Visiteur', 'visiteur@glop.com', 20, 'VISITEUR', 33);

-- ============================
-- Liaisons équipes ↔ sportifs (Via participant_equipe_sportif)
-- ============================

-- Solo (1-20)
-- Table should be renamed to participant_equipe_sportif or similar based on mapping, in Java it is `equipe_sportif` on ParticipantEquipe
--INSERT INTO equipe_sportif (equipe_id, sportif_id) VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),
--(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20);

-- Duo (21-35)
INSERT INTO equipe_sportif (equipe_id, sportif_id) VALUES (21,1),(21,2),(22,3),(22,4),(23,5),(23,6),(24,7),(24,8),(25,9),(25,10),
(26,11),(26,12),(27,13),(27,14),(28,15),(28,16),(29,17),(29,18),(30,19),(30,20),(31,1),(31,3),
(32,5),(32,7),(33,9),(33,11),(34,13),(34,15),(35,17),(35,19);


-- Équipes de 3 joueurs
INSERT INTO equipe_sportif (equipe_id, sportif_id) VALUES
(11,1),(11,2),(11,3),
(12,4),(12,5),(12,6),
(13,7),(13,8),(13,9),
(14,10),(14,11),(14,12),
(15,13),(15,14),(15,15),
(16,16),(16,17),(16,18),
(17,19),(17,20),(17,1),
(18,2),(18,3),(18,4),
(19,5),(19,6),(19,7),
(20,8),(20,9),(20,10);

-- Also create ParticipantSportif entries for all sportifs (1-20)
-- Need to map IDs correctly. Let's assume Participant IDs 36 to 55 for sportifs.
INSERT INTO participant (id_participant) VALUES (36), (37), (38), (39), (40), (41), (42), (43), (44), (45),
(46), (47), (48), (49), (50), (51), (52), (53), (54), (55);

INSERT INTO participant_sportif (sportif_id, id_participant) VALUES 
(1, 36), (2, 37), (3, 38), (4, 39), (5, 40), (6, 41), (7, 42), (8, 43), (9, 44), (10, 45),
(11, 46), (12, 47), (13, 48), (14, 49), (15, 50), (16, 51), (17, 52), (18, 53), (19, 54), (20, 55);
