package com.kt.cloud.cop.client.codeproject.query;

import com.kt.component.dto.PagingQuery;
import lombok.Data;

@Data
public class CodeProjectListQuery extends PagingQuery {

    private String projectName;

}
