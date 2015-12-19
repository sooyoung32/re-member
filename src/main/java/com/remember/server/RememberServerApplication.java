package com.remember.server;

import com.google.common.collect.Lists;
import com.remember.server.entity.*;
import com.remember.server.repository.ActionRepository;
import com.remember.server.repository.IssueRepository;
import com.remember.server.repository.RecordRepository;
import com.remember.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class RememberServerApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

    public static void main(String[] args) throws Throwable {
        ApplicationContext context = SpringApplication.run(RememberServerApplication.class, args);
	    context.getBean(RememberServerApplication.class).springMain(args);

    }


	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private IssueRepository issueRepository;

	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private UserRepository userRepository;

	public void springMain(String[] args) throws InterruptedException {

		while(true) {

			try {
//				createTestIssue();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Thread.sleep(3000);

		}

	}

	public void createTestIssue() {

		UserEntity creator = userRepository.findOneByEmail("madelene.callender@mailpost.zzn.com");

		RecordEntity recordEntity = new ManualRecordEntity();
		recordEntity.setTitle("서해대교 2중추돌사고");
		recordEntity.setDescription("서해대교에서 승용차가 승합차를 추돌, 2중추돌사고가 발생하였다.");
		recordEntity.setDate(new Date());
		recordEntity.setImageUrl("http://img.naver.net/static/www/u/2013/0731/nmms_224940510.gif");
		recordEntity.setReferences(Arrays.asList(
				new ReferenceEntity("http://post.naver.com/viewer/postView.nhn?memberNo=11227948&volumeNo=3194334&vType=VERTICAL"),
				new ReferenceEntity("http://news.naver.com/main/closing/2015/touchingNews.nhn")
		));
		recordEntity.setCreator(creator);

		recordRepository.save(recordEntity);


		List<ActionEntity> actionEntities = Lists.newArrayList(
				new ActionEntity("한국도로공사에 신고하기", "http://www.naver.com"),
				new ActionEntity("행정안전부에 신고하기", "http://www.google.com")
		);
		actionRepository.save(actionEntities);

		IssueEntity issueEntity = new IssueEntity();
		issueEntity.setTitle("서해대교 사고");
		issueEntity.setRecords(Arrays.asList(recordEntity));
		issueEntity.setTags(Arrays.asList(
				new TagEntity("서해대교"),
				new TagEntity("서해안고속도로"),
				new TagEntity("사고"),
				new TagEntity("정체")
		));
		issueEntity.setActions(actionEntities);
		issueEntity.setShareCount(0);
		issueEntity.setCreator(creator);

		issueRepository.save(issueEntity);

	}

}
