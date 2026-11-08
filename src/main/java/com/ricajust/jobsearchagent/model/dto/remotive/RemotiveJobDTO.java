package com.ricajust.jobsearchagent.model.dto.remotive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RemotiveJobDTO(

        long id,
        String url,
        String title,
        @JsonProperty("company_name") String companyName,
        String category,
        @JsonProperty("job_type") String jobType,
        @JsonProperty("publication_date") String publicationDate,
        @JsonProperty("candidate_required_location") String candidateRequiredLocation,
        String salary,
        String description
) {
}
