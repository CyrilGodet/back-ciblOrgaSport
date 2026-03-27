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

INSERT INTO lieu (nom_lieu, ville, adresse, categorie_lieu) VALUES
-- Lieux génériques (ID 1-10)
('Stade Pierre-Mauroy', 'Lille', '261 Boulevard de Tournai', 'EVENEMENT'),
('Parc des Sports', 'Roubaix', 'Avenue Alfred Motte', 'EVENEMENT'),
('Complexe Sportif Jean Bouin', 'Paris', '20 Avenue du Général Sarrail', 'EVENEMENT'),
('Stade Vélodrome', 'Marseille', '3 Boulevard Michelet', 'EVENEMENT'),
('Stade de Gerland', 'Lyon', '353 Avenue Jean Jaurès', 'EVENEMENT'),
('Complexe Sportif Bordeaux Lac', 'Bordeaux', 'Rue du Petit Barail', 'EVENEMENT'),
('Stade Marcel Picot', 'Nancy', '90 Boulevard Jean Jaurès', 'EVENEMENT'),
('Stade de la Mosson', 'Montpellier', '345 Avenue de Heidelberg', 'EVENEMENT'),
('Complexe Sportif Nantes Métropole', 'Nantes', '2 Boulevard de Berlin', 'EVENEMENT'),
('Piscine Olympique', 'Paris', '1 Avenue des Sports', 'EVENEMENT'),
-- Lieux personnels (ID 11-33)
('lucas.martin@example.com', 'Paris', 'Domicile', 'UTILISATEUR'),
('hugo.bernard@example.com', 'Marseille', 'Domicile', 'UTILISATEUR'),
('nathan.dubois@example.com', 'Lyon', 'Domicile', 'UTILISATEUR'),
('louis.thomas@example.com', 'Toulouse', 'Domicile', 'UTILISATEUR'),
('jules.robert@example.com', 'Nice', 'Domicile', 'UTILISATEUR'),
('adam.richard@example.com', 'Nantes', 'Domicile', 'UTILISATEUR'),
('leo.petit@example.com', 'Strasbourg', 'Domicile', 'UTILISATEUR'),
('noah.durand@example.com', 'Montpellier', 'Domicile', 'UTILISATEUR'),
('ethan.leroy@example.com', 'Bordeaux', 'Domicile', 'UTILISATEUR'),
('gabriel.moreau@example.com', 'Lille', 'Domicile', 'UTILISATEUR'),
('arthur.simon@example.com', 'Rennes', 'Domicile', 'UTILISATEUR'),
('paul.laurent@example.com', 'Reims', 'Domicile', 'UTILISATEUR'),
('tom.lefebvre@example.com', 'Le Havre', 'Domicile', 'UTILISATEUR'),
('enzo.michel@example.com', 'Saint-Étienne', 'Domicile', 'UTILISATEUR'),
('raphael.garcia@example.com', 'Toulon', 'Domicile', 'UTILISATEUR'),
('maxime.david@example.com', 'Grenoble', 'Domicile', 'UTILISATEUR'),
('antoine.bertrand@example.com', 'Dijon', 'Domicile', 'UTILISATEUR'),
('mathis.roux@example.com', 'Angers', 'Domicile', 'UTILISATEUR'),
('alexis.vincent@example.com', 'Villeurbanne', 'Domicile', 'UTILISATEUR'),
('clement.fournier@example.com', 'Le Mans', 'Domicile', 'UTILISATEUR'),
('admin@glop.com', 'Paris', 'Bureau Admin', 'UTILISATEUR'),
('commissaire@glop.com', 'Lyon', 'Bureau Commissaire', 'UTILISATEUR'),
('visiteur@glop.com', 'Marseille', 'Accueil', 'UTILISATEUR');

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

INSERT INTO utilisateur (nom, prenom, email, age, state, id_lieu, dtype) VALUES
('Martin','Lucas', 'lucas.martin@example.com', 25, 1, 11, 'SPORTIF'),
('Bernard','Hugo', 'hugo.bernard@example.com', 28, 1, 12, 'SPORTIF'),
('Dubois','Nathan', 'nathan.dubois@example.com', 22, 1, 13, 'SPORTIF'),
('Thomas','Louis', 'louis.thomas@example.com', 30, 1, 14, 'SPORTIF'),
('Robert','Jules', 'jules.robert@example.com', 24, 1, 15, 'SPORTIF'),
('Richard','Adam', 'adam.richard@example.com', 26, 1, 16, 'SPORTIF'),
('Petit','Léo', 'leo.petit@example.com', 27, 1, 17, 'SPORTIF'),
('Durand','Noah', 'noah.durand@example.com', 23, 1, 18, 'SPORTIF'),
('Leroy','Ethan', 'ethan.leroy@example.com', 29, 1, 19, 'SPORTIF'),

('Moreau','Gabriel', 'gabriel.moreau@example.com', 25, 1, 20, 'SPORTIF'),
('Simon','Arthur', 'arthur.simon@example.com', 21, 1, 21, 'SPORTIF'),
('Laurent','Paul', 'paul.laurent@example.com', 32, 1, 22, 'SPORTIF'),
('Lefebvre','Tom', 'tom.lefebvre@example.com', 26, 1, 23, 'SPORTIF'),
('Michel','Enzo', 'enzo.michel@example.com', 24, 1, 24, 'SPORTIF'),
('Garcia','Raphaël', 'raphael.garcia@example.com', 28, 1, 25, 'SPORTIF'),
('David','Maxime', 'maxime.david@example.com', 27, 1, 26, 'SPORTIF'),
('Bertrand','Antoine', 'antoine.bertrand@example.com', 25, 1, 27, 'SPORTIF'),
('Roux','Mathis', 'mathis.roux@example.com', 23, 1, 28, 'SPORTIF'),
('Vincent','Alexis', 'alexis.vincent@example.com', 29, 1, 29, 'SPORTIF'),
('Fournier','Clément', 'clement.fournier@example.com', 31, 1, 30, 'SPORTIF'),
('Admin', 'Jean', 'admin@glop.com', 40, 1, 31, 'ADMIN'),
('Alice', 'Commissaire', 'commissaire@glop.com', 35, 1, 32, 'COMMISSAIRE'),
('Bob', 'Visiteur', 'visiteur@glop.com', 20, 1, 33, 'VISITEUR');

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



-- ============================
-- Ajout de Volontaires
-- ============================

INSERT INTO utilisateur (nom, prenom, email, age, state, id_lieu, dtype) VALUES
('Dupont','Marie','marie.dupont@example.com', 30, 1, 1, 'VOLONTAIRE'),
('Lemoine','Julien','julien.lemoine@example.com', 35, 1, 2, 'VOLONTAIRE'),
('Carpentier','Sophie','sophie.carpentier@example.com', 28, 1, 3, 'VOLONTAIRE'),
('Morel','Antoine','antoine.morel@example.com', 40, 1, 4, 'VOLONTAIRE'),
('Fournier','Emma','emma.fournier@example.com', 32, 1, 5, 'VOLONTAIRE'),
('Rousseau','Lucas','lucas.rousseau@example.com', 27, 1, 6, 'VOLONTAIRE'),
('Blanc','Clara','clara.blanc@example.com', 26, 1, 7, 'VOLONTAIRE'),
('Gauthier','Thomas','thomas.gauthier@example.com', 29, 1, 8, 'VOLONTAIRE'),
('Martinez','Laura','laura.martinez@example.com', 31, 1, 9, 'VOLONTAIRE'),
('Petit','Maxime','maxime.petit@example.com', 33, 1, 10, 'VOLONTAIRE');



-- ============================
-- Création de compétitions en DRAFT
-- ============================

INSERT INTO competition (
    name_competition,
    description,
    statut,
    age_min,
    age_max,
    date_debut,
    date_fin,
    genre,
    sport,
    id_lieu
) VALUES
(
    'Tournoi de Printemps',
    'Compétition en préparation',
    0,
    0,
    99,
    NOW(),
    NOW(),
    0,
    0,
    1
),
(
    'Challenge Été 2026',
    'Brouillon du tournoi estival',
    0,
    0,
    99,
    NOW(),
    NOW(),
    0,
    0,
    1
);

INSERT INTO epreuve (
    nom_epreuve,
    description,
    age_min,
    age_max,
    date_debut,
    date_fin,
    discipline,
    genre,
    nb_elim_par_match,
    nombre_equipe_par_match,
    statut,
    taille_equipe,
    competition_id_competition,
    type_resultat
) VALUES (
    'Épreuve dsfhdshfklfdhskjh',                   -- nom
    'Épreuve test', -- description
    1,                                -- age_min
    99,                               -- age_max
    CURRENT_DATE,                     -- date_debut
    CURRENT_DATE + INTERVAL '1 day',  -- date_fin
    0,                                -- discipline (0..8)
    0,                                -- genre (0..2)
    1,                                -- nb_elim_par_match
    2,                                -- nombre_equipe_par_match
    0,                                -- statut (0..5)
    2,                                -- taille_equipe
    2,                                -- competition existante
    'POINTS'                          -- type_resultat
);


INSERT INTO epreuve (
    nom_epreuve,
    description,
    age_min,
    age_max,
    date_debut,
    date_fin,
    discipline,
    genre,
    nb_elim_par_match,
    nombre_equipe_par_match,
    statut,
    taille_equipe,
    competition_id_competition,
    type_resultat
) VALUES (
    'Épreuve Test',                   -- nom
    'Épreuve pour test affectations', -- description
    0,                                -- age_min
    99,                               -- age_max
    CURRENT_DATE,                     -- date_debut
    CURRENT_DATE + INTERVAL '1 day',  -- date_fin
    0,                                -- discipline (0..8)
    0,                                -- genre (0..2)
    1,                                -- nb_elim_par_match
    2,                                -- nombre_equipe_par_match
    0,                                -- statut (0..5)
    2,                                -- taille_equipe
    1,                                -- competition existante
    'POINTS'                          -- type_resultat
);





INSERT INTO affectation_volontaire (
    volontaire_id_utilisateur,
    epreuve_id_epreuve,
    date_affectation,
    heure_debut,
    heure_fin,
    lieu_rdv_id_lieu,
    poste,
    commentaire
) VALUES
(
    25,                 -- ID utilisateur (volontaire)
    1,                  -- ID épreuve
    CURRENT_DATE,
    '08:00:00',
    '12:00:00',
    1,                  -- lieu
    0,                  -- poste (ordinal enum)
    'Accueil des participants'
),
(
    26,
    1,
    CURRENT_DATE,
    '12:00:00',
    '16:00:00',
    1,
    1,
    'Gestion ravitaillement'
);

-- ============================
-- Création des comptes (Authentification)
-- ============================

INSERT INTO compte (username, password, type, active, date_creation, id_utilisateur) VALUES
('admin', 'password', 'ADMIN', true, NOW(), 21),
('commissaire', 'password', 'COMMISSAIRE', true, NOW(), 22),
('visiteur', 'password', 'VISITEUR', true, NOW(), 23),
('sportif', 'password', 'SPORTIF', true, NOW(), 1),
('volontaire', 'password', 'VOLONTAIRE', true, NOW(), 24);