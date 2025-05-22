// frontend/src/components/MedicoCard.js
import React from 'react';
import './MedicoCard.css'; // Importa o CSS

// Ícones (mesmo exemplo)
const EditIcon = () => <span>✏️</span>;
const DeleteIcon = () => <span>🗑️</span>;

function MedicoCard({ medico, onEdit, onDelete }) {
  return (
    <div className="card">
      <div className="card-content">
        <p className="card-id">Médico ID: {medico.id}</p>
        <h3 className="card-title">Dr(a). {medico.nome}</h3>
        <p className="card-subtitle">CRM: {medico.crm}</p>
        <div className="card-body">
          <p><strong>Especialidade:</strong> {medico.especialidade}</p>
          <p><strong>Email:</strong> {medico.email}</p>
          {/* Adicionar mais campos se necessário, como 'papel' se for relevante exibir */}
        </div>
      </div>
      <div className="card-actions">
        <button className="button button-secondary button-edit" onClick={() => onEdit(medico)}>
          <EditIcon /> Editar
        </button>
        <button className="button button-danger button-delete" onClick={() => onDelete(medico.id)}>
          <DeleteIcon /> Excluir
        </button>
      </div>
    </div>
  );
}

export default MedicoCard;