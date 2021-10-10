DROP table if EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(50) NOT NULL COMMENT '用户编号',
  `user_account` varchar(50) NOT NULL COMMENT '登录账号',
  `user_name` varchar(50) NOT NULL COMMENT '用户姓名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `user_type` varchar(10) NOT NULL COMMENT '用户类型',
  `class_no` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `py_ky_info`;
CREATE TABLE `py_ky_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `year` varchar(255) DEFAULT NULL COMMENT '年份',
  `name` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `class` varchar(255) DEFAULT NULL COMMENT '院系名称',
  `subject_code` varchar(255) DEFAULT NULL COMMENT '专业编码',
  `subject` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `sum_score` varchar(255) DEFAULT NULL COMMENT '总分',
  `one` varchar(255) DEFAULT NULL COMMENT '科目一分数线',
  `two` varchar(255) DEFAULT NULL COMMENT '科目二分数线',
  `three` varchar(255) DEFAULT NULL COMMENT '科目三分数线',
  `four` varchar(255) DEFAULT NULL COMMENT '科目四分数线',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各大高校历年考研录取信息';




