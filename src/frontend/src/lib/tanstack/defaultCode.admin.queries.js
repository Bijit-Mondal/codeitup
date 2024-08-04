import { useMutation, useQuery, useQueryClient} from "@tanstack/vue-query";

import { QUERY_KEYS } from "@/lib/api/queryKeys";

import { addDefaultCode, getDefaultCodeByProblem, updateDefaultCode } from "@/lib/api/defaultCode.admin.api";
import {ref} from "vue";

export const defaultCodeAdminQueries = () => {

        const queryClient = useQueryClient();
        const addDefaultCodeMutation = () => {
            const problemId = ref("")
            return useMutation({
                mutationFn: (defaultCode) => {
                    problemId.value = defaultCode.problemId
                    return addDefaultCode(defaultCode)
                },
                onSuccess: () => {
                    queryClient.invalidateQueries({
                        queryKey: [QUERY_KEYS.GET_DEFAULT_CODE_BY_PROBLEM_ADMIN, problemId.value]
                    }).then(r => console.log(r))
                }
            })
        }
        
        const updateDefaultCodeMutation = () =>
            useMutation({
                mutationFn: ({problemId, languageId, data}) => updateDefaultCode(problemId, languageId, data),
                onSuccess: (data) => {
                    queryClient.invalidateQueries({
                        queryKey: [QUERY_KEYS.GET_DEFAULT_CODE_BY_PROBLEM_ADMIN, data.problemId]
                    }).then(r => console.log(r))
                }
            })


        const getDefaultCodeByProblemAdminQuery = (id) =>
            useQuery({
                queryKey: [QUERY_KEYS.GET_DEFAULT_CODE_BY_PROBLEM_ADMIN, id],
                queryFn: () => getDefaultCodeByProblem(id)
            })

        return {
            addDefaultCodeMutation,
            updateDefaultCodeMutation,
            getDefaultCodeByProblemAdminQuery
        }
}