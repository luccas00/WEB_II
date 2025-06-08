import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import Usuarios from './pages/Usuarios';
import About from './pages/About';
import Contact from './pages/Contact';
import UsuariosSuper from './pages/UsuariosSuper';

function App() {
  return (
    <div className="container mt-5">
      <nav className="navbar navbar-expand navbar-dark bg-dark mb-4">
        <Link className="navbar-brand" to="/">My React App</Link>
        <div className="navbar-nav">
          <Link className="nav-link" to="/usuarios">Usuários</Link>
          <Link className="nav-link" to="/about">About</Link>
          <Link className="nav-link" to="/contact">Contact</Link>
        </div>

      </nav>

      <Routes>
        <Route path="/usuarios" element={<Usuarios />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/usuarios/super" element={<UsuariosSuper />} />
      </Routes>

    </div>
  );
}

export default App;
