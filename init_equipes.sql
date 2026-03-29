--- docker exec -i back-ciblorgasport-postgres-1 psql -U admin -d glop < init_equipes.sql
INSERT INTO equipe (nom_equipe, competition_id_competition)
VALUES 
('PSG', 1),
('OM', 1),
('Lyon', 1),
('Real Madrid', 2),
('Barcelone', 2);