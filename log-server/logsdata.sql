/*
 Navicat Premium Data Transfer

 Source Server         : myecs
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 139.9.181.57:3306
 Source Schema         : logsdata

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 26/12/2020 09:39:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(12) NOT NULL COMMENT '用户的id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for logs_act
-- ----------------------------
DROP TABLE IF EXISTS `logs_act`;
CREATE TABLE `logs_act`  (
  `act` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`act`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logs_act
-- ----------------------------
INSERT INTO `logs_act` VALUES ('addCart', 3);
INSERT INTO `logs_act` VALUES ('addComment', 3);
INSERT INTO `logs_act` VALUES ('addFavor', 2);
INSERT INTO `logs_act` VALUES ('clickItem', 14);
INSERT INTO `logs_act` VALUES ('coupon', 19);

-- ----------------------------
-- Table structure for logs_dau
-- ----------------------------
DROP TABLE IF EXISTS `logs_dau`;
CREATE TABLE `logs_dau`  (
  `mid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `appid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `vs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `logDate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `logHour` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ts` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`mid`, `logDate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logs_dau
-- ----------------------------
INSERT INTO `logs_dau` VALUES ('mid_118', '400', 'mall2020', 'shan1xi', 'andriod', 'baidu', 'startup', '1.1.3', '2020-12-24', '11', 1608779746539);
INSERT INTO `logs_dau` VALUES ('mid_136', '357', 'mall2020', 'guangdong', 'andriod', 'huawei', 'startup', '1.1.3', '2020-12-24', '11', 1608779761811);
INSERT INTO `logs_dau` VALUES ('mid_369', '190', 'mall2020', 'shanghai', 'ios', 'appstore', 'startup', '1.1.3', '2020-12-24', '11', 1608779759785);
INSERT INTO `logs_dau` VALUES ('mid_384', '204', 'mall2020', 'guangdong', 'andriod', 'wandoujia', 'startup', '1.1.3', '2020-12-24', '11', 1608779744478);
INSERT INTO `logs_dau` VALUES ('mid_447', '46', 'mall2020', 'tianjin', 'andriod', 'tencent', 'startup', '1.2.0', '2020-12-24', '11', 1608779758775);
INSERT INTO `logs_dau` VALUES ('mid_71', '59', 'mall2020', 'guangdong', 'ios', 'appstore', 'startup', '1.2.0', '2020-12-24', '11', 1608779745519);
INSERT INTO `logs_dau` VALUES ('mid_77', '153', 'mall2020', 'shanghai', 'ios', 'appstore', 'startup', '1.1.3', '2020-12-24', '11', 1608779743466);
INSERT INTO `logs_dau` VALUES ('mid_82', '115', 'mall2020', 'hebei', 'ios', 'appstore', 'startup', '1.2.0', '2020-12-24', '11', 1608779742385);

SET FOREIGN_KEY_CHECKS = 1;
