package com.cgesgin.personal_blog.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cgesgin.personal_blog.model.Article;
import com.cgesgin.personal_blog.model.repository.IGenericRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Repository
public class ArticleRepository implements IGenericRepository<Article> {

    private static final String FILE_DIRECTORY = System.getProperty("user.dir") + "/data/";
    private static final String FILE_PATH = "articles.json";

    private File file;

    private ObjectMapper mapper;

    List<Article> articles;

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.file = new File(FILE_DIRECTORY + FILE_PATH);
    }

    @Override
    public Boolean save(Article value) {
        try {
            File directory = new File(FILE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            if (file.length() != 0) {
                articles = mapper.readValue(file, new TypeReference<List<Article>>() {
                });
            }

            articles.add(value);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, articles);

            return true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean remove(Article value) {
        try {
            if (file.length() == 0) {
                return false;
            }
            articles = mapper.readValue(file, new TypeReference<List<Article>>() {
            });
            articles.removeIf(article -> article.getId().equals(value.getId()));
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, articles);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(Article value) {
        try {

            if (file.length() == 0) {
                return false;
            }

            articles = mapper.readValue(file, new TypeReference<List<Article>>() {
            });
            for (Article article : articles) {
                if (article.getId().equals(value.getId())) {
                    article.setAuthor(value.getAuthor());
                    article.setContent((value.getContent()));
                    article.setTitle(value.getTitle());
                    article.setUpdated(value.getUpdated());
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, articles);
                }
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Article> getAll() {
        try {
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            articles = mapper.readValue(file, new TypeReference<List<Article>>() {
            });
            return articles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Article get(String id) {
        Article value = new Article();
        try {

            if (file.length() == 0) {
                return value;
            }

            articles = mapper.readValue(file, new TypeReference<List<Article>>() {
            });
            for (Article article : articles) {
                if (article.getId().equals(id)) {
                    value = article;
                }
            }
            return value;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}