export const useNotification = () => {
    // didn't like passing vs as a parameter to the function, need to solve
    const notify = (vs,title, message, color) => {
        vs.notify({
            time: 4000,
            title: title,
            text: message,
            color: color,
            position: 'top-right',
        });
    };

    const successMessage = (vs, message) => {
        notify(vs, 'Success', message, 'success');
    };

    const errorMessage = (vs, message) => {
        notify(vs, 'Error', message, 'danger');
    };

    const warningMessage = (vs, message) => {
        notify(vs, 'Warning' ,message, 'warning');
    };

    const loading = (vs, type='corners') => {
        vs.loading({
            type
        })
    }

    return { successMessage, errorMessage, warningMessage, loading };
};