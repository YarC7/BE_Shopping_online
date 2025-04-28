# Back-end Shopping Online (E-commerce Website)

A comprehensive e-commerce backend system built with Spring Boot, providing a robust API for online shopping platforms.

## Overview
This project implements a full-featured e-commerce backend system with RESTful APIs, focusing on user management, product management, shopping cart, and order processing functionalities.

## Technologies Used
- **Framework**: Spring Boot 2.7.11
- **Language**: Java 11
- **Database**: PostgreSQL
- **Cache**: Redis
- **Security**: Spring Security with JWT
- **ORM**: Spring Data JPA
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven
- **Other Tools**: 
  - Lombok
  - Spring Mail
  - Spring Validation
  - Jackson

## Key Features

### 1. User Management
- User registration and authentication
- Role-based access control (User, Seller, Admin)
- Profile management
- Address management
- Email verification

### 2. Product Management
- Product CRUD operations
- Product variants
- Product categories and brands
- Product reviews and ratings
- Product search and filtering
- Product specifications
- Stock management

### 3. Shopping Cart
- Cart management
- Multiple items from different sellers
- Quantity management
- Price calculations
- Cart persistence

### 4. Order Management
- Order processing
- Multiple payment methods
- Order status tracking
- Payment status management
- Order history

### 5. Security Features
- JWT-based authentication
- Role-based authorization
- CORS and CSRF protection
- Secure password handling
- Session management

### 6. Performance Optimization
- Redis caching
- Lazy loading
- Pagination
- Efficient database queries

## Project Structure
```
src/main/java/com/example/salesmanagement/
├── entity/
│   ├── models/          # Entity classes
│   ├── repositories/    # JPA repositories
│   ├── services/        # Business logic
│   ├── controllers/     # REST controllers
│   ├── configs/         # Configuration classes
│   ├── auth/           # Authentication related
│   ├── token/          # JWT token handling
│   ├── redisCache/     # Redis configuration
│   ├── exceptions/     # Custom exceptions
│   └── utilities/      # Utility classes
```

## API Endpoints

### Authentication
- POST `/api/auth/register` - User registration
- POST `/api/auth/login` - User login
- POST `/api/auth/logout` - User logout

### Products
- GET `/api/products` - List all products
- GET `/api/products/{id}` - Get product details
- POST `/api/products` - Create new product
- PUT `/api/products/{id}` - Update product
- DELETE `/api/products/{id}` - Delete product

### Categories
- GET `/api/categories` - List all categories
- POST `/api/categories` - Create category
- PUT `/api/categories/{id}` - Update category
- DELETE `/api/categories/{id}` - Delete category

### Cart
- GET `/api/cart` - Get user's cart
- POST `/api/cart/items` - Add item to cart
- PUT `/api/cart/items/{id}` - Update cart item
- DELETE `/api/cart/items/{id}` - Remove item from cart

### Orders
- GET `/api/orders` - List user's orders
- POST `/api/orders` - Create new order
- GET `/api/orders/{id}` - Get order details
- PUT `/api/orders/{id}` - Update order status

## Getting Started

### Prerequisites
- Java 11
- Maven
- PostgreSQL
- Redis

### Installation
1. Clone the repository
2. Configure database in `application.properties`
3. Configure Redis in `application.properties`
4. Run `mvn install`
5. Start the application with `mvn spring-boot:run`

## Contributing
Feel free to submit issues and enhancement requests.

## License
This project is licensed under the MIT License.




