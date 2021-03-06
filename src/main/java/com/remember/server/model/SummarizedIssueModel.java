package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by eunhwanpark on 15. 12. 20..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummarizedIssueModel {

    private String id;

    private String title;

    private String content;

    private long shareCount;

	private long subscribeCount;

    private long recordSize;

    private String imageUrl;

	private Date createdAt;

	private Date modifiedAt;

}
