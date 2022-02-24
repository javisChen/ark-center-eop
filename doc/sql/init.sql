create table if not exists project_basic
(
    id             bigint auto_increment comment 'id'
        primary key,
    name           varchar(64)      default ''                not null comment '工程名称',
    code           varchar(64)      default ''                not null comment '工程代码',
    description    varchar(256)     default ''                not null comment '工程介绍',
    type           tinyint(1)       default 1                 not null comment '工程类型 enums[BACKEND,后端应用,1 ;FRONTEND,前端应用,2]',
    scaffold       tinyint(1)       default 1                 not null comment '脚手架 enums[SpringCloud,SpringCloud,1;]',
    git_repos_url  varchar(256)     default ''                not null comment 'Git仓库地址',
    ext_properties varchar(1000)    default ''                not null comment '工程的独有属性JSON',
    push_status    tinyint unsigned default '0'               not null comment '代码推送状态 enums[NOT_PUSH,未推送,0;SUCCESS,推送成功,1;FAIL,推送失败,2;]',
    repos_status   tinyint unsigned default '0'               not null comment '仓库状态 enums[NOT_CREATE,未推送,0;SUCCESS,创建成功,1;FAIL,创建失败,2;]',
    gmt_create     datetime         default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified   datetime         default CURRENT_TIMESTAMP not null comment '更新时间',
    creator        bigint           default 0                 not null comment '创建人',
    modifier       bigint           default 0                 not null comment '更新人',
    is_deleted     bigint unsigned  default '0'               not null comment '删除标识 0-表示未删除 大于0-已删除'
)
    comment '工程表';

