This is a repository for cloud web service container. 

# Stack
1. Springboot web application
2. Nacos with gateway and loadbalance
3. Springboot web application
   1. Webflux
   2. JPA & Cache support (Redis or other memcache)
   3. Oauth
   4. Redis support
   5. RabitMQ support
   6. Cron support
   7. Openapi / swagger

# Base infra services
1. User management
   1. Tenant data access control
   2. Support 3rd application oauth (github, weixin)
   3. Support mobile phone and authcode signup / login
2. Data management 
   1. Tenant access annotation 
   2. 3rd service clients:
      1. Minio
      2. Redis
      3. Mysql (integrated into jpa)
      4. RabbitMQ
   3. Data Base attributes: created_at, updated_at, is_deleted, uuid
   4. Common operation interface 
   ```java
   class ValiRet {
    int code;
    String msg;
   }
   interface BaseOp {
        ValiRet validate(Data data); //0 for passed, others should mapped with msg
        Data update(Data old, Data new);
        Data insert(Data data);
        Data remove(Data data);
        Data queryList(int page, int pageSize);
   }
   ```

3. System management
   1. Aggregate openapi documentation

4. 3rd infra services
   1. Mysql
   2. Mongodb
   3. Minio
   4. RabbitMQ

Other spec:
1. wire the user management

# Deployment
1. using https://github.com/lingfliu/infraseed-devops for the infra platform deployment with git sync.