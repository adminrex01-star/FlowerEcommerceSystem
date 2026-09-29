# 网上花店系统 (Online Flower Shop)

这是一个基于 **Java EE (Spring Boot + Spring MVC + MyBatis)** 的 B2C 电商平台。系统旨在为消费者提供便捷的在线鲜花选购、购物车下单、订单管理等服务，同时为管理员提供商品、分类、订单和用户的完整后台管理功能。

## 🛠️ 技术栈
- **开发语言**: Java 17 (JDK 17)
- **后端框架**: Spring Boot 2.7.11 (整合 Spring MVC, Spring, MyBatis)
- **前端技术**: Thymeleaf 3.0, HTML5, CSS3, JavaScript
- **数据库**: MySQL 8.0.44
- **数据导出**: Apache POI (XSSF)
- **项目管理**: Maven 3.8+

## 📚 详细设计文档
- [数据库设计详细说明 (E-R图、表结构)](docs/database_design.md)
- [系统架构与核心技术点](docs/architecture.md)

## ✨ 核心功能

### 👤 普通用户端
- **用户中心**：注册、登录、会话保持（Session）、修改个人资料、修改密码。
- **商品浏览**：分页展示、按分类筛选、按名称模糊搜索、查看商品详情。
- **购物车**：加入购物车、修改数量、删除商品、**部分结算**（支持复选框勾选）。
- **订单管理**：提交订单、查看个人订单列表（分页）、取消待发货订单。

### 🛡️ 管理员端
- **商品管理**：新增、编辑、删除、上架/下架、**一键导出Excel**。
- **分类管理**：分页查看、新增、编辑、删除。
- **订单管理**：查看所有订单、发货、完成订单、删除订单。
- **用户管理**：查看用户列表、新增、编辑、删除、修改用户角色。

## 🚀 快速开始

### 1. 环境准备
- 确保本地已安装 JDK 17、Maven 3.8+ 和 MySQL 8.0+。
- 确保本地已安装 MySQL 管理工具（如 Navicat）。

### 2. 数据库初始化
本项目使用 MySQL 8.0.44，数据库名称为 `onlineflowershop`。

**导入方式（推荐使用 Navicat）：**
1. 打开 Navicat，连接到本地 MySQL。
2. 新建数据库 `onlineflowershop`，字符集选择 `utf8mb4`。
3. 右键该数据库 -> 运行 SQL 文件 -> 选择项目根目录下的 `onlineflowershop.sql` -> 点击开始。
4. 执行成功后，刷新即可看到所有表和初始数据。

**命令行导入方式（备选）：**
```bash
mysql -u root -p
CREATE DATABASE IF NOT EXISTS onlineflowershop;
USE onlineflowershop;
SOURCE /你的实际路径/onlineflowershop.sql;  -- 替换为实际路径
