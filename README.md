# Spring Boot Device Seller

## Endpoints

### sign-up
....
POST /api/authentication/sign-up HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 76

{
"name":"admin",
"username": "admin",
"password": "admin"
}
....

### sign-in
....
POST /api/authentication/sign-in HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 53

{
"username": "admin",
"password": "admin"
}
}
....

### change-Role
....
PUT /api/user/change/ADMIN HTTP/1.1
Host: localhost:8080
Authorization: ••••••
....

### Save Device
....
POST /api/device HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoiUk9MRV9BRE1JTiIsInVzZXJJZCI6MiwiZXhwIjoxNzU0MzU4NDYzfQ.FI_beTgmkOpuAQUzaHmS1ZITEo7n5OS6TetWtSuXbggWagjsAO2WQ6aYB0s9b7fpxJhDpeVNf0HRDbzfa4mRow
Content-Length: 104

{
"name":"device-20",
"description":"desc-20",
"price":999,
"deviceType":"LAPTOP"
}
....
### Delete-Device
....
DELETE /api/device/1 HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoiUk9MRV9BRE1JTiIsInVzZXJJZCI6MiwiZXhwIjoxNzU0MzU4NDYzfQ.FI_beTgmkOpuAQUzaHmS1ZITEo7n5OS6TetWtSuXbggWagjsAO2WQ6aYB0s9b7fpxJhDpeVNf0HRDbzfa4mRow
....

### Purhase-device
....
GET /api/purchase HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOiJST0xFX1VTRVIiLCJ1c2VySWQiOjEsImV4cCI6MTc1NDM2MDYyMH0.dKLtVVj7jEJ1AqOnwxODNSSsDfhL1XqqOhvakv08jWU4Dhtmr2IsBMzX_kWjpfJxkmxjFEM0JdTeB_vfIKCAYA
Content-Length: 77

{
"userId":2,
"deviceId":3,
"price":99,
"color":"blue"
}
....
### Get-Purhase
....
POST /api/purchase HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOiJST0xFX1VTRVIiLCJ1c2VySWQiOjEsImV4cCI6MTc1NDM2MDYyMH0.dKLtVVj7jEJ1AqOnwxODNSSsDfhL1XqqOhvakv08jWU4Dhtmr2IsBMzX_kWjpfJxkmxjFEM0JdTeB_vfIKCAYA
Content-Length: 79

{
"userId":2,
"deviceId":3,
"price":99,
"color":"blue"

}
....