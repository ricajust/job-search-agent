package com.ricajust.jobsearchagent.model.dto.remotive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RemotiveResponse(
        @JsonProperty("job-count") int jobCount,
        List<RemotiveJobDTO> jobs
) {
}
