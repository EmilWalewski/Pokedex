# Pokedex

Set up:

1. set appropriate database credentials in application.properties file

Basic information:

* pagination - http://localhost:8080/pokedex?page=x, where x is number of page from 0 to n-1, without param page is set default to 0 
* get all pokemons - http://localhost:8080/pokedex
* post new pokemon or update old - http://localhost:8080/pokedex
* get pokemon by id - http://localhost:8080/pokedex/x,  where x is pokemon id
* get pokemon by name - http://localhost:8080/pokedex?name=x,  where x is pokemon name
* get pokemon by type - http://localhost:8080/pokedex?type=x,  where x is pokemon type, there are defined only 4 types(fire, earth, water, wind)

All cases relate to logged user. HttpBasic is used as authenticated method.

If name, type or id is not correct then exceptions are handled and appropriate answer is returned

To post:
{
  "name": "Chamander",
  "type": "Fire"
}

To update:
{
	"id": 17,
  "name": "Chamander",
   type": "Wind"
}

There are 2 predefined users: 

1. username - a, password - a 
2. username - b, password - b

All passwords are stored as encrypted in database

Each user can add, update or delete only own pokemons. User can not have two the same pokemons(by name). Unique constraint (pokemon_name, user) preserve data integrity.
