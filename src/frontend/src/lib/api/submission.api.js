import { fetchWithAuth } from "./fetchWithAuth";

export const makeSubmission = async (data) => {
    const response =  await fetchWithAuth(`/api/v1/user/submission`, {
        method: 'POST',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}