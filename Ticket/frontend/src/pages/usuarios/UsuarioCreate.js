import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { API_BASE_URL } from '../Config';

export default function UsuarioCreate() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    name: '',
    userType: 'CUSTOMER',
    status: 'PENDING',
    email: '',
    password: '',
    city: '',
    cpf: '',
    cep: '',
    phone: '',
    dateOfBirth: ''
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${API_BASE_URL}/users`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(form)
      });

      if (!response.ok) throw new Error('Erro ao criar usuário');

      alert('Usuário criado com sucesso');
      navigate('/usuarios');
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="container mt-4">
      <h1>Novo Usuário</h1>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Nome</label>
          <input
            type="text"
            name="name"
            className="form-control"
            value={form.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Email</label>
          <input
            type="email"
            name="email"
            className="form-control"
            value={form.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Senha</label>
          <input
            type="password"
            name="password"
            className="form-control"
            value={form.password}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>CEP</label>
          <input
            type="text"
            name="cep"
            className="form-control"
            value={form.cep}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>CPF</label>
          <input
            type="text"
            name="cpf"
            className="form-control"
            value={form.cpf}
            onChange={handleChange}
            required
          />
        </div>

        <div className="mb-3">
          <label>Telefone</label>
          <input
            type="text"
            name="phone"
            className="form-control"
            value={form.phone}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <label>Data de Nascimento</label>
          <input
            type="datetime-local"
            name="dateOfBirth"
            className="form-control"
            value={form.dateOfBirth}
            onChange={handleChange}
          />
        </div>

        <button type="submit" className="btn btn-success">
          Criar Usuário
        </button>
      </form>
    </div>
  );
}
