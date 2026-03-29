# Back CiblOrgaSport

## Comment lancer l'application ?

### Lancement de Docker

`docker compose up -d`

*Remarque*: Si c'est la première fois que vous lancez l'application ou que vous avez modifié le code, il ne faut pas oublier de build l'application Spring au moment du démarrage du conteneur Docker. Pour cela, il faut plutôt lancer la commande : `docker compose up --build -d`

### Arrêt de Docker

`docker compose down`

### Accès à la base de données 

`docker exec -it back-ciblorgasport-postgres-1 psql -U admin -d glop`

### Exécuter le scipt SQL pour injecter des données dans la base de données

`cat seed_data.sql |  docker compose exec -T postgres psql -U admin -d glop`


## Monitoring

### Accès à Grafana

Ouvrir un navigateur web et aller à l'adresse : http://localhost:3000
Les identifiants par défaut sont :
- Utilisateur : admin
- Mot de passe : admin

#### Créer un data source pour Prometheus
1. Cliquer sur l'icône d'engrenage (Configuration) dans le menu de gauche.
2. Sélectionner "Data Sources".
3. Cliquer sur "Add data source".
4. Choisir "Prometheus" dans la liste des types de data source.
5. Dans le champ "URL", entrer : http://prometheus:9090
6. Cliquer sur "Save & Test" pour vérifier la connexion.


#### Créer un dashboard pour visualiser les métriques
1. Dans le menu de gauche, cliquer sur "Dashboard".
2. Cliquer sur "New". et "import"
3. Mettre l'id pour avoir les metrics de springboot : 12900 et cliquer sur "Load"
4. Sélectionner la data source Prometheus créée précédemment.
5. Cliquer sur "Import" pour ajouter le dashboard.

*Remarque*: La création du data source et de la configuration du dashboad n'est qu'a réaliser qu'une seule fois parce que par la suite tout est stocké dans un volume Docker.

## Déploiement sur Render

Une version de l'application a été déployée sur Render

Elle est accessible via ce [lien](https://front-ciblorgasport-wdcm.onrender.com)

Pour vous connecter vous pouvez utiliser ces comptes:

Admin : 
* login: admin , password: password
  
Commissaire :
* login: commissaire , password: password

Visiteur :
* login: visiteur , password: password   

