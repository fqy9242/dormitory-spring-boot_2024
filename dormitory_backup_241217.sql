/*
 Navicat Premium Data Transfer

 Source Server         : qht
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : dormitory

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 17/12/2024 08:46:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`  (
  `id` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 1是0否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_admin_username`(`username` ASC) USING BTREE COMMENT '用户名'
) ENGINE = InnoDB COMMENT = '管理员';

-- ----------------------------
-- Records of administrator
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `building_name` varchar(255) NOT NULL COMMENT '楼栋名称',
  `type` tinyint NOT NULL COMMENT '楼栋类型 1.男生宿舍 2.女生宿舍 3.混合宿舍',
  `area_name` varchar(255) NULL DEFAULT NULL COMMENT '区域名称 ',
  `floor_amount` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '层数',
  `foolr_dormitory_amount` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '每层宿舍数量',
  `principal_phone` varchar(255) NULL DEFAULT NULL COMMENT '负责人电话',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_principal_phone`(`principal_phone` ASC) USING BTREE COMMENT '负责人电话',
  INDEX `idx_building_name`(`building_name` ASC) USING BTREE COMMENT '楼栋名称'
) ENGINE = InnoDB AUTO_INCREMENT = 3 COMMENT = '楼栋表';

-- ----------------------------
-- Records of building
-- ----------------------------
BEGIN;
INSERT INTO `building` (`id`, `building_name`, `type`, `area_name`, `floor_amount`, `foolr_dormitory_amount`, `principal_phone`, `create_time`, `update_time`) VALUES (1, '1栋', 1, '东一区', 12, 6, '18888888888', '2024-12-11 15:27:29', '2024-12-11 15:27:31'), (2, '春风楼', 1, '豪华区', 10, 6, '19999999999', '2024-12-12 20:45:06', '2024-12-12 20:45:08');
COMMIT;

-- ----------------------------
-- Table structure for choose_bed
-- ----------------------------
DROP TABLE IF EXISTS `choose_bed`;
CREATE TABLE `choose_bed`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_number` char(13) NOT NULL COMMENT '学号',
  `bed_number` varchar(255) NOT NULL COMMENT '床位号：生成规则 -> 宿舍id - 床位序号',
  `create_time` datetime NOT NULL COMMENT '生成时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除0否1是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_choose_bed_student_number`(`student_number` ASC) USING BTREE,
  INDEX `uk_choose_bed_bed_number`(`bed_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 COMMENT = '学生选床位表';

-- ----------------------------
-- Records of choose_bed
-- ----------------------------
BEGIN;
INSERT INTO `choose_bed` (`id`, `student_number`, `bed_number`, `create_time`, `update_time`, `is_delete`) VALUES (23, '2331020130229', '1-1', '2024-12-14 00:12:09', '2024-12-15 10:59:29', 1), (27, '2331020130229', '1-6', '2024-12-14 00:24:04', '2024-12-15 10:59:29', 1), (28, '2331020130229', '4-3', '2024-12-14 09:33:39', '2024-12-15 10:59:29', 1), (29, '2331020130229', '1-8', '2024-12-14 15:14:57', '2024-12-15 10:59:29', 1), (31, '2331020130229', '1-1', '2024-12-14 15:37:12', '2024-12-15 10:59:29', 1), (32, '2331020130229', '4-1', '2024-12-14 20:02:07', '2024-12-15 10:59:29', 1), (33, '2331020130229', '1-1', '2024-12-14 20:46:33', '2024-12-15 10:59:29', 1), (34, '2331020130229', '1-5', '2024-12-14 22:26:55', '2024-12-15 10:59:29', 1), (35, '2331020130230', '4-3', '2024-12-14 22:41:31', '2024-12-14 22:44:51', 1), (36, '2331020130230', '3-1', '2024-12-14 22:43:23', '2024-12-14 22:44:51', 1), (37, '2331020130230', '4-1', '2024-12-14 22:43:29', '2024-12-14 22:44:51', 1), (38, '2331020130230', '3-3', '2024-12-14 22:44:21', '2024-12-14 22:44:51', 1), (39, '2331020130230', '2-7', '2024-12-14 22:44:38', '2024-12-14 22:44:51', 1), (40, '2331020130230', '3-7', '2024-12-14 22:44:51', '2024-12-14 22:44:51', 0), (41, '2331020130229', '4-2', '2024-12-15 10:59:29', '2024-12-15 10:59:29', 0);
COMMIT;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) NOT NULL COMMENT '班级名称',
  `grade` int UNSIGNED NOT NULL COMMENT '年级',
  `boy_amount` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '男生人数',
  `girl_amount` tinyint NOT NULL DEFAULT 0 COMMENT '女生人数',
  `teacher_phone_number` varchar(255) NULL DEFAULT NULL COMMENT '辅导员电话号码',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_class_name`(`class_name` ASC) USING BTREE,
  INDEX `idx_teacher_phone`(`teacher_phone_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 COMMENT = '班级表';

-- ----------------------------
-- Records of class
-- ----------------------------
BEGIN;
INSERT INTO `class` (`id`, `class_name`, `grade`, `boy_amount`, `girl_amount`, `teacher_phone_number`, `create_time`, `update_time`) VALUES (1, '计算机应用工程(中职生本)2302', 2023, 20, 20, '17777402163', '2024-12-11 15:39:40', '2024-12-11 15:39:42'), (2, '计算机应用工程(中职生本)2301', 2023, 30, 20, '17777402163', '2024-12-11 15:39:40', '2024-12-11 15:39:42');
COMMIT;

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dormitory_number` varchar(255) NOT NULL COMMENT '宿舍号',
  `building_id` int UNSIGNED NULL DEFAULT NULL COMMENT '楼栋id',
  `bed_amount` tinyint NOT NULL DEFAULT 0 COMMENT '床位数',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0否1是',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_dormitory_number_building`(`dormitory_number` ASC, `building_id` ASC) USING BTREE,
  INDEX `idx_dormitory_number`(`dormitory_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 COMMENT = '宿舍表';

-- ----------------------------
-- Records of dormitory
-- ----------------------------
BEGIN;
INSERT INTO `dormitory` (`id`, `dormitory_number`, `building_id`, `bed_amount`, `create_time`, `update_time`, `is_delete`) VALUES (1, '111', 1, 10, '2024-12-11 15:50:57', '2024-12-11 15:50:59', 0), (2, '110', 1, 10, '2024-12-11 15:50:57', '2024-12-11 15:50:59', 0), (3, '112', 1, 10, '2024-12-12 00:07:36', '2024-12-12 00:07:39', 0), (4, '302', 2, 4, '2024-12-12 20:45:51', '2024-12-12 20:45:52', 0);
COMMIT;

-- ----------------------------
-- Table structure for dormitory_area
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_area`;
CREATE TABLE `dormitory_area`  (
  `Id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `area_name` varchar(255) NOT NULL COMMENT '区域名称',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`Id`) USING BTREE,
  UNIQUE INDEX `uk_area_name`(`area_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 COMMENT = '宿舍区域表';

-- ----------------------------
-- Records of dormitory_area
-- ----------------------------
BEGIN;
INSERT INTO `dormitory_area` (`Id`, `area_name`, `create_time`, `update_time`, `is_delete`) VALUES (1, '东一区', '2024-12-11 15:19:28', '2024-12-11 15:19:31', 0), (2, '东二区', '2024-12-11 15:19:41', '2024-12-11 15:19:44', 0), (3, '西三区', '2024-12-11 15:20:36', '2024-12-11 15:20:38', 0), (4, '南区', '2024-12-11 15:20:53', '2024-12-11 15:20:56', 0), (5, '新南区', '2024-12-11 15:21:07', '2024-12-11 15:21:15', 0), (6, '西一区', '2024-12-11 15:21:27', '2024-12-11 15:21:29', 0), (7, '豪华区', '2024-12-12 20:44:08', '2024-12-12 20:44:10', 0);
COMMIT;

-- ----------------------------
-- Table structure for plan_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `plan_dormitory`;
CREATE TABLE `plan_dormitory`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dormitory_id` int UNSIGNED NOT NULL COMMENT '宿舍id',
  `dormitory_type` tinyint NOT NULL COMMENT '类型,1男2女',
  `class_name` varchar(255) NOT NULL COMMENT '班级名称',
  `plan_number` tinyint UNSIGNED NOT NULL COMMENT '安排人数',
  `choose_number` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '已选择人数',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除 1是0否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 COMMENT = '宿舍安排	';

-- ----------------------------
-- Records of plan_dormitory
-- ----------------------------
BEGIN;
INSERT INTO `plan_dormitory` (`id`, `dormitory_id`, `dormitory_type`, `class_name`, `plan_number`, `choose_number`, `create_time`, `update_time`, `is_delete`) VALUES (1, 1, 1, '计算机应用工程(中职生本)2302', 10, 0, '2024-12-11 16:10:16', '2024-12-11 16:10:17', 0), (2, 2, 1, '计算机应用工程(中职生本)2302', 10, 0, '2024-12-11 16:10:16', '2024-12-11 16:10:17', 0), (3, 3, 1, '计算机应用工程(中职生本)2302', 10, 0, '2024-12-11 16:10:16', '2024-12-11 16:10:17', 0), (4, 4, 1, '计算机应用工程(中职生本)2302', 2, 0, '2024-12-11 16:10:16', '2024-12-11 16:10:17', 0);
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `student_number` char(13) NOT NULL COMMENT '学号',
  `login_password` varchar(255) NOT NULL DEFAULT '123456' COMMENT '登录密码',
  `gender` tinyint NOT NULL COMMENT '学生性别 1男2女',
  `class_name` varchar(255) NULL DEFAULT NULL COMMENT '班级名称',
  `bed_number` varchar(255) NULL DEFAULT NULL COMMENT '床位号',
  `emergency_contact_phone` varchar(255) NULL DEFAULT NULL COMMENT '紧急联系人电话',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint UNSIGNED NULL DEFAULT 0 COMMENT '是否删除 1是0否',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_student_number`(`student_number` ASC) USING BTREE,
  INDEX `fk_class_name`(`class_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 COMMENT = '学生表';

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` (`id`, `name`, `student_number`, `login_password`, `gender`, `class_name`, `bed_number`, `emergency_contact_phone`, `create_time`, `update_time`, `is_delete`) VALUES (1, '覃惠通', '2331020130229', '123456', 1, '计算机应用工程(中职生本)2302', '4-2', '19999999999', '2024-12-08 10:30:53', '2024-12-15 10:59:29', 0), (2, '暴龙战士', '2331020130230', '123456', 1, '计算机应用工程(中职生本)2302', '3-7', '18888888888', '2024-12-14 10:30:53', '2024-12-14 22:44:51', 0), (3, '战一柔', '2331020130231', '123456', 2, '计算机应用工程(中职生本)2302', '', '16666666666', '2024-12-13 16:30:53', '2024-12-14 20:46:40', 0), (4, '晴天小猪', '2331020130111', '123456', 1, '计算机应用工程(中职生本)2301', NULL, '13333333333', '2024-12-14 21:33:19', '2024-12-14 21:33:23', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
