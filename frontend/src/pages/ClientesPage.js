import React, { useState, useEffect } from 'react';
import ClienteCard from '../components/ClientCard';
import ClienteForm from '../components/ClienteForm'; // Criaremos este
import Modal from '../components/Modal'; // Componente Modal genérico
import { getAllClientes, deleteCliente } from './clienteAPI';
import './ClientesPage.css'; // CSS específico da página
import '../App.css'; // Para estilos de .button, .page-header, etc.

// Ícone de Adicionar (exemplo)
const AddIcon = () => <span>➕</span>;

function ClientesPage() {
  const [clientes, setClientes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [clienteAtual, setClienteAtual] = useState(null);

  const fetchClientes = async () => {
    // ... (mesma lógica de fetch da versão MUI) ...
    try {
      console.log(process.env)
      setLoading(true);
      const response = await getAllClientes();
      setClientes(response.data || []);
      setError(null);
    } catch (err) {
      setError('Falha ao carregar clientes. ' + (err.response?.data?.message || err.message));
      setClientes([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchClientes();
  }, []);

  const handleOpenModal = (cliente = null) => {
    setClienteAtual(cliente);
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setClienteAtual(null);
  };

  const handleSave = () => {
    fetchClientes();
    handleCloseModal();
  };

  const handleDelete = async (id) => {
    // ... (mesma lógica de delete da versão MUI) ...
    if (window.confirm('Tem certeza que deseja excluir este cliente?')) {
      try {
        await deleteCliente(id);
        fetchClientes();
      } catch (err) {
        setError('Falha ao excluir cliente. ' + (err.response?.data?.message || err.message));
      }
    }
  };

  if (loading) {
    return <div className="loading-indicator">Carregando clientes...</div>;
  }

  return (
    <div className="container clientes-page-container">
      <div className="page-header">
        <h1>Gerenciamento de Clientes</h1>
        <button className="button button-primary" onClick={() => handleOpenModal()}>
          <AddIcon /> Novo Cliente
        </button>
      </div>

      {error && <div className="error-message">{error}</div>}
      
      {clientes.length === 0 && !loading && !error && (
        <div className="no-data-message">Nenhum cliente encontrado.</div>
      )}

      <div className="cards-grid">
        {clientes.map((cliente) => (
          <ClienteCard
            key={cliente.id}
            cliente={cliente}
            onEdit={() => handleOpenModal(cliente)}
            onDelete={handleDelete}
          />
        ))}
      </div>

      {isModalOpen && (
        <Modal title={clienteAtual ? 'Editar Cliente' : 'Novo Cliente'} onClose={handleCloseModal}>
          <ClienteForm clienteToEdit={clienteAtual} onSave={handleSave} onCancel={handleCloseModal} />
        </Modal>
      )}
    </div>
  );
}

export default ClientesPage;