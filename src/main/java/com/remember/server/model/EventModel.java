package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by eunhwanpark on 15. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {

    private String subject;
    private String content;
    private List<String> tags;
    private Date createdAt;
    private String timeTreeId;

}
