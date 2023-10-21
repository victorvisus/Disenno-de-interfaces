-- 10. Nombre de los empleados que han ganado más de 2000  en el año 2006.
/* JOIN es otra manera de unir tablas: con FROM le indicamos la primera tabla y
    después con JOIN indicamos una segunda tabla y le indicamos la relación
    mediante ON tabla2.columna = tabla1.columna
*/
/* Con GROUP BY le decimos como queremos que agrupe los registros, con que columnas,
    con la clausula HAVING le aplicamos un filtro al agrupamiento
*/
/*
SELECT Empleados.Nombre, SUM(Just_Nominas.Ingreso) AS Total_Ingreso_2006
    FROM Empleados
    JOIN Just_Nominas ON Just_Nominas.cod_emp = Empleados.codigo
    WHERE Just_nominas.ejercicio = 2006
    GROUP BY Empleados.nombre HAVING SUM(Just_Nominas.ingreso) > 2000;
*/


SELECT
 clientes.Nombre, clientes.Direccion, clientes.Ciudad,
 pedidos.ID_Pedido, pedidos.Fecha_Pedido,
 detalle_pedidos.ID_Articulo, detalle_pedidos.Precio, detalle_pedidos.Unidades
  FROM clientes
 JOIN pedidos ON clientes.ID_Cliente = pedidos.ID_Cliente
 JOIN detalle_pedidos ON pedidos.ID_Pedido = detalle_pedidos.ID_Pedido;
 -- GROUP BY clientes.Nombre;
 
 
 
 
 