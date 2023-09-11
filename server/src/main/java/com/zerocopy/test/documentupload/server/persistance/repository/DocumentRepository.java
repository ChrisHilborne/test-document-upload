package com.zerocopy.test.documentupload.server.persistance.repository;

import com.zerocopy.test.documentupload.server.persistance.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
