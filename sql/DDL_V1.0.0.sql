/*
 Navicat Premium Data Transfer

 Source Server         : sh-cynosdbmysql-grp-4pyk1uri.sql.tencentcdb.com_20794
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : sh-cynosdbmysql-grp-4pyk1uri.sql.tencentcdb.com:20794
 Source Schema         : school_bbs

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 09/10/2022 10:15:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `summary` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章摘要',
  `category_id` tinyint(0) NOT NULL COMMENT '所属分类id',
  `thumbnail` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图',
  `is_top` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否置顶（0否，1是）',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（0禁用、1待审核，2草稿，3发布，4仅自己可见）',
  `view_count` bigint(0) NULL DEFAULT 0 COMMENT '访问量',
  `comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否允许评论 1是，0否',
  `create_by` bigint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_by` bigint(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NOT NULL,
  `deleted` tinyint(0) NOT NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_notice
-- ----------------------------
DROP TABLE IF EXISTS `message_notice`;
CREATE TABLE `message_notice`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `publish_user_id` bigint(0) NOT NULL COMMENT '消息发布者id',
  `subscriber_user_id` bigint(0) NULL DEFAULT NULL COMMENT '消息订阅者id',
  `read_user_id` bigint(0) NULL DEFAULT NULL COMMENT '消息阅读者id',
  `read_user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息阅读者姓名',
  `read_time` bigint(0) NULL DEFAULT NULL COMMENT '打开消息时间',
  `type` int(0) NOT NULL COMMENT '通知类型',
  `message_level` tinyint(0) NOT NULL COMMENT '事件等级',
  `message_template` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息模板',
  `extend` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展字段，前端渲染通知模板内容',
  `attachment` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `state` tinyint(0) NOT NULL COMMENT '消息状态0未读，1已读',
  `create_time` bigint(0) NOT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(0) NOT NULL COMMENT '已删除,0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oss_client
-- ----------------------------
DROP TABLE IF EXISTS `oss_client`;
CREATE TABLE `oss_client`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端注册名称',
  `client_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端唯一标识',
  `secret_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '秘钥',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_client_id`(`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oss_meta
-- ----------------------------
DROP TABLE IF EXISTS `oss_meta`;
CREATE TABLE `oss_meta`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件所属人',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `size` bigint(0) NOT NULL COMMENT '文件大小',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件相对目录地址',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件Hash指纹',
  `media_type` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '媒体类型',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL COMMENT '是否删除 0未删除 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report_notice
-- ----------------------------
DROP TABLE IF EXISTS `report_notice`;
CREATE TABLE `report_notice`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `user_id` bigint(0) NOT NULL COMMENT '被举报用户id',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '被举报用户名',
  `report_user_id` bigint(0) NOT NULL COMMENT '检举用户id',
  `report_user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '检举用户名称',
  `report_type` tinyint(0) NOT NULL COMMENT '举报类型',
  `report_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '举报内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `role` tinyint(0) UNSIGNED NOT NULL DEFAULT 3 COMMENT '角色：1后台管理员、2管理员、3用户',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别 1男 2女',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_user` bigint(0) NOT NULL COMMENT '创建人id',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态 0禁用1启用',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否删除 0未删除1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
