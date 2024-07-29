<template>
  <table class="table">
    <thead>
      <tr>
        <th>Problem</th>
        <th>External Link</th>
        <th class="text-align-center">Difficulty</th>
        <th class="text-align-center">Video Solution</th>
        <th class="text-align-center">Code</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>
          <router-link class="problem_title" to="/problems/id/he">
              Reverse Linked List
          </router-link>
        </td>
        <td>
          <vs-button color="primary" type="line" size="small">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M15.4081 18.5919C15.0081 18.5919 14.6781 18.2619 14.6781 17.8619C14.6781 17.4619 15.0081 17.1319 15.4081 17.1319C18.2281 17.1319 20.5281 14.8319 20.5281 12.0119C20.5281 9.19188 18.2281 6.89187 15.4081 6.89187C12.5881 6.89187 10.2881 9.19188 10.2881 12.0119C10.2881 12.4119 9.95813 12.7419 9.55813 12.7419C9.15813 12.7419 8.82812 12.4119 8.82812 12.0119C8.82812 8.38188 11.7781 5.42188 15.4181 5.42188C19.0581 5.42188 21.9981 8.37187 21.9981 12.0019C21.9981 15.6319 19.0481 18.5919 15.4081 18.5919Z" fill="var(--primary)"/>
              <path d="M8.59 5.41016C8.99 5.41016 9.32 5.74016 9.32 6.14016C9.32 6.54016 8.99 6.87016 8.59 6.87016C5.77 6.87016 3.47 9.17016 3.47 11.9902C3.47 14.8102 5.77 17.1102 8.59 17.1102C11.41 17.1102 13.71 14.8102 13.71 11.9902C13.71 11.5902 14.04 11.2602 14.44 11.2602C14.84 11.2602 15.17 11.5902 15.17 11.9902C15.17 15.6202 12.22 18.5802 8.58 18.5802C4.94 18.5802 2 15.6302 2 12.0002C2 8.37016 4.95 5.41016 8.59 5.41016Z" fill="var(--primary)"/>
            </svg>
          </vs-button>
        </td>
        <td class="text-align-center">
          <vs-chip transparent color="success">
            Easy
          </vs-chip>
        </td>
        <td class="text-align-center">
          <vs-button color="warning" type="flat">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M21.15 6.17C20.74 5.95 19.88 5.72 18.71 6.54L17.24 7.58C17.13 4.47 15.78 3.25 12.5 3.25H6.5C3.08 3.25 1.75 4.58 1.75 8V16C1.75 18.3 3 20.75 6.5 20.75H12.5C15.78 20.75 17.13 19.53 17.24 16.42L18.71 17.46C19.33 17.9 19.87 18.04 20.3 18.04C20.67 18.04 20.96 17.93 21.15 17.83C21.56 17.62 22.25 17.05 22.25 15.62V8.38C22.25 6.95 21.56 6.38 21.15 6.17ZM11 11.38C9.97 11.38 9.12 10.54 9.12 9.5C9.12 8.46 9.97 7.62 11 7.62C12.03 7.62 12.88 8.46 12.88 9.5C12.88 10.54 12.03 11.38 11 11.38Z"  fill="rgba(var(--vs-primary),0.7)"/>
            </svg>
          </vs-button>
        </td>
        <td class="text-align-center">
          <vs-button color="primary" type="border" size="small">
            View Code</vs-button>
        </td>
      </tr>
      <tr>
        <td>
          <router-link class="problem_title" to="/problems/id/he">
              Reverse Linked List
          </router-link>
        </td>
        <td>
          <vs-button color="primary" type="line" size="small">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M15.4081 18.5919C15.0081 18.5919 14.6781 18.2619 14.6781 17.8619C14.6781 17.4619 15.0081 17.1319 15.4081 17.1319C18.2281 17.1319 20.5281 14.8319 20.5281 12.0119C20.5281 9.19188 18.2281 6.89187 15.4081 6.89187C12.5881 6.89187 10.2881 9.19188 10.2881 12.0119C10.2881 12.4119 9.95813 12.7419 9.55813 12.7419C9.15813 12.7419 8.82812 12.4119 8.82812 12.0119C8.82812 8.38188 11.7781 5.42188 15.4181 5.42188C19.0581 5.42188 21.9981 8.37187 21.9981 12.0019C21.9981 15.6319 19.0481 18.5919 15.4081 18.5919Z" fill="var(--primary)"/>
              <path d="M8.59 5.41016C8.99 5.41016 9.32 5.74016 9.32 6.14016C9.32 6.54016 8.99 6.87016 8.59 6.87016C5.77 6.87016 3.47 9.17016 3.47 11.9902C3.47 14.8102 5.77 17.1102 8.59 17.1102C11.41 17.1102 13.71 14.8102 13.71 11.9902C13.71 11.5902 14.04 11.2602 14.44 11.2602C14.84 11.2602 15.17 11.5902 15.17 11.9902C15.17 15.6202 12.22 18.5802 8.58 18.5802C4.94 18.5802 2 15.6302 2 12.0002C2 8.37016 4.95 5.41016 8.59 5.41016Z" fill="var(--primary)"/>
            </svg>
          </vs-button>
        </td>
        <td class="text-align-center">
          <vs-chip transparent color="success">
            Easy
          </vs-chip>
        </td>
        <td class="text-align-center">
          <vs-button color="warning" type="flat">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M21.15 6.17C20.74 5.95 19.88 5.72 18.71 6.54L17.24 7.58C17.13 4.47 15.78 3.25 12.5 3.25H6.5C3.08 3.25 1.75 4.58 1.75 8V16C1.75 18.3 3 20.75 6.5 20.75H12.5C15.78 20.75 17.13 19.53 17.24 16.42L18.71 17.46C19.33 17.9 19.87 18.04 20.3 18.04C20.67 18.04 20.96 17.93 21.15 17.83C21.56 17.62 22.25 17.05 22.25 15.62V8.38C22.25 6.95 21.56 6.38 21.15 6.17ZM11 11.38C9.97 11.38 9.12 10.54 9.12 9.5C9.12 8.46 9.97 7.62 11 7.62C12.03 7.62 12.88 8.46 12.88 9.5C12.88 10.54 12.03 11.38 11 11.38Z"  fill="rgba(var(--vs-primary),0.7)"/>
            </svg>
          </vs-button>
        </td>
        <td class="text-align-center">
          <vs-button color="primary" type="border" size="small">
            View Code</vs-button>
        </td>
      </tr>
    </tbody>
  </table>
</template>
<style scoped>
.table {
  max-width: 1400px;
  width: 100%;
  border-radius: 5px;
  border: none;
  background-color: var(--background);
  color: var(--text);
  border-collapse: collapse;
  border-spacing: 10px;
  & .text-align-center {
    text-align: center;
  }
  & thead {
    background-color: transparent;
  }
  & th {
    border-bottom: 1px solid rgba(var(--vs-primary,0.1));
    padding-bottom: 8px;
    font-family: "Gilroy-Bold",sans-serif;
    font-size: 12.6px;
  }
  & td {
    padding: 0.1rem 0.6rem;
    font-size: 14.4px;
    font-weight: bolder;
    border-bottom: 0.2px solid var(--text-disable);
    & .problem_title {
      cursor: pointer;
      transition: color 0.3s;
      color: var(--text);
      &:hover{
        color: var(--primary);
      }
    }
  }
}
@media only screen and (max-width: 600px) {
  .table th:nth-child(3), .table td:nth-child(3) {
    display: none;
  }
}
</style>
<style>
.vs-button-border.isActive .vs-button--icon, .vs-button-border.isActive .vs-button--text, .vs-button-flat.isActive .vs-button--icon, .vs-button-flat.isActive .vs-button--text{
  color: var(--dark) !important;
}
td {
  & .vs-chip-success.bg-chip-transparent, .vs-chip-danger.bg-chip-transparent {
    float: none;
    margin: 0 auto;
    text-align: center;
    width: 40px;
  }
}
</style>
<script setup>
// import {ref} from "vue";

</script>