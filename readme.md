# Spring Boot Rest API - AF

## Authors

> Michell Algarra Barros - 190100

> Gustavo Moreira de Mello - 180525

## Endpoints
### Use http://localhost:5000 to access the API
```bash
> Clients

Get - /clients
Post - /clients

Get (by id) - /clients/{id}
Put - /clients/{id}
Delete - /clients/{id}

Get (reservations from a client) - /clients/{id}/reservations
Post (client reservation) - /clients/{id}/reservations

> Vehicles

Get - /vehicles
Post - /vehicles

Get (by id) - /vehicles/{id}
Put - /vehicles/{id}
Delete - /vehicles/{id}

Get (reservations from a vehicle) - /vehicles/{id}/reservations

> Reservations

Get - /reservations
Get (by number) - /reservations/{number}
```

## Notes
### Use the following pattern in Postman
### **Clients**
> The clients id starts at 630
```JSON
    "name": "Client Name", // must have between 3 and 30 characters
    "address": "Client Address", // must have between 5 and 60 characters
    "cpf": "44455566600" // must have 11 characters
```
### **Vehicles**
> The vehicles id starts at 240
```JSON
    "model": "Vehicle Model", // must have between 2 and 30 characters
    "dailyValue": 0000.00 // just positive values
```
### **Reservations**
> The reservations number starts at 1

> To do a reservation, at least one client and one vehicle must exist
```JSON
    "start": "dd/mm/yyyy@hh:mm:ss", // must be in this format
    "end": "dd/mm/yyyy@hh:mm:ss", // must be in this format
    "vehicleModel": "Vehicle Model" // must enter a model that already exists
```

## Observations
- The code was fully wrote in English
- No comments were added into the code
- All data must be passed in the body as a JSON
