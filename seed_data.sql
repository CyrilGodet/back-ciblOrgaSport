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
-- Lieux personnels (ID 11-25 for reduced set)
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
('admin@glop.com', 'Paris', 'Bureau Admin', 'UTILISATEUR'),
('commissaire@glop.com', 'Lyon', 'Bureau Commissaire', 'UTILISATEUR'),
('visiteur@glop.com', 'Marseille', 'Accueil', 'UTILISATEUR');

-- ============================
-- Création des équipes (Maintenant via Polymorphisme Participant)
-- ============================

INSERT INTO participant (id_participant) VALUES 
(1), (2), (3), (4), (5), (6), (7), (8), (9), (10),
(11), (12), (13), (14), (15);

INSERT INTO participant_equipe (nom_equipe, id_participant) VALUES
('Les Foulées Rapides', 1), ('Sprint Nord', 2), ('Les Solitaires du Stade', 3), ('Run Power', 4), ('Les Gazelles', 5),
('Les Flèches Rouges', 6), ('Ultra Solo', 7), ('Les Coureurs Libres', 8), ('Solo Performance', 9), ('Nord Running', 10),
('Duo Dynamique', 11), ('Les Binômes Rapides', 12), ('Double Impact', 13), ('Les Frères de Piste', 14), ('Duo Nordique', 15);

-- ============================
-- Création des utilisateurs
-- ============================

INSERT INTO utilisateur (nom, prenom, email, age, state, id_lieu, dtype, est_conforme_charte_europeenne, a_founi_passeport, a_founicertificat_medical, est_acreditecen) VALUES
('Martin','Lucas', 'lucas.martin@example.com', 25, 1, 11, 'SPORTIF', false, true, true, NULL),
('Bernard','Hugo', 'hugo.bernard@example.com', 28, 1, 12, 'SPORTIF', false, true, true, NULL),
('Dubois','Nathan', 'nathan.dubois@example.com', 22, 1, 13, 'SPORTIF', false, true, true, NULL),
('Thomas','Louis', 'louis.thomas@example.com', 30, 1, 14, 'SPORTIF', false, true, true, NULL),
('Robert','Jules', 'jules.robert@example.com', 24, 1, 15, 'SPORTIF', false, true, true, NULL),
('Richard','Adam', 'adam.richard@example.com', 26, 1, 16, 'SPORTIF', false, true, true, NULL),
('Petit','Léo', 'leo.petit@example.com', 27, 1, 17, 'SPORTIF', false, true, true, NULL),
('Durand','Noah', 'noah.durand@example.com', 23, 1, 18, 'SPORTIF', false, true, true, NULL),
('Leroy','Ethan', 'ethan.leroy@example.com', 29, 1, 19, 'SPORTIF', false, true, true, NULL),
('Moreau','Gabriel', 'gabriel.moreau@example.com', 25, 1, 20, 'SPORTIF', false, true, true, NULL),
('Admin', 'Jean', 'admin@glop.com', 40, 1, 21, 'ADMIN', NULL, NULL, NULL, NULL),
('Alice', 'Commissaire', 'commissaire@glop.com', 35, 1, 22, 'COMMISSAIRE', NULL, NULL, NULL, true),
('Bob', 'Visiteur', 'visiteur@glop.com', 20, 1, 23, 'VISITEUR', NULL, NULL, NULL, NULL);

-- ============================
-- Liaisons équipes ↔ sportifs
-- ============================

-- Trios (1-5 in participant table -> IDs 1-5 in participant_equipe)
INSERT INTO equipe_sportif (equipe_id, sportif_id) VALUES
(1,1),(1,2),(1,3),
(2,4),(2,5),(2,6),
(3,7),(3,8),(3,9),
(4,10),(4,1),(4,2),
(5,3),(5,4),(5,5);

-- Duos (6-15)
INSERT INTO equipe_sportif (equipe_id, sportif_id) VALUES 
(6,1),(6,2),(7,3),(7,4),(8,5),(8,6),(9,7),(9,8),(10,9),(10,10),
(11,1),(11,3),(12,5),(12,7),(13,9),(13,2),(14,4),(14,6),(15,8),(15,10);

-- Participant IDs 16-25 for sportifs 1-10
INSERT INTO participant (id_participant) VALUES (16), (17), (18), (19), (20), (21), (22), (23), (24), (25);

INSERT INTO participant_sportif (sportif_id, id_participant) VALUES 
(1, 16), (2, 17), (3, 18), (4, 19), (5, 20), (6, 21), (7, 22), (8, 23), (9, 24), (10, 25);

-- ============================
-- Ajout de Volontaires
-- ============================

INSERT INTO utilisateur (nom, prenom, email, age, state, id_lieu, dtype, est_conforme_charte_europeenne, a_founi_passeport, a_founicertificat_medical, est_acreditecen) VALUES
('Dupont','Marie','marie.dupont@example.com', 30, 1, 1, 'VOLONTAIRE', NULL, NULL, NULL, NULL),
('Lemoine','Julien','julien.lemoine@example.com', 35, 1, 2, 'VOLONTAIRE', NULL, NULL, NULL, NULL),
('Carpentier','Sophie','sophie.carpentier@example.com', 28, 1, 3, 'VOLONTAIRE', NULL, NULL, NULL, NULL),
('Morel','Antoine','antoine.morel@example.com', 40, 1, 4, 'VOLONTAIRE', NULL, NULL, NULL, NULL),
('Fournier','Emma','emma.fournier@example.com', 32, 1, 5, 'VOLONTAIRE', NULL, NULL, NULL, NULL);

-- Super Admin account
INSERT INTO utilisateur (nom, prenom, email, age, state, id_lieu, dtype, mdp, id_roles) VALUES
('Admin', 'Super', 'superadmin@glop.com', 30, 10, 21, 'ADMIN', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 1);

-- ============================
-- Création de compétitions en DRAFT
-- ============================

INSERT INTO competition (name_competition, description, statut, age_min, age_max, date_debut, date_fin, genre, sport, id_lieu) VALUES
('Tournoi de Printemps', 'Compétition printanière d''athlétisme', 0, 0, 99, '2026-04-01 08:00:00', '2026-04-15 20:00:00', 0, 0, 1),
('Challenge Été 2026', 'Le grand rendez-vous estival', 0, 0, 99, '2026-07-01 08:00:00', '2026-07-31 22:00:00', 0, 0, 1);

INSERT INTO epreuve (nom_epreuve, description, age_min, age_max, date_debut, date_fin, discipline, genre, nb_elim_par_match, nombre_equipe_par_match, statut, taille_equipe, competition_id_competition, type_resultat, commissaire_id) VALUES
('Épreuve Vitesse Printemps', 'Sprint 100m', 1, 99, '2026-04-05 09:00:00', '2026-04-05 18:00:00', 0, 0, 1, 2, 0, 2, 1, 'POINTS', 12),
('Challenge Endurance Été', 'Course de fond 5000m', 0, 99, '2026-07-10 10:00:00', '2026-07-10 17:00:00', 0, 0, 1, 2, 0, 2, 2, 'POINTS', 12);

-- ============================
-- Participations aux épreuves
-- ============================

-- Participations pour Épreuve 1 (ID 1)
-- On utilise les participants individuels (16 à 25)
INSERT INTO participation (competition_id_competition, participant_id_participant, epreuve_id, statut) VALUES
(1, 16, 1, 'QUALIFIE'), (1, 17, 1, 'QUALIFIE'), (1, 18, 1, 'QUALIFIE'), (1, 19, 1, 'QUALIFIE'), (1, 20, 1, 'QUALIFIE'),
(1, 21, 1, 'QUALIFIE'), (1, 22, 1, 'QUALIFIE'), (1, 23, 1, 'QUALIFIE'), (1, 24, 1, 'QUALIFIE'), (1, 25, 1, 'QUALIFIE');

-- Participations pour Épreuve 2 (ID 2)
-- On utilise les équipes (11 à 15)
INSERT INTO participation (competition_id_competition, participant_id_participant, epreuve_id, statut) VALUES
(2, 11, 2, 'QUALIFIE'), (2, 12, 2, 'QUALIFIE'), (2, 13, 2, 'QUALIFIE'), (2, 14, 2, 'QUALIFIE'), (2, 15, 2, 'QUALIFIE');

--INSERT INTO affectation_volontaire (volontaire_id_utilisateur, epreuve_id_epreuve, date_affectation, heure_debut, heure_fin, lieu_rdv_id_lieu, poste, commentaire) VALUES
--(14, 1, '2026-04-05', '08:00:00', '12:00:00', 1, 0, 'Accueil des participants'),
--(15, 1, '2026-04-05', '12:00:00', '16:00:00', 1, 1, 'Gestion ravitaillement');

-- ============================
-- Création des comptes (Authentification)
-- ============================

INSERT INTO compte (username, password, type, active, date_creation, id_utilisateur) VALUES
('admin', 'password', 'ADMIN', true, NOW(), 11),
('commissaire', 'password', 'COMMISSAIRE', true, NOW(), 12),
('visiteur', 'password', 'VISITEUR', true, NOW(), 13),
('sportif', 'password', 'SPORTIF', true, NOW(), 1),
('volontaire', 'password', 'VOLONTAIRE', true, NOW(), 14),
('superadmin', 'password', 'ADMIN', true, NOW(), 19);