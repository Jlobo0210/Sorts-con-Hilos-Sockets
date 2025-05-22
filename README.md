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

____________________________________________________________

You want to have a computer running a program and that is the client, at
beginning of the program should ask the user what problem he wants to solve.

The problems to solve are:

- Given a vector / list of 𝑛 positions, sort it in ascending order
using the Mergesort algorithm.

- Given a vector / list of 𝑛 positions, sort it in ascending order
using the Heapsort algorithm.

- Given a vector / list of 𝑛 positions, sort it in ascending order
using the Quicksort algorithm.

Once the problem to be solved has been decided, it must be sent to another computer
(different from the client) called worker_0 which together with the help of another worker_1,
must sort the vector.

Each worker will have a time 𝑡 (in seconds and given by the client) for
to solve, as far as possible, the problem. If in the time 𝑡 the worker that
currently has the vector has not finished the task, it must send the vector
as it takes it to the other worker and this one must continue the work.

Once the problem has been solved, the worker that has finished the problem should
return the ordered vector to the client along with the time it took to solve it.
Finally, this information should be displayed by the client.

Translated with DeepL.com (free version)
