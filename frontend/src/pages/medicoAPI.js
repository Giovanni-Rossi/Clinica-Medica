// frontend/src/api/medicoApi.js
import axios from 'axios';

// URL base da API, vinda das variáveis de ambiente do Docker ou um fallback
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

// Instância do Axios configurada para este módulo de API
const apiClientInstance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

const API_URL_MEDICOS = '/medicos'; // Endpoint específico para médicos

export const getAllMedicos = () => {
    return apiClientInstance.get(API_URL_MEDICOS);
};

export const getMedicoById = (id) => {
    return apiClientInstance.get(`${API_URL_MEDICOS}/${id}`);
};

export const getMedicosByEspecialidade = (especialidade) => {
    return apiClientInstance.get(`${API_URL_MEDICOS}/especialidade/${especialidade}`);
};

export const createMedico = (medicoData) => {
    return apiClientInstance.post(API_URL_MEDICOS, medicoData);
};

// medicoAPI.js
export const updateMedico = (id, medicoData) => apiClientInstance.put(`/medicos/${id}`, medicoData);

export const deleteMedico = (id) => {
    return apiClientInstance.delete(`${API_URL_MEDICOS}/${id}`);
};