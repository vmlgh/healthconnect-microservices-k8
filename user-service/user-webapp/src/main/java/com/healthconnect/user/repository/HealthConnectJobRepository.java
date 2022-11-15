package com.healthconnect.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.common.HealthConnectJob;

@Repository
public interface HealthConnectJobRepository extends JpaRepository<HealthConnectJob, Long> {

    @Query(" SELECT j from HealthConnectJob j WHERE j.deleted = false AND (j.emailPending = true OR j.smsPending = true)")
    List<HealthConnectJob> findPendingJobs();
}
