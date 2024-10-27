CREATE DATABASE IF NOT EXISTS macintosh;

USE macintosh;

CREATE TABLE wechat_access_token (
                                    id BIGINT AUTO_INCREMENT COMMENT '主键',
                                    corp_id VARCHAR(255) NOT NULL COMMENT '企业ID',
                                    corp_secret VARCHAR(255) NOT NULL COMMENT '应用的凭证密钥',
                                    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '删除标志 1 已删除 0 未删除',
                                    access_token VARCHAR(255) COMMENT '每个应用有独立的secret，获取到的access_token只能本应用使用，所以每个应用的access_token应该分开来获取',
                                    expires_in TIMESTAMP COMMENT '过期时间',
                                    create_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间',
                                    update_time DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间',
                                    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用的access_token表';