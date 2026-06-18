Respuestas:
Pregunta 1: ¿Por qué Collections.sort() no compila cuando le pasamos una List de Estudiante? ¿Qué contrato exige Java que nuestra clase no está cumpliendo?
No compila porque Java necesita saber de antemano cómo ordenar los estudiantes. Para eso, exige que la clase implemente la interfaz Comparable, que define el orden por defecto. Como la clase no tenía nada de esto, el compilador tira error porque no sabe si acomodarlos por nota, edad o nombre.

Pregunta 2: ¿Por qué elegiste el atributo promedio como orden natural? ¿Qué pasaría si mañana un nuevo requisito pide ordenar por cantidadMateriasAprobadas? ¿Modificarías compareTo? ¿Qué consecuencias tendría?
El promedio es el dato más lógico para ordenar a los alumnos por defecto. Si piden cambiar el orden a materias aprobadas, modificar el compareTo sería un error porque el orden natural tiene que ser fijo. Si lo cambiamos, se pueden romper otras estructuras de Java (como un TreeSet) y obligaría a tocar el código interno de la clase Estudiante cada vez que cambie el criterio.

Pregunta 3: Comparable nos ata a un único criterio de ordenamiento. ¿Qué problemas de diseño introduce esto si nuestro sistema necesitara ordenar la misma lista de estudiantes de 4 formas distintas según el contexto? Relacioná tu respuesta con los principios de responsabilidad única (SRP) y abierto/cerrado (OCP).
Si se resuelven todos los órdenes con Comparable, se rompen dos reglas de diseño:

-Responsabilidad Única: La clase Estudiante tendría que encargarse de sus datos y también de conocer todas las formas de ordenar que pida el sistema.
- Abierto/Cerrado: Cada vez que haga falta un orden nuevo, habría que abrir el archivo Estudiante para modificarlo en vez de dejarlo cerrado. Lo correcto es manejar los otros criterios por fuera usando Comparator.

Pregunta 4: Explicá con tus palabras qué es un overflow de enteros, por qué el truco de la resta lo provoca, qué parte del contrato de Comparator rompe, y por qué Integer.compare() no sufre este problema.
Un overflow pasa cuando una operación da un resultado más grande o más chico de lo que un entero puede guardar, haciendo que el valor se pase del límite y cambie de signo de golpe.
Si para comparar se resta un número positivo muy alto con uno negativo muy bajo, el resultado se desborda y da al revés. Esto rompe la regla de Comparator porque Java espera un valor positivo si el primero es mayor, pero el overflow lo vuelve negativo y ordena al revés. Integer.compare() no usa restas; compara con mayor o menor y devuelve de forma segura -1, 1 o 0.

Pregunta 5: ¿Qué patrón de diseño estás aplicando al usar un Map de String, Comparator en lugar de un switch? Explicá cómo se relaciona este patrón con el polimorfismo y por qué es preferible a la alternativa procedural.
Se aplica el patrón Strategy. En lugar de un switch gigante, se guardan los comparadores en un mapa. Se relaciona con el polimorfismo porque el servicio usa la interfaz Comparator sin importarle cuál criterio sea exactamente. En carrera, el programa busca la palabra clave en el mapa, saca el comparador que corresponde y lo ejecuta. Es mejor porque si se agrega otro filtro, solo se suma una línea al mapa sin tocar el código principal.