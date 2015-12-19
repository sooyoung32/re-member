package com.remember.server.model;

import com.remember.server.entity.ActionEntity;
import com.remember.server.entity.RecordEntity;
import com.remember.server.entity.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by eunhwanpark on 15. 12. 20..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailIssueModel {

    private String id;

    private String title;

    private String content;

    private List<String> tags;

    private long shareCount;

    private List<RecordModel> records;

    private List<ActionModel> actions;

    private String imageUrl;

}
