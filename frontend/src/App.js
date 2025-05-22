// frontend/src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import ClientesPage from './pages/ClientesPage';
import MedicosPage from './pages/MedicosPage'; // Precisa ser criado
//import ConsultasPage from './pages/ConsultasPage'; // Precisa ser criado
import './App.css'; // Estilos da Navbar e globais

function App() {
  return (
    <Router>
      <header className="app-navbar">
        <div className="logo">Clínica App</div>
        <nav>
          <Link to="/clientes">Clientes</Link>
          <Link to="/medicos">Médicos</Link>
        </nav>
      </header>
      <main>
        {/* O container pode ser aplicado aqui ou dentro de cada página */}
        <Routes>
          <Route path="/" element={
            <div className="container" style={{textAlign: 'center', marginTop: '50px'}}>
                <h1>Bem-vindo à Clínica App</h1>
                <p>Selecione uma opção no menu acima.</p>
            </div>
          } />
          <Route path="/clientes" element={<ClientesPage />} />
          <Route path="/medicos" element={<MedicosPage />} />
        </Routes>
      </main>
    </Router>
  );
}

export default App;