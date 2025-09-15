# 1 Zielbestimmungen

Ziel des Projektes ist es, ein voll funktionsfähiges, grafisch ansprechendes Schachspiel in Java zu entwickeln,
das sowohl gegen einen menschlichen Mitspieler als auch gegen eine KI gespielt werden kann.
Die Anwendung soll die offiziellen Schachregeln vollständig umsetzen und eine intuitive Benutzeroberfläche bieten.

## 1.1 Muss-Kriterium

- /M1/ Das Schachbrett wird als 8x8-Feld dargestellt und initial korrekt befüllt.
- /M2/ Alle Schachfiguren (König, Dame, Turm, Läufer, Springer, Bauer) sind implementiert
  und verhalten sich regelkonform.
- /M3/ Es können Züge gemäß den offiziellen Schachregeln durchgeführt werden.
- /M4/ Sonderregeln wie Rochade, En Passant und Bauernumwandlung sind vollständig umgesetzt.
- /M5/ Der Spielstatus (Schach, Schachmatt, Patt) wird korrekt erkannt und angezeigt.
- /M6/ Das Spiel kann gespeichert und später wieder geladen werden.
- /M7/ Es existiert ein lokaler Zwei-Spieler-Modus.
- /M8/ Eine einfache KI ist integriert.
- /M9/ Es gibt eine Rückgängig-Funktion für den letzten Zug. (nur bei Spiel gegen KI)
- /M10/ Die Benutzeroberfläche ist vollständig mit JavaFX umgesetzt.

## 1.2 Wunschkriterien

- /W1/ Die KI kann in mehreren Schwierigkeitsstufen konfiguriert werden.
- /W2/ Es gibt eine Anzeige der möglichen Züge beim Klick auf eine Figur.
- /W3/ Die Figuren können per Drag-and-Drop bewegt werden.
- /W4/ Es existiert ein Online-Multiplayer-Modus über Netzwerkverbindung.
- /W5/ Die Spielzüge werden in algebraischer Notation angezeigt und gespeichert (für spätere Wiedergabe).
- /W6/ Es gibt eine visuelle Anzeige für Schach, Schachmatt und Patt.
- /W7/ Ein Spiel-Timer für beide Spieler ist integriert.
- /W8/ Die Benutzeroberfläche ist anpassbar (z.B. Farbthemen, Figurenstil).
- /W9/ Es gibt eine 3D-Darstellung des Schachbretts.

## 1.3 Abgrenzungskriterien

- /A1/ Varianten wie Chess960 oder andere Schachvarianten werden nicht unterstützt.
- /A2/ Keine Unterstützung für mobile Endgeräte (nur Desktop).
- /A3/ Kein Sprachinterface oder Sprachausgabe.

---

# 2 Produkteinsatz

## 2.1 Anwendungsbereiche

Das Produkt wird auf Desktop-Computern eingesetzt und dient der Unterhaltung, dem Training
und der Analyse von Schachpartien.

## 2.2 Zielgruppen

Das Spiel richtet sich an Freizeitspieler, Schüler, Studenten, und Schachinteressierte im Allgemeinen.

## 2.3 Betriebsbedingungen

Das Programm wird auf einem handelsüblichen PC mit installiertem Java Runtime Environment ausgeführt.
Es wird manuell gestartet und beendet.

---

# 3 Produktfunktionen

## 3.1 Spielstart und -ende

- /F10/ Der Benutzer kann das Spiel über eine Schaltfläche starten.
- /F20/ Der Benutzer kann das Spiel über eine Schaltfläche beenden.
- /F30/ Der Benutzer kann eine neue Partie starten.
- /F40/ Der Benutzer kann eine gespeicherte Partie laden. (/M7/)
- /F50/ Der Benutzer kann die aktuelle Partie speichern. (/M7/)

## 3.2 Spielsteuerung

- /F60/ Der Benutzer kann eine Figur auswählen und gemäß den Regeln bewegen. (/M3/)
- /F70/ Der Benutzer kann den letzten Zug rückgängig machen. (/M9/)
- /F80/ Der Benutzer kann zwischen Mensch vs. Mensch und Mensch vs. KI wählen. (/M8/, /M9/)
- /F90/ Der Benutzer kann die KI-Schwierigkeit einstellen. (/W1/)
- /F100/ Der Benutzer kann die Anzeige der möglichen Züge aktivieren/deaktivieren. (/W2/)
- /F110/ Der Benutzer kann die Figuren per Drag-and-Drop bewegen. (/W3/)
- /F120/ Der Benutzer kann die Spielzeit pro Spieler einstellen. (/W7/)

## 3.3 Spielregeln und Logik

- /F130/ Das Spiel erkennt Schach, Schachmatt und Patt automatisch. (/M5/)
- /F140/ Rochade, En Passant und Bauernumwandlung sind implementiert. (/M4/)
- /F150/ Die Züge werden in algebraischer Notation angezeigt. (/W5/)
- /F160/ Die Spielzüge werden in einer Liste gespeichert und angezeigt. (/W5/)

---

# 4 Produktdaten

Folgende Daten werden persistent gespeichert:

- /D10/ Spielstand (Positionen, Spieler am Zug, Uhrzeit). (/M7/)
- /D20/ Liste der bisherigen Züge in algebraischer Notation. (/W5/)
- /D30/ KI-Schwierigkeitsgrad und Spielmodus. (/W1/, /M8/)

---

# 5 Produktleistungen

- /L10/ Die Reaktionszeit bei Benutzerinteraktionen beträgt maximal 100 ms.
- /L20/ Die KI berechnet ihren Zug in unter 5 Sekunden bei mittlerer Schwierigkeit.
- /L30/ Die Benutzeroberfläche ist intuitiv und barrierefrei bedienbar.
- /L40/ Das Spiel läuft stabil ohne Abstürze oder Regelverletzungen.

---

# 6 Benutzeroberfläche

Die Benutzeroberfläche besteht aus:

- einem zentralen Schachbrett
- einer Seitenleiste mit Spielinformationen (Züge, Status, Timer)
- Buttons für "Neu", "Speichern", "Laden", "Rückgängig", "Beenden"
- Einstellungen für KI, Anzeigeoptionen und Farbthemen

---

# 7 Technische Produktumgebung

## 7.1 Hardware

Das Spiel benötigt einen PC mit mindestens:

- Dual-Core-Prozessor
- 4 GB RAM
- Bildschirmauflösung 1280×720
- Tastatur und Maus

## 7.2 Software

- Java 21 oder höher
- JavaFX-Bibliothek
- Betriebssystem: Windows, macOS oder Linux

---

# 8 Qualitätsanforderungen

| Produktqualität    | sehr gut | gut | normal | irrelevant |
|--------------------|----------|-----|--------|------------|
| Funktionalität     | x        |     |        |            |
| Zuverlässigkeit    | x        |     |        |            |
| Benutzbarkeit      | x        |     |        |            |
| Änderbarkeit       |          | x   |        |            |
| Effizienz          | x        |     |        |            |
| Portierbarkeit     | x        |     |        |            |

---

# 9 Globale Testszenarien und Testfälle

- /T10/ Das Spiel wird gestartet und zeigt das initiale Schachbrett. (/F10/)
- /T20/ Der Benutzer wählt eine Figur und sieht die möglichen Züge. (/F60/, /W2/)
- /T30/ Der Benutzer führt einen gültigen Zug aus. (/F60/)
- /T40/ Der Benutzer führt eine Rochade durch. (/F140/)
- /T50/ Der Benutzer setzt einen Bauern um. (/F140/)
- /T60/ Die KI führt einen Zug aus. (/F80/, /M9/)
- /T70/ Der Benutzer speichert das Spiel und lädt es erneut. (/F40/, /F50/)
- /T80/ Das Spiel erkennt Schachmatt korrekt. (/F130/)
- /T90/ Der Benutzer macht einen Zug rückgängig. (/F70/)
- /T100/ Die Benutzeroberfläche reagiert innerhalb von 100 ms. (/L10/)

---

# 10 Glossar

- **Rochade**  
  Sonderzug, bei dem König und Turm gleichzeitig bewegt werden - 3.3
- **En Passant**  
  Sonderregel für Bauern, die einen gegnerischen Bauern "im Vorbeigehen" schlagen - 3.3
- **Bauernumwandlung**  
  Ein Bauer, der die gegnerische Grundlinie erreicht, wird in eine andere Figur umgewandelt - 3.3
