# Gestion de Comptes Bancaires - API GraphQL

Ce projet est une application Spring Boot implémentant une API GraphQL pour la gestion de comptes bancaires et de transactions.

## Fonctionnalités

- **Gestion des Comptes** :
  - Création de comptes (Courant ou Épargne).
  - Consultation de la liste des comptes.
  - Recherche d'un compte par ID.
  - Statistiques sur les soldes (total, moyenne).

- **Gestion des Transactions** :
  - Ajout de transactions (Dépôt ou Retrait).
  - Consultation des transactions d'un compte.
  - Statistiques globales sur les transactions.

- **Gestion des Erreurs** :
  - Messages d'erreur personnalisés pour les ressources non trouvées.

## Technologies Utilisées

- **Java 17**
- **Spring Boot 3+**
- **Spring GraphQL**
- **Spring Data JPA**
- **H2 Database** (Base de données en mémoire)
- **Lombok**

## Prérequis

- JDK 17 installé.
- Maven (ou utiliser le wrapper `mvnw` inclus).

## Installation et Démarrage

1.  Cloner le projet ou extraire l'archive.
2.  Ouvrir un terminal à la racine du projet.
3.  Lancer l'application avec Maven :

    ```bash
    ./mvnw spring-boot:run
    ```

    Ou sous Windows :

    ```cmd
    .\mvnw.cmd spring-boot:run
    ```

L'application démarrera sur le port `8082`.

## Utilisation

L'interface **GraphiQL** est activée pour tester l'API interactivement.

1.  Ouvrez votre navigateur et accédez à : [http://localhost:8082/graphiql](http://localhost:8082/graphiql)

### Exemples de Requêtes

#### 1. Lister tous les comptes
```graphql
query {
  allComptes {
    id
    solde
    type
    dateCreation
  }
}
```

#### 2. Créer un compte
```graphql
mutation {
  saveCompte(compte: {
    solde: 5000,
    dateCreation: "2024-11-30",
    type: COURANT
  }) {
    id
    solde
    type
  }
}
```

#### 3. Ajouter une transaction
```graphql
mutation {
  addTransaction(transaction: {
    compteId: 1,
    montant: 200,
    date: "2024-12-01",
    type: DEPOT
  }) {
    id
    montant
    type
    compte {
      id
      solde
    }
  }
}
```

#### 4. Voir les transactions d'un compte
```graphql
query {
  compteTransactions(id: 1) {
    id
    montant
    type
    date
  }
}
```

#### 5. Statistiques des transactions
```graphql
query {
  transactionStats {
    count
    sumDepots
    sumRetraits
  }
}
```
