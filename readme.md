# Back CiblOrgaSport

## Comment lancer l'application ?

### Lancement de Docker

docker compose up -d

### Arrêt de Docker

docker compose down

### Accès à la base de données

docker exec -it back-ciblorgasport-postgres-1 psql -U admin -d glop


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



sudo docker exec -it postgresql_database psql -U admin -d glop
cat seed_data.sql | sudo docker exec -i postgresql_database psql -U admin -d glop


sudo docker exec -it postgresql_database psql -U admin -d glop
cat seed_data.sql | sudo docker exec -i postgresql_database psql -U admin -d glop


cat seed_data.sql |  docker exec -i postgresql_database psql -U admin -d glop



FAC:
docker compose up -d postgres
mvn clean install run 
cat seed_data.sql |  docker exec -i back-ciblorgasport-postgres-1   psql -U admin -d glop



 cat seed_data.sql | docker exec -i back-ciblorgasport-postgres-1 psql -U admin -d glop
