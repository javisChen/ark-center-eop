package com.ark.center.eop.manager.git;

import lombok.Data;

@Data
public class GitCreateReposRequest {

    private String name;
    private String description;
    private Boolean isPrivate;
}
