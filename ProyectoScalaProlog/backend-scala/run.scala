import java.io._
import sys.process._

// =======================================
// Clase Estudiante
// =======================================
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

// =======================================
// Objeto principal
// =======================================
object RunProyecto {
  def main(args: Array[String]): Unit = {

    // Lista de estudiantes de ejemplo
    val estudiantes = List(
      Estudiante("Juan","202601",80,65,5,"Baja",3,true,"Regular",true),
      Estudiante("Ana","202602",92,88,10,"Alta",10,true,"Bueno",true),
      Estudiante("Luis","202603",70,72,6,"Media",2,false,"Regular",false),
      Estudiante("María","202604",95,95,12,"Alta",15,true,"Bueno",true),
      Estudiante("Pedro","202605",60,50,2,"Baja",1,false,"Bajo",false)
    )

    // Función pura para clasificar riesgo
    def clasificacionRiesgo(estudiantes: List[Estudiante]): List[(String, String)] = {
      estudiantes.map { e =>
        val riesgo = if (e.promedio < 70 || e.asistencia < 75) "Alto"
                     else if (e.promedio < 85 || e.asistencia < 90) "Medio"
                     else "Bajo"
        (e.nombre, riesgo)
      }
    }

    // Mostrar clasificación en consola
    val resultado = clasificacionRiesgo(estudiantes)
    resultado.foreach{ case (nombre, riesgo) =>
      println(s"$nombre: $riesgo")
    }

    // =======================================
    // Ruta absoluta de tu archivo Prolog
    // =======================================
    val prologPath = "C:\\Users\\Wisi\\Downloads\\ProyectoScalaProlog\\prolog\\knowledge_base.pl"
    
    // =======================================
    // Generar archivo Prolog y escribir reglas
    // =======================================
    val writer = new PrintWriter(new File(prologPath))

    // Suprimir warnings de cláusulas discontinuas
    writer.println(":- discontiguous asistencia/2.")
    writer.println(":- discontiguous promedio/2.")
    writer.println(":- discontiguous tareas_entregadas/2.")
    writer.println(":- discontiguous participacion/2.")
    writer.println(":- discontiguous horas_estudio/2.")
    writer.println(":- discontiguous entrega_evidencias/2.")
    writer.println(":- discontiguous nivel_comprension/2.")
    writer.println(":- discontiguous usa_recursos/2.")
    writer.println("% Hechos de estudiantes")

    // Escribir hechos
    estudiantes.foreach{ e =>
      writer.println(s"asistencia('${e.nombre}',${e.asistencia}).")
      writer.println(s"promedio('${e.nombre}',${e.promedio}).")
      writer.println(s"tareas_entregadas('${e.nombre}',${e.tareasEntregadas}).")
      writer.println(s"participacion('${e.nombre}','${e.participacion}').")
      writer.println(s"horas_estudio('${e.nombre}',${e.horasEstudio}).")
      writer.println(s"entrega_evidencias('${e.nombre}',${e.entregaEvidencias}).")
      writer.println(s"nivel_comprension('${e.nombre}','${e.nivelComprension}').")
      writer.println(s"usa_recursos('${e.nombre}',${e.usaRecursos}).")
    }

    // Reglas Prolog
    writer.println("""
riesgo_alto(X) :- asistencia(X,A), A < 75.
riesgo_alto(X) :- promedio(X,P), P < 70.
riesgo_medio(X) :- not(riesgo_alto(X)), asistencia(X,A), A < 90.
riesgo_bajo(X) :- not(riesgo_alto(X)), not(riesgo_medio(X)).
recomendacion(X,'Estudiar minimo 2 horas') :- riesgo_alto(X).
recomendacion(X,'Mantener hábitos actuales') :- riesgo_bajo(X).
recomendacion(X,'Prepararse para examen') :- promedio(X,P), P < 85.
recomendacion(X,'Revisar tareas') :- tareas_entregadas(X,T), T < 8.
recomendacion(X,'Estudiar en grupo') :- participacion(X,'Media').
recomendacion(X,'Autoevaluación semanal') :- riesgo_medio(X).
recomendacion(X,'Mantener esa constancia') :- riesgo_bajo(X).
""")

    writer.close()

    // =======================================
    // Ejecutar Prolog sin lanzar excepción
    // =======================================
    val comando = s"""swipl -s "$prologPath" -g "forall(recomendacion(X,R), (write(X), write(' -> '), write(R), nl)), halt.""""
    comando.! // ! evita que Scala lance error si Prolog termina con false
  }
}