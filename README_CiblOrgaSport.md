# Back CiblOrgaSport

Backend de gestion d'utilisateurs avec **authentification JWT** et
**gestion des rôles & permission avec historique et exception**.

------------------------------------------------------------------------

# Lancer l'application

## Lancer Docker

``` bash
docker compose up -d
```

##  Lancer l'application Spring Boot

``` bash
mvn spring-boot:run
```

------------------------------------------------------------------------

# API Utilisateurs -- Gestion des comptes

## Description

Cette API permet de gérer :

-   l'inscription des utilisateurs
-   l'authentification via **JWT**
-   la gestion des rôles
-   la gestion des permissions
-   la gestion des utilisateurs
-   la gestion des validations et exceptions
-   la validation des comptes par un administrateur

------------------------------------------------------------------------

# Rôles disponibles

Deux rôles sont créés au départ :

  Rôle             ID   Description
  ---------------- ---- -----------------
  **admin**        1    Administrateur
  **spectateur**   2    Rôle par défaut

Tout nouvel utilisateur reçoit automatiquement :

    role = spectateur
    state = 0

## États utilisateur

  State   Signification
  ------- --------------------------
  `0`     En attente de validation
  `10`    Compte activé

Un administrateur doit passer l'utilisateur à **state = 10** pour
autoriser la connexion.

------------------------------------------------------------------------

# Stack technique

  Technologie          Description
  -------------------- --------------------------------
  **Spring Boot 3+**   Backend Java
  **PostgreSQL**       Base de données
  **JWT**              Authentification
  **Swagger**          Documentation API
  **Docker**           Environnement de développement

------------------------------------------------------------------------

# Documentation API

## Accès Swagger

Interface interactive :

    http://localhost:8080/swagger-ui/index.html

------------------------------------------------------------------------

#  Endpoints principaux

##  Création des rôles

À exécuter **au démarrage du projet**

  Méthode   Endpoint
  --------- ---------------------
  POST      `/api/roles/create`

### Body

``` json
{
  "designation": "admin"
}
```

### Exemple pour spectateur

``` json
{
  "designation": "spectateur"
}
```

### Résultat attendu

``` json
{
  "id": 2,
  "designation": "spectateur"
}
```

------------------------------------------------------------------------

## Inscription (Signup)

Tout nouvel utilisateur reçoit automatiquement :

    role = spectateur (id = 2)

  Méthode   Endpoint
  --------- -----------------------
  POST      `/api/v1/auth/signup`

### Body

``` json
{
  "name": "maggy",
  "lastname": "ANDRIA",
  "login": "maggy",
  "mdp": "string",
  "verifyMdp": "string"
}
```

### Réponse attendue

``` json
{
  "login": "maggy",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

------------------------------------------------------------------------

## Validation par Administrateur

Par défaut :

    state = 0

L'administrateur doit changer :

    state = 10

  Méthode   Endpoint
  --------- -------------------------------
  PUT       `/api/utilisateurs/user/{id}`

### Body pour activation

``` json
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
```

------------------------------------------------------------------------

## Connexion (Signin)

Une fois `state = 10`, l'utilisateur peut se connecter.

  Méthode   Endpoint
  --------- -----------------------
  POST      `/api/v1/auth/signin`

### Body

``` json
{
  "login": "maggy",
  "mdp": "string"
}
```

### Réponse attendue

``` json
{
  "login": "maggy",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

