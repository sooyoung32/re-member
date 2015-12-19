package com.remember.server.repository;

import com.remember.server.entity.IssueEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by eunhwanpark on 15. 12. 20..
 */
public interface IssuePaginableRepository extends MongoRepository<IssueEntity, ObjectId> {
}
