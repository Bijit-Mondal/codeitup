export const register = async (data) => {
    // eslint-disable-next-line no-useless-catch
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
    try {
        const response = await fetch('/api/v1/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        return await response.json();
    } catch (error) {
        return { error: error.message };
    }
};