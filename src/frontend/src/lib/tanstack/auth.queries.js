import { useMutation } from '@tanstack/vue-query'
import { useRouter } from 'vue-router'

import { login, register } from "@/lib/api/auth.api";


export const authQueries = () => {
    // const queryClient = useQueryClient();
    const router = useRouter();

    const loginMutation = () =>
        useMutation({
            mutationFn: (auth) => login(auth),
            onSuccess: () => {
                router.push({name: 'Home'});
            }
        })

    const registerMutation = () =>
        useMutation({
            mutationFn: ( auth) => register(auth),
            onSuccess: () => {
                // router.push({ name:'problem' });
            }
        });


    return { loginMutation, registerMutation };
}