import { defineStore } from "pinia";

export default defineStore("indexStore", {
  state: () => ({
    location: 0
  }),
  getters: {
    getLocation: (status) => `${status.location}`
  },
  actions: {
    updateLocation(local) {
      this.location = local;
    }
  }
})