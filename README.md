## Notas

- URL `https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/3.3.2`
- La credenciales por default es `user` y copiar la contraseña que brinda en la terminal
- Es una autenticacion tipo basic
- Genera una cookie llamada `JSESSIONID` por default en el navegador

### DATABASE BASE DE SPRING
- `https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl`

### RECOMENDACIONES
- Utilizar mejor el `UserDetailsService` que el `AuthenticationProvider` en los filtros de spring

### CORS & CSRF
- Si realimos una peticion desde el Front con dominio `http://localhost:4200` a mi Backend al endpoint `http://localhost:8080/welcome` los cors van a denegar la peticion porque el dominio del front no se encuentra configurados en el backend
- En Csrf se genera una cookie en los headers `XSRF-TOKEN=cfc8699a-114e-44e4-b463-bc930ac648c8` para evitar los ataques

### FILTERS
- Existen diferentes tipos de filtros: 
  - `addFilterBefore(myFilter, CorsFilter.class)` => mi filtro y el filtro que va a ir antes
  - `addFilterAfter(myFilter, CorsFilter.class)` => mi filtro y el filtro que va a ir despues
  - `addFilterAt(myFilter, CsrfFilter.class)` => => mi filtro y durante el filtro antes o despues