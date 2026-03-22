INSERT INTO roles (designation)
SELECT 'admin'
    WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE designation = 'admin'
);

INSERT INTO roles (designation)
SELECT 'commissaire'
    WHERE NOT EXISTS (
    SELECT 1 FROM roles WHERE designation = 'commissaire'
);