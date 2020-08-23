/*
 Navicat Premium Data Transfer

 Source Server         : 47.107.128.84
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.107.128.84:3306
 Source Schema         : spring-boot-plus2

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 24/08/2020 00:28:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_authority`;
CREATE TABLE `t_admin_authority`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限类Id(方法与类/层级关系展示)',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式(GET/POST/PUT/DELETE)',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限url',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限备注信息',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是  -1-类数据)',
  `type` int(1) NOT NULL COMMENT '终端(字典code, 如 0-管理端 1-用户端 -1-All)',
  `state` int(1) NOT NULL COMMENT '授权状态(字典code  0-无需登录 1-需登录 2-需登录+授权 -1-类数据+拼接终端数据)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限接口表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_authority
-- ----------------------------
INSERT INTO `t_admin_authority` VALUES ('457203749895671808', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '', '', '/admin/adminAuthority', 'URL权限管理', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749899866112', NULL, NULL, '2020-08-22 17:31:17', '2020-08-22 17:31:17', 0, 63, '457203749895671808', 'GET', '/admin/adminAuthority/findByRoleIdList', '查询所有 || 根据角色ID选中', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749899866113', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749895671808', 'GET', '/admin/adminAuthority/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749899866114', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 19:22:30', 0, 63, '457203749895671808', 'PUT', '/admin/adminAuthority/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749899866115', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 19:22:17', 0, 63, '457203749895671808', 'PUT', '/admin/adminAuthority/refreshAuthority', '扫描权限', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749904060416', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '', '', '/admin/adminDictionary', '字典管理', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749904060417', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'POST', '/admin/adminDictionary/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749904060418', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'PUT', '/admin/adminDictionary/updBySort', '修改排序', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749904060419', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'GET', '/admin/adminDictionary/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749904060420', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'PUT', '/admin/adminDictionary/upd', '编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749904060421', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'DELETE', '/admin/adminDictionary/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749908254720', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'GET', '/admin/adminDictionary/generateEnum', '生成枚举', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749908254721', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'GET', '/admin/adminDictionary/findCodeGroup', '查询所有-code分组', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749908254722', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'GET', '/admin/adminDictionary/findByCode', 'Code查询(Tree)', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749908254723', NULL, NULL, '2020-08-22 17:31:18', '2020-08-22 17:31:18', 0, 63, '457203749904060416', 'GET', '/admin/adminDictionary/findVersion', '获取字典版本', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749912449024', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '', '', '/admin/adminMenu', '菜单管理', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749912449025', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749912449024', 'POST', '/admin/adminMenu/insert', '菜单添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749912449026', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749912449024', 'GET', '/admin/adminMenu/findByPidOrRoleId', 'pid + roleId 查询菜单列表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749912449027', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749912449024', 'GET', '/admin/adminMenu/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749916643328', NULL, NULL, '2020-08-22 17:31:19', '2020-08-23 04:21:47', 0, 63, '457203749912449024', 'PUT', '/admin/adminMenu/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749916643329', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749912449024', 'DELETE', '/admin/adminMenu/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749916643330', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749912449024', 'GET', '/admin/adminMenu/findTree', '左导航菜单', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749916643331', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '', '', '/admin/adminRole', '角色管理', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749916643332', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749916643331', 'POST', '/admin/adminRole/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837632', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749916643331', 'GET', '/admin/adminRole/findList', '查询所有', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837633', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749916643331', 'PUT', '/admin/adminRole/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837634', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749916643331', 'DELETE', '/admin/adminRole/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837635', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749916643331', 'PUT', '/admin/adminRole/updRoleMenu', '角色的菜单分配', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837636', NULL, NULL, '2020-08-22 17:31:19', '2020-08-22 17:31:19', 0, 63, '457203749916643331', 'GET', '/admin/adminRole/findUserRole', '获取用户的当前角色', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837637', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749916643331', 'GET', '/admin/adminRole/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837638', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749916643331', 'PUT', '/admin/adminRole/updUserRole', '用户的角色分配', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837639', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749916643331', 'PUT', '/admin/adminRole/updRoleAuth', '角色的URL权限分配', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749920837640', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '', '', '/admin/adminUser', '用户管理', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749925031936', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'POST', '/admin/adminUser/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749925031937', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'PUT', '/admin/adminUser/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749925031938', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'DELETE', '/admin/adminUser/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749925031939', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'GET', '/admin/adminUser/findByRoleId', '获取指定角色的用户列表', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749925031940', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'DELETE', '/admin/adminUser/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749925031941', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'PUT', '/admin/adminUser/updByPassword', '密码修改', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749925031942', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:33:39', 0, 63, '457203749920837640', 'POST', '/admin/adminUser/login', '登录', 0, 0, 0);
INSERT INTO `t_admin_authority` VALUES ('457203749925031943', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'GET', '/admin/adminUser/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749929226240', NULL, NULL, '2020-08-22 17:31:20', '2020-08-22 17:31:20', 0, 63, '457203749920837640', 'GET', '/admin/adminUser/findUser', '个人信息', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749929226241', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '', '', '/dev/devBug', 'Bug修复', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749929226242', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749929226241', 'PUT', '/dev/devBug/updByState', 'ID编辑状态', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749929226243', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749929226241', 'PUT', '/dev/devBug/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749929226244', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749929226241', 'DELETE', '/dev/devBug/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749933420544', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749929226241', 'POST', '/dev/devBug/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749933420545', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749929226241', 'GET', '/dev/devBug/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749933420546', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749929226241', 'DELETE', '/dev/devBug/delByIds', 'ID批量删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749933420547', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '', '', '/dev/devNorm', '开发规范', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749933420548', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749933420547', 'PUT', '/dev/devNorm/upd', 'ID编辑', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749933420549', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749933420547', 'POST', '/dev/devNorm/insert', '添加', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749937614848', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749933420547', 'DELETE', '/dev/devNorm/del', 'ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749937614849', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749933420547', 'DELETE', '/dev/devNorm/delByIds', '批量ID删除', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749937614850', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749933420547', 'GET', '/dev/devNorm/findPage', '分页查询', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749937614851', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '', '', '/dev/devTask', '开发任务', -1, 1, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749937614852', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:34:16', 0, 63, '457203749937614851', 'PUT', '/dev/devTask/updByState', 'ID编辑状态', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749937614853', NULL, NULL, '2020-08-22 17:31:21', '2020-08-22 17:31:21', 0, 63, '457203749937614851', 'PUT', '/dev/devTask/upd', 'ID编辑', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749937614854', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749937614851', 'GET', '/dev/devTask/findId', 'ID查询', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749941809152', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749937614851', 'DELETE', '/dev/devTask/del', 'ID删除', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749941809153', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749937614851', 'POST', '/dev/devTask/insert', '添加', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749941809154', NULL, NULL, '2020-08-22 17:31:22', '2020-08-23 04:33:45', 0, 63, '457203749937614851', 'GET', '/dev/devTask/findList', '查询所有', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749941809155', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 19:47:19', 0, 63, '457203749937614851', 'GET', '/dev/devTask/findPage', '分页查询', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749941809156', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749937614851', 'DELETE', '/dev/devTask/delByIds', '批量ID删除', 0, 1, 1);
INSERT INTO `t_admin_authority` VALUES ('457203749941809157', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '', '', '/dataBase', '数据库表查询', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749946003456', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749941809157', 'GET', '/dataBase/findTableField', '查询指定表下使用字段内容', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749946003457', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749941809157', 'GET', '/dataBase/findTable', '查询所有表名', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749946003458', NULL, NULL, '2020-08-22 17:31:23', '2020-08-22 17:31:23', 0, 63, '', '', '/generate', '代码生成器-只限于页面调用', -1, 0, -1);
INSERT INTO `t_admin_authority` VALUES ('457203749946003459', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749946003458', 'GET', '/generate/getPath', '代码生成路径', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749946003460', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749946003458', 'POST', '/generate/generateCode', '生成代码', 0, 0, 2);
INSERT INTO `t_admin_authority` VALUES ('457203749946003461', NULL, NULL, '2020-08-22 17:31:22', '2020-08-22 17:31:22', 0, 63, '457203749946003458', 'POST', '/generate/preview', '生成预览代码', 0, 0, 2);

-- ----------------------------
-- Table structure for t_admin_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_banner`;
CREATE TABLE `t_admin_banner`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner标题',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'banner描叙',
  `img_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'banner图片',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT 'banner排序',
  `disable` int(1) NOT NULL COMMENT 'banner禁用(0-启用 1-禁用)',
  `is_skip` int(1) NOT NULL DEFAULT 0 COMMENT '是否跳转(0-无  1-内部链接 2-外部链接)',
  `skip_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '跳转地址url(地址直接添加或字典表配置)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'banner表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_banner
-- ----------------------------
INSERT INTO `t_admin_banner` VALUES ('1297542919313518594', NULL, NULL, '2020-08-23 14:35:12', '2020-08-23 14:49:43', 0, 0, '1', '2', '3', 3, 3, 3, '34');
INSERT INTO `t_admin_banner` VALUES ('1297542968424624130', NULL, NULL, '2020-08-23 14:35:24', '2020-08-23 14:51:00', 0, 0, '4', '2', '2', 2, 2, 2, '2');
INSERT INTO `t_admin_banner` VALUES ('1297544335478321153', NULL, NULL, '2020-08-23 14:40:49', '2020-08-23 14:40:49', 0, 0, '1', '1', '1', 1, 1, 1, '1');
INSERT INTO `t_admin_banner` VALUES ('1297544358421164034', NULL, NULL, '2020-08-23 14:40:55', '2020-08-23 14:40:55', 0, 0, '1', '1', '1', 1, 1, 1, '1');
INSERT INTO `t_admin_banner` VALUES ('1297544390046216193', NULL, NULL, '2020-08-23 14:41:03', '2020-08-23 14:49:50', 0, 0, '45', '4', '4', 4, 4, 4, '4');
INSERT INTO `t_admin_banner` VALUES ('1297544416243838978', NULL, NULL, '2020-08-23 14:41:09', '2020-08-23 14:50:08', 0, 0, '5', '55', '5', 5, 5, 5, '5');
INSERT INTO `t_admin_banner` VALUES ('1297544445788516353', NULL, NULL, '2020-08-23 14:41:18', '2020-08-23 14:41:18', 0, 0, '6', '6', '6', 6, 6, 6, '6');
INSERT INTO `t_admin_banner` VALUES ('1297546446064676866', NULL, NULL, '2020-08-23 14:49:13', '2020-08-23 14:49:13', 0, 0, '5', '5', '5', 5, 5, 5, '5');
INSERT INTO `t_admin_banner` VALUES ('1297546634170822657', NULL, NULL, '2020-08-23 14:49:58', '2020-08-23 14:49:58', 0, 0, '8', '8', '8', 8, 8, 8, '8');
INSERT INTO `t_admin_banner` VALUES ('1297553687614447617', NULL, NULL, '2020-08-23 15:17:59', '2020-08-23 15:17:59', 0, 0, '1', '1', '1', 1, 1, 1, '1');
INSERT INTO `t_admin_banner` VALUES ('1297553724478185473', NULL, NULL, '2020-08-23 15:18:08', '2020-08-23 15:18:08', 0, 0, '2', '2', '2', 2, 2, 2, '2');
INSERT INTO `t_admin_banner` VALUES ('1297553966887985154', NULL, NULL, '2020-08-23 15:19:06', '2020-08-23 15:19:06', 0, 0, '1', '1', '1', 1, 1, 1, '1');

-- ----------------------------
-- Table structure for t_admin_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_dictionary`;
CREATE TABLE `t_admin_dictionary`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父Id',
  `desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-否 1-是)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_dictionary
-- ----------------------------
INSERT INTO `t_admin_dictionary` VALUES ('1290684671448936449', NULL, NULL, '2020-08-05 00:23:00', '2020-08-23 04:21:14', 0, 0, 'ENUMS', '枚举字典', '0', '状态/动态字段值，如：state，type，gender等, 可直接生成 前/ 后端枚举对象类代码', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290686507555844098', NULL, NULL, '2020-08-05 00:30:17', '2020-08-09 17:38:53', 0, 0, 'ADMIN', '系统模块', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290686763962036225', NULL, NULL, '2020-08-05 00:31:18', '2020-08-09 17:38:45', 0, 0, 'DEV', '开发模块', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687277911076865', NULL, NULL, '2020-08-05 00:33:21', '2020-08-09 17:41:00', 0, 0, 'MENU_ROOT', '菜单级别', '1290686507555844098', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687351005212673', NULL, NULL, '2020-08-05 00:33:38', '2020-08-07 16:47:57', 0, 0, '1', '顶部菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687461252493314', NULL, NULL, '2020-08-05 00:34:05', '2020-08-07 16:47:57', 0, 0, '2', '菜单', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687547940368386', NULL, NULL, '2020-08-05 00:34:25', '2020-08-07 16:47:57', 0, 0, '3', '页面', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290687608594198529', NULL, NULL, '2020-08-05 00:34:40', '2020-08-07 16:47:58', 0, 0, '4', '按钮', '1290687277911076865', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688121255587841', NULL, NULL, '2020-08-05 00:36:42', '2020-08-09 17:39:03', 0, 0, 'BASE', '通用枚举', '1290684671448936449', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688660164931586', NULL, NULL, '2020-08-05 00:38:51', '2020-08-09 17:34:36', 0, 0, 'GENDER', '性别', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688703043301378', NULL, NULL, '2020-08-05 00:39:01', '2020-08-07 16:47:58', 0, 0, '1', '男', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290688742289403906', NULL, NULL, '2020-08-05 00:39:10', '2020-08-07 16:47:58', 0, 0, '0', '女', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290690446414147586', NULL, NULL, '2020-08-05 00:45:56', '2020-08-14 01:03:35', 0, 0, 'test', '测试数据', '0', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290690909788270593', NULL, NULL, '2020-08-05 00:47:47', '2020-08-07 16:47:58', 0, 0, '3', '测试数据3', '1290690446414147586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694593746112514', NULL, NULL, '2020-08-05 01:02:25', '2020-08-07 16:47:59', 0, 0, '1', '1', '1290690909788270593', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694620853899266', NULL, NULL, '2020-08-05 01:02:32', '2020-08-07 16:47:59', 0, 0, '2', '2', '1290690909788270593', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694644753043457', NULL, NULL, '2020-08-05 01:02:37', '2020-08-07 16:47:59', 0, 0, '3', '3', '1290690909788270593', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694667049963521', NULL, NULL, '2020-08-05 01:02:43', '2020-08-07 16:47:59', 0, 0, '4', '4', '1290690909788270593', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694684838006785', NULL, NULL, '2020-08-05 01:02:47', '2020-08-07 16:47:59', 0, 0, '5', '5', '1290690909788270593', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290694701980127233', NULL, NULL, '2020-08-05 01:02:51', '2020-08-07 16:47:59', 0, 0, '6', '6', '1290690909788270593', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290699978137735170', NULL, NULL, '2020-08-05 01:23:49', '2020-08-10 02:19:43', 0, 0, 'BASE_STATE', '任务状态', '1290686763962036225', 'Task + Bug 任务状态', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700055099019265', NULL, NULL, '2020-08-05 01:24:07', '2020-08-07 16:48:00', 0, 0, '0', '未开始', '1290699978137735170', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700115199201282', NULL, NULL, '2020-08-05 01:24:22', '2020-08-07 16:48:00', 0, 0, '1', '正在进行', '1290699978137735170', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700159658823681', NULL, NULL, '2020-08-05 01:24:32', '2020-08-07 16:48:00', 0, 0, '2', '已完成', '1290699978137735170', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1290700198498078721', NULL, NULL, '2020-08-05 01:24:42', '2020-08-07 16:48:00', 0, 0, '3', '已撤销', '1290699978137735170', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1291341478399897601', NULL, NULL, '2020-08-06 11:52:54', '2020-08-07 16:48:00', 0, 0, '2', '未知', '1290688660164931586', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1292646044785561601', NULL, NULL, '2020-08-10 02:16:47', '2020-08-15 18:52:01', 0, 0, '3', '牙贝', '34', '牙贝，医院牙科项目', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1292773879323852802', NULL, NULL, '2020-08-10 10:44:46', '2020-08-15 18:51:55', 0, 0, '4', '慧车行', '34', '鹏程慧车行系统', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469448399593474', NULL, NULL, '2020-08-20 15:29:38', '2020-08-20 15:29:38', 0, 0, 'DISABLE', '是否禁用', '1290688121255587841', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469518025039873', NULL, NULL, '2020-08-20 15:29:55', '2020-08-20 15:29:55', 0, 0, '1', '禁用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296469564455985154', NULL, NULL, '2020-08-20 15:30:06', '2020-08-20 15:30:06', 0, 0, '0', '启用', '1296469448399593474', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995475064434690', NULL, NULL, '2020-08-22 02:19:52', '2020-08-22 02:19:52', 0, 0, 'AUTHORITY_TYPE', '权限终端', '1290686507555844098', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995560007479298', NULL, NULL, '2020-08-22 02:20:12', '2020-08-22 12:24:42', 0, 0, '0', '管理端', '1296995475064434690', '管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995610632728578', NULL, NULL, '2020-08-22 02:20:24', '2020-08-22 12:24:51', 0, 0, '1', '用户端', '1296995475064434690', '用户端, 类上标有该参数所有接口都会默认被列为-[需登录]', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995742778470401', NULL, NULL, '2020-08-22 02:20:55', '2020-08-22 02:20:55', 0, 0, 'AUTHORITY_STATE', '权限状态', '1290686507555844098', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296995839977271297', NULL, NULL, '2020-08-22 02:21:19', '2020-08-22 02:23:10', 0, 0, '0', '无', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996062090833922', NULL, NULL, '2020-08-22 02:22:12', '2020-08-22 02:22:31', 0, 0, '1', '需登录', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('1296996224863383554', NULL, NULL, '2020-08-22 02:22:50', '2020-08-22 02:22:50', 0, 0, '2', '需登录+授权', '1296995742778470401', '-', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('34', NULL, NULL, '2020-07-25 09:29:07', '2020-08-15 18:52:07', 0, 0, 'BASE_ITEM', '项目名称', '1290686763962036225', 'Task + Bug 展示的项目名称', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('35', NULL, NULL, '2020-07-25 09:29:07', '2020-08-09 17:36:50', 0, 0, '1', 'xj-admin', '34', '个人开发项目--通用后台管理系统', 0, 0);
INSERT INTO `t_admin_dictionary` VALUES ('36', NULL, NULL, '2020-07-25 09:29:07', '2020-08-09 18:35:47', 0, 0, '2', 'xj-cloud', '34', '个人搭建的基于spring-colud-ailbaba微服务基础架构', 0, 0);

-- ----------------------------
-- Table structure for t_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_menu`;
CREATE TABLE `t_admin_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `pid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指定父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单url',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `root` int(1) NOT NULL DEFAULT 0 COMMENT '目录级别(1-系统, 2-菜单 ，3-页面, 4-按钮)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_menu
-- ----------------------------
INSERT INTO `t_admin_menu` VALUES ('1', NULL, NULL, '2020-07-25 09:29:38', '2020-08-22 09:50:34', 0, 0, '0', '兮家-系统管理', '', '0', 1, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('10', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '9', '用户管理', '', '/', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('100', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '外链', '', 'layui-icon-file-b', 600, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('101', NULL, NULL, '2020-07-25 09:29:38', '2020-08-07 16:41:52', 0, 0, '134', 'layui模板', 'http://localhost:9049/views/index.html', 'layui-icon-file-b', 60001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('102', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', 'layui官网', 'https://www.layui.com', 'layui-icon-file-b', 60002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('11', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '10', '用户信息', '/page/game_user_user', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('123', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '135', '我的CSDN', 'https://blog.csdn.net/qq_41463655', 'layui-icon-file-b', 60006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('124', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '百度', 'http://www.baidu.com', 'layui-icon-file-b', 60005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('125', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '135', '我的码云', 'https://gitee.com/wslxm', 'layui-icon-file-b', 60004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('126', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '阿里云', 'https://homenew.console.aliyun.com', 'layui-icon-file-b', 60003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879303106289665', NULL, NULL, '2020-07-31 00:49:06', '2020-07-31 01:26:21', 0, 0, '1', 'MD编辑器', '', 'layui-icon-file-b', 500, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879370219347969', NULL, NULL, '2020-07-31 00:49:22', '2020-07-31 01:25:51', 0, 0, '1288879303106289665', '编辑', '/page/modules_dev_markdown_index', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1288879436422242305', NULL, NULL, '2020-07-31 00:49:38', '2020-07-31 01:25:54', 0, 0, '1288879303106289665', '展示', '/page/modules_dev_markdown_detail', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('129', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '蚂蚁课堂', 'http://www.mayikt.com/', 'layui-icon-file-b', 60007, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297047088646905857', NULL, NULL, '2020-08-22 05:44:58', '2020-08-22 06:13:44', 0, 0, '4', '接口管理', '/page/modules_admin_authority_authority', 'layui-icon-file-b', 10005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('1297533242571763714', NULL, NULL, '2020-08-23 13:56:45', '2020-08-23 13:56:45', 0, 0, '4', 'Banner 管理', '	/page/modules_admin_adminBanner_adminBanner', 'layui-icon-file-b', 10008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('13', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '0', '测试系统', '', '1', 3, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('130', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '腾讯课堂', 'https://ke.qq.com/user/index/index.html#/plan/cid=291872&term_id=102601151', 'layui-icon-file-b', 60008, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('131', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '133', '哔哩哔哩', 'https://www.bilibili.com/', 'layui-icon-file-b', 60009, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('133', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:23', 0, 0, '100', '娱乐', '', 'layui-icon-file-b', 60003, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('134', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:26', 0, 0, '100', '工作学习', '', 'layui-icon-file-b', 60002, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('135', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:10', 0, 0, '100', '我的系列', '', 'layui-icon-file-b', 60001, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('136', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '133', 'bilbil视频下载', 'https://xbeibeix.com/api/bilibili/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('137', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '134', '蚂蚁课堂资料下载', 'http://down.mayikt.com/', 'layui-icon-file-b', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('138', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:47:32', 0, 0, '95', 'BUG修复', '/page/modules_dev_devBug_devBug', 'layui-icon-file-b', 40002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('139', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:30:30', 0, 0, '100', '在线工具', '', 'layui-icon-file-b', 60004, 2, 1);
INSERT INTO `t_admin_menu` VALUES ('14', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '13', '一级菜单', '', '1', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('140', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:22:51', 0, 0, '91', '聊天室', '/page/others_websocket_websocket', 'layui-icon-file-b', 50002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('141', NULL, NULL, '2020-07-30 18:16:24', '2020-07-30 18:20:12', 0, 0, '1', '代码生成', '', 'layui-icon-file-b', 300, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('15', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 00:13:34', 0, 0, '14', '页面', '/aaaa', '1', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('21', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:21', 0, 0, '4', '系统用户', '/page/modules_admin_user_user', '', 10002, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('22', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:25', 0, 0, '4', '角色管理', '/page/modules_admin_role_role', '', 10003, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('23', NULL, NULL, '2020-07-25 09:29:38', '2020-08-22 06:12:24', 0, 0, '4', '角色菜单权限', '/page/modules_admin_role_roleMenuAuth', '', 10006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('24', NULL, NULL, '2020-07-25 09:29:38', '2020-08-22 06:12:27', 0, 0, '4', '角色URL权限', '/page/modules_admin_role_roleUrlAuth', '', 10007, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('25', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:37', 0, 0, '4', '字典管理', '/page/modules_admin_dictionary_dictionary', '', 10004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('27', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:36:40', 0, 0, '14', '登录', '/page/modules_admin_user_login', '', 0, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('30', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:19:04', 0, 0, '141', '数据库表', '/page/others_generatecode_dataBase_dataBase', '1', 40006, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('4', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '系统管理', '', 'layui-icon-set', 100, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('40', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '13', 'aaa', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('43', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '43', 'ccc', '', 'layui-icon-file-b', 0, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('7', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:37:20', 0, 0, '4', '菜单管理', '/page/modules_admin_menu_menu', 'layui-icon-home', 10001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('9', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '0', '兮家-次元空间', '', '0', 2, 1, 0);
INSERT INTO `t_admin_menu` VALUES ('91', NULL, NULL, '2020-07-25 09:29:38', '2020-08-15 19:39:18', 0, 0, '1', '单页应用', '', 'layui-icon-file-b', 500, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('92', NULL, NULL, '2020-07-25 09:29:38', '2020-08-15 19:39:26', 0, 0, '91', '栅格应用', '/page/others_front_show_show', 'layui-icon-file-b', 50000, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('93', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:22:48', 0, 0, '91', '文字注释', '/page/others_front_symbol_main', 'layui-icon-file-b', 50001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('95', NULL, NULL, '2020-07-25 09:29:38', '2020-07-25 09:29:38', 0, 0, '1', '开发', '', 'layui-icon-file-b', 400, 2, 0);
INSERT INTO `t_admin_menu` VALUES ('96', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 15:14:31', 0, 0, '95', '开发规范', '/page/modules_dev_devNorm_devNorm', 'layui-icon-file-b', 40005, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('97', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 17:46:27', 0, 0, '95', '开发任务', '/page/modules_dev_devTask_devTask', 'layui-icon-file-b', 40001, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('98', NULL, NULL, '2020-07-25 09:29:38', '2020-07-30 18:29:24', 0, 0, '95', '更新记录', '/2', 'layui-icon-file-b', 40004, 3, 0);
INSERT INTO `t_admin_menu` VALUES ('99', NULL, NULL, '2020-07-25 09:29:38', '2020-07-31 00:40:56', 0, 0, '95', '学习计划', '/page/modules_dev_devStudy_devStudy', 'layui-icon-file-b', 40003, 3, 0);

-- ----------------------------
-- Table structure for t_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role`;
CREATE TABLE `t_admin_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描叙',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '禁用(0-启用 1-禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role
-- ----------------------------
INSERT INTO `t_admin_role` VALUES ('1', NULL, NULL, '2020-07-25 09:30:08', '2020-08-07 17:47:34', 0, 0, '系统管理员', '拥有该系统所有操作权限', 0);
INSERT INTO `t_admin_role` VALUES ('2', NULL, NULL, '2020-07-25 09:30:08', '2020-08-07 18:00:37', 0, 0, '开发人员', '-', 0);
INSERT INTO `t_admin_role` VALUES ('3', NULL, NULL, '2020-07-25 09:30:08', '2020-08-07 17:31:43', 0, 0, '测试人员', '-', 0);
INSERT INTO `t_admin_role` VALUES ('31', NULL, NULL, '2020-07-25 09:30:08', '2020-08-07 17:47:35', 0, 0, '系统体验用户', '开发给第三方人员使用的角色，多数功能接口权限受限', 0);

-- ----------------------------
-- Table structure for t_admin_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_auth`;
CREATE TABLE `t_admin_role_auth`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `auth_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色/接口权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_auth
-- ----------------------------
INSERT INTO `t_admin_role_auth` VALUES ('1297258379932196866', NULL, NULL, '2020-08-22 19:44:33', '2020-08-22 19:44:33', 0, 0, '457203749929226241', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379936391169', NULL, NULL, '2020-08-22 19:44:33', '2020-08-22 19:44:33', 0, 0, '457203749933420546', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585474', NULL, NULL, '2020-08-22 19:44:33', '2020-08-22 19:44:33', 0, 0, '457203749929226244', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585475', NULL, NULL, '2020-08-22 19:44:33', '2020-08-22 19:44:33', 0, 0, '457203749933420545', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585476', NULL, NULL, '2020-08-22 19:44:33', '2020-08-22 19:44:33', 0, 0, '457203749933420544', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585477', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749929226242', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585478', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749929226243', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585479', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749895671808', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585480', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749899866112', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379940585481', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749899866113', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779778', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749899866114', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779779', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749899866115', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779780', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749916643331', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779781', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749920837634', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779782', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749920837637', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779783', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749920837636', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779784', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749920837632', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379944779785', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749916643332', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974082', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749920837635', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974083', NULL, NULL, '2020-08-22 19:44:34', '2020-08-22 19:44:34', 0, 0, '457203749920837633', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974084', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749920837638', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974085', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749920837639', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974086', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749904060416', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974087', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749904060421', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974088', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749908254721', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974089', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749904060419', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379948974090', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749908254720', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168385', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749908254722', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168386', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749908254723', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168387', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749904060417', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168388', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749904060418', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168389', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749904060420', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168390', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749946003458', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168391', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749946003459', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379953168392', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749946003460', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362690', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749946003461', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362691', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749941809157', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362692', NULL, NULL, '2020-08-22 19:44:35', '2020-08-22 19:44:35', 0, 0, '457203749946003457', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362693', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749946003456', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362694', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749933420547', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362695', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749937614849', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362696', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749937614848', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379957362697', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749937614850', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556993', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749933420549', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556994', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749933420548', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556995', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749912449024', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556996', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749916643329', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556997', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749916643330', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556998', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749912449026', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961556999', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749912449027', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961557000', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749912449025', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379961557001', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749916643328', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751297', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749920837640', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751298', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749925031938', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751299', NULL, NULL, '2020-08-22 19:44:36', '2020-08-22 19:44:36', 0, 0, '457203749925031940', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751300', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749925031939', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751301', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749925031943', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751302', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749929226240', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751303', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749925031942', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751304', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749925031936', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751305', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749925031937', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297258379965751306', NULL, NULL, '2020-08-22 19:44:37', '2020-08-22 19:44:37', 0, 0, '457203749925031941', '1');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006766202881', NULL, NULL, '2020-08-23 04:15:39', '2020-08-23 04:15:39', 0, 0, '457203749929226241', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006782980097', NULL, NULL, '2020-08-23 04:15:39', '2020-08-23 04:15:39', 0, 0, '457203749933420545', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006782980098', NULL, NULL, '2020-08-23 04:15:39', '2020-08-23 04:15:39', 0, 0, '457203749895671808', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006782980099', NULL, NULL, '2020-08-23 04:15:39', '2020-08-23 04:15:39', 0, 0, '457203749899866112', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006782980100', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749899866113', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006787174402', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749916643331', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006787174403', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749920837637', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006787174404', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749920837636', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006791368706', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749920837632', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006791368707', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749904060416', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006791368708', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749908254721', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006791368709', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749904060419', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006795563010', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749908254720', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006795563011', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749908254722', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006795563012', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749908254723', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006795563013', NULL, NULL, '2020-08-23 04:15:40', '2020-08-23 04:15:40', 0, 0, '457203749946003458', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006799757313', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749946003459', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006799757314', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749941809157', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006799757315', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749946003457', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006803951617', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749946003456', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006803951618', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749933420547', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006803951619', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749937614850', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006803951620', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749912449024', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006808145922', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749916643330', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006808145923', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749912449026', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006808145924', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749912449027', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006812340226', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749920837640', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006812340227', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749925031939', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006812340228', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749925031943', '31');
INSERT INTO `t_admin_role_auth` VALUES ('1297387006812340229', NULL, NULL, '2020-08-23 04:15:41', '2020-08-23 04:15:41', 0, 0, '457203749929226240', '31');

-- ----------------------------
-- Table structure for t_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_menu`;
CREATE TABLE `t_admin_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色/菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_menu
-- ----------------------------
INSERT INTO `t_admin_role_menu` VALUES ('1291794867738976257', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '1', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867743170562', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '4', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867743170563', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '7', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364866', NULL, NULL, '2020-08-07 17:54:30', '2020-08-07 17:54:30', 0, 0, '21', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364867', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '22', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364868', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '25', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364869', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '5', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364870', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '23', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364871', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '24', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867747364872', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '141', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559170', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '30', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559171', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '95', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559172', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '97', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559173', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '138', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559174', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '99', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867751559175', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '98', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753474', NULL, NULL, '2020-08-07 17:54:31', '2020-08-07 17:54:31', 0, 0, '96', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753475', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '1288879303106289665', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753476', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '1288879436422242305', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753477', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '1288879370219347969', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753478', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '100', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753479', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '135', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867755753480', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '125', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947778', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '123', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947779', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '134', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947780', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '137', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947781', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '101', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947782', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '102', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867759947783', NULL, NULL, '2020-08-07 17:54:32', '2020-08-07 17:54:32', 0, 0, '126', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142082', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '124', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142083', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '129', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142084', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '130', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142085', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '133', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142086', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '136', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142087', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '131', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142088', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '139', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867764142089', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '9', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336385', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '10', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336386', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '11', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336387', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '91', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336388', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '92', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336389', NULL, NULL, '2020-08-07 17:54:33', '2020-08-07 17:54:33', 0, 0, '93', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336390', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '140', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336391', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '13', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336392', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '40', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336393', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '14', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336394', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '27', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1291794867768336395', NULL, NULL, '2020-08-07 17:54:34', '2020-08-07 17:54:34', 0, 0, '15', '2');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036351057922', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '1', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036430749698', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '4', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036430749699', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '7', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036430749700', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '21', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036439138306', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '22', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036447526914', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '25', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036451721218', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '1297047088646905857', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036451721219', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '23', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036451721220', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '24', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036455915521', NULL, NULL, '2020-08-23 04:11:48', '2020-08-23 04:11:48', 0, 0, '141', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036455915522', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '30', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036455915523', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '95', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036455915524', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '97', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036455915525', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '138', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036464304130', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '99', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036464304131', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '98', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036464304132', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '96', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036468498433', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '1288879303106289665', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036468498434', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '1288879370219347969', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036468498435', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '1288879436422242305', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036468498436', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '91', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036476887042', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '92', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036476887043', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '93', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036476887044', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '140', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036481081345', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '100', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036481081346', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '135', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036481081347', NULL, NULL, '2020-08-23 04:11:49', '2020-08-23 04:11:49', 0, 0, '125', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036481081348', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '123', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275649', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '134', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275650', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '137', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275651', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '101', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275652', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '102', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275653', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '126', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275654', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '124', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275655', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '129', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275656', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '130', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275657', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '133', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036485275658', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '136', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664257', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '131', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664258', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '139', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664259', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '9', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664260', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '10', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664261', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '11', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664262', NULL, NULL, '2020-08-23 04:11:50', '2020-08-23 04:11:50', 0, 0, '13', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664263', NULL, NULL, '2020-08-23 04:11:51', '2020-08-23 04:11:51', 0, 0, '14', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664264', NULL, NULL, '2020-08-23 04:11:51', '2020-08-23 04:11:51', 0, 0, '15', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664265', NULL, NULL, '2020-08-23 04:11:51', '2020-08-23 04:11:51', 0, 0, '27', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297386036493664266', NULL, NULL, '2020-08-23 04:11:51', '2020-08-23 04:11:51', 0, 0, '40', '31');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301824696321', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '1', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301828890625', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '4', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301828890626', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '7', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301833084930', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '21', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301833084931', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '22', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301833084932', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '25', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301833084933', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '1297047088646905857', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301833084934', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '23', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279233', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '24', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279234', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '1297533242571763714', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279235', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '141', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279236', NULL, NULL, '2020-08-23 13:56:59', '2020-08-23 13:56:59', 0, 0, '30', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279237', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '95', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279238', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '97', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279239', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '138', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279240', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '99', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301837279241', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '98', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473537', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '96', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473538', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '1288879303106289665', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473539', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '1288879370219347969', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473540', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '1288879436422242305', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473541', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '91', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473542', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '92', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473543', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '93', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301841473544', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '140', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667841', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '100', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667842', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '135', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667843', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '125', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667844', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '123', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667845', NULL, NULL, '2020-08-23 13:57:00', '2020-08-23 13:57:00', 0, 0, '134', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667846', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '137', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667847', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '101', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301845667848', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '102', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862145', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '126', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862146', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '124', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862147', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '129', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862148', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '130', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862149', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '133', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862150', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '136', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862151', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '131', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862152', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '139', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301849862153', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '9', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056449', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '10', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056450', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '11', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056451', NULL, NULL, '2020-08-23 13:57:01', '2020-08-23 13:57:01', 0, 0, '13', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056452', NULL, NULL, '2020-08-23 13:57:02', '2020-08-23 13:57:02', 0, 0, '40', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056453', NULL, NULL, '2020-08-23 13:57:02', '2020-08-23 13:57:02', 0, 0, '14', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056454', NULL, NULL, '2020-08-23 13:57:02', '2020-08-23 13:57:02', 0, 0, '27', '1');
INSERT INTO `t_admin_role_menu` VALUES ('1297533301854056455', NULL, NULL, '2020-08-23 13:57:02', '2020-08-23 13:57:02', 0, 0, '15', '1');
INSERT INTO `t_admin_role_menu` VALUES ('505', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '1', '3');
INSERT INTO `t_admin_role_menu` VALUES ('506', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '4', '3');
INSERT INTO `t_admin_role_menu` VALUES ('507', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '7', '3');
INSERT INTO `t_admin_role_menu` VALUES ('508', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '21', '3');
INSERT INTO `t_admin_role_menu` VALUES ('509', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '22', '3');
INSERT INTO `t_admin_role_menu` VALUES ('510', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '25', '3');
INSERT INTO `t_admin_role_menu` VALUES ('511', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '5', '3');
INSERT INTO `t_admin_role_menu` VALUES ('512', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '23', '3');
INSERT INTO `t_admin_role_menu` VALUES ('513', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '24', '3');
INSERT INTO `t_admin_role_menu` VALUES ('514', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '6', '3');
INSERT INTO `t_admin_role_menu` VALUES ('515', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '30', '3');
INSERT INTO `t_admin_role_menu` VALUES ('516', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '19', '3');
INSERT INTO `t_admin_role_menu` VALUES ('517', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '9', '3');
INSERT INTO `t_admin_role_menu` VALUES ('518', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '10', '3');
INSERT INTO `t_admin_role_menu` VALUES ('519', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '11', '3');
INSERT INTO `t_admin_role_menu` VALUES ('520', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '38', '3');
INSERT INTO `t_admin_role_menu` VALUES ('521', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '37', '3');
INSERT INTO `t_admin_role_menu` VALUES ('522', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '13', '3');
INSERT INTO `t_admin_role_menu` VALUES ('523', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '14', '3');
INSERT INTO `t_admin_role_menu` VALUES ('524', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '15', '3');
INSERT INTO `t_admin_role_menu` VALUES ('525', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '16', '3');
INSERT INTO `t_admin_role_menu` VALUES ('526', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '17', '3');
INSERT INTO `t_admin_role_menu` VALUES ('527', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '27', '3');
INSERT INTO `t_admin_role_menu` VALUES ('528', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '39', '3');
INSERT INTO `t_admin_role_menu` VALUES ('529', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '40', '3');
INSERT INTO `t_admin_role_menu` VALUES ('530', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '41', '3');
INSERT INTO `t_admin_role_menu` VALUES ('531', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '42', '3');
INSERT INTO `t_admin_role_menu` VALUES ('532', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '43', '3');
INSERT INTO `t_admin_role_menu` VALUES ('533', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '46', '3');
INSERT INTO `t_admin_role_menu` VALUES ('534', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '66', '3');
INSERT INTO `t_admin_role_menu` VALUES ('535', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '79', '3');
INSERT INTO `t_admin_role_menu` VALUES ('71', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '5', '0');
INSERT INTO `t_admin_role_menu` VALUES ('72', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '23', '0');
INSERT INTO `t_admin_role_menu` VALUES ('73', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '4', '0');
INSERT INTO `t_admin_role_menu` VALUES ('74', NULL, NULL, '2020-07-25 09:30:40', '2020-07-25 09:30:40', 0, 0, '7', '0');

-- ----------------------------
-- Table structure for t_admin_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_role_user`;
CREATE TABLE `t_admin_role_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除字段(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色/用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_role_user
-- ----------------------------
INSERT INTO `t_admin_role_user` VALUES ('1', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '1', '1');
INSERT INTO `t_admin_role_user` VALUES ('1289941838182023169', NULL, NULL, '2020-08-02 15:11:14', '2020-08-02 15:11:14', 0, 0, '1289941795546923009', '1');
INSERT INTO `t_admin_role_user` VALUES ('1289941838194606081', NULL, NULL, '2020-08-02 15:11:14', '2020-08-02 15:11:14', 0, 0, '1289941795546923009', '2');
INSERT INTO `t_admin_role_user` VALUES ('1291570476593795074', NULL, NULL, '2020-08-07 03:02:52', '2020-08-07 03:02:52', 0, 0, '1291570418964058114', '2');
INSERT INTO `t_admin_role_user` VALUES ('1297388491570790401', NULL, NULL, '2020-08-23 04:21:33', '2020-08-23 04:21:33', 0, 0, '20', '31');
INSERT INTO `t_admin_role_user` VALUES ('42', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '1');
INSERT INTO `t_admin_role_user` VALUES ('43', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '2');
INSERT INTO `t_admin_role_user` VALUES ('44', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '33', '3');
INSERT INTO `t_admin_role_user` VALUES ('45', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '1');
INSERT INTO `t_admin_role_user` VALUES ('46', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '2');
INSERT INTO `t_admin_role_user` VALUES ('47', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '18', '3');
INSERT INTO `t_admin_role_user` VALUES ('49', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '42', '2');
INSERT INTO `t_admin_role_user` VALUES ('7', NULL, NULL, '2020-07-25 09:30:54', '2020-07-25 09:30:54', 0, 0, '1', '2');

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常 1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `head` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号/用户/手机号',
  `full_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `age` int(3) NOT NULL COMMENT '年龄',
  `gender` int(1) NOT NULL DEFAULT 0 COMMENT '性别(1男，0女)',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '是否禁用(0-否，1-是)',
  `reg_time` datetime(0) NOT NULL COMMENT '注册时间',
  `ent_time` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('1', NULL, NULL, '2020-08-02 15:11:04', '2020-08-23 15:52:22', 0, 0, 'http://xijia.plus/oss/file/image/head/20200822150143006266-5.png', '1720696548', '王松', 'dfd5e22c8ee4de7da4f07a75fefb6420', '四川成都', 24, 1, 0, '2020-08-02 23:11:05', '2020-08-23 23:52:22');
INSERT INTO `t_admin_user` VALUES ('1291570418964058114', NULL, NULL, '2020-08-07 03:02:38', '2020-08-23 04:34:33', 0, 0, 'http://xijia.plus/oss/file/image/head/20200807110236510316-2.jpg', '15828327664', '顾全', '229db5460300fdcede08e56a90529475', '四川成都', 25, 1, 0, '2020-08-07 11:02:39', '2020-08-10 21:35:09');
INSERT INTO `t_admin_user` VALUES ('20', NULL, NULL, '2020-07-25 09:31:07', '2020-08-23 15:16:11', 0, 0, 'http://xijia.plus/oss/file/image/head/20200712000102739895-1.png', 'admin', '游客', '58a42a22f652b6cd9a34f840cd4ea559', '四川成都', 0, 0, 0, '2020-01-31 10:15:07', '2020-08-23 23:16:12');

-- ----------------------------
-- Table structure for t_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_basic`;
CREATE TABLE `t_basic`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0-正常  1-删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统基表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_dev_bug
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_bug`;
CREATE TABLE `t_dev_bug`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `task_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指派给id',
  `item` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目(字典表code)',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务内容',
  `type` int(1) NOT NULL DEFAULT 1 COMMENT '任务类型(1-管理端 2-用户端 3-app端  4-所有端 )',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)',
  `planned_time` datetime(0) NOT NULL COMMENT '计划完成时间',
  `ent_time` datetime(0) DEFAULT NULL COMMENT '实际完成时间',
  `estimate_time` double(11, 2) NOT NULL COMMENT '预计耗时(小时)',
  `take_up_time` double(11, 2) DEFAULT NULL COMMENT '实际耗时(小时)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_bug
-- ----------------------------
INSERT INTO `t_dev_bug` VALUES ('1289559552445919234', '1', NULL, '2020-08-01 13:52:10', '2020-08-01 13:53:29', 0, 0, '1', 'item-xj-admin', 'id删除异常', 'id删除字段类型错误', 1, 2, '2020-08-01 00:00:00', '2020-08-01 21:53:30', 1.00, 1.00);
INSERT INTO `t_dev_bug` VALUES ('1289559832394739714', '1', NULL, '2020-08-01 13:53:17', '2020-08-01 13:53:35', 0, 0, '1', 'item-xj-admin', '权限url扫描异常', '更新权限url 时后台错误', 1, 2, '2020-08-01 00:00:00', '2020-08-01 21:53:35', 1.00, 2.00);
INSERT INTO `t_dev_bug` VALUES ('1289980627638067202', '1', NULL, '2020-08-02 17:45:23', '2020-08-04 04:16:54', 0, 0, '1', 'item-xj-admin', '服务器添加头像 405 Method Not Allowed', '405 Method Not Allowed，\n前端ajax发起post请求，结果返回405，Not allow method！原因为 Apache、IIS、Nginx等绝大多数web服务器，\n都不允许静态文件响应POST请求。\n\n错误原因：接口与Nginx配置冲突，访问地址不对 oss/', 1, 2, '2020-08-09 00:00:00', '2020-08-04 04:16:54', 2.00, 2.00);
INSERT INTO `t_dev_bug` VALUES ('1291802505147133953', '1', NULL, '2020-08-07 18:24:51', '2020-08-09 10:57:26', 0, 0, '1', 'item-xj-admin', '菜单分配', '选择菜单，角色回显错误', 1, 2, '2020-08-08 00:00:00', '2020-08-09 10:57:27', 1.00, 1.00);
INSERT INTO `t_dev_bug` VALUES ('1292780965478813697', '1', NULL, '2020-08-10 11:12:55', '2020-08-10 11:12:55', 0, 0, '1', '4', '角色菜单权限', '角色菜单权限分配后无效', 1, 0, '2020-08-11 00:00:00', NULL, 4.00, NULL);

-- ----------------------------
-- Table structure for t_dev_norm
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_norm`;
CREATE TABLE `t_dev_norm`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规范名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规范内容(md-富文本)',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发规范' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_norm
-- ----------------------------
INSERT INTO `t_dev_norm` VALUES ('1289116285992501250', NULL, NULL, '2020-07-31 16:30:47', '2020-08-01 10:22:16', 0, 0, '测试添加', '啊是发撒\nsafsafasface[吐] 11', 0);
INSERT INTO `t_dev_norm` VALUES ('1289126554407862273', NULL, NULL, '2020-07-31 17:11:36', '2020-07-31 17:15:20', 0, 0, '测试编辑', '\n表头|表头|表头\n:---:|:--:|:---:\n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n内容|内容|内容 \n', 0);
INSERT INTO `t_dev_norm` VALUES ('1289133797173895169', NULL, NULL, '2020-07-31 17:40:22', '2020-07-31 17:40:22', 0, 0, '测试-数据查看', 'face[嘻嘻] face[嘻嘻] face[嘻嘻]\n\n![图片加载失败](https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1576026414&di=4121c010940f72cab1cbcea65e698d0f&src=http://hbimg.b0.upaiyun.com/b1cce6f996734bdbb9b3fb9ef7705deabc980e35493b-ysf8BZ_fw658)\n\n[http://www.baidu.com](http://www.baidu.com)\n\n - 是的发送的\n - 阿斯顿发生大\n - 阿斯顿发斯蒂芬\n\n\n1. 阿斯顿发斯蒂芬\n2. 阿斯顿发送到发士大夫\n3. 阿斯顿发送到发大\n\n\n\n~~~\n	public static void main(String[] args){\n		System.out.print(\"Hello World\");\n	}\n~~~\n\n -----\n\n\n> Java世界上最好的语言\n\n\n -  跨平台\n -  面向对象\n -  牛掰\n\n -----\n\n\n1. 大众化\n2. 应用广\n3. 方向全\n\n -----\n\n\n表头|表头|表头\n:---:|:--:|:---:\n内容|内容|内容\n\n -----\n\n\n强制换行（双空格加回车）\n\n\n\n强制换行\n		', 0);
INSERT INTO `t_dev_norm` VALUES ('1289149437230190594', NULL, NULL, '2020-07-31 18:42:31', '2020-07-31 18:48:18', 0, 0, '接口方法统一命名', '\n## 方法统一命名\n\n### 查询\n| 方法 | 方法命名| 必传参数  |\n|:--------| :-------------| :-------------:|\n| ID查询 | findId|id|\n| 分页查询 | findPage | current=页数 size=记录数|\n| 所有查询 | findList | 无|\n| 查询 必须 xx+yy 条件 | findXxAndYy | xx && yy|\n| 查询 根据 xx+yy 其中一个| findXxOrYy | xx 或 yy|\n\n### 添加\n| 方法 | 方法命名  | 必传参数 |\n|:--------| :-------------| :-------------:|\n| 添加 | add  或  insert | dto\n\n### 修改\n| 方法 | 方法命名  | 必传参数 |\n|:--------| :-------------| :-------------:|\n| ID编辑所有 | upd | dto+id|\n| 编辑 xx 字段 | updXx |  xx|\n| 编辑 xx + yy字段 | updXxAndYy | xx + yy|\n\n### 删除\n| 方法 | 方法命名 | 必传参数      |\n|:--------| :-------------| :-------------:|\n| ID删除 | removeId |id|\n| 根据 xx字段删除 | removeXx | xx|\n| 根据 xx + yy 字段删除 | removeXxAndYy | xx + yy|\n\n### 判断\n| 方法 | 方法命名 | 必传参数   | 返回\n|:--------| :-------------| :-------------:| :-------------:|\n| 判断???  | is???  | 判断必传递参数 | true 或 false|\n', 0);

-- ----------------------------
-- Table structure for t_dev_renew
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_renew`;
CREATE TABLE `t_dev_renew`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新内容(md-富文本)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '更新内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_dev_study
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_study`;
CREATE TABLE `t_dev_study`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除(0：正常  1：删除)',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '学习内容(md-富文本)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学习计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_study
-- ----------------------------
INSERT INTO `t_dev_study` VALUES ('1288877310027554818', NULL, NULL, '2020-07-31 00:41:11', '2020-07-31 00:41:11', 0, 0, '1', '2', 1);

-- ----------------------------
-- Table structure for t_dev_task
-- ----------------------------
DROP TABLE IF EXISTS `t_dev_task`;
CREATE TABLE `t_dev_task`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建账户id',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新账户id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  `deleted` int(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除（0：正常  1：删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `task_user_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '指派给id(可以多个)',
  `item` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目(字典表code)',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务内容',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '任务类型(1-管理端 2-用户端 3-app端  4-所有端 可以同时存在多个)',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '任务状态(0-未开始 1-正在进行 2-已完成 3-已撤销)',
  `planned_time` datetime(0) NOT NULL COMMENT '计划完成时间',
  `ent_time` datetime(0) DEFAULT NULL COMMENT '实际完成时间',
  `estimate_time` double(11, 2) NOT NULL COMMENT '预计耗时(小时)',
  `take_up_time` double(11, 2) DEFAULT NULL COMMENT '实际耗时(小时)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '开发任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dev_task
-- ----------------------------
INSERT INTO `t_dev_task` VALUES ('1', '2020-06-27 12:04:22', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '开发任务模块', '类型，状态搜索，开始，完成，撤销任务等', '1', 3, '2020-05-05 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('10', '2020-07-25 10:10:04', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '数据库表结构通用字段重定义', '除id外的6大通用字段\n-- 添加\nalter table t_dev_norm add `create_user`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT \'创建账户id\';\nalter table t_dev_norm add `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT \'更新账户id\';\nalter table t_dev_norm add `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT \'创建时间(自动插入)\';\nalter table t_dev_norm add `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT \'更新时间(自动插入)\';\nalter table t_dev_norm add `deleted` int(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT \'逻辑删除字段(0：正常 1：删除)\';\nalter table t_dev_norm add `version` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT \'乐观锁\';', '1', 2, '2020-07-25 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('11', '2020-07-25 10:11:52', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '任务字体颜色修改', '已完成改为绿色\n未开始改为红色\n正在执行默认粉红色\n撤销的默认黑色', '1', 2, '2020-07-25 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('12', '2020-07-25 10:18:52', NULL, NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '添加任务完成时间', '任务完成后没有完成时间', '1', 2, '2020-07-25 00:00:00', '2020-07-25 10:39:28', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289557981020565505', '2020-08-01 13:45:56', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '数据表-添加是否必填字段', '添加是否必填，方便查看字段', '1', 2, '2020-08-01 00:00:00', '2020-08-01 22:15:42', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289558965448880129', '2020-08-01 13:49:50', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '代码中所有 TODO 标识处理', ' TODO 代处理代码, 勿烂用', '1', 2, '2020-08-02 00:00:00', '2020-08-02 20:36:57', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289559346019053569', '2020-08-01 13:51:21', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '代码生成器controller优化', '1、添加findId 查询\n2、分页查询排序改为根据创建时间 DESC倒序\n3、添加 mapper.xml 层代码\n4、代码生成器DTO添加必填验证注解', '1', 2, '2020-08-02 00:00:00', '2020-08-02 10:35:57', 2.00, 4.00);
INSERT INTO `t_dev_task` VALUES ('1289580275457675266', '2020-08-01 15:14:31', '1', NULL, '2020-08-09 17:36:38', 0, 0, '1', '1', '修改返回对象+返回枚举', '通用返回对象优化，返回枚举类优化', '1', 2, '2020-08-02 00:00:00', '2020-08-02 11:21:45', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289734714160132098', '2020-08-02 01:28:12', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'jwt TOKEN 自动刷新', 'token到期会自动失效，任意操作后自动刷新token', '1', 2, '2020-08-02 00:00:00', '2020-08-02 23:38:47', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('1289752060748636162', '2020-08-02 02:37:08', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'JSR 303 验证返回错误信息处理', '全局异常处理jsr303返回dto字段标注的异常内容', '1', 2, '2020-08-02 00:00:00', '2020-08-02 12:41:35', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289763468169199617', '2020-08-02 03:22:27', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'req 请求参数不匹配返回错误处理', 'req 请求参数不匹配返回对应错误信息', '1', 2, '2020-08-02 00:00:00', '2020-08-02 12:41:39', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289783274108891137', '2020-08-02 04:41:09', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'req 没有请求参数异常信息返回处理', '优化请求错误返回信息', '1', 2, '2020-08-02 00:00:00', '2020-08-02 12:41:43', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1289916333886050305', '2020-08-02 13:29:53', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '接口名称统一', '部分接口名称不统一\n1、分页查询 --> findPage\n2、查询使用 --> findList\n3、id查询 --> findId\n4、id编辑  --> upd\n5、添加 --> insert\n6 、id删除  -->  del\n7、多id删除  delByIds', '1', 2, '2020-08-02 00:00:00', '2020-08-02 23:27:07', 2.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('1289928054088421377', '2020-08-02 14:16:27', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '请求日志优化', '请求log 日志处理', '1', 2, '2020-08-02 00:00:00', '2020-08-02 22:29:08', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289928341167558658', '2020-08-02 14:17:36', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '登录处理，目前只支持账号密码登录', '1、账号密码登录\n2、手机号+验证码登录\n3、手机号+密码登录', '1', 0, '2020-08-02 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289928892554956802', '2020-08-02 14:19:47', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '阿里云短信', '添加阿里云短信工具类，用于登录等其他用途', '1', 2, '2020-08-02 00:00:00', '2020-08-04 23:41:34', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289929809945690114', '2020-08-02 14:23:26', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'swagger 方法@ApiOperation 优化', '统一格式为：\n@ApiOperation(value = \"操作功能\",notes= \"详细描叙\")', '1', 2, '2020-08-06 00:00:00', '2020-08-04 06:41:27', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289937151382781953', '2020-08-02 14:52:36', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '前端页面接口参数命名优化', '所有html 接口地址参数命名统一', '1', 0, '2020-08-06 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1289937407927386113', '2020-08-02 14:53:37', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '所有类名优化', '以数据库名称为准，如: t_admin_user = AdminUser', '1', 2, '2020-08-06 00:00:00', '2020-08-06 09:28:40', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289942318287224834', '2020-08-02 15:13:08', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典管理删除优化', '删除父级时同时删除所有子层级实际\n优化查询，无极限\n优化添加，code 不重复\n优化应该，code 不重复', '1', 2, '2020-08-06 00:00:00', '2020-08-04 06:40:13', 2.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('1289949333222977538', '2020-08-02 15:41:01', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发任务功能优化', '点击第二页未携带第一页的查询条件', '1', 2, '2020-08-06 00:00:00', '2020-08-03 00:26:39', 1.00, 1.50);
INSERT INTO `t_dev_task` VALUES ('1291185151111348225', '2020-08-06 09:31:43', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '角色添加禁用功能', '角色添加禁用功能，\n1、禁用后的角色将没有对应的菜单权限\n2、禁用后的角色将没有对应的url接口权限，重新登录生效\n', '1', 2, '2020-08-07 00:00:00', '2020-08-08 02:01:14', 2.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1291185386852204546', '2020-08-06 09:32:39', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '接口权限添加禁用功能', '接口禁用后任何人无法访问该接口，', '1', 0, '2020-08-07 00:00:00', NULL, 1.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1291338450041131010', '2020-08-06 11:40:53', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典添加禁用功能', '字典添加禁用功能，禁用数据除了当前列表外，其他地方的页面查询都不查询出来禁用数据', '1', 2, '2020-08-09 00:00:00', '2020-08-08 01:19:05', 2.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1291777119851974658', '2020-08-07 16:43:59', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典管理生成代码', '1、生成 java常量类，做逻辑判断\n2、生成 js 常量key，做缓存数据获取', '1', 0, '2020-08-08 00:00:00', NULL, 3.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1291798116088971266', '2020-08-07 18:07:25', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '权限分配联动选择', '选中父级自动选择子菜单', '1', 2, '2020-08-09 00:00:00', '2020-08-08 15:32:27', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1292261691161096193', '2020-08-09 08:49:30', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '禁用统一使用 upd 方法来操作', '所有禁用功能使用upd方法', '1', 2, '2020-08-09 09:30:00', '2020-08-09 10:58:55', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('1292261904844107777', '2020-08-09 08:50:21', '1', NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典管理生成静态数据', '字典管理生成对应的后台数据+ 前端数据', '1', 0, '2020-08-09 10:30:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292294460721389569', '2020-08-09 10:59:43', '1', NULL, '2020-08-14 01:40:53', 0, 0, '1', '1', '后台代码优化', '后台代码优化，删除无用代码，规范整理', '1', 0, '2020-08-09 11:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292774268832088066', '2020-08-10 10:46:18', '1', NULL, '2020-08-10 13:12:52', 0, 0, '1291570418964058114,1', '4', '紧急联系人需要3个', '紧急联系人需要3个才可以提交申请', '1,3', 0, '2020-08-01 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292774678410067970', '2020-08-10 10:47:56', '1', NULL, '2020-08-10 13:12:37', 0, 0, '1291570418964058114,1', '4', '文件信息查询', '详情页+审核页 --> 查询出文件信息', '1', 0, '2020-08-10 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292775439395225601', '2020-08-10 10:50:58', '1', NULL, '2020-08-10 12:53:25', 0, 0, '1291570418964058114,1', '4', '数据到风控审核后，业务员不可编辑', '数据到风控审核后，业务员不可编辑业务员信息+车辆信息\n', '1', 0, '2020-08-11 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292776778116399105', '2020-08-10 10:56:17', '1', NULL, '2020-08-10 12:54:05', 0, 0, '1291570418964058114,1', '4', '编辑--内勤审核', '内勤审核所有内容可编辑，包括客户图片上传+文件上传', '1', 0, '2020-08-11 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292777200369565698', '2020-08-10 10:57:57', '1', NULL, '2020-08-10 13:11:44', 0, 0, '1', '4', '审核退回', '退回修改为退回到内勤审核，不退回到业务员', '1', 0, '2020-08-11 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292777639320256514', '2020-08-10 10:59:42', '1', NULL, '2020-08-10 12:53:10', 0, 0, '1291570418964058114,1', '4', '不要跟单', '跟单修改为查看所有审核数据，没有功能操作，只有查看，删除掉操作，把详情留下', '1', 0, '2020-08-11 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292778169228623873', '2020-08-10 11:01:48', '1', NULL, '2020-08-10 13:10:58', 0, 0, '1291570418964058114,1', '4', '添加备注', 'pc 审核页添加客户+配偶+紧急联系人+担保人备注', '1', 0, '2020-08-11 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292781381587324930', '2020-08-10 11:14:34', '1', NULL, '2020-08-23 15:50:04', 0, 0, '1', '1', '任务端+任务人改为多选', '任务端+ 任务人改为多选，一个任务可能多个人同时执行', '1', 0, '2020-08-11 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('1292782644550979585', '2020-08-10 11:19:35', '1', NULL, '2020-08-10 13:13:07', 0, 0, '1291570418964058114', '4', '角色菜单分配Bug', '分配菜单时没有同时选中父级菜单参数传递给后端， 导致分配的下级菜单无法展示', '1', 0, '2020-08-11 00:00:00', NULL, 2.00, NULL);
INSERT INTO `t_dev_task` VALUES ('13', '2020-07-25 10:49:21', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', 'bug修复-完整功能', 'bug修复页的全部功能内容', '1', 2, '2020-08-09 00:00:00', '2020-08-01 21:46:39', 8.00, 2.00);
INSERT INTO `t_dev_task` VALUES ('14', '2020-07-25 10:51:01', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '学习计划-完整功能', '学习计划页的全部功能内容', '1', 0, '2020-08-08 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('15', '2020-07-25 10:52:30', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '更新记录-全功能', '更新记录也的全部功能内容', '1', 0, '2020-08-23 00:00:00', NULL, 8.00, NULL);
INSERT INTO `t_dev_task` VALUES ('16', '2020-07-25 10:53:18', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发规范-全功能', '开发规范页的全部功能内容', '1', 2, '2020-08-30 00:00:00', '2020-07-31 17:41:37', 8.00, 3.00);
INSERT INTO `t_dev_task` VALUES ('18', '2020-07-25 12:24:11', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '密码加密', '用户密码进行MD5 加盐/加密\n1、修改密码\n2、登录\n3、添加', '1', 2, '2020-08-01 00:00:00', '2020-08-01 23:13:15', 1.00, 1.00);
INSERT INTO `t_dev_task` VALUES ('3', '2020-07-12 20:30:13', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '字典表操作后自动刷新', '添加/编辑/删除时未自动刷新页面数据，需优化功能', '1', 2, '2020-07-12 00:00:00', '2020-08-04 04:32:14', 1.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('4', '2020-07-12 20:32:02', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '菜单图标', '添加和编辑时图标改为可选', '1', 0, '2020-07-18 00:00:00', NULL, 4.00, NULL);
INSERT INTO `t_dev_task` VALUES ('7', '2020-07-12 23:26:44', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发任务模块', '完成输入实际耗时，撤销已完成删除完成时间及实际耗时', '1', 2, '2020-07-11 00:00:00', '2020-07-25 10:41:24', 2.00, 0.50);
INSERT INTO `t_dev_task` VALUES ('8', '2020-07-12 23:29:02', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '开发任务模块', '添加任务详情页，鼠标移动到任务名或任务内存处自动弹出任务详情...', '1', 0, '2020-07-18 00:00:00', NULL, 0.00, NULL);
INSERT INTO `t_dev_task` VALUES ('9', '2020-07-12 23:39:11', NULL, NULL, '2020-08-09 17:36:39', 0, 0, '1', '1', '每页记录数BUG处理', '选择了其他页数后面，点击搜索每页记录数被回滚到10', '4', 3, '2020-07-18 00:00:00', NULL, 2.00, NULL);

SET FOREIGN_KEY_CHECKS = 1;
