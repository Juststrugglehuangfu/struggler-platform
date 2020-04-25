/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : platform

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2020-04-25 09:52:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(255) DEFAULT NULL COMMENT '加密的access_token的值',
  `token` longblob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
  `authentication_id` varchar(255) DEFAULT NULL COMMENT '加密过的username,client_id,scope',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登录的用户名',
  `client_id` varchar(255) DEFAULT NULL COMMENT '客户端ID',
  `authentication` longblob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '加密的refresh_token的值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(255) DEFAULT NULL COMMENT '登录的用户名',
  `clientId` varchar(255) DEFAULT NULL COMMENT '客户端ID',
  `scope` varchar(255) DEFAULT NULL COMMENT '申请的权限范围',
  `status` varchar(10) DEFAULT NULL COMMENT '状态（Approve或Deny）',
  `expiresAt` datetime DEFAULT NULL COMMENT '过期时间',
  `lastModifiedAt` datetime DEFAULT NULL COMMENT '最终修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端密匙',
  `scope` varchar(255) DEFAULT NULL COMMENT '客户端申请的权限范围',
  `authorized_grant_types` varchar(255) DEFAULT NULL COMMENT '客户端支持的grant_type',
  `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '重定向URI',
  `authorities` varchar(255) DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '访问令牌有效时间值(单位:秒)',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '更新令牌有效时间值(单位:秒)',
  `additional_information` varchar(255) DEFAULT NULL COMMENT '预留字段',
  `autoapprove` varchar(255) DEFAULT NULL COMMENT '用户是否自动Approval操作'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('client1', null, '$2a$10$qkr6ryASviheiFuwXKxATOGmxefpfGng.CzR31A5TzqObqCte7h8W', 'all', 'client_credentials,implicit,authorization_code,refresh_token,password', 'https://www.baidu.com', null, '600', '600', null, 'false');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) DEFAULT NULL COMMENT '加密的access_token值',
  `token` longblob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
  `authentication_id` varchar(255) DEFAULT NULL COMMENT '加密过的username,client_id,scope',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登录的用户名',
  `client_id` varchar(255) DEFAULT NULL COMMENT '客户端ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(255) DEFAULT NULL COMMENT '授权码(未加密)',
  `authentication` varchar(500) DEFAULT NULL COMMENT 'AuthorizationRequestHolder.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(255) DEFAULT NULL COMMENT '加密过的refresh_token的值',
  `token` longblob COMMENT 'OAuth2RefreshToken.java对象序列化后的二进制数据 ',
  `authentication` longblob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(19) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `permissions` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` varchar(2) DEFAULT NULL COMMENT '类型   10：目录   20：菜单   30：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` bigint(19) DEFAULT NULL COMMENT '排序',
  `status` varchar(1) DEFAULT NULL COMMENT '菜单状态 Y启用 N禁用',
  `comments` varchar(255) DEFAULT NULL COMMENT '菜单描述',
  `created_by` bigint(19) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(19) DEFAULT NULL COMMENT '修改人',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单管理 ';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `parent_id` bigint(10) DEFAULT NULL COMMENT '父级组织ID',
  `org_type` varchar(10) DEFAULT NULL COMMENT '组织类型(数据字典SYS_ORG_TYPE:<DEPARTMENT:部门,COMPANY:公司>)',
  `short_name` varchar(50) DEFAULT NULL COMMENT '组织简称',
  `full_name` varchar(255) DEFAULT NULL COMMENT '组织全名',
  `status` varchar(1) DEFAULT NULL COMMENT '组织状态 Y启用 N禁用',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `updated_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `sort` decimal(22,0) DEFAULT NULL COMMENT '组织排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织管理';

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `revision` int(10) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  `com_serial` varchar(32) DEFAULT NULL COMMENT '企业编号',
  `com_serial_id` bigint(19) DEFAULT NULL COMMENT '企业id',
  `del` int(10) DEFAULT NULL COMMENT '删除状态[-1:删除，1:正常]',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `status` int(10) DEFAULT NULL COMMENT '角色状态 1启用 -1禁用',
  `role_type` varchar(32) DEFAULT NULL COMMENT '角色类型(数据字典sys_role_type:<system:系统角色,workflow:业务流程角色>)',
  `readonly` int(10) DEFAULT NULL COMMENT '是否只读角色(数据字典pub_y_n:<1:是,-1:否>)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色信息  ';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `revision` int(10) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  `com_serial` varchar(32) DEFAULT NULL COMMENT '企业编号',
  `com_serial_id` bigint(19) DEFAULT NULL COMMENT '企业id',
  `del` int(10) DEFAULT NULL COMMENT '删除状态[-1:删除，1:正常]',
  `role_id` bigint(19) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(19) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色资源  ';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `revision` int(10) DEFAULT '0' COMMENT '乐观锁',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_date` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `com_serial` varchar(32) DEFAULT NULL COMMENT '企业编号',
  `com_serial_id` bigint(19) DEFAULT NULL COMMENT '企业id',
  `account` varchar(128) DEFAULT NULL COMMENT '登陆账号',
  `password` varchar(128) DEFAULT NULL COMMENT '登陆密码',
  `user_name` varchar(32) DEFAULT NULL COMMENT '全名',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `employee_number` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `phone_number` varchar(32) DEFAULT NULL COMMENT '电话',
  `given_name` varchar(32) DEFAULT NULL COMMENT '名',
  `surname` varchar(32) DEFAULT NULL COMMENT '姓氏',
  `alias_name` varchar(32) DEFAULT NULL COMMENT '别名',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `bank_account_number` varchar(32) DEFAULT NULL COMMENT '银行账号',
  `leader_id` varchar(32) DEFAULT NULL COMMENT '直属领导',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `email_remind` int(10) DEFAULT NULL COMMENT '是否邮箱提醒[-1:否,1:是]',
  `login_salt` varchar(128) DEFAULT NULL COMMENT '加密盐',
  `login_on` int(10) DEFAULT NULL COMMENT '是否允许登陆[-1:不允许登陆，1:允许登陆]',
  `user_type` int(10) DEFAULT NULL COMMENT '用户类型[0:超级管理员，1:企业管理员,2:普通用户]',
  `del` int(10) DEFAULT NULL COMMENT '删除状态[-1:删除，1:正常]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1112 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表  ';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1111', '0', null, '2020-04-22 22:56:33', null, '2020-04-22 22:56:37', '100', '1', 'admin', '$2a$10$qkr6ryASviheiFuwXKxATOGmxefpfGng.CzR31A5TzqObqCte7h8W', 'zhangsan', '12@', '10001', '1212', '三', '张', null, null, null, null, null, null, null, '1', '0', '1');

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `revision` int(10) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `com_serial` varchar(32) DEFAULT NULL COMMENT '企业编号',
  `com_serial_id` int(10) DEFAULT NULL COMMENT '企业id',
  `user_id` bigint(19) DEFAULT NULL COMMENT '用户id',
  `dept_id` bigint(19) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户所属部门  ';

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(10) DEFAULT NULL COMMENT '用户ID',
  `org_id` bigint(10) DEFAULT NULL COMMENT '组织ID',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `updated_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户组织管理';

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(10) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(10) DEFAULT NULL COMMENT '角色ID',
  `effect_start_date` date DEFAULT NULL COMMENT '生效时间',
  `effect_end_date` date DEFAULT NULL COMMENT '失效时间',
  `created_by` bigint(10) DEFAULT NULL COMMENT '创建人',
  `created_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(10) DEFAULT NULL COMMENT '修改人',
  `updated_date` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色管理';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_t`;
CREATE TABLE `sys_user_role_t` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `revision` int(10) DEFAULT NULL COMMENT '乐观锁',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  `com_serial` varchar(32) DEFAULT NULL COMMENT '企业编号',
  `com_serial_id` bigint(19) DEFAULT NULL COMMENT '企业id',
  `effect_start_date` date DEFAULT NULL COMMENT '生效时间',
  `effect_end_date` date DEFAULT NULL COMMENT '时效时间',
  `user_id` bigint(19) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(19) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色  ';

-- ----------------------------
-- Records of sys_user_role_t
-- ----------------------------
