/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : cloud_note

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2019-02-01 22:13:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cn_activity`
-- ----------------------------
DROP TABLE IF EXISTS `cn_activity`;
CREATE TABLE `cn_activity` (
  `cn_activity_id` varchar(100) NOT NULL COMMENT '活动ID',
  `cn_activity_title` varchar(500) DEFAULT NULL COMMENT '活动标题',
  `cn_activity_body` text COMMENT '活动介绍(html片段)',
  `cn_activity_end_time` bigint(20) DEFAULT NULL COMMENT '活动结束时间',
  PRIMARY KEY (`cn_activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_activity_status`
-- ----------------------------
DROP TABLE IF EXISTS `cn_activity_status`;
CREATE TABLE `cn_activity_status` (
  `cn_activity_status_id` varchar(100) NOT NULL COMMENT '活动状态ID',
  `cn_activity_id` varchar(100) DEFAULT NULL COMMENT '活动ID',
  `cn_activity_status_code` varchar(500) DEFAULT NULL COMMENT '活动状态Code',
  `cn_activity_status_name` varchar(500) DEFAULT NULL COMMENT '活动状态名称',
  PRIMARY KEY (`cn_activity_status_id`),
  KEY `FK_Reference_9` (`cn_activity_id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`cn_activity_id`) REFERENCES `cn_activity` (`cn_activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_activity_status
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_collection`
-- ----------------------------
DROP TABLE IF EXISTS `cn_collection`;
CREATE TABLE `cn_collection` (
  `cn_collection_id` varchar(100) NOT NULL,
  `cn_collection_user_id` varchar(100) NOT NULL,
  `cn_collection_share_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cn_collection_id`),
  KEY `cn_collection_ibfk_1` (`cn_collection_user_id`),
  KEY `cn_collection_share_id` (`cn_collection_share_id`),
  CONSTRAINT `cn_collection_ibfk_1` FOREIGN KEY (`cn_collection_user_id`) REFERENCES `cn_user` (`cn_user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cn_collection_ibfk_2` FOREIGN KEY (`cn_collection_share_id`) REFERENCES `cn_share` (`cn_share_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_collection
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_note`
-- ----------------------------
DROP TABLE IF EXISTS `cn_note`;
CREATE TABLE `cn_note` (
  `cn_note_id` varchar(100) NOT NULL COMMENT '笔记ID',
  `cn_notebook_id` varchar(100) DEFAULT NULL COMMENT '笔记本ID',
  `cn_user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `cn_note_status_id` varchar(100) DEFAULT NULL COMMENT '笔记状态ID:备用',
  `cn_note_type_id` varchar(100) DEFAULT NULL COMMENT '笔记本类型ID：备用',
  `cn_note_title` varchar(500) DEFAULT NULL COMMENT '笔记标题',
  `cn_note_body` text COMMENT '笔记内容',
  `cn_note_create_time` bigint(20) DEFAULT NULL COMMENT '笔记创建时间',
  `cn_note_last_modify_time` bigint(20) DEFAULT NULL COMMENT '笔记最近修改时间',
  PRIMARY KEY (`cn_note_id`),
  KEY `FK_Reference_2` (`cn_notebook_id`),
  KEY `FK_Reference_3` (`cn_user_id`),
  KEY `FK_Reference_7` (`cn_note_status_id`),
  KEY `FK_Reference_8` (`cn_note_type_id`),
  CONSTRAINT `cn_note_ibfk_1` FOREIGN KEY (`cn_notebook_id`) REFERENCES `cn_notebook` (`cn_notebook_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cn_note_ibfk_2` FOREIGN KEY (`cn_user_id`) REFERENCES `cn_user` (`cn_user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_note
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_notebook`
-- ----------------------------
DROP TABLE IF EXISTS `cn_notebook`;
CREATE TABLE `cn_notebook` (
  `cn_notebook_id` varchar(100) NOT NULL COMMENT '笔记本ID',
  `cn_user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `cn_notebook_type_id` varchar(100) DEFAULT NULL COMMENT '笔记本类型ID',
  `cn_notebook_name` varchar(500) DEFAULT NULL COMMENT '笔记本名',
  `cn_notebook_desc` text COMMENT '笔记本说明',
  `cn_notebook_createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`cn_notebook_id`),
  KEY `FK_Note_User_Reference` (`cn_user_id`),
  KEY `FK_Reference_6` (`cn_notebook_type_id`),
  CONSTRAINT `cn_notebook_ibfk_1` FOREIGN KEY (`cn_user_id`) REFERENCES `cn_user` (`cn_user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_notebook
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_notebook_type`
-- ----------------------------
DROP TABLE IF EXISTS `cn_notebook_type`;
CREATE TABLE `cn_notebook_type` (
  `cn_notebook_type_id` varchar(100) NOT NULL COMMENT '笔记本类型ID',
  `cn_notebook_type_code` varchar(100) DEFAULT NULL COMMENT '笔记本类型Code',
  `cn_notebook_type_name` varchar(500) DEFAULT NULL COMMENT '笔记本类型名称',
  `cn_notebook_type_desc` text COMMENT '笔记本类型说明',
  PRIMARY KEY (`cn_notebook_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_notebook_type
-- ----------------------------
INSERT INTO `cn_notebook_type` VALUES ('1', 'favorites', 'favorites', '收藏');
INSERT INTO `cn_notebook_type` VALUES ('2', 'recycle', 'recycle', '回收站');
INSERT INTO `cn_notebook_type` VALUES ('3', 'action', 'action', '活动');
INSERT INTO `cn_notebook_type` VALUES ('4', 'push', 'push', '推送');
INSERT INTO `cn_notebook_type` VALUES ('5', 'normal', 'normal', '正常');

-- ----------------------------
-- Table structure for `cn_note_activity`
-- ----------------------------
DROP TABLE IF EXISTS `cn_note_activity`;
CREATE TABLE `cn_note_activity` (
  `cn_note_activity_id` varchar(100) NOT NULL COMMENT '投稿ID',
  `cn_activity_id` varchar(100) DEFAULT NULL COMMENT '活动ID',
  `cn_note_id` varchar(100) DEFAULT NULL COMMENT '笔记ID',
  `cn_note_activity_up` int(11) DEFAULT NULL COMMENT '投稿赞:增加数',
  `cn_note_activity_down` int(11) DEFAULT NULL COMMENT '投稿踩:增加数',
  `cn_note_activity_title` varchar(500) DEFAULT NULL,
  `cn_note_activity_body` text,
  PRIMARY KEY (`cn_note_activity_id`),
  KEY `FK_Reference_4` (`cn_activity_id`),
  KEY `FK_Reference_5` (`cn_note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_note_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_note_status`
-- ----------------------------
DROP TABLE IF EXISTS `cn_note_status`;
CREATE TABLE `cn_note_status` (
  `cn_note_status_id` varchar(100) NOT NULL COMMENT '笔记状态ID',
  `cn_note_status_code` varchar(100) DEFAULT NULL COMMENT '笔记状态Code',
  `cn_note_status_name` varchar(500) DEFAULT NULL COMMENT '笔记状态名字',
  PRIMARY KEY (`cn_note_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_note_status
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_note_type`
-- ----------------------------
DROP TABLE IF EXISTS `cn_note_type`;
CREATE TABLE `cn_note_type` (
  `cn_note_type_id` varchar(100) NOT NULL COMMENT '笔记本类型ID',
  `cn_note_type_code` varchar(100) DEFAULT NULL COMMENT '笔记本类型Code',
  `cn_note_type_name` varchar(500) DEFAULT NULL COMMENT '笔记本类型名称',
  `cn_note_type_desc` text COMMENT '笔记本类型说明',
  PRIMARY KEY (`cn_note_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_note_type
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_share`
-- ----------------------------
DROP TABLE IF EXISTS `cn_share`;
CREATE TABLE `cn_share` (
  `cn_share_id` varchar(100) NOT NULL COMMENT '共享ID',
  `cn_share_title` varchar(500) DEFAULT NULL COMMENT '共享标题',
  `cn_share_body` text COMMENT '共享内容',
  `cn_note_id` varchar(100) DEFAULT NULL COMMENT '笔记id',
  PRIMARY KEY (`cn_share_id`),
  KEY `cn_note_id` (`cn_note_id`) USING BTREE,
  CONSTRAINT `cn_share_ibfk_1` FOREIGN KEY (`cn_note_id`) REFERENCES `cn_note` (`cn_note_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of cn_share
-- ----------------------------

-- ----------------------------
-- Table structure for `cn_user`
-- ----------------------------
DROP TABLE IF EXISTS `cn_user`;
CREATE TABLE `cn_user` (
  `cn_user_id` varchar(100) NOT NULL COMMENT '用户ID',
  `cn_user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `cn_user_password` varchar(100) DEFAULT NULL COMMENT '密码',
  `cn_user_token` varchar(100) DEFAULT NULL COMMENT '令牌',
  `cn_user_nick` text COMMENT '说明',
  PRIMARY KEY (`cn_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_user
-- ----------------------------
