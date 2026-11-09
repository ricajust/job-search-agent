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
                .filter(job -> job.title() != null && job.title()
                        .toLowerCase(Locale.ROOT)
                        .contains(searchTerm)
                )
                .toList();
    }

    public List<JobOpportunity> rankByKeyword(
            List<JobOpportunity> jobs,
            String keyword
    ) {
        Objects.requireNonNull(jobs, "jobs não pode ser null");
        Objects.requireNonNull(keyword, "keyword não pode ser null");

        String searchTerm = keyword.toLowerCase(Locale.ROOT);

        return jobs.stream()
                .filter(job -> {
                    String title = job.title() == null ? "" : job.title().toLowerCase(Locale.ROOT);
                    String description = job.description() == null ? "" : job.description().toLowerCase(Locale.ROOT);

                    return title.contains(searchTerm) || description.contains(searchTerm);
                })
                .sorted((job1, job2) -> {
                    int scoreComparison = Integer.compare(
                            calculateScore(job2, searchTerm),
                            calculateScore(job1, searchTerm)
                    );

                    if (scoreComparison != 0) {
                        return scoreComparison;
                    }

                    return Boolean.compare(
                            hasTitleMatch(job2, searchTerm),
                            hasTitleMatch(job1, searchTerm)
                    );
                })
                .toList();
    }

    private int calculateScore(JobOpportunity job, String searchTerm) {
        int score = 0;

        if (hasTitleMatch(job,searchTerm)) {
            score++;
        }

        if (job.description() != null && job.description().toLowerCase(Locale.ROOT).contains(searchTerm)) {
            score++;
        }

        return score;
    }

    private boolean hasTitleMatch(
            JobOpportunity job,
            String searchTerm
    ) {
        return job.title() != null && job.title().toLowerCase(Locale.ROOT).contains(searchTerm);
    }

}
