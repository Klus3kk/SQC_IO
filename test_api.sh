#!/bin/bash

# Skrypt do testowania endpoint /scenario/checkActors
# Użycie: ./test_api.sh (po uruchomieniu aplikacji mvn spring-boot:run)

API_URL="http://localhost:8080/scenario/checkActors"

echo "=== Test 1: Wszystkie kroki poprawne ==="
curl -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
    "type": "scenario",
    "title": "Test poprawny",
    "system actor": "System",
    "actors": ["User"],
    "elements": [
      {"type": "step", "content": "User clicks button"},
      {"type": "step", "content": "System validates input"}
    ]
  }'
echo -e "\n\n"

echo "=== Test 2: Kroki bez aktora ==="
curl -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
    "type": "scenario",
    "title": "Test błędny",
    "system actor": "System",
    "actors": ["User"],
    "elements": [
      {"type": "step", "content": "User clicks button"},
      {"type": "step", "content": "Clicks another button"}
    ]
  }'
echo -e "\n\n"

echo "=== Test 3: Słowa kluczowe IF/ELSE/FOR EACH ==="
curl -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
    "type": "scenario",
    "title": "Test ze słowami kluczowymi",
    "system actor": "System",
    "actors": ["User"],
    "elements": [
      {"type": "step", "content": "IF User is logged in"},
      {"type": "step", "content": "ELSE System redirects to login"},
      {"type": "step", "content": "IF clicks button"}
    ]
  }'
echo -e "\n\n"

echo "=== Test 4: Zagnieżdżone scenariusze ==="
curl -X POST $API_URL \
  -H "Content-Type: application/json" \
  -d '{
    "type": "scenario",
    "title": "Test z zagnieżdżeniem",
    "system actor": "System",
    "actors": ["User"],
    "elements": [
      {"type": "step", "content": "User starts process"},
      {
        "type": "scenario",
        "title": "Sub-scenario",
        "system actor": "System",
        "actors": ["Admin"],
        "elements": [
          {"type": "step", "content": "Admin reviews"},
          {"type": "step", "content": "Approves the request"}
        ]
      }
    ]
  }'
echo -e "\n\n"
