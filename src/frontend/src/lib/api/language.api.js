import { fetchWithAuth } from "./fetchWithAuth";

export const getAllLanguage = async () => {
    const response =  await fetchWithAuth('/api/v1/open/language', {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const addLanguage = async (data) => {
    const response =  await fetchWithAuth('/api/v1/open/language', {
        method: 'POST',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return "Language Added Successfully"
}