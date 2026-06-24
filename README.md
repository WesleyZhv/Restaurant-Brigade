# KitchenFlow — Simulateur de Brigade de Restaurant

Application Java orientée objet simulant la gestion d'une brigade de cuisine et d'un service de restaurant, avec interface graphique JavaFX.

Projet personnel réalisé dans le cadre d'une reconversion professionnelle (cuisine → informatique), en parallèle d'un Bachelor en Informatique de Gestion à la HES-SO Neuchâtel.

---

## Technologies

- Java 24
- Maven
- JavaFX 21
- Gson 2.10.1

---

## Concepts Java démontrés

- **Programmation orientée objet complète** — héritage, polymorphisme, classes abstraites, interfaces
- **Généricité** — classe `Brigade<T extends Personnel>` typée à la compilation
- **Exceptions métier custom** — `ServiceSurchargeException`, `PlatIndisponibleException`, `PersonnelAbsentException`
- **Collections** — `ArrayList`, `Queue` (FIFO) via `LinkedList`, `ObservableList` JavaFX
- **Enum** — `StatutPlat` (EN_ATTENTE, EN_COURS, PRET, ECHEC)
- **Persistance JSON** — sérialisation/désérialisation avec Gson, gestion du polymorphisme via `RuntimeTypeAdapterFactory`
- **Interface graphique JavaFX** — `TableView`, `ListView`, `BorderPane`, `VBox`, `HBox`, event handlers via lambdas
- **Gestion de projet** — Maven, Git avec historique de commits progressif

---

## Architecture

src/main/java/

├── restaurant/

│   ├── exception/        # Exceptions métier custom

│   ├── main/             # Point d'entrée console (Main) + interface JavaFX (App)

│   ├── model/

│   │   ├── Brigade.java  # Classe générique Brigade<T extends Personnel>

│   │   ├── personnel/    # Hiérarchie Personnel → Cuisinier/Manager → sous-classes

│   │   ├── plat/         # Plat + enum StatutPlat

│   │   └── service/      # Service avec file de commandes FIFO

│   └── storage/          # Persistance JSON via Gson

└── com/google/gson/typeadapters/  # RuntimeTypeAdapterFactory (source officielle Google)

---

## Lancer le projet

**Prérequis :** Java 21+, Maven

```bash
git clone https://github.com/WesleyZhv/Restaurant-Brigade.git
cd restaurantMaven
mvn javafx:run
```

---

## Fonctionnalités de l'interface

- Tableau de bord avec brigade complète (Nom, Poste, Rôle hiérarchique)
- Formulaire d'ajout de cuisinier avec validation (TextField, ComboBox, Alert)
- Suppression d'un membre sélectionné avec confirmation
- Liste des plats en cours de service avec compteur en temps réel
- Ajout de commandes et traitement FIFO via boutons
- Fenêtre détails au double-clic sur un cuisinier (nouvelle Stage)
- Rapport de fin de service (plats traités, membres de la brigade)
---

*Projet en cours de développement — JavaFX Phase 3 active.*