package com.remember.server.repository;

import com.remember.server.entity.IssueEntity;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Repository
public interface IssueRepository extends MongoRepository<IssueEntity, ObjectId> {

    List<IssueEntity> findTop5ByOrderByCreatedAtDesc();
    List<IssueEntity> findTop5ByOrderByModifiedAtDesc();
    List<IssueEntity> findByTitleContaining(String title, Pageable pageable);
}
