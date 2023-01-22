## Resolución de Sudoku por Backtracking

Pequeño programa que calcula todas las posibles soluciones de cualquier Sudoku mediante backtracking.

La dinámica de este algoritmo consiste en ir introduciendo el primer valor válido en cada casilla. Si se llega a una casilla en la que ningún valor cumpla las restricciones propuesta, se volverá atrás y se probará una combinación nueva. Este proceso se repetirá hasta que no haya ninguna casilla vacía.