<template>
  <div class="problem-tabs">
    <h2>
      Problems
    </h2>
<!--    tab is commented need to open in future-->
<!--    <vs-tabs class="problem-tabs__tabs" alignment="fixed" hover-line hover-text>-->
<!--      <vs-tab label="Striver's A-Z DSA Sheet">-->
<!--        <div class="tab-content">-->
            <ProblemList/>
<!--        </div>-->
<!--      </vs-tab>-->
<!--      <vs-tab label="Striver's SDE Sheet">-->
<!--        <div>-->
<!--          <Suspense>-->
<!--            <ProblemList/>-->
<!--            <template #fallback>-->
<!--              <SkeletonList/>-->
<!--            </template>-->
<!--          </Suspense>-->
<!--        </div>-->
<!--      </vs-tab>-->
<!--      <vs-tab label="Striver's 79 Sheet">-->
<!--        <div>-->

<!--        </div>-->
<!--      </vs-tab>-->
<!--      <vs-tab label="Blind 75 Sheet">-->
<!--        <div>-->

<!--        </div>-->
<!--      </vs-tab>-->
<!--    </vs-tabs>-->
  </div>
</template>
<script setup>
import ProblemList from "../../component/problem/ProblemList"
</script>
<style scoped>
.problem-tabs{
  margin: 0.5rem;
  background-color: var(--dark);
  border-radius: 5px;
  padding: 0.8rem 0.7rem;
  & h2 {
      margin-left: 1rem;
      font-size: 18px;
      margin-bottom: 10px;
  }
  & .problem-tabs__tabs {
    margin-top: 1em;
  }
}

</style>
<style>
.ul-tabs-fixed > .activeChild{
  font-family: 'Gilroy-Bold',sans-serif;
}
</style>