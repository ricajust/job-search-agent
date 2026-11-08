package com.ricajust.jobsearchagent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobOpportunity(
        String id,
        String title,
        String company,
        String location,
        String salary,
        String description,
        String url,
        String source
) {
}
