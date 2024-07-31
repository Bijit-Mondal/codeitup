import { required, email, minLength } from '@vuelidate/validators';

export const registerValidation = {
    name: { required },
    username: { required },
    email: { required, email },
    password: { required, minLength: minLength(8) }
};