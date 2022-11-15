package com.healthconnect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.core.Role;
import com.healthconnect.user.model.enums.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    /*@Query("SELECT r from Role r where r.deleted = :deleted AND (r.serviceType = :serviceType OR r.serviceType is null)")
    Set<Role> findAllDefaultRolesByServiceType(@Param("serviceType") ServiceType serviceType, @Param("deleted") boolean deleted);*/

    Role findByNameAndDeleted(RoleType roleType, boolean deleted);
}
