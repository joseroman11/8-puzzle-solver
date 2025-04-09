# 🧩 Solucionador de 8-Puzzle con A*

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)

Implementación del algoritmo A* para resolver el clásico 8-Puzzle (3×3) con interfaz gráfica.

## 🚀 Características
- Algoritmo A* con heurística de distancia Manhattan
- Verificación automática de solubilidad
- Visualización paso a paso con GUI
- Solución óptima (mínimos movimientos)

## 📦 Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/8-puzzle-solver.git
   ```
2. Abre en NetBeans/Eclipse como proyecto Java existente

## 🎯 Uso
Ejecuta `Main.java` e ingresa tu puzzle:
```
Fila 1: 1 2 3
Fila 2: 4 0 5
Fila 3: 7 8 6
```

## 📊 Complejidad Algorítmica
| Componente | Complejidad |
|------------|-------------|
| Verificación | O(n²) |
| A* (peor caso) | O(bᵈ) |
| Heurística | O(n) |

## 📝 Licencia
MIT License - Ver [LICENSE](LICENSE)
