package com.survivingcodingbootcamp.blog.controller;


import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class HashTagController {

    private HashtagRepository hashtagRepo;
    private PostRepository postRepo;


    public HashTagController(HashtagRepository hashtagRepo, PostRepository postRepo) {
        this.hashtagRepo = hashtagRepo;
        this.postRepo = postRepo;

    }

    @GetMapping("/all-hashtags")
    public String showAllHashtags(Model model) {
        List<Hashtag> hashtags = (List<Hashtag>) hashtagRepo.findAll();
        if(hashtags.size() > 0) {
            model.addAttribute("hashtags", hashtags);
            return "all-hashtags-template";
        }
        return "redirect:/";
    }

//    @GetMapping("/hashtag/{id}")
//    public String showOneHashtag(Model model) {
//        List<Hashtag> hashtags = (List<Hashtag>) hashtagRepo.findAll();
//        if (hashtags.size() > 0) {
//            model.addAttribute("hashtags", hashtags);
//            return "single-hashtag-template";
//        }
//        return "redirect:/";
//    }


    @GetMapping("/hashtags/{id}")
    public String seeHashtag (Model model, @PathVariable Long id) {
        Optional<Hashtag> optionalHash = hashtagRepo.findById(id);
        model.addAttribute("hashtag", optionalHash.get());
        return "single-hashtag-template";
    }

    @PostMapping("/posts/hashtags/{id}")
    public String addHashtag(Model model, @PathVariable Long id, @RequestParam String hashtag){
        String tempHashtag = checkHashTag(hashtag);
        Optional<Hashtag> optionalHash = hashtagRepo.findByHashtag(tempHashtag);

        Post post = postRepo.findById(id).get();
        if(optionalHash.isPresent()){
            if(!optionalHash.get().getPosts().contains(post)) {
                optionalHash.get().setPost(post);
                hashtagRepo.save(optionalHash.get());
            }
        }
        else {
            Hashtag hash = new Hashtag(tempHashtag, post);
            hashtagRepo.save(hash);
        }
        model.addAttribute("hashtag", tempHashtag);
        return "redirect:/posts/" + id;
    }

    private String checkHashTag(String hashtag) {
        List<String> list = Arrays.asList(hashtag);
        String result = String.join(",", list);
        String tempHashtag = "";
        if (hashtag.contains("#")) {
            tempHashtag = hashtag;
        }
        else {
            tempHashtag = "#" + hashtag;
        }
        return tempHashtag;
    }

}
