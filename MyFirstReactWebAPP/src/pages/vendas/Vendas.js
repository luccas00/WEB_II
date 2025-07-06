import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function Usuarios() {
  const [vendas, setVendas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchVendas();
  }, []);

  const fetchVendas = () => {
    fetch('http://localhost:4000/sales')
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao buscar vendas');
        return res.json();
      })
      .then((data) => {
        setVendas(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  };

  const handleDelete = (id) => {
    if (!window.confirm('Confirma exclusão desta venda?')) return;

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({ id });

    const requestOptions = {
      method: "DELETE",
      headers: myHeaders,
      body: raw,
      redirect: "follow"
    };

    fetch("http://localhost:4000/sales/remove", requestOptions)
      .then((response) => {
        if (!response.ok) throw new Error('Erro ao deletar venda');
        return response.text();
      })
      .then(() => {
        setVendas((prev) => prev.filter((user) => user.id !== id));
      })
      .catch((error) => alert(error.message));
  };

  if (loading) return <p>Carregando vendas...</p>;
  if (error) return <p>Erro: {error}</p>;

  return (
    <div>
      <h1 className="text-center mb-4">Vendas</h1>

      <div className="d-flex justify-content-end mb-3">
        <Link
          to="/vendas/new"
          className="btn btn-success"
          style={{ width: '5cm' }}
        >
          Nova Venda
        </Link>
      </div>

      <table className="table table-striped table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Proprietario</th>
            <th>Evento</th>
            <th>Data da Compra</th>
            <th>Status da Compra</th>
            <th className="text-center" style={{ width: '200px' }}>Ações</th>
          </tr>
        </thead>
        <tbody>
          {vendas.map((venda) => (
            <tr key={venda.id}>
              <td>{venda.userName}</td>
              <td>{venda.eventName}</td>
              <td>{venda.purchaseDate}</td>
              <td>{venda.purchaseStatus}</td>
              <td className="text-center">
                <div className="d-flex justify-content-center gap-3">
                  <Link
                    to={`/vendas/${venda.id}`}
                    className="btn btn-info btn-sm"
                  >
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
