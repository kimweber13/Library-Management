# Library Management System

A robust data management solution designed for libraries to manage book transactions and administrative processes effectively.

## Overview

The Library Management System provides a comprehensive solution for managing library operations efficiently. From cataloging books to managing memberships and tracking borrowings, this system offers a centralized platform for librarians to handle their daily tasks.

## Key Features

1. **Display Books**: View the list of all books available in the library.
2. **Delete Book**: Remove a book from the library by its ISBN.
3. **Display Book Copies**: View the list of all book copies available in the library.
4. **Delete Book Copy**: Remove a book copy from the library by its ID.
5. **Display Customers**: View the list of all registered customers.
6. **Delete Customer**: Remove a customer from the library by their ID.

## Getting Started

### Installation

1. Clone the ZIP file to your local machine.
2. Open the project in your preferred IDE.
3. Build and run the application.

### Usage

1. Run the `LibrarySystem` class to start the application.
2. Choose to enter the library system or decline.
3. Access the main dashboard to navigate through different modules such as book catalog, customer management, and book copy management.
4. Perform various actions such as displaying books, deleting books, displaying customers, or more.
5. To see the calculated test coverage via JaCoCo open the index.html file in the htmlReport folder. You can as well generate a new report in your IDE by choice.

### Example Data

Upon starting the application, some example data will be added to the system:

- Books:
    - "Odyssee, Homer" with ISBN "1234567890"
    - "Der Fremde, Albert Camus" with ISBN "0987654321"

- Customers:
    - Thomas Müller with ID 1
    - Marco Reus with ID 2

- Book Copies:
    - Copy of "Odyssee, Homer" with ID 1
    - Copy of "Der Fremde, Albert Camus" with ID 2

## Testing

- **BookTest**: Covers 100% of the methods and lines of code, ensuring the correct execution of the core functionality of deleting a book via ISBN and displaying books.
- **BookCopyTest**: Covers 100% of the methods and lines of code, ensuring the correct execution of the core functionalities of deleting a book copy via ID and displaying all book copies.
- **CustomerTest**: Covers 100% of the methods and lines of code, ensuring the correct execution of the core functionality of deleting a customer via ID and displaying customers.

## Test Coverage with JaCoCo

A test coverage report has already been created and is available to open through the index.html file in the folder htmlReport. 
To be sure an updated test coverage has been calculated please generate a new report through your IDE.
In IntelliJ you could do so by running the project with Coverage and generate a new report after doing so in the new coverage window.

## Support

For assistance or inquiries, please contact Team 50 in Software Engineering.

## Authors

Developed by Team 50, EST / EST-SS24 / project50.

## Version History

- 2.0.0: Current version with updated features and improved functionality.
- 1.0.0: Initial release of the Library Management System.

## License

Usage is free for those permitted.
