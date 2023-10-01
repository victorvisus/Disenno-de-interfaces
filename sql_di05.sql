SELECT
 cl.Nombre, cl.Direccion, cl.Ciudad,
 p.ID_Pedido, p.Fecha_Pedido,
 ar.Descripcion,
 dp.Unidades
  FROM clientes AS cl
 JOIN pedidos AS p
 ON p.ID_Cliente = cl.ID_Cliente
 RIGHT JOIN detalle_pedidos AS dp
 ON dp.ID_Pedido = p.ID_Pedido
 RIGHT JOIN articulos AS ar
 ON ar.Id_articulo = dp.ID_Articulo;