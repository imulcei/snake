# 🐍 Snake – Jeu en Java

Un petit jeu **Snake** développé en Java avec Swing.
Contrôlez le serpent, mangez les pommes et évitez de vous mordre !

## ✅ Fonctionnalités

- 🎮 Contrôles simples au clavier (flèches directionnelles)
- 🍏 Apparition aléatoire des pommes
- 💥 Collision avec soi-même = Game Over
- 🖼️ Interface graphique en Java Swing
- 📦 Version portable en `.jar` (fonctionne sur Windows, macOS, Linux)

## ▶️ Lancer le jeu

### Avec Java installé :

```bash```
```java -jar snake.jar```

## 🎮 Contrôles

| Touche | Action         |
| ------ | -------------- |
| ↑      | Aller en haut  |
| ↓      | Aller en bas   |
| ←      | Aller à gauche |
| →      | Aller à droite |

## 🛠️ Prérequis

- Java 17 ou plus (JRE ou JDK)
- Aucun framework externe requis

## Structure du projet

```text
.
├── src/
│   └── jv/
│       ├── App.java
│       └── model/
│           └── Game.java
│           └── Gameboard.java
│           └── Apple.java
│           └── Snake.java
├── resources/
│   ├── PressStart2P-Regular.ttf
│   └── snake.png
├── target/
│   └── snake.jar
└── README.md
```

## 📜 Licence

Ce projet est libre d’utilisation pour des fins pédagogiques ou personnelles.
Modifications et contributions bienvenues !