# Codigo base

Desarrollo de actividades universitarias semestre 2024-2

# Problema a resolver

Se requiere desarrollar un sistema de reservas de viajes personalizadas, donde los usuarios pueden planificar su itinerario de viaje de manera detallada. Cada reserva puede incluir información como la fecha de salida, la duración del viaje, el tipo de alojamiento, preferencias de transporte, actividades planeadas y dietas especiales. Sin embargo, no todos los usuarios desean proporcionar la misma cantidad de detalles al realizar una reserva. Algunos podrían querer especificar sólo la fecha de salida y el destino, mientras que otros desean personalizar cada aspecto del viaje. Haga uso del patrón Builder para flexibilizar la creación de objetos de tipo Reserva. Cree la clase Reserva y ReservaBuilder. Además, haga una clase adicional para probar la implementación.

# Autor

-   Autor Cesar Baquiro

# Herramientas

-   [java 17](https://adoptium.net/es)
-   [junit 5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.10.0)
-   [maven](https://maven.apache.org)

# Construcción y pruebas

Para compilar el proyecto puede usar el comando:

```shell
mvn clean compile
```

Para ejecutar las pruebas puede usar el comando:

```shell
 mvn clean test
```

Para generar el jar puede usar el comando:

```shell
 mvn clean package
```

y para ejecutar el jar

```shell
 java -jar target/rectangle_tdd-1.0.jar
```