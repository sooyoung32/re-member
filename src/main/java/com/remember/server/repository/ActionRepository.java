package com.remember.server.repository;

import com.remember.server.entity.ActionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Repository
public interface ActionRepository extends MongoRepository<ActionEntity, ObjectId> {

}
