import React from 'react';
import './Modal.css';

function Modal({ title, children, onClose }) {
  // Para fechar o modal ao clicar fora ou pressionar Escape
  React.useEffect(() => {
    const handleEsc = (event) => {
      if (event.keyCode === 27) {
        onClose();
      }
    };
    window.addEventListener('keydown', handleEsc);
    return () => {
      window.removeEventListener('keydown', handleEsc);
    };
  }, [onClose]);

  return (
    <div className="modal-backdrop" onClick={onClose}> {/* Fecha ao clicar no backdrop */}
      <div className="modal-content" onClick={(e) => e.stopPropagation()}> {/* Impede fechamento ao clicar dentro do conteúdo */}
        <div className="modal-header">
          <h2>{title}</h2>
          <button className="modal-close-button" onClick={onClose}>×</button>
        </div>
        <div className="modal-body">
          {children}
        </div>
      </div>
    </div>
  );
}

export default Modal;