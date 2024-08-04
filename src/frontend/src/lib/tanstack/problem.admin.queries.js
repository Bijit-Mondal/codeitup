import { useMutation, useQueryClient } from "@tanstack/vue-query";

import { addProblem } from "@/lib/api/problem.admin.api";

import { QUERY_KEYS } from "@/lib/api/queryKeys";

export const problemAdminQueries = () => {

    const queryClient = useQueryClient();
    const addProblemMutation = () =>
        useMutation({
            mutationFn: (problem) => addProblem(problem),
            onSuccess: () => {
                queryClient.invalidateQueries({
                    queryKey: [QUERY_KEYS.GET_ALL_PROBLEM_ADMIN]
                }).then(r => console.log(r))
            }
        })

    return {
        addProblemMutation
    }
}