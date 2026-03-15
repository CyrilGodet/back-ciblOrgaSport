# Back CiblOrgaSport

## Comment lancer l'application ?

### Lancement de Docker

docker compose up -d

### Lancer l’application

mvn spring-boot:run

# API Utilisateurs – Gestion d’authentification et de rôles

## Description

Cette API permet de gérer des utilisateurs avec un système d’authentification et des rôles prédéfinis.

Deux rôles sont créés au départ :

-   **admin**
-   **spectateur** (id = 2) 👉 attribué automatiquement par défaut à chaque nouvel utilisateur.

L’utilisateur nouvellement créé est mis dans un **state = 0**, c’est-à-dire en attente de validation par un administrateur.

L’administrateur peut ensuite modifier le state à **10** pour activer le compte.

## Stack technique

-   **Backend:** Spring Boot 3+
-   **Base de données:** PostgreSQL
-   **Authentification:** JWT (JSON Web Token)
-   **Port par défaut:** `8080`

# Documentation de l’API

## Accès à l’interface Swagger

L’API est interactive et documentée via Swagger UI :

[http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)

## Endpoints principaux

### Création des rôles

> **Note :** À exécuter au démarrage pour initialiser le système.

**URL** : `/api/roles/create`

**Méthode** : `POST`

**Corps de la requête (Exemple pour Admin) :**

JSON



{
"designation": "admin"
}

### Inscription (Signup)

Tout nouvel utilisateur reçoit l'ID de rôle **2** ("spectateur") par défaut.

**URL** : `/api/v1/auth/signup`

**Méthode** : `POST`

**Corps de la requête (Body minimal) :**

JSON



{
"name": "maggy",
"lastname": "ANDRIA",
"login": "maggy",
"mdp": "string",
"verifyMdp": "string"
}

**Réponse attendue (201 Created) :**

JSON



{
"login": "maggy",
"token": "eyJhbGciOiJIUzI1NiJ9..."
}

### Validation par l'Administrateur

Par défaut, le compte est créé avec `state: 0`. L'administrateur doit passer ce statut à **10** pour permettre la connexion.

**URL** : `/api/utilisateurs/user/{id}`

**Méthode** : `PUT`

**Corps de la requête pour activation :**

JSON



{
"id": 1,
"name": "ANDRIA",
"lastname": "maggy",
"login": "maggy",
"roles": {
"id": 2,
"designation": "spectateur"
},
"state": 10
}

### Connexion (Signin)

Une fois le `state` à **10**, l'utilisateur peut s'authentifier pour obtenir un jeton d'accès.

**URL** : `/api/v1/auth/signin`

**Méthode** : `POST`

**Corps de la requête :**

JSON



{
"login": "maggy",
"mdp": "string"
}

**Réponse attendue :**

JSON



{
"login": "maggy",
"token": "eyJhbGciOiJIUzI1NiJ9..."
}

## Test rapide avec Postman

Un fichier de collection nommé `postman_collection.json` est disponible à la racine du projet.

**Pour l’utiliser :**

1.  Ouvrir **Postman**.
2.  **Importer** le fichier `.json`.
3.  Utiliser le **Runner** de collection pour exécuter les requêtes dans cet ordre précis :

-   `Create Role Admin`
-   `Create Role Spectateur`
-   `Signup User`
-   `Validate User (PUT)`
-   `Signin User`