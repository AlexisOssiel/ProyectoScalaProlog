:- discontiguous asistencia/2.
:- discontiguous promedio/2.
:- discontiguous tareas_entregadas/2.
:- discontiguous participacion/2.
:- discontiguous horas_estudio/2.
:- discontiguous entrega_evidencias/2.
:- discontiguous nivel_comprension/2.
:- discontiguous usa_recursos/2.
% Hechos de estudiantes
asistencia('Juan',80.0).
promedio('Juan',65.0).
tareas_entregadas('Juan',5).
participacion('Juan','Baja').
horas_estudio('Juan',3).
entrega_evidencias('Juan',true).
nivel_comprension('Juan','Regular').
usa_recursos('Juan',true).
asistencia('Ana',92.0).
promedio('Ana',88.0).
tareas_entregadas('Ana',10).
participacion('Ana','Alta').
horas_estudio('Ana',10).
entrega_evidencias('Ana',true).
nivel_comprension('Ana','Bueno').
usa_recursos('Ana',true).
asistencia('Luis',70.0).
promedio('Luis',72.0).
tareas_entregadas('Luis',6).
participacion('Luis','Media').
horas_estudio('Luis',2).
entrega_evidencias('Luis',false).
nivel_comprension('Luis','Regular').
usa_recursos('Luis',false).
asistencia('María',95.0).
promedio('María',95.0).
tareas_entregadas('María',12).
participacion('María','Alta').
horas_estudio('María',15).
entrega_evidencias('María',true).
nivel_comprension('María','Bueno').
usa_recursos('María',true).
asistencia('Pedro',60.0).
promedio('Pedro',50.0).
tareas_entregadas('Pedro',2).
participacion('Pedro','Baja').
horas_estudio('Pedro',1).
entrega_evidencias('Pedro',false).
nivel_comprension('Pedro','Bajo').
usa_recursos('Pedro',false).
asistencia('Lucía',85.0).
promedio('Lucía',78.0).
tareas_entregadas('Lucía',9).
participacion('Lucía','Media').
horas_estudio('Lucía',6).
entrega_evidencias('Lucía',true).
nivel_comprension('Lucía','Regular').
usa_recursos('Lucía',true).

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

