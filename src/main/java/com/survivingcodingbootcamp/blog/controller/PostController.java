package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class PostController {
    private PostRepository postRepo;
    private TopicRepository topicRepo;

    public PostController(PostRepository postRepo, TopicRepository topicRepo) {
        this.postRepo = postRepo;
        this.topicRepo = topicRepo;
    }


    @GetMapping("/posts/{id}")
    public String shoSinglePost(Model model, @PathVariable Long id) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }



    @RequestMapping("/single-post")
    public String showAllPosts(Model model) {
        model.addAttribute("post", postRepo.findAll());
        return "single-post-template";
    }


    @RequestMapping("/posts/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }

    @PostMapping("/posts/{id}")
    public String addNewPost(Model model, @PathVariable Long id, @RequestParam String title, @RequestParam String content, @RequestParam String author) {
        Topic topic = topicRepo.findById(id).get();
        Post newPostZ = new Post(title, topic, content, author);
        postRepo.save(newPostZ);

        return "redirect:/topics/" + id;
    }

}
