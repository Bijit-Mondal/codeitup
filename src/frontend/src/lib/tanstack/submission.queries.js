import { makeSubmission } from "@/lib/api/submission.api";
import { useMutation, QueryClient } from "@tanstack/vue-query";

export const submissionQueries = () => {
    /* eslint-disable no-unused-vars */
    const queryClient = new QueryClient()
    const makeSubmissionMutation = () =>
        useMutation({
            mutationFn: (data) => makeSubmission(data),
        })

    return {
        makeSubmissionMutation,
    };
}