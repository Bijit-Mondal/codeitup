import { fetchWithAuth } from "./fetchWithAuth";

export const addDefaultCode = async (data) => {
    const response =  await fetchWithAuth('/api/v1/admin/default-code', {
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
    const response =  await fetchWithAuth(`/api/v1/admin/default-code/${id}`, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const updateDefaultCode = async (problemId, languageId, data) => {
    const response =  await fetchWithAuth(`/api/v1/admin/default-code/${problemId}/${languageId}`, {
        method: 'PUT',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return "Default Code Updated Successfully"
}