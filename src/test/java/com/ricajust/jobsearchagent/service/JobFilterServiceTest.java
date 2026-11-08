package com.ricajust.jobsearchagent.service;

import com.ricajust.jobsearchagent.model.JobOpportunity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JobFilterServiceTest {

    private final JobFilterService jobFilterService = new JobFilterService();

    @Test
    void shouldReturnJobsContainingKeyword() {
        //Arrange
        JobOpportunity job1 = new JobOpportunity(
                "1", "Senior Angular Developer",
                "Company A", "Brazil", null, null,
                "https://example.com/1", "Remotive"
        );

        JobOpportunity job2 = new JobOpportunity(
                "2", "Java Developer",
                "Company B", "Brazil", null, null,
                "https://example.com/2", "Remotive"
        );

        //Act
        List<JobOpportunity> result = jobFilterService.filterByKeyword(List.of(job1, job2), "Angular");

        //Assert
        assertEquals(1, result.size());
        assertEquals("Senior Angular Developer", result.get(0).title());
    }

    @Test
    void shouldIgnoreUppercaseAndLowercase() {
        //Arrange
        JobOpportunity job = new JobOpportunity(
                "1", "SENIOR ANGULAR DEVELOPER",
                "Company A", "Brazil", null, null,
                "https://example.com/1", "Remotive"
        );

        //Act
        List<JobOpportunity> result = jobFilterService.filterByKeyword(List.of(job), "angular");

        //Assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmpityListWhenNoJobsMatch() {
        //Arrange
        List<JobOpportunity> result = jobFilterService.filterByKeyword(List.of(), "angular");

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenJobsIsNull() {
        //Assert
        assertThrows(NullPointerException.class, () -> jobFilterService.filterByKeyword(null, "angular"));
    }
}
