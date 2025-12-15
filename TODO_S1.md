# TODO

## Cel Sprintu #1
"Dostarczony szkielet aplikacji SQC z działającą analizą scenariuszy tekstowych, dostępny przez REST API, z automatycznym buildem, dokumentacją techniczną i podstawową analizą jakości scenariusza."

* Tutaj głównie potrzebujemy:
  - Fundamentów,
  - Architektury,
  - 2-4 sensowne PB items,
  - pełne DoD

## Organizacyjne
* Oszacowanie dostępności czasowej (liczby godzin) wszystkich członków zespołu.
* Uwzględnienie oszacowanych godzin (capacity) przy wyborze zakresu Sprint Backlogu.

## Sprint Backlog (na Sprint 1)
1. Parsowanie scenariusza tekstowego -> model obiektowy
2. Analiza:
* Liczba kroków
* Aktor na początku kroku
* Słowa kluczowe IF/ELSE/FOR EACH
3. REST API:
* Upload scenariusza
* Zwrot raportu jakości
4. Infrastruktura:
* Repo
* CI
* Dokumentacja
* UML

## Zadania
### PB: Parsowanie scenariusza
* Zaprojektowanie modelu (Composite)
* Parser tekstu
* Walidacja struktury
* Testy jednostkowe

### PB: Analiza jakości (Visitor + Strategy)
* Liczba kroków
* Analiza aktora
* Analiza słów kluczowych
* Report wyników

### PB: REST API
* Kontroler REST
* Endpoint analizy
* DTO odpowiedzi
* Diagram sekwencji

### Techniczne (DoD):
* Konfiguracja GitHub repo
* Konfiguracja GitHub Actions
* Konfiguracja logowania SLF4J
* Javadoc (min. 3 klasy)
* Diagram klas UML
* Oznaczenie wesji (tag + pom.xml)

## Jak ma wyglądać GitHub

### Repo
* README:
  - Opis projektu
  - DoD
  - Cel Sprintu
* pom.xml w katalogu głównym
* tag wersji (np. v0.1.0)

### Issues
* PB item = Issue
* Zadania = checklist / sub-issues
* Labels:
  - PB
  - Sprint1
  - Feature
  - Tech
* Milestone: Sprint 1
