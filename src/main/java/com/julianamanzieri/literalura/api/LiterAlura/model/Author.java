package com.julianamanzieri.literalura.api.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Author(DataAuthor dataAuthor) {
        this.name = dataAuthor.name();
        this.birthYear = dataAuthor.birthYear();
        this.deathYear = dataAuthor.deathYear();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearBirth() {
        return birthYear;
    }

    public void setYearBirth(Integer yearBirth) {
        this.birthYear = yearBirth;
    }

    public Integer getYearDeath() {
        return deathYear;
    }

    public void setYearDeath(Integer yearDeath) {
        this.deathYear = yearDeath;
    }

    @Override
    public String toString() {
        return "Name: '" + name + '\'' +
                ", Year birth: " + birthYear +
                ", Year death:" + deathYear;
    }
}
