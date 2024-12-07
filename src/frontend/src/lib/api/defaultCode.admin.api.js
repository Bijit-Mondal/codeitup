import { API_ENDPOINTS } from '../constants/api';
import { fetchWithAuth } from "./fetchWithAuth";

export const addDefaultCode = async (data) => {
    const response = await fetchWithAuth(API_ENDPOINTS.ADMIN.DEFAULT_CODE, {
        method: 'POST',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return "Default Code Added Successfully"
}

export const getDefaultCodeByProblem = async (id) => {
    const response = await fetchWithAuth(`${API_ENDPOINTS.ADMIN.DEFAULT_CODE}/${id}`, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const updateDefaultCode = async (problemId, languageId, data) => {
    const response = await fetchWithAuth(`${API_ENDPOINTS.ADMIN.DEFAULT_CODE}/${problemId}/${languageId}`, {
        method: 'PUT',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return "Default Code Updated Successfully"
}