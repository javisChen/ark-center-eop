package com.kt.cloud.eop.api.codeproject.query;

import com.kt.component.dto.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CodeProjectListQuery extends PagingQuery {

    private String projectName;

}
