/*
Navicat MySQL Data Transfer

Source Server         : anzhuang
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : fileplat

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2019-03-18 19:36:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL COMMENT '权限ID',
  `name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `type` varchar(1) NOT NULL DEFAULT '1' COMMENT '类型\r\n1 按钮',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '以blob类型存储的触发器（用于Quartz 用户用JDBC创建他们自己定制的触发器类型，JobStore并不知道如何存储实例的时候）',
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '以Blob类型存储Quartz的 Calendar 信息',
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------
INSERT INTO `qrtz_calendars` VALUES ('quartzScheduler', 'firstCalendar', 0xACED0005737200256F72672E71756172747A2E696D706C2E63616C656E6461722E43726F6E43616C656E6461728E96DAB5BB4D1F810200014C000E63726F6E45787072657373696F6E74001B4C6F72672F71756172747A2F43726F6E45787072657373696F6E3B787200256F72672E71756172747A2E696D706C2E63616C656E6461722E4261736543616C656E6461722B1CF186E3DF18EF0200034C000C6261736543616C656E6461727400154C6F72672F71756172747A2F43616C656E6461723B4C000B6465736372697074696F6E7400124C6A6176612F6C616E672F537472696E673B4C000874696D655A6F6E657400144C6A6176612F7574696C2F54696D655A6F6E653B7870707070737200196F72672E71756172747A2E43726F6E45787072657373696F6E00000002E47E2F0F0200024C000E63726F6E45787072657373696F6E71007E00044C000874696D655A6F6E6571007E0005787074000D302F35202A202A203F202A202A70);

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储Cron Trigger,包括Cron 表达式和 时区信息',
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'firstCronTrigger', 'firstCronGroup', '0/6 * * ? * *', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储与已触发的触发器相关的状态信息，以及相关联job的执行信息',
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
INSERT INTO `qrtz_fired_triggers` VALUES ('quartzScheduler', 'NON_CLUSTERED1552908743186', 'dailyTimeTrigger', 'dailyTimeGroup', 'NON_CLUSTERED', '1552908975013', '1552908976000', '5', 'ACQUIRED', null, null, '0', '0');

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储每一个已配置的job的详细信息',
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL COMMENT '作业是否应在孤立后保留存储（没有触发器指向它）。\n',
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL COMMENT '是否更新作业数据',
  `REQUESTS_RECOVERY` varchar(1) NOT NULL COMMENT '指示调度程序在遇到“恢复”或“故障转移”情况时是否应重新执行作业。\n',
  `JOB_DATA` blob COMMENT '作业数据',
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'firstSimpleTask', 'firstSimpleTask', null, 'com.hua.fileplat.cloud.base.quartz.HelloWordJob', '1', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000A68656C6C6F576F726C64740004776F72647800);

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储程序的悲观锁的信息（假如使用了悲观锁）',
  `LOCK_NAME` varchar(40) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储已暂停的调度器组的信息',
  `TRIGGER_GROUP` varchar(200) NOT NULL COMMENT '触发器组',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储少量的有关调度器的状态 和 别的调度器 实例（加入是用于一个集群中）',
  `INSTANCE_NAME` varchar(200) NOT NULL COMMENT '实例名称',
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL COMMENT '最后的检查时间',
  `CHECKIN_INTERVAL` bigint(13) NOT NULL COMMENT '检查间隔',
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储单例触发器，包括重复次数，间隔，已经已触发的次数',
  `TRIGGER_NAME` varchar(200) NOT NULL COMMENT '触发器名称',
  `TRIGGER_GROUP` varchar(200) NOT NULL COMMENT '触发器组名称',
  `REPEAT_COUNT` bigint(7) NOT NULL COMMENT '重复次数',
  `REPEAT_INTERVAL` bigint(12) NOT NULL COMMENT '触发次数',
  `TIMES_TRIGGERED` bigint(10) NOT NULL COMMENT '重复间隔',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------
INSERT INTO `qrtz_simple_triggers` VALUES ('quartzScheduler', 'simpleTriggerImpl', 'simpleGroup', '1', '3', '0');

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储单例触发器，包括重复次数，间隔，已经已触发的次数',
  `TRIGGER_NAME` varchar(200) NOT NULL COMMENT '触发器名称',
  `TRIGGER_GROUP` varchar(200) NOT NULL COMMENT '触发器组',
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------
INSERT INTO `qrtz_simprop_triggers` VALUES ('quartzScheduler', 'calendarName', 'calendarGroup', 'DAY', 'Asia/Shanghai', null, '3', '0', '0', '0', null, null, '0', '0');
INSERT INTO `qrtz_simprop_triggers` VALUES ('quartzScheduler', 'dailyTimeTrigger', 'dailyTimeGroup', 'SECOND', '1,2,3,4,5,6,7', '19,0,0,20,0,0', '1', '233', '-1', '0', null, null, '0', '0');

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL COMMENT '存储已经配置的触发器信息    调度器名称',
  `TRIGGER_NAME` varchar(200) NOT NULL COMMENT '触发器名称',
  `TRIGGER_GROUP` varchar(200) NOT NULL COMMENT '触发器组名称',
  `JOB_NAME` varchar(200) NOT NULL COMMENT '作业名称',
  `JOB_GROUP` varchar(200) NOT NULL COMMENT '作业组名',
  `DESCRIPTION` varchar(250) DEFAULT NULL COMMENT '描述',
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL COMMENT '下次执行时间',
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL COMMENT '前一次执行时间',
  `PRIORITY` int(11) DEFAULT NULL COMMENT '有限权',
  `TRIGGER_STATE` varchar(16) NOT NULL COMMENT '触发器状态  |-NONE 无  \n        |-NORMAL 正常状态  \n        |-PAUSED 暂停状态   \n        |-COMPLETE 完成  \n        |-ERROR 错误\n        |-BLOCKED 堵塞',
  `TRIGGER_TYPE` varchar(8) NOT NULL COMMENT '触发器类型 CAL_INT（日历）  DAILY_I(日期)  CRON  SIMPLE',
  `START_TIME` bigint(13) NOT NULL COMMENT '开始时间',
  `END_TIME` bigint(13) DEFAULT NULL COMMENT '结束时间',
  `CALENDAR_NAME` varchar(200) DEFAULT NULL COMMENT '日历名称',
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL COMMENT '失败次数',
  `JOB_DATA` blob COMMENT '作业数据',
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'calendarName', 'calendarGroup', 'firstSimpleTask', 'firstSimpleTask', null, '61513729200000', '-1', '5', 'WAITING', 'CAL_INT', '61513729200000', '61513734600000', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'dailyTimeTrigger', 'dailyTimeGroup', 'firstSimpleTask', 'firstSimpleTask', null, '1552908976000', '1552908975000', '5', 'ACQUIRED', 'DAILY_I', '1552908743388', '0', null, '0', '');
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'firstCronTrigger', 'firstCronGroup', 'firstSimpleTask', 'firstSimpleTask', null, '1552908978000', '1552908972000', '0', 'WAITING', 'CRON', '1552908742000', '0', 'firstCalendar', '0', '');
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'simpleTriggerImpl', 'simpleGroup', 'firstSimpleTask', 'firstSimpleTask', null, '61513729200000', '-1', '5', 'WAITING', 'SIMPLE', '61513729200000', '61513734600000', null, '0', '');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `NAME` varchar(200) NOT NULL COMMENT '姓名',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `ID` varchar(32) NOT NULL,
  `PERMISSION_ID` varchar(32) NOT NULL COMMENT '权限ID',
  `ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `spring_session`
-- ----------------------------
DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE `spring_session` (
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL COMMENT '创建时间',
  `LAST_ACCESS_TIME` bigint(20) NOT NULL COMMENT '上次访问时间',
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL COMMENT '最大误差',
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL COMMENT '主要名称\r\n',
  PRIMARY KEY (`SESSION_ID`),
  KEY `SPRING_SESSION_IX1` (`LAST_ACCESS_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spring_session
-- ----------------------------

-- ----------------------------
-- Table structure for `spring_session_attributes`
-- ----------------------------
DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE `spring_session_attributes` (
  `SESSION_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_ID`,`ATTRIBUTE_NAME`),
  KEY `SPRING_SESSION_ATTRIBUTES_IX1` (`SESSION_ID`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_ID`) REFERENCES `spring_session` (`SESSION_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spring_session_attributes
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(32) NOT NULL COMMENT '用户ID',
  `USER_NAME` varchar(200) NOT NULL COMMENT '用户名',
  `EMAIL` varchar(100) NOT NULL COMMENT '用户邮箱',
  `PHONE` varchar(20) NOT NULL COMMENT '手机号',
  `PASSWORD` varchar(16) NOT NULL COMMENT '用户密码',
  `ADDRESS` varchar(300) DEFAULT NULL COMMENT '用户地址',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('99999999999999999999999999999999', 'admin123', '1308189967@qq.com', '13081899671', 'admin123', 'xxx', '2019-03-18 09:22:29', '2019-03-18 09:22:32');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` varchar(32) NOT NULL COMMENT 'ID',
  `USER_ID` varchar(32) NOT NULL,
  `ROLE_ID` varchar(32) NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
