package com.remember.server.repository;

import com.remember.server.entity.RecordEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Repository
public interface RecordRepository extends MongoRepository<RecordEntity, ObjectId> {

}
