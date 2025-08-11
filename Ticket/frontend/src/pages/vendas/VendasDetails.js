import React, { useEffect, useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import { API_BASE_URL } from '../Config';

const STATUS_LABEL = {
  EM_ABERTO: 'Em Aberto',
  PAGO: 'Pago',
  CANCELADO: 'Cancelado',
  ESTORNADO: 'Estornado',
};

export default function VendasDetalhes() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [venda, setVenda] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`${API_BASE_URL}/sales/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error('Venda não encontrada');
        return res.json();
      })
      .then((data) => setVenda(data))
      .catch((err) => setError(err.message));
  }, [id]);

  if (error) return <p>Erro: {error}</p>;
  if (!venda) return <p>Carregando...</p>;

  const event = venda.event || {};
  const formatDate = (d) => (d ? new Date(d).toLocaleString() : '-');
  const formatPrice = (p) =>
    typeof p === 'number' ? `R$ ${p.toFixed(2)}` : (p ? `R$ ${Number(p).toFixed(2)}` : '-');

  return (
    <div>
      <h1>Detalhes da Venda</h1>

      <p><strong>ID da Venda:</strong> {venda.id}</p>
      <p><strong>ID do Usuário:</strong> {venda.user}</p>
      <p><strong>Status da Compra:</strong> {STATUS_LABEL[venda.purchaseStatus] || venda.purchaseStatus}</p>
      <p><strong>Data da Compra:</strong> {formatDate(venda.purchaseDate)}</p>

      <div className="mt-3">
        <button
          className="btn btn-primary me-2"
          onClick={() => navigate(`/vendas/${venda.id}/status`)}
        >
          Atualizar Status
        </button>
        <Link to="/vendas" className="btn btn-secondary">Voltar</Link>
        {event.id && (
          <Link to={`/eventos/${event.id}`} className="btn btn-outline-primary ms-2">
            Ver Evento
          </Link>
        )}
      </div>

      <hr />

      <h2>Evento</h2>
      <p><strong>Nome:</strong> {event.name || '-'}</p>
      <p><strong>Tipo:</strong> {event.type || '-'}</p>
      <p><strong>Data:</strong> {formatDate(event.date)}</p>
      <p><strong>Início das Vendas:</strong> {formatDate(event.startSales)}</p>
      <p><strong>Fim das Vendas:</strong> {formatDate(event.endSales)}</p>
      <p><strong>Preço:</strong> {formatPrice(event.price)}</p>
    </div>
  );
}
