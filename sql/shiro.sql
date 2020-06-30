/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 30/06/2020 09:56:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(64) NOT NULL COMMENT '权限id',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1277061221371117569, 'add');
INSERT INTO `permission` VALUES (1277061256922038273, 'delete');
INSERT INTO `permission` VALUES (1277061281852981249, 'update');
INSERT INTO `permission` VALUES (1277061346785001473, 'select');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(64) NOT NULL COMMENT '权限表Id',
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1277061573222891522, 'user-add');
INSERT INTO `role` VALUES (1277061606206898178, 'user-update');
INSERT INTO `role` VALUES (1277061632744259585, 'user-delete');
INSERT INTO `role` VALUES (1277061705855172610, 'user-select');
INSERT INTO `role` VALUES (1277061723097956353, 'admin');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` bigint(64) NOT NULL COMMENT '角色权限关联表id',
  `role_id` bigint(64) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(64) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1277076589787598849, 1277061573222891522, 1277061221371117569);
INSERT INTO `role_permission` VALUES (1277076627389534210, 1277061606206898178, 1277061281852981249);
INSERT INTO `role_permission` VALUES (1277076669282242562, 1277061705855172610, 1277061346785001473);
INSERT INTO `role_permission` VALUES (1277076824605708289, 1277061632744259585, 1277061256922038273);
INSERT INTO `role_permission` VALUES (1277077064222101506, 1277061723097956353, 1277061221371117569);
INSERT INTO `role_permission` VALUES (1277077086170894338, 1277061723097956353, 1277061281852981249);
INSERT INTO `role_permission` VALUES (1277077107700256770, 1277061723097956353, 1277061346785001473);
INSERT INTO `role_permission` VALUES (1277077138742300673, 1277061723097956353, 1277061256922038273);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(64) NOT NULL COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1277058852419833857, 'user', 'dbcb74a47c04a2c7373d2448f6783209');
INSERT INTO `user` VALUES (1277059004341719041, 'admin', 'f901eb3293e91e409b10d9ab98c1c380');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(64) NOT NULL COMMENT '用户角色关联表id',
  `user_id` bigint(64) NOT NULL COMMENT '用户id',
  `role_id` bigint(64) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1277075507652898818, 1277058852419833857, 1277061573222891522);
INSERT INTO `user_role` VALUES (1277075702990024706, 1277058852419833857, 1277061705855172610);
INSERT INTO `user_role` VALUES (1277075807222673409, 1277059004341719041, 1277061573222891522);
INSERT INTO `user_role` VALUES (1277075831306366977, 1277059004341719041, 1277061705855172610);
INSERT INTO `user_role` VALUES (1277075858024083458, 1277059004341719041, 1277061606206898178);
INSERT INTO `user_role` VALUES (1277075908452200449, 1277059004341719041, 1277061632744259585);

SET FOREIGN_KEY_CHECKS = 1;
