// import { API_ENDPOINTS } from '../constants/api';
import { fetchWithAuth } from "./fetchWithAuth";

export const addProblem = async (data) => {
    const response =  await fetchWithAuth('/api/v1/admin/problem', {
        method: 'POST',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return "Problem Added Successfully"
};

export const getAllProblem = async (page) => {
    const response =  await fetchWithAuth(`/api/v1/admin/problem?page=${page}`, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const getProblemBySlug = async (slug) => {
    const response =  await fetchWithAuth(`/api/v1/admin/problem/${slug}`, {
        method: 'GET',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return response.json()
}

export const updateProblem = async (slug, data) => {
    const response =  await fetchWithAuth(`/api/v1/admin/problem/${slug}`, {
        method: 'PUT',
        body: JSON.stringify(data)
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return await response.json()
}

export const toggleProblemStatus = async (slug) => {
    const response =  await fetchWithAuth(`/api/v1/admin/problem/toggle-hide/${slug}`, {
        method: 'PUT',
    });
    if(!response.ok){
        const res = await response.json()
        throw new Error(res.message)
    }
    return await response.json()
}