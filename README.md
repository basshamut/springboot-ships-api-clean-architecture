# 🚀 API de Gestión de Naves Espaciales

Este proyecto implementa una API RESTful completa para la gestión de naves espaciales de películas y series, desarrollada con Spring Boot y Java 21.

## 🗂️ Tabla de Contenidos

- [✨ Características](#-características)
- [🛠️ Tecnologías](#-tecnologías)
- [🏗️ Arquitectura](#-arquitectura)
- [⚙️ Instalación y Ejecución](#-instalación-y-ejecución)
- [🔒 Seguridad y Autenticación](#-seguridad-y-autenticación)
- [📖 Documentación de la API](#-documentación-de-la-api)
- [📬 Integración con Sistemas de Mensajería](#-integración-con-sistemas-de-mensajería)
- [📊 Stack de Observabilidad Completo](#-stack-de-observabilidad-completo)
- [🗄️ Base de Datos](#-base-de-datos)
- [🧪 Pruebas](#-pruebas)
- [🔧 Uso de Herramientas](#-uso-de-herramientas)
- [🧪 Generación de Datos de Prueba](#-generación-de-datos-de-prueba)
- [📡 Flujo de Datos](#-flujo-de-datos)
- [🚨 Troubleshooting](#-troubleshooting)
- [🔮 Extensiones Futuras](#-extensiones-futuras)
- [📚 Recursos y Referencias](#-recursos-y-referencias)

## ✨ Características

El sistema implementa todas las operaciones CRUD para naves espaciales:

- 🔍 **Consultas avanzadas**:
  - 📄 Listado completo con paginación y ordenamiento
  - 🆔 Búsqueda por ID
  - 🔤 Búsqueda por nombre (parcial o completo)
  - 🎯 Filtrado por múltiples criterios

- 📝 **Operaciones de modificación**:
  - ➕ Creación de nuevas naves
  - ✏️ Actualización de naves existentes
  - ❌ Eliminación de naves

- 🚀 **Características avanzadas**:
  - ⚡ Caché para optimizar rendimiento
  - 🛡️ Gestión centralizada de excepciones
  - 📝 Logging mediante aspectos AOP
  - ✅ Validación de datos de entrada
  - 📬 Mensajería asíncrona con RabbitMQ y Kafka

## 🛠️ Tecnologías

- ☕ **Backend**: Java 21, Spring Boot 3.x
- 🗄️ **Base de datos**: H2 (embebida)
- 🦋 **Migración de datos**: Flyway
- 🛡️ **Seguridad**: Spring Security con JWT
- 📖 **Documentación**: OpenAPI (Swagger)
- 📬 **Mensajería**: RabbitMQ, Apache Kafka
- 🐳 **Contenedorización**: Docker, Docker Compose
- 🧪 **Pruebas**: JUnit 5, Mockito, Cucumber (BDD)

## 🏗️ Arquitectura

El proyecto implementa **Clean Architecture** (Arquitectura Limpia) siguiendo los principios de Robert C. Martin, organizando el código en capas concéntricas con dependencias que fluyen hacia el interior.

### 📊 Diagrama de Arquitectura Limpia

🌐 INFRASTRUCTURE LAYER
🎯 PRESENTATION LAYER
📦 APPLICATION LAYER
🧠 DOMAIN LAYER

```
    ┌─────────────────────────────────────────────────────────┐
    │                    INFRASTRUCTURE LAYER                 │
    │  ┌─────────────────────────────────────────────────┐    │
    │  │                 PRESENTATION LAYER              │    │
    │  │  ┌─────────────────────────────────────────┐    │    │
    │  │  │              APPLICATION LAYER          │    │    │
    │  │  │  ┌─────────────────────────────────┐    │    │    │
    │  │  │  │           DOMAIN LAYER          │    │    │    │
    │  │  │  │                                 │    │    │    │
    │  │  │  │  • Entidades (Business Objects) │    │    │    │
    │  │  │  │  • Value Objects                │    │    │    │
    │  │  │  │  • Domain Services              │    │    │    │
    │  │  │  │  • Repository Interfaces        │    │    │    │
    │  │  │  │                                 │    │    │    │
    │  │  │  └─────────────────────────────────┘    │    │    │
    │  │  │  • Use Cases (Business Logic)           │    │    │
    │  │  │  • Application Services                 │    │    │
    │  │  │  • DTOs y Mappers                       │    │    │
    │  │  └─────────────────────────────────────────┘    │    │
    │  │  • Controllers (REST Endpoints)                 │    │
    │  │  • Exception Handlers                           │    │
    │  │  • Security Configuration                       │    │
    │  └─────────────────────────────────────────────────┘    │
    │  • Repository Implementations                           │
    │  • Database Configuration                               │
    │  • External Services (RabbitMQ, Kafka)                  │
    │  • Observability (Metrics, Tracing)                     │
    └─────────────────────────────────────────────────────────┘
```

### � Estructura de Capas Implementada

#### 🧠 **Domain Layer** (Núcleo del Negocio)
**Ubicación**: `com.jrhub.domain`
- **Entidades**: `User`, `SpaceShip` - Objetos de negocio puros
- **Repository Interfaces**: `UserRepository`, `MovieSpaceShipRepository`
- **Principio**: Sin dependencias externas, solo lógica de negocio pura

```java
// Ejemplo: Entidad de dominio
public class SpaceShip {
    private Long id;
    private String name;
    private String movie;
    // Lógica de negocio pura, sin dependencias de framework
}
```

#### 📦 **Application Layer** (Casos de Uso)
**Ubicación**: `com.jrhub.application`
- **Use Cases**: `MovieSpaceShipUseCase`, `UserUseCase` - Orquestación de la lógica de negocio
- **Application Services**: `AuthService` - Servicios de aplicación para casos específicos
- **DTOs**: Objetos de transferencia entre capas
- **Mappers**: Transformación entre DTOs y entidades de dominio

```java
// Ejemplo: Use Case
@Component
public class MovieSpaceShipUseCaseImpl implements MovieSpaceShipUseCase {
    private final MovieSpaceShipRepository repository;
    
    public Page<SpaceShip> getAllSpaceShips(Pageable pageable) {
        // Lógica de aplicación orquestando el dominio
    }
}
```

#### 🎯 **Presentation Layer** (Interfaz de Usuario)
**Ubicación**: `com.jrhub.api`
- **Controllers**: `MovieSpaceShipController`, `LoginController` - Endpoints REST
- **Exception Handlers**: Manejo centralizado de errores
- **Security**: Configuración de autenticación y autorización

```java
// Ejemplo: Controller
@RestController
public class MovieSpaceShipController {
    private final MovieSpaceShipUseCase spaceShipUseCase;
    
    @GetMapping("/api/spaceships")
    public ResponseEntity<Page<SpaceShipResponseDto>> getAllSpaceShips() {
        // Delegación a la capa de aplicación
    }
}
```

#### 🌐 **Infrastructure Layer** (Detalles Técnicos)
**Ubicación**: `com.jrhub.infrastructure`
- **Repository Implementations**: Implementaciones JPA de interfaces de dominio
- **Database Configuration**: Configuración de Flyway, H2, JPA
- **External Services**: Integración con RabbitMQ, Kafka, JWT
- **Observability**: Métricas, trazas, logging

```java
// Ejemplo: Repository Implementation
@Repository
public interface MovieSpaceShipJpaRepository 
    extends JpaRepository<SpaceShip, Long>, MovieSpaceShipRepository {
    // Implementación técnica de la interfaz de dominio
}
```

### 🔄 Flujo de Dependencias

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│Presentation │───▶│ Application │───▶│   Domain    │◀───│Infrastructure│
│   Layer     │    │   Layer     │    │   Layer     │    │   Layer     │
└─────────────┘    └─────────────┘    └─────────────┘    └─────────────┘
```

**Principios de Dependencia**:
- ✅ **Presentation** → **Application** → **Domain**
- ✅ **Infrastructure** → **Domain** (implementa interfaces)
- ❌ **Domain** nunca depende de capas externas
- ❌ **Application** nunca depende de **Infrastructure** directamente

### 🛠️ Implementación Pragmática

#### **Decisiones Arquitectónicas Pragmáticas**

1. **🔧 JwtService en Infrastructure**
   ```java
   // Decisión: Mantener JwtService en Infrastructure
   // Razón: Es un detalle técnico de Spring Security, no lógica de dominio
   @Service
   public class JwtService {
       // Implementación técnica específica de JWT
   }
   ```

2. **📊 MapStruct para Mapping**
   ```java
   // Implementación: Mappers automáticos en Application Layer
   @Mapper(componentModel = "spring")
   public interface AuditEventMapper {
       @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
       AuditEventDto toDto(AuditEvent auditEvent);
   }
   ```

3. **🎯 DTOs con Anotaciones Spring**
   ```java
   // Decisión pragmática: DTOs con validaciones
   public class SpaceShipRequestDto {
       @NotBlank(message = "Name is required")
       private String name;
       // Anotaciones de validación para mayor robustez
   }
   ```

#### **Configuración de Inyección de Dependencias**

La configuración manual de beans es **fundamental** en Clean Architecture para mantener la inversión de dependencias y evitar que Spring escanee automáticamente toda la aplicación, lo cual podría crear dependencias incorrectas.

##### **🎯 ¿Por qué es Necesaria esta Configuración?**

1. **🔒 Control Explícito de Dependencias**
   ```java
   // ❌ PROBLEMA: Sin configuración explícita
   // Spring podría inyectar directamente JwtService en Controllers
   @RestController
   public class LoginController {
       @Autowired
       private JwtService jwtService; // VIOLACIÓN de Clean Architecture
   }
   
   // ✅ SOLUCIÓN: Con configuración explícita
   @RestController
   public class LoginController {
       private final AuthService authService; // Solo depende de Application Layer
   }
   ```

2. **🧱 Prevenir Violaciones Arquitectónicas**
   - Sin configuración explícita, Spring podría crear dependencias directas entre capas
   - La configuración manual **fuerza** el respeto de los límites de capas
   - Evita que la capa de presentación acceda directamente a infrastructure

3. **🎨 Implementación del Patrón Factory**
   ```java
   // La configuración actúa como un Factory centralizado
   @Configuration
   public class ApplicationLayerInjectionConfig {
       
       // Factory method que controla EXACTAMENTE cómo se crean los Use Cases
       @Bean
       public MovieSpaceShipUseCase movieSpaceShipUseCase(
               MovieSpaceShipRepository repository,    // Interface del Domain
               MetricsService metricsService) {        // Service de Infrastructure
           
           // Aquí controlamos qué implementaciones se inyectan
           return new MovieSpaceShipUseCaseImpl(repository, metricsService);
       }
   }
   ```

##### **🔄 Flujo de Creación de Dependencias**

```
   ┌─────────────────────────────────────────────────────────────┐
   │                    ApplicationLayerInjectionConfig          │
   │                                                             │
   │  @Bean MovieSpaceShipUseCase movieSpaceShipUseCase() {     │
   │      return new MovieSpaceShipUseCaseImpl(                 │
   │          repository,    // ◀── Interface del Domain        │
   │          metricsService // ◀── Implementation Infrastructure│
   │      );                                                     │
   │  }                                                          │
   └─────────────────────────────────────────────────────────────┘
                                    │
                                    ▼
        ┌─────────────────────────────────────────────────────────┐
        │               Spring Container                          │
        │                                                         │
        │  1. Busca MovieSpaceShipRepository implementation      │
        │     → Encuentra MovieSpaceShipJpaRepository            │
        │                                                         │
        │  2. Busca MetricsService                               │
        │     → Encuentra @Service MetricsService                │
        │                                                         │
        │  3. Crea MovieSpaceShipUseCaseImpl con estas deps     │
        │                                                         │
        │  4. Registra como MovieSpaceShipUseCase               │
        └─────────────────────────────────────────────────────────┘
```

##### **🛡️ Ventajas de esta Aproximación**

1. **Testabilidad Mejorada**
   ```java
   // En tests, podemos crear fácilmente mocks
   @Test
   void testMovieSpaceShipUseCase() {
       MovieSpaceShipRepository mockRepo = mock(MovieSpaceShipRepository.class);
       MetricsService mockMetrics = mock(MetricsService.class);
       
       // Creación manual SIN Spring Context
       MovieSpaceShipUseCase useCase = new MovieSpaceShipUseCaseImpl(mockRepo, mockMetrics);
       
       // Test puro, sin dependencias de framework
   }
   ```

2. **Flexibilidad de Configuración**
   ```java
   @Configuration
   @Profile("production")
   public class ProductionConfig extends ApplicationLayerInjectionConfig {
       
       @Bean
       @Override
       public AuthService authService(JwtService jwtService) {
           // En producción, podemos añadir decoradores, proxies, etc.
           return new CachedAuthService(
               new MetricsAwareAuthService(
                   new AuthServiceImpl(jwtService)
               )
           );
       }
   }
   ```

3. **Detección Temprana de Problemas**
   ```java
   // Si hay problemas de dependencias circulares o faltantes,
   // Spring fallará en startup con mensajes claros apuntando a esta configuración
   @Bean
   public MovieSpaceShipUseCase movieSpaceShipUseCase(
           MovieSpaceShipRepository repository,
           MetricsService metricsService,
           NonExistentService missing) {  // ← Error claro en startup
       return new MovieSpaceShipUseCaseImpl(repository, metricsService);
   }
   ```

##### **🚀 Comparación: @ComponentScan vs Configuración Manual**

```java
// ❌ ENFOQUE TRADICIONAL (peligroso para Clean Architecture)
@SpringBootApplication
@ComponentScan(basePackages = "com.jrhub") // Escanea TODO
public class Application {
    // Spring encuentra automáticamente todos los @Component, @Service, etc.
    // Puede crear dependencias incorrectas entre capas
}

// ✅ ENFOQUE CLEAN ARCHITECTURE
@SpringBootApplication
@Import({
    ApplicationLayerInjectionConfig.class,  // Solo Application Layer beans
    InfrastructureConfig.class,             // Solo Infrastructure beans
    SecurityConfig.class                    // Solo Security beans
})
public class Application {
    // Control total sobre qué beans se crean y cómo
}
```

##### **🔧 Configuración Completa del Proyecto**

```java
// ApplicationLayerInjectionConfig.java - NÚCLEO de la arquitectura
@Configuration
public class ApplicationLayerInjectionConfig {
    
    // Use Cases - Lógica de aplicación pura
    @Bean
    public MovieSpaceShipUseCase movieSpaceShipUseCase(
            MovieSpaceShipRepository repository,
            MetricsService metricsService) {
        return new MovieSpaceShipUseCaseImpl(repository, metricsService);
    }
    
    @Bean
    public UserUseCase userUseCase(UserRepository userRepository) {
        return new UserUseCaseImpl(userRepository);
    }
}
```

Esta configuración es el **corazón** de nuestra implementación de Clean Architecture, asegurando que:
- Las dependencias fluyan en la dirección correcta
- Los límites de capas se respeten estrictamente  
- El código sea testeable y mantenible
- Spring no cree dependencias automáticas que violen la arquitectura

### 🎖️ Beneficios Logrados

#### **📈 Mantenibilidad**
- Separación clara de responsabilidades
- Código de negocio independiente de frameworks
- Fácil testing con mocks de interfaces

#### **🔄 Testabilidad**
- Domain layer testeable sin Spring Context
- Use Cases testeable con mocks simples
- Integration tests enfocados en HTTP

#### **⚡ Flexibilidad**
- Cambio de base de datos sin afectar lógica de negocio
- Cambio de framework web sin afectar use cases
- Nuevos canales de entrada (GraphQL, gRPC) fáciles de agregar

#### **🧩 Escalabilidad**
- Nuevos use cases siguiendo el mismo patrón
- Microservicios fáciles de extraer por bounded context
- Arquitectura preparada para Domain-Driven Design

### 🔍 Patrones Implementados

- 🏛️ **Repository Pattern**: Abstracción del acceso a datos
- 📦 **DTO Pattern**: Transferencia de datos entre capas
- 🎯 **Use Case Pattern**: Encapsulación de lógica de aplicación
- 🔄 **Dependency Inversion**: Interfaces en domain, implementaciones en infrastructure
- 🧩 **Dependency Injection**: Spring IoC para gestión de dependencias
- 📊 **Mapper Pattern**: Transformación automática con MapStruct
- 🛡️ **Exception Handling**: Manejo centralizado de errores
- 📈 **Observability**: Métricas y trazas integradas

## ⚙️ Instalación y Ejecución

### Requisitos Previos

- Java 21 JDK
- Maven 3.8+
- Docker y Docker Compose (opcional, para contenedorización)

### Métodos de Ejecución

#### Con Docker Compose (Recomendado)

El proyecto incluye configuración completa para un entorno de desarrollo con:
- Aplicación Spring Boot
- RabbitMQ con interfaz de administración
- Kafka con su gestor UI
- Base de datos H2

Pasos:

1. **Compilar el proyecto**:
   ```bash
   mvn clean package
   ```

2. **Iniciar el entorno**:
   ```bash
   docker-compose up -d
   ```

3. **Verificar los logs**:
   ```bash
   docker-compose logs -f
   ```

4. **Detener el entorno**:
   ```bash
   docker-compose down
   ```

#### Ejecución Local (sin Docker)

1. **Compilar el proyecto**:
   ```bash
   mvn clean package
   ```

2. **Ejecutar la aplicación**:
   ```bash
   java -jar target/springboot-ships-backoffice-1.0-SNAPSHOT.jar
   ```

### Acceso a los Servicios

- **API REST**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **RabbitMQ Management**: http://localhost:15672
  - Usuario: `guest`
  - Contraseña: `guest`
- **Kafka UI**: http://localhost:9000

## 🔒 Seguridad y Autenticación

El proyecto implementa un sistema de autenticación basado en JWT (JSON Web Token) con Spring Security:

- **Inicio de Sesión**: POST a `/api/login` con credenciales
- **Autenticación**: Token JWT incluido en header Authorization
- **Permisos**: Roles configurados en Spring Security
- **Expiración**: Tokens con tiempo limitado de validez

Credenciales para pruebas:
- **Usuario**: `test@test.com`
- **Contraseña**: `miContraseña123` (o en Base64: `bWlDb250cmFzZcOxYTEyMw==`)

## 📖 Documentación de la API

La API está completamente documentada con OpenAPI (Swagger):
- **URL de acceso**: http://localhost:8080/swagger-ui.html
- **Endpoints principales**:
  - `/api/ships` - Operaciones CRUD para naves
  - `/api/login` - Autenticación
  - `/api/kafka` - Operaciones con Kafka
  - `/api/rabbitmq` - Operaciones con RabbitMQ

## 📬 Integración con Sistemas de Mensajería

El sistema implementa mensajería asíncrona utilizando **RabbitMQ** y **Apache Kafka** para desacoplar procesos, mejorar la escalabilidad y permitir la integración con otros sistemas.

### Arquitectura General de Mensajería

```
    ┌────────────────────────────┐
    │    Spring Boot App         │
    │ (Operación de Negocio)     │
    └─────────────┬──────────────┘
                  │
                  ▼
   ┌────────────────────────────┐
   │ Servicio Productor         │
   │ (KafkaSenderService        │
   │  o RabbitMQSenderService)  │
   └────┬──────────────────┬────┘
        │                  │
        │                  │
        ▼                  ▼
┌────────────────┐ ┌────────────────┐
│   Kafka Topic  │ │  RabbitMQ Cola │
└───────┬────────┘ └───────┬────────┘
        │                  │
        ▼                  ▼
┌────────────────┐ ┌────────────────────┐
│ Servicio       │ │ Servicio           │
│ Consumidor     │ │ Consumidor         │
│ (KafkaReceiver │ │ (RabbitMQReceiver  │
│  Service)      │ │  Service)          │
└───────┬────────┘ └─────────┬──────────┘
        │                    │
        ▼                    ▼
┌───────────────────────────────────────┐
│ Procesamiento final:                  │
│ - Persistencia                        │
│ - Logging                             │
│ - Notificaciones                      │
└───────────────────────────────────────┘
```

---

### RabbitMQ

RabbitMQ se utiliza para el envío y recepción de eventos de auditoría (por ejemplo, acciones sobre naves espaciales). Su integración es la siguiente:

- **Configuración**: Definida en `application.yml` y en la clase `ConfigureRabbitMq.java`, donde se crean la cola, el listener y el adaptador de mensajes.
- **Productor**: `RabbitMQSenderService` serializa y envía mensajes (objetos `AuditEventDto`) a la cola definida (`AUDIT_QUEUE`).
- **Consumidor**: `RabbitMQReceiverService` recibe y procesa los mensajes de auditoría.
- **Flujo**: Cuando ocurre una acción relevante, el servicio de negocio llama a `sendAuditMessage`, que publica el evento en la cola. El receptor lo procesa de forma asíncrona.

#### Ejemplo de uso en código:
```java
// Envío de mensaje
rabbitMQSenderService.sendAuditMessage(auditEventDto);

// Recepción de mensaje
public void receiveMessage(AuditEventDto message) {
    log.info("Received message: {}", message);
}
```

#### Ejecución de RabbitMQ Local

Si necesitas ejecutar RabbitMQ localmente sin Docker Compose:
```bash
docker run -d -p 9090:15672 -p 9091:5672 --name rabbitmq rabbitmq:3-management
```

---

### Kafka

Kafka se utiliza para el registro y monitoreo de intentos de login y otros eventos relevantes del dominio:

- **Configuración**: Definida en `application.yml` y gestionada por Spring Boot.
- **Productor**: `KafkaSenderService` serializa cualquier objeto a JSON y lo publica en el topic correspondiente.
- **Consumidor**: `KafkaReceiverService` escucha el topic (por ejemplo, `LOGIN_ATTEMPTS_TOPIC`) y procesa los mensajes recibidos.
- **Flujo**: Al producirse un evento (como un intento de login), se llama a `sendMessage`, que publica el evento en el topic. El consumidor lo procesa de forma asíncrona.

#### Ejemplo de uso en código:
```java
// Envío de mensaje
data = new LoginAttemptDto(...);
kafkaSenderService.sendMessage(LOGIN_ATTEMPTS_TOPIC, data);

// Recepción de mensaje
@KafkaListener(topics = LOGIN_ATTEMPTS_TOPIC, groupId = SHIPS_CONSUMER_GROUP)
public void loginAttempsReceive(String message) {
    LoginAttemptDto loginAttempt = objectMapper.readValue(message, LoginAttemptDto.class);
    log.info("Received login attempt: {}", loginAttempt);
}
```

#### Acceso a Kafka UI
- **Kafka UI**: http://localhost:9000

---

## 🗄️ Base de Datos

La aplicación utiliza H2 como base de datos embebida:

- **Migraciones**: Gestionadas por Flyway
- **Modelo de datos**: Entidades definidas en el paquete repository
- **Scripts**: Ubicados en `resources/migrations/`

El esquema incluye:
- Tabla de naves espaciales (`space_ships`)
- Tabla de usuarios (`users`)

## 🧪 Pruebas

El proyecto incluye:

- **Pruebas unitarias**: Con JUnit 5 y Mockito
- **Pruebas de integración**: Con Cucumber (BDD)
- **Tests de rendimiento**: Para verificar tiempos de respuesta

### Ejecución de Pruebas

```bash
mvn test                 # Ejecutar pruebas unitarias
mvn verify               # Ejecutar todas las pruebas incluyendo integración
```

Las pruebas de integración utilizan escenarios Cucumber ubicados en `src/test/resources/integration/cucumber/`.

# 📊 Stack de Observabilidad Completo

Este proyecto implementa un stack completo de observabilidad siguiendo las mejores prácticas de monitoreo moderno, utilizando **Micrometer Tracing + Brave + Zipkin** en lugar de OpenTelemetry para evitar conflictos de versiones.

## 📊 **Arquitectura del Stack de Observabilidad**

```
┌─────────────────────┐    ┌─────────────────────┐    ┌─────────────────────┐
│   Spring Boot App   │───▶│     Prometheus      │───▶│      Grafana        │
│   (Puerto 8080)     │    │   (Puerto 9090)     │    │   (Puerto 3000)     │
│                     │    │                     │    │                     │
│ • Micrometer        │    │ • Scraping métricas │    │ • Dashboards        │
│ • Actuator          │    │ • Almacenamiento    │    │ • Visualizaciones   │
│ • Métricas custom   │    │ • PromQL            │    │ • Alertas           │
└─────────────────────┘    └─────────────────────┘    └─────────────────────┘
          │
          │ Traces
          ▼
┌──────────────────────┐
│       Zipkin         │
│   (Puerto 9411)      │
│                      │
│ • Trazas distribuidas│
│ • Análisis latencia  │
│ • Visualización spans│
└──────────────────────┘
```

## 🚀 **Componentes Implementados**

### **1. Micrometer + Spring Boot Actuator**
- **Propósito**: Exposición de métricas estándar y personalizadas
- **Puerto**: 8080 (endpoint `/actuator/prometheus`)
- **Tecnología**: Micrometer Registry para Prometheus

### **2. Prometheus**
- **Propósito**: Recolección, almacenamiento y consulta de métricas
- **Puerto**: 9090
- **Configuración**: Scraping cada 5 segundos de la aplicación

### **3. Grafana**
- **Propósito**: Visualización de métricas y dashboards
- **Puerto**: 3000 (admin/admin)
- **Datasource**: Prometheus configurado automáticamente

### **4. Zipkin**
- **Propósito**: Trazas distribuidas y análisis de latencia
- **Puerto**: 9411
- **Tecnología**: Brave Tracer integrado con Micrometer Tracing

## 🛠️ **Configuración Implementada**

### **Dependencias en pom.xml**
```xml
<!-- Observabilidad -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

### **Configuración application.yml**

#### **Actuator y Métricas**
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus,httptrace,env
      base-path: /actuator
  endpoint:
    health:
      show-details: always
    metrics:
      enabled: true
    prometheus:
      enabled: true
  metrics:
    export:
      prometheus:
        enabled: true
    distribution:
      percentiles-histogram:
        http.server.requests: true
      percentiles:
        http.server.requests: 0.5, 0.95, 0.99
    tags:
      application: ${spring.application.name}
```

#### **Tracing**
```yaml
management:
  tracing:
    sampling:
      probability: 1.0  # 100% de sampling para desarrollo
  zipkin:
    tracing:
      endpoint: http://localhost:9411/api/v2/spans

logging:
  pattern:
    level: "%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]"
```

### **Configuración de Seguridad**
Se configuró Spring Security para permitir acceso sin autenticación a los endpoints de Actuator:

```java
// En SecurityConfig.java
auth.requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll();
auth.requestMatchers(new AntPathRequestMatcher("/actuator/prometheus")).permitAll();
auth.requestMatchers(new AntPathRequestMatcher("/actuator/health")).permitAll();
auth.requestMatchers(new AntPathRequestMatcher("/actuator/metrics")).permitAll();
```

## 📈 **Métricas Implementadas**

### **1. Métricas de Negocio Personalizadas**
Implementadas en `MetricsService.java`:

- `spaceship_created_total`: Contador de naves espaciales creadas
- `spaceship_updated_total`: Contador de naves espaciales actualizadas
- `spaceship_deleted_total`: Contador de naves espaciales eliminadas
- `spaceship_read_total`: Contador de operaciones de lectura
- `audit_messages_sent_total`: Contador de mensajes de auditoría enviados a RabbitMQ
- `kafka_messages_sent_total`: Contador de mensajes enviados a Kafka

### **2. Métricas de Rendimiento**
- `database_operation_duration_seconds`: Duración de operaciones de base de datos
- `external_service_duration_seconds`: Duración de llamadas a servicios externos

### **3. Métricas HTTP Estándar (Automáticas)**
- `http_server_requests_seconds`: Histograma de duración de requests HTTP
- Percentiles: 50%, 95%, 99%
- Desglosado por: método, URI, status, outcome

### **4. Métricas de Sistema (Automáticas)**
- **JVM**: Memoria heap/non-heap, GC, threads
- **HikariCP**: Pool de conexiones de base de datos
- **Kafka**: Consumer metrics, lag, throughput
- **RabbitMQ**: Conexiones, mensajes publicados/consumidos
- **Spring Security**: Filtros, autenticación, autorización

## 🔍 **Tracing Distribuido**

### **Implementación con Micrometer Tracing**
```java
// En MetricsService.java
public <T> T executeWithTracing(String operationName, Supplier<T> operation) {
    Span span = tracer.nextSpan().name(operationName).start();
    long startTime = System.nanoTime();

    try (Tracer.SpanInScope spanInScope = tracer.withSpan(span)) {
        T result = operation.get();
        span.tag("status", "success");
        return result;
    } catch (Exception e) {
        span.tag("status", "error");
        span.tag("error.message", e.getMessage());
        throw e;
    } finally {
        long duration = System.nanoTime() - startTime;
        recordDatabaseOperationTime(duration, TimeUnit.NANOSECONDS);
        span.end();
    }
}
```

### **Instrumentación Automática**
- **HTTP Requests**: Automática via Micrometer Tracing
- **Base de Datos**: Automática via instrumentación JDBC
- **Operaciones de Negocio**: Manual via `MetricsService.executeWithTracing()`

### **Propagación de Context**
- **Trace ID y Span ID** se propagan automáticamente
- **Logs** incluyen trace/span IDs para correlación
- **Headers HTTP** transportan context entre servicios

## 🐳 **Infraestructura Docker**

### **docker-compose-monitoring.yml**
```yaml
version: '3.8'

services:
  prometheus:
    image: prom/prometheus:latest
    ports:
      - "9090:9090"
    volumes:
      - ./prometheus/prometheus.yml:/etc/prometheus/prometheus.yml

  grafana:
    image: grafana/grafana:latest
    ports:
      - "3000:3000"
    environment:
      - GF_SECURITY_ADMIN_PASSWORD=admin
    volumes:
      - ./grafana/provisioning:/etc/grafana/provisioning
      - ./grafana/dashboards:/var/lib/grafana/dashboards

  zipkin:
    image: openzipkin/zipkin:latest
    ports:
      - "9411:9411"
```

### **Configuración de Prometheus**
```yaml
# prometheus/prometheus.yml
scrape_configs:
  - job_name: 'springboot-ships-backoffice'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['host.docker.internal:8080']
    scrape_interval: 5s
```

### **Provisioning Automático de Grafana**
- **Datasource**: Prometheus configurado automáticamente
- **Dashboard**: Dashboard personalizado pre-cargado
- **Configuración**: Sin setup manual necesario

## 📊 **Dashboard de Grafana**

### **Paneles Incluidos**

1. **HTTP Request Rate**
    - Métrica: `rate(http_server_requests_seconds_count[5m])`
    - Vista: Gráfico de líneas por endpoint

2. **Operaciones de Naves Espaciales**
    - Métricas: `spaceship_created_total`, `spaceship_updated_total`, etc.
    - Vista: Serie temporal de contadores

3. **Mensajes de Auditoría y Kafka**
    - Métricas: `audit_messages_sent_total`, `kafka_messages_sent_total`
    - Vista: Gauges

4. **Tiempos de Respuesta HTTP**
    - Métrica: `histogram_quantile(0.95, rate(http_server_requests_seconds_bucket[5m]))`
    - Vista: Percentiles 50%, 95%, 99%

5. **Métricas de Rendimiento de Servicios**
    - Métricas: `database_operation_duration_seconds`, `external_service_duration_seconds`
    - Vista: Serie temporal de duración

### **Alertas Configuradas**
- **High Request Rate**: Rate > 1000 req/s durante 5 minutos
- **Umbral configurable** para métricas críticas

## 🔧 **Uso de Herramientas**

### **Prometheus (http://localhost:9090)**

#### **Explorar Métricas**
```promql
# Rate de requests HTTP
rate(http_server_requests_seconds_count[5m])

# Percentil 95 de latencia
histogram_quantile(0.95, rate(http_server_requests_seconds_bucket[5m]))

# Memoria JVM usada
jvm_memory_used_bytes{area="heap"}

# Operaciones de naves espaciales
spaceship_created_total
```

#### **Verificar Targets**
- Ve a: http://localhost:9090/targets
- Verifica que `springboot-ships-backoffice` esté **UP**

### **Grafana (http://localhost:3000)**

#### **Acceso**
- Usuario: `admin`
- Contraseña: `admin`

#### **Explorar Datos**
1. **Explore** → Seleccionar Prometheus
2. **Query**: Escribir consultas PromQL
3. **Dashboards** → "Spring Boot Ships Backoffice Dashboard"

#### **Crear Visualizaciones**
1. **Dashboard** → New Panel
2. **Query**: Seleccionar métrica
3. **Visualization**: Elegir tipo de gráfico
4. **Settings**: Configurar títulos, unidades, umbrales

### **Zipkin (http://localhost:9411)**

#### **Buscar Trazas**
1. **Service Name**: springboot-ships-backoffice
2. **Operation Name**: Filtrar por operación específica
3. **Tags**: Filtrar por status, error, etc.

#### **Analizar Spans**
- **Timeline**: Visualización temporal de spans
- **Dependencies**: Mapa de servicios
- **Annotations**: Events dentro de spans

## 🧪 **Generación de Datos de Prueba**

### **APIs Disponibles**
```bash
# Health check (genera métricas HTTP)
curl http://localhost:8080/actuator/health

# Listar naves espaciales (genera métricas de lectura)
curl http://localhost:8080/api/spaceships

# Ver métricas directamente
curl http://localhost:8080/actuator/prometheus | grep spaceship
```

### **Operaciones CRUD** (requieren autenticación)
```bash
# Login para obtener JWT
curl -X POST http://localhost:8080/api/v1/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "password"}'

# Crear nave espacial (genera spaceship_created_total)
curl -X POST http://localhost:8080/api/spaceships \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"name": "Enterprise", "movie": "Star Trek"}'
```

## 📡 **Flujo de Datos**

### **Métricas**
```
Spring Boot App → Micrometer → Actuator Endpoint → Prometheus → Grafana
```

1. **Micrometer** recolecta métricas en la aplicación
2. **Actuator** expone métricas en `/actuator/prometheus`
3. **Prometheus** hace scraping cada 5 segundos
4. **Grafana** consulta Prometheus para visualizaciones

### **Trazas**
```
Spring Boot App → Brave Tracer → Zipkin Reporter → Zipkin Server
```

1. **Brave** captura spans de operaciones
2. **Zipkin Reporter** envía spans a Zipkin
3. **Zipkin** almacena y visualiza trazas

### **Logs Correlacionados**
```
Application Logs + Trace ID + Span ID = Observabilidad Completa
```

## 🚨 **Troubleshooting**

### **Métricas No Aparecen**
1. Verificar endpoints Actuator: `curl http://localhost:8080/actuator/prometheus`
2. Verificar targets Prometheus: http://localhost:9090/targets
3. Verificar configuración seguridad en `SecurityConfig.java`

### **Trazas No Aparecen**
1. Verificar Zipkin endpoint: http://localhost:9411
2. Verificar configuración `management.zipkin.tracing.endpoint`
3. Verificar logs de aplicación para errores de tracing

### **Grafana Sin Datos**
1. Verificar datasource Prometheus
2. Verificar consultas PromQL en Explore
3. Verificar que Prometheus tenga datos

## 🔮 **Extensiones Futuras**

### **Alertas Avanzadas**
- Configurar Alertmanager para notificaciones
- Integrar con Slack, email, PagerDuty
- Definir SLOs y SLIs

### **Métricas Adicionales**
- Business metrics específicos del dominio
- Métricas de calidad de código (cobertura, bugs)
- Métricas de infraestructura (CPU, memoria, disk)

### **Distributed Tracing Avanzado**
- Correlación entre múltiples servicios
- Sampling inteligente basado en errores
- Performance profiling detallado

### **Observabilidad de Seguridad**
- Métricas de intentos de login fallidos
- Detección de anomalías en patrones de acceso
- Auditoría de operaciones sensibles

## 📚 **Recursos y Referencias**

- [Micrometer Documentation](https://micrometer.io/docs)
- [Prometheus Configuration](https://prometheus.io/docs/prometheus/latest/configuration/configuration/)
- [Grafana Dashboard Best Practices](https://grafana.com/docs/grafana/latest/dashboards/)
- [Zipkin Architecture](https://zipkin.io/pages/architecture.html)
- [Spring Boot Actuator Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)

### 🏗️ **Evolución hacia Modularización**

La modularización es el **próximo paso evolutivo** natural de nuestro proyecto. Aunque actualmente implementamos Clean Architecture mediante convenciones de paquetes, la modularización proporcionaría **límites físicos** que harían imposible violar la arquitectura.

#### **🎯 Estructura Modular Propuesta**

```
📁 jrhub-core-api/
├── 📄 pom.xml                           # Parent POM
├── 🧠 jrhub-domain/                     # Módulo Domain
│   ├── 📄 pom.xml                       # Sin dependencias externas
│   └── 📁 src/main/java/com/jrhub/domain/
│       ├── SpaceShip.java
│       ├── User.java
│       ├── MovieSpaceShipRepository.java
│       └── UserRepository.java
│
├── 📦 jrhub-application/                # Módulo Application  
│   ├── 📄 pom.xml                       # Solo depende de domain
│   └── 📁 src/main/java/com/jrhub/application/
│       ├── usecases/
│       ├── services/
│       ├── dto/
│       └── mappers/
│
├── 🌐 jrhub-infrastructure/             # Módulo Infrastructure
│   ├── 📄 pom.xml                       # Depende de domain y application
│   └── 📁 src/main/java/com/jrhub/infrastructure/
│       ├── persistence/
│       ├── messaging/
│       ├── security/
│       └── config/
│
├── 🎯 jrhub-presentation/               # Módulo Presentation
│   ├── 📄 pom.xml                       # Solo depende de application
│   └── 📁 src/main/java/com/jrhub/api/
│       ├── controllers/
│       ├── security/
│       └── exceptions/
│
└── 🚀 jrhub-application-runner/         # Módulo Main
    ├── 📄 pom.xml                       # Depende de todos los módulos
    └── 📁 src/main/java/com/jrhub/
        └── JrHubApplication.java
```

#### **🔒 Control de Dependencias por Módulo**

```xml
<!-- jrhub-domain/pom.xml -->
<dependencies>
    <!-- SIN DEPENDENCIAS EXTERNAS - CORE PURO -->
</dependencies>

<!-- jrhub-application/pom.xml -->
<dependencies>
    <dependency>
        <groupId>com.jrhub</groupId>
        <artifactId>jrhub-domain</artifactId>
    </dependency>
    <!-- MapStruct, validation, etc. -->
</dependencies>

<!-- jrhub-infrastructure/pom.xml -->
<dependencies>
    <dependency>
        <groupId>com.jrhub</groupId>
        <artifactId>jrhub-domain</artifactId>
    </dependency>
    <dependency>
        <groupId>com.jrhub</groupId>
        <artifactId>jrhub-application</artifactId>
    </dependency>
    <!-- Spring Data JPA, RabbitMQ, Kafka, JWT, etc. -->
</dependencies>

<!-- jrhub-presentation/pom.xml -->
<dependencies>
    <dependency>
        <groupId>com.jrhub</groupId>
        <artifactId>jrhub-application</artifactId>
    </dependency>
    <!-- Spring Web, Security, etc. -->
</dependencies>
```

#### **🛡️ Ventajas de la Modularización**

1. **🔒 Límites Físicos Imposibles de Violar**
   ```java
   // ❌ ESTO SERÍA IMPOSIBLE - Error de compilación
   import com.jrhub.infrastructure.JwtService; // No está en el classpath!
   
   @RestController
   public class LoginController {
       // Compilación fallará - infrastructure no disponible
   }
   ```

2. **📦 Reutilización de Módulos**
   ```xml
   <!-- Otro proyecto puede usar solo el dominio -->
   <dependency>
       <groupId>com.jrhub</groupId>
       <artifactId>jrhub-domain</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

3. **🧪 Testing Mejorado por Módulo**
   ```bash
   mvn test -pl jrhub-domain        # Solo tests de dominio
   mvn test -pl jrhub-application   # Solo tests de aplicación
   mvn test -pl jrhub-infrastructure # Solo tests de infraestructura
   ```

4. **⚡ Builds Incrementales**
   ```bash
   # Solo rebuilding módulos que cambiaron
   mvn clean install -pl jrhub-application -am
   ```

5. **👥 Equipos Especializados**
   - **Team Domain**: Solo trabaja en `jrhub-domain`
   - **Team Infrastructure**: Solo `jrhub-infrastructure` 
   - **Team API**: Solo `jrhub-presentation`

#### **🔄 Migración Gradual Sugerida**

**Fase 1: Preparación**
```bash
# 1. Crear estructura de módulos
mkdir jrhub-domain jrhub-application jrhub-infrastructure jrhub-presentation

# 2. Mover clases a sus módulos correspondientes
mv src/main/java/com/jrhub/domain/* jrhub-domain/src/main/java/com/jrhub/domain/
mv src/main/java/com/jrhub/application/* jrhub-application/src/main/java/com/jrhub/application/
# etc...
```

**Fase 2: Configuración Maven**
```xml
<!-- Parent pom.xml -->
<modules>
    <module>jrhub-domain</module>
    <module>jrhub-application</module>
    <module>jrhub-infrastructure</module>
    <module>jrhub-presentation</module>
    <module>jrhub-application-runner</module>
</modules>
```

**Fase 3: Validación**
```bash
mvn clean compile  # Debe fallar si hay violaciones arquitectónicas
```

#### **📊 Comparación: Antes vs Después**

| Aspecto | **Actual (Paquetes)** | **Propuesto (Módulos)** |
|---------|----------------------|------------------------|
| **Límites** | Solo por convención | Físicos (Maven) |
| **Violaciones** | Posibles sin darse cuenta | Error de compilación |
| **Reutilización** | Difícil | Fácil (artifacts independientes) |
| **Testing** | Todo junto | Por módulo |
| **CI/CD** | Build completo siempre | Builds incrementales |
| **Equipos** | Todos en el mismo código | Especializados por módulo |

#### **🚀 Beneficios Adicionales**

1. **Microservicios Ready**
   ```java
   // jrhub-domain puede convertirse en shared library
   // jrhub-infrastructure-events → Event Service
   // jrhub-infrastructure-persistence → Data Service
   ```

2. **Versionado Independiente**
   ```xml
   <dependency>
       <groupId>com.jrhub</groupId>
       <artifactId>jrhub-domain</artifactId>
       <version>2.1.0</version>  <!-- Domain evoluciona independiente -->
   </dependency>
   ```

3. **Performance de Build**
   ```bash
   # Solo cambios en presentation
   mvn clean install -pl jrhub-presentation -am  # 80% más rápido
   ```

#### **🎯 Decisión Recomendada**

**SÍ, definitivamente tiene sentido modularizar** porque:

- ✅ **Enforcement Automático**: Maven impide violaciones arquitectónicas
- ✅ **Escalabilidad**: Equipos pueden trabajar independientemente
- ✅ **Reutilización**: Módulos como building blocks
- ✅ **Performance**: Builds más rápidos
- ✅ **Microservices**: Base para futura evolución

**Momento Ideal**: **Ahora**, cuando la arquitectura está estabilizada y antes de que el equipo crezca.



