## Algoritmos

En este repositorio podemos encontrar mis propias implementaciones de algunos algoritmos conocidos.

---

### Búsqueda A*

Para empezar, tengo el algoritmo de búsqueda A*, en el que he diseñado una función `solve` que se encarga de obtener el camino desde un punto A a un punto B.

`public Board solve(Board b, int[] f, int[] l, Set<int[]> frontier, HashMap<int[], int[]> sol)`

Como podemos ver, en esta función pasamos como argumentos un objeto de tipo `Board`, que es el tablero que queremos resolver; un array con dos enteros `f`, donde almacenamos la posición de la casilla final; un Set `frontier`, donde vamos a ir almacenando todas las casillas que podemos explorar desde la que estamos; y, por último, un HashMap `sol` donde se guardará la solución. 

También utilizo dos funciones auxiliares `explore` y `backtrack`. La primera se encarga de, dándole todos los nodos que puede explorar (`frontier`), devolver cuál es el más prometedor. Para ello he diseñado una heurística sencilla que estima el coste de cada casilla y se queda con la de menor. 

La segunda función auxiliar que utilizo sirve para, una vez haber llegado a la casilla B, volver hacia atrás para ir construyendo la solución final. Para ello, va tomando el padre de cada una de las casillas exploradas, de modo que llega inevitablemente a la casilla inicial A, trazando la solución.

<center>

![](/a-search/imgs/unsolved.png) ![](/a-search/imgs/solved.png)
</center>

---

### Resolución de Sudoku por Backtracking

También podemos encontrar un pequeño programa que calcula todas las posibles soluciones de cualquier Sudoku mediante un algoritmo de backtracking.

Como cualquier algoritmo por vuelta atrás, lo único que hace es, ir probando valores en cada celda vacía y, cuando llega a alguna en la que no hay ninguna opción válida, vuelve atrás y prueba nuevas combinaciones.

Una pequeña variante que he añadido a mi código consiste en que no solo te devuelve la primera solución que encuentra, sino todas las soluciones posibles.

<center>

![](/sudoku/imgs/unsolved.png) ![](/sudoku/imgs/solved1.png) ![](/sudoku/imgs/solved2.png)

</center>