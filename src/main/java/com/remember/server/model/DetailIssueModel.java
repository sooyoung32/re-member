package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private RecordModel records;

    private Date createdAt;

    private Date modifiedAt;

}
