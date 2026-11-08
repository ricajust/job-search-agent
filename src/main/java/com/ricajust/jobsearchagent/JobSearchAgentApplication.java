package com.ricajust.jobsearchagent;

import com.ricajust.jobsearchagent.service.JobSearchService;
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
    CommandLineRunner testJobSearch(JobSearchService jobSearchService) {
        return args -> {
            var jobs = jobSearchService.searchJobs("angular");

            System.out.println("Vagas encontradas: " + jobs.size());

            jobs.forEach(job -> {
                System.out.println("-----------------------");
                System.out.println("Titulo: " + job.title());
                System.out.println("Empresa: " + job.company());
                System.out.println("Localização: " + job.location());
                System.out.println("URL: " + job.url());
            });
        };
    }
}
