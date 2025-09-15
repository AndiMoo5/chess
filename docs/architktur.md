# Architekturübersicht - Java-Schachspiel

## 1. Ziel der Architektur

Die Architektur des Java-Schachspiels verfolgt das Ziel, eine modulare, wartbare
und erweiterbare Softwarestruktur zu schaffen. Sie soll die Trennung von Benutzeroberfläche, Spiellogik
und Datenmodell ermöglichen und eine klare Verantwortlichkeit der Komponenten sicherstellen.
Die Anwendung basiert auf dem MVC-Architekturmuster (Model-View-Controller)
und verwendet JavaFX für die grafische Darstellung.

---

## 2. Architekturstil

- **MVC (Model-View-Controller)**  
  Die Anwendung ist in drei Hauptkomponenten unterteilt:
    - **Model**: Repräsentiert die Spiellogik und Datenstrukturen (z.B. Figuren, Brett, Züge)
    - **View**: Präsentiert die Benutzeroberfläche mit JavaFX
    - **Controller**: Vermittelt zwischen Benutzerinteraktionen und Spiellogik

- **Schichtenarchitektur**  
  Die Software ist zusätzlich in logische Schichten gegliedert:
    - Präsentationsschicht (GUI)
    - Anwendungsschicht (Controller, KI)
    - Datenmodell (Board, Pieces, Moves)

---

## 3. Modulübersicht

```plaintext
src/
├── model/        → Spiellogik, Figuren, Brett, Züge
├── controller/   → Spielsteuerung, KI, Regeln
├── view/         → JavaFX-GUI-Komponenten
├── util/         → Hilfsklassen (Logger, Parser, etc.)
└── test/         → Unit- und Integrationstests
```

---

# 4. Datenfluss

- Der Benutzer interagiert mit der GUI (**View**).
- Die View ruft Methoden im **Controller** auf.
- Der Controller verarbeitet die Eingabe und aktualisiert das **Model**.
- Änderungen im Model werden zurück an die View übermittelt.
- Die View aktualisiert die Darstellung entsprechend.

---

# 5. Wichtige Klassen und ihre Rollen

| Klasse                | Aufgabe                                                 |
|-----------------------|---------------------------------------------------------|
| `Board`               | Repräsentiert das Schachbrett und seine Felder          |
| `Piece`               | Abstrakte Klasse für alle Schachfiguren                 |
| `Pawn`, `Rook`, etc.  | Spezialisierungen von `Piece`                           |
| `Move`                | Repräsentiert einen einzelnen Zug                       |
| `GameController`      | Steuert den Spielablauf und die Regeln                  |
| `AIPlayer`            | Berechnet KI-Züge mittels Minimax                       |
| `MainApp`             | Startet die JavaFX-Anwendung                            |
| `GameState`           | Speichert den aktuellen Zustand des Spiels              |

---

# 6. Designentscheidungen

- **JavaFX** wurde gewählt für eine moderne, plattformunabhängige GUI.
- Die **KI** basiert auf dem Minimax-Algorithmus mit Alpha-Beta-Pruning.
- Die Architektur erlaubt einfache Erweiterungen, z. B. Online-Multiplayer oder alternative KI-Strategien.
- **Maven** wird zur Projektverwaltung und für das Dependency Management eingesetzt.

---

# 7. Erweiterbarkeit

- Neue Figuren oder Spielvarianten können durch Erweiterung der `Piece`-Klasse integriert werden.
- Zusätzliche GUI-Themes können über CSS in JavaFX eingebunden werden.
- Netzwerkfunktionen lassen sich über zusätzliche Controller-Module ergänzen.
- Die KI kann durch Austausch der `AIPlayer`-Implementierung verbessert oder ersetzt werden.

---

# 8. Schnittstellen

- **Benutzerschnittstelle**: JavaFX-GUI mit Buttons, Drag-and-Drop und Statusanzeigen
- **Dateischnittstelle**: Laden und Speichern von Partien im JSON- oder XML-Format
- **Optionale Netzwerkschnittstelle**: Socket-basierte Kommunikation für Multiplayer-Modus

---

# 9. Risiken und Herausforderungen

- Komplexität der KI-Logik bei höheren Schwierigkeitsstufen
- Synchronisation von GUI und Spiellogik bei schnellen Interaktionen
- Erweiterung um Netzwerkfunktionen erfordert zusätzliche Sicherheitsmaßnahmen

---

# 10. Fazit

Die gewählte Architektur bietet eine solide Grundlage für ein robustes, benutzerfreundliches
und erweiterbares Schachspiel in Java. Durch die klare Trennung der Komponenten und die Verwendung etablierter Muster
wie MVC ist eine langfristige Wartbarkeit und Weiterentwicklung gewährleistet.
