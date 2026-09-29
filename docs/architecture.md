# 系统架构说明


## 1. 整体架构设计
本项目采用标准的 SSM (Spring Boot + Spring MVC + MyBatis) 分层架构，各层职责清晰，降低耦合度。

| 架构层级 | 核心职责 | 核心技术/组件 |
| :--- | :--- | :--- |
| **Controller (控制层)** | 接收前端请求，参数校验，调用 Service，返回视图或数据 | Spring MVC, Thymeleaf |
| **Service (业务逻辑层)** | 核心业务逻辑处理，事务控制，调用 Mapper | Spring, `@Transactional` |
| **Mapper (数据访问层)** | 与数据库交互，执行 SQL 语句 | MyBatis, XML 映射文件 |
| **PO (实体层)** | 映射数据库表结构，在各层之间传递数据 | Java Bean |
| **Database (数据库)** | 数据持久化存储 | MySQL 8.0 |

## 2. 核心技术点
- **权限控制**：基于 Session 的 `LoginInterceptor` (登录拦截器) 和 `AdminInterceptor` (管理员拦截器)，实现未登录拦截和角色权限控制。
- **分页查询**：利用 `PageHelper` 插件配合自定义的 `PageResult<T>` 工具类，实现列表数据的高效分页展示。
- **数据导出**：集成 Apache POI 库（XSSFWorkbook），支持将商品数据一键导出为 Excel 文件。
- **前后端交互**：使用 Thymeleaf 模板引擎渲染动态页面，通过表单提交和 AJAX 实现前后端数据交互。
- **数据安全与校验**：前端负责非空与格式校验，后端使用注解进行二次校验，保障输入合法性。

以下是本项目的系统分层架构图：

```mermaid
graph TD
    User[用户浏览器] -->|HTTP请求| Controller(Controller层<br>接收请求与参数校验)
    Controller -->|调用业务逻辑| Service(Service层<br>业务处理与事务控制)
    Service -->|调用数据访问| Mapper(Mapper层<br>MyBatis接口与SQL映射)
    Mapper -->|JDBC| DB[(MySQL数据库)]

    style User fill:#f9f,stroke:#333,stroke-width:2px
    style Controller fill:#bbf,stroke:#333,stroke-width:2px
    style Service fill:#bfb,stroke:#333,stroke-width:2px
    style Mapper fill:#fbb,stroke:#333,stroke-width:2px
    style DB fill:#eee,stroke:#333,stroke-width:2px
    
