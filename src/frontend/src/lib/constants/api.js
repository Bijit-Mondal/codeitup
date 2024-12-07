export const API_ENDPOINTS = {
    AUTH: {
        LOGIN: '/api/v1/auth/login',
        REGISTER: '/api/v1/auth/register',
        VALIDATE_OTP: '/api/v1/auth/validate',
        LOGOUT: '/api/v1/auth/logout'
    },
    PROBLEMS: {
        LIST: '/api/v1/open/problem',
        DETAIL: '/api/v1/open/problem/:slug',
        LANGUAGES: '/api/v1/open/language',
        SUBMIT: '/api/v1/user/submission',
        SUBMISSIONS: '/api/v1/user/submission/problem/:slug'
    },
    ADMIN: {
        PROBLEMS: '/api/v1/admin/problem',
        LANGUAGES: '/api/v1/admin/language',
        DEFAULT_CODE: '/api/v1/admin/default-code'
    }
};