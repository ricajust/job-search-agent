package com.ricajust.jobsearchagent.service;

import com.ricajust.jobsearchagent.model.JobOpportunity;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class JobFilterService {

    public List<JobOpportunity> filterByKeyword(
            List<JobOpportunity> jobs,
            String keyword
    ) {
        Objects.requireNonNull(jobs, "jobs não pode ser null");
        Objects.requireNonNull(keyword, "keyword não pode ser null");

        String searchTerm = keyword.toLowerCase(Locale.ROOT);

        return jobs.stream()
                .filter(job ->
                        job.title() != null && job.title()
                                .toLowerCase(Locale.ROOT)
                                .contains(searchTerm)
                )
                .toList();
    }
}
