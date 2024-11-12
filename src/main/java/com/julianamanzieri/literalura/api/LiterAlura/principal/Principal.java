package com.julianamanzieri.literalura.api.LiterAlura.principal;

import com.julianamanzieri.literalura.api.LiterAlura.model.*;
import com.julianamanzieri.literalura.api.LiterAlura.repository.AuthorRepository;
import com.julianamanzieri.literalura.api.LiterAlura.repository.BookRepository;
import com.julianamanzieri.literalura.api.LiterAlura.service.ConsumptionApi;
import com.julianamanzieri.literalura.api.LiterAlura.service.ConvertData;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner scanner = new Scanner(System.in);
    ConsumptionApi consumptionApi = new ConsumptionApi();
    private final String URL_API = "http://gutendex.com/books/?search=";
    ConvertData convertData = new ConvertData();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Book> books;
    private List<Author> authors;

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void displayMenu() {
        var option = -1;
        while(option != 0) {
            var menu = """
                    1 - Search for book by title
                    2 - List registered books
                    3 - List registered authors
                    4 - List living authors in a given year
                    5 - List books in a given language
                    
                    0 - Exit
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchForBookByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsInAGivenYear();
                    break;
                case 5:
                    listBooksInAGivenLanguage();
                    break;
                case 0:
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private DataResults getDataResults() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        var json = consumptionApi.getData(URL_API + title.replace(" ", "%20"));
        DataResults data = convertData.getData(json, DataResults.class);
        return data;
    }

    private void searchForBookByTitle() {
        DataResults dataResults = getDataResults();
        if (dataResults != null && dataResults.results() != null && !dataResults.results().isEmpty()) {
            List<DataBook> dataBooks = dataResults.results();

            for (DataBook dataBook : dataBooks) {
                List<Author> authors = dataBook.authors().stream()
                        .map(da -> {
                            Author author = authorRepository.findByName(da.name());
                            if (author == null) {
                                author = new Author(da.name(), da.birthYear(), da.deathYear());
                                authorRepository.save(author);
                            }
                            return author;
                        })
                        .collect(Collectors.toList());

                Optional<Book> optionalBook = bookRepository.findByTitle(dataBook.title());

                if (optionalBook.isPresent()) {
                    System.out.println("The book '" + optionalBook.get().getTitle() + "' already exists in the database!");
                } else {
                    Book book = new Book();
                    book.setTitle(dataBook.title());
                    book.setLanguage(dataBook.languages().get(0));
                    book.setNumberOfDownloads(dataBook.numberOfDownloads());

                    if (!authors.isEmpty()) {
                        book.setAuthor(authors.get(0));
                    }

                    bookRepository.save(book);

                    System.out.println("----- BOOK -----");
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor().getName());
                    System.out.println("Language: " + book.getLanguage());
                    System.out.println("Number of downloads: " + book.getNumberOfDownloads());
                    System.out.println("-----------------");
                }
            }
        } else {
            System.out.println("No results found for the given title!");
        }
    }


    private void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No books registered.");
        } else {
            System.out.println("Books registered:");
            books.forEach(System.out::println);
        }
    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No authors registered!");
        } else {
            System.out.println("Registered authors:");
            authors.forEach(System.out::println);
        }
    }

    private void listLivingAuthorsInAGivenYear() {
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        List<Author> authors = authorRepository.findAuthorsAliveInYear(year);

        if (authors.isEmpty()) {
            System.out.println("No authors were alive in " + year);
        } else {
            System.out.println("Authors alive in " + year + ":");
            authors.forEach(System.out::println);
        }
    }

    private void listBooksInAGivenLanguage() {
        Map<String, String> languages = Map.of(
                "es", "Spanish",
                "en", "English",
                "fr", "French",
                "pt", "Portuguese"
        );
        System.out.println("Enter the language to search: ");
        languages.forEach((code, name) -> System.out.println(code + "- " + name));
        String languageCode = scanner.nextLine().toLowerCase();
        if (!languages.containsKey(languageCode)) {
            System.out.println("Invalid language code. Please try again!");
            return;
        }

        var books = bookRepository.findByLanguages(languageCode);
        if (books.isEmpty()) {
            System.out.println("No books found in the specified language!");
        } else {
            books.forEach(System.out::println);
        }
    }
}

