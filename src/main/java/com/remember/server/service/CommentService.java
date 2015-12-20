package com.remember.server.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remember.server.entity.CommentEntity;
import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.model.NewCommentModel;
import com.remember.server.repository.CommentRepository;
import com.remember.server.repository.IssueRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private ModelMapper modelMapper;

	public IssueEntity createNewComment(ObjectId issueOid,NewCommentModel commentModel, UserEntity userEntity) {
		CommentEntity commentEntity = modelMapper.map(commentModel,	CommentEntity.class);
		commentEntity.setCreator(userEntity);
		commentRepository.save(commentEntity);
		
		IssueEntity issueEntity = issueRepository.findOne(issueOid);
		List<CommentEntity> comments = issueEntity.getComments();
		if (comments == null) {
			comments = new ArrayList<CommentEntity>();
		}
		comments.add(commentEntity);
		issueEntity.setCommentSize(comments.size());
		issueEntity.setComments(comments);
		issueRepository.save(issueEntity);
		
		return issueEntity;

	}
	
}
