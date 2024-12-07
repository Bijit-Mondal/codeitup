import { API_ENDPOINTS } from '../constants/api';
import { fetchWithAuth } from "./fetchWithAuth";

export const getAllLanguage = async () => {
    const response =  await fetchWithAuth(API_ENDPOINTS.PROBLEMS.LANGUAGES, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const addLanguage = async (data) => {
    console.log(data)
    const response =  await fetchWithAuth(API_ENDPOINTS.ADMIN.LANGUAGES, {
        method: 'POST',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return "Language Added Successfully"
}