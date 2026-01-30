# FlipFit Dropwizard Application

A gym management system built with Dropwizard framework providing RESTful APIs.

## Prerequisites

- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+

## Project Structure

```
src/com/flipfit/
├── FlipFitDropwizardApplication.java  # Main application entry point
├── FlipFitConfiguration.java          # Dropwizard configuration
├── bean/                              # Entity/Model classes
├── business/                          # Service layer (interfaces & implementations)
├── client/                            # REST Controllers
│   ├── UserController.java
│   ├── AdminController.java
│   ├── GymCustomerController.java
│   ├── GymOwnerController.java
│   ├── GymCenterController.java
│   ├── BookingController.java
│   ├── SlotController.java
│   └── CorsFilter.java
├── dao/                               # Data Access Objects
├── dto/                               # Data Transfer Objects
├── exception/                         # Custom exceptions & mappers
└── health/                            # Health checks
```

## Building the Application

```bash
mvn clean package
```

## Running the Application

```bash
java -jar target/flipfit-dropwizard-1.0-SNAPSHOT.jar server config.yml
```

Or using Maven:
```bash
mvn exec:java -Dexec.mainClass="com.flipfit.FlipFitDropwizardApplication" -Dexec.args="server config.yml"
```

## API Endpoints

### User APIs (`/api/users`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users/login` | User login |
| POST | `/api/users/register` | User registration |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{userId}` | Get user by ID |
| PUT | `/api/users/{userId}` | Update user profile |
| POST | `/api/users/{userId}/logout` | User logout |

### Admin APIs (`/api/admin`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/{adminId}` | Get admin by ID |
| POST | `/api/admin/register` | Register admin |
| GET | `/api/admin/gym-requests/pending` | View pending gym requests |
| PUT | `/api/admin/gym-requests/{centerId}/approve` | Approve gym center |
| PUT | `/api/admin/gym-requests/{centerId}/decline` | Decline gym center |
| GET | `/api/admin/users` | View all users |
| GET | `/api/admin/gym-centers` | View all gym centers |
| GET | `/api/admin/gym-owners` | View all gym owners |
| PUT | `/api/admin/gym-owners/{ownerId}/verify` | Verify gym owner |
| GET | `/api/admin/reports/monthly` | Get monthly report |

### Gym Customer APIs (`/api/customers`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/customers/register` | Register customer |
| GET | `/api/customers/{customerId}` | Get customer by ID |
| GET | `/api/customers/slots` | View all slots |
| GET | `/api/customers/slots/center/{centerId}` | View slots by center |
| GET | `/api/customers/centers` | View all centers |
| GET | `/api/customers/centers/approved` | View approved centers |
| POST | `/api/customers/{customerId}/bookings` | Book a slot |
| DELETE | `/api/customers/{customerId}/bookings/{bookingId}` | Cancel booking |
| GET | `/api/customers/{customerId}/workout-plan` | View workout plan |
| GET | `/api/customers/{customerId}/bookings` | Get customer bookings |

### Gym Owner APIs (`/api/owners`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/owners/register` | Register gym owner |
| POST | `/api/owners/{ownerId}/centers` | Add gym center |
| GET | `/api/owners/{ownerId}/centers` | Get gym centers by owner |
| PUT | `/api/owners/{ownerId}/centers/{centerId}` | Update center details |
| POST | `/api/owners/{ownerId}/centers/{centerId}/slots` | Add slot |
| DELETE | `/api/owners/{ownerId}/centers/{centerId}/slots/{slotId}` | Remove slot |
| GET | `/api/owners/{ownerId}/centers/{centerId}/slots` | Manage slots |
| GET | `/api/owners/{ownerId}/bookings` | View bookings |
| GET | `/api/owners/{ownerId}/centers/{centerId}/bookings` | View bookings by center |
| POST | `/api/owners/{ownerId}/centers/{centerId}/request-approval` | Request approval |

### Gym Center APIs (`/api/centers`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/centers` | Add gym center |
| GET | `/api/centers` | Get all gym centers |
| GET | `/api/centers/{centerId}` | Get gym center by ID |
| GET | `/api/centers/approved` | Get approved gym centers |
| GET | `/api/centers/pending` | Get pending gym centers |
| GET | `/api/centers/owner/{ownerId}` | Get gym centers by owner |
| PUT | `/api/centers/{centerId}` | Update gym center |
| PUT | `/api/centers/{centerId}/approve` | Approve gym center |
| PUT | `/api/centers/{centerId}/reject` | Reject gym center |
| GET | `/api/centers/{centerId}/slots` | View available slots |

### Booking APIs (`/api/bookings`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/bookings` | Create booking |
| GET | `/api/bookings/{bookingId}` | Get booking by ID |
| PUT | `/api/bookings/{bookingId}/confirm` | Confirm booking |
| DELETE | `/api/bookings/{bookingId}` | Cancel booking |
| GET | `/api/bookings/user/{userId}` | Get bookings by user |
| GET | `/api/bookings/slot/{slotId}` | Get bookings by slot |
| GET | `/api/bookings/center/{centerId}` | Get bookings by center |
| GET | `/api/bookings/date/{date}` | Get bookings by date |

### Slot APIs (`/api/slots`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/slots` | Create slot |
| GET | `/api/slots/{slotId}` | Get slot by ID |
| GET | `/api/slots/center/{centerId}` | Get slots by center |
| GET | `/api/slots/center/{centerId}/available` | Get available slots by center |
| GET | `/api/slots/{slotId}/availability` | Check slot availability |
| PUT | `/api/slots/{slotId}` | Update slot |
| DELETE | `/api/slots/{slotId}` | Delete slot |
| PUT | `/api/slots/{slotId}/lock` | Lock slot |
| PUT | `/api/slots/{slotId}/unlock` | Unlock slot |

## Example API Requests

### Login
```bash
curl -X POST http://localhost:8080/api/users/login \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "password123"}'
```

### Register User
```bash
curl -X POST http://localhost:8080/api/users/register \
  -H "Content-Type: application/json" \
  -d '{"name": "John Doe", "email": "john@example.com", "password": "password123", "role": "CUSTOMER"}'
```

### Book a Slot
```bash
curl -X POST http://localhost:8080/api/customers/1/bookings \
  -H "Content-Type: application/json" \
  -d '{"slotId": 1, "centerId": 1}'
```

### Create Booking with Date
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{"userId": 1, "slotId": 1, "centerId": 1, "bookingDate": "2026-02-01"}'
```

## Health Check

Access the health check at: `http://localhost:8081/healthcheck`

## Admin Console

Access the admin console at: `http://localhost:8081`

## Configuration

Edit `config.yml` to configure:
- Server ports
- Database connection
- Logging settings

## Response Format

All API responses follow this format:
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... }
}
```

## Error Handling

HTTP Status Codes:
- `200` - Success
- `201` - Created
- `400` - Bad Request
- `401` - Unauthorized
- `404` - Not Found
- `409` - Conflict
- `500` - Internal Server Error
