package com.kt.cloud.codeproject.module.codeproject.service;

import com.kt.cloud.codeproject.git.GitCreate;
import com.kt.cloud.codeproject.git.GitService;
import com.kt.cloud.codeproject.infrastructure.generate.JavaProjectGenerator;
import com.kt.cloud.codeproject.infrastructure.generate.ProjectGenerator;
import com.kt.cloud.codeproject.infrastructure.generate.model.JavaProjectGenerateParam;
import com.kt.cloud.codeproject.module.codeproject.GenCodeProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CodeProjectService implements ICodeProjectService {

    @Autowired
    private GitService gitService;

    @Override
    public void genCodeProject(GenCodeProjectDTO genCodeProjectDTO) {
        ProjectGenerator generator = new JavaProjectGenerator();

        JavaProjectGenerateParam param = new JavaProjectGenerateParam();
        param.setArtifactId(genCodeProjectDTO.getArtifactId());
        param.setGroupId(genCodeProjectDTO.getGroupId());
        param.setPackageName(param.getPackageName());
        File codeProject = generator.generator(param);

        GitCreate gitCreate = new GitCreate();
        gitCreate.setName(genCodeProjectDTO.getName());
        gitCreate.setDescription(genCodeProjectDTO.getDescription());
        String repositoryUrl = gitService.createRepository(gitCreate);

        boolean b = gitService.intiAndPushToRepos(codeProject, repositoryUrl);


    }
}
