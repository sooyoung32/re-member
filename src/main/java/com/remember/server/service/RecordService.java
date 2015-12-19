package com.remember.server.service;

import com.remember.server.entity.IssueEntity;
import com.remember.server.entity.RecordEntity;
import com.remember.server.model.NewRecordModel;
import com.remember.server.repository.IssueRepository;
import com.remember.server.repository.RecordRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Service
public class RecordService {

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private ModelMapper modelMapper;

	public IssueEntity createNewRecord(ObjectId issueOid, NewRecordModel recordModel) {

		RecordEntity recordEntity = modelMapper.map(
				recordModel,
				RecordEntity.class
		);
		recordRepository.save(recordEntity);

		IssueEntity issueEntity = issueRepository.findOne(issueOid);
		List<RecordEntity> records = issueEntity.getRecords();
		records.add(records.size(), recordEntity);
		issueEntity.setRecords(records);

		issueRepository.save(issueEntity);

		return issueEntity;
	}

}
