/*
 Navicat Premium Data Transfer

 Source Server         : Leonardo-iWzl-Master-172.22.0.31
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 172.22.0.31:3306
 Source Schema         : movie_review_backend

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 09/08/2020 17:13:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
                           `uid` int NOT NULL AUTO_INCREMENT COMMENT '账户userId 唯一标识用户',
                           `role` enum('Admin','User') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'User' COMMENT '用户身份',
                           `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名/用户登录',
                           `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密钥Key',
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户密码',
                           `birthday` bigint NOT NULL DEFAULT '1577808000000' COMMENT '用户生日 UNIX时间戳',
                           `nike_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
                           `gender` enum('M','F','NO') CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT 'NO' COMMENT '用户性别',
                           `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '个性签名',
                           `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户头像',
                           `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '背景底色图片',
                           `status` int NOT NULL DEFAULT '0' COMMENT '用户状态,用于标识用户的基本状态',
                           `create_time` bigint NOT NULL COMMENT '创建时间',
                           `update_time` bigint NOT NULL COMMENT '更新时间',
                           `last_login_time` bigint NOT NULL DEFAULT '0',
                           PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100036 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
                            `classify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类',
                            PRIMARY KEY (`classify`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
                            `fid` bigint NOT NULL AUTO_INCREMENT,
                            `uid` bigint NOT NULL,
                            `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                            `create_time` bigint NOT NULL,
                            `status` int DEFAULT NULL,
                            PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for moments
-- ----------------------------
DROP TABLE IF EXISTS `moments`;
CREATE TABLE `moments` (
                           `mid` bigint NOT NULL AUTO_INCREMENT,
                           `uid` bigint NOT NULL,
                           `content` varchar(255) COLLATE utf8mb4_bin NOT NULL,
                           `image` varchar(255) COLLATE utf8mb4_bin NOT NULL,
                           `review` int NOT NULL,
                           `create_time` bigint NOT NULL,
                           `update_time` bigint NOT NULL,
                           `status` int DEFAULT NULL,
                           PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
                         `mid` bigint NOT NULL AUTO_INCREMENT COMMENT '电影的唯一标示符号',
                         `name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '电影名',
                         `director` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '导演',
                         `script_writer` varchar(512) COLLATE utf8mb4_bin NOT NULL COMMENT '编剧',
                         `starring` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '主演',
                         `release_time` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '上映时间',
                         `duration` int NOT NULL COMMENT '时长(min)',
                         `introduction` varchar(1024) COLLATE utf8mb4_bin NOT NULL COMMENT '剧情简介',
                         `poster` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '海报URL',
                         `status` int(2) unsigned zerofill NOT NULL DEFAULT '00' COMMENT '状态',
                         PRIMARY KEY (`mid`),
                         KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for movie_classify
-- ----------------------------
DROP TABLE IF EXISTS `movie_classify`;
CREATE TABLE `movie_classify` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `mid` bigint NOT NULL COMMENT '电影ID',
                                  `classify` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '分类',
                                  `status` int NOT NULL DEFAULT '0',
                                  PRIMARY KEY (`id`),
                                  KEY `classify` (`classify`),
                                  CONSTRAINT `classify` FOREIGN KEY (`classify`) REFERENCES `classify` (`classify`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for movie_review
-- ----------------------------
DROP TABLE IF EXISTS `movie_review`;
CREATE TABLE `movie_review` (
                                `rid` bigint NOT NULL AUTO_INCREMENT,
                                `mid` bigint NOT NULL,
                                `uid` bigint NOT NULL,
                                `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0',
                                `review_time` bigint NOT NULL,
                                `status` int NOT NULL DEFAULT '0',
                                PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
                        `nid` bigint NOT NULL AUTO_INCREMENT,
                        `uid` bigint NOT NULL,
                        `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
                        `create_time` bigint NOT NULL,
                        `status` int DEFAULT NULL,
                        PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
