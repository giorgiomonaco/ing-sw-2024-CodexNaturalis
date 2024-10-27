CodexNaturalis Board Game - Software Engineering Project
<img src="https://www.craniocreations.it/storage/media/products/19/41/Codex_scatola+ombra.png" width="260" align="right" />
CodexNaturalis Board Game is the final test of *"Software Engineering"* course of *"Software Engineering"* course
of *"Engineering of Computing Systems"* held at Politecnico di Milano (2023/2024)

*Teacher* : Pierluigi San Pietro

## The Team
* [Giorgio Monaco](https://github.com/giorgiomonaco)
* [Fabio Montuschi](https://github.com/Feb126)
* [Tammaso Neri](https://github.com/tommasonerii)
* [Matteo Croce](https://github.com/TeoCroce)

## Project specification
The project consists of a Java version of the board game Codex Naturalis, made by Cranio Creations. You can find the real game [here] (https://www.craniocreations.it/prodotto/codex-naturalis).

Project requirements : [link] (https://github.com/giorgiomonaco/ing-sw-2024-Monaco-Montuschi-Neri-Croce/blob/main/requirements/ProjectRequirements.pdf)




## Implemented functionalities
### Main functionalities
| Functionality                    | Status |
|:---------------------------------|:------:|
| Complete rules                   |   ✅    |
| RMI                              |   ✅    |
| Socket                           |   ✅    |
| CLI (Command Line Interface)   |   ✅    |
| GUI (Graphical User Interface) |   ✅    |


### Advanced functionalities
| Functionality                | Status |
|:-----------------------------|:------:|
| Chat                         |   ✅    |
| Resilience to disconnections |   ✅    |


### Coverege by testing
| Target     | % Classes |
|:-----------|:---------:|
| Model      |   100%    |
| Controller |    90%    |

### Requirements

Regardless of the operating system, you must have installed the following programs:
- Java 21
- Maven

### Setup

* In the [JAR](https://github.com/giorgiomonaco/ing-sw-2024-Monaco-Montuschi-Neri-Croce/tree/main/CodexNaturalis/deliverables/JAR) folder there are two multi-platform jar files, one to set the server up, and the other one to start the Client:

* The Server can be run with the following command, the RMI port is 1234, the socket port is 1235:
 > java -jar ServerMain.jar 
 
The Server IP can be chosen in the setup phase, if no insertion it will be automatically selected "localhost" as Server

* The Client can be run with the following command:
 > java -jar ClientMain.jar 

In the setup phase will be asked:
* The Server Ip and the connection ports to which connect to.
* The wanted type of UI : TUI or GUI
* The wanted type of connection: Socket or RMI

### NOTA

* Codex Naturalis è un gioco da tavolo sviluppato ed edito da Cranio Creations Srl. I contenuti grafici di questo progetto riconducibili al prodotto editoriale da tavolo sono utilizzati previa approvazione di Cranio Creations Srl a solo scopo didattico. È vietata la distribuzione, la copia o la riproduzione dei contenuti e immagini in qualsiasi forma al di fuori del progetto, così come la redistribuzione e la pubblicazione dei contenuti e immagini a fini diversi da quello sopracitato. È inoltre vietato l'utilizzo commerciale di suddetti contenuti.
