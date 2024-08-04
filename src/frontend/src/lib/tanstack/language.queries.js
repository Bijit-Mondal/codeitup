import { useMutation, useQuery, useQueryClient} from "@tanstack/vue-query";

import { QUERY_KEYS } from "@/lib/api/queryKeys";
import {addLanguage, getAllLanguage} from "@/lib/api/language.api";

export const languageQueries = () => {
        const queryClient = useQueryClient();

        const getAllLanguageQuery = () =>
            useQuery({
                queryKey: [QUERY_KEYS.GET_ALL_LANGUAGE],
                queryFn: () => getAllLanguage()
            })

        const addLanguageMutation = () =>
            useMutation({
                mutationFn: (language) => addLanguage(language),
                onSuccess: () => {
                    queryClient.invalidateQueries({
                        queryKey: [QUERY_KEYS.GET_ALL_LANGUAGE]
                    }).then(r => console.log(r))
                }
            })

        return {
            getAllLanguageQuery,
            addLanguageMutation
        }
}