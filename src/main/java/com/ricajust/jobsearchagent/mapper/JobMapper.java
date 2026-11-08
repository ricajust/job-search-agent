
package com.ricajust.jobsearchagent.mapper;

import com.ricajust.jobsearchagent.model.JobOpportunity;
import com.ricajust.jobsearchagent.model.dto.remotive.RemotiveJobDTO;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobOpportunity toJobOpportunity(RemotiveJobDTO job) {

        return new JobOpportunity(
                String.valueOf(job.id()),
                job.title(),
                job.companyName(),
                job.candidateRequiredLocation(),
                job.salary(),
                job.description(),
                job.url(),
                "Remotive"
        );
    }
}
