/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : sell_new

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 16/01/2019 18:35:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_address_book`;
CREATE TABLE `tb_address_book`  (
  `gid` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '信息所属用户',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家信息',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家手机号',
  `country` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `x` double(9, 6) NULL DEFAULT NULL COMMENT '经度',
  `y` double(9, 6) NOT NULL COMMENT '纬度',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `gid` bigint(20) NOT NULL AUTO_INCREMENT,
  `seller_id` bigint(20) NOT NULL,
  `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类目名字',
  `category_type` int(11) NULL DEFAULT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (1, 1, '精选热菜', 1, '2018-12-11 09:51:32', '2018-12-11 09:52:20');
INSERT INTO `tb_category` VALUES (2, 1, '爽口凉菜', 2, '2018-12-11 09:51:34', '2018-12-11 09:52:21');
INSERT INTO `tb_category` VALUES (3, 1, '精选套餐', 3, '2018-12-11 09:51:34', '2018-12-11 09:52:22');
INSERT INTO `tb_category` VALUES (4, 1, '果拼果汁', 4, '2018-12-11 09:51:35', '2018-12-11 09:52:23');
INSERT INTO `tb_category` VALUES (5, 1, '小吃主食', 5, '2018-12-11 09:51:36', '2018-12-11 09:52:25');

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `gid` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(8, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `quantity` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_order_master
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_master`;
CREATE TABLE `tb_order_master`  (
  `gid` bigint(20) NOT NULL,
  `seller_id` bigint(20) NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家名字',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家电话',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家地址',
  `openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家微信openid',
  `amount` decimal(8, 2) NULL DEFAULT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NULL DEFAULT 0 COMMENT '订单状态, 默认为0新下单，1完结， 2去掉',
  `pay_status` tinyint(3) NULL DEFAULT 0 COMMENT '支付状态, 默认0未支付， 1已支付',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `gid` bigint(20) NOT NULL,
  `seller_id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(8, 2) NULL DEFAULT NULL COMMENT '单价',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小图',
  `status` tinyint(3) NULL DEFAULT 0 COMMENT '商品状态,1正常0下架',
  `category_type` int(11) NULL DEFAULT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (1, 1, '皮蛋粥', 8.50, 40, '养身粥', 'http://fuss10.elemecdn.com/c/cd/c12745ed8a5171e13b427dbc39401jpeg.jpeg?imageView2/1/w/114/h/114', 1, 1, '2018-12-11 09:46:15', '2018-12-11 09:48:05');
INSERT INTO `tb_product` VALUES (2, 1, '红枣山药粥套餐', 20.00, 50, '红枣山药糙米粥,素材包,爽口莴笋丝,四川泡菜或八宝酱菜,配菜可备注', '红枣山药糙米粥,素材包,爽口莴笋丝,四川泡菜或八宝酱菜,配菜可备注', 1, 2, '2018-12-11 09:48:29', '2018-12-11 09:48:44');
INSERT INTO `tb_product` VALUES (3, 1, '口水鸡', 22.00, 98, '很好吃', 'http://p0.meituan.net/wmproduct/949d7640f54cfe3e9fc98294de11c427170346.jpg@130w_130h_1e_1c', 1, 1, '2018-12-12 11:30:17', '2018-12-12 13:35:47');

-- ----------------------------
-- Table structure for tb_seller
-- ----------------------------
DROP TABLE IF EXISTS `tb_seller`;
CREATE TABLE `tb_seller`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卖家小图',
  `notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卖家公告',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `delivery_time` int(11) NULL DEFAULT NULL COMMENT '送达时间',
  `sell_score` decimal(8, 2) NULL DEFAULT NULL COMMENT '服务评分',
  `min_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '最低消费',
  `delivery_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '配送费',
  `packing_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '包装费',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_seller
-- ----------------------------
INSERT INTO `tb_seller` VALUES (1, '粥品香坊（回龙观）', '粥品香坊（回龙观）', '粥品香坊其烹饪粥料的秘方源于中国千年古法，在融和现代制作工艺，由世界烹饪大师屈浩先生领衔研发。坚守纯天然、0添加的良心品质深得消费者青睐，发展至今成为粥类的引领品牌。是2008年奥运会和2013年园博会指定餐饮服务商。', '蜂鸟专送', 38, 4.30, 20.00, 4.00, 2.00, '2018-12-14 13:06:26', '2018-12-14 13:06:26');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `gid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户注册手机号',
  `sex` tinyint(2) NULL DEFAULT NULL COMMENT '性别',
  `open_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'openid',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '黄潇', '$2a$10$dkjeG1jCJ3UgIh1wgsKAy.kuqnUAQA047xA2CtQ9kc6pY0N.619Va', '18201137668', 0, NULL, NULL, '2019-01-07 17:14:21');

-- ----------------------------
-- Table structure for tb_wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_wx_menu`;
CREATE TABLE `tb_wx_menu`  (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `data` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单数据',
  `type` tinyint(2) NULL DEFAULT NULL COMMENT '0默认菜单，1个性化菜单',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_wx_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_wx_token`;
CREATE TABLE `tb_wx_token`  (
  `gid` bigint(20) NOT NULL AUTO_INCREMENT,
  `token` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expires_in` int(11) NULL DEFAULT NULL,
  `app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `app_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_wx_token
-- ----------------------------
INSERT INTO `tb_wx_token` VALUES (6, '17_YtKJLtLlyBufVsK7Qvv_r3PCwAZmUmIeBj92YsHHEBDm3OuDyHOiA05kxwnSxUuS9IANPyQ9954LMniDGWph0LjbiH0bLTenOicNVCdIPUcFzQVJ_vjjc8OBiKustyvxnBYvURd7P7TCkImNWBHbAAAINT', 7200, 'wxa26732e5541ff033', '010c9ce1c550429f5b9a4dbef6f08709', '2019-01-14 18:51:14');
INSERT INTO `tb_wx_token` VALUES (7, '17_SJCJaEJbmAGODtOx8QYFi-GlzK9Q2ZE1_iObhWl00TwYb1-3EwpN0xWSLuIE0NSl144uaf-lIG2k_R_4v7l9wU2vhgWpGidjN0Jy80qAi9-eglgj1f97GSiz8ZXJKwHIgkfLP46WBmLMLsQ0FFXfAFAAAS', 7200, 'wxa26732e5541ff033', '010c9ce1c550429f5b9a4dbef6f08709', '2019-01-15 10:58:45');
INSERT INTO `tb_wx_token` VALUES (8, '17__lzZuu2dnkewQjsPmK8mhBQJWOoBljR7OFVyDDeI53jtWFNyvRsLmrQnMwGhBaBZx_DklTkFmzao2FMF1He0lN_fKeebkaxSQM4CNf33alP5iEiuUy8C7gFIe4tC3PpLdtV_q910pX44TCACBKLhAHASBC', 7200, 'wxa26732e5541ff033', '010c9ce1c550429f5b9a4dbef6f08709', '2019-01-15 13:13:01');
INSERT INTO `tb_wx_token` VALUES (9, '17_PvtNvF836SdOlntEeiz0dgPbsToTlZ1W018d4qrfyL7ZCQPh8-gToKctTrvLxb-k8lSZDgxu1v8Ip7EcbMleRNtNTR2pXXymmej_ysTpRsp0Txv1kPkMWx7o03KVTTY4rSmDwdQLnAAOiAf7QWIbAJAIOA', 7200, 'wxa26732e5541ff033', '010c9ce1c550429f5b9a4dbef6f08709', '2019-01-16 15:38:55');
INSERT INTO `tb_wx_token` VALUES (10, '17_jEEl60qe94PxHiFIr-_KS1-IOZgpI36Ja9TykbOUIXakBGcf3FTCKLLWAT_8CH0r4cCpSHJRWNUe4bUXVyQsAwdgyY_04udBDKliI8qNqk6U2Z0uJxIeaK9_jbslWMV5IXe7m3defAjTxjxWJHXbAJAVTJ', 7200, 'wxa26732e5541ff033', '010c9ce1c550429f5b9a4dbef6f08709', '2019-01-16 17:44:32');

SET FOREIGN_KEY_CHECKS = 1;
