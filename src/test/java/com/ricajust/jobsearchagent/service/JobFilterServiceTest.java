
package com.ricajust.jobsearchagent.service;

import com.ricajust.jobsearchagent.model.JobOpportunity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobFilterServiceTest {

    private final JobFilterService jobFilterService =
            new JobFilterService();

    @Test
    void shouldPrioritizeTitleMatchWhenScoresAreEqual() {
        // Arrange
        JobOpportunity backendDeveloper = new JobOpportunity(
                "1", "Senior Back-end Developer",
                "Company A", "Brazil",
                null, "Experience with Angular",
                "https://example.com/1", "Remotive"
        );

        JobOpportunity angularDeveloper = new JobOpportunity(
                "2", "Senior Angular Developer",
                "Company B", "Brazil",
                null, "Experience with Java",
                "https://example.com/2", "Remotive"
        );

        // Act
        List<JobOpportunity> result =
                jobFilterService.rankByKeyword(
                        List.of(backendDeveloper, angularDeveloper),
                        "Angular"
                );

        // Assert
        assertEquals(2, result.size());

        assertEquals(
                "Senior Angular Developer",
                result.get(0).title()
        );

        assertEquals(
                "Senior Back-end Developer",
                result.get(1).title()
        );
    }

    @Test
    void shouldRankJobsWithKeywordInTitleAndDescriptionFirst() {
        // Arrange
        JobOpportunity angularDeveloper = new JobOpportunity(
                "1", "Senior Angular Developer",
                "Company A", "Brazil",
                null, "Experience with Angular",
                "https://example.com/1", "Remotive"
        );

        JobOpportunity backendDeveloper = new JobOpportunity(
                "2", "Senior Back-end Developer",
                "Company B", "Brazil",
                null, "Experience with Angular",
                "https://example.com/2", "Remotive"
        );

        // Act
        List<JobOpportunity> result =
                jobFilterService.rankByKeyword(
                        List.of(backendDeveloper, angularDeveloper),
                        "Angular"
                );

        // Assert
        assertEquals(2, result.size());
        assertEquals("Senior Angular Developer",
                result.get(0).title());
    }

    @Test
    void shouldRankJobsWithKeywordOnlyInDescription() {
        // Arrange
        JobOpportunity job = new JobOpportunity(
                "1", "Senior Back-end Developer",
                "Company A", "Brazil",
                null, "Experience with Angular",
                "https://example.com/1", "Remotive"
        );

        // Act
        List<JobOpportunity> result =
                jobFilterService.rankByKeyword(
                        List.of(job), "Angular"
                );

        // Assert
        assertEquals(1, result.size());
        assertEquals("Senior Back-end Developer",
                result.get(0).title());
    }

    @Test
    void shouldRemoveJobsWithoutKeyword() {
        // Arrange
        JobOpportunity job = new JobOpportunity(
                "1", "Java Developer",
                "Company A", "Brazil",
                null, "Experience with Spring Boot",
                "https://example.com/1", "Remotive"
        );

        // Act
        List<JobOpportunity> result =
                jobFilterService.rankByKeyword(
                        List.of(job), "Angular"
                );

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenNoJobsAreProvided() {
        // Arrange
        List<JobOpportunity> jobs = List.of();

        // Act
        List<JobOpportunity> result =
                jobFilterService.rankByKeyword(jobs, "Angular");

        // Assert
        assertTrue(result.isEmpty());
    }
}
