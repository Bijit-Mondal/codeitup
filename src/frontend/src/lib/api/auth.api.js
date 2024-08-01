export const register = async (data) => {
    const response = await fetch('/api/v1/auth/register', {
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
    const response = await fetch('/api/v1/auth/login', {
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
    const response = await fetch('/api/v1/auth/validate', {
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