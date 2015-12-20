package com.remember.server.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import springfox.documentation.annotations.ApiIgnore;

import com.remember.server.entity.ActionEntity;
import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.RecordEntity;
import com.remember.server.entity.UserEntity;
import com.remember.server.model.NewActionModel;
import com.remember.server.model.NewManualRecordModel;
import com.remember.server.model.SessionModel;
import com.remember.server.repository.ActionRepository;
import com.remember.server.repository.IssueRepository;

@Slf4j
@Service
public class ActionService {
	
	@Autowired
	private ActionRepository actionRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
    @Autowired
    private ModelMapper modelMapper;

	public IssueEntity createNewAction(ObjectId issueOid, NewActionModel actionModel) {
		
		ActionEntity actionEntity = modelMapper.map(actionModel, ActionEntity.class);
		actionRepository.save(actionEntity);
		log.debug("issueOid===>{}", issueOid);
		IssueEntity issueEntity = issueRepository.findOne(issueOid);
		List<ActionEntity> actions = issueEntity.getActions();
		if (actions == null) {
			actions = new ArrayList<ActionEntity>();
		}
		actions.add(actionEntity);
		issueEntity.setActions(actions);
		issueRepository.save(issueEntity);
		return issueEntity;
	}
    
	
}
