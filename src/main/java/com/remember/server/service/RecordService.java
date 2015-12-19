package com.remember.server.service;

import com.remember.server.entity.*;
import com.remember.server.model.NewManualRecordModel;
import com.remember.server.model.NewOpenGraphRecordModel;
import com.remember.server.model.NewYoutubeRecordModel;
import com.remember.server.repository.IssueRepository;
import com.remember.server.repository.RecordRepository;
import me.shakiba.og4j.OpenGraph;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

	public IssueEntity createNewRecord(ObjectId issueOid, NewManualRecordModel recordModel, UserEntity userEntity) {

		ManualRecordEntity recordEntity = modelMapper.map(
				recordModel,
				ManualRecordEntity.class
		);
		recordEntity.setCreator(userEntity);
		recordRepository.save(recordEntity);

		IssueEntity issueEntity = issueRepository.findOne(issueOid);
		List<RecordEntity> records = issueEntity.getRecords() == null ? new ArrayList<>() : issueEntity.getRecords();
		records.add(records.size(), recordEntity);
		issueEntity.setRecords(records);
		issueEntity.setModifiedAt(new Date());
		issueEntity.setRecordSize(issueEntity.getRecordSize() + 1);
		issueRepository.save(issueEntity);

		return issueEntity;
	}

	public IssueEntity createNewRecord(ObjectId issueOid, NewOpenGraphRecordModel recordModel, UserEntity userEntity) throws IOException {

		OpenGraphRecordEntity recordEntity = modelMapper.map(
				recordModel,
				OpenGraphRecordEntity.class
		);

		Document doc = Jsoup.parse(new URL(recordModel.getOpenGraphUrl()), 1000);

		OpenGraph og = new OpenGraph();
		for (Element tag : doc.select("meta[property^=og:]")) {
			og.put(tag.attr("property"), tag.attr("content"));
		}
//		for (Element tag : doc.select("meta[property^=http://ogp.me/ns#]")) {
//			og.put(tag.attr("property").replace("http://ogp.me/ns#", "og:"),
//					tag.attr("content"));
//		}
		recordEntity.setTitle(og.title());
		recordEntity.setDescription(og.description());
		recordEntity.setImageUrl(og.imageUrl().equals("") ? og.image() : og.imageUrl());
		recordEntity.setCreator(userEntity);
		recordRepository.save(recordEntity);

		IssueEntity issueEntity = issueRepository.findOne(issueOid);
		List<RecordEntity> records = issueEntity.getRecords() == null ? new ArrayList<>() : issueEntity.getRecords();
		records.add(records.size(), recordEntity);
		issueEntity.setRecords(records);
		issueEntity.setModifiedAt(new Date());
		issueEntity.setRecordSize(issueEntity.getRecordSize() + 1);
		issueRepository.save(issueEntity);

		return issueEntity;
	}


	public IssueEntity createNewRecord(ObjectId issueOid, NewYoutubeRecordModel recordModel, UserEntity userEntity) throws IOException {

		YoutubeRecordEntity recordEntity = modelMapper.map(
				recordModel,
				YoutubeRecordEntity.class
		);

		Document doc = Jsoup.parse(new URL(recordModel.getYoutubeUrl()), 1000);

		OpenGraph og = new OpenGraph();
		for (Element tag : doc.select("meta[property^=og:]")) {
			og.put(tag.attr("property"), tag.attr("content"));
		}
//		for (Element tag : doc.select("meta[property^=http://ogp.me/ns#]")) {
//			og.put(tag.attr("property").replace("http://ogp.me/ns#", "og:"),
//					tag.attr("content"));
//		}
		recordEntity.setTitle(og.title());
		recordEntity.setDescription(og.description());
		recordEntity.setImageUrl(og.imageUrl().equals("") ? og.image() : og.imageUrl());
		recordEntity.setCreator(userEntity);
		recordRepository.save(recordEntity);

		IssueEntity issueEntity = issueRepository.findOne(issueOid);
		List<RecordEntity> records = issueEntity.getRecords() == null ? new ArrayList<>() : issueEntity.getRecords();
		records.add(records.size(), recordEntity);
		issueEntity.setRecords(records);
		issueEntity.setModifiedAt(new Date());
		issueEntity.setRecordSize(issueEntity.getRecordSize() + 1);
		issueRepository.save(issueEntity);

		return issueEntity;
	}

}
