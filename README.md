
# Sistem za upravljanje bibliotekom

## Opis

Sistem za upravljanje bibliotekom (Library Management System) je Java konzolna aplikacija razvijena kao školski projekat za predmet Objektno orijentisano programiranje.

Aplikacija pruža osnovnu funkcionalnost upravljanja bibliotekom, uključujući upravljanje knjigama i časopisima, registraciju članova, pozajmljivanje i vraćanje bibliotečkih stavki, kao i praćenje pozajmica.

## Glavne karakteristike

- Prikaz svih bibliotečkih stavki
- Prikaz dostupnih bibliotečkih stavki
- Dodavanje novih knjiga i časopisa
- Registracija novih članova biblioteke
- Prikaz registrovanih članova
- Pozajmljivanje knjiga i časopisa
- Vraćanje pozajmljenih stavki
- Prikaz svih pozajmica
- Prikaz aktivnih pozajmica
- Pretraga bibliotečkih stavki po naslovu
- Prikaz prekoračenih (isteklih) pozajmica
- Automatsko generisanje ID-ja pozajmice
- Validacija unosa i upravljanje greškama

## Koncepti objektno orijentisanog programiranja

Projekat demonstrira sledeće OOP principe:

- **Enkapsulacija:** Privatni atributi sa konstruktorima, get metodama i validiranim set metodama.
- **Nasleđivanje:** Klase Book i Magazine nasleđuju klasu LibraryItem.
- **Polimorfizam:** Nadjačane (overridden) metode se pozivaju kroz nadređeni tip LibraryItem.
- **Apstrakcija:** Klasa LibraryItem je apstraktna klasa.
- **Interfejsi:** Interfejs Borrowable definiše operacije pozajmljivanja.

## Tehnologije

- Java SE 26
- Eclipse IDE
- Java Collections Framework
- ArrayList
- LocalDate
- Standardne Java biblioteke

Nisu potrebne spoljne zavisnosti (external dependencies).

## Kako pokrenuti u Eclipse okruzenju

1. Instalirajte JDK 26 i Otvorite Eclipse IDE.
2. Izaberite File → Import → Existing Projects into Workspace.
3. Izaberite folder projekta LibraryManagementSystem.
4. Uverite se da projekat koristi JavaSE-26.
5. Otvorite paket library.app.
6. Kliknite desnim tasterom miša na fajl Main.java. i izaberite Run As → Java Application.

## Struktura projekta

```text
LibraryManagementSystem/
│
├── src/
│   └── library/
│       ├── app/
│       │   └── Main.java
│       │
│       ├── model/
│       │   ├── Borrowable.java
│       │   ├── LibraryItem.java
│       │   ├── Book.java
│       │   ├── Magazine.java
│       │   ├── Member.java
│       │   └── Loan.java
│       │
│       ├── service/
│       │   └── LibraryService.java
│       │
│       └── ui/
│           └── ConsoleUI.java
│
├── .gitignore
└── README.md
```

## Odgovornosti klasa

### model

- `Borrowable` - Interfejs koji definiše operacije pozajmljivanja.
- `LibraryItem` - Apstraktna nadređena klasa za bibliotečke stavke.
- `Book` - Predstavlja knjigu.
- `Magazine` - Predstavlja časopis.
- `Member` - Predstavlja registrovanog člana biblioteke.
- `Loan` - Čuva informacije o pozajmici i upravlja njenim statusom.

### service

- `LibraryService` - Upravlja bibliotečkim stavkama, članovima, pozajmljivanjem, vraćanjem, pretragom i praćenjem pozajmica.

### ui

- `ConsoleUI` - Upravlja korisničkim unosom i prikazuje konzolni meni.

### app

- `Main` - Ulazna tačka aplikacije i inicijalizacija početnih podataka.

## Pravila pozajmljivanja

- Podrazumevani period pozajmljivanja je 14 dana.
- Svaka pozajmica dobija automatski generisan ID.
- Nedostupna stavka se ne može ponovo pozajmiti.
- Vraćene stavke ponovo postaju dostupne za pozajmljivanje.
- Prekoračene pozajmice se automatski identifikuju.

## Skladištenje podataka

Aplikacija čuva podatke u memoriji pomoću ArrayList kolekcija.
Ne koristi se spoljna baza podataka.

Svi podaci aplikacije se resetuju kada se program ponovo pokrene.

## Status projekta

Završen – Školski projekat.
