# Sistem za upravljanje bibliotekom

## Opis

Sistem za upravljanje bibliotekom je Java konzolna aplikacija razvijena
za projekat iz objektno orijentisanog programiranja.

Aplikacija pruža osnovnu funkcionalnost upravljanja bibliotekom,
uključujući upravljanje knjigama i časopisima, registraciju članova,
kao i pozajmljivanje i vraćanje bibliotečkih stavki.

## Glavne funkcije

- Prikaz svih dostupnih bibliotečkih stavki
- Dodavanje knjiga i časopisa
- Registracija članova biblioteke
- Pozajmljivanje bibliotečkih stavki
- Vraćanje pozajmljenih stavki
- Prikaz aktivnih pozajmica
- Pretraga bibliotečkih stavki po naslovu

## Tehnologije

- JavaSE-26
- Eclipse IDE
- Java Collections Framework
- Standardne Java biblioteke

## Kako pokrenuti u Eclipse-u

1. Instalirajte JDK 17 ili noviji.
2. Otvorite Eclipse IDE.
3. Izaberite File → Import → Existing Projects into Workspace.
4. Izaberite folder projekta LibraryManagementSystem.
5. Otvorite paket `library.app`.
6. Pokrenite `Main.java` kao Java aplikaciju.

## Struktura projekta

- `model` - Sadrži klase podataka i interfejse aplikacije.
- `service` - Sadrži logiku upravljanja bibliotekom.
- `ui` - Upravlja ulazom i izlazom preko konzole.
- `app` - Sadrži ulaznu tačku aplikacije.

## Status projekta

Projekat je trenutno u fazi razvoja.