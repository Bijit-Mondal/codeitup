import {useInfiniteQuery, useMutation, useQuery, useQueryClient} from "@tanstack/vue-query";

import {
    addProblem,
    getAllProblem,
    getProblemBySlug,
    toggleProblemStatus,
    updateProblem
} from "@/lib/api/problem.admin.api";

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

    const getAllProblemAdminQuery = () =>
        useInfiniteQuery({
            queryKey: [QUERY_KEYS.GET_ALL_PROBLEM_ADMIN],
            queryFn: ({ pageParam = 0 }) => getAllProblem(pageParam),
            getNextPageParam: (lastPage) => {
                const { pageable, totalPages } = lastPage;
                const { pageNumber } = pageable;
                if (pageNumber + 1 < totalPages) {
                    return pageNumber + 1;
                }
                return undefined;
            },
        })

    const getProblemBySlugAdminQuery = (slug) =>
        useQuery({
            queryKey: [QUERY_KEYS.GET_PROBLEM_BY_SLUG_ADMIN, slug],
            queryFn: () => getProblemBySlug(slug)
        })

    const updateProblemMutation = () =>
        useMutation({
            mutationFn: ({ slug, problem }) => updateProblem(slug, problem),
            onSuccess: (data) => {
                queryClient.invalidateQueries({
                    queryKey: [QUERY_KEYS.GET_PROBLEM_BY_SLUG_ADMIN, data?.slug]
                }).then(r => console.log(r))
            }
        });

    const toggleProblemStatusMutation = () =>
        useMutation({
            mutationFn: ({ slug, problem }) => toggleProblemStatus(slug, problem),
            onSuccess: (data) => {
                queryClient.invalidateQueries({
                    queryKey: [QUERY_KEYS.GET_PROBLEM_BY_SLUG_ADMIN, data?.slug]
                }).then(r => console.log(r))
            }
        });
    return {
        addProblemMutation,
        getAllProblemAdminQuery,
        getProblemBySlugAdminQuery,
        toggleProblemStatusMutation,
        updateProblemMutation
    }
}