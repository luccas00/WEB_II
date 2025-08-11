import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { API_BASE_URL } from '../Config';

const STATUS_OPTIONS = [
  { value: 'EM_ABERTO', label: 'Em Aberto' },
  { value: 'PAGO', label: 'Pago' },
  { value: 'CANCELADO', label: 'Cancelado' },
  { value: 'ESTORNADO', label: 'Estornado' },
];

export default function VendasAtualizarStatus() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [currentStatus, setCurrentStatus] = useState('EM_ABERTO');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Pré-carrega status atual para setar o default do select
    fetch(`${API_BASE_URL}/sales/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error('Venda não encontrada');
        return res.json();
      })
      .then((data) => setCurrentStatus(data.purchaseStatus || 'EM_ABERTO'))
      .catch(() => {}) // silencioso
      .finally(() => setLoading(false));
  }, [id]);

  const [status, setStatus] = useState('EM_ABERTO');
  useEffect(() => { setStatus(currentStatus); }, [currentStatus]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = { id, purchaseStatus: status };

    try {
      const res = await fetch(`${API_BASE_URL}/sales/update/status`, {
        method: 'PUT', // ajuste para PUT se sua API exigir
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });
      if (!res.ok) {
        const msg = await res.text();
        throw new Error(msg || 'Falha ao atualizar status');
      }
      await res.text();
      navigate(`/vendas/${id}`);
    } catch (err) {
      alert(err.message);
    }
  };

  if (loading) return <p>Carregando...</p>;

  return (
    <div>
      <h1>Atualizar Status da Venda</h1>
      <p><strong>Venda:</strong> {id}</p>

      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Novo Status</label>
          <select
            name="purchaseStatus"
            className="form-control"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
            required
          >
            {STATUS_OPTIONS.map(s => (
              <option key={s.value} value={s.value}>{s.label}</option>
            ))}
          </select>
        </div>

        <button type="submit" className="btn btn-primary">Atualizar</button>
        <Link to={`/vendas/${id}`} className="btn btn-secondary ms-2">Cancelar</Link>
      </form>
    </div>
  );
}
