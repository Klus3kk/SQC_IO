# Przykłady testowe dla CheckActorVisitor

## Test 1: Wszystkie kroki poprawne
**Request:**
```json
{
  "type": "scenario",
  "title": "Test poprawny",
  "system actor": "System",
  "actors": ["User", "Admin"],
  "elements": [
    {
      "type": "step",
      "content": "User clicks button"
    },
    {
      "type": "step",
      "content": "System validates input"
    },
    {
      "type": "step",
      "content": "Admin approves request"
    }
  ]
}
```

**Oczekiwany wynik:**
```json
{
  "invalid steps": []
}
```

---

## Test 2: Kroki bez aktora
**Request:**
```json
{
  "type": "scenario",
  "title": "Test błędny",
  "system actor": "System",
  "actors": ["User"],
  "elements": [
    {
      "type": "step",
      "content": "User clicks button"
    },
    {
      "type": "step",
      "content": "Clicks another button"
    },
    {
      "type": "step",
      "content": "Validates the form"
    }
  ]
}
```

**Oczekiwany wynik:**
```json
{
  "invalid steps": [
    "Clicks another button",
    "Validates the form"
  ]
}
```

---

## Test 3: Słowa kluczowe IF/ELSE/FOR EACH
**Request:**
```json
{
  "type": "scenario",
  "title": "Test ze słowami kluczowymi",
  "system actor": "System",
  "actors": ["User"],
  "elements": [
    {
      "type": "step",
      "content": "IF User is logged in"
    },
    {
      "type": "step",
      "content": "ELSE System redirects to login"
    },
    {
      "type": "step",
      "content": "FOR EACH User processes item"
    },
    {
      "type": "step",
      "content": "IF clicks button"
    }
  ]
}
```

**Oczekiwany wynik:**
```json
{
  "invalid steps": [
    "IF clicks button"
  ]
}
```

**Wyjaśnienie:**
- "IF User is logged in" - OK (po usunięciu IF jest "User is logged in")
- "ELSE System redirects to login" - OK (po usunięciu ELSE jest "System redirects to login")
- "FOR EACH User processes item" - OK (po usunięciu FOR EACH jest "User processes item")
- "IF clicks button" - BŁĄD (po usunięciu IF jest "clicks button", nie ma aktora)

---

## Test 4: Zagnieżdżone scenariusze
**Request:**
```json
{
  "type": "scenario",
  "title": "Test z zagnieżdżeniem",
  "system actor": "System",
  "actors": ["User"],
  "elements": [
    {
      "type": "step",
      "content": "User starts process"
    },
    {
      "type": "scenario",
      "title": "Sub-scenario",
      "system actor": "System",
      "actors": ["Admin"],
      "elements": [
        {
          "type": "step",
          "content": "Admin reviews"
        },
        {
          "type": "step",
          "content": "Approves the request"
        },
        {
          "type": "step",
          "content": "User can also act here"
        }
      ]
    }
  ]
}
```

**Oczekiwany wynik:**
```json
{
  "invalid steps": [
    "Approves the request"
  ]
}
```

**Wyjaśnienie:**
- "User starts process" - OK (User jest aktorem w głównym scenariuszu)
- "Admin reviews" - OK (Admin jest aktorem w sub-scenariuszu)
- "Approves the request" - BŁĄD (nie zaczyna się od żadnego aktora)
- "User can also act here" - OK (User jest dostępny z głównego scenariusza, aktorzy są dziedziczeni)

---

## Test 5: Pusta lista aktorów
**Request:**
```json
{
  "type": "scenario",
  "title": "Test bez aktorów",
  "system actor": "",
  "actors": [],
  "elements": [
    {
      "type": "step",
      "content": "User clicks button"
    }
  ]
}
```

**Oczekiwany wynik:**
```json
{
  "invalid steps": [
    "User clicks button"
  ]
}
```

---

## Jak przetestować:

1. Uruchom aplikację:
```bash
mvn spring-boot:run
```

2. Wyślij request (przykład z curl):
```bash
curl -X POST http://localhost:8080/scenario/checkActors \
  -H "Content-Type: application/json" \
  -d @test_case.json
```

3. Sprawdź czy wynik zgadza się z oczekiwanym
