package com.kt.cloud.eop.module.codeproject.dto.query.request;

import com.kt.component.dto.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CodeProjectListQueryReq extends PagingQuery {

    private String projectName;

}
