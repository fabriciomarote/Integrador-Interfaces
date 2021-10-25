# Integrador

## Modelo

https://github.com/unq-ui/unq-notes

## TP 1 - Arena

Se debe realizar una aplicación de escritorio utilizando Arena Framework. La aplicación requiere que el usuario pueda hacer las siguientes acciones:

* Loguearse.
* Poder ver sus notas.
* Agregar una nueva nota.
* Modificar una nota.
* Eliminar una nota (con ventana de confirmación)

Algunos puntos ya se encuentran implementados, otros faltan ser terminados.

## TP 2 - Api

Se debe realizar una API utilizando Javalin. Se debe armar los siguientes requests:

### POST /register

#### Cuerpo del post

```json
{
    "name": "Edward Elric",
    "email": "edwardElric@gmail.com",
    "password": "philosopherStone",
    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg"
}
```

#### Respuestas

* 201:

Se agrega un header Authentication con el token JWT.

Body:

```json
{
    "result": "ok"
}
```


### POST /login

#### Cuerpo del post

```json
{
    "email": "edwardElric@gmail.com",
    "password": "philosopherStone",
}
```

#### Respuestas

* 200:

Se agrega un header Authentication con el token JWT.

Body:

```json
{
    "result": "ok"
}
```


## NOTA

Para los siguientes request se tiene que agregar el header **Authentication** con el token JWT,
si este no se pasa o el usuario no existe la respuesta tienen que ser un **401**.


### GET /user

Retorna al usuario logueado, las notas tienen que estar ordenadas por `lastModifiedDate`

#### Respuesta

* 200:

```json
{
    "id": "user_1",
    "email": "user@gmail.com",
    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
    "displayName": "Edward",
    "notes": [
        {
            "id": "note_1",
            "title": "danger disorder",
            "description": "He always wore his sunglasses at night. It was the first time he had ever seen someone cook dinner on an elephant.",
            "lastModifiedDate": "2021-09-20T19:49:51.392"
        },
        ...
    ]
}
```

### POST /user/note

Agrega una nota al usuario

#### Cuerpo del post

```json
{
    "title": "danger disorder",
    "description": "He always wore his sunglasses at night. It was the first time he had ever seen someone cook dinner on an elephant."
}
```

#### Respuesta

* 200:

```json
{
    "id": "user_1",
    "email": "user@gmail.com",
    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
    "displayName": "Edward",
    "notes": [
        {
            "id": "note_1",
            "title": "danger disorder",
            "description": "He always wore his sunglasses at night. It was the first time he had ever seen someone cook dinner on an elephant.",
            "lastModifiedDate": "2021-09-20T19:49:51.392"
        },
        ...
    ]
}
```

### PUT /user/note/{id}

Edita la nota con el {id} del usuario

#### Cuerpo del post

```json
{
    "title": "danger disorder",
    "description": "He always wore his sunglasses at night. It was the first time he had ever seen someone cook dinner on an elephant."
}
```

#### Respuesta

* 200:

```json
{
    "id": "user_1",
    "email": "user@gmail.com",
    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
    "displayName": "Edward",
    "notes": [
        {
            "id": "note_1",
            "title": "danger disorder",
            "description": "He always wore his sunglasses at night. It was the first time he had ever seen someone cook dinner on an elephant.",
            "lastModifiedDate": "2021-09-20T19:49:51.392"
        },
        ...
    ]
}
```

### DELETE /user/note/{id}

Elimina la nota con el {id} del usuario

#### Respuesta

* 200:

```json
{
    "id": "user_1",
    "email": "user@gmail.com",
    "image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
    "displayName": "Edward",
    "notes": [
        {
            "id": "note_1",
            "title": "danger disorder",
            "description": "He always wore his sunglasses at night. It was the first time he had ever seen someone cook dinner on an elephant.",
            "lastModifiedDate": "2021-09-20T19:49:51.392"
        },
        ...
    ]
}
```

## TP 3 - Web

Se debe realizar una apliación web utilizando [React](https://es.reactjs.org/).

La aplicacion tiene que tener las siguientes funcionalidades:

* Poder loguear a un usuario.
* Poder registrar a un usuario nuevo.

> NOTA: Todas las acciones siguientes se tiene que estar logueado, en caso contrario se tiene que redireccionar a la pantalla de login

* Al loguearse con un usuario ver sus notas.
* Poder agregar una nota.
* Poder modificar una nota.
* Poder eliminar una nota.
