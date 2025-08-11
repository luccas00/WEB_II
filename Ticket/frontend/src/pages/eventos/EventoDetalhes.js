import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';

import { API_BASE_URL } from '../Config';

export default function EventoDetalhes() {
  const { id } = useParams();
  const [evento, setEvento] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`${API_BASE_URL}/events/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error('Evento não encontrado');
        return res.json();
      })
      .then((data) => setEvento(data))
      .catch((err) => setError(err.message));
  }, [id]);

  if (error) return <p>Erro: {error}</p>;
  if (!evento) return <p>Carregando...</p>;

  return (
    <div>
      <h1>Detalhes do Evento</h1>
      <p><strong>Nome:</strong> {evento.name}</p>
      <p><strong>Descrição:</strong> {evento.description}</p>
      <p><strong>Tipo:</strong> {evento.type}</p>
      <p><strong>Data:</strong> {new Date(evento.date).toLocaleString()}</p>
      <p><strong>Início das Vendas:</strong> {new Date(evento.startSales).toLocaleString()}</p>
      <p><strong>Fim das Vendas:</strong> {new Date(evento.endSales).toLocaleString()}</p>
      <p><strong>Preço:</strong> R$ {evento.price.toFixed(2)}</p>
      <Link to="/eventos" className="btn btn-secondary">Voltar</Link>
    </div>
  );
}
