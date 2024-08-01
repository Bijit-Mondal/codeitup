import { required, email, minLength , maxLength } from '@vuelidate/validators';

export const registerValidation = {
    name: { required },
    profileName: { required, minLength: minLength(4), maxLength: maxLength(14) },
    email: { required, email },
    password: { required, minLength: minLength(8) }
};

export const loginValidation = {
    email: { required, email },
    password: { required, minLength: minLength(8) }
};

export const otpValidation  = {
    otp: { required, minLength: minLength(4), maxLength: maxLength(4) },
    email: { required, email }
}