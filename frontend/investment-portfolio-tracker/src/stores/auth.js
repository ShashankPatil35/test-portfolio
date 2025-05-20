import { defineStore } from 'pinia'
import axios from 'axios'

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: localStorage.getItem('token') || null,
    user: JSON.parse(localStorage.getItem('user')) || null
  }),
  
  getters: {
    isAuthenticated: (state) => !!state.token,
    isAdmin: (state) => state.user?.role === 'ADMIN'
  },
  
  actions: {
    async login(email, password) {
      try {
        const response = await axios.post('http://localhost:8080/auth/login', {
          email,
          passwordHash: password
        })
        
        this.token = response.data
        localStorage.setItem('token', this.token)
        
        // Get user profile
        const userResponse = await axios.get('http://localhost:8080/user/profile', {
          headers: { Authorization: `Bearer ${this.token}` }
        })
        
        this.user = userResponse.data
        localStorage.setItem('user', JSON.stringify(this.user))
        
        return true
      } catch (error) {
        console.error('Login error:', error)
        throw error
      }
    },
    
    async register(userData) {
      try {
        await axios.post('http://localhost:8080/auth/register', userData)
        return true
      } catch (error) {
        console.error('Registration error:', error)
        throw error
      }
    },
    
    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem('token')
      localStorage.removeItem('user')
    }
  }
})