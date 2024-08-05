import {useInfiniteQuery, useQuery} from "@tanstack/vue-query";
import {getAllProblem, getProblemBySlug} from "@/lib/api/problem.user.api";
import { QUERY_KEYS } from "@/lib/tanstack/queryKeys";

export const problemUserQueries = () => {
    const getAllProblemQuery = () =>
        useInfiniteQuery({
            queryKey: [QUERY_KEYS.GET_ALL_PROBLEM],
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

    const getProblemBySlugQuery = (slug) =>
        useQuery({
            queryKey: [QUERY_KEYS.GET_PROBLEM_BY_SLUG, slug],
            queryFn: () => getProblemBySlug(slug)
        })

    return {
        getAllProblemQuery,
        getProblemBySlugQuery
    }
}
