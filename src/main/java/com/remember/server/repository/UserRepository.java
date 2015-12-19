package com.remember.server.repository;

import com.remember.server.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

	UserEntity findOneByEmail(String email);

}
