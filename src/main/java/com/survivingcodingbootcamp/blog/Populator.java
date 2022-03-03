package com.survivingcodingbootcamp.blog;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator implements CommandLineRunner {
    @Autowired
    private TopicRepository topicRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private HashtagRepository hashtagRepo;

//    public Populator(TopicRepository topicRepo, PostRepository postRepo) {
//
//        this.topicRepo = topicRepo;
//        this.postRepo = postRepo;
//    }

    @Override
    public void run(String... args) throws Exception {

        Topic topic1 = new Topic("Learning TDD");
        topicRepo.save(topic1);
        Topic topic2 = new Topic("Battling Imposter Syndrome");
        topicRepo.save(topic2);
        Topic topic3 = new Topic("Introductory Java");
        topicRepo.save(topic3);
        Topic topic4 = new Topic("Object Oriented Programming and You");
        topicRepo.save(topic4);
        Topic topic5 = new Topic("Fun Quotes from CritRole");
        topicRepo.save(topic5);

        Post post1 = new Post("TDD For Fun and Profit", topic1, "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim" +
                " veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis " +
                "aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id " +
                "est laborum.", "Yasha Nydoorin");
        postRepo.save(post1);
        Post post2 = new Post("Test the Fear Away", topic1, "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim" +
                " veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis " +
                "aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id " +
                "est laborum.", "Taryon Darrington");
        postRepo.save(post2);
        Post post3 = new Post("Unit Tests and You", topic1, "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim" +
                " veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis " +
                "aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id " +
                "est laborum.", "Caduceus Clay");
        postRepo.save(post3);
        Post post4 = new Post("Quotes from Grog", topic5, "I have an Intelligence of six, I know what I'm doing." +
                "\nUh, Grog isn’t here right now, this is Phillip." + "\n" + "I go into a cow rage and start chewing faster.", "Grog Strongjaw");
        postRepo.save(post4);
        Post post5 = new Post("Wisdom from Percy", topic5, "Life needs things to live", "Percival Fredrickstein von Musel Klossowski de Rolo III");
        postRepo.save(post5);



        Hashtag wow = new Hashtag("#wow", post2);
        hashtagRepo.save(wow);

        Hashtag neat = new Hashtag("#neat", post1);
        hashtagRepo.save(neat);

        Hashtag cool = new Hashtag("#cool", post3, post2);
        hashtagRepo.save(cool);

        Hashtag criticalRoleQuotes = new Hashtag("#CriticalRoleQuotes", post4, post5);
        hashtagRepo.save(criticalRoleQuotes);


    }

}
