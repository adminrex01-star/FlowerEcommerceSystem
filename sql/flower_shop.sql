-- 创建数据库
CREATE DATABASE IF NOT EXISTS flower_shop DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE flower_shop;

-- 用户表
CREATE TABLE `user` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `real_name` VARCHAR(50),
    `phone` VARCHAR(20),
    `email` VARCHAR(100),
    `role` VARCHAR(20) DEFAULT 'user',
    `avatar` VARCHAR(500),
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_username` (`username`)
);

-- 商品分类表
CREATE TABLE `category` (
    `category_id` INT NOT NULL AUTO_INCREMENT,
    `category_name` VARCHAR(50) NOT NULL,
    `sort` INT DEFAULT 0,
    `description` VARCHAR(200),
    PRIMARY KEY (`category_id`)
);

-- 商品表
CREATE TABLE `product` (
    `product_id` INT NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(100) NOT NULL,
    `category_id` INT NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    `stock` INT NOT NULL DEFAULT 0,
    `pic` VARCHAR(500),
    `content` TEXT,
    `status` INT DEFAULT 1,
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`product_id`),
    FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`)
);

-- 订单主表
CREATE TABLE `order` (
    `order_id` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `total_price` DECIMAL(10,2) NOT NULL,
    `order_status` VARCHAR(20) DEFAULT 'pending',
    `pay_status` VARCHAR(20) DEFAULT 'unpaid',
    `receiver` VARCHAR(50),
    `phone` VARCHAR(20),
    `address` VARCHAR(200),
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`)
);

-- 订单明细表
CREATE TABLE `order_item` (
    `item_id` INT NOT NULL AUTO_INCREMENT,
    `order_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `product_name` VARCHAR(100),
    `price` DECIMAL(10,2),
    `num` INT,
    PRIMARY KEY (`item_id`),
    FOREIGN KEY (`order_id`) REFERENCES `order`(`order_id`) ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `product`(`product_id`)
);

-- 插入分类数据
INSERT INTO `category` (`category_name`, `sort`, `description`) VALUES
('玫瑰', 1, '经典浪漫之选'),
('百合', 2, '纯洁高雅'),
('向日葵', 3, '阳光活力'),
('康乃馨', 4, '温馨母爱'),
('满天星', 5, '精致点缀');

-- 插入商品数据
INSERT INTO `product` (`product_name`, `category_id`, `price`, `stock`, `pic`, `content`, `status`) VALUES
('红玫瑰', 1, 99.00, 100, '/images/rose_red.jpg', '经典红玫瑰，寓意热恋', 1),
('白玫瑰', 1, 89.00, 80, '/images/rose_white.jpg', '纯洁白玫瑰，寓意纯真', 1),
('粉玫瑰', 1, 95.00, 90, '/images/rose_pink.jpg', '浪漫粉玫瑰，寓意初恋', 1),
('香水百合', 2, 129.00, 50, '/images/lily.jpg', '香水百合，寓意百年好合', 1),
('向日葵', 3, 79.00, 60, '/images/sunflower.jpg', '阳光向日葵，寓意希望', 1),
('康乃馨', 4, 69.00, 120, '/images/carnation.jpg', '温馨康乃馨，寓意母爱', 1),
('满天星', 5, 59.00, 200, '/images/gypsophila.jpg', '精致满天星，寓意思念', 1);

-- 插入用户数据
INSERT INTO `user` (`username`, `password`, `real_name`, `phone`, `email`, `role`) VALUES
('admin', '123456', '管理员', '13800000000', 'admin@flowershop.com', 'admin'),
('zhangshan', '123456', '张三', '13900000001', 'zhangshan@example.com', 'user'),
('lisi', '123456', '李四', '13900000002', 'lisi@example.com', 'user'),
('wangwu', '123456', '王五', '13900000003', 'wangwu@example.com', 'user');