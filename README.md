
---

![logo](tools/images/Logo_Meli.png)

# MELI: Morse Translator
Java 11 native solution for MELI's Morse Challenge.

This API exposes three main features:
* _decodeBits2Morse_: decode a bit string representing human pulses to a morse string.
* _humanToMorse_: encode a natural language string into a morse string.
* _morseToHuman_: decode a morse string into a natural language sentence.

---
## Features

### 1. decodeBits2Morse

#### Endpoint
```
URL: /morse/bits2morse
Input: MorseDto with bits string
Responses: 
    * 200 OK: MorseDto with morse string
    * 404 Bad Request
    * 500 Internal Server Error
```

#### Validations
* The text input must be exclusively binary with a minimum length of 1.
* Assumes the max dot and the min dash have a pulse difference in bits of ***at least greater than*** 2.
    * Example: a dot would be 111 and a dash 111111
* Does ***not*** support single character representation. This refers to dot-only or dash-only intended morse message.
For example: `HI (.... ..)` . In this case please use a fullstop `HI. (.... .. .-.-.-)`
* Pauses between characters have a length of less or equal than the maximum dot length
* Pauses between letters have a length greater than the maximum dot length, and up to the maximum dash length.
* Pauses between words have a length greater than the maximum dash length.

#### Testing
```
curl --location --request POST 'https://bohledevs-meli-morse.herokuapp.com/morse/bits2morse' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text": "0000000011011011001110000011111100011111100111111000000011101111111101110111000000011000111111000000000111111001111110000000110000110111111110111011100000011011100000000000"
}'
```


### 2. humanToMorse

#### Endpoint
```
URL: /morse/2morse
Input: MorseDto with natural language string
Responses: 
    * 200 OK: MorseDto with morse string
    * 404 Bad Request
    * 500 Internal Server Error
```

#### Validations
* Only supports the following range of characters:
   * English Letters [A-Za-z]
   * Digits [0-9]
   * Whitespaces [ ]
   * Fullstops [.]
* Assumes words are separated by whitespaces

#### Testing
```
curl --location --request POST 'https://bohledevs-meli-morse.herokuapp.com/morse/2morse' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text": "HOLA MELI"
}'
```

### 3. morseToHuman

#### Endpoint
```
URL: /morse/2text
Input: MorseDto with morse string
Responses: 
    * 200 OK: MorseDto with natural language string
    * 404 Bad Request
    * 500 Internal Server Error
```

#### Validations
* Only supports the following range of characters:
    * English Letters [A-Za-z]
    * Digits [0-9]
    * Whitespaces [ ]
    * Fullstops [.]
* Assumes letters are separated by **one** whitespace
* Assumes words are separated by **two** whitespaces

#### Testing
```
curl --location --request POST 'https://bohledevs-meli-morse.herokuapp.com/morse/2text' \
--header 'Content-Type: application/json' \
--data-raw '{
    "text": ".... --- .-.. .-  -- . .-.. .."
}'
```

---
## Project Settings

### Specifications
* Java 11
```
  openjdk 11.0.14 2022-01-18
  OpenJDK Runtime Environment (build 11.0.14+9-Ubuntu-0ubuntu2.20.04)
  OpenJDK 64-Bit Server VM (build 11.0.14+9-Ubuntu-0ubuntu2.20.04, mixed mode, sharing)
```
* Maven 3
* Spring Boot 2.6.4


### Dependencies
* [spring-boot-starter-validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation): for input validation annotations
* [spring-boot-starter-web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web): for layered architecture beans
* [spring-boot-starter-test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test): unit testing
* [project lombok](https://projectlombok.org/setup/maven): for utility annotations
* [google java format](https://github.com/google/google-java-format): code style tool
* [jackson-core](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind): for serialization of Morse Alphabet during unit tests

---
## Challenge Description

* [Technical Exam PDF](Descripcion_Examen_Tecnico.pdf)