package com.example.joblisting.controller;

import com.example.joblisting.repository.SearchRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.example.joblisting.repository.PostRepository;
import com.example.joblisting.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.awt.SystemColor.text;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class PostController {
    @Autowired
    PostRepository repo;
    @Autowired
    SearchRepository srepo;


    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "cluster0");
    }

    @Bean
    public MongoClient mongoClient() {
        String connectionString = "mongodb+srv://root:root@cluster0.gquvdig.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        return MongoClients.create(connectionString);
    }

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/allposts")
    @CrossOrigin
    public List<Post> getAllPosts()
    {
        return repo.findAll();

    }
    @GetMapping("/posts/{text}")
    @CrossOrigin
    public List<Post>search(@PathVariable String text){
        return srepo.findByText(text);
    }
    @PostMapping("/post")
    @CrossOrigin
    public Post addPost(@RequestBody Post post){

        return repo.save(post);
    }
}