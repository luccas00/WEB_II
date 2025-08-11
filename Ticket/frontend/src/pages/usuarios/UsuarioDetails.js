import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';

import { API_BASE_URL } from '../Config';

export default function UsuarioDetails() {
  const { id } = useParams();
  const [usuario, setUsuario] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`${API_BASE_URL}/users/${id}`)
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao buscar usuário');
        return res.json();
      })
      .then((data) => {
        setUsuario(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p>Carregando detalhes...</p>;
  if (error) return <p>Erro: {error}</p>;
  if (!usuario) return <p>Nenhum dado encontrado.</p>;

  return (
    <div>
      <h1 className="mb-4">Detalhes do Usuário</h1>

      <p><strong>Nome:</strong> {usuario.name}</p>
      <p><strong>Email:</strong> {usuario.email}</p>
      <p><strong>CPF:</strong> {usuario.cpf}</p>
      <p><strong>Telefone:</strong> {usuario.phone}</p>
      <p><strong>Data de Nascimento:</strong> {usuario.dateOfBirth}</p>
      <p><strong>Status:</strong> {usuario.status}</p>
      <p><strong>Tipo:</strong> {usuario.userType}</p>
      <p><strong>Data Criação:</strong> {usuario.createdAt}</p>
      <p><strong>Data Atualização:</strong> {usuario.updatedAt}</p>

      <h3>Cartões de Crédito</h3>
      <ul>
        {usuario.creditCards && usuario.creditCards.map((cc) => (
          <li key={cc.id}>
            {cc.owner} - {cc.creditCardNetwork} - {cc.creditCardNumber} - {cc.expiryDate}
          </li>
        ))}
      </ul>

      <h3>Endereços</h3>
      <ul>
        {usuario.addresses && usuario.addresses.map((addr) => (
          <li key={addr.id}>
            {addr.street}, {addr.neighborhood}, {addr.city} - {addr.state}
          </li>
        ))}
      </ul>

      <Link to="/usuarios" className="btn btn-secondary mt-3">Voltar</Link>
    </div>
  );
}
