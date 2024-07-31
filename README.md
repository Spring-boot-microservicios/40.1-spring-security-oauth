## Notas

- URL `https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/3.3.2`
- La credenciales por default es `user` y copiar la contraseña que brinda en la terminal
- Es una autenticacion tipo basic
- Genera una cookie llamada `JSESSIONID` por default en el navegador

### DATABASE BASE DE SPRING
- `https://github.com/spring-projects/spring-security/blob/main/core/src/main/resources/org/springframework/security/core/userdetails/jdbc/users.ddl`

### RECOMENDACIONES
- Utilizar mejor el `UserDetailsService` que el `AuthenticationProvider` en los filtros de spring