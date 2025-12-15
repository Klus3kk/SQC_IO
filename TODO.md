# TODO

Link do spreadsheets: [Link](https://docs.google.com/spreadsheets/d/e/2PACX-1vTn6j3M8pmGEzrsQk8mXse7lVHUdhYWkfxbkQiYI23rBtwM4N3bWw0qtupW-gesfCkcYasnZ-eEXl-F/pubhtml)

## Kontekst projektu
### **Scenario Quality Checker (SQC):**
Robimy aplikację dla analityków wymagań, która:
* Analizuje scenariusza zapisane tekstowo,
* Sprawdza ich jakość (czyli strukturę, aktorów, słowa kluczowe itd.)
* Udostępnia funkcjonalność:
  - Przez GUI
  - Przez REST API (integracja z innymi narzędziami)

Scenariusze:
* Mają aktorów (zewnętrznych + system),
* Kroki numerowane,
* Kroki warunkowe (IF / ELSE / FOR EACH)
* Zagnieżdżenia,
* Reprezentację wewnętrzną (model obiektowy)

## Role w zespole
### **Scrum Master (1 osoba)**
* Pilnuje Scruma,
* Pilnuje DoD (kryteria gotowości zadania, czyli pewnie sprawdza czy wszystko jest skończone)
* **Też programuje (no shit XD)**

### **Proxy Product Owner (1 osoba)**
* Opiekuje się Product Backlogiem
* Dba o opisy, priorytety, kryteria akceptacji,
* Kontakt z prowadzącym (tzw. PO)

### **Developer (2 osoby)**
* Implementacja, testy, dokumentacja

## Product Backlog
Najważniejsze elementy PB (z priorytetami i BV):
1. REST API do pobierania funkcjonalności SQC (BV = 1)
2. Sprawdzenie liczby kroków scenariusza (BV = 1)
3. Sprawdzenie słów kluczowych (IF/ELSE/FOR EACH) (BV = 1)
4. Weryfikacja czy kroki zaczynają się od aktora (BV = 1)
5. Eksport scenariusza jako tekst z numeracją (BV = 1.5)
6. Analiza poziomów zagnieżdżenia i uproszczenie scenariusza (BV = 1)
7. GUI (na później – brak negocjacji na tym etapie)

Każdy PB item ma:
* Opis user story,
* Ważność,
* Pracochłonność
* Business Value,
* Kryteria akceptacji (to jest bardzo ważne, bo bez tego PB jest niegotowy)

## Definition of Done (DoD) - co musi być spełnione?
* Kod w Github, prowadzący ma dostęp
* Repo oznaczone tagiem wersji, zgodnym z pom.xml
* Automatyczny build (CI)
* CI reaguje na push (Github Actions)
* Dokumentacja techniczna (czyli pewnie Javadoc):
  - Dla nas to min. 3 klasy
* Rejestr Produktu + Rejestr Sprintu w narzędziu (Github Issues / Projects)
* Analiza wykonania + logowanie SLF4J (DEBUG + INFO)
* Diagram UML:
  - Diagram klas
  - Diagram sekwencji (REST)
* Wzorce projektowe:
  - Scenario Quality Checker -> Visitor
  - Building Info / Struktura Scenariusza -> Composite
  - Transformacje (np. eksport) -> Decorator
  - Algorytmy analizy -> Strategy



