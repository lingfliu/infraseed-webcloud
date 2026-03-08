For the base services you just created:
1. Create a simple data service with SimpleEntity inherit from BaseEntity:
```java
class SimpleEntity extends BaseEntity {
   int cnt;
}
```
2. This SimpleEntity should inherit the baseentity's attributes and with a "cnt" attribute
3. I want no explicit definition of tenantId for this entity, this should be auto injected or inherited into. The base entity only hols the basic attributes of the data, i.e. some data need no tenant attibute. Find another solution to create an annotation to expose the tenant to the data  if the user do want the data to be access-controlled by the tenantId