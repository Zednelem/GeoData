# Notes de travail

## Squelette de Démarrage

J'ai choisi de démarrer avec Spring Boot initializer pour inclure la dépendance web MVC uniquement

## Organisation du projet


Il s'agit d'une application spring.

Cette application possède un unique controlleur exposant l'API Density

route en local: localhost:8080/api/public/density/{count}/max

avec un seul controlleur la liste des zones avec la plus forte densité

Il y a deux services qui pourraient se décomposer en microService

## Definition d'une zone

Étant donné le quadrillage de 0.5, un POI est dans une zone et une seule

Faire tourner les tests OK
