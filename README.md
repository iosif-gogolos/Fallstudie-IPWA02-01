# Fallstudie-IPWA02-01
Dieses Repository dient zur Speicherung des Projektes zur Lösung der Aufgabe 3 der Fallstudie zum Kurs IPWA02-01



## Voraussetzungen

- Java JDK 17 oder höher (getestet mit JDK 22)
- Apache Maven 3.8+
- Apache Tomcat 11 (oder kompatibler Jakarta EE 10 Servlet-Container)
- Git

Das Projekt verwendet **Jakarta EE (JSF + CDI)**, **PrimeFaces** und **Hibernate/JPA**.  
Die persistente Datenhaltung erfolgt in einer **H2 Embedded Datenbank** (`ghostnetfinder.db`) im Projektverzeichnis.

---

## Installation & Start

### 1. Projekt klonen
```bash
git clone https://github.com/<USERNAME>/Fallstudie-IPWA02-01.git
cd Fallstudie-IPWA02-01/GhostNetFinder
```
### 2. Build mit Maven
```bash
mvn clean package

```

Das Ergebnis ist eine ghostnetfinder.war im target/ Verzeichnis.

### 3. Deployment in Tomcat
Kopiere die WAR-Datei nach TOMCAT_HOME/webapps:

```bash
cp target/ghostnetfinder.war $TOMCAT_HOME/webapps/

```
Starte Tomcat anschließend neu.

### 4. Aufruf im Browser
```bash
http://localhost:8080/ghostnetfinder/index.xhtml

```

---
#Plattfotm-spezifische Hinweise
##Windows (PowerShell oder CMD)
```bash
git clone https://github.com/<USERNAME>/Fallstudie-IPWA02-01.git
cd Fallstudie-IPWA02-01\GhostNetFinder
mvn clean package
copy target\ghostnetfinder.war %CATALINA_HOME%\webapps\
%CATALINA_HOME%\bin\startup.bat

```

##macOS (zsh/bash)
```bash
git clone https://github.com/<USERNAME>/Fallstudie-IPWA02-01.git
cd Fallstudie-IPWA02-01/GhostNetFinder
mvn clean package
cp target/ghostnetfinder.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh

```

## Linux (bash)
```bash
git clone https://github.com/<USERNAME>/Fallstudie-IPWA02-01.git
cd Fallstudie-IPWA02-01/GhostNetFinder
mvn clean package
cp target/ghostnetfinder.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh
```

---

# Testen

- Nach dem ersten Start legt Hibernate die Datei ghostnetfinder.db im Projektverzeichnis an.
- Diese Datenbank kann mit jedem H2-kompatiblen Tool geöffnet werden, z. B. dem H2 Console Client oder IntelliJ IDEA/VS Code Datenbank-Plugins.

- Verbindungseinstellungen:

    - JDBC-URL: jdbc:h2:file:./ghostnetfinder.db;AUTO_SERVER=TRUE;MODE=MySQL

    - Benutzer: sa

    -   Passwort: (leer)

Durch die Verwendung von H2 ist die Datenbank plattformunabhängig, es muss keine externe DB installiert werden.

