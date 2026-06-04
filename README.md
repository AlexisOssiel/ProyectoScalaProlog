# ProyectoScalaProlog
Sistema híbrido Scala-Prolog que captura datos de estudiantes, procesa asistencia, calificaciones y hábitos de estudio, y genera automáticamente recomendaciones académicas usando inferencia lógica, con interfaz web y trazabilidad de las reglas activadas.

Proyecto Integrador Scala-Prolog – Paso a Paso
1️⃣ Preparación del entorno
Instalación de Java JDK
Verifica si tienes Java instalado en CMD:
java -version
javac -version
Si no está instalado, descarga OpenJDK 17 (Windows x64) desde:
https://jdk.java.net/archive/

Instala Java o extrae el zip en una carpeta como:

C:\Usuarios\Wisi\Descargas\Java\jdk-17
Configura variables de entorno:
JAVA_HOME = C:\Usuarios\Wisi\Descargas\Java\jdk-17
Agrega al Path: %JAVA_HOME%\bin
Instalación de Scala 3
Descarga Scala 3.8.3 binario para Windows:
https://www.scala-lang.org/download/

Extrae el zip en:

C:\Usuarios\Wisi\Descargas\Scala3\
Agrega al Path: C:\Usuarios\Wisi\Descargas\Scala3\bin
Verifica la instalación:
scala -version
scalac -version
Instalación de SWI-Prolog
Descarga desde: https://www.swi-prolog.org/download/stable
Instala normalmente.
Verifica en CMD:
swipl --version
2️⃣ Estructura del proyecto
ProyectoScalaProlog/
├── backend-scala/
│   └── run.scala           # Código Scala principal
├── prolog/
│   └── knowledge_base.pl   # Base de hechos y reglas (generado desde Scala)
├── frontend/
│   └── index.html          # Formulario HTML
├── docs/
│   ├── Proyecto_Tecnico.pdf
│   └── Bitacora.xlsx
└── README.md
backend-scala: contiene la lógica de Scala y la integración con Prolog.
prolog: contiene el archivo .pl generado por Scala con hechos y reglas.
frontend: formulario de captura de datos.
docs: documentación del proyecto.
README.md: instrucciones de ejecución.
3️⃣ Código Scala (run.scala)
Definición de la clase Estudiante
case class Estudiante(
  nombre: String,
  matricula: String,
  asistencia: Double,
  promedio: Double,
  tareasEntregadas: Int,
  participacion: String,
  horasEstudio: Int,
  entregaEvidencias: Boolean,
  nivelComprension: String,
  usaRecursos: Boolean
)
Objeto principal y función main
Lista de estudiantes de ejemplo.
Función pura clasificacionRiesgo que usa map para clasificar a los estudiantes.
Generación del archivo Prolog (knowledge_base.pl) con hechos y reglas.
Ejecución de Prolog desde Scala mostrando las recomendaciones.
val prologPath = "C:\\Usuarios\\Wisi\\Downloads\\ProyectoScalaProlog\\prolog\\knowledge_base.pl"
Esta ruta apunta al archivo .pl existente.
Agregar un nuevo estudiante
Solo se agrega en la lista val estudiantes = List(...):
Estudiante("Pedro","202605",60,50,2,"Baja",1,false,"Bajo",false)
La función de clasificación y el bloque que genera Prolog usan la misma lista, así que el nuevo alumno se incluye automáticamente.
4️⃣ Archivo Prolog (knowledge_base.pl)
Generado automáticamente desde Scala.
Contiene:
Hechos de todos los estudiantes.
Reglas: riesgo_alto, riesgo_medio, riesgo_bajo, recomendacion.
Directivas discontiguous para evitar warnings.
Se genera en UTF-8 para que los acentos se vean correctamente.
5️⃣ Frontend (index.html)
Formulario de captura de datos de estudiantes.
Envía los datos al backend (demo de fetch en JS).
Muestra resultados en un <div>.
<form id="formulario">
  Nombre: <input type="text" name="nombre"><br>
  Asistencia: <input type="number" name="asistencia"><br>
  Promedio: <input type="number" name="promedio"><br>
  <button type="submit">Diagnosticar</button>
</form>
<div id="resultado"></div>
6️⃣ Ejecución del proyecto
En CMD
cd "C:\Usuarios\Wisi\Downloads\ProyectoScalaProlog\backend-scala"
scala run.scala
Verifica:
Clasificación de estudiantes (Alto/Medio/Bajo)
Recomendaciones de Prolog (recomendacion/2)
No aparecen warnings ni errores
Pruebas importantes
Agregar un alumno nuevo → verificar que se incluya en la clasificación y Prolog.
Entrada inválida → estudiante con asistencia >100 → programa estable.
Consulta inédita → agregar temporalmente un estudiante y ejecutar.
Modificación en vivo → cambiar reglas y ejecutar nuevamente.
7️⃣ Documentación
Proyecto_Tecnico.pdf
Arquitectura completa (diagrama)
Explicación de reglas y trazabilidad
Pruebas funcionales, inválidas y consulta inédita
Observaciones y decisiones técnicas
Bitacora.xlsx
Tabla con fechas, acciones y resultados
Registrar cambios de alumnos, reglas, pruebas y modificaciones en vivo
8️⃣ Entrega final
backend-scala/run.scala
prolog/knowledge_base.pl
frontend/index.html
docs/Proyecto_Tecnico.pdf
docs/Bitacora.xlsx
README.md con instrucciones de ejecución
