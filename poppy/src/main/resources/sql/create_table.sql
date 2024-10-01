
CREATE DATABASE IF NOT EXISTS macintosh;

USE macintosh;

CREATE TABLE hogwarts_test_case (
    id BIGINT AUTO_INCREMENT COMMENT '主键',
    case_name VARCHAR(255) NOT NULL COMMENT '用例名称',
    remark VARCHAR(255) COMMENT '备注',
    del_flag TINYINT NOT NULL COMMENT '删除标志 1 未删除 0 已删除',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    case_data VARCHAR(255) COMMENT '测试用例内容',
    create_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试用例表';

CREATE TABLE hogwarts_test_jenkins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    name VARCHAR(255) NOT NULL COMMENT '名称',
    test_command VARCHAR(255) COMMENT '测试命令',
    url VARCHAR(255) COMMENT 'Jenkins的baseUrl',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    user_name VARCHAR(255) COMMENT '用户名',
    password VARCHAR(255) COMMENT '密码',
    command_run_case_type TINYINT COMMENT '命令运行的测试用例类型  1 文本 2 文件',
    remark VARCHAR(255) COMMENT '备注',
    command_run_case_suffix VARCHAR(255) COMMENT '测试用例后缀名 如果case为文件时，此处必填',
    create_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='jenkins服务器表';

CREATE TABLE hogwarts_test_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    name VARCHAR(255) NOT NULL COMMENT '任务名称',
    test_jenkins_id BIGINT COMMENT '运行测试的Jenkins服务器id',
    build_url VARCHAR(255) COMMENT 'Jenkins的构建url',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    case_count BIGINT COMMENT '用例数量',
    remark VARCHAR(255) COMMENT '备注',
    task_type TINYINT COMMENT '任务类型 1 执行测试任务 2 一键执行测试的任务',
    status TINYINT COMMENT '状态 0 无效 1 新建 2 执行中 3 执行完成',
    test_command VARCHAR(255) COMMENT 'Jenkins执行测试时的命令脚本',
    create_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='测试任务表';

CREATE TABLE hogwarts_test_task_case_rel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    create_user_id BIGINT NOT NULL COMMENT '创建人id',
    task_id BIGINT NOT NULL COMMENT '任务id',
    case_id BIGINT COMMENT '用例id',
    create_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关联关系表';

CREATE TABLE hogwarts_test_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    user_name VARCHAR(255) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT '邮箱',
    auto_create_case_job_name VARCHAR(255) COMMENT '自动生成用例job名称 不为空时表示已经创建job',
    start_test_job_name VARCHAR(255) COMMENT '执行测试job名称 不为空时表示已经创建job',
    default_jenkins_id BIGINT COMMENT '默认Jenkins服务器',
    create_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
