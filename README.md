# LiterAlura: Book Catalog

Welcome to **LiterAlura**, a Java application that allows users to create and explore a book catalog using data from the Gutendex API. This project was developed as part of the "LiterAlura" challenge in the Oracle Next Education program in partnership with Alura.

## Description

**LiterAlura** enables users to interact with a book catalog directly via the console. Through various menu options, users can search for books by title, list all registered books and authors, check which authors were alive in a specific year, and filter books by language. The application consumes data from a public API, stores it in a database, and offers multiple options for querying and displaying data.

## Features

- **Search for book by title**: The user inputs the desired title, and the application retrieves data from the API, stores it, and displays the book's information.
- **List all registered books**: Displays a list of all books saved in the database.
- **List all registered authors**: Displays a list of authors stored in the database.
- **List authors alive in a specific year**: Allows users to check which authors were alive in a given year.
- **List books by language**: Filters and displays books based on the selected language.

## API Data Keys

The API used by **LiterAlura** returns book information using specific keys, such as:

- `"title"`: The book's title.
- `"authors"`: A list of authors associated with the book.
- `"languages"`: Languages in which the book is available.
- `"download_count"`: The number of downloads of the book.

Example:

- Title: *"Pride and Prejudice"*
- Author: *Jane Austen*
- Language: *English (en)*
- Download count: 50,000+

These details are processed and stored for future queries.

## Requirements

- Works with text input via the console.
- Requires Java 17 or later.
- The database used is **Postgres**, but it can be configured for other databases.

## How to Use

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/julianamanzieri/liter_alura.git
    ```

2. **Install dependencies and build the project with Maven**:

    ```bash
    mvn clean install
    ```

3. **Run the project**:

    ```bash
    mvn spring-boot:run
    ```

4. **Interact with the Application**:
   - Upon starting the project, the main menu will be displayed in the console with the following options:
      - **1** - Search for a book by title
      - **2** - List registered books
      - **3** - List registered authors
      - **4** - List authors alive in a specific year
      - **5** - List books by language
      - **0** - Exit the application

## Technologies Used

- **Java**
- **Spring Boot**
- **JPA (Java Persistence API)**
- **Postgres**
- **Gutendex** 
- **HttpClient**
- **Jackson**


## Author

Developed by Juliana Manzieri. Connect with me on [LinkedIn](https://www.linkedin.com/in/julianamanzieri/).

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
