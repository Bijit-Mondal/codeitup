<template>
  <div class="submission">
    <vs-alert
        v-model:active="isError"
        title="Error fetching problems"
        color="danger"
        close-icon="close">
      {{ error }}
    </vs-alert>
    <div class="submission__switches">
<!--      Switch from ButtonGroup to switch between My Submission and All Submission-->
    </div>
    <div class="submission__list">
      <table v-if="!isLoading">
        <thead>
          <tr>
            <th>Time</th>
            <th>Status</th>
            <th>User</th>
            <th>Language</th>
            <th>Code</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="submission in submissions" :key="submission.submissionId">
            <td>{{ dateFormat(submission.time) }}</td>
            <td>{{ submission.submissionResult }}</td>
            <td>{{ submission.profile }}</td>
            <td>{{ submission.language }}</td>
            <td>
              <vs-button @click="popupActivo=true"  color="primary" type="flat" size="small">View Code</vs-button>
            </td>
          </tr>
        </tbody>
      </table>
      <div v-else>
        <SkeletonList/>
      </div>
    </div>
    <vs-popup class="holamundo"  title="Lorem ipsum dolor sit amet" v-model:active="popupActivo">
      <p>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      </p>
    </vs-popup>
  </div>
</template>
<script setup>
import { useRoute } from "vue-router";

import { submissionQueries } from "@/lib/tanstack/submission.queries"
const { getAllSubmissionByProblemSlug } = submissionQueries()

import SkeletonList from "@/component/skeleton/SkeletonList"

import { dateFormat } from "@/lib/composable/dateFormat"
import {ref} from "vue";

const route = useRoute()
const problemSlug = route.params.slug
const { data: submissions, isLoading, isError, error } = getAllSubmissionByProblemSlug(problemSlug)

const popupActivo = ref(false)

</script>
<style scoped>
.submission {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  & .submission__switches {
    margin-bottom: 1rem;
  }
  & .submission__list {
    background-color: var(--background);
    border-radius: 5px;
    width: 100%;
    overflow-x: auto;
    & table {
      width: 100%;
      border-collapse: collapse;
      & th {
        color: var(--text);
        padding: 0.5rem;
        border: 0;
        border-bottom: 1px solid var(--dark);
      }
      & td {
        font-size: 0.8rem;
        color: var(--text-disable);
        padding: 0.5rem;
        border: 0;
        border-bottom: 1px solid var(--dark);
      }
    }
  }
}
</style>
<style>
.vs-popup{
  background-color: var(--background) !important;
  & .vs-popup--title{
    background-color: var(--dark) !important;
  }
  & .vs-popup--close {
      background-color: var(--dark) !important;
      color: var(--text) !important;
  }
}
</style>