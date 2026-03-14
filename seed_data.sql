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
-- Création des équipes
-- ============================

INSERT INTO equipe (nom_equipe) VALUES
('Les Foulées Rapides'), ('Sprint Nord'), ('Les Solitaires du Stade'), ('Run Power'), ('Les Gazelles'),
('Les Flèches Rouges'), ('Ultra Solo'), ('Les Coureurs Libres'), ('Solo Performance'), ('Nord Running'),
('Speed Force'), ('Les Endurants'), ('Solo Elite'), ('Run Storm'), ('Les Turbos'),
('Les Rapides'), ('Solo Horizon'), ('Les Sprinteurs'), ('Endurance Max'), ('Solo Victory'),
('Duo Dynamique'), ('Les Binômes Rapides'), ('Double Impact'), ('Les Frères de Piste'), ('Duo Nordique'),
('Les Inséparables'), ('Double Endurance'), ('Les Fusées'), ('Duo Sprint'), ('Les Compagnons de Course'),
('Twin Speed'), ('Les Coureurs Unis'), ('Double Energie'), ('Duo Horizon'), ('Les Alliés du Run');

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
-- Liaisons équipes ↔ sportifs
-- ============================

-- Solo (1-20)
INSERT INTO equipe_sportif VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),
(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20);

-- Duo (21-35)
INSERT INTO equipe_sportif VALUES (21,1),(21,2),(22,3),(22,4),(23,5),(23,6),(24,7),(24,8),(25,9),(25,10),
(26,11),(26,12),(27,13),(27,14),(28,15),(28,16),(29,17),(29,18),(30,19),(30,20),(31,1),(31,3),
(32,5),(32,7),(33,9),(33,11),(34,13),(34,15),(35,17),(35,19);