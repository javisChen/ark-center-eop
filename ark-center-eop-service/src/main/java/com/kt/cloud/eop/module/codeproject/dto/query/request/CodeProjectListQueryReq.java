package com.ark.center.eop.module.codeproject.dto.query.request;

import com.ark.component.dto.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CodeProjectListQueryReq extends PagingQuery {

    private String projectName;

}
