import {defineStore} from "pinia/dist/pinia";
import { computed, ref } from "vue";

export const useAuthStore = defineStore('auth', () => {
    // create the auth store here
    const accessToken = ref(null)
    const refreshToken = ref(null)
    const isLoggedIn = computed(() => !!accessToken.value)
    const login = (data) => {
        console.log(data)
        accessToken.value = data.access_token
        refreshToken.value = data.refresh_token
    }
    const logout = () => {
        accessToken.value = null
        refreshToken.value = null
    }
    return {
        accessToken,
        refreshToken,
        isLoggedIn,
        login,
        logout
    }
},
{
    persist: true,
},
)