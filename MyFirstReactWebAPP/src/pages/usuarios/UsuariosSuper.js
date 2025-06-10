import React, { useEffect, useState } from 'react';

export default function UsuariosSuper() {
  const [usuarios, setUsuarios] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [usuarioSelecionado, setUsuarioSelecionado] = useState(null);

  useEffect(() => {
    fetch('http://localhost:9030/usuarios/super', {
      method: 'GET', // Ajuste para GET se não precisar enviar corpo
      headers: { 'Content-Type': 'application/json' },
    })
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

  function handleDetalhes(user) {
    setUsuarioSelecionado(user);
  }

  return (
    <div>
      <h1 className="text-center mb-4">Usuários Super</h1>
      <div className="table-responsive">
        <table className="table table-striped table-bordered">
          <thead className="table-dark">
            <tr>
              <th>Nome</th>
              <th>Email</th>
              <th>CPF</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {usuarios.map((user) => (
              <tr key={user.id}>
                <td>{user.fullName}</td>
                <td>{user.email}</td>
                <td>{user.cpf}</td>
                <td>
                  <button
                    className="btn btn-sm btn-primary"
                    onClick={() => handleDetalhes(user)}
                  >
                    Detalhes
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {usuarioSelecionado && (
        <div className="card mt-4 p-3">
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h3 className="m-0">Detalhes do Usuário</h3>
                <button
                    className="btn btn-secondary"
                    onClick={() => setUsuarioSelecionado(null)}
                >
                    Fechar Detalhes
                </button>
            </div>

            <form>
                {Object.entries(usuarioSelecionado).map(([key, value]) => (
                <div className="mb-3" key={key}>
                    <label className="form-label text-capitalize">{key}</label>
                    <input
                    type="text"
                    className="form-control"
                    readOnly
                    value={
                        (key === 'createdAt' || key === 'updatedAt') && value
                        ? new Date(value).toLocaleString()
                        : value
                    }
                    />
                </div>
                ))}
            </form>
          
        </div>
      )}
    </div>
  );
}
