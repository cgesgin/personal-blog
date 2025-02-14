package com.cgesgin.personal_blog.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cgesgin.personal_blog.model.Article;
import com.cgesgin.personal_blog.model.repository.IGenericRepository;




@Controller
public class ArticleController {

    @Autowired
    private IGenericRepository<Article> repository;
    
    @GetMapping({"/", "/home"})
    public String getAll(Model model) {

        List<Article> articles = repository.getAll();

        model.addAttribute("articles", articles);
        return "article/articles";
    }
    
    @GetMapping("/new")
    public String createArtice(Model model) {

        model.addAttribute("article", new Article());

        return "article/article-new";
    }

    @PostMapping("/new")
    public String createArtice(Article article) {

        String id=UUID.randomUUID().toString();

        article.setId(id);
        article.setCreated(LocalDateTime.now());

        repository.save(article);

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable String id, Model model) {
        Article article = repository.get(id);
        if (article != null) {
            model.addAttribute("article", article);
            return "article/article-edit";
        }
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, Article updatedArticle) {
        Article existingArticle = repository.get(id);
    
        if (existingArticle != null) {
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setContent(updatedArticle.getContent());
            existingArticle.setAuthor(updatedArticle.getAuthor());
            existingArticle.setUpdated(LocalDateTime.now());
            
            repository.update(existingArticle);
        }
    
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String getAllForAdmin(Model model) {
        List<Article> articles = repository.getAll();

        model.addAttribute("articles", articles);
        return "article/admin-page";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable String id) {
        Article article = repository.get(id);

        if (article != null){
            repository.remove(article);
        }

        return "redirect:/admin";
    }
    
    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable String id,Model model) {
        Article article = repository.get(id);

        if (article != null){

            model.addAttribute("article", article);
            return "article/article";
        }

        return "redirect:/";
    }

}
