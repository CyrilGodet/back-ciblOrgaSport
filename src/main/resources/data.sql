-- Création des rôles (si pas déjà présents)
INSERT INTO roles (designation)
SELECT 'admin'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE designation = 'admin');

INSERT INTO roles (designation)
SELECT 'volontaire'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE designation = 'volontaire');


INSERT INTO roles (designation)
SELECT 'spectateur'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE designation = 'spectateur');

INSERT INTO roles (designation)
SELECT 'sportif'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE designation = 'sportif');

/*-- Création et activation des utilisateurs (state = 10 = approuvé)
INSERT INTO utilisateur (nom, prenom, email, mdp, state, idRoles)
VALUES ('Mina', 'Solo', 'admin@a.com', 'string', 10, 1)
    ON CONFLICT (email) DO NOTHING;

INSERT INTO utilisateur (nom, prenom, email, mdp, state, idRoles)
VALUES ('Tiana', 'Rabe', 'commi@c.com', 'string', 10, 2)
    ON CONFLICT (email) DO NOTHING;*/