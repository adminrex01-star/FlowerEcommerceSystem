# 网上花店系统 (Online Flower Shop)

这是一个基于 **Java EE (Spring Boot + Spring MVC + MyBatis)** 的 B2C 电商平台。系统旨在为消费者提供便捷的在线鲜花选购、购物车下单、订单管理等服务，同时为管理员提供商品、分类、订单和用户的完整后台管理功能。

## 🛠️ 技术栈
- **开发语言**: Java 17 (JDK 17)
- **后端框架**: Spring Boot 2.7.11 (整合 Spring MVC, Spring, MyBatis)
- **前端技术**: Thymeleaf 3.0, HTML5, CSS3, JavaScript
- **数据库**: MySQL 8.0.44
- **数据导出**: Apache POI (XSSF)
- **项目管理**: Maven 3.8+
- **服务器**: 内置 Tomcat 9.0.74

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

## 🏗️ 系统架构
项目采用标准的四层架构设计：
1. **Controller层**：接收请求，参数校验，返回视图或数据。
2. **Service层**：核心业务逻辑处理，事务管理（`@Transactional`）。
3. **Mapper层**：MyBatis 数据访问接口。
4. **PO层**：实体类，映射数据库表。

**核心亮点代码**：
- `LoginInterceptor` / `AdminInterceptor`：基于 Session 的角色权限控制拦截器。
- `PageResult<T>`：自定义分页结果封装工具，便于前端渲染分页导航。
- **部分结算**：利用购物车 ID 列表（`cartIds`）实现灵活的购物车订单创建。
- **Excel 导出**：通过 `AdminProductController` 结合 Apache POI 动态生成 `.xlsx` 文件流。

## 🚀 快速开始

### 1. 环境准备
- 确保本地已安装 JDK 17、Maven 3.8+ 和 MySQL 8.0+。
- 确保本地已安装 MySQL 管理工具（如 Navicat）。

### 2. 数据库初始化
1. 新建一个 MySQL 数据库（例如 `onlineflowershop`）。
2. 导入项目根目录下的 `onlineflowershop.sql` 文件。
3. 代码默认内置了测试账号（如普通用户 `zhangshan` / `123456`，管理员 `admin` / `admin123`），可直接登录体验。

### 3. 修改配置
打开 `src/main/resources/application.yml` (或 `.properties`)，修改你的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/onlineflowershop?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8
    username: root
    password: your_password  # 请修改为你自己的数据库密码
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
