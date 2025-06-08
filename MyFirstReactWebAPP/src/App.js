import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import Usuarios from './pages/Usuarios';
import About from './pages/About';
import Contact from './pages/Contact';
import UsuariosSuper from './pages/UsuariosSuper';


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
          <div className="collapse navbar-collapse justify-content-between">
            <div className="navbar-nav">
              <Link className="nav-link" to="/usuarios">Usuários</Link>
              <Link className="nav-link" to="/about">About</Link>
              <Link className="nav-link" to="/contact">Contact</Link>
            </div>
          </div>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/usuarios" element={<Usuarios />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/usuarios/super" element={<UsuariosSuper />} />
      </Routes>
    </div>
  );
}

export default App;
