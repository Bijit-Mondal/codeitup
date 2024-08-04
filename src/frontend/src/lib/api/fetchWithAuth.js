import { useAuthStore } from "@/lib/store/auth.store";

export const fetchWithAuth = async (url, options = {}) => {
    const authStore = useAuthStore();
    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authStore.accessToken}`
    };

    return await fetch(url, { ...options, headers });
};