# ブログアプリ「Yours Blog」
Spring Bootで開発したブログアプリ
使用ツール
- template: Thymleaf
- DB: PostgreSQL

## ディレクトリ構成
```
.
├── README.md
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── blog
│   │   │               ├── BlogApplication.java
│   │   │               ├── WebSecurityConfig.java
│   │   │               ├── controller
│   │   │               │   ├── BlogController.java
│   │   │               │   ├── LoginController.java
│   │   │               │   └── RegisterUserController.java
│   │   │               ├── model
│   │   │               │   ├── Blog.java
│   │   │               │   ├── User.java
│   │   │               │   └── UserDto.java
│   │   │               ├── repository
│   │   │               │   ├── BlogRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── security
│   │   │               │   └── UserPrincipal.java
│   │   │               └── service
│   │   │                   ├── BlogService.java
│   │   │                   ├── UserPrincipal.java
│   │   │                   └── UserService.java
│   │   └── resources
│   │       ├── META-INF
│   │       │   └── additional-spring-configuration-metadata.json
│   │       ├── application.properties
│   │       ├── messages.properties
│   │       ├── static
│   │       │   ├── css
│   │       │   │   └── signup.css
│   │       │   └── js
│   │       ├── templates
│   │       │   ├── blog
│   │       │   │   ├── blogDetail.html
│   │       │   │   ├── blogList.html
│   │       │   │   ├── complete.html
│   │       │   │   ├── confirm.html
│   │       │   │   └── input.html
│   │       │   ├── index.html
│   │       │   └── user
│   │       │       ├── complete.html
│   │       │       ├── confirm.html
│   │       │       ├── login.html
│   │       │       └── signup.html
│   │       └── validation.properties
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── blog
│                       └── BlogApplicationTests.java
└── target(target下は省略)
```

## Spring Boot アノテーション
(参考)  
https://qiita.com/ist-a-ku/items/c20d67140402634cd5db

## バリデーション
(参考)  
https://b1san-blog.com/post/spring/spring-validation/

## Bootstrap5チートシート
(参考)  
https://bootstrap-cheatsheet.themeselection.com/

## その他
### Markdown記法
(参考)  
https://qiita.com/tbpgr/items/989c6badefff69377da7