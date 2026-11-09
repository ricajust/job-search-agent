
package com.ricajust.jobsearchagent.service;

import com.ricajust.jobsearchagent.model.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileServiceTest {

    private UserProfileService userProfileService;

    @BeforeEach
    void setUp() throws Exception {
        userProfileService = new UserProfileService(
                new ObjectMapper(),
                new ClassPathResource("profiles/default-profile.json")
        );
    }

    @Test
    void shouldLoadUserProfile() {
        assertNotNull(userProfileService.getUserProfile());
    }

    @Test
    void shouldReturnCorrectProfessionalRole() {
        UserProfile profile = userProfileService.getUserProfile();

        assertEquals(
                "Senior Front-end Developer",
                profile.role()
        );
    }

    @Test
    void shouldReturnCorrectExperienceYears() {
        UserProfile profile = userProfileService.getUserProfile();

        assertEquals(10, profile.experienceYears());
    }

    @Test
    void shouldReturnCorrectTechnologies() {
        UserProfile profile = userProfileService.getUserProfile();

        assertTrue(profile.technologies().contains("Angular"));
        assertTrue(profile.technologies().contains("TypeScript"));
        assertTrue(profile.technologies().contains("Java"));
    }

    @Test
    void shouldReturnCorrectWorkModels() {
        UserProfile profile = userProfileService.getUserProfile();

        assertEquals(2, profile.workModels().size());
        assertTrue(profile.workModels().contains("Remote"));
        assertTrue(profile.workModels().contains("Hybrid"));
    }
}
