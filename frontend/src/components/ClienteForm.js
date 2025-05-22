import React, { useState, useEffect } from 'react';
import { createCliente, updateCliente } from '../pages/clienteAPI';
import './ClienteForm.css'; // Importa o CSS
import '../App.css'; // Para estilos de .button

function ClienteForm({ clienteToEdit, onSave, onCancel }) {
  const [formData, setFormData] = useState({
    nome: '', email: '', senha: '', cpf: '',
    telefone: '', sexo: '', dataNascimento: '', papel: 'CLIENTE',
  });
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  useEffect(() => {
    // ... (mesma lógica de preenchimento do formulário da versão MUI) ...
    if (clienteToEdit) {
      setFormData({
        nome: clienteToEdit.nome || '',
        email: clienteToEdit.email || '',
        senha: '', // Não preencher senha para edição por segurança
        cpf: clienteToEdit.cpf || '',
        telefone: clienteToEdit.telefone || '',
        sexo: clienteToEdit.sexo || '',
        dataNascimento: clienteToEdit.dataNascimento || '',
        papel: clienteToEdit.papel || 'CLIENTE',
      });
    } else {
      setFormData({
        nome: '', email: '', senha: '', cpf: '',
        telefone: '', sexo: '', dataNascimento: '', papel: 'CLIENTE',
      });
    }
  }, [clienteToEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    // ... (mesma lógica de validação da versão MUI) ...
    let tempErrors = {};
    if (!formData.nome) tempErrors.nome = "Nome é obrigatório.";
    if (!formData.email) tempErrors.email = "Email é obrigatório.";
    else if (!/\S+@\S+\.\S+/.test(formData.email)) tempErrors.email = "Email inválido.";
    if (!clienteToEdit && !formData.senha) tempErrors.senha = "Senha é obrigatória para novos clientes.";
    if (!formData.cpf) tempErrors.cpf = "CPF é obrigatório.";
    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  if (!validate()) return;

  setIsSubmitting(true);
  try {
    const dataToSend = { ...formData };
    
    // Remover senha apenas se estiver vazia (edição)
    if (clienteToEdit && !dataToSend.senha) {
      delete dataToSend.senha;
    }

    console.log("Enviando dados:", dataToSend); // 👈 Depuração

    if (clienteToEdit) {
      await updateCliente(clienteToEdit.id, dataToSend);
    } else {
      await createCliente(dataToSend);
    }
    onSave();
  } catch (error) {
    console.error("Erro completo:", error.response?.data); // 👈 Detalhes do erro
    setErrors(prev => ({ ...prev, form: 'Falha ao salvar. Verifique os dados.' }));
  } finally {
    setIsSubmitting(false);
  }
};
  return (
    <form onSubmit={handleSubmit} className="form-container">
      <div className="form-grid">
        <div className="form-group full-width"> {/* Exemplo de campo full-width */}
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
            {clienteToEdit ? "Nova Senha (deixe em branco para manter)" : "Senha"}
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
          <label htmlFor="cpf">CPF</label>
          <input
            type="text"
            id="cpf"
            name="cpf"
            value={formData.cpf}
            onChange={handleChange}
            className={errors.cpf ? 'input-error' : ''}
          />
          {errors.cpf && <p className="error-text">{errors.cpf}</p>}
        </div>
        
        <div className="form-group">
          <label htmlFor="telefone">Telefone</label>
          <input
            type="text"
            id="telefone"
            name="telefone"
            value={formData.telefone}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="sexo">Sexo</label>
          <input // Poderia ser um <select>
            type="text"
            id="sexo"
            name="sexo"
            value={formData.sexo}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label htmlFor="dataNascimento">Data de Nascimento</label>
          <input
            type="date"
            id="dataNascimento"
            name="dataNascimento"
            value={formData.dataNascimento}
            onChange={handleChange}
          />
        </div>
        
        <div className="form-group full-width">
            <label htmlFor="papel">Papel</label>
            <input
                type="text"
                id="papel"
                name="papel"
                value={formData.papel}
                onChange={handleChange}
                disabled // Geralmente o papel não é editável pelo usuário comum
            />
        </div>
      </div>

      {errors.form && <p className="error-message" style={{marginTop: '15px'}}>{errors.form}</p>}

      <div className="form-actions">
        <button type="button" className="button button-secondary" onClick={onCancel} disabled={isSubmitting}>
          Cancelar
        </button>
        <button type="submit" className="button button-primary" disabled={isSubmitting}>
          {isSubmitting ? 'Salvando...' : (clienteToEdit ? 'Atualizar' : 'Criar')}
        </button>
      </div>
    </form>
  );
}

export default ClienteForm;