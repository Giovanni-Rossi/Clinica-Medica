// frontend/src/pages/MedicosPage.js
import React, { useState, useEffect } from 'react';
import MedicoCard from '../components/MedicoCard';
import MedicoForm from '../components/MedicoForm';
import Modal from '../components/Modal';
import { getAllMedicos, deleteMedico } from './medicoAPI'; // API correta
import './MedicosPage.css'; // CSS específico da página (pode ser similar a ClientesPage.css)
import '../App.css'; // Para .button, .page-header, etc.

const AddIcon = () => <span>➕</span>;

function MedicosPage() {
  const [medicos, setMedicos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [medicoAtual, setMedicoAtual] = useState(null);

  const fetchMedicos = async () => {
    try {
      setLoading(true);
      const response = await getAllMedicos();
      setMedicos(response.data || []);
      setError(null);
    } catch (err) {
      setError('Falha ao carregar médicos. ' + (err.response?.data?.message || err.message));
      setMedicos([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchMedicos();
  }, []);

  const handleOpenModal = (medico = null) => {
    setMedicoAtual(medico);
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setMedicoAtual(null);
  };

  const handleSave = () => {
    fetchMedicos();
    handleCloseModal();
  };

  const handleDelete = async (id) => {
    if (window.confirm('Tem certeza que deseja excluir este médico?')) {
      try {
        await deleteMedico(id);
        fetchMedicos();
      } catch (err) {
        setError('Falha ao excluir médico. ' + (err.response?.data?.message || err.message));
      }
    }
  };

  if (loading) {
    return <div className="loading-indicator">Carregando médicos...</div>;
  }

  return (
    <div className="container medicos-page-container">
      <div className="page-header">
        <h1>Gerenciamento de Médicos</h1>
        <button className="button button-primary" onClick={() => handleOpenModal()}>
          <AddIcon /> Novo Médico
        </button>
      </div>

      {error && <div className="error-message">{error}</div>}
      
      {medicos.length === 0 && !loading && !error && (
        <div className="no-data-message">Nenhum médico encontrado.</div>
      )}

      <div className="cards-grid">
        {medicos.map((medico) => (
          <MedicoCard
            key={medico.id}
            medico={medico}
            onEdit={() => handleOpenModal(medico)}
            onDelete={handleDelete}
          />
        ))}
      </div>

      {isModalOpen && (
        <Modal title={medicoAtual ? 'Editar Médico' : 'Novo Médico'} onClose={handleCloseModal}>
          <MedicoForm medicoToEdit={medicoAtual} onSave={handleSave} onCancel={handleCloseModal} />
        </Modal>
      )}
    </div>
  );
}

export default MedicosPage;