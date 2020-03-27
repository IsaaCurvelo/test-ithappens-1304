
-- Escrever uma consulta que retorne todos os produtos com quantidade maior ou igual a 10
select produto.* from produto join estoque on codigo = produto_codigo where quantidade >= 100

-- Escrever uma consulta que traga todos os produtos que têm estoque para a filial de código 60
select produto.* from produto join estoque on codigo = produto_codigo where filial_codigo = 60 and quantidade > 0

-- Escrever consulta que liste todos os campos para o domínio PedidoEstoque e ItensPedido filtrando apenas o produto de código 7993
select * from pedido_estoque pe join item_pedido ip on pe.id = ip.pedido_estoque_id where ip.produto_codigo = 7993

-- Escrever uma consulta que liste os pedidos com suas respectivas formas de pagamento.
select * from pedido_estoque where entrada is false

-- Escrever um consulta para sumarizar e bater os valores da capa do pedido com os valores dos ítens de pedido 
select pe.id, pe.valor valor_pedido, t.valor valor_itens from pedido_estoque pe
join (select pedido_estoque_id, sum(`quantidade` * valor_unitario) valor from item_pedido group by pedido_estoque_id) t on t.pedido_estoque_id = pe.id
where pe.entrada is false;

-- Escrever uma consulta para sumarizar o total dos itens por pedido e que filtre apenas os pedidos no qual a soma total da quantidade de ítens de pedido seja maior que 10
select pe.*, t.qtd quantidade_total_itens from pedido_estoque pe
join (select pedido_estoque_id, sum(quantidade) qtd from item_pedido group by pedido_estoque_id) t on t.pedido_estoque_id = pe.id
where t.qtd > 10;

