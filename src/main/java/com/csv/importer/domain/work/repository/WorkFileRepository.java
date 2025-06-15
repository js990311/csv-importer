package com.csv.importer.domain.work.repository;

import com.csv.importer.domain.work.entity.WorkFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkFileRepository extends JpaRepository<WorkFile, Long> {
    @Query("select wf from WorkFile wf")
    Page<WorkFile> findAllBy(Pageable pageable);
}
