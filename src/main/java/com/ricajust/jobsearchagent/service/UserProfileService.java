package com.ricajust.jobsearchagent.service;

import com.ricajust.jobsearchagent.model.UserProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
public class UserProfileService {

    private final UserProfile userProfile;

    public UserProfileService(
            ObjectMapper objectMapper,
            @Value("${app.profile.path}") Resource resource
    ) throws IOException {
        this.userProfile = objectMapper.readValue(resource.getInputStream(), UserProfile.class);
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
