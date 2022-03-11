# Projet Rent Manager

## Lancer le projet
Utiliser la commande `mvn tomcat7:run` puis ouvrir le site dans un navigateur internet avec l'adresse suivante :
http://localhost:8080/rentmanager

## Fonctionnalités réussies
### Affichage
* Dashboard / Home avec les 3 counts
* Opérations CRUD pour les clients
* Opérations CRUD pour les véhicules
* Opérations CRD pour les réservations
* Affichage des détails pour les clients et les véhicules (liste de toutes les réservations selon un client ou un véhicule spécifique)

### Contraintes / Vérifications
* Validation du formulaire de création, en respectant la syntaxe de l'email, des dates, âge du client devant être majeur (>18ans).
* Nombre de places dans un véhicule restreint entre minimum 2 et 9 maximum.
* Lorsqu'un client ou un véhicule est supprimé, les réservations relatives le sont aussi.
* Un client n'ayant pas 18 ans ne peut pas être créé
* Le nom et le prénom d'un client doivent faire au moins 3 caractères
* Un client ayant une adresse mail déjà prise ne peut pas être créé
* Une voiture ne peux pas être réservé plus de 7 jours de suite par le même utilisateur

## Fonctionnalités non réussies
* Une voiture ne peux pas être réservé 2 fois le même jour
* Une voiture ne peux pas être réservé 30 jours de suite sans pause

## Que du bonus !
* Dashboard cliquable
* Message d'erreurs dans les formulaires (type Mail existant, avoir plus de 18 ans et ne pas faire une réservation de plus de 7 jours)
