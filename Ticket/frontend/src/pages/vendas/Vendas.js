import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { API_BASE_URL } from '../Config';

const STATUS_LABEL = {
  EM_ABERTO: 'Em Aberto',
  CONCLUIDA: 'Concluída',
  CANCELADA: 'Cancelada',
};

export default function Vendas() {
  const [vendas, setVendas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => { fetchVendas(); }, []);

  const fetchVendas = () => {
    setLoading(true);
    fetch(`${API_BASE_URL}/sales`)
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao buscar vendas');
        return res.json();
      })
      .then((data) => {
        // Suporta tanto array puro quanto Page<SalesRecordDTO>
        const list = Array.isArray(data) ? data : (data?.content ?? []);
        setVendas(list);
      })
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  };

  const handleDelete = (id) => {
    if (!window.confirm('Confirma exclusão desta venda?')) return;

    fetch(`${API_BASE_URL}/sales/remove`, {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id }),
    })
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao deletar venda');
        return res.text();
      })
      .then(() => setVendas((prev) => prev.filter((v) => v.id !== id)))
      .catch((err) => alert(err.message));
  };

  const fmtDate = (d) => (d ? new Date(d).toLocaleString() : '-');

  if (loading) return <p>Carregando vendas...</p>;
  if (error) return <p>Erro: {error}</p>;

  return (
    <div>
      <h1 className="text-center mb-4">Vendas</h1>

      <div className="d-flex justify-content-end mb-3">
        <Link to="/vendas/new" className="btn btn-success" style={{ width: '5cm' }}>
          Nova Venda
        </Link>
      </div>

      <table className="table table-striped table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Proprietário (UUID)</th>
            <th>Evento</th>
            <th>Data da Compra</th>
            <th>Status da Compra</th>
            <th className="text-center" style={{ width: '200px' }}>Ações</th>
          </tr>
        </thead>
        <tbody>
          {vendas.map((venda) => (
            <tr key={venda.id}>
              {/* Backend: SalesRecordDTO.user = UUID */}
              <td>{venda.user ?? venda.user_id ?? '-'}</td>

              {/* Backend: SalesRecordDTO.event = objeto; exibir nome */}
              <td>{venda.event?.name ?? venda.eventName ?? '-'}</td>

              <td>{fmtDate(venda.purchaseDate)}</td>
              <td>{STATUS_LABEL[venda.purchaseStatus] ?? venda.purchaseStatus ?? '-'}</td>

              <td className="text-center">
                <div className="d-flex justify-content-center gap-3">
                  <Link to={`/vendas/${venda.id}`} className="btn btn-info btn-sm">
                    Detalhes
                  </Link>
                  <button
                    onClick={() => handleDelete(venda.id)}
                    className="btn btn-danger btn-sm"
                  >
                    Excluir
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
