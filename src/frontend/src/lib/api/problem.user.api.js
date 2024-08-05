import { fetchWithAuth } from "./fetchWithAuth";

export const getAllProblem = async (page) => {
    const response =  await fetchWithAuth(`/api/v1/user/problem?page=${page}`, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const getProblemBySlug = async (slug) => {
    const response =  await fetchWithAuth(`/api/v1/user/problem/${slug}`, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}