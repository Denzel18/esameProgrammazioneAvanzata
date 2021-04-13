# Progetto Programmazione Avanzata

## Introduzione 
Il progetto nasce dall'esigenza di gestire il parco mezzi della sede CRI di fano, ecco quindi che attraverso questo progetto ci poniamo come obbiettivo quello di realizzare un'applicazione WEB per gestire tutto ciò. Il parco mezzi prevede quindi la gestione dei veicoli a 365 gradi
* Manutenzione
* Prenotazione 
* Documentazione (con relativi allegati)

La gestione del parco mezzi è in capo a 3 tipologie di utenti:
* Utente Admin
* Utente Account(amministrativo)
* Utente Driver 
Ciascuna categorie di utenti avrà permessi e operazione diversificate. 

## Installazione
1. Installare Tomcat, MySQL, Maven e JDK
2. Creare un nuovo utente in MySQL con le seguenti informazioni, `username: root `e` password: rootroot` (**ALL PRIVILEGES**)
3. Creare un nuovo utente **Tomcat** con le seguenti informazioni, `username: tomcat`,` password: tomcat `e` roles: manager-script`
4. Avviare i servizi tomcat e mysql
* Per avviare i servizi tomcat sarà sufficiente recarsi nella cartella di sistema **Apache Tomcat** ed eseguire lo script *startup.sh*, viceversa nel caso in cui si intenda arrestare, bisognerà eseguire lo script *shutdown.sh* (Nel caso di installazione Mac o Unix Based, attraverso il gestore di pacchetti BREW, la cartella è situata in /usr/local/Apache...)
* Per quanto concerne i servizi **MySQL** per gli utenti Mac, successivamente all'installazione sarà sufficiente recarsi in *Preferenze di Sistema* e verificare il corretto avvio dei servizi 
5. Posizionarsi nella directory di root del progetto
6. Eseguire il comando `mvn install`
    * Per ulteriori opzioni in fase di debugging e testing sarà utile lanciare anche i commandi  `mvn clean` che permette la pulizia dei file compilati e l'undeploy dell'applicazione dal server Tomcat 
    * `mvn install -x` per il debug 
    * `mvn install -e` per visualizzare lo stack relativo all'errore 
7. Fine
### Per il progetto sono state utilizzate le seguenti versioni dei diversi applicativi 
1. **Apache Tomcat** 9.0.43 
2. **MySQL** 8.0.18
3. **Maven** 3.6.3 
4. **JDK** 15.0.2 

## Accesso alla piattaforma
***Ancora in fase di sviluppo***

## Funzioni per Tipologia di Utenti

CRUD = CREATE, READ, UPDATE, DELETE 
SHOW = LIST 

### Admin User 
1. SHOW VEICOLI 
2. CRUD USER 
3. SHOW PRENOTAZIONI 
4. SHOW MANUTENZIONI 
5. SHOW DOCUMENTAZIONE 
6. PROFILE 

### Account User 
1. CRUD VEICOLI 
2. CRUD DOCUMENTAZIONI & ALLEGATI RELATIVI  
3. SHOW PRENOTAZIONI 
4. CRUD MANUTENZIONI 
5. PROFILE

### Driver User 
1. PROFILE 
2. CRUD PRENOTAZIONI

## MAPPA DELLE PAGINE 
#### PRENOTAZIONI 
- /prenotazioni : SHOW prenotazioni 
- /prenotazione/new : CREATE prenotazione
- /prenotazione/new/save : SAVE prenotazione (salvataggio)
- /prenotazione/edit/${PRENOTAZIONE_ID} : UPDATE prenotazione
- /prenotazione/delete/${PRENOTAZIONE_ID} : DELETE prenotazione 

#### MANUTENZIONI 
- /manutenzioni : SHOW manutenzioni 
- /manutenzione/new : CREATE manutenzione
- /manutenzione/new/save : SAVE manutenzione (salvataggio)
- /manutenzione/edit/${MANUTENZIONE_ID} : UPDATE manutenzione
- /manutenzione/delete/${MANUTENZIONE_ID} : DELETE manutenzione 

#### DOCUMENTAZIONI
- /documentazioni : SHOW documentazioni 
- /documentazione/new : CREATE documentazione
- /documentazione/new/save : SAVE documentazione (salvataggio)
- /documentazione/edit/${DOCUMENTO_ID} : UPDATE documentazione
- /documentazione/delete/${DOCUMENTO_ID} : DELETE documentazione 

***In maniera analoga si procede per allegato, link e file (singolare operazione, plurale lista)***

#### CAR 
- /cars : SHOW veicoli 
- /car/new : CREATE veicolo 
- /car/new/save : SAVE veicolo (salvataggio)
- /car/edit/${TARGA} : UPDATE veicolo
- /car/delete/${TARGA} : DELETE veicolo 

#### USER 
- /users : SHOW users 
- /user/new : CREATE user 
- /user/new/save : SAVE user (salvataggio)
- /user/edit/${USERNAME} : UPDATE user 
- /user/delete/${USERNAME} : DELETE user 

## DOCUMENTAZIONE 
**Fare il check della documentazione !!!***

[Link alla documentazione](https://gitlab.com/bernovschi.denis/esameprogrammazioneavanzata/-/tree/master/doc)

***Ancora in fase di sviluppo***

[Link Modello DB](https://www.google.com)







