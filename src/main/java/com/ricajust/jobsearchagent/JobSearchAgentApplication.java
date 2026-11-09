package com.ricajust.jobsearchagent;

import com.ricajust.jobsearchagent.service.JobSearchService;
import com.ricajust.jobsearchagent.service.UserProfileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JobSearchAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobSearchAgentApplication.class, args);

    }

    @Bean
    CommandLineRunner testUserProfile(
            UserProfileService userProfileService) {

        return args -> {
            System.out.println(
                    userProfileService.getUserProfile()
            );
        };
    }

}
