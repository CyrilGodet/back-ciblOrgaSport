INSERT INTO utilisateur (nom, prenom, email, mdp, state, idRoles)
VALUES ('Mina', 'Solo', 'admin@a.com', 'string', 10, 1)
    ON CONFLICT (email) DO NOTHING;

INSERT INTO utilisateur (nom, prenom, email, mdp, state, idRoles)
VALUES ('Tiana', 'Rabe', 'commi@c.com', 'string', 10, 2)
    ON CONFLICT (email) DO NOTHING;