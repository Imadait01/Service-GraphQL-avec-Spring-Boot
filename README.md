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

<img width="1855" height="917" alt="Screenshot 2025-11-30 174327" src="https://github.com/user-attachments/assets/4e5a2433-c925-4fbb-8dee-6593a1cce4ef" />

<img width="1868" height="939" alt="Screenshot 2025-11-30 174541" src="https://github.com/user-attachments/assets/3b48d7e5-692d-4444-9746-dd9f55ec250c" />

<img width="1855" height="935" alt="Screenshot 2025-11-30 174605" src="https://github.com/user-attachments/assets/534ef85f-0b0a-4bbc-8f3d-14404cad47ec" />

<img width="1861" height="920" alt="Screenshot 2025-11-30 174650" src="https://github.com/user-attachments/assets/49e82d80-746a-427c-b471-8e4cc69de4b4" />

<img width="1860" height="929" alt="Screenshot 2025-11-30 174754" src="https://github.com/user-attachments/assets/34421d44-bb32-45f7-b51c-3a26fbb6ca25" />

<img width="1862" height="937" alt="Screenshot 2025-11-30 175813" src="https://github.com/user-attachments/assets/5ad11b6e-feaf-4964-b3f2-31fd1e6d594a" />

<img width="1869" height="934" alt="Screenshot 2025-11-30 175901" src="https://github.com/user-attachments/assets/e4e911a5-8727-44f2-9711-e98593eb3a82" />

<img width="1872" height="942" alt="Screenshot 2025-11-30 180912" src="https://github.com/user-attachments/assets/00be2fc0-a5c4-46eb-9bca-74f3954d71a3" />

<img width="1855" height="930" alt="Screenshot 2025-11-30 180935" src="https://github.com/user-attachments/assets/03703c59-d148-4bb6-8eae-a3eff9015c69" />

<img width="1857" height="912" alt="Screenshot 2025-11-30 180953" src="https://github.com/user-attachments/assets/fe84b555-11ec-4b4b-869d-7b6ab35cc59e" />
