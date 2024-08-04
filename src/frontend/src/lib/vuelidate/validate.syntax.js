import { required, email, minLength , maxLength, url } from '@vuelidate/validators';

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

export const problemValidation = {
    title: { required, maxLength: maxLength(40) },
    slug: { required, maxLength: maxLength(20) },
    description: { required,minLength: minLength(50) },
    testCases: { required, url},
    difficulty: { required }
}

export const defaultCodeValidation = {
    problemId: {required},
    runnerCode: {required, minLength: minLength(1) },
    code: {required, minLength: minLength(1)},
    languageId: { required }
}