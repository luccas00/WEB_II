import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

import { API_BASE_URL } from '../Config';

export default function Usuarios() {
  const [usuarios, setUsuarios] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchUsuarios();
  }, []);

  const fetchUsuarios = () => {
    fetch(`${API_BASE_URL}/users`)
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao buscar usuários');
        return res.json();
      })
      .then((data) => {
        setUsuarios(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  };

  const handleDelete = (id) => {
    if (!window.confirm('Confirma exclusão deste usuário?')) return;

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({ id });

    const requestOptions = {
      method: "DELETE",
      headers: myHeaders,
      body: raw,
      redirect: "follow"
    };

    fetch(`${API_BASE_URL}/users/remove`, requestOptions)
      .then((response) => {
        if (!response.ok) throw new Error('Erro ao deletar usuário');
        return response.text();
      })
      .then(() => {
        setUsuarios((prev) => prev.filter((user) => user.id !== id));
      })
      .catch((error) => alert(error.message));
  };

  if (loading) return <p>Carregando usuários...</p>;
  if (error) return <p>Erro: {error}</p>;

  return (
    <div>
      <h1 className="text-center mb-4">Usuários</h1>

      <div className="d-flex justify-content-end mb-3">
        <Link
          to="/usuarios/new"
          className="btn btn-success"
          style={{ width: '5cm' }}
        >
          Novo Usuário
        </Link>
      </div>

      <table className="table table-striped table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>CPF</th>
            <th className="text-center" style={{ width: '200px' }}>Ações</th>
          </tr>
        </thead>
        <tbody>
          {usuarios.map((user) => (
            <tr key={user.id}>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>{user.cpf}</td>
              <td className="text-center">
                <div className="d-flex justify-content-center gap-3">
                  <Link
                    to={`/usuarios/${user.id}`}
                    className="btn btn-info btn-sm"
                  >
                    Detalhes
                  </Link>
                  <button
                    onClick={() => handleDelete(user.id)}
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
