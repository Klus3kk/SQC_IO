serwer na porcie 3000
http://localhost:3000/test



# SQC_IO
Aplikacja do weryfikowania scenariuszy.

## Jak ma wyglądać notacja
* Scenariusz zawiera nagłówek określający jego tytuł i aktorów (zewnętrznych oraz system)
* Scenariusz składa się z kroków (każdy krok zawiera tekst)
* Kroki mogą zawierać pod-scenariusze (dowolny poziom zagłębień)
* Kroki mogą się zaczynać od słów kluczowych: IF, ELSE, FOR EACH

## Przykładowa notacja scenariuszy
Tytuł: Dodanie książki

Aktorzy:  Bibliotekarz

Aktor systemowy: System

* Bibliotekarz wybiera opcje dodania nowej pozycji książkowej
* Wyświetla się formularz.
* Bibliotekarz podaje dane książki.
* IF: Bibliotekarz pragnie dodać egzemplarze książki
    - Bibliotekarz wybiera opcję definiowania egzemplarzy
    - System prezentuje zdefiniowane egzemplarze
    - FOR EACH egzemplarz:
        - Bibliotekarz wybiera opcję dodania egzemplarza
        - System prosi o podanie danych egzemplarza
        - Bibliotekarz podaje dane egzemplarza i zatwierdza.
        - System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.
- Bibliotekarz zatwierdza dodanie książki.
- System informuje o poprawnym dodaniu książki.
