# stargazingapi

Cette API permet de consulter les informations d'un observatoire astronomique. On peut créer un profil d'astronome avec différents rôles, se connecter, consulter et gérer les ressources: instruments d'observation, objets célestes, évènements célestes, observations et sessions d'observation.


Le projet est développé sous Java 17 et Springboot 3. La documentation est gérée via Swagger.


## Les endpoints

### Auth
Endpoints permettant la création de compte et la connection
- POST /auth/register : crée un utilisateur
- POST /auth/login : se connecter avec un username et un password valides. Retourne un bearer token

## Astronomes
Endpoints permettant la gestion RUD des users/astronomes (le C est géré dans la partie Auth)
- GET /astronomers/{id} récupère un astronome via son ID
- GET /astronomers récupère tous les astronomes paginés
- PUT /astronomers/{id} modifie un astronome
- DELETE /astronomers/{id} supprime un astronome via son ID


### Stargazing session
Endpoints permettant la gestion CRUD des sessions d'observations
- POST /stargazingSessions crée une session d'observation
- GET /stargazingSessions/{id} récupère une session d'observation via son ID
- GET /stargazingSessions récupère toutes les session d'observations paginées
- PUT /stargazingSessions/{id} Modifie une session d'observation
- PUT /stargazingSessions/{sessionId}/participants/{astronomerId} ajoute un participant à une session avec leurs ID
- DELETE /stargazingSessions/{id} supprime une session avec son ID


### Observation
Endpoints permettant la gestion CRUD des observations d'objets célestes
- POST /observations crée une observation
- GET /observations/{id} récupère une observation via son ID
- GET /observations récupère toutes les observations paginées
- PUT /observations/{id} met à jour une observation
- DELETE /observations/{id} supprime une observation avec son ID


### Instrument
Endpoints permettant la gestion CRUD des instruments d'observation (ex: téléscopes)
- POST /instruments crée un instrument
- GET /instruments/{id} récupère un instrument via son ID
- GET /instruments récupère tous les instruments paginés
- PUT /instruments/{id} met à jour un instrument
- DELETE /instruments/{id} supprime un instrument avec son ID


### Celestial events
Endpoints permettant la gestion CRUD des évènements célestes (ex: éclipes, planète en phase gibbeuse)
- POST /celestialEvents crée un évènement céleste
- GET /celestialEvents/{id} récupère un évènement céleste via son ID
- GET /celestialEvents récupère tous les évènements célestes paginés
- PUT /celestialEvents/{id} met à jour un évènement céleste
- DELETE /celestialEvents/{id} supprime un évènement céleste avec son ID


### Celestial bodies
Endpoints permettant la gestion CRUD des objets célestes (ex: planètes, satellites naturels, étoiles)
- POST /celestialBodies crée un objets céleste
- GET /celestialBodies/{id} récupère un objets céleste via son ID
- GET /celestialBodies récupère tous les objets célestes paginés
- PUT /celestialBodies/{id} met à jour un objets céleste
- DELETE /celestialBodies/{id} supprime un objets céleste avec son ID


### AI
- POST /ai/stella : discution avec le chatbot que j'ai appelé Stella pour rester dans le thème :)
- POST /ai/stargazing-suggestions : suggère des observations à faire en fonction du pays, de la ville, et du mois de l'année.

Je comptais mettre en fonction de la date exacte, mais en raison du décalage des données de l'IA c'était compliqué. Dans l'idée, une IA branchée sur une BDD en temps réel pourrait faire des suggestions en temps réel pour des dates futures