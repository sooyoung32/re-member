package com.remember.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by eunhwanpark on 15. 12. 19..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueModel {

    @NotNull
    private String subject;

    @NotNull
    private String content;

    private List<String> tags;

    private Date createdAt;

    private String timeTreeId;

}
