<template>
  <div class="min-h-screen bg-gray-50">
    <nav v-if="isAuthenticated" class="bg-white shadow">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16">
          <div class="flex">
            <router-link to="/dashboard" class="flex-shrink-0 flex items-center">
              Investment Tracker
            </router-link>
            <div class="hidden sm:ml-6 sm:flex sm:space-x-8">
              <router-link to="/dashboard" class="nav-link">Dashboard</router-link>
              <router-link to="/investments" class="nav-link">Investments</router-link>
              <router-link to="/portfolio" class="nav-link">Portfolio</router-link>
              <router-link to="/transactions" class="nav-link">Transactions</router-link>
              <router-link to="/support" class="nav-link">Support</router-link>
              <router-link v-if="isAdmin" to="/admin" class="nav-link">Admin</router-link>
            </div>
          </div>
          <div class="flex items-center">
            <button @click="logout" class="text-gray-500 hover:text-gray-700">
              Logout
            </button>
          </div>
        </div>
      </div>
    </nav>

    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from './stores/auth'

const router = useRouter()
const authStore = useAuthStore()

const isAuthenticated = computed(() => authStore.isAuthenticated)
const isAdmin = computed(() => authStore.isAdmin)

const logout = () => {
  authStore.logout()
  router.push('/login')
}
</script>

<style>
.nav-link {
  @apply inline-flex items-center px-1 pt-1 text-sm font-medium text-gray-500 hover:text-gray-700;
}

.nav-link.router-link-active {
  @apply border-b-2 border-indigo-500 text-gray-900;
}
</style>