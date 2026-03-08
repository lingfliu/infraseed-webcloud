# infraseed-webcloud

Cloud web service container: multi-service Spring Boot scaffold with gateway, user management, data service, and system/OpenAPI aggregation.

## Stack

- **Java 21**, Maven multi-module
- **Spring Boot 3.4**, Spring Cloud 2024, Spring Cloud Alibaba (Nacos)
- **Gateway**: Spring Cloud Gateway, load balancer, Nacos discovery
- **Services**: WebFlux or Web MVC, JPA, Redis, RabbitMQ, MinIO, OAuth2, OpenAPI/Swagger

## Repository layout

```
infraseed-webcloud/
├── pom.xml                 # Parent BOM and modules
├── common/
│   ├── common-core/        # ValiRet, ErrorCodes, ApiResponse, metadata primitives
│   ├── common-data/        # BaseEntity, JPA auditing, soft-delete, BaseOp<T>
│   ├── common-security/    # TenantContext, TenantPrincipal, @TenantAccess aspect
│   └── common-web/         # GlobalExceptionHandler, PageDto, OpenAPI config
├── apps/
│   ├── gateway-service/    # Routes to user/data/system services
│   ├── user-service/       # User, tenant, OAuth (GitHub/Weixin), mobile auth placeholders
│   ├── data-service/       # Tenant-aware CRUD, MinIO/Redis/RabbitMQ, BaseOp sample
│   └── system-service/     # Aggregated docs and service catalog
└── deploy/
    ├── docker-compose.yml  # MySQL, MongoDB, Redis, RabbitMQ, MinIO (optional Nacos)
    ├── env/.env.example    # Env vars for local and compose
    ├── fix-kubeconfig.sh   # Run with sudo to fix ~/.kube/config ownership
    └── k3d-namespace.sh    # Create namespace infraseed_p0 for k3d deployment
```

## Architecture

- **Client** → **Gateway** (port 8080) → **user-service** (8081), **data-service** (8082), **system-service** (8083).
- Backing services: MySQL, Redis, RabbitMQ, MinIO (and optionally Nacos for discovery).
- All persistent entities extend `BaseEntity` (uuid, created_at, updated_at, is_deleted). Tenant-scoped APIs use `@TenantAccess` and `TenantContext` (e.g. header `X-Tenant-Id` in dev).

## Build

```bash
mvn -q -DskipTests package
```

## Local run (profile `local`)

1. **Start backing services** (from repo root):

   ```bash
   docker compose -f deploy/docker-compose.yml up -d
   ```

   Optional: MinIO with `docker compose -f deploy/docker-compose.yml --profile full up -d`.

2. **Configure env** (optional):

   ```bash
   cp deploy/env/.env.example deploy/env/.env
   # Edit deploy/env/.env if needed (e.g. MYSQL_PASSWORD=app for docker default DB)
   ```

3. **Start apps** (in separate terminals, with profile `local`):

   ```bash
   # Terminal 1 – data-service (needs MySQL; creates schema with ddl-auto: update)
   mvn -pl apps/data-service -DskipTests spring-boot:run -Dspring-boot.run.profiles=local

   # Terminal 2 – user-service
   mvn -pl apps/user-service -DskipTests spring-boot:run -Dspring-boot.run.profiles=local

   # Terminal 3 – system-service (no DB)
   mvn -pl apps/system-service -DskipTests spring-boot:run -Dspring-boot.run.profiles=local

   # Terminal 4 – gateway (routes to fixed localhost URLs when Nacos is disabled)
   mvn -pl apps/gateway-service -DskipTests spring-boot:run -Dspring-boot.run.profiles=local
   ```

4. **Check**:

   - Gateway: http://localhost:8080/actuator/health  
   - Via gateway: http://localhost:8080/api/user/me , http://localhost:8080/api/data/samples (add header `X-Tenant-Id: t1` for tenant context), http://localhost:8080/api/system/catalog  

## Conventions

- **Tenant**: Use `TenantContext.setTenantId(...)` (e.g. from filter reading `X-Tenant-Id` or JWT). Methods that require tenant scope are annotated with `@TenantAccess`.
- **Data**: Implement `BaseOp<T>` for CRUD; use `ValiRet` for validation (code 0 = pass). Entities extend `BaseEntity` and use `SoftDeleteRepository` for logical delete.
- **API**: Responses use `ApiResponse<T>`. Pagination uses `PageDto<T>.of(Page)`.

## Deployment (infraseed-devops)

Production-style deployment uses [infraseed-devops](https://github.com/lingfliu/infraseed-devops) for infra platform and Git sync:

- Build and publish images for `gateway-service`, `user-service`, `data-service`, `system-service`.
- Configure Nacos (or your discovery) and point services to the registry.
- Use the same env vars as in `deploy/env/.env.example` (or equivalent ConfigMaps/Secrets) for datasource, Redis, RabbitMQ, MinIO, and Nacos.

See the infraseed-devops repository for cluster bootstrap, namespaces, and sync workflow.

**k3d / kubectl**

- Use namespace `infraseed_p0` when deploying via infraseed-devops. If `kubectl` fails with *permission denied* on `~/.kube/config` (e.g. config owned by root), fix ownership once with:
  ```bash
  sudo ./deploy/fix-kubeconfig.sh
  ```
- Then create the namespace:
  ```bash
  ./deploy/k3d-namespace.sh
  ```
  Or: `kubectl create namespace infraseed_p0`

## License

Apache-2.0 (see LICENSE).
