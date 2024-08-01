import { useMutation } from '@tanstack/vue-query'
import { useRouter } from 'vue-router'

import { login, otpValidate, register } from "@/lib/api/auth.api";

import { useAuthStore } from '../store/auth.store';
import {ref} from "vue";

export const authQueries = () => {
    const router = useRouter();
    const authStore = useAuthStore();

    const loginMutation = () =>
        useMutation({
            mutationFn: (auth) => login(auth),
            onSuccess: (data) => {
                authStore.login(data);
                router.push({name: 'home'});
            }
        })

    const otpValidateMutation = () =>
        useMutation({
            mutationFn: (otp) => otpValidate(otp),
            onSuccess: () => {
                router.push({name: 'auth'})
            }
        })


    const registerMutation = () => {
        const email = ref("")
        return useMutation({
            mutationFn: ( auth) =>{
                email.value = auth.email
                return register(auth)
            },
            onSuccess: () => {
                router.push({ name: 'otp', query: { email: email.value } });
            }
        });
    }


    return { loginMutation, registerMutation, otpValidateMutation };
}