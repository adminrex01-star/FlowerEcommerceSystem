/*
 Navicat Premium Dump SQL

 Source Server         : OnlineFlowerShop
 Source Server Type    : MySQL
 Source Server Version : 80044 (8.0.44)
 Source Host           : localhost:3306
 Source Schema         : onlineflowershop

 Target Server Type    : MySQL
 Target Server Version : 80044 (8.0.44)
 File Encoding         : 65001

 Date: 07/06/2026 21:05:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT 1,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_id`) USING BTREE,
  UNIQUE INDEX `uk_user_product`(`user_id` ASC, `product_id` ASC) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sort` int NULL DEFAULT 0,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '玫瑰', 1, '浪漫经典');
INSERT INTO `category` VALUES (2, '百合', 2, '纯洁高雅');
INSERT INTO `category` VALUES (3, '向日葵', 3, '阳光活力');
INSERT INTO `category` VALUES (4, '康乃馨', 4, '温馨母爱');
INSERT INTO `category` VALUES (5, '满天星', 5, '精致点缀');
INSERT INTO `category` VALUES (6, '扶郎花', 6, '乐观向阳');
INSERT INTO `category` VALUES (7, '勿忘我', 7, '永恒思念');
INSERT INTO `category` VALUES (8, '绣球花', 8, '团圆美满');
INSERT INTO `category` VALUES (9, '菊花', 9, '清净高雅');
INSERT INTO `category` VALUES (10, '洋桔梗', 10, '真诚不变的爱');
INSERT INTO `category` VALUES (11, '郁金香', 11, '热情告白');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_price` decimal(10, 2) NOT NULL,
  `order_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'pending',
  `pay_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'unpaid',
  `receiver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 2, 188.00, 'completed', 'unpaid', '测试用户1', '13900000001', '广东省广州市天河区花店路1号', '2026-06-07 15:43:29');
INSERT INTO `order` VALUES (2, 3, 99.00, 'shipped', 'unpaid', '张小明', '13811111111', '上海市浦东新区花店路2号', '2026-06-07 15:43:29');
INSERT INTO `order` VALUES (3, 1, 258.00, 'completed', 'unpaid', '管理员', '13800000000', '北京市海淀区', '2026-06-07 15:43:29');
INSERT INTO `order` VALUES (4, 2, 570.00, 'shipped', 'unpaid', '测试用户1', '13900000001', '广金北八楼下', '2026-06-07 17:38:58');
INSERT INTO `order` VALUES (5, 2, 263.00, 'cancelled', 'unpaid', '测试用户1', '13900000001', '广金西门', '2026-06-07 17:39:57');
INSERT INTO `order` VALUES (7, 2, 49.00, 'pending', 'unpaid', '测试用户1', '13900000001', 'xx山下鲜花寄存处', '2026-06-07 17:40:58');
INSERT INTO `order` VALUES (8, 2, 59.00, 'pending', 'unpaid', '测试用户1', '13900000001', '教师楼a层b号房', '2026-06-07 17:41:41');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE,
  INDEX `order_id`(`order_id` ASC) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, 1, 1, '红玫瑰', 99.00, 1);
INSERT INTO `order_item` VALUES (2, 1, 4, '香水百合', 129.00, 1);
INSERT INTO `order_item` VALUES (3, 2, 5, '向日葵', 79.00, 1);
INSERT INTO `order_item` VALUES (4, 2, 6, '康乃馨', 69.00, 1);
INSERT INTO `order_item` VALUES (5, 3, 13, '郁金香', 109.00, 2);
INSERT INTO `order_item` VALUES (6, 3, 7, '满天星', 59.00, 1);
INSERT INTO `order_item` VALUES (7, 4, 1, '红玫瑰', 99.00, 1);
INSERT INTO `order_item` VALUES (8, 4, 6, '康乃馨', 69.00, 1);
INSERT INTO `order_item` VALUES (9, 4, 8, '扶郎花', 85.00, 1);
INSERT INTO `order_item` VALUES (10, 4, 10, '混搭绣球', 99.00, 1);
INSERT INTO `order_item` VALUES (11, 4, 13, '郁金香', 109.00, 2);
INSERT INTO `order_item` VALUES (12, 5, 9, '勿忘我', 75.00, 1);
INSERT INTO `order_item` VALUES (13, 5, 10, '混搭绣球', 99.00, 1);
INSERT INTO `order_item` VALUES (14, 5, 12, '洋桔梗', 89.00, 1);
INSERT INTO `order_item` VALUES (16, 7, 11, '菊花', 49.00, 1);
INSERT INTO `order_item` VALUES (17, 8, 7, '满天星', 59.00, 1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_id` int NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `stock` int NOT NULL DEFAULT 0,
  `pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` int NULL DEFAULT 1,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '红玫瑰', 1, 99.00, 99, '/images/RedRose.jpg', '经典红玫瑰，寓意热恋', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (2, '白玫瑰', 1, 89.00, 80, '/images/WhiteRose.jpg', '纯洁白玫瑰，寓意纯真', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (3, '粉玫瑰', 1, 95.00, 90, '/images/PinkRose.jpg', '浪漫粉玫瑰，寓意初恋', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (4, '香水百合', 2, 129.00, 50, '/images/PerfumeLily.jpg', '香水百合，寓意百年好合', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (5, '向日葵', 3, 79.00, 60, '/images/SunFlower.jpg', '阳光向日葵，寓意希望', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (6, '康乃馨', 4, 69.00, 119, '/images/Carnation.jpg', '温馨康乃馨，寓意母爱', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (7, '满天星', 5, 59.00, 199, '/images/Gypsophila.jpg', '精致满天星，寓意思念', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (8, '扶郎花', 6, 85.00, 69, '/images/Gerbera.jpg', '乐观扶郎，寓意互敬互爱', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (9, '勿忘我', 7, 75.00, 109, '/images/ForgetMeNot.jpg', '勿忘我，寓意永恒的爱', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (10, '混搭绣球', 8, 99.00, 38, '/images/MixedHydrangea.jpg', '多彩绣球，寓意团圆美满', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (11, '菊花', 9, 49.00, 149, '/images/Chrysanthemum.jpg', '菊花，寓意清净高洁', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (12, '洋桔梗', 10, 89.00, 78, '/images/Lisianthus.jpg', '洋桔梗，寓意真诚不变的爱', 1, '2026-06-07 15:43:29');
INSERT INTO `product` VALUES (13, '郁金香', 11, 109.00, 63, '/images/Tulip.jpg', '郁金香，寓意爱的表白', 1, '2026-06-07 15:43:29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'user',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin123', '系统管理员', '13800000000', 'admin@flowershop.com', 'admin', NULL, '2026-06-07 15:43:29');
INSERT INTO `user` VALUES (2, 'test1', '234567', '测试用户1', '13900000001', 'test1@example.com', 'user', NULL, '2026-06-07 15:43:29');
INSERT INTO `user` VALUES (3, 'user_zhang', 'pass1234', '张小明', '13811111111', 'zhang@example.com', 'user', NULL, '2026-06-07 15:43:29');
INSERT INTO `user` VALUES (4, 'user_li', 'mypass567', '李芳', '13822222222', 'li@example.com', 'user', NULL, '2026-06-07 15:43:29');
INSERT INTO `user` VALUES (5, 'user_wang', 'wang1234', '王磊', '13833333333', 'wang@example.com', 'user', NULL, '2026-06-07 15:43:29');
INSERT INTO `user` VALUES (6, 'user_zhao', 'zhao888', '赵丽', '13844444444', 'zhao@example.com', 'user', NULL, '2026-06-07 15:43:29');
INSERT INTO `user` VALUES (7, 'user_chen', 'chen12345', '陈晨', '13855555555', 'chen@example.com', 'user', NULL, '2026-06-07 15:43:29');


SET FOREIGN_KEY_CHECKS = 1;
