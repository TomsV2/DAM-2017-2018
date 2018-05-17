SELECT * FROM CLTiposVino
SELECT * FROM CLVinos
SELECT * FROM CLCartaVinos
SELECT * FROM CLPlatos

SELECT * FROM CLClientes
SELECT * FROM CLEstablecimientos
SELECT * FROM CLCamarers

SELECT * FROM CLPedidos

SELECT * FROM CLPedidosVinos
SELECT * FROM CLPedidosComplementos
SELECT * FROM CLPedidosPlatos

/*delete from CLClientes
where ID = 101*/

--CLPedidos
SELECT GETDATE() AS [Fecha], c.ID AS [IDCliente], e.ID AS [IDEstablecimiento], cm.ID AS [IDCamarers]
	FROM CLClientes AS c
	CROSS JOIN CLCamarers AS cm
	CROSS JOIN CLEstablecimientos AS e
	WHERE c.Nombre = 'Armando' AND c.Apellidos = 'Bronca Segura' AND cm.Nombre = 'Margarita' AND e.Denominacion = 'Levante'

INSERT INTO CLPedidos(Fecha, IDCliente, IDEstablecimiento, IDCamarer)
	SELECT GETDATE() AS [Fecha], c.ID AS [IDCliente], e.ID AS [IDEstablecimiento], cm.ID AS [IDCamarers]
		FROM CLClientes AS c
		CROSS JOIN CLCamarers AS cm
		CROSS JOIN CLEstablecimientos AS e
		WHERE c.Nombre = 'Armando' AND c.Apellidos = 'Bronca Segura' AND cm.Nombre = 'Margarita' AND e.Denominacion = 'Levante'

--CLPedidosVinos
SELECT p.ID AS [IDPedido], v.ID AS [IDVino], 2 AS [Cantidad]
	FROM CLVinos AS v
	CROSS JOIN CLPedidos AS p
	WHERE v.Nombre = 'Tio Robustiano'

INSERT INTO CLPedidosVinos(IDPedido, IDVino, Cantidad)
	SELECT p.ID AS [IDPedido], v.ID AS [IDVino], 2 AS [Cantidad]
		FROM CLVinos AS v
		CROSS JOIN CLPedidos AS p
		WHERE v.Nombre = 'Tio Robustiano' AND p.ID = 1

--CLPedidosComplementos
SELECT p.ID AS [IDPedido], c.ID AS [IDComplementos], 2 AS [Cantidad]
	FROM CLComplementos AS c
	CROSS JOIN CLPedidos AS p
	WHERE c.Complemento = 'Nestea' AND p.ID = 1

SELECT p.ID AS [IDPedido], c.ID AS [IDComplementos], 1 AS [Cantidad]
	FROM CLComplementos AS c
	CROSS JOIN CLPedidos AS p
	WHERE c.Complemento = 'Botella agua' AND p.ID = 1

INSERT INTO CLPedidosComplementos(IDPedido, IDComplemento, Cantidad)
	SELECT p.ID AS [IDPedido], c.ID AS [IDComplemento], 2 AS [Cantidad]
		FROM CLComplementos AS c
		CROSS JOIN CLPedidos AS p
		WHERE c.Complemento = 'Nestea' AND p.ID = 1

INSERT INTO CLPedidosComplementos(IDPedido, IDComplemento, Cantidad)
	SELECT p.ID AS [IDPedido], c.ID AS [IDComplemento], 1 AS [Cantidad]
		FROM CLComplementos AS c
		CROSS JOIN CLPedidos AS p
		WHERE c.Complemento = 'Botella agua' AND p.ID = 1

--CLPedidosPlatos
SELECT pe.ID AS [IDPedido], pla.ID AS [IDPlato], 1 AS [CantidadRaciones]
	FROM CLPlatos AS pla
	CROSS JOIN CLPedidos AS pe
	WHERE pla.Nombre = 'Pulpo a la gallega' AND pe.ID = 1

SELECT pe.ID AS [IDPedido], pla.ID AS [IDPlato], 2 AS [CantidadMedias]
	FROM CLPlatos AS pla
	CROSS JOIN CLPedidos AS pe
	WHERE pla.Nombre = 'Choco Frito' AND pe.ID = 1

SELECT pe.ID AS [IDPedido], pla.ID AS [IDPlato], 3 AS [CantidadTapas]
	FROM CLPlatos AS pla
	CROSS JOIN CLPedidos AS pe
	WHERE pla.Nombre = 'Zanahoria ali�ada' AND pe.ID = 1

begin transaction 
INSERT INTO CLPedidosPlatos(IDPedido, IDPlato, CantidadRaciones)
	SELECT pe.ID AS [IDPedido], pla.ID AS [IDPlato], 1 AS [CantidadRaciones]
		FROM CLPlatos AS pla
		CROSS JOIN CLPedidos AS pe
		WHERE pla.Nombre = 'Pulpo a la gallega' AND pe.ID = 1
rollback
commit

begin transaction 
INSERT INTO CLPedidosPlatos(IDPedido, IDPlato, CantidadMedias)
	SELECT pe.ID AS [IDPedido], pla.ID AS [IDPlato], 2 AS [CantidadMedias]
		FROM CLPlatos AS pla
		CROSS JOIN CLPedidos AS pe
		WHERE pla.Nombre = 'Choco Frito' AND pe.ID = 1
rollback
commit

begin transaction 
INSERT INTO CLPedidosPlatos(IDPedido, IDPlato, CantidadTapas)
	SELECT pe.ID AS [IDPedido], pla.ID AS [IDPlato], 3 AS [CantidadTapas]
		FROM CLPlatos AS pla
		CROSS JOIN CLPedidos AS pe
		WHERE pla.Nombre = 'Zanahoria ali�ada' AND pe.ID = 1
rollback
commit

SELECT (v.PVP * pv.Cantidad)
	FROM CLVinos AS v
	INNER JOIN CLPedidosVinos AS pv
	ON v.ID = pv.IDVino

--Actializando el Importe de Pedidos
BEGIN TRANSACTION
UPDATE CLPedidos
	SET Importe = ISNULL(Importe,0) + (SELECT (v.PVP * pv.Cantidad)
						FROM CLVinos AS v
						INNER JOIN CLPedidosVinos AS pv
						ON v.ID = pv.IDVino)
	WHERE ID = 1
ROLLBACK
COMMIT

SELECT (c.Importe * pc.Cantidad)
	FROM CLComplementos AS c
	INNER JOIN CLPedidosComplementos AS pc
	ON c.ID = pc.IDComplemento
	WHERE IDComplemento = 4

BEGIN TRANSACTION
UPDATE CLPedidos
	SET Importe = ISNULL(Importe,0) + (SELECT (c.Importe * pc.Cantidad)
											FROM CLComplementos AS c
											INNER JOIN CLPedidosComplementos AS pc
											ON c.ID = pc.IDComplemento
											WHERE IDComplemento = 4)
										WHERE ID = 1
ROLLBACK
COMMIT

SELECT (c.Importe * pc.Cantidad)
	FROM CLComplementos AS c
	INNER JOIN CLPedidosComplementos AS pc
	ON c.ID = pc.IDComplemento
	WHERE IDComplemento = 7

BEGIN TRANSACTION
UPDATE CLPedidos
	SET Importe = ISNULL(Importe,0) + (SELECT (c.Importe * pc.Cantidad)
											FROM CLComplementos AS c
											INNER JOIN CLPedidosComplementos AS pc
											ON c.ID = pc.IDComplemento
											WHERE IDComplemento = 7)
										WHERE ID = 1
ROLLBACK
COMMIT

SELECT ((pla.PVPTapaRecomendado * pepla.CantidadTapas)+
		(pla.PVPRacionRecomendado * pepla.CantidadRaciones)+
		(pla.PVPMediaRecomendado * pepla.CantidadMedias))
	FROM CLPlatos AS pla
	INNER JOIN CLPedidosPlatos AS pepla
	ON pla.ID = pepla.IDPlato
	WHERE pla.Nombre = 'Pulpo a la gallega'

BEGIN TRANSACTION
UPDATE CLPedidos
	SET Importe = ISNULL(Importe,0) + (SELECT ( (pla.PVPTapaRecomendado * pepla.CantidadTapas)+
												(pla.PVPRacionRecomendado * pepla.CantidadRaciones)+
												(pla.PVPMediaRecomendado * pepla.CantidadMedias))
											FROM CLPlatos AS pla
											INNER JOIN CLPedidosPlatos AS pepla
											ON pla.ID = pepla.IDPlato
											WHERE pla.Nombre = 'Pulpo a la gallega')
										WHERE ID = 1
ROLLBACK
COMMIT

SELECT ((pla.PVPTapaRecomendado * pepla.CantidadTapas)+
		(pla.PVPRacionRecomendado * pepla.CantidadRaciones)+
		(pla.PVPMediaRecomendado * pepla.CantidadMedias))
	FROM CLPlatos AS pla
	INNER JOIN CLPedidosPlatos AS pepla
	ON pla.ID = pepla.IDPlato
	WHERE pla.Nombre = 'Choco Frito'

BEGIN TRANSACTION
UPDATE CLPedidos
	SET Importe = ISNULL(Importe,0) + (SELECT ( (pla.PVPTapaRecomendado * pepla.CantidadTapas)+
												(pla.PVPRacionRecomendado * pepla.CantidadRaciones)+
												(pla.PVPMediaRecomendado * pepla.CantidadMedias))
											FROM CLPlatos AS pla
											INNER JOIN CLPedidosPlatos AS pepla
											ON pla.ID = pepla.IDPlato
											WHERE pla.Nombre = 'Choco Frito')
										WHERE ID = 1
ROLLBACK
COMMIT

SELECT ((pla.PVPTapaRecomendado * pepla.CantidadTapas)+
		(pla.PVPRacionRecomendado * pepla.CantidadRaciones)+
		(pla.PVPMediaRecomendado * pepla.CantidadMedias))
	FROM CLPlatos AS pla
	INNER JOIN CLPedidosPlatos AS pepla
	ON pla.ID = pepla.IDPlato
	WHERE pla.Nombre = 'Zanahoria ali�ada'

BEGIN TRANSACTION
UPDATE CLPedidos
	SET Importe = ISNULL(Importe,0) + (SELECT ( (pla.PVPTapaRecomendado * pepla.CantidadTapas)+
												(pla.PVPRacionRecomendado * pepla.CantidadRaciones)+
												(pla.PVPMediaRecomendado * pepla.CantidadMedias))
											FROM CLPlatos AS pla
											INNER JOIN CLPedidosPlatos AS pepla
											ON pla.ID = pepla.IDPlato
											WHERE pla.Nombre = 'Zanahoria ali�ada')
										WHERE ID = 1
ROLLBACK
COMMIT