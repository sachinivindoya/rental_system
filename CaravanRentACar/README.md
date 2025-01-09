# Caravan Rent-a-Car System

A **Caravan Rent-a-Car System** is a JavaFX-based desktop application developed to manage car rentals. This system interacts with a MySQL database using JDBC (Java Database Connectivity) for CRUD operations. Below is the comprehensive README file explaining how to implement, set up, and run the project.

---

## **Project Overview**

The system includes the following features:
1. Customer Management: Add, update, view, and delete customer records.
2. Vehicle Management: Manage vehicle inventory with details like type, price, and availability.
3. Booking Management: Record bookings and link them to customers and vehicles.
4. Booking Details: Store detailed information about each booking.

### **Tech Stack**
- **Frontend**: JavaFX (FXML)
- **Backend**: Java (JDK 11 or higher)
- **Database**: MySQL
- **Build Tool**: Gradle or Maven (optional)
- **IDE**: IntelliJ IDEA

---

## **Project Structure**

The project is organized as follows:

```
CaravanRentACar/
├── src/
│   ├── main/
│   │   ├── App.java                          # Main Application Class
│   │   ├── controller/
│   │   │   ├── MainController.java           # Main Controller
│   │   ├── dao/
│   │   │   ├── CrudDAO.java                  # Generic DAO Interface
│   │   │   ├── CustomerDAO.java              # DAO Implementation for Customer
│   │   │   ├── VehicleDAO.java               # DAO Implementation for Vehicle
│   │   │   ├── BookingDAO.java               # DAO Implementation for Booking
│   │   │   ├── BookingDetailDAO.java         # DAO Implementation for Booking Details
│   │   ├── models/
│   │   │   ├── Customer.java                 # Customer Model Class
│   │   │   ├── Vehicle.java                  # Vehicle Model Class
│   │   │   ├── Booking.java                  # Booking Model Class
│   │   │   ├── BookingDetail.java            # BookingDetail Model Class
│   │   ├── database/
│   │   │   ├── DatabaseConnection.java       # Database Connection Logic
│   │   ├── util/
│   │       ├── SceneManager.java             # Utility for Scene Management
│   ├── resources/
│   │   ├── view/
│   │   │   ├── main.fxml                     # FXML for Main UI
│   │   ├── styles.css                        # CSS for Styling
├── lib/                                      # JavaFX SDK JAR Files
├── README.md                                 # Documentation (This file)
├── build.gradle or pom.xml                   # Build Configuration
```

---

## **Setup Instructions**

### **1. Prerequisites**
- **Java Development Kit (JDK)**: Version 11 or higher.
- **JavaFX SDK**: Download from [Gluon](https://gluonhq.com/products/javafx/).
- **MySQL Server**: Version 8 or higher.
- **IntelliJ IDEA**: Community or Ultimate edition.

### **2. Database Configuration**

1. Install and run MySQL Server.
2. Create a database named `caravan_rent` using the following command:
   ```sql
   CREATE DATABASE caravan_rent;
   ```
3. Use the provided SQL script to create tables and populate sample data.

**SQL Script:**
```sql
DROP DATABASE IF EXISTS caravan_rent;
CREATE DATABASE IF NOT EXISTS caravan_rent;
USE caravan_rent;

CREATE TABLE customer (
    cust_id VARCHAR(6) NOT NULL,
    name VARCHAR(30) NOT NULL,
    dob DATE,
    salary DECIMAL(10,2) NOT NULL,
    address TEXT,
    city VARCHAR(20),
    province VARCHAR(20),
    postal_code VARCHAR(9),
    PRIMARY KEY (cust_id)
);

CREATE TABLE vehicle (
    reg_id VARCHAR(6) NOT NULL,
    vehicle_type VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    price_per_day DECIMAL(10,2) NOT NULL,
    price_per_excess_mileage DECIMAL(6,2) NOT NULL,
    vehicle_qty_on_hand INT(5) NOT NULL,
    PRIMARY KEY (reg_id)
);

CREATE TABLE booking (
    booking_id VARCHAR(6) NOT NULL,
    date DATE NOT NULL,
    customer_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (customer_id) REFERENCES customer(cust_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE booking_detail (
    booking_id VARCHAR(6) NOT NULL,
    reg_id VARCHAR(6) NOT NULL,
    vehicle_qty INT(11) NOT NULL,
    discount INT(2),
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (reg_id) REFERENCES vehicle(reg_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);
```

4. Update the database credentials in `DatabaseConnection.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/caravan_rent";
   private static final String USER = "root";         // Replace with your username
   private static final String PASSWORD = "password"; // Replace with your password
   ```

---

### **3. Add JavaFX to Project**

1. **Download JavaFX SDK**:
    - Download from [Gluon](https://gluonhq.com/products/javafx/).
    - Extract it to a folder, e.g., `C:\javafx-sdk-23`.

2. **Configure IntelliJ IDEA**:
    - Go to `File > Project Structure > Libraries` and add all `.jar` files from the `lib` directory of the JavaFX SDK.

3. **Set VM Options** in Run Configurations:
   ```
   --module-path "C:/javafx-sdk-23/lib" --add-modules javafx.controls,javafx.fxml
   ```

---

## **How It Works**

### **1. Application Flow**
1. **Main Class (`App.java`)**:
    - Launches the JavaFX application and loads the `main.fxml` file.
    - Sets the primary stage.

2. **FXML File (`main.fxml`)**:
    - Defines the structure and layout of the user interface.

3. **Controller (`MainController.java`)**:
    - Handles user interactions, such as button clicks and table views.

4. **Database Access**:
    - DAOs (`CustomerDAO`, `VehicleDAO`, etc.) handle all interactions with the MySQL database using JDBC.

5. **Scene Management**:
    - `SceneManager` utility dynamically switches between scenes (e.g., customer view, booking view).

---

### **2. JDBC Overview**

**JDBC (Java Database Connectivity)** is used to interact with the MySQL database:
- Establish a connection using `DriverManager`.
- Execute SQL queries using `Statement` or `PreparedStatement`.
- Retrieve results using `ResultSet`.

**Example (Connecting to Database):**
```java
try (Connection connection = DatabaseConnection.getConnection()) {
    String sql = "SELECT * FROM customer";
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();

    while (resultSet.next()) {
        System.out.println("Customer Name: " + resultSet.getString("name"));
    }
} catch (SQLException e) {
    e.printStackTrace();
}
```

---

## **How to Run**

1. **Build the Project**:
    - In IntelliJ IDEA, click `Build > Build Project`.

2. **Run the Application**:
    - Go to `Run > Edit Configurations`.
    - Ensure the **Main Class** is set to `App`.
    - Add VM options:
      ```
      --module-path "C:/javafx-sdk-23/lib" --add-modules javafx.controls,javafx.fxml
      ```
    - Click **Run**.

3. **Test the Application**:
    - Verify functionality for managing customers, vehicles, and bookings.

---

## **Features**

- **Customer Management**: Add, view, update, delete customers.
- **Vehicle Management**: Manage car inventory and availability.
- **Booking System**: Track bookings and associated details.

---

## **Troubleshooting**

### **1. Common Errors**
- **`java.lang.module.FindException: Module javafx.controls not found`**:
    - Ensure `--module-path` is correct in VM options.
- **Database Connection Issues**:
    - Verify MySQL server is running and credentials are correct in `DatabaseConnection.java`.

### **2. Debugging**
- Use IntelliJ’s debugger to step through the application.
- Add print statements to log database queries and exceptions.

