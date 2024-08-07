import { getAllSubmissionsByProblemSlug, getSubmission, makeSubmission } from "@/lib/api/submission.api";
import { useMutation, QueryClient, useQuery } from "@tanstack/vue-query";
import { QUERY_KEYS } from "@/lib/tanstack/queryKeys";
import { ref } from "vue";

export const submissionQueries = () => {
    const queryClient = new QueryClient()
    const makeSubmissionMutation = () => {
        const problemSlug = ref("");
        return useMutation({
            mutationFn: (data) => {
                problemSlug.value = data.problemSlug
                return makeSubmission(data)
            },
            onSuccess: () => {
                queryClient.invalidateQueries({
                    queryKey: [QUERY_KEYS.GET_ALL_SUBMISSION, problemSlug.value]
                }).then(r => console.log(r))
            }
        })
    }

    const getAllSubmissionByProblemSlug = (slug) =>
        useQuery({
            queryKey: [QUERY_KEYS.GET_ALL_SUBMISSION, slug],
            queryFn: () => getAllSubmissionsByProblemSlug(slug)
        })

    const getSubmissionQuery = (id) =>
        useQuery({
            queryKey: [QUERY_KEYS.GET_SUBMISSION_BY_ID, id],
            queryFn: () => getSubmission(id)
        })

    return {
        makeSubmissionMutation,
        getAllSubmissionByProblemSlug,
        getSubmissionQuery
    };
}