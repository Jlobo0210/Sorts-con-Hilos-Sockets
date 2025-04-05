Se desea tener un computador ejecutando un programa y que sea el cliente, al
comienzo del programa debe preguntarle al usuario qué problema quiere resolver.

Los problemas para resolver son:
• Dado un vector / lista de 𝑛 posiciones, ordenarlo de manera ascendente
usando el algoritmo de Mergesort.
• Dado un vector / lista de 𝑛 posiciones, ordenarlo de manera ascendente
usando el algoritmo de Heapsort.
• Dado un vector / lista de 𝑛 posiciones, ordenarlo de manera ascendente
usando el algoritmo de Quicksort.

Una vez decidido el problema a resolver este debe ser enviado a otro computador
(distinto del cliente) llamado worker_0 el cual junto con la ayuda de otro worker_1,
deben ordenar el vector.

Cada worker contara con un tiempo 𝑡 (en segundos y dado por el cliente) para
poder resolver, hasta donde pueda, el problema. Si en el tiempo 𝑡 el worker que
actualmente tiene el vector no ha terminado la tarea, deberá mandar el vector
como lo lleve al otro worker y este debe continuar el trabajo.

Una vez resuelto el problema, el worker que haya terminado el problema debe
devolver el vector ordenado al cliente junto con el tiempo que le tomó resolverlo.
Finalmente, esta información debe ser mostrada por el cliente
