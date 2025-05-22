// frontend/src/components/MedicoForm.js
import React, { useState, useEffect } from 'react';
import { createMedico, updateMedico } from '../pages/medicoAPI'; // API correta
import './MedicoForm.css';
import '../App.css'; // Para .button

function MedicoForm({ medicoToEdit, onSave, onCancel }) {
  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    senha: '',
    crm: '',
    especialidade: '',
    papel: 'MEDICO', // Papel padrão para médico
  });
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  useEffect(() => {
    if (medicoToEdit) {
      setFormData({
        nome: medicoToEdit.nome || '',
        email: medicoToEdit.email || '',
        senha: '', // Não preencher senha para edição
        crm: medicoToEdit.crm || '',
        especialidade: medicoToEdit.especialidade || '',
        papel: medicoToEdit.papel || 'MEDICO',
      });
    } else {
      setFormData({
        nome: '', email: '', senha: '', crm: '',
        especialidade: '', papel: 'MEDICO',
      });
    }
  }, [medicoToEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    let tempErrors = {};
    if (!formData.nome) tempErrors.nome = "Nome é obrigatório.";
    if (!formData.email) tempErrors.email = "Email é obrigatório.";
    else if (!/\S+@\S+\.\S+/.test(formData.email)) tempErrors.email = "Email inválido.";
    if (!medicoToEdit && !formData.senha) tempErrors.senha = "Senha é obrigatória para novos médicos.";
    if (!formData.crm) tempErrors.crm = "CRM é obrigatório.";
    if (!formData.especialidade) tempErrors.especialidade = "Especialidade é obrigatória.";
    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  if (!validate()) return;

  setIsSubmitting(true);
  try {
    const dataToSend = { ...formData };
    
    // Se for edição e a senha estiver vazia, mantenha a senha existente
    if (medicoToEdit && !dataToSend.senha) {
      dataToSend.senha = medicoToEdit.senha; // 👈 Novo código
    }

    console.log("Dados enviados:", dataToSend); // Depuração

    if (medicoToEdit) {
      await updateMedico(medicoToEdit.id, dataToSend);
    } else {
      await createMedico(dataToSend);
    }
    onSave();
  } catch (error) {
    console.error("Resposta do erro:", error.response?.data);
    setErrors(prev => ({ ...prev, form: 'Falha ao salvar. Verifique os dados.' }));
  } finally {
    setIsSubmitting(false);
  }
};

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <div className="form-grid">
        <div className="form-group full-width">
          <label htmlFor="nome">Nome Completo</label>
          <input
            type="text"
            id="nome"
            name="nome"
            value={formData.nome}
            onChange={handleChange}
            className={errors.nome ? 'input-error' : ''}
          />
          {errors.nome && <p className="error-text">{errors.nome}</p>}
        </div>

        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className={errors.email ? 'input-error' : ''}
          />
          {errors.email && <p className="error-text">{errors.email}</p>}
        </div>

        <div className="form-group">
          <label htmlFor="senha">
            {medicoToEdit ? "Nova Senha (deixe em branco para manter)" : "Senha"}
          </label>
          <input
            type="password"
            id="senha"
            name="senha"
            value={formData.senha}
            onChange={handleChange}
            className={errors.senha ? 'input-error' : ''}
          />
          {errors.senha && <p className="error-text">{errors.senha}</p>}
        </div>

        <div className="form-group">
          <label htmlFor="crm">CRM</label>
          <input
            type="text"
            id="crm"
            name="crm"
            value={formData.crm}
            onChange={handleChange}
            className={errors.crm ? 'input-error' : ''}
          />
          {errors.crm && <p className="error-text">{errors.crm}</p>}
        </div>
        
        <div className="form-group">
          <label htmlFor="especialidade">Especialidade</label>
          <input
            type="text"
            id="especialidade"
            name="especialidade"
            value={formData.especialidade}
            onChange={handleChange}
            className={errors.especialidade ? 'input-error' : ''}
          />
          {errors.especialidade && <p className="error-text">{errors.especialidade}</p>}
        </div>
        
        <div className="form-group full-width">
            <label htmlFor="papel">Papel</label>
            <input
                type="text"
                id="papel"
                name="papel"
                value={formData.papel}
                onChange={handleChange}
                disabled
            />
        </div>
      </div>

      {errors.form && <p className="error-message" style={{marginTop: '15px'}}>{errors.form}</p>}

      <div className="form-actions">
        <button type="button" className="button button-secondary" onClick={onCancel} disabled={isSubmitting}>
          Cancelar
        </button>
        <button type="submit" className="button button-primary" disabled={isSubmitting}>
          {isSubmitting ? 'Salvando...' : (medicoToEdit ? 'Atualizar' : 'Criar')}
        </button>
      </div>
    </form>
  );
}

export default MedicoForm;