# LoginPage

A Spring boot API with JWT Authentication.

## Development server

Run LoginAuthApiApplication.java. Endpoints are accessed by the address `http://localhost:8080/`

## Endpoints 

| METHOD | ENDPOINT | DESCRIPTION | REQUEST EXAMPLE | RESPONSE EXAMPLE |
|---|---|---|---|---|
| GET | `/user` | Return a page when user has a token | `curl -X GET "http://localhost:8080/user"`
| POST | `/auth/login` | Signin request | `curl -X POST -H "Content-Type: application/json" -d '{"email": "test@gmail.com", "password":"teste123"}' "http://localhost:8080/auth/login"` | `{"name": "xpto", "token":"xpto"}` |
| POST | `/auth/register` | Signup request | `curl -X POST -H "Content-Type: application/json" -d '{"name":"John","email": "test@gmail.com", "password":"teste123"}' "http://localhost:8080/auth/register"` | `{"name": "John", "token":"xpto"}` |
