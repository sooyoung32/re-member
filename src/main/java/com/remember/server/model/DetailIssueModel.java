package com.remember.server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
	private int recordSize;

    private List<ActionModel> actions;

    private String imageUrl;
    
    private List<CommentModel> comments;
    
    private int commentSize;
    
    

}
