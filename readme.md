## 🎯 Overview
This project is a simple and functional personal blog web application where users can write and publish articles on various topics. For more information and details on how to contribute, you can check this link:

    https://roadmap.sh/projects/personal-blog

## 🔍 Features

### Pages that anyone can access

- ✅ **/home**: This page will display the list of articles published on the blog.
- ✅ **/article/{id}**: This page will display the content of the article along with the date of publication.

### Admin Section — Pages that only you can access to publish, edit, or delete articles

- ✅ **/admin**: This page will display the list of articles published on the blog along with the option to add a new article, edit an existing article, or delete an article.
- ✅ **/new**: This page will contain a form to add a new article. The form will have fields like title, content, and date of publication.
- ✅ **/edit/{id}**: This page will contain a form to edit an existing article. The form will have fields like title, content, and date of publication.

## 🛠️ Technical Details

- Java 17, Spring Framework 3.4.2 for the backend
- HTML, CSS, Thymeleaf for the frontend
- JSON file is used as the database
- Basic Authentication is implemented for secure access to the admin section

## 🏃 How to Run
To get started with the project, follow these steps:

- Clone the repository:

        git clone https://github.com/cgesgin/personal-blog.git

- Navigate to the project directory:

        cd unit-converter

- Build the project:

        mvn clean install

- Run the application:

        mvn exec:java

- Visit the application in your browser:

Open http://localhost:8080 in your browser to use the personal blog.
