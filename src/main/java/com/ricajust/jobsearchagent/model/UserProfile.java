package com.ricajust.jobsearchagent.model;

import java.util.List;

public record UserProfile(
        String role,
        List<String> technologies,
        int experienceYears,
        String location,
        List<String> workModels,
        List<String> preferredRoles
) {
}
