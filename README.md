# Product Manager — API REST Java / Spring Boot

[![CI](https://github.com/CvSi-DeV/product-manager-jv-back/actions/workflows/CI.yaml/badge.svg)](https://github.com/CvSi-DeV/product-manager-jv-back/actions/workflows/CI.yaml)

Déclinaison Java du projet fil rouge **Product Manager** (version TypeScript : [product-manager](https://github.com/CvSi-DeV/product-manager) / [product-manager-api](https://github.com/CvSi-DeV/product-manager-api)).

Projet personnel d'autoformation. Objectif : pratiquer une architecture Spring Boot en couches, la persistance JPA sur PostgreSQL et la conteneurisation de l'ensemble.

## Stack

| Domaine | Technologies |
|---|---|
| Langage | Java 21 |
| Framework | Spring Boot 3.4 (Web, Data JPA) |
| Base de données | PostgreSQL 18 |
| Build | Maven |
| Conteneurs | Docker (build multi-étapes) + Docker Compose (application + base, healthcheck) |
| Intégration continue | GitHub Actions (`mvn verify` sur JDK 21) |

## Architecture

```
src/main/java/nc/cvsi/productmanager
├── controller   → exposition REST (ProduitController)
├── service      → règles métier (stock, promotions, catégories)
├── repository   → accès aux données (Spring Data JPA)
├── model        → entités JPA (Produit, Categorie) et modèle utilisateurs
└── exception    → exceptions métier + gestion centralisée des erreurs
```

## API

Base : `http://localhost:8081/api/produits`

| Méthode | Route | Rôle | Réponses |
|---|---|---|---|
| GET | `/api/produits` | Liste des produits | 200 |
| GET | `/api/produits/{id}` | Détail d'un produit | 200 · 404 |
| GET | `/api/produits/categorie/{categorie}` | Produits d'une catégorie | 200 · 404 |
| GET | `/api/produits/promos` | Produits en promotion | 200 |
| GET | `/api/produits/valeur-totale` | Valeur totale du stock (prix × quantité) | 200 |
| POST | `/api/produits` | Création d'un produit | 201 |
| PATCH | `/api/produits/{id}/decrement/{quantite}` | Sortie de stock | 200 · 400 (stock insuffisant) · 404 |

Exemple de création :

```bash
curl -X POST http://localhost:8081/api/produits \
  -H "Content-Type: application/json" \
  -d '{"nom":"Clavier","prix":49.9,"stock":10,"categorie":{"nom":"Informatique"}}'
```

## Lancement

**Prérequis** : Docker et Docker Compose.

```bash
git clone https://github.com/CvSi-DeV/product-manager-jv-back.git
cd product-manager-jv-back
cp .env.example .env        # renseigner POSTGRES_USER, POSTGRES_PASSWORD, POSTGRES_DB
docker compose up --build
```

- API : `http://localhost:8081`
- PostgreSQL : port `5433` sur la machine hôte

## Choix techniques

- **Injection par constructeur** dans les contrôleurs et services : dépendances explicites et immuables, classes testables sans conteneur Spring.
- **Gestion centralisée des erreurs** (`@RestControllerAdvice`) : chaque exception métier est traduite en code HTTP (404 produit ou catégorie introuvable, 400 stock insuffisant), sans `try/catch` dans les contrôleurs.
- **Réutilisation d'une catégorie existante** à la création d'un produit (`Optional`) : évite de créer des doublons en base quand la catégorie existe déjà.
- **`equals` / `hashCode`** sur l'identifiant (Produit) et sur le nom (Catégorie), cohérents avec le cycle de vie des entités JPA.
- **Build Docker multi-étapes** : image d'exécution légère (JRE seul) ; démarrage de l'application conditionné à la disponibilité de PostgreSQL (healthcheck).

## Limites connues et suite

- **Tests automatisés** : pas encore en place. Prévus : tests unitaires du service (Mockito) et tests d'intégration du contrôleur, pour que la CI vérifie le comportement et pas seulement la compilation.
- **DTO** : les entités JPA sont exposées directement ; des objets de transfert dédiés sont prévus.
- **Validation des entrées** (`@Valid`) : à ajouter.
- **Schéma** : généré par Hibernate (`ddl-auto=update`) ; une gestion versionnée des migrations (Flyway ou Liquibase) est la cible.
