import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { API_BASE_URL } from '../Config';

const EVENT_TYPES = [
  { value: 'PALESTRA', label: 'Palestra' },
  { value: 'SHOW', label: 'Show' },
  { value: 'TEATRO', label: 'Teatro' },
  { value: 'CINEMA', label: 'Cinema' },
  { value: 'CURSO', label: 'Curso' },
];

export default function EventoCreate() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: '',
    description: '',
    type: '',
    date: '',
    startSales: '',
    endSales: '',
    price: ''
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch(`${API_BASE_URL}/events`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao criar evento');
        return res.json();
      })
      .then(() => navigate('/eventos'))
      .catch((err) => alert(err.message));
  };

  return (
    <div>
      <h1>Criar Evento</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Nome</label>
          <input
            name="name"
            className="form-control"
            value={form.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Descrição</label>
          <textarea
            name="description"
            className="form-control"
            value={form.description}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>Tipo</label>
          <select
            name="type"
            className="form-control"
            value={form.type}
            onChange={handleChange}
            required
          >
            <option value="" disabled>Selecione…</option>
            {EVENT_TYPES.map(t => (
              <option key={t.value} value={t.value}>{t.label}</option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label>Data do Evento</label>
          <input
            name="date"
            type="datetime-local"
            className="form-control"
            value={form.date}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>Início das Vendas</label>
          <input
            name="startSales"
            type="datetime-local"
            className="form-control"
            value={form.startSales}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>Fim das Vendas</label>
          <input
            name="endSales"
            type="datetime-local"
            className="form-control"
            value={form.endSales}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>Preço</label>
          <input
            name="price"
            type="number"
            step="0.01"
            className="form-control"
            value={form.price}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="btn btn-primary">
          Criar
        </button>
      </form>
    </div>
  );
}
