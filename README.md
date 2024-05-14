

# UserManager API

## Descripción 📝
API RESTful para la creación y gestión de usuarios. Esta aplicación permite registrar nuevos usuarios y gestionarlos de manera eficiente y segura, cumpliendo con los estándares modernos de desarrollo y seguridad.

## Tecnologías y Dependencias 🛠️
- **Spring Boot** 3.2.5: Framework para crear aplicaciones Spring basadas en microservicios.
- **Java** 17: Lenguaje de programación.
- **Hibernate**: Herramienta de mapeo objeto-relacional para Java.
- **JPA**: Java Persistence API para el acceso a datos.
- **H2 Database**: Base de datos en memoria para pruebas y desarrollo.
- **Swagger**: Para documentación automática de la API.

## Instalación y Configuración 🚀
1. Instalar **Maven**.
2. Clonar el repositorio: `git clone https://github.com/BAguileraSilva/UserManager.git`.
3. Ejecutar en la terminal:
 
```markdown
mvn clean install
mvn spring-boot:run
   ```
   Esto iniciará la aplicación en `localhost:8080`.

## Uso 📊
Para usar la API, puedes realizar solicitudes HTTP a los endpoints definidos, ocupando POSTMAN. Por ejemplo, para registrar un nuevo usuario:

- **URL**: `/api/create`
- **Método**: POST
- **Puerto**: 8080
- **Body**:
  ```json
  {
    "name": "Juan Rodriguez",
    "email": "juan2@rodqriqguez.org",
    "password": "Ab1122334455",
    "phones": [
      {
        "number": "1234567",
        "cityCode": "1",
        "countryCode": "57"
      }
    ]
  }
  ```

### Respuestas:
- **201 Created**: Usuario registrado exitosamente.
- **400 Bad Request**: Solicitud inválida.
- **500 Internal Server Error**: Error interno del servidor.

## Documentación de la API 📚
Accede a la documentación completa de la API en `http://localhost:8080/swagger-ui/index.html` después de iniciar la aplicación.

## Pruebas 🧪
Ejecutar pruebas con:
```
mvn test
```

## Esquema de Base de Datos 🗄️
El esquema de la base de datos se encuentra en `src/main/resources/schema.sql`.

## Contacto o Autores 📫
Bruno Aguilera Silva 

---

