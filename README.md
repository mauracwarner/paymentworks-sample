# PaymentWorks Sample App

This is a simple REST API that provides MBTA line and stop data per the
PaymentWorks sample app coding challenge instructions.  The app has
3 endpoints and can be used via any HTTP client.  By default, it will
listen on port 8080 when run.

All of my code is under `src/main/java`.  Given more time, I would have
added tests under `src/test`, but since I was only supposed to spend a
couple of hours on this I didn't bother.

## Tech specs

* Micronaut 1.1.4
* Java 8
* Jackson 2.9.8 (JSON serialization/deserialization)
* jasminb JsonAPI Converter 0.10 (per MBTA developer recommendation)

The JsonAPI Converter uses Jackson under the covers, which allowed me to
use Jackson annotations to customize JSON serialization/deserialization.
Note the Jackson annotations in the DTO classes.

## Running the app

In your terminal:

```
$ cd paymentworks
$ ./gradlew run
```

## Using the app

By default, the server will listen on port 8080.

### Endpoints

#### `/ [GET]`

Simple index endpoint, provides proof of life and not much else.

#### `/lines [GET]`

Provides a list of all MBTA subway and light rail lines.

Sample response body:

```json
[
  {
    "id": "Red",
    "longName": "Red Line"
  }
]
```

#### `/stops?line={lineId} [GET]`

Provides a list of stops for the specified line.  The `lineId` parameter
corresponds to the `id` parameter returned by `/lines`.  Invalid `lineId`s
in the GET request will return a 404.  The `lineId` parameter is required;
if it is not populated, the API will return a 400.

Sample request:

`GET /stops?line=Orange`

Sample response body:

```json
[
  {
    "id": "place-forhl",
    "name": "Forest Hills"
  }
]
```