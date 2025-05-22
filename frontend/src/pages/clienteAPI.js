import axios from 'axios'; // Ou seu apiClient configurado

// Se você configurou apiClient:
// import apiClient from './axiosConfig';
// const API_BASE_URL = ''; // Já está no apiClient

// Se não configurou apiClient, use a URL completa ou defina aqui:
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';


// Ajuste para usar apiClient se você o criou, ou axios diretamente
const apiClientInstance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});


const API_URL_CLIENTES = '/clientes';

export const getAllClientes = () => apiClientInstance.get(API_URL_CLIENTES);
export const getClienteById = (id) => apiClientInstance.get(`${API_URL_CLIENTES}/${id}`);
export const createCliente = (clienteData) => apiClientInstance.post(API_URL_CLIENTES, clienteData);
export const updateCliente = (id, clienteData) => apiClientInstance.put(`${API_URL_CLIENTES}/${id}`, clienteData);
export const deleteCliente = (id) => apiClientInstance.delete(`${API_URL_CLIENTES}/${id}`);

// Adicione outras funções de API para Medico e Consulta aqui ou em arquivos separados
// como medicoApi.js e consultaApi.js