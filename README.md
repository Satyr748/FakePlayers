# FakePlayers — Fabric 1.21.11 (Quellcode-Prototyp)

## Ehrlicher Funktionsumfang dieser ZIP

Dieses Projekt ist ein **kompilierbares Startgerüst/Prototyp**, kein fertiger Fake-Spieler-Mod:
- Fabric-Projekt für Minecraft 1.21.11 und Java 21
- Admin-Befehle `/fakeplayers spawn <1..2000>`, `/fakeplayers count` und `/fakeplayers remove`
- Zufällige Namen und UUIDs werden als reine Daten gespeichert
- Es werden keine echten Verbindungen und keine Minecraft-Entities erzeugt

**Wichtig:** Diese Version zeigt noch KEINE sichtbaren Spielerfiguren. Dafür fehlen noch die Netzwerk-Payloads zum Verteilen der Daten und die clientseitige Player-Model-Render-Implementierung. Ich kennzeichne das bewusst, statt eine nicht getestete Funktion als fertig auszugeben.

## Bauen

Voraussetzung: Java 21 (JDK) und eine installierte Gradle-Version 8.14 oder neuer.

Im Projektordner:

Windows:
```powershell
gradle build
```

Linux/macOS:
```bash
gradle build
```

Die JAR liegt bei erfolgreichem Build in `build/libs/`.

## Test-Befehle

Als Operator im Spiel:
```text
/fakeplayers spawn 2000
/fakeplayers count
/fakeplayers remove
```

Die Spawn-Befehle legen in diesem Prototyp nur Datensätze an. Es erscheinen noch keine Figuren.

## Geplante nächste Entwicklungsstufe

1. Server-Payload mit Dimension, UUID, Name und Position.
2. Client empfängt den Datensatz und rendert ein statisches Player-Modell.
3. Aktualisieren/Entfernen beim Join, Leave und Dimensionswechsel.
4. Sichtbarkeits-/Distanzgrenze und gebündelte Pakete zur Schonung der Clients.
5. Optionale Kit-Darstellung, sofern die betreffenden Item-/Rüstungsdaten verfügbar sind.

## Performance-Hinweis

0 FPS Verlust kann niemand für 2.000 sichtbare Modelle garantieren. Clientseitiges Rendern kostet GPU/CPU-Zeit. Dieses Startgerüst rendert noch keine Figuren und hat daher auch noch keine entsprechende Renderlast.
