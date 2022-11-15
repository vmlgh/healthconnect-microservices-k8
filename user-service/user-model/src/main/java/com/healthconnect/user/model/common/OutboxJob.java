package com.healthconnect.user.model.common;

import com.healthconnect.user.model.enums.OutboxJobType;

public class OutboxJob {

    private final OutboxJobType jobType;
    private final Object[] jobData;

    public OutboxJob(OutboxJobType outboxJobType, Object... jobData){
        this.jobType = outboxJobType;
        this.jobData = jobData;
    }

    public OutboxJobType getJobType() {
        return jobType;
    }

    public Object[] getJobData() {
        return jobData;
    }
}

