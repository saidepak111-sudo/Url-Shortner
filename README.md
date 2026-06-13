# 🔗 URL Shortener API

A clean, production-ready URL Shortener built with Spring Boot — converts long URLs into compact short links using Base62 encoding, handles redirects, and tracks click analytics.

---

## ✨ Features

- Convert long URLs into short, shareable links
- Base62 encoding for compact, collision-resistant short codes
- Seamless redirect from short URL to original destination
- Click count tracking per short URL
- Duplicate URL prevention
- Persistent storage with MariaDB via JPA

---

## 🛠️ Tech Stack

| Layer        | Technology              |
|--------------|-------------------------|
| Language     | Java 21                 |
| Framework    | Spring Boot 3           |
| Persistence  | Spring Data JPA         |
| Database     | MariaDB                 |
| Build Tool   | Maven                   |
| Utilities    | Lombok                  |

---

## 📁 Project Structure

```
src/main/java/com/example/urlshortener/
├── controller/       # REST endpoints & redirect handler
├── service/          # Business logic & Base62 encoding
├── repository/       # JPA data access layer
├── model/            # Entity & DTO definitions
├── exception/        # Custom exception handling
└── config/           # App configuration
```

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- MariaDB running locally or remotely

### Clone & Run

```bash
git clone https://github.com/saidepak111-sudo/Url-Shortner.git
cd Url-Shortner
```

Configure your database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/urlshortener
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

Then start the application:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## 📡 API Reference

### Shorten a URL

```http
POST /api/url/shorten
Content-Type: application/json
```

**Request Body:**

```json
{
  "longUrl": "https://www.example.com/some/very/long/path"
}
```

**Response:**

```json
{
  "shortUrl": "http://localhost:8080/b"
}
```

---

### Redirect to Original URL

```http
GET /{shortCode}
```

**Example:**

```http
GET /b
```

Automatically redirects the user to the original long URL and increments the click counter.

---

### Get URL Statistics

```http
GET /api/url/stats/{shortCode}
```

**Response:**

```json
{
  "longUrl": "https://www.example.com/some/very/long/path",
  "clickCount": 15,
  "createdAt": "2026-06-13T16:30:00"
}
```

---

## ⚙️ How It Works

### Shortening a URL

```
User submits long URL
        │
        ▼
URL saved to database
        │
        ▼
Database generates unique ID
        │
        ▼
ID encoded to Base62 string  (e.g., 10015 → "b3X")
        │
        ▼
Short URL returned to user
```

### Redirecting a Short URL

```
User visits short URL  (e.g., /b3X)
        │
        ▼
Short code decoded back to database ID
        │
        ▼
Original URL fetched from database
        │
        ▼
Click count incremented
        │
        ▼
User redirected to original URL
```

---

## 🗄️ Database Schema

### `url` Table

| Column        | Type           | Description                     |
|---------------|----------------|---------------------------------|
| `id`          | BIGINT (PK)    | Auto-generated primary key      |
| `long_url`    | VARCHAR        | The original long URL           |
| `short_url`   | VARCHAR        | The generated short code        |
| `click_count` | BIGINT         | Number of times URL was visited |
| `created_at`  | DATETIME       | Timestamp of creation           |

---

## 🗺️ Roadmap

- [ ] Custom short code aliases
- [ ] URL expiration / TTL support
- [ ] User authentication with JWT
- [ ] Redis caching for high-traffic short codes
- [ ] Rate limiting per IP / user
- [ ] Analytics dashboard (charts, referrer tracking)
- [ ] Docker & Docker Compose deployment
- [ ] QR code generation for short URLs

---

## 💡 Learning Outcomes

Building this project reinforced concepts including:

- Designing REST APIs with Spring Boot
- Working with JPA repositories and relational databases
- Implementing Base62 encoding for ID-to-string mapping
- Handling HTTP redirects programmatically
- Structuring a maintainable Spring Boot codebase
- Custom exception handling and error responses
- Fundamentals of backend system design

---

## 🤝 Contributing

Contributions are welcome! To get started:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add your feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 👤 Author

**Pujala Venkata Sai Deepak**  
GitHub: [@saidepak111-sudo](https://github.com/saidepak111-sudo)

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
