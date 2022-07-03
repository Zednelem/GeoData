# Notes de travail

## Squelette de Démarrage

J'ai choisi de démarrer avec Spring Boot initializer pour inclure la dépendance web MVC uniquement

## Description rapide


Il s'agit d'une application spring.

Cette application possède un unique controlleur exposant l'API Density

route en local: localhost:8080/api/public/zones?size=4

Il y a deux services qui pourraient se décomposer en microService

## Organisation du projet

1. un package controllers pour l'api exposée
2. Un package dto contenant le DTO de retour du controlleur   
3. un package service contenant 2 services
    * Un service reccupération de PointsOfInterest POI ( l'implémenttion va chercher dans les ressources de l'application src/main/resources)
    * Un service d'opération sur les POI:
        * getDensity :
            renvoie la densité d'une zone passée en paramètre
        * getMostDenseZonesWithSize: renvoie les zones les plus denses avec une taille maximum de liste renvoyé
4. Un package model contenant la logique logique métier

### Couverture des tests = 88%

