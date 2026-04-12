# Programacion2
Este repositorio contiene una serie de proyectos y ejercicios desarrollados como parte del plan de estudios de la Universidad. Esta actividad planteaba el reto en parejas de modelar un sistema de negocio real (un camping con diversos tipos de alojamiento) para aplicar de forma práctica los pilares de la Programación Orientada a Objetos (POO). Conceptos clave de la asignatura de Programación 2.

# Sistema de Gestión de Camping - Programación Orientada a Objetos (Java)
Este proyecto contiene un sistema para la gestión de reservas, clientes y alojamientos de un camping, desarrollado íntegramente en Java.

### Arquitectura y Conceptos Aplicados
* **Jerarquía de Herencia:** Implementación de una estructura de clases basada en una clase abstracta `Allotjament`, de la cual derivan tipos específicos como `Bungalow`, `Glamping`, `Casa` y `Parcela`.
* **Polimorfismo y Abstracción:** Uso de interfaces (`InCamping`, `InAllotjament`) para definir comportamientos comunes y aplicación de métodos abstractos para validaciones específicas de cada alojamiento.
* **Gestión de Colecciones:** Utilización de `ArrayList` e iteradores para la manipulación dinámica de datos de clientes y reservas.
* **Tratamiento de Excepciones:** Diseño de excepciones personalizadas (`ExcepcioReserva`) para gestionar errores de flujo y validaciones de negocio.
* **Lógica de Negocio Compleja:** Implementación de algoritmos para el cálculo de disponibilidad de fechas, estancias mínimas por temporada y estados operativos de los servicios.
