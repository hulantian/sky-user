/*
Navicat MySQL Data Transfer

Source Server         : tencent
Source Server Version : 50626
Source Host           : 49.234.229.59:3306
Source Database       : sky

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2019-09-30 14:02:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `id` varchar(32) NOT NULL COMMENT '主键（机构ID）',
  `pid` varchar(32) NOT NULL DEFAULT '' COMMENT '父机构ID',
  `name` varchar(128) NOT NULL COMMENT '机构名称',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '机构状态：1：正常，2：停用',
  `is_delete` int(1) NOT NULL DEFAULT '1' COMMENT '是否已删除：1：正常，2：删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';

-- ----------------------------
-- Records of t_organization
-- ----------------------------
INSERT INTO `t_organization` VALUES ('1173471726124675074', '-1', '联合金控', '1', '1', '2019-09-16 13:40:35', '2019-09-16 13:40:35');
INSERT INTO `t_organization` VALUES ('1173471883998277634', '1173472695256363009', '后端组', '1', '1', '2019-09-16 13:41:13', '2019-09-27 13:44:00');
INSERT INTO `t_organization` VALUES ('1173471960489799682', '1173472695256363009', '前端组', '1', '1', '2019-09-16 13:41:31', '2019-09-27 13:44:02');
INSERT INTO `t_organization` VALUES ('1173472695256363009', '1173471726124675074', '技术部', '1', '1', '2019-09-16 13:44:26', '2019-09-27 13:44:03');
INSERT INTO `t_organization` VALUES ('1173472801800073217', '1173471726124675074', '行政部', '1', '1', '2019-09-16 13:44:52', '2019-09-16 13:44:52');
INSERT INTO `t_organization` VALUES ('1173473645790507009', '1173472695256363009', '移动端组', '1', '1', '2019-09-16 13:48:13', '2019-09-27 13:44:05');
INSERT INTO `t_organization` VALUES ('1173473717173366786', '1173472695256363009', '爬虫端组', '1', '1', '2019-09-16 13:48:30', '2019-09-27 13:44:07');
INSERT INTO `t_organization` VALUES ('1173493389990047746', '1173473645790507009', 'Android研发组', '1', '1', '2019-09-16 15:06:40', '2019-09-27 13:44:10');
INSERT INTO `t_organization` VALUES ('1173493450694209538', '1173473645790507009', 'IOS研发组', '1', '1', '2019-09-16 15:06:55', '2019-09-27 13:44:11');
INSERT INTO `t_organization` VALUES ('1174978886381912066', '1173472695256363009', 'web组', '1', '1', '2019-09-20 17:29:34', '2019-09-27 13:44:14');
INSERT INTO `t_organization` VALUES ('1176704972388118530', '1173472695256363009', '测试组', '1', '1', '2019-09-25 11:48:21', '2019-09-27 13:44:16');
INSERT INTO `t_organization` VALUES ('1177160828183482370', '1173472695256363009', '牛逼组', '1', '1', '2019-09-26 17:59:47', '2019-09-27 13:44:18');
INSERT INTO `t_organization` VALUES ('1177164540169699329', '1173472695256363009', '产品组', '1', '1', '2019-09-26 18:14:31', '2019-09-27 13:44:20');
INSERT INTO `t_organization` VALUES ('1177431292187312129', '1177164540169699329', 'ui组', '1', '1', '2019-09-27 11:54:30', '2019-09-27 13:44:24');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` varchar(32) NOT NULL COMMENT '资源Id',
  `pid` varchar(32) NOT NULL DEFAULT '' COMMENT '父级资源Id',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '资源名称',
  `url` varchar(256) NOT NULL DEFAULT '' COMMENT '访问地址',
  `is_res` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否为功能模块：1：功能模块，2：功能文件夹',
  `description` varchar(128) NOT NULL DEFAULT '' COMMENT '描述信息',
  `is_delete` int(1) NOT NULL DEFAULT '1' COMMENT '是否已删除：1：正常，2：删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源信息（功能模块）表';

-- ----------------------------
-- Records of t_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL COMMENT '主键（角色ID）',
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '角色代码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名称',
  `sys_code` varchar(32) NOT NULL DEFAULT '' COMMENT '角色所在系统编码',
  `description` varchar(128) NOT NULL DEFAULT '' COMMENT '角色描述',
  `is_delete` int(1) NOT NULL DEFAULT '1' COMMENT '是否已删除：1：正常，2：删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色代码表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1174874984715436033', 's', 'name1', '2', 'desc1', '2', '2019-09-20 10:36:39', '2019-09-20 16:58:01');
INSERT INTO `t_role` VALUES ('1174875964643241986', 'a', 'name1', '2', 'desc1', '1', '2019-09-20 10:40:32', '2019-09-20 10:40:32');
INSERT INTO `t_role` VALUES ('1174972210972647426', 'hjhihihihihihihihihihihihi', '123123asd', '2', '', '1', '2019-09-20 17:02:59', '2019-09-20 17:02:59');

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  `res_id` varchar(32) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`role_code`,`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色资源关系表';

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_system_info
-- ----------------------------
DROP TABLE IF EXISTS `t_system_info`;
CREATE TABLE `t_system_info` (
  `id` varchar(32) NOT NULL COMMENT '主键（系统ID）',
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '系统编码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '系统名称',
  `description` varchar(128) NOT NULL DEFAULT '' COMMENT '系统描述',
  `is_delete` int(1) NOT NULL DEFAULT '1' COMMENT '是否已删除：1：正常，2：删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统信息表';

-- ----------------------------
-- Records of t_system_info
-- ----------------------------
INSERT INTO `t_system_info` VALUES ('1174212996786384897', '1', '测试系统1', '测试系统1的描述', '2', '2019-09-18 14:46:08', '2019-09-18 15:13:28');
INSERT INTO `t_system_info` VALUES ('1174213026016489474', '1', '测试系统1', '测试系统1的描述', '2', '2019-09-18 14:46:15', '2019-09-18 15:13:28');
INSERT INTO `t_system_info` VALUES ('1174213093104381954', '2', '测试系统2', '测试系统2的描述', '2', '2019-09-18 14:46:31', '2019-09-18 15:13:41');
INSERT INTO `t_system_info` VALUES ('1174213101501378561', '2', '测试系统2', '测试系统2的描述', '2', '2019-09-18 14:46:33', '2019-09-18 15:13:41');
INSERT INTO `t_system_info` VALUES ('1174218245022502913', '3', '测试系统2', '测试系统2的描述', '2', '2019-09-18 15:07:00', '2019-09-18 15:12:32');
INSERT INTO `t_system_info` VALUES ('1174219710738808834', '3', '测试系统2', '测试系统2的描述', '1', '2019-09-18 15:12:49', '2019-09-18 15:12:49');
INSERT INTO `t_system_info` VALUES ('1174220733645692929', '2', '测试系统2', '测试系统2的描述', '1', '2019-09-18 15:16:53', '2019-09-18 15:16:53');
INSERT INTO `t_system_info` VALUES ('1174220793951395842', '4', '测试系统2', '测试系统2的描述', '2', '2019-09-18 15:17:07', '2019-09-18 15:17:19');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT '主键（用户ID）',
  `org_id` varchar(32) NOT NULL DEFAULT '' COMMENT '机构ID',
  `root_org_id` varchar(32) NOT NULL DEFAULT '' COMMENT '根机构ID',
  `username` varchar(128) NOT NULL COMMENT '用户名称',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `gender` tinyint(2) NOT NULL DEFAULT '3' COMMENT '性别:1:男，2：女,3:未知',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `address` varchar(120) DEFAULT NULL COMMENT '地址',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '用户状态：1：正常，2：停用',
  `is_delete` int(1) NOT NULL DEFAULT '1' COMMENT '是否已删除：1：正常，2：删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1177458778665238529', '1173493389990047746', '', '胡蓝天', '{bcrypt}$2a$10$9qgQldrPiHhmvsSYJk.qpuWSWpxZUQ7ouYVqhQuGJx72b8LUCXyAa', null, '3', '18782937142', null, null, '1', '1', '2019-09-27 13:43:43', '2019-09-27 13:43:43');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  PRIMARY KEY (`user_id`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for ucs_survey
-- ----------------------------
DROP TABLE IF EXISTS `ucs_survey`;
CREATE TABLE `ucs_survey` (
  `survey_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(120) NOT NULL COMMENT '调查人名称',
  `tel_no` varchar(20) NOT NULL COMMENT '调查人电话号码',
  `education_background` varchar(120) DEFAULT NULL COMMENT '调查人学历',
  `major` varchar(120) DEFAULT NULL COMMENT '调查人专业',
  `id_card` varchar(20) DEFAULT NULL COMMENT '调查人身份证',
  `test_province` tinyint(2) DEFAULT NULL COMMENT '调查人报考省份',
  `gendar` tinyint(2) DEFAULT '1' COMMENT '调查人性别，1：男2：女',
  `is_no_basis` tinyint(1) DEFAULT '1' COMMENT '调查人是否有基础 1：否 2：是',
  `is_first_test` tinyint(1) DEFAULT '1' COMMENT '调查人是否首次:1：否2: 是',
  `join_class_type` tinyint(2) DEFAULT NULL COMMENT '调查人报考参加培训班类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '其他备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已删除（1未删除，2已删除）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次修订时间',
  PRIMARY KEY (`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='调查数据表';

-- ----------------------------
-- Records of ucs_survey
-- ----------------------------

-- ----------------------------
-- Table structure for ucs_user
-- ----------------------------
DROP TABLE IF EXISTS `ucs_user`;
CREATE TABLE `ucs_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(50) NOT NULL COMMENT '用户名称',
  `pwd` varchar(255) NOT NULL COMMENT '用户密码',
  `role_code` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户角色:1:普通用户2:经理3:管理员',
  `is_default_pwd` tinyint(1) NOT NULL DEFAULT '2' COMMENT '初始密码标志，1为非初始密码，2为初始密码',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `gender` tinyint(2) NOT NULL DEFAULT '3' COMMENT '性别:1:男，2：女,3:未知',
  `tel_no` varchar(20) NOT NULL COMMENT '电话号码',
  `address` varchar(120) DEFAULT NULL COMMENT '地址',
  `email` varchar(120) DEFAULT NULL COMMENT '邮箱',
  `user_status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户状态:1：正常2：停用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否已删除（1未删除，2已删除）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次修订时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户中心-用户表';

-- ----------------------------
-- Records of ucs_user
-- ----------------------------
