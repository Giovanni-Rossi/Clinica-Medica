// frontend/src/api/consultaApi.js
import axios from 'axios';

// URL base da API, vinda das variáveis de ambiente do Docker ou um fallback
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

// Instância do Axios configurada para este módulo de API
const apiClientInstance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

const API_URL_CONSULTAS = '/consultas'; // Endpoint específico para consultas

export const getAllConsultas = () => {
    return apiClientInstance.get(API_URL_CONSULTAS);
};

// Nota: No seu backend, você tem buscaPorId para consulta, mas não está no controller exposto como /consultas/{id}
// Se precisar, adicione um endpoint GET /consultas/{id} no backend e a função aqui.
// export const getConsultaById = (id) => {
//     return apiClientInstance.get(`${API_URL_CONSULTAS}/${id}`);
// };

export const getConsultasByPacienteId = (pacienteId) => {
    return apiClientInstance.get(`${API_URL_CONSULTAS}/paciente/${pacienteId}`);
};

export const getConsultasByMedicoId = (medicoId) => {
    return apiClientInstance.get(`${API_URL_CONSULTAS}/medico/${medicoId}`);
};

export const createConsulta = (consultaData) => {
    // Backend espera: { data: "string", clienteId: Long, medicoId: Long }
    return apiClientInstance.post(API_URL_CONSULTAS, consultaData);
};

export const updateConsulta = (id, consultaData) => {
    // Backend espera: { data: "string", clienteId: Long, medicoId: Long }
    return apiClientInstance.put(`${API_URL_CONSULTAS}/${id}`, consultaData);
};

export const deleteConsulta = (id) => {
    return apiClientInstance.delete(`${API_URL_CONSULTAS}/${id}`);
};