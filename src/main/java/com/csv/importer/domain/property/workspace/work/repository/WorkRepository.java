package com.csv.importer.domain.property.workspace.work.repository;

import com.csv.importer.domain.property.workspace.work.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {
    @Query("select w from Work w join fetch w.workSpace join fetch w.columns c where w.workSpaceId=:workSpaceId")
    List<Work> findWorkWithWorkSpace(@Param("workSpaceId") Long workSpaceId);
}
