import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { API_BASE_URL } from '../Config';

const STATUS_OPTIONS = [
  { value: 'EM_ABERTO', label: 'Em Aberto' },
  { value: 'CONCLUIDA', label: 'Concluída' },
  { value: 'CANCELADA', label: 'Cancelada' },
];

// Converte "YYYY-MM-DDTHH:mm" -> "YYYY-MM-DDTHH:mm:ss" (LocalDateTime, sem Z)
function toLocalDateTime(value) {
  if (!value) return null;
  return value.length === 16 ? `${value}:00` : value; // já vem sem timezone do input
}

export default function VendasCreate() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    user_id: '',
    event_id: '',
    purchaseDate: '',          // datetime-local
    purchaseStatus: 'EM_ABERTO'
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      user_id: form.user_id || null,
      event_id: form.event_id || null,
      purchaseDate: toLocalDateTime(form.purchaseDate),
      purchaseStatus: form.purchaseStatus || 'EM_ABERTO',
    };

    try {
      const res = await fetch(`${API_BASE_URL}/sales`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || 'Erro ao criar venda');
      }
      await res.json();
      navigate('/vendas');
    } catch (err) {
      alert(err.message);
    }
  };

  return (
    <div>
      <h1>Criar Venda</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Usuário (UUID)</label>
          <input
            name="user_id"
            className="form-control"
            value={form.user_id}
            onChange={handleChange}
            placeholder="ex: 550e8400-e29b-41d4-a716-446655440000"
            required
          />
        </div>

        <div className="mb-3">
          <label>Evento (UUID)</label>
          <input
            name="event_id"
            className="form-control"
            value={form.event_id}
            onChange={handleChange}
            placeholder="ex: 7d9b7a2f-8d9b-4a7c-9b2e-1a2b3c4d5e6f"
            required
          />
        </div>

        <div className="mb-3">
          <label>Data da Compra</label>
          <input
            name="purchaseDate"
            type="datetime-local"
            className="form-control"
            value={form.purchaseDate}
            onChange={handleChange}
          />
          {/* Observação: backend pode sobrescrever para agora; deixe em branco se quiser usar o default do servidor */}
        </div>

        <div className="mb-3">
          <label>Status da Compra</label>
          <select
            name="purchaseStatus"
            className="form-control"
            value={form.purchaseStatus}
            onChange={handleChange}
          >
            {STATUS_OPTIONS.map(s => (
              <option key={s.value} value={s.value}>{s.label}</option>
            ))}
          </select>
          {/* Se o serviço fixar EM_ABERTO no create, este campo será ignorado pelo backend. */}
        </div>

        <button type="submit" className="btn btn-primary">Criar</button>
        <button type="button" className="btn btn-secondary ms-2" onClick={() => navigate('/vendas')}>
          Cancelar
        </button>
      </form>
    </div>
  );
}
