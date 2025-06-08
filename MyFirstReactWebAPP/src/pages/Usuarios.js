import React, { useEffect, useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';

import UsuariosSuper from './UsuariosSuper';



export default function Usuarios() {
  const [usuarios, setUsuarios] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:3030/usuarios')
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
  }, []);

  if (loading) return <p>Carregando usuários...</p>;
  if (error) return <p>Erro: {error}</p>;

  return (
    <div>
      <h1 className="text-center mb-4">Usuários</h1>
      <div className="navbar-nav mb-3">
        <Link
          to="/usuarios/super"
          className="btn btn-primary"
          style={{ width: '5cm', display: 'inline-block' }}
        >
          Usuários Super
        </Link>
      </div>

      <table className="table table-striped table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>CPF</th>
          </tr>
        </thead>
        <tbody>
          {usuarios.map((user) => (
            <tr key={user.id}>
              <td>{user.firstName} {user.lastName}</td>
              <td>{user.email}</td>
              <td>{user.CPF}</td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );


}
