import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import Vendas from './pages/vendas/Vendas';

import UsuarioDetalhes from './pages/usuarios/UsuarioDetails';
import UsuarioCreate from './pages/usuarios/UsuarioCreate';

import Eventos from './pages/eventos/Eventos';
import EventoCreate from './pages/eventos/EventoCreate';
import EventoDetalhes from './pages/eventos/EventoDetalhes';

import Usuarios from './pages/usuarios/Usuarios';
import About from './pages/About';
import Contact from './pages/Contact';

function Home() {
  return (
    <div className="text-center">
      <h1 className="display-4">Bem-vindo ao My React App</h1>
      <p className="lead">Esta é a página inicial do seu projeto com React Router.</p>
      <p>Use a navegação acima para acessar outras páginas.</p>
      <div className="d-flex justify-content-center my-4">
        <a
          className="btn btn-dark"
          style={{ width: '50%' }}
          href="https://learn.microsoft.com/en-us/azure/static-web-apps/get-started-portal?tabs=react&pivots=github"
          target="_blank"
          rel="noopener noreferrer"
        >
          Quickstart: Build your first static web app
        </a>
      </div>
      <div className="d-flex justify-content-center my-4">
        <a
          className="btn btn-dark"
          style={{ width: '50%' }}
          href="https://github.com/luccas00/WEB_II"
          target="_blank"
          rel="noopener noreferrer"
        >
          GitHub Repository
        </a>
      </div>

    </div>
    
  );
}

function App() {
  return (
    <div className="container mt-5">

      <nav className="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">My React App</Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarNav">
            <div className="navbar-nav">
              <Link className="nav-link" to="/usuarios">Usuários</Link>
              <Link className="nav-link" to="/eventos">Eventos</Link>
              <Link className="nav-link" to="/vendas">Vendas</Link>
              <Link className="nav-link" to="/about">About</Link>
              <Link className="nav-link" to="/contact">Contact</Link>
            </div>
          </div>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/usuarios" element={<Usuarios />} />
        <Route path="/usuarios/:id" element={<UsuarioDetalhes />} />
        <Route path="/usuarios/new" element={<UsuarioCreate />} />
        <Route path="/vendas" element={<Vendas />} />
        <Route path="/vendas/new" element={<Vendas />} />
        <Route path="/vendas/:id" element={<Vendas />} />
        <Route path="/eventos" element={<Eventos />} />
        <Route path="/eventos/new" element={<EventoCreate />} />
        <Route path="/eventos/:id" element={<EventoDetalhes />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
    </div>
  );
}

export default App;
