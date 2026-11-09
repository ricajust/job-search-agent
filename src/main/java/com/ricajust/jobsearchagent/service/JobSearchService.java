
package com.ricajust.jobsearchagent.service;

import com.ricajust.jobsearchagent.mapper.JobMapper;
import com.ricajust.jobsearchagent.model.JobOpportunity;
import com.ricajust.jobsearchagent.model.dto.remotive.RemotiveResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class JobSearchService {

    private final RestClient restClient;
    private final JobMapper jobMapper;
    private final JobFilterService jobFilterService;

    public JobSearchService(
            @Value("${remotive.base-url}") String baseUrl,
            JobMapper jobMapper,
            JobFilterService jobFilterService
    ) {

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        this.jobMapper = jobMapper;
        this.jobFilterService = jobFilterService;
    }

    public List<JobOpportunity> searchJobs(String keyword) {

        RemotiveResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/remote-jobs")
                        .queryParam("search", keyword)
                        .queryParam("limit", 10)
                        .build())
                .retrieve()
                .body(RemotiveResponse.class);

        if (response == null || response.jobs() == null) {
            return List.of();
        }

        List<JobOpportunity> opportunities = response.jobs()
                .stream()
                .map(jobMapper::toJobOpportunity)
                .toList();

        return jobFilterService.rankByKeyword(opportunities, keyword);

    }
}
