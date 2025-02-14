package com.cgesgin.personal_blog.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Article{
    
    private String id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;

}
