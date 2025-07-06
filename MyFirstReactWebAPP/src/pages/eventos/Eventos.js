import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function Eventos() {
  const [eventos, setEventos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  

  useEffect(() => {
    fetch('http://localhost:4000/events')
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao buscar eventos');
        return res.json();
      })
      .then((data) => {
        setEventos(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  const handleDelete = (id) => {
    if (!window.confirm('Confirma exclusão deste evento?')) return;

    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({ id });

    const requestOptions = {
      method: "DELETE",
      headers: myHeaders,
      body: raw,
      redirect: "follow"
    };

    fetch("http://localhost:4000/events/remove", requestOptions)
      .then((response) => {
        if (!response.ok) throw new Error('Erro ao deletar evento');
        return response.text();
      })
      .then(() => {
        setEventos((prev) => prev.filter((user) => user.id !== id));
      })
      .catch((error) => alert(error.message));
  };

  if (loading) return <p>Carregando eventos...</p>;
  if (error) return <p>Erro: {error}</p>;

  return (
    <div>
      <h1 className="text-center mb-4">Eventos</h1>

      <div className="d-flex justify-content-end mb-3">
        <Link to="/eventos/new" className="btn btn-success">
          Novo Evento
        </Link>
      </div>

      <table className="table table-striped table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Tipo</th>
            <th>Data</th>
            <th>Preço</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {eventos.map((evento) => (
            <tr key={evento.id}>
              <td>{evento.name}</td>
              <td>{evento.description}</td>
              <td>{evento.type}</td>
              <td>{new Date(evento.date).toLocaleString()}</td>
              <td>R$ {evento.price.toFixed(2)}</td>
              <td className="text-center">
                <div className="d-flex justify-content-center gap-3">
                <Link
                  to={`/eventos/${evento.id}`}
                  className="btn btn-info btn-sm"
                >
                  Detalhes
                </Link>
                <button
                    onClick={() => handleDelete(evento.id)}
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
