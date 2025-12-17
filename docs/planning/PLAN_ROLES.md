# Plan na Sprint 1

## Założenia
- Prace realizowane zgodnie z SB Sprintu 1
- Zakres Sprintu 1 dobrany z uwzględnieniem dostępności czasowej (capacity) zespołu (bo niby na to też trzeba patrzyć)
- Wszystkie zadania realizowane zgodnie z DoDą
- GUI oraz funkcjonalności poza Sprint Backlogiem nie są realizowane w Sprincie 1.

## Podział pracy

### Scrum Master (Łukasz)
- Pilnowanie przebiegu Sprintu zgodnie z zasadami Scruma.
- Pilnowanie spełnienia Definition of Done dla wszystkich zadań.
- Współudział w implementacji:
  - przygotowanie szkieletu projektu,
  - współtworzenie architektury rozwiązania,
  - implementacja wybranych elementów kodu wspólnie z Developerami.
- Konfiguracja i utrzymanie repozytorium GitHub.
- Weryfikacja działania CI.
- Oznaczenie wersji inkrementu produktu (tag + wersja w pom.xml).

### Proxy Product Owner
- Zarządzanie Product Backlogiem.
- Przeniesienie Product Backlogu do GitHub Issues.
- Utworzenie Sprint Backlogu (Milestone: Sprint 1).
- Dopilnowanie opisów zadań i kryteriów akceptacji.
- Kontrola zakresu Sprintu 1.
- Kontakt z prowadzącym (Product Ownerem).

### Developer 1
- Implementacja parsera scenariusza:
  - konwersja tekstu do modelu obiektowego,
  - obsługa numerowanych kroków,
  - obsługa zagnieżdżeń.
- Walidacja struktury scenariusza.
- Testy jednostkowe parsera.
- Dokumentacja techniczna (Javadoc) dla implementowanych klas.

### Developer 2
- Implementacja analizy jakości scenariusza:
  - sprawdzenie liczby kroków,
  - weryfikacja aktora na początku kroku,
  - wykrywanie słów kluczowych IF / ELSE / FOR EACH.
- Zastosowanie wzorców Visitor i Strategy.
- Implementacja REST API:
  - endpoint przyjmujący scenariusz,
  - endpoint zwracający raport jakości.
- Konfiguracja logowania SLF4J (DEBUG, INFO).
- Dokumentacja techniczna (Javadoc) dla klas analizy i REST.

## Zadania techniczne wspólne
- Konfiguracja GitHub Actions (automatyczny build na push).
- Przygotowanie diagramów UML:
  - diagram klas,
  - diagram sekwencji REST API.
- Bieżąca aktualizacja statusów zadań w GitHub Issues.

## Efekt Sprintu 1
- Działający inkrement aplikacji
- Repozytorium GitHub z tagiem wersji
- REST API umożliwiające analizę scenariusza
- Automatyczny build (CI)
- Dokumentacja techniczna i UML zgodne z DoD.
