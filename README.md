# API Automation Challenge

## Test Cases

---
### Auth Spec
---

**SCENARIO**: User wants login in TMDB</br>
**DESCRIPTION**: Verify session id has been created successfully

**GIVEN**	The user has the TMDB url</br>
**AND** The user has an api key

**WHEN** User creates a request token</br>
**AND** User creates a session with username and password using request token

**THEN** Should return the session_id

---
### List Spec
---

**SCENARIO**: An user wants to create a list of movies </br>
**DESCRIPTION**: Verify movie list has been created

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has the session id</br>
**AND** The user has a json with the list features

**WHEN** The user creates a list

**THEN** Status code should be 201</br>
**AND** Status message saying "The item/record was created successfully"

---

**SCENARIO**: An user wants get the list details</br>
**DESCRIPTION**: Should provide the details from a list

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has the session id</br>
**AND** The user has an id from a list</br>
**AND** The list has a name

**WHEN** The user creates a list

**THEN** Status code should be 200</br>
**AND** The name list must be the same that the given

---

**SCENARIO**: An user wants add a movie into a list</br>
**DESCRIPTION**: Should add a movie in a movie list

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has the session id</br>
**AND** The user has an id from a list</br>
**AND** The list has a name

**WHEN** The user creates a list

**THEN** Status code should be 200</br>
**AND** The name list must be the same that the given

---

**SCENARIO**: An user wants clean a movie list</br>
**DESCRIPTION**: Should remove movies from a list

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has the session id</br>
**AND** The user has an id from a list</br>
**AND** The list contains at least 1 movie

**WHEN** The user clears the list
  
**THEN** Status code should be 201</br>
**AND** The list must be empty</br>
**AND** Status message saying "The item/record was updated successfully"

---

**SCENARIO**: An user wants delete a movie list</br>
**DESCRIPTION**: Should delete a movie list

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has the session id</br>
**AND** The user has an id from a existing movie list

**WHEN** The user deletes a list</br>
**AND** The user get details from removed list
  
**THEN** Status code should be 404</br>
**AND** The resource you requested could not be found

---
### Movie Spec
---

**SCENARIO**: An user wants asks for a movie</br>
**DESCRIPTION**: Should provide the details from a movie

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has a movie id</br>

**WHEN** The user asks for a movie by id</br>
**AND** The user get details from a movie
  
**THEN** Status code should be 200</br>
**AND** The title movie must match with the id provided</br>
**AND** The movie must has its release date</br>
**AND** The movie must have a producer

---

**SCENARIO**: An user wants update his movie rated vote</br>
**DESCRIPTION**: Should update the movie rated vote

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has the session id</br>
**AND** The user has a movie id</br>
**AND** The user has a json with vote to give

**WHEN** The user gives his new rated vote

**THEN** Status code should be 201</br>
**AND** The item/record was updated successfully

---
### TVShow Spec
---

**SCENARIO**: An user wants asks for a TV show</br>
**DESCRIPTION**: Should provide the details from a TV show

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has a TV show id</br>

**WHEN** The user asks for a TV show by id</br>
**AND** The user get details from a tv show
  
**THEN** Status code should be 200</br>
**AND** The name must match with the id provided</br>
**AND** The response must contain a number of seasons</br>
**AND** The response must contain a number of episodes

---

**SCENARIO**: An user wants asks for alternatives titles from a TV show</br>
**DESCRIPTION**: Should provide the alternatives titles from a TV show

**GIVEN** The user has the TMDB url</br>
**AND** The user has an api key</br>
**AND** The user has a TV show id</br>

**WHEN** The user asks for a TV show by id</br>
**AND** The user get details from a TV show
  
**THEN** Status code should be 200</br>
**AND** The response must contain a results.title key</br>
**AND** There must be different titles from the same TV show
