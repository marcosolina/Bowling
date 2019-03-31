
## Istruzioni per creare l'immagine Docker ed eseguire il container


Pre requisiti:
* Macchina linux
* Avere dinsttalato docker

Passi da eseguire:
* scaricare la cartella BowlingDocker sulla macchin dove avete installato Docker
* Aprire il terminale
* cd /nella/directory/scaricata
* docker build -t marco/bowling .
* docker run -p 80:8080 marco/bowling
* aprite il browser incollte il link seguente sostituendo il place holder con l'ip del server su cui gira il container: http://{ip server}/Bowling/
