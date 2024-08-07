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

export const getSubmission = async (id) => {
    const response =  await fetchWithAuth(`/api/v1/user/submission/${id}`);
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

// need to improve this endpoint + pagination
export const getAllSubmissionsByProblemSlug = async (slug) => {
    const response =  await fetchWithAuth(`/api/v1/user/submission/problem/${slug}`);
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}