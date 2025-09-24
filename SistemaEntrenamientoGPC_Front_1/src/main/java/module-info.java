/**
 * Módulo principal de la aplicación de entrenamiento GPC.
 * <p>
 * Exporta los paquetes de beans (JSF), modelo, servicios HTTP y utilidades.
 * Se abren (opens) ciertos paquetes a Gson para permitir la deserialización
 * reflexiva de atributos privados.
 * </p>
 * <p>
 * NOTAS:
 * <ul>
 *   <li>Si se agregan librerías externas (PrimeFaces/JSF) en el module path y exponen
 *       nombres de módulo explícitos, puede ser necesario añadir sus {@code requires} aquí.</li>
 *   <li>Para Apache Commons Codec (Base64) si se coloca en el module path, añadir la línea
 *       correspondiente (por ejemplo: {@code requires org.apache.commons.codec;} o {@code requires commons.codec;}
 *       según el Automatic-Module-Name del JAR). Mientras esté sólo en el classpath no es necesario modificar esto.</li>
 *   <li>Si en el futuro se cambia de Gson a otra librería que use reflexión, ajustar las sentencias {@code opens}.</li>
 * </ul>
 * </p>
 */
module co.edu.unbosque.sistemaentrenamiento {
    // Dependencias del JDK usadas en el código (HttpClient, etc.)
    requires java.net.http;

    // Librería externa Gson (asegúrese de que el JAR esté en el module path con Automatic-Module-Name: com.google.gson)
    requires com.google.gson;

    // Exposición pública de las API de la aplicación
    exports co.edu.unbosque.beans;
    exports co.edu.unbosque.model;
    exports co.edu.unbosque.service;
    exports co.edu.unbosque.util;

    // Apertura para frameworks de serialización (Gson) que usan reflexión para acceder a campos privados
    opens co.edu.unbosque.model to com.google.gson;
    opens co.edu.unbosque.beans to com.google.gson;
}
