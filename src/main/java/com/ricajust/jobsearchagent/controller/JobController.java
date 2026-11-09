
package com.ricajust.jobsearchagent.controller;

import com.ricajust.jobsearchagent.model.JobOpportunity;
import com.ricajust.jobsearchagent.service.JobSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobSearchService jobSearchService;

    public JobController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    @GetMapping("/search")
    public List<JobOpportunity> searchJobs(
            @RequestParam String keyword) {

        return jobSearchService.searchJobs(keyword);
    }
}
