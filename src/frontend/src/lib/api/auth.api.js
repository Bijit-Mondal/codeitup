import { API_ENDPOINTS } from '../constants/api';

export const register = async (data) => {
    const response = await fetch(API_ENDPOINTS.AUTH.REGISTER, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    if(!response.ok) {
        const res = await response.json();
        throw new Error(res.message);
    }
    return "Registration successful. Please check your email to verify your account.";
};

export const login = async (data) => {
    const response = await fetch(API_ENDPOINTS.AUTH.LOGIN, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    if(!response.ok) {
        const res = await response.json();
        throw new Error(res.message);
    }
    return await response.json();
};

export const otpValidate = async (data) => {
    const response = await fetch(API_ENDPOINTS.AUTH.VALIDATE_OTP, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    if(!response.ok) {
        const res = await response.json();
        throw new Error(res.message);
    }
    return "OTP validation successful. You can now login.";
}