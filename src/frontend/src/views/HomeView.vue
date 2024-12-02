<template>
  <div class="home">
    <!-- Hero Section -->
    <section class="hero">
      <div class="hero__content">
        <h1 class="hero__title">
          Master DSA with 
          <span class="clip clip_codeitup">CodeitUp</span>
        </h1>
        <p class="hero__subtitle">
          Practice coding problems, participate in contests, and improve your problem-solving skills
        </p>
        <div class="hero__buttons">
          <vs-button 
            :to="{ name: 'problem' }" 
            color="primary" 
            size="large"
            class="hero__button">
            Start Practicing
          </vs-button>
          <vs-button 
            :to="{ name: 'contest' }" 
            color="primary" 
            type="border" 
            size="large"
            class="hero__button">
            Join Contest
          </vs-button>
        </div>
      </div>
      <div class="hero__image">
        <object class="no-pointers" data="https://bijit.xyz/assets/HeroIntro.svg">Banner image</object>
      </div>
    </section>

    <!-- Features Section -->
    <section class="features">
      <h2 class="features__title">Why Choose CodeitUp?</h2>
      <div class="features__grid">
        <div class="feature-card">
          <div class="feature-card__icon">
            <i class="material-icons">code</i>
          </div>
          <h3 class="feature-card__title">Interactive Coding</h3>
          <p class="feature-card__description">
            Write, compile and run code in multiple programming languages directly in your browser
          </p>
        </div>

        <div class="feature-card">
          <div class="feature-card__icon">
            <i class="material-icons">school</i>
          </div>
          <h3 class="feature-card__title">Learn DSA</h3>
          <p class="feature-card__description">
            Structured learning path with curated problems to master Data Structures & Algorithms
          </p>
        </div>

        <div class="feature-card">
          <div class="feature-card__icon">
            <i class="material-icons">emoji_events</i>
          </div>
          <h3 class="feature-card__title">Contests</h3>
          <p class="feature-card__description">
            Participate in coding contests to challenge yourself and compete with others
          </p>
        </div>

        <div class="feature-card">
          <div class="feature-card__icon">
            <i class="material-icons">trending_up</i>
          </div>
          <h3 class="feature-card__title">Track Progress</h3>
          <p class="feature-card__description">
            Monitor your progress with detailed statistics and performance analytics
          </p>
        </div>
      </div>
    </section>

    <!-- Getting Started Section -->
    <section class="getting-started">
      <h2 class="getting-started__title">Get Started Today</h2>
      <div class="getting-started__steps">
        <div class="step">
          <div class="step__number">1</div>
          <h3 class="step__title">Create Account</h3>
          <p class="step__description">Sign up for free and join our coding community</p>
        </div>
        <div class="step">
          <div class="step__number">2</div>
          <h3 class="step__title">Choose Problems</h3>
          <p class="step__description">Select from our curated collection of coding problems</p>
        </div>
        <div class="step">
          <div class="step__number">3</div>
          <h3 class="step__title">Start Coding</h3>
          <p class="step__description">Write, test, and submit your solutions</p>
        </div>
      </div>
      <vs-button 
        v-if="!authStore.isLoggedIn"
        :to="{ name: 'auth' }" 
        color="primary" 
        size="large"
        class="getting-started__cta">
        Create Free Account
      </vs-button>
    </section>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/lib/store/auth.store';

const authStore = useAuthStore();
</script>

<style scoped>
.home {
  padding: 2rem;
  max-width: 1400px;
  margin: 0 auto;
}

.hero {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 2rem;
  margin-bottom: 4rem;
}

.hero__content {
  flex: 1;
}

.hero__title {
  font-size: 3.5rem;
  line-height: 1.2;
  margin-bottom: 1.5rem;
}

.hero__subtitle {
  font-size: 1.25rem;
  color: var(--text-disable);
  margin-bottom: 2rem;
  max-width: 600px;
}

.hero__buttons {
  display: flex;
  gap: 1rem;
}

.hero__image {
  flex: 1;
  display: flex;
  justify-content: center;
}

.hero__image img {
  max-width: 100%;
  height: auto;
}

.features {
  padding: 4rem 0;
}

.features__title {
  text-align: center;
  font-size: 2.5rem;
  margin-bottom: 3rem;
}

.features__grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
}

.feature-card {
  background: var(--background);
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
  transition: transform 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-5px);
}

.feature-card__icon {
  background: var(--dark);
  width: 64px;
  height: 64px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto 1.5rem;
}

.feature-card__icon i {
  font-size: 2rem;
  color: var(--primary);
}

.feature-card__title {
  font-size: 1.25rem;
  margin-bottom: 1rem;
}

.feature-card__description {
  color: var(--text-disable);
  line-height: 1.6;
}

.getting-started {
  padding: 4rem 0;
  text-align: center;
}

.getting-started__title {
  font-size: 2.5rem;
  margin-bottom: 3rem;
}

.getting-started__steps {
  display: flex;
  justify-content: center;
  gap: 3rem;
  margin-bottom: 3rem;
}

.step {
  flex: 1;
  max-width: 300px;
}

.step__number {
  width: 48px;
  height: 48px;
  background: var(--primary);
  color: var(--dark);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  font-weight: bold;
  margin: 0 auto 1rem;
}

.step__title {
  font-size: 1.25rem;
  margin-bottom: 0.5rem;
}

.step__description {
  color: var(--text-disable);
}

.getting-started__cta {
  margin-top: 2rem;
}

@media (max-width: 768px) {
  .hero {
    flex-direction: column;
    text-align: center;
  }

  .hero__buttons {
    justify-content: center;
  }

  .hero__title {
    font-size: 2.5rem;
  }

  .getting-started__steps {
    flex-direction: column;
    align-items: center;
  }

  .step {
    text-align: center;
  }
}
</style>
