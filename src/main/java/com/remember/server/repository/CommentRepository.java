package com.remember.server.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.remember.server.entity.CommentEntity;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, ObjectId>{

}
