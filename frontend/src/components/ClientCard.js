import React from 'react';
import './ClientCard.css'; // Importa o CSS

// Você pode importar ícones SVG ou usar caracteres Unicode
const EditIcon = () => <span>✏️</span>; // Exemplo simples
const DeleteIcon = () => <span>🗑️</span>; // Exemplo simples

function ClienteCard({ cliente, onEdit, onDelete }) {
  return (
    <div className="card">
      <div className="card-content">
        <p className="card-id">Cliente ID: {cliente.id}</p>
        <h3 className="card-title">{cliente.nome}</h3>
        <p className="card-subtitle">CPF: {cliente.cpf}</p>
        <div className="card-body">
          <p><strong>Email:</strong> {cliente.email}</p>
          <p><strong>Telefone:</strong> {cliente.telefone}</p>
          <p><strong>Sexo:</strong> {cliente.sexo}</p>
          <p><strong>Data Nasc.:</strong> {cliente.dataNascimento}</p>
        </div>
      </div>
      <div className="card-actions">
        <button className="button button-secondary button-edit" onClick={() => onEdit(cliente)}>
          <EditIcon /> Editar
        </button>
        <button className="button button-danger button-delete" onClick={() => onDelete(cliente.id)}>
          <DeleteIcon /> Excluir
        </button>
      </div>
    </div>
  );
}

export default ClienteCard;