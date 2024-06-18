# Bibliothekssystem README

## Einführung
Das Bibliothekssystem ist eine Anwendung zur Verwaltung von Büchern, Buchkopien und Kunden in einer Bibliothek. Es ermöglicht Benutzern verschiedene Aktionen wie das Anzeigen von Büchern, das Verwalten von Buchkopien und das Ausleihen sowie Rückgeben von Buchkopien durchzuführen.

## Klassenübersicht

### Book
Die **Book**-Klasse repräsentiert ein Buch mit detaillierten Informationen wie ISBN, Titel, Autoren, Veröffentlichungsjahr, Stadt, Verlag und Auflage.

#### Methoden
- `loadBooksFromCSV()`: Lädt Bücher aus einer CSV-Datei, wobei das Format der CSV-Datei vorgegeben ist.
- `displayBooks()`: Zeigt alle gespeicherten Bücher mit ihren Details in der Konsole an.
- `deleteBook(String isbn)`: Löscht ein Buch anhand der ISBN.
- `findBookByIsbn(String isbn)`: Sucht und liefert ein Buch anhand der ISBN zurück.

### BookCopy
Die **BookCopy**-Klasse verwaltet einzelne Kopien eines Buches in der Bibliothek, einschließlich Informationen wie ID, ISBN, Regalplatz, Status (Hinzugefügt zur Bibliothek, Ausgeliehen) und Ausleihdatum

#### Methoden
- `loadBookCopiesFromCSV()`: Lädt Buchkopien aus einer CSV-Datei.
- `displayBookCopies()`: Zeigt alle Buchkopien mit ihren Details in der Konsole an.
- `displayLentBookCopies()`: Zeigt alle aktuell ausgeliehenen Buchkopien an.
- `displayAvailableBookCopies()`: Zeigt alle verfügbaren Buchkopien an, die nicht ausgeliehen sind.
- `deleteBookCopy(int id)`: Löscht eine Buchkopie anhand der ID.
- `searchBookCopy(String query)`: Sucht Buchkopien anhand von Kriterien wie ISBN, Titel oder Autor.
- `findBookCopyById(int id)`: Sucht eine Buchkopie anhand der ID.
- Methoden zum Ausleihen (`lendBookCopy()`) und Zurückgeben (`returnBookCopy()`) von Buchkopien.

### Customer
Die **Customer**-Klasse repräsentiert die Bibliothekskunden mit Informationen wie ID, Name, Vorname, Adresse, PLZ, Stadt und Gebührenstatus.

#### Methoden
- `loadCustomersFromCSV()`: Lädt Kundeninformationen aus einer CSV-Datei.
- `displayCustomers()`: Zeigt alle Kunden mit ihren Details in der Konsole an.
- `deleteCustomer(int id)`: Löscht einen Kunden anhand der ID.
- `findCustomerById(int id)`: Sucht einen Kunden anhand der ID.

### LibrarySystem
Die **LibrarySystem**-Klasse stellt die Benutzeroberfläche für das Bibliothekssystem bereit. Benutzer führen über das Hauptmenü Funktionen wie das Anzeigen, Löschen, Suchen und Verwalten von Büchern, Buchkopien und Kunden aus.

#### Methoden
- `main(String[] args)`: Startet die Anwendung und das Menüsystem.
- `loadExampleData()`: Lädt Beispieldaten für Bücher, Buchkopien und Kunden.
- Methoden zur Anzeige von Büchern (`displayBooks()`), Buchkopien (`displayBookCopies()`), Kunden (`displayCustomers()`) sowie spezielle Methoden zur Suche und Verwaltung von Ausleihen und Rückgaben von Buchkopien.
- Das System bietet umfangreiche Fehlerbehandlung und Eingabeverarbeitung, um eine reibungslose Interaktion zu gewährleisten.

## Ressourcen
- Die Anwendung nutzt CSV-Dateien im Verzeichnis `src/main/resources`, um Daten für Bücher, Buchkopien und Kunden zu speichern und zu laden.

## Autoren und Version
Die Bibliothek wurde von Team 50 entwickelt.

## .csv-Format
**benutzer.csv**
- id,name,firstName,address,zipCode,city,feesPayed
- 1,Sachs,David,Urbanstraße4,70182,Stuttgart,ja
- 2,Mustermann,Max,Teststraße1,12345,Berlin,nein

**buchkopien.csv**
- id,isbn,shelfLocation,addedToLibrary,lent,lentDate
- 1,978-3-16-148410-0,Regal A,true,false,2024-01-9
- 2,978-1-23-456789-0,Regal B,true,false,2023-05-12

**buecher.csv**
- isbn,title,authors,year,city,publisher,edition
- 978-3-16-148410-0,Troja,Dave,2024,Stuttgart,Beispielverlag,1. Auflage
- 978-1-23-456789-0,Odyssee,Homer,2023,Berlin,Ein weiterer Verlag,2. Auflage