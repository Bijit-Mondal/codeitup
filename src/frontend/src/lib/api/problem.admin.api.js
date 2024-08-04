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
