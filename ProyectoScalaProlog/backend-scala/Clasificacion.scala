import java.io._
import sys.process._

// Lista de ejemplo de estudiantes
val estudiantes = List(
  Estudiante("Juan","202601",80,65,5,"Baja",3,true,"Regular",true),
  Estudiante("Ana","202602",92,88,10,"Alta",10,true,"Bueno",true),
  Estudiante("Luis","202603",70,72,6,"Media",2,false,"Regular",false),
  Estudiante("María","202604",95,95,12,"Alta",15,true,"Bueno",true)
)

// Función pura usando map/filter
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
resultado.foreach{ case (nombre, riesgo) => println(s"$nombre: $riesgo") }

// Generar archivo Prolog
val writer = new PrintWriter(new File("prolog/knowledge_base.pl"))
writer.println("% Hechos de estudiantes")
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

// Reglas Prolog (15 reglas)
writer.println("""
riesgo_alto(X) :- asistencia(X,A), A < 75.
riesgo_alto(X) :- promedio(X,P), P < 70.
riesgo_medio(X) :- not(riesgo_alto(X)), asistencia(X,A), A < 90.
riesgo_bajo(X) :- not(riesgo_alto(X)), not(riesgo_medio(X)).
recomendacion(X,'Estudiar más horas') :- horas_estudio(X,H), H < 5.
recomendacion(X,'Entregar todas las evidencias') :- entrega_evidencias(X,false).
recomendacion(X,'Participar en clase') :- participacion(X,'Baja').
recomendacion(X,'Revisar material de estudio') :- usa_recursos(X,false).
recomendacion(X,'Solicitar apoyo docente') :- riesgo_alto(X).
recomendacion(X,'Mantener hábitos actuales') :- riesgo_bajo(X).
recomendacion(X,'Prepararse para examen') :- promedio(X,P), P < 85.
recomendacion(X,'Revisar tareas') :- tareas_entregadas(X,T), T < 8.
recomendacion(X,'Estudiar en grupo') :- participacion(X,'Media').
recomendacion(X,'Autoevaluación semanal') :- riesgo_medio(X).
recomendacion(X,'Mantener constancia') :- riesgo_bajo(X).
""")
writer.close()

// Ejecutar Prolog desde Scala y mostrar resultados
val comando = "swipl -s prolog/knowledge_base.pl -g \"recomendacion(X,R), write(X), write(' -> '), write(R), nl, fail.\" -t halt"
val salida = comando.!!
println("----- Resultados Prolog -----")
println(salida)
