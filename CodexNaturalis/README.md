#CodexNaturalis Board Game - Software Engineering Project
<img src="https://www.craniocreations.it/storage/media/products/19/41/Codex_scatola+ombra.png" width="260" align="right" />
CodexNaturalis Board Game is the final test of *"Software Engineering"* course of *"Software Engineering"* course
of *"Engineering of Computing Systems"* held at Politecnico di Milano (2023/2024)

*Teacher* : Pierluigi San Pietro

## The Team
* [Fabio Montuschi] (https://github.com/Feb126)
* [Giorgio Monaco] (https://github.com/giorgiomonaco)
* [Tammaso Neri] (https://github.com/tommasonerii)
* [Matteo Croce] (https://github.com/TeoCroce)

## Project specification
The project consists of a Java version of the board game Codex Naturalis, made by Cranio Creations. You can find the real game [here] (https://www.craniocreations.it/prodotto/codex-naturalis).

Project requirements : [link] (https://github.com/giorgiomonaco/ing-sw-2024-Monaco-Montuschi-Neri-Croce/blob/main/Resources/ProjectRequirements.pdf)
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
| Simultaneous games           |   ⛔    |
| Persistence                  |   ⛔    |
| Resilience to disconnections |   ✅    |


⛔ Not implemented(or Work in Progress) &nbsp;&nbsp;&nbsp;&nbsp; ✅ Implemented
Usage

### Requirements

Regardless of the operating system, you must have installed the following programs:
- Java 21
- Maven []

### Compile Instructions
1. Clone this repository:
   shell
   git clone https://github.com/giorgiomonaco/ing-sw-2024-Monaco-Montuschi-Neri-Croce

2. Move to the repository folder.
3. Build the code with maven and move the jar files from target to a new directory of your choice:
   shell
   mvn clean package

4. Move to the that directory and execute the server and/or the client:
   shell
   java -jar Server.jar
   java -jar Client.jar


### Run Instructions
1. Clone this repository:
   shell
   git clone https://github.com/giorgiomonaco/ing-sw-2024-Monaco-Montuschi-Neri-Croce

2. Move to the repository folder.

3. Move to the directory "deliverables\final\jar" and execute the server and a client:
   shell
   java -jar Server.jar
   java -jar Client.jar